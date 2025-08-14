package com.wealthwise.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wealthwise.data.dao.LeaderboardDao
import com.wealthwise.data.dao.PlayerDao
import com.wealthwise.data.dao.TransactionDao
import com.wealthwise.data.database.dao.AchievementDao
import com.wealthwise.data.database.dao.GameSessionDao
import com.wealthwise.data.database.dao.GoalDao
import com.wealthwise.data.database.dao.UserDao
import com.wealthwise.data.database.entities.AchievementEntity
import com.wealthwise.data.database.entities.GameSessionEntity
import com.wealthwise.data.database.entities.GoalEntity
import com.wealthwise.data.database.entities.TransactionEntity
import com.wealthwise.data.database.entities.UserEntity
import com.wealthwise.data.model.LeaderboardEntry
import com.wealthwise.data.model.Player
import com.wealthwise.data.model.Transaction

@Database(
    entities = [
        Player::class,
        Transaction::class,
        LeaderboardEntry::class,
        UserEntity::class,
        GameSessionEntity::class,
        GoalEntity::class,
        TransactionEntity::class,
        AchievementEntity::class
    ],
    version = 5,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WealthWiseDatabase : RoomDatabase() {
    
    abstract fun playerDao(): PlayerDao
    abstract fun transactionDao(): TransactionDao
    abstract fun leaderboardDao(): LeaderboardDao
    abstract fun userDao(): UserDao
    abstract fun gameSessionDao(): GameSessionDao
    abstract fun goalDao(): GoalDao
    abstract fun achievementDao(): AchievementDao
    
    companion object {
        @Volatile
        private var INSTANCE: WealthWiseDatabase? = null
        
        fun getDatabase(context: Context): WealthWiseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WealthWiseDatabase::class.java,
                    "wealthwise_database"
                )
                    .addTypeConverter(Converters())
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
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

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
            CREATE TABLE new_transaction_entities (
                transaction_id TEXT PRIMARY KEY NOT NULL,
                session_id TEXT NOT NULL,
                type TEXT NOT NULL,
                amount INTEGER NOT NULL,
                description TEXT NOT NULL,
                category TEXT,
                day INTEGER NOT NULL,
                balance_before INTEGER NOT NULL,
                balance_after INTEGER NOT NULL,
                goal_id TEXT,
                created_at INTEGER NOT NULL,
                is_synced INTEGER NOT NULL,
                FOREIGN KEY(session_id) REFERENCES game_session_entities(session_id) ON DELETE CASCADE
            )
        """)

                // Copy data from old table
                database.execSQL("""
            INSERT INTO new_transaction_entities 
            SELECT * FROM transaction_entities
        """)

                // Remove old table
                database.execSQL("DROP TABLE transaction_entities")

                // Rename new table
                database.execSQL("ALTER TABLE new_transaction_entities RENAME TO transaction_entities")
            }
        }

        fun build(context: Context) = Room.databaseBuilder(
            context,
            WealthWiseDatabase::class.java,
            "wealthwise.db"
        ).addMigrations(MIGRATION_1_2)
    }


} 