# 💰 WealthWise - Kenyan Financial Literacy Game

A gamified Android app that teaches young Kenyans about financial literacy through realistic 30-day simulations of different careers and financial decisions.

## 📱 Overview

WealthWise is an educational mobile game built with **Kotlin** and **Jetpack Compose** that simulates real-world financial scenarios specific to Kenya. Players choose from 4 different roles and navigate 30 days of financial decisions, learning about local financial tools like SACCOs, MMFs, Fuliza, and more.

### 🎯 Key Features

- **4 Career Roles**: Student, Boda Boda Rider, Mama Mboga, Hustler
- **Real Kenyan Financial Tools**: SACCOs, MMFs, Fuliza, Chamas, REITs, Land Investment
- **30-Day Simulation**: Daily events with meaningful choices
- **Educational Content**: Learn Center with detailed explanations
- **Leaderboard**: Firebase-powered ranking system
- **Offline-First**: Play without internet, sync when available

## 🏗️ Technical Architecture

### Tech Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material3
- **Architecture**: MVVM with StateFlow
- **Database**: Room (SQLite)
- **Cloud**: Firebase Firestore
- **Navigation**: Navigation Compose
- **Dependency Injection**: Manual DI with ViewModels

### Project Structure
```
app/src/main/java/com/wealthwise/
├── data/
│   ├── dao/                 # Room DAOs
│   ├── database/            # Database and converters
│   ├── model/               # Data classes
│   └── repository/          # Repository pattern
├── engine/
│   ├── GameEngine.kt        # Core game logic
│   └── GameEvents.kt        # Event definitions
├── ui/
│   ├── components/          # Reusable UI components
│   ├── screens/             # Screen composables
│   └── theme/               # Material3 theme
├── viewmodel/               # ViewModels with StateFlow
└── MainActivity.kt          # Main entry point
```

## 🎮 Game Mechanics

### Roles & Starting Conditions

| Role | Starting Cash | Starting Debt | Daily Income Range |
|------|---------------|---------------|-------------------|
| 🎓 Student | KSh 2,000 | KSh 30,000 (HELB) | KSh 300-800 |
| 🏍️ Boda Boda Rider | KSh 5,000 | KSh 0 | KSh 800-2,000 |
| 🥬 Mama Mboga | KSh 8,000 | KSh 0 | KSh 500-1,200 |
| 💼 Hustler | KSh 3,000 | KSh 0 | KSh 200-3,000 |

### Financial Tools Simulated

- **🏦 SACCOs**: Safe savings with 6-10% returns, loan access
- **📊 MMFs**: 8-12% returns, 7-day withdrawal
- **📱 Fuliza**: High-interest overdraft (emergency use)
- **👥 Chamas**: Group savings with rotation payouts
- **🏢 REITs**: Real estate investment trusts
- **🏡 Land**: High-value, illiquid asset
- **💳 Loans**: HELB, M-Shwari with realistic interest

### Scoring System
**Net Worth = Cash + Assets - Debt**
- Cash: Immediate liquidity
- Assets: SACCO + MMF + Land + REITs
- Debt: All outstanding loans

## 📚 Educational Value

### Learning Outcomes
- **Budgeting**: Daily income vs. expenses
- **Saving**: Understanding different savings vehicles
- **Investing**: Risk vs. return concepts
- **Debt Management**: Avoiding predatory lending
- **Emergency Planning**: Building financial resilience

### Real-World Application
All scenarios are based on actual Kenyan financial products and common situations young adults face, making the learning directly applicable to real life.

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 24+ (Android 7.0)
- Firebase account (for leaderboard)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/wealthwise.git
   cd wealthwise
   ```

2. **Set up Firebase**
   - Create a new Firebase project
   - Add an Android app with package name `com.wealthwise`
   - Download `google-services.json` to `app/` directory
   - Enable Firestore Database

3. **Build and run**
   ```bash
   ./gradlew assembleDebug
   ./gradlew installDebug
   ```

### Firebase Configuration

Replace the template values in `app/google-services.json`:
```json
{
  "project_info": {
    "project_id": "your-project-id",
    "project_number": "your-project-number"
  },
  "client": [{
    "client_info": {
      "mobilesdk_app_id": "your-app-id",
      "android_client_info": {
        "package_name": "com.wealthwise"
      }
    },
    "api_key": [{"current_key": "your-api-key"}]
  }]
}
```

## 🎨 UI/UX Design

### Design Principles
- **Material3**: Modern, accessible design
- **Kenyan Context**: Culturally relevant emojis and language
- **Financial Clarity**: Clear money displays with KSh formatting
- **Gamification**: Progress indicators, achievements, rankings

### Color Scheme
- Primary: Wealth Green (#4CAF50)
- Secondary: Wealth Gold (#FFC107)
- Error: Red for debt/warnings
- Success: Green for positive outcomes

## 🧪 Testing

Run unit tests for game logic:
```bash
./gradlew test
```

Key test coverage:
- ✅ Player calculations (net worth, assets)
- ✅ Game engine decision processing
- ✅ Financial tool simulations
- ✅ Role-specific income generation
- ✅ Database operations

## 📊 Performance

### Offline-First Architecture
- All gameplay data stored locally in Room
- Leaderboard syncs to Firebase when online
- No internet required for core gameplay

### Database Schema
- **Players**: Current game state
- **Transactions**: Financial history
- **Leaderboard**: Final scores for ranking

## 🔄 Game Flow

1. **Home Screen**: Role selection and player name
2. **Daily Turns**: Event → Decision → Result (×30 days)
3. **Wallet**: Real-time financial tracking
4. **Learn Center**: Educational content
5. **End Game**: Final score and ranking
6. **Leaderboard**: Global competition

## 🌟 Key Features in Detail

### Smart Decision Engine
- Context-aware events based on role and day
- Realistic financial consequences
- Educational feedback on every choice

### Comprehensive Financial Tracking
- Real-time net worth calculation
- Transaction history
- Asset breakdown visualization

### Educational Integration
- In-context tips during gameplay
- Detailed Learn Center with pros/cons
- Real-world application examples

## 🛣️ Future Enhancements

- **Multiplayer**: Family/friend challenges
- **Advanced Analytics**: Spending pattern insights
- **More Roles**: Teacher, Mechanic, Farmer
- **Regional Variants**: Different county scenarios
- **Achievement System**: Financial milestones

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support

For support or questions about WealthWise:
- Create an issue on GitHub
- Email: support@wealthwise.app

## 🙏 Acknowledgments

- Kenya's financial inclusion initiatives
- Local SACCOs and MMF providers
- Youth financial literacy advocates
- Open source Android community

---

**Made with ❤️ for Kenya's financial future** 