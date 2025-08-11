# 🔐 Firebase Authentication Implementation Guide

## 📋 Overview

This guide provides step-by-step instructions for implementing Firebase Authentication in WealthWise. Firebase Auth will enable user accounts, data synchronization across devices, and secure leaderboard functionality.

## 🎯 Authentication Methods to Implement

1. **Email/Password** - Primary authentication method
2. **Google Sign-In** - Social authentication for convenience
3. **Anonymous Auth** - Allow users to try the app without signing up
4. **Account Linking** - Convert anonymous users to permanent accounts

## 📦 Dependencies Setup

### 1. Add Firebase Auth Dependencies

Add these to `app/build.gradle`:

```kotlin
dependencies {
    // Firebase Auth
    implementation 'com.google.firebase:firebase-auth:22.3.0'
    
    // Google Sign-In
    implementation 'com.google.android.gms:play-services-auth:20.7.0'
    
    // Coroutines support for Firebase
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3'
    
    // Existing dependencies...
}
```

### 2. Enable Authentication in Firebase Console

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Select your WealthWise project
3. Navigate to **Authentication** → **Sign-in method**
4. Enable the following providers:
   - **Email/Password**
   - **Google**
   - **Anonymous**

### 3. Configure Google Sign-In

1. In Firebase Console → Authentication → Sign-in method
2. Click **Google** → **Enable**
3. Add your app's SHA-1 fingerprint:
   ```bash
   # Debug keystore (for development)
   keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
   ```
4. Download updated `google-services.json`
5. Replace the existing file in `app/`

## 🏗️ Implementation Structure

### 1. Create Authentication Repository

Create `app/src/main/java/com/wealthwise/data/repository/AuthRepository.kt`:

```kotlin
package com.wealthwise.data.repository

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

sealed class AuthResult {
    object Loading : AuthResult()
    data class Success(val user: FirebaseUser?) : AuthResult()
    data class Error(val message: String) : AuthResult()
}

@Singleton
class AuthRepository @Inject constructor() {
    
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    
    val currentUser: FirebaseUser?
        get() = auth.currentUser
    
    fun getAuthStateFlow(): Flow<FirebaseUser?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose { auth.removeAuthStateListener(authStateListener) }
    }
    
    suspend fun signInWithEmail(email: String, password: String): AuthResult {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            AuthResult.Success(result.user)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Sign in failed")
        }
    }
    
    suspend fun signUpWithEmail(email: String, password: String, displayName: String): AuthResult {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(
                com.google.firebase.auth.UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .build()
            )?.await()
            AuthResult.Success(result.user)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Sign up failed")
        }
    }
    
    suspend fun signInWithGoogle(idToken: String): AuthResult {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            AuthResult.Success(result.user)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Google sign in failed")
        }
    }
    
    suspend fun signInAnonymously(): AuthResult {
        return try {
            val result = auth.signInAnonymously().await()
            AuthResult.Success(result.user)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Anonymous sign in failed")
        }
    }
    
    suspend fun linkWithEmail(email: String, password: String): AuthResult {
        return try {
            val credential = com.google.firebase.auth.EmailAuthProvider.getCredential(email, password)
            val result = currentUser?.linkWithCredential(credential)?.await()
            AuthResult.Success(result?.user)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Account linking failed")
        }
    }
    
    fun signOut() {
        auth.signOut()
    }
    
    suspend fun resetPassword(email: String): AuthResult {
        return try {
            auth.sendPasswordResetEmail(email).await()
            AuthResult.Success(null)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Password reset failed")
        }
    }
    
    fun isSignedIn(): Boolean = currentUser != null
    
    fun isAnonymous(): Boolean = currentUser?.isAnonymous ?: false
    
    fun getUserDisplayName(): String = currentUser?.displayName ?: "Player"
    
    fun getUserEmail(): String = currentUser?.email ?: ""
}
```

