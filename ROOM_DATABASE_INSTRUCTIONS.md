# 🗄️ Room Database Implementation Guide

## 📋 Overview

This guide provides comprehensive instructions for implementing Room Database in WealthWise. Room will handle local data persistence for game state, user progress, goals, transactions, and offline functionality.

## 🎯 Database Requirements

### Core Entities
1. **User** - Player profile and preferences
2. **GameSession** - Current game state and progress
3. **Goal** - Financial goals with progress tracking
4. **Transaction** - Financial transactions history
5. **Achievement** - Unlocked achievements
6. **Career** - Career information and progression

### Key Features
- **Offline-first architecture** - App works without internet
- **Data synchronization** - Sync with Firebase when online
- **Migration support** - Handle database schema changes
- **Type converters** - Handle complex data types
- **Relationships** - Foreign keys and joins

## 📦 Dependencies Setup

### 1. Add Room Dependencies

Add these to `app/build.gradle`:

```kotlin
dependencies {
    // Room components
    implementation "androidx.room:room-runtime:2.6.1"
    implementation "androidx.room:room-ktx:2.6.1"
    kapt "androidx.room:room-compiler:2.6.1"
    
    // Optional - Test helpers
    testImplementation "androidx.room:room-testing:2.6.1"
    
    // Coroutines support (if not already added)
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"
    
    // Type converters for complex objects
    implementation "com.google.code.gson:gson:2.10.1"
    
    // Existing dependencies...
}

// Enable kapt
apply plugin: 'kotlin-kapt'
```

### 2. Enable Kapt in Project

Ensure kapt is enabled in your module's `build.gradle`:

```kotlin
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'  // Add this line
    // ... other plugins
}
```

## 🏗️ Database Structure Implementation

### 1. Create Type Converters

Create `app/src/main/java/com/wealthwise/data/database/Converters.kt`:

```kotlin
package com.wealthwise.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wealthwise.data.model.Goal
import com.wealthwise.data.model.GoalCategory
import java.util.*

class Converters {
    private val gson = Gson()
    
    // Date converters
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
    
    // GoalCategory converters
    @TypeConverter
    fun fromGoalCategory(category: GoalCategory): String {
        return category.name
    }
    
    @TypeConverter
    fun toGoalCategory(categoryString: String): GoalCategory {
        return GoalCategory.valueOf(categoryString)
    }
    
    // List<String> converters
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }
    
    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }
    
    // List<Goal> converters
    @TypeConverter
    fun fromGoalList(goals: List<Goal>): String {
        return gson.toJson(goals)
    }
    
    @TypeConverter
    fun toGoalList(goalsString: String): List<Goal> {
        val listType = object : TypeToken<List<Goal>>() {}.type
        return gson.fromJson(goalsString, listType)
    }
    
    // Map<String, Any> converters for flexible data storage
    @TypeConverter
    fun fromStringMap(value: Map<String, Any>): String {
        return gson.toJson(value)
    }
    
    @TypeConverter
    fun toStringMap(value: String): Map<String, Any> {
        val mapType = object : TypeToken<Map<String, Any>>() {}.type
        return gson.fromJson(value, mapType)
    }
}
```

### 2. Create Entity Classes

Create `app/src/main/java/com/wealthwise/data/database/entities/UserEntity.kt`:

```kotlin
package com.wealthwise.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: String = UUID.randomUUID().toString(),
    
    @ColumnInfo(name = "firebase_uid")
    val firebaseUid: String? = null,
    
    @ColumnInfo(name = "display_name")
    val displayName: String,
    
    @ColumnInfo(name = "email")
    val email: String? = null,
    
    @ColumnInfo(name = "is_anonymous")
    val isAnonymous: Boolean = false,
    
    @ColumnInfo(name = "profile_image_url")
    val profileImageUrl: String? = null,
    
    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),
    
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date = Date(),
    
    @ColumnInfo(name = "preferences")
    val preferences: Map<String, Any> = emptyMap(),
    
    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false
)
```

Create `app/src/main/java/com/wealthwise/data/database/entities/GameSessionEntity.kt`:

```kotlin
package com.wealthwise.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.wealthwise.data.model.Goal
import java.util.*

@Entity(
    tableName = "game_sessions",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GameSessionEntity(
    @PrimaryKey
    @ColumnInfo(name = "session_id")
    val sessionId: String = UUID.randomUUID().toString(),
    
    @ColumnInfo(name = "user_id", index = true)
    val userId: String,
    
    @ColumnInfo(name = "current_day")
    val currentDay: Int = 1,
    
    @ColumnInfo(name = "balance")
    val balance: Int = 0,
    
    @ColumnInfo(name = "savings")
    val savings: Int = 0,
    
    @ColumnInfo(name = "debt")
    val debt: Int = 0,
    
    @ColumnInfo(name = "monthly_income")
    val monthlyIncome: Int = 45000,
    
    @ColumnInfo(name = "monthly_expenses")
    val monthlyExpenses: Int = 35000,
    
    @ColumnInfo(name = "career")
    val career: String = "Teacher",
    
    @ColumnInfo(name = "career_id")
    val careerId: String? = null,
    
    @ColumnInfo(name = "goals")
    val goals: List<Goal> = emptyList(),
    
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false,
    
    @ColumnInfo(name = "completion_score")
    val completionScore: Int = 0,
    
    @ColumnInfo(name = "started_at")
    val startedAt: Date = Date(),
    
    @ColumnInfo(name = "completed_at")
    val completedAt: Date? = null,
    
    @ColumnInfo(name = "last_played_at")
    val lastPlayedAt: Date = Date(),
    
    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false
)
```

Create `app/src/main/java/com/wealthwise/data/database/entities/GoalEntity.kt`:

```kotlin
package com.wealthwise.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.wealthwise.data.model.GoalCategory
import java.util.*

@Entity(
    tableName = "goals",
    foreignKeys = [
        ForeignKey(
            entity = GameSessionEntity::class,
            parentColumns = ["session_id"],
            childColumns = ["session_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GoalEntity(
    @PrimaryKey
    @ColumnInfo(name = "goal_id")
    val goalId: String = UUID.randomUUID().toString(),
    
    @ColumnInfo(name = "session_id", index = true)
    val sessionId: String,
    
    @ColumnInfo(name = "title")
    val title: String,
    
    @ColumnInfo(name = "description")
    val description: String,
    
    @ColumnInfo(name = "target_amount")
    val targetAmount: Int,
    
    @ColumnInfo(name = "current_amount")
    val currentAmount: Int = 0,
    
    @ColumnInfo(name = "category")
    val category: GoalCategory,
    
    @ColumnInfo(name = "icon")
    val icon: String,
    
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false,
    
    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),
    
    @ColumnInfo(name = "completed_at")
    val completedAt: Date? = null,
    
    @ColumnInfo(name = "priority")
    val priority: Int = 0,
    
    @ColumnInfo(name = "deadline")
    val deadline: Date? = null,
    
    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false
)
```

Create `app/src/main/java/com/wealthwise/data/database/entities/TransactionEntity.kt`:

```kotlin
package com.wealthwise.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

enum class TransactionType {
    INCOME, EXPENSE, INVESTMENT, LOAN, GOAL_CONTRIBUTION, BILL_PAYMENT
}

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = GameSessionEntity::class,
            parentColumns = ["session_id"],
            childColumns = ["session_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TransactionEntity(
    @PrimaryKey
    @ColumnInfo(name = "transaction_id")
    val transactionId: String = UUID.randomUUID().toString(),
    
    @ColumnInfo(name = "session_id", index = true)
    val sessionId: String,
    
    @ColumnInfo(name = "type")
    val type: TransactionType,
    
    @ColumnInfo(name = "amount")
    val amount: Int,
    
    @ColumnInfo(name = "description")
    val description: String,
    
    @ColumnInfo(name = "category")
    val category: String? = null,
    
    @ColumnInfo(name = "day")
    val day: Int,
    
    @ColumnInfo(name = "balance_before")
    val balanceBefore: Int,
    
    @ColumnInfo(name = "balance_after")
    val balanceAfter: Int,
    
    @ColumnInfo(name = "goal_id")
    val goalId: String? = null,
    
    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),
    
    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false
)
```

Create `app/src/main/java/com/wealthwise/data/database/entities/AchievementEntity.kt`:

```kotlin
package com.wealthwise.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "achievements",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AchievementEntity(
    @PrimaryKey
    @ColumnInfo(name = "achievement_id")
    val achievementId: String,
    
    @ColumnInfo(name = "user_id", index = true)
    val userId: String,
    
    @ColumnInfo(name = "title")
    val title: String,
    
    @ColumnInfo(name = "description")
    val description: String,
    
    @ColumnInfo(name = "icon")
    val icon: String,
    
    @ColumnInfo(name = "is_unlocked")
    val isUnlocked: Boolean = false,
    
    @ColumnInfo(name = "unlocked_at")
    val unlockedAt: Date? = null,
    
    @ColumnInfo(name = "progress")
    val progress: Int = 0,
    
    @ColumnInfo(name = "target")
    val target: Int,
    
    @ColumnInfo(name = "reward_amount")
    val rewardAmount: Int = 0,
    
    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false
)
```

### 3. Create DAO Interfaces

Create `app/src/main/java/com/wealthwise/data/database/dao/UserDao.kt`:

