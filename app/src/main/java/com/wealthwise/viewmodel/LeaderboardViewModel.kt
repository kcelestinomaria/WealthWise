package com.wealthwise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wealthwise.data.model.LeaderboardEntry
import com.wealthwise.data.repository.LeaderboardRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class LeaderboardUiState(
    val topEntries: List<LeaderboardEntry> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null
)

class LeaderboardViewModel(
    private val leaderboardRepository: LeaderboardRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(LeaderboardUiState())
    val uiState: StateFlow<LeaderboardUiState> = _uiState.asStateFlow()
    
    init {
        loadLeaderboard()
    }
    
    private fun loadLeaderboard() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            leaderboardRepository.getTopEntries(10).collect { entries ->
                _uiState.value = _uiState.value.copy(
                    topEntries = entries.mapIndexed { index, entry ->
                        entry.copy(rank = index + 1)
                    },
                    isLoading = false
                )
            }
        }
    }
    
    fun refreshFromFirebase() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isRefreshing = true)
                
                val firebaseEntries = leaderboardRepository.fetchFromFirebase(10)
                
                // Update local database with fresh data
                firebaseEntries.forEach { entry ->
                    leaderboardRepository.insertEntry(entry)
                }
                
                _uiState.value = _uiState.value.copy(isRefreshing = false)
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isRefreshing = false,
                    errorMessage = "Failed to refresh leaderboard: ${e.message}"
                )
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
} 