### 2. Create Authentication ViewModel

Create `app/src/main/java/com/wealthwise/viewmodel/AuthViewModel.kt`:

```kotlin
package com.wealthwise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wealthwise.data.repository.AuthRepository
import com.wealthwise.data.repository.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean = false,
    val user: com.google.firebase.auth.FirebaseUser? = null,
    val errorMessage: String? = null,
    val isSignedIn: Boolean = false,
    val isAnonymous: Boolean = false,
    val showLinkAccountDialog: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()
    
    init {
        // Monitor auth state changes
        viewModelScope.launch {
            authRepository.getAuthStateFlow().collect { user ->
                _uiState.value = _uiState.value.copy(
                    user = user,
                    isSignedIn = user != null,
                    isAnonymous = user?.isAnonymous ?: false
                )
            }
        }
    }
    
    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            when (val result = authRepository.signInWithEmail(email, password)) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        user = result.user
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
                is AuthResult.Loading -> {
                    // Already handling loading state
                }
            }
        }
    }
    
    fun signUpWithEmail(email: String, password: String, displayName: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            when (val result = authRepository.signUpWithEmail(email, password, displayName)) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        user = result.user
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
                is AuthResult.Loading -> {
                    // Already handling loading state
                }
            }
        }
    }
    
    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            when (val result = authRepository.signInWithGoogle(idToken)) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        user = result.user
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
                is AuthResult.Loading -> {
                    // Already handling loading state
                }
            }
        }
    }
    
    fun signInAnonymously() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            when (val result = authRepository.signInAnonymously()) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        user = result.user
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
                is AuthResult.Loading -> {
                    // Already handling loading state
                }
            }
        }
    }
    
    fun linkWithEmail(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            when (val result = authRepository.linkWithEmail(email, password)) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        user = result.user,
                        showLinkAccountDialog = false
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
                is AuthResult.Loading -> {
                    // Already handling loading state
                }
            }
        }
    }
    
    fun signOut() {
        authRepository.signOut()
    }
    
    fun resetPassword(email: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            when (val result = authRepository.resetPassword(email)) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Password reset email sent"
                    )
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
                is AuthResult.Loading -> {
                    // Already handling loading state
                }
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
    
    fun showLinkAccountDialog() {
        _uiState.value = _uiState.value.copy(showLinkAccountDialog = true)
    }
    
    fun hideLinkAccountDialog() {
        _uiState.value = _uiState.value.copy(showLinkAccountDialog = false)
    }
}
```

### 3. Create Authentication Screens

Create `app/src/main/java/com/wealthwise/ui/screens/auth/SignInScreen.kt`:

```kotlin
package com.wealthwise.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wealthwise.ui.theme.*

@Composable
fun SignInScreen(
    onSignInClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
    onGoogleSignInClick: () -> Unit,
    onAnonymousSignInClick: () -> Unit,
    onForgotPasswordClick: (String) -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showForgotPassword by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App Logo/Title
        Icon(
            imageVector = Icons.Default.AccountBalance,
            contentDescription = "WealthWise",
            modifier = Modifier.size(80.dp),
            tint = Emerald500
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "WealthWise",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Gray900
        )
        
        Text(
            text = "Learn. Save. Grow.",
            fontSize = 16.sp,
            color = Gray600
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email"
                )
            }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password"
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )
        
        // Error Message
        errorMessage?.let { message ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                color = Red500,
                fontSize = 14.sp
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Forgot Password
        TextButton(
            onClick = { showForgotPassword = true }
        ) {
            Text("Forgot Password?")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Sign In Button
        Button(
            onClick = { onSignInClick(email, password) },
            modifier = Modifier.fillMaxWidth(),
            enabled = email.isNotEmpty() && password.isNotEmpty() && !isLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = Emerald500
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    color = androidx.compose.ui.graphics.Color.White
                )
            } else {
                Text("Sign In")
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Google Sign In
        OutlinedButton(
            onClick = onGoogleSignInClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Icon(
                imageVector = Icons.Default.Login,
                contentDescription = "Google",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Continue with Google")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Sign Up Link
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account?",
                color = Gray600
            )
            TextButton(onClick = onSignUpClick) {
                Text("Sign Up")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Try Without Account
        TextButton(
            onClick = onAnonymousSignInClick,
            enabled = !isLoading
        ) {
            Text(
                text = "Try without an account",
                color = Gray500
            )
        }
    }
    
    // Forgot Password Dialog
    if (showForgotPassword) {
        var resetEmail by remember { mutableStateOf(email) }
        
        AlertDialog(
            onDismissRequest = { showForgotPassword = false },
            title = { Text("Reset Password") },
            text = {
                Column {
                    Text("Enter your email address to receive a password reset link.")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = resetEmail,
                        onValueChange = { resetEmail = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onForgotPasswordClick(resetEmail)
                        showForgotPassword = false
                    },
                    enabled = resetEmail.isNotEmpty()
                ) {
                    Text("Send Reset Link")
                }
            },
            dismissButton = {
                TextButton(onClick = { showForgotPassword = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
```

