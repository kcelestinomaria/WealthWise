# 🚀 WealthWise - Build Instructions

## Quick Start (Guaranteed to Work)

### 1. **Prerequisites**
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 8 or higher
- Android SDK API 24+ 
- Git

### 2. **Clone & Setup**
```bash
git clone <your-repo-url>
cd WealthWise
```

### 3. **Firebase Setup (Optional for Testing)**
The app will run without Firebase, but for full functionality:

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create new project: "wealthwise-app" 
3. Add Android app with package: `com.wealthwise`
4. Download `google-services.json` → place in `app/` folder
5. Enable Firestore Database

**OR for quick testing:** The app includes a template `google-services.json` that allows offline-only testing.

### 4. **Build & Run**
```bash
# In Android Studio
1. Open project folder
2. Wait for Gradle sync
3. Click "Run" or Ctrl+R

# OR via command line
./gradlew assembleDebug
./gradlew installDebug
```

### 5. **Troubleshooting**

**Build Errors:**
- Clean project: `Build > Clean Project`
- Invalidate caches: `File > Invalidate Caches and Restart`
- Check internet connection for Gradle sync

**Runtime Errors:**
- Firebase: App works offline, leaderboard needs real config
- Emulator: Use API 24+ with Google Play services

### 6. **Testing**
```bash
# Run unit tests
./gradlew test

# Check specific test
./gradlew testDebugUnitTest --tests "GameEngineTest"
```

### 7. **APK Generation**
```bash
# Debug APK
./gradlew assembleDebug
# Output: app/build/outputs/apk/debug/app-debug.apk

# Release APK (after setting up signing)
./gradlew assembleRelease
```

## ✅ Expected Behavior

**First Launch:**
1. Home screen with role selection
2. Choose player name + role
3. Start 30-day simulation
4. Navigate between screens
5. Complete game and see results

**Core Features Working:**
- ✅ Role selection (4 roles)
- ✅ Daily financial events  
- ✅ Decision making system
- ✅ Real-time net worth tracking
- ✅ Wallet with asset breakdown
- ✅ Educational Learn Center
- ✅ Game completion screen
- ✅ Local leaderboard (offline)
- ⚠️ Firebase leaderboard (needs config)

## 🐛 Known Issues (Non-blocking)
- Template Firebase config → offline leaderboard only
- Charts dependency unused → no visual impact  
- No app icon → uses default Android icon

## 📱 Minimum Requirements
- **Android**: API 24 (Android 7.0)
- **RAM**: 2GB+
- **Storage**: 100MB
- **Internet**: Optional (offline-first design)

---

**🎯 Ready to test!** The app should compile and run immediately after following these steps. 