# 🏗️ WealthWise Build Instructions

## 📋 Prerequisites

- **Android Studio**: Arctic Fox (2020.3.1) or newer
- **JDK**: Java 17 or newer
- **Android SDK**: API 24+ (minimum), API 34 (target)
- **Gradle**: 8.2+ (handled by wrapper)
- **Firebase Project**: Required for leaderboard functionality

## 🚀 Quick Setup

### 1. Clone and Open Project
```bash
git clone <repository-url>
cd WealthWise
```

Open the project in Android Studio and let it sync.

### 2. Firebase Configuration (MANDATORY)

#### A. Create Firebase Project
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create a new project named "WealthWise App"
3. Enable Google Analytics (recommended)

#### B. Add Android App
1. Click "Add app" → Android
2. Package name: `com.wealthwise`
3. App nickname: "WealthWise"
4. Download `google-services.json`

#### C. Replace Configuration
Replace the placeholder `app/google-services.json` with your downloaded file.

#### D. Enable Firestore
1. In Firebase Console → Firestore Database
2. Create database in test mode
3. Create collection: `leaderboards`

### 3. Sync and Build
```bash
./gradlew clean
./gradlew build
```

## 🏆 Firebase Leaderboard Setup

### Firestore Rules
Set these security rules for the leaderboard:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /leaderboards/{document} {
      allow read: if true;
      allow write: if request.auth != null || true; // Allow anonymous writes for now
    }
  }
}
```

### Data Structure
The leaderboard collection will store documents with this structure:

```json
{
  "playerName": "John Doe",
  "netWorth": 15000.50,
  "role": "Student",
  "cash": 5000.00,
  "assets": 12000.50,
  "debt": 2000.00,
  "timestamp": "2024-01-15T10:30:00Z"
}
```

## 🛠️ Development Commands

### Clean Build
```bash
./gradlew clean build
```

### Run Tests
```bash
./gradlew test
./gradlew connectedAndroidTest
```

### Generate APK
```bash
./gradlew assembleDebug
# APK location: app/build/outputs/apk/debug/
```

### Generate Release APK
```bash
./gradlew assembleRelease
# APK location: app/build/outputs/apk/release/
```

## 🐛 Troubleshooting

### Common Issues

#### 1. "Could not resolve com.google.firebase"
**Solution**: Ensure `google-services.json` is in `app/` directory and contains valid Firebase configuration.

#### 2. "Gradle sync failed"
**Solutions**:
- Check internet connection
- Run `./gradlew --stop` then `./gradlew clean`
- Invalidate caches: File → Invalidate Caches and Restart

#### 3. "Compilation failed"
**Solutions**:
- Ensure JDK 17+ is selected in Android Studio
- Check Project Structure → SDK Location
- Verify Gradle wrapper uses version 8.2

#### 4. "Firebase not found"
**Solutions**:
- Verify `google-services.json` placement
- Check internet connection for Firebase SDK download
- Ensure Google Services plugin is applied

### Dependency Conflicts

If you encounter dependency conflicts:
```bash
./gradlew app:dependencies
```

This shows the dependency tree to identify conflicts.

## 📱 Running on Device

### Debug Build
1. Enable Developer Options on Android device
2. Enable USB Debugging
3. Connect device via USB
4. Run: `./gradlew installDebug`

### Emulator
1. Create AVD with API 24+ in Android Studio
2. Launch emulator
3. Run: `./gradlew installDebug`

## 🏗️ Project Structure

```
WealthWise/
├── app/
│   ├── src/main/java/com/wealthwise/
│   │   ├── data/           # Room database, DAOs, repositories
│   │   ├── domain/         # Business logic
│   │   ├── engine/         # Game engine and events
│   │   ├── ui/             # Compose UI components and screens
│   │   └── viewmodel/      # ViewModels for MVVM
│   ├── google-services.json # Firebase configuration
│   └── build.gradle       # App-level dependencies
├── build.gradle           # Project-level configuration
└── gradle/wrapper/        # Gradle wrapper
```

## 🎯 Key Features Implementation

### 1. Game Engine
- Located in `engine/GameEngine.kt`
- Manages 30-day game simulation
- Handles player decisions and consequences

### 2. Firebase Leaderboard
- Repository: `data/repository/LeaderboardRepository.kt`
- Automatically syncs scores to Firestore
- Offline-capable with sync when online

### 3. Room Database
- Local storage for game state
- Entities: Player, Transaction, LeaderboardEntry
- DAOs provide clean data access

### 4. Compose UI
- Modern Android UI framework
- Material 3 design system
- Responsive layouts for different screen sizes

## 📊 Performance Tips

1. **Proguard**: Enabled for release builds to reduce APK size
2. **R8**: Automatic code shrinking and obfuscation
3. **Baseline Profiles**: Consider adding for improved performance

## 🔐 Security Notes

- Firebase rules currently allow anonymous writes for development
- Consider implementing proper authentication for production
- APK signing handled automatically for debug builds
- Generate proper signing keys for release builds

## 📞 Support

If you encounter issues:
1. Check this document first
2. Review Android Studio's Build Output
3. Check Firebase Console for configuration issues
4. Ensure all prerequisites are met

---

**Built with ❤️ for Kenyan Financial Literacy** 