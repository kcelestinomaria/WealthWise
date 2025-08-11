package com.wealthwise.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.wealthwise.data.dao.LeaderboardDao
import com.wealthwise.data.model.LeaderboardEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaderboardRepository @Inject constructor(
    private val leaderboardDao: LeaderboardDao,
    private val firestore: FirebaseFirestore?
) {
    
    private val leaderboardCollection = firestore?.collection("leaderboards")
    
    // Local database operations
    fun getTopEntries(limit: Int = 10): Flow<List<LeaderboardEntry>> = 
        leaderboardDao.getTopEntries(limit)
    
    fun getTopEntriesByRole(scenario: String, limit: Int = 10): Flow<List<LeaderboardEntry>> = 
        leaderboardDao.getTopEntriesByRole(scenario, limit)
    
    suspend fun insertEntry(entry: LeaderboardEntry): Long = 
        leaderboardDao.insertEntry(entry)
    
    // Firebase operations
    suspend fun syncToFirebase(entry: LeaderboardEntry): Boolean {
        return try {
            val collection = leaderboardCollection ?: return false
            
            val data = hashMapOf(
                "playerName" to entry.playerName,
                "netWorth" to entry.netWorth,
                "scenario" to entry.scenario,
                "cash" to entry.cash,
                "assets" to entry.assets,
                "liabilities" to entry.liabilities,
                "date" to entry.date
            )
            
            collection.add(data).await()
            leaderboardDao.markAsSynced(entry.id)
            true
        } catch (e: Exception) {
            false
        }
    }
    
    suspend fun fetchFromFirebase(limit: Int = 10): List<LeaderboardEntry> {
        return try {
            val collection = leaderboardCollection ?: return emptyList()
            
            val snapshot = collection
                .orderBy("netWorth", Query.Direction.DESCENDING)
                .limit(limit.toLong())
                .get()
                .await()
            
            snapshot.documents.mapIndexed { index, document ->
                LeaderboardEntry(
                    playerName = document.getString("playerName") ?: "",
                    netWorth = document.getDouble("netWorth") ?: 0.0,
                    scenario = document.getString("scenario") ?: "",
                    cash = document.getDouble("cash") ?: 0.0,
                    assets = document.getDouble("assets") ?: 0.0,
                    liabilities = document.getDouble("liabilities") ?: 0.0,
                    date = document.getLong("date") ?: 0L,
                    rank = index + 1,
                    syncedToFirebase = true
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun syncUnsyncedEntries() {
        try {
            val unsyncedEntries = leaderboardDao.getUnsyncedEntries()
            unsyncedEntries.forEach { entry ->
                syncToFirebase(entry)
            }
        } catch (e: Exception) {
            // Handle sync errors
        }
    }
} 