```kotlin
package com.wealthwise.data.database.dao

import androidx.room.*
import com.wealthwise.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    
    @Query("SELECT * FROM users WHERE user_id = :userId")
    suspend fun getUserById(userId: String): UserEntity?
    
    @Query("SELECT * FROM users WHERE firebase_uid = :firebaseUid")
    suspend fun getUserByFirebaseUid(firebaseUid: String): UserEntity?
    
    @Query("SELECT * FROM users WHERE is_anonymous = :isAnonymous LIMIT 1")
    suspend fun getAnonymousUser(isAnonymous: Boolean = true): UserEntity?
    
    @Query("SELECT * FROM users ORDER BY created_at DESC")
    fun getAllUsersFlow(): Flow<List<UserEntity>>
    
    @Query("SELECT * FROM users WHERE is_synced = 0")
    suspend fun getUnsyncedUsers(): List<UserEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long
    
    @Update
    suspend fun updateUser(user: UserEntity)
    
    @Delete
    suspend fun deleteUser(user: UserEntity)
    
    @Query("UPDATE users SET is_synced = 1 WHERE user_id = :userId")
    suspend fun markUserAsSynced(userId: String)
    
    @Query("DELETE FROM users WHERE user_id = :userId")
    suspend fun deleteUserById(userId: String)
}
```

Create `app/src/main/java/com/wealthwise/data/database/dao/GameSessionDao.kt`:

```kotlin
package com.wealthwise.data.database.dao

import androidx.room.*
import com.wealthwise.data.database.entities.GameSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameSessionDao {
    
    @Query("SELECT * FROM game_sessions WHERE user_id = :userId ORDER BY last_played_at DESC")
    fun getSessionsByUserFlow(userId: String): Flow<List<GameSessionEntity>>
    
    @Query("SELECT * FROM game_sessions WHERE user_id = :userId AND is_completed = 0 LIMIT 1")
    suspend fun getCurrentSession(userId: String): GameSessionEntity?
    
    @Query("SELECT * FROM game_sessions WHERE user_id = :userId AND is_completed = 0 LIMIT 1")
    fun getCurrentSessionFlow(userId: String): Flow<GameSessionEntity?>
    
    @Query("SELECT * FROM game_sessions WHERE session_id = :sessionId")
    suspend fun getSessionById(sessionId: String): GameSessionEntity?
    
    @Query("SELECT * FROM game_sessions WHERE is_completed = 1 ORDER BY completion_score DESC LIMIT :limit")
    suspend fun getTopCompletedSessions(limit: Int = 10): List<GameSessionEntity>
    
    @Query("SELECT * FROM game_sessions WHERE is_synced = 0")
    suspend fun getUnsyncedSessions(): List<GameSessionEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: GameSessionEntity): Long
    
    @Update
    suspend fun updateSession(session: GameSessionEntity)
    
    @Delete
    suspend fun deleteSession(session: GameSessionEntity)
    
    @Query("UPDATE game_sessions SET is_synced = 1 WHERE session_id = :sessionId")
    suspend fun markSessionAsSynced(sessionId: String)
    
    @Query("UPDATE game_sessions SET last_played_at = :timestamp WHERE session_id = :sessionId")
    suspend fun updateLastPlayed(sessionId: String, timestamp: java.util.Date)
    
    @Query("DELETE FROM game_sessions WHERE session_id = :sessionId")
    suspend fun deleteSessionById(sessionId: String)
}
```

Create `app/src/main/java/com/wealthwise/data/database/dao/GoalDao.kt`:

