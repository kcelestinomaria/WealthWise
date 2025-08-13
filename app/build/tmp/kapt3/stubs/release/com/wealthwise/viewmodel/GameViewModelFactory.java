package com.wealthwise.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/wealthwise/viewmodel/GameViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "playerRepository", "Lcom/wealthwise/data/repository/PlayerRepository;", "leaderboardRepository", "Lcom/wealthwise/data/repository/LeaderboardRepository;", "(Lcom/wealthwise/data/repository/PlayerRepository;Lcom/wealthwise/data/repository/LeaderboardRepository;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_release"})
public final class GameViewModelFactory implements androidx.lifecycle.ViewModelProvider.Factory {
    @org.jetbrains.annotations.NotNull()
    private final com.wealthwise.data.repository.PlayerRepository playerRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wealthwise.data.repository.LeaderboardRepository leaderboardRepository = null;
    
    public GameViewModelFactory(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.repository.PlayerRepository playerRepository, @org.jetbrains.annotations.NotNull()
    com.wealthwise.data.repository.LeaderboardRepository leaderboardRepository) {
        super();
    }
    
    @java.lang.Override()
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    @org.jetbrains.annotations.NotNull()
    public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
}