package com.wealthwise.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00130\u0012J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00130\u0012J\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0012J\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00122\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00130\u00122\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/wealthwise/data/repository/PlayerRepository;", "", "playerDao", "Lcom/wealthwise/data/dao/PlayerDao;", "transactionDao", "Lcom/wealthwise/data/dao/TransactionDao;", "(Lcom/wealthwise/data/dao/PlayerDao;Lcom/wealthwise/data/dao/TransactionDao;)V", "deletePlayer", "", "player", "Lcom/wealthwise/data/model/Player;", "(Lcom/wealthwise/data/model/Player;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlayerById", "playerId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTransactionsByPlayer", "getAllPlayers", "Lkotlinx/coroutines/flow/Flow;", "", "getCompletedPlayers", "getCurrentPlayer", "getPlayerById", "getTransactionsByPlayer", "Lcom/wealthwise/data/model/Transaction;", "insertPlayer", "insertTransaction", "transaction", "(Lcom/wealthwise/data/model/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePlayer", "app_debug"})
public final class PlayerRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.wealthwise.data.dao.PlayerDao playerDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wealthwise.data.dao.TransactionDao transactionDao = null;
    
    @javax.inject.Inject()
    public PlayerRepository(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.dao.PlayerDao playerDao, @org.jetbrains.annotations.NotNull()
    com.wealthwise.data.dao.TransactionDao transactionDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.Player>> getAllPlayers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.wealthwise.data.model.Player> getPlayerById(long playerId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.wealthwise.data.model.Player> getCurrentPlayer() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.Player>> getCompletedPlayers() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertPlayer(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Player player, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updatePlayer(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Player player, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePlayer(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Player player, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePlayerById(long playerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.Transaction>> getTransactionsByPlayer(long playerId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteTransactionsByPlayer(long playerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}