```kotlin
package com.wealthwise.data.database.dao

import androidx.room.*
import com.wealthwise.data.database.entities.GoalEntity
import com.wealthwise.data.model.GoalCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    
    @Query("SELECT * FROM goals WHERE session_id = :sessionId ORDER BY priority DESC, created_at ASC")
    fun getGoalsBySessionFlow(sessionId: String): Flow<List<GoalEntity>>
    
    @Query("SELECT * FROM goals WHERE session_id = :sessionId AND is_completed = 0 ORDER BY priority DESC")
    suspend fun getActiveGoals(sessionId: String): List<GoalEntity>
    
    @Query("SELECT * FROM goals WHERE goal_id = :goalId")
    suspend fun getGoalById(goalId: String): GoalEntity?
    
    @Query("SELECT * FROM goals WHERE session_id = :sessionId AND category = :category")
    suspend fun getGoalsByCategory(sessionId: String, category: GoalCategory): List<GoalEntity>
    
    @Query("SELECT * FROM goals WHERE is_completed = 1 AND session_id = :sessionId")
    suspend fun getCompletedGoals(sessionId: String): List<GoalEntity>
    
    @Query("SELECT * FROM goals WHERE is_synced = 0")
    suspend fun getUnsyncedGoals(): List<GoalEntity>
    
    @Query("SELECT COUNT(*) FROM goals WHERE session_id = :sessionId AND is_completed = 1")
    suspend fun getCompletedGoalsCount(sessionId: String): Int
    
    @Query("SELECT SUM(target_amount) FROM goals WHERE session_id = :sessionId")
    suspend fun getTotalGoalAmount(sessionId: String): Int?
    
    @Query("SELECT SUM(current_amount) FROM goals WHERE session_id = :sessionId")
    suspend fun getTotalSavedAmount(sessionId: String): Int?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: GoalEntity): Long
    
    @Update
    suspend fun updateGoal(goal: GoalEntity)
    
    @Delete
    suspend fun deleteGoal(goal: GoalEntity)
    
    @Query("UPDATE goals SET current_amount = :amount, is_completed = :isCompleted WHERE goal_id = :goalId")
    suspend fun updateGoalProgress(goalId: String, amount: Int, isCompleted: Boolean)
    
    @Query("UPDATE goals SET is_synced = 1 WHERE goal_id = :goalId")
    suspend fun markGoalAsSynced(goalId: String)
    
    @Query("DELETE FROM goals WHERE goal_id = :goalId")
    suspend fun deleteGoalById(goalId: String)
}
```

Create `app/src/main/java/com/wealthwise/data/database/dao/TransactionDao.kt`:

```kotlin
package com.wealthwise.data.database.dao

import androidx.room.*
import com.wealthwise.data.database.entities.TransactionEntity
import com.wealthwise.data.database.entities.TransactionType
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    
    @Query("SELECT * FROM transactions WHERE session_id = :sessionId ORDER BY created_at DESC")
    fun getTransactionsBySessionFlow(sessionId: String): Flow<List<TransactionEntity>>
    
    @Query("SELECT * FROM transactions WHERE session_id = :sessionId ORDER BY created_at DESC LIMIT :limit")
    suspend fun getRecentTransactions(sessionId: String, limit: Int = 20): List<TransactionEntity>
    
    @Query("SELECT * FROM transactions WHERE session_id = :sessionId AND type = :type ORDER BY created_at DESC")
    suspend fun getTransactionsByType(sessionId: String, type: TransactionType): List<TransactionEntity>
    
    @Query("SELECT * FROM transactions WHERE goal_id = :goalId ORDER BY created_at DESC")
    suspend fun getTransactionsByGoal(goalId: String): List<TransactionEntity>
    
    @Query("SELECT SUM(amount) FROM transactions WHERE session_id = :sessionId AND type = :type")
    suspend fun getTotalAmountByType(sessionId: String, type: TransactionType): Int?
    
    @Query("SELECT * FROM transactions WHERE is_synced = 0")
    suspend fun getUnsyncedTransactions(): List<TransactionEntity>
    
    @Query("SELECT COUNT(*) FROM transactions WHERE session_id = :sessionId")
    suspend fun getTransactionCount(sessionId: String): Int
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(transactions: List<TransactionEntity>)
    
    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)
    
    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)
    
    @Query("UPDATE transactions SET is_synced = 1 WHERE transaction_id = :transactionId")
    suspend fun markTransactionAsSynced(transactionId: String)
    
    @Query("DELETE FROM transactions WHERE session_id = :sessionId")
    suspend fun deleteTransactionsBySession(sessionId: String)
    
    @Query("DELETE FROM transactions WHERE transaction_id = :transactionId")
    suspend fun deleteTransactionById(transactionId: String)
}
```

### 4. Create Database Class

Create `app/src/main/java/com/wealthwise/data/database/WealthWiseDatabase.kt`:

```kotlin
package com.wealthwise.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import android.content.Context
import com.wealthwise.data.database.dao.*
import com.wealthwise.data.database.entities.*

@Database(
    entities = [
        UserEntity::class,
        GameSessionEntity::class,
        GoalEntity::class,
        TransactionEntity::class,
        AchievementEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class WealthWiseDatabase : RoomDatabase() {
    
    abstract fun userDao(): UserDao
    abstract fun gameSessionDao(): GameSessionDao
    abstract fun goalDao(): GoalDao
    abstract fun transactionDao(): TransactionDao
    abstract fun achievementDao(): AchievementDao
    
    companion object {
        const val DATABASE_NAME = "wealthwise_database"
        
        @Volatile
        private var INSTANCE: WealthWiseDatabase? = null
        
        fun getDatabase(context: Context): WealthWiseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WealthWiseDatabase::class.java,
                    DATABASE_NAME
                )
                    .addTypeConverters(Converters())
                    .fallbackToDestructiveMigration() // For development - remove for production
                    .addCallback(DatabaseCallback())
                    .build()
                    
                INSTANCE = instance
                instance
            }
        }
        
        // Database callback for initial setup
        private class DatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Initialize with default data if needed
            }
        }
        
        // Example migration for version updates
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add new column example:
                // database.execSQL("ALTER TABLE users ADD COLUMN phone_number TEXT")
            }
        }
    }
}
```

