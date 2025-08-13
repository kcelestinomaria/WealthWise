package com.wealthwise.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0016\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\tH\u0082@\u00a2\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\tH\u0002J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\tH\u0002J\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u001b2\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020\u0013H\u0002J\u000e\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020\u0013J\u0016\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020)R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f\u00a8\u0006*"}, d2 = {"Lcom/wealthwise/viewmodel/GameViewModel;", "Landroidx/lifecycle/ViewModel;", "playerRepository", "Lcom/wealthwise/data/repository/PlayerRepository;", "leaderboardRepository", "Lcom/wealthwise/data/repository/LeaderboardRepository;", "(Lcom/wealthwise/data/repository/PlayerRepository;Lcom/wealthwise/data/repository/LeaderboardRepository;)V", "_currentPlayer", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/wealthwise/data/model/Player;", "_uiState", "Lcom/wealthwise/viewmodel/GameUiState;", "currentPlayer", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentPlayer", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "clearError", "", "clearLastResult", "completeGame", "player", "(Lcom/wealthwise/data/model/Player;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateDailyEvent", "generateDailyIncome", "getTransactionsForPlayer", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/wealthwise/data/model/Transaction;", "playerId", "", "loadCurrentPlayer", "makeDecision", "option", "Lcom/wealthwise/data/model/DecisionOption;", "resetGame", "startNewGame", "playerName", "", "selectedRole", "Lcom/wealthwise/data/model/Role;", "app_debug"})
public final class GameViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.wealthwise.data.repository.PlayerRepository playerRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wealthwise.data.repository.LeaderboardRepository leaderboardRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.wealthwise.viewmodel.GameUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.wealthwise.viewmodel.GameUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.wealthwise.data.model.Player> _currentPlayer = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.wealthwise.data.model.Player> currentPlayer = null;
    
    @javax.inject.Inject()
    public GameViewModel(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.repository.PlayerRepository playerRepository, @org.jetbrains.annotations.NotNull()
    com.wealthwise.data.repository.LeaderboardRepository leaderboardRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.wealthwise.viewmodel.GameUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.wealthwise.data.model.Player> getCurrentPlayer() {
        return null;
    }
    
    private final void loadCurrentPlayer() {
    }
    
    public final void startNewGame(@org.jetbrains.annotations.NotNull()
    java.lang.String playerName, @org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Role selectedRole) {
    }
    
    public final void makeDecision(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.DecisionOption option) {
    }
    
    private final void generateDailyEvent(com.wealthwise.data.model.Player player) {
    }
    
    private final void generateDailyIncome(com.wealthwise.data.model.Player player) {
    }
    
    private final java.lang.Object completeGame(com.wealthwise.data.model.Player player, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void resetGame() {
    }
    
    public final void clearError() {
    }
    
    public final void clearLastResult() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.Transaction>> getTransactionsForPlayer(long playerId) {
        return null;
    }
}