### 4. Create Sign Up Screen

Create `app/src/main/java/com/wealthwise/ui/screens/auth/SignUpScreen.kt`:

```kotlin
package com.wealthwise.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wealthwise.ui.theme.*

@Composable
fun SignUpScreen(
    onSignUpClick: (String, String, String) -> Unit,
    onSignInClick: () -> Unit,
    onBackClick: () -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var agreedToTerms by remember { mutableStateOf(false) }
    
    val passwordsMatch = password == confirmPassword
    val isFormValid = name.isNotEmpty() && 
                     email.isNotEmpty() && 
                     password.length >= 6 && 
                     passwordsMatch && 
                     agreedToTerms
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Back Button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Title
        Text(
            text = "Create Account",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Gray900
        )
        
        Text(
            text = "Join WealthWise and start your financial journey",
            fontSize = 16.sp,
            color = Gray600
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Name Field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Name"
                )
            }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email"
                )
            }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password"
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            supportingText = {
                Text(
                    text = "Minimum 6 characters",
                    color = if (password.length >= 6 || password.isEmpty()) Gray600 else Red500
                )
            }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Confirm Password Field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Confirm Password"
                )
            },
            trailingIcon = {
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(
                        imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            isError = confirmPassword.isNotEmpty() && !passwordsMatch,
            supportingText = {
                if (confirmPassword.isNotEmpty() && !passwordsMatch) {
                    Text(
                        text = "Passwords don't match",
                        color = Red500
                    )
                }
            }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Terms and Conditions
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = agreedToTerms,
                onCheckedChange = { agreedToTerms = it }
            )
            Text(
                text = "I agree to the Terms of Service and Privacy Policy",
                fontSize = 14.sp,
                color = Gray600
            )
        }
        
        // Error Message
        errorMessage?.let { message ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                color = Red500,
                fontSize = 14.sp
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Sign Up Button
        Button(
            onClick = { onSignUpClick(email, password, name) },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid && !isLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = Emerald500
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    color = androidx.compose.ui.graphics.Color.White
                )
            } else {
                Text("Create Account")
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Sign In Link
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account?",
                color = Gray600
            )
            TextButton(onClick = onSignInClick) {
                Text("Sign In")
            }
        }
    }
}
```

## 🔗 Integration Steps

### 1. Update Navigation

Add authentication screens to your navigation in `WealthWiseApp.kt`:

```kotlin
// Add these routes to your Screen sealed class
object SignIn : Screen("sign_in")
object SignUp : Screen("sign_up")

// Add these composables to your NavHost
composable(Screen.SignIn.route) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val authUiState by authViewModel.uiState.collectAsState()
    
    SignInScreen(
        onSignInClick = { email, password ->
            authViewModel.signInWithEmail(email, password)
        },
        onSignUpClick = {
            navController.navigate(Screen.SignUp.route)
        },
        onGoogleSignInClick = {
            // Handle Google Sign-In (implement launcher)
        },
        onAnonymousSignInClick = {
            authViewModel.signInAnonymously()
        },
        onForgotPasswordClick = { email ->
            authViewModel.resetPassword(email)
        },
        isLoading = authUiState.isLoading,
        errorMessage = authUiState.errorMessage
    )
}

composable(Screen.SignUp.route) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val authUiState by authViewModel.uiState.collectAsState()
    
    SignUpScreen(
        onSignUpClick = { email, password, name ->
            authViewModel.signUpWithEmail(email, password, name)
        },
        onSignInClick = {
            navController.popBackStack()
        },
        onBackClick = {
            navController.popBackStack()
        },
        isLoading = authUiState.isLoading,
        errorMessage = authUiState.errorMessage
    )
}
```

### 2. Update App Entry Point

Modify your app to check authentication state:

```kotlin
@Composable
fun WealthWiseApp() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val authUiState by authViewModel.uiState.collectAsState()
    
    // Determine start destination based on auth state
    val startDestination = when {
        !authUiState.isSignedIn -> Screen.SignIn.route
        authUiState.isAnonymous -> Screen.Welcome.route // Show welcome for anonymous users
        else -> Screen.Dashboard.route
    }
    
    // Rest of your app setup...
}
```

### 3. Update Firestore Security Rules

Update your Firestore rules to require authentication:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Allow authenticated users to read/write their own data
    match /users/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Allow all authenticated users to read leaderboards
    match /leaderboards/{document} {
      allow read: if request.auth != null;
      allow write: if request.auth != null && request.auth.uid == resource.data.userId;
    }
    
    // Allow anonymous users to read sample data
    match /public/{document=**} {
      allow read: if true;
    }
  }
}
```

## 🔐 Security Best Practices

### 1. Password Requirements
- Minimum 6 characters (enforced in UI)
- Consider adding complexity requirements for production

### 2. Email Verification
Add email verification for production:
```kotlin
// After successful sign up
currentUser?.sendEmailVerification()?.await()
```

### 3. Account Linking
Encourage anonymous users to create accounts:
```kotlin
// Show dialog when anonymous user has significant progress
if (authRepository.isAnonymous() && gameState.currentDay > 7) {
    showLinkAccountDialog = true
}
```

### 4. Data Migration
When linking accounts, migrate anonymous data:
```kotlin
suspend fun migrateAnonymousData(fromUserId: String, toUserId: String) {
    // Copy game state, goals, etc. from anonymous user to permanent account
}
```

## 🧪 Testing

### 1. Test Authentication Flow
- Sign in with email/password
- Sign up new account
- Google Sign-In integration
- Anonymous authentication
- Account linking
- Password reset

### 2. Test Error Scenarios
- Invalid email format
- Weak passwords
- Network connectivity issues
- Already registered email

### 3. Test Security Rules
- Verify users can only access their own data
- Test anonymous vs authenticated access
- Ensure leaderboard security

## 🚀 Deployment Checklist

- [ ] Enable required authentication methods in Firebase Console
- [ ] Add SHA-1 fingerprints for Google Sign-In
- [ ] Update Firestore security rules
- [ ] Test all authentication flows
- [ ] Implement proper error handling
- [ ] Add loading states for better UX
- [ ] Test account linking functionality
- [ ] Verify data migration works correctly

## 📚 Additional Resources

- [Firebase Auth Documentation](https://firebase.google.com/docs/auth)
- [Google Sign-In for Android](https://developers.google.com/identity/sign-in/android)
- [Firebase Security Rules](https://firebase.google.com/docs/firestore/security/get-started)

---

**🔐 Secure authentication is crucial for user data protection and app reliability!**