Create the missing `AchievementDao.kt`:

```kotlin
package com.wealthwise.data.database.dao

import androidx.room.*
import com.wealthwise.data.database.entities.AchievementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementDao {
    
    @Query("SELECT * FROM achievements WHERE user_id = :userId ORDER BY unlocked_at DESC")
    fun getAchievementsByUserFlow(userId: String): Flow<List<AchievementEntity>>
    
    @Query("SELECT * FROM achievements WHERE user_id = :userId AND is_unlocked = 1")
    suspend fun getUnlockedAchievements(userId: String): List<AchievementEntity>
    
    @Query("SELECT * FROM achievements WHERE achievement_id = :achievementId")
    suspend fun getAchievementById(achievementId: String): AchievementEntity?
    
    @Query("SELECT COUNT(*) FROM achievements WHERE user_id = :userId AND is_unlocked = 1")
    suspend fun getUnlockedAchievementsCount(userId: String): Int
    
    @Query("SELECT * FROM achievements WHERE is_synced = 0")
    suspend fun getUnsyncedAchievements(): List<AchievementEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievement(achievement: AchievementEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievements(achievements: List<AchievementEntity>)
    
    @Update
    suspend fun updateAchievement(achievement: AchievementEntity)
    
    @Delete
    suspend fun deleteAchievement(achievement: AchievementEntity)
    
    @Query("UPDATE achievements SET is_unlocked = 1, unlocked_at = :unlockedAt WHERE achievement_id = :achievementId")
    suspend fun unlockAchievement(achievementId: String, unlockedAt: java.util.Date)
    
    @Query("UPDATE achievements SET progress = :progress WHERE achievement_id = :achievementId")
    suspend fun updateAchievementProgress(achievementId: String, progress: Int)
    
    @Query("UPDATE achievements SET is_synced = 1 WHERE achievement_id = :achievementId")
    suspend fun markAchievementAsSynced(achievementId: String)
}
```

## 🏗️ Repository Implementation

### 1. Create Database Repository

Create `app/src/main/java/com/wealthwise/data/repository/DatabaseRepository.kt`:

