package com.wealthwise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wealthwise.data.repository.LeaderboardRepository
import com.wealthwise.data.repository.PlayerRepository

class GameViewModelFactory(
    private val playerRepository: PlayerRepository,
    private val leaderboardRepository: LeaderboardRepository
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(playerRepository, leaderboardRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class LeaderboardViewModelFactory(
    private val leaderboardRepository: LeaderboardRepository
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LeaderboardViewModel::class.java)) {
            return LeaderboardViewModel(leaderboardRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 