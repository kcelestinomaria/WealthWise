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