```kotlin
package com.wealthwise.data.repository

import com.wealthwise.data.database.WealthWiseDatabase
import com.wealthwise.data.database.entities.*
import com.wealthwise.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import java.util.*

@Singleton
class DatabaseRepository @Inject constructor(
    private val database: WealthWiseDatabase
) {
    
    private val userDao = database.userDao()
    private val gameSessionDao = database.gameSessionDao()
    private val goalDao = database.goalDao()
    private val transactionDao = database.transactionDao()
    private val achievementDao = database.achievementDao()
    
    // User operations
    suspend fun createUser(
        displayName: String,
        email: String? = null,
        firebaseUid: String? = null,
        isAnonymous: Boolean = false
    ): String {
        val user = UserEntity(
            displayName = displayName,
            email = email,
            firebaseUid = firebaseUid,
            isAnonymous = isAnonymous
        )
        userDao.insertUser(user)
        return user.userId
    }
    
    suspend fun getUserById(userId: String): UserEntity? = userDao.getUserById(userId)
    
    suspend fun getUserByFirebaseUid(firebaseUid: String): UserEntity? = 
        userDao.getUserByFirebaseUid(firebaseUid)
    
    suspend fun getAnonymousUser(): UserEntity? = userDao.getAnonymousUser()
    
    suspend fun updateUser(user: UserEntity) = userDao.updateUser(user)
    
    // Game session operations
    suspend fun createGameSession(
        userId: String,
        career: String,
        monthlyIncome: Int,
        monthlyExpenses: Int,
        careerId: String? = null
    ): String {
        val startingBalance = (monthlyIncome * 0.3).toInt()
        val session = GameSessionEntity(
            userId = userId,
            balance = startingBalance,
            career = career,
            monthlyIncome = monthlyIncome,
            monthlyExpenses = monthlyExpenses,
            careerId = careerId
        )
        gameSessionDao.insertSession(session)
        return session.sessionId
    }
    
    suspend fun getCurrentSession(userId: String): GameSessionEntity? = 
        gameSessionDao.getCurrentSession(userId)
    
    fun getCurrentSessionFlow(userId: String): Flow<GameSessionEntity?> = 
        gameSessionDao.getCurrentSessionFlow(userId)
    
    suspend fun updateGameSession(session: GameSessionEntity) = 
        gameSessionDao.updateSession(session)
    
    suspend fun completeGameSession(sessionId: String, finalScore: Int) {
        val session = gameSessionDao.getSessionById(sessionId)
        session?.let {
            val completedSession = it.copy(
                isCompleted = true,
                completionScore = finalScore,
                completedAt = Date()
            )
            gameSessionDao.updateSession(completedSession)
        }
    }
    
    // Goal operations
    suspend fun createGoal(
        sessionId: String,
        title: String,
        description: String,
        targetAmount: Int,
        category: GoalCategory,
        icon: String
    ): String {
        val goal = GoalEntity(
            sessionId = sessionId,
            title = title,
            description = description,
            targetAmount = targetAmount,
            category = category,
            icon = icon
        )
        goalDao.insertGoal(goal)
        return goal.goalId
    }
    
    fun getGoalsBySessionFlow(sessionId: String): Flow<List<Goal>> = 
        goalDao.getGoalsBySessionFlow(sessionId).map { entities ->
            entities.map { it.toGoal() }
        }
    
    suspend fun updateGoalProgress(goalId: String, amount: Int): Boolean {
        val goal = goalDao.getGoalById(goalId) ?: return false
        val newAmount = (goal.currentAmount + amount).coerceAtMost(goal.targetAmount)
        val isCompleted = newAmount >= goal.targetAmount
        
        goalDao.updateGoalProgress(goalId, newAmount, isCompleted)
        
        if (isCompleted && !goal.isCompleted) {
            // Goal completed for the first time
            return true
        }
        return false
    }
    
    suspend fun deleteGoal(goalId: String) = goalDao.deleteGoalById(goalId)
    
    // Transaction operations
    suspend fun recordTransaction(
        sessionId: String,
        type: TransactionType,
        amount: Int,
        description: String,
        balanceBefore: Int,
        balanceAfter: Int,
        day: Int,
        goalId: String? = null,
        category: String? = null
    ): String {
        val transaction = TransactionEntity(
            sessionId = sessionId,
            type = type,
            amount = amount,
            description = description,
            balanceBefore = balanceBefore,
            balanceAfter = balanceAfter,
            day = day,
            goalId = goalId,
            category = category
        )
        transactionDao.insertTransaction(transaction)
        return transaction.transactionId
    }
    
    fun getTransactionsBySessionFlow(sessionId: String): Flow<List<TransactionEntity>> = 
        transactionDao.getTransactionsBySessionFlow(sessionId)
    
    suspend fun getRecentTransactions(sessionId: String, limit: Int = 10): List<TransactionEntity> = 
        transactionDao.getRecentTransactions(sessionId, limit)
    
    // Achievement operations
    suspend fun initializeAchievements(userId: String) {
        val defaultAchievements = listOf(
            AchievementEntity(
                achievementId = "first_investment",
                userId = userId,
                title = "First Investment",
                description = "Made your first investment",
                icon = "trending_up",
                target = 1
            ),
            AchievementEntity(
                achievementId = "goal_achiever",
                userId = userId,
                title = "Goal Achiever",
                description = "Completed your first financial goal",
                icon = "star",
                target = 1
            ),
            AchievementEntity(
                achievementId = "saver_10k",
                userId = userId,
                title = "Saver",
                description = "Saved Ksh 10,000",
                icon = "account_balance",
                target = 10000
            ),
            AchievementEntity(
                achievementId = "week_player",
                userId = userId,
                title = "Week Player",
                description = "Played for 7 consecutive days",
                icon = "calendar_today",
                target = 7
            )
        )
        achievementDao.insertAchievements(defaultAchievements)
    }
    
    fun getAchievementsByUserFlow(userId: String): Flow<List<AchievementEntity>> = 
        achievementDao.getAchievementsByUserFlow(userId)
    
    suspend fun updateAchievementProgress(achievementId: String, progress: Int): Boolean {
        val achievement = achievementDao.getAchievementById(achievementId) ?: return false
        
        if (!achievement.isUnlocked && progress >= achievement.target) {
            achievementDao.unlockAchievement(achievementId, Date())
            return true // Achievement unlocked
        } else {
            achievementDao.updateAchievementProgress(achievementId, progress)
        }
        return false
    }
    
    suspend fun getUnlockedAchievementsCount(userId: String): Int = 
        achievementDao.getUnlockedAchievementsCount(userId)
    
    // Sync operations
    suspend fun getUnsyncedData(): UnsyncedData {
        return UnsyncedData(
            users = userDao.getUnsyncedUsers(),
            sessions = gameSessionDao.getUnsyncedSessions(),
            goals = goalDao.getUnsyncedGoals(),
            transactions = transactionDao.getUnsyncedTransactions(),
            achievements = achievementDao.getUnsyncedAchievements()
        )
    }
    
    suspend fun markDataAsSynced(
        userIds: List<String> = emptyList(),
        sessionIds: List<String> = emptyList(),
        goalIds: List<String> = emptyList(),
        transactionIds: List<String> = emptyList(),
        achievementIds: List<String> = emptyList()
    ) {
        userIds.forEach { userDao.markUserAsSynced(it) }
        sessionIds.forEach { gameSessionDao.markSessionAsSynced(it) }
        goalIds.forEach { goalDao.markGoalAsSynced(it) }
        transactionIds.forEach { transactionDao.markTransactionAsSynced(it) }
        achievementIds.forEach { achievementDao.markAchievementAsSynced(it) }
    }
}

data class UnsyncedData(
    val users: List<UserEntity>,
    val sessions: List<GameSessionEntity>,
    val goals: List<GoalEntity>,
    val transactions: List<TransactionEntity>,
    val achievements: List<AchievementEntity>
)

// Extension function to convert entity to domain model
private fun GoalEntity.toGoal(): Goal = Goal(
    id = goalId,
    title = title,
    description = description,
    targetAmount = targetAmount,
    currentAmount = currentAmount,
    icon = icon,
    category = category,
    isCompleted = isCompleted
)
```

