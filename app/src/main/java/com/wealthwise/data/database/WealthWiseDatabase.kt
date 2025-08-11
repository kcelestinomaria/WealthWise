package com.wealthwise.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.wealthwise.data.dao.LeaderboardDao
import com.wealthwise.data.dao.PlayerDao
import com.wealthwise.data.dao.TransactionDao
import com.wealthwise.data.model.LeaderboardEntry
import com.wealthwise.data.model.Player
import com.wealthwise.data.model.Transaction

@Database(
    entities = [
        Player::class,
        Transaction::class,
        LeaderboardEntry::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WealthWiseDatabase : RoomDatabase() {
    
    abstract fun playerDao(): PlayerDao
    abstract fun transactionDao(): TransactionDao
    abstract fun leaderboardDao(): LeaderboardDao
    
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
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
} 