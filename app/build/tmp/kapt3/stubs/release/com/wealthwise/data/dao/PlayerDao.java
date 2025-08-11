package com.wealthwise.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000eH\'J\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000eH\'J\u0010\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000eH\'J\u0018\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000e2\u0006\u0010\n\u001a\u00020\u000bH\'J\u0016\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u0015"}, d2 = {"Lcom/wealthwise/data/dao/PlayerDao;", "", "deleteAllPlayers", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlayer", "player", "Lcom/wealthwise/data/model/Player;", "(Lcom/wealthwise/data/model/Player;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlayerById", "playerId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPlayers", "Lkotlinx/coroutines/flow/Flow;", "", "getCompletedPlayers", "getCurrentPlayer", "getPlayerById", "insertPlayer", "updatePlayer", "app_release"})
@androidx.room.Dao()
public abstract interface PlayerDao {
    
    @androidx.room.Query(value = "SELECT * FROM players ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.Player>> getAllPlayers();
    
    @androidx.room.Query(value = "SELECT * FROM players WHERE id = :playerId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.wealthwise.data.model.Player> getPlayerById(long playerId);
    
    @androidx.room.Query(value = "SELECT * FROM players WHERE gameCompleted = 0 ORDER BY createdAt DESC LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.wealthwise.data.model.Player> getCurrentPlayer();
    
    @androidx.room.Query(value = "SELECT * FROM players WHERE gameCompleted = 1 ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.Player>> getCompletedPlayers();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertPlayer(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Player player, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePlayer(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Player player, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletePlayer(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Player player, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM players WHERE id = :playerId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletePlayerById(long playerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM players")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllPlayers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}