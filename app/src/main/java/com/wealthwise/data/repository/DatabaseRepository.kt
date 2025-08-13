package com.wealthwise.data.repository

import com.wealthwise.data.database.WealthWiseDatabase
import com.wealthwise.data.database.entities.*
import com.wealthwise.data.model.Goal
import com.wealthwise.data.model.GoalCategory
import com.wealthwise.data.model.TransactionType
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
        val session = gameSessionDao.getSessionById(sessionId) ?: throw IllegalStateException("Session not found")

        val transaction = TransactionEntity(
            sessionId = sessionId,
            type = type,
            amount = amount,
            description = description,
            balanceBefore = balanceBefore,
            balanceAfter = balanceAfter,
            day = day,
            createdAt = Date(),
            goalId = goalId,
            category = category,
            playerId = session.userId,
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