## 🔗 Integration with ViewModels

### 1. Update WealthWiseViewModel

Update your existing `WealthWiseViewModel.kt` to use Room:

```kotlin
// Add to your WealthWiseViewModel class
@Inject
lateinit var databaseRepository: DatabaseRepository

private var currentUserId: String? = null
private var currentSessionId: String? = null

// Initialize user session
suspend fun initializeUserSession(userId: String) {
    currentUserId = userId
    
    // Get or create current game session
    val existingSession = databaseRepository.getCurrentSession(userId)
    currentSessionId = existingSession?.sessionId ?: run {
        // Create new session with default career
        val defaultCareer = CareersData.careers.first()
        databaseRepository.createGameSession(
            userId = userId,
            career = defaultCareer.title,
            monthlyIncome = defaultCareer.salary,
            monthlyExpenses = (defaultCareer.salary * 0.7).toInt(),
            careerId = defaultCareer.id
        )
    }
    
    // Initialize achievements if new user
    if (existingSession == null) {
        databaseRepository.initializeAchievements(userId)
    }
    
    // Load game state from database
    loadGameStateFromDatabase()
}

private suspend fun loadGameStateFromDatabase() {
    currentUserId?.let { userId ->
        currentSessionId?.let { sessionId ->
            val session = databaseRepository.getCurrentSession(userId)
            session?.let { sessionEntity ->
                val updatedGameState = GameState(
                    currentDay = sessionEntity.currentDay,
                    balance = sessionEntity.balance,
                    savings = sessionEntity.savings,
                    debt = sessionEntity.debt,
                    monthlyIncome = sessionEntity.monthlyIncome,
                    career = sessionEntity.career,
                    monthlyExpenses = sessionEntity.monthlyExpenses,
                    isOnboardingCompleted = true,
                    selectedCareerId = sessionEntity.careerId,
                    goals = sessionEntity.goals
                )
                _gameState.value = updatedGameState
            }
        }
    }
}

// Override existing methods to save to database
override fun payBills() {
    viewModelScope.launch {
        val currentState = _gameState.value
        
        if (currentState.balance >= currentState.monthlyExpenses) {
            val balanceBefore = currentState.balance
            val balanceAfter = balanceBefore - currentState.monthlyExpenses
            
            // Record transaction
            currentSessionId?.let { sessionId ->
                databaseRepository.recordTransaction(
                    sessionId = sessionId,
                    type = TransactionType.BILL_PAYMENT,
                    amount = currentState.monthlyExpenses,
                    description = "Monthly bills payment",
                    balanceBefore = balanceBefore,
                    balanceAfter = balanceAfter,
                    day = currentState.currentDay
                )
            }
            
            val updatedState = currentState.copy(
                balance = balanceAfter,
                currentDay = currentState.currentDay + 1
            )
            
            // Save to database
            saveGameStateToDatabase(updatedState)
            
            _gameState.value = updatedState
            _uiState.value = _uiState.value.copy(
                gameState = updatedState,
                showPayBillsDialog = false,
                successMessage = "Bills paid successfully! Day advanced to ${updatedState.currentDay}."
            )
        } else {
            _uiState.value = _uiState.value.copy(
                showPayBillsDialog = false,
                errorMessage = "Insufficient funds to pay bills!"
            )
        }
    }
}

private suspend fun saveGameStateToDatabase(gameState: GameState) {
    currentUserId?.let { userId ->
        val session = databaseRepository.getCurrentSession(userId)
        session?.let { sessionEntity ->
            val updatedSession = sessionEntity.copy(
                currentDay = gameState.currentDay,
                balance = gameState.balance,
                savings = gameState.savings,
                debt = gameState.debt,
                monthlyIncome = gameState.monthlyIncome,
                career = gameState.career,
                monthlyExpenses = gameState.monthlyExpenses,
                goals = gameState.goals,
                lastPlayedAt = Date()
            )
            databaseRepository.updateGameSession(updatedSession)
        }
    }
}
```

## 🧪 Testing Room Implementation

### 1. Create Database Tests

Create `app/src/androidTest/java/com/wealthwise/DatabaseTest.kt`:

```kotlin
package com.wealthwise

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wealthwise.data.database.WealthWiseDatabase
import com.wealthwise.data.database.entities.UserEntity
import com.wealthwise.data.database.entities.GameSessionEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    
    private lateinit var database: WealthWiseDatabase
    
    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WealthWiseDatabase::class.java
        ).build()
    }
    
    @After
    fun closeDb() {
        database.close()
    }
    
    @Test
    fun insertAndGetUser() = runBlocking {
        val user = UserEntity(
            displayName = "Test User",
            email = "test@example.com"
        )
        
        database.userDao().insertUser(user)
        
        val retrievedUser = database.userDao().getUserById(user.userId)
        assertNotNull(retrievedUser)
        assertEquals(user.displayName, retrievedUser?.displayName)
        assertEquals(user.email, retrievedUser?.email)
    }
    
    @Test
    fun insertAndGetGameSession() = runBlocking {
        // First create a user
        val user = UserEntity(displayName = "Test User")
        database.userDao().insertUser(user)
        
        // Create a game session for the user
        val session = GameSessionEntity(
            userId = user.userId,
            balance = 15000,
            career = "Teacher"
        )
        database.gameSessionDao().insertSession(session)
        
        val retrievedSession = database.gameSessionDao().getCurrentSession(user.userId)
        assertNotNull(retrievedSession)
        assertEquals(session.balance, retrievedSession?.balance)
        assertEquals(session.career, retrievedSession?.career)
    }
}
```

## 🚀 Migration Strategy

### 1. From In-Memory to Room Database

If you already have in-memory data structures, create a migration helper:

```kotlin
class DataMigrationHelper @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    
    suspend fun migrateExistingData(
        existingGameState: GameState,
        existingGoals: List<Goal>,
        userId: String
    ) {
        // Create game session from existing state
        val sessionId = databaseRepository.createGameSession(
            userId = userId,
            career = existingGameState.career,
            monthlyIncome = existingGameState.monthlyIncome,
            monthlyExpenses = existingGameState.monthlyExpenses,
            careerId = existingGameState.selectedCareerId
        )
        
        // Migrate goals
        existingGoals.forEach { goal ->
            databaseRepository.createGoal(
                sessionId = sessionId,
                title = goal.title,
                description = goal.description,
                targetAmount = goal.targetAmount,
                category = goal.category,
                icon = goal.icon
            )
        }
        
        // Update session with current progress
        val session = databaseRepository.getCurrentSession(userId)
        session?.let {
            val updatedSession = it.copy(
                currentDay = existingGameState.currentDay,
                balance = existingGameState.balance,
                savings = existingGameState.savings,
                debt = existingGameState.debt
            )
            databaseRepository.updateGameSession(updatedSession)
        }
    }
}
```

## ✅ Implementation Checklist

- [ ] Add Room dependencies to build.gradle
- [ ] Create all entity classes with proper annotations
- [ ] Implement all DAO interfaces with required queries
- [ ] Create type converters for complex data types
- [ ] Build database class with proper configuration
- [ ] Implement repository layer for data access
- [ ] Update ViewModels to use Room instead of in-memory storage
- [ ] Create migration strategies for existing data
- [ ] Write unit and integration tests
- [ ] Add proper error handling and logging
- [ ] Implement data synchronization with Firebase
- [ ] Test offline functionality
- [ ] Performance optimization for large datasets

## 📊 Performance Optimization

### 1. Query Optimization
- Use indexes on frequently queried columns
- Limit query results with appropriate LIMIT clauses
- Use Flow for reactive UI updates

### 2. Database Configuration
```kotlin
Room.databaseBuilder(context, WealthWiseDatabase::class.java, "database")
    .setQueryExecutor(Executors.newFixedThreadPool(4)) // Custom thread pool
    .enableMultiInstanceInvalidation() // Multi-process support
    .build()
```

### 3. Batch Operations
```kotlin
@Transaction
suspend fun batchUpdateGoals(goals: List<GoalEntity>) {
    goals.forEach { goal ->
        goalDao.updateGoal(goal)
    }
}
```

---

**🗄️ Room Database provides robust local storage with excellent offline capabilities!**