package com.wealthwise.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u001e\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n2\b\b\u0002\u0010\r\u001a\u00020\u000eH\'J&\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\r\u001a\u00020\u000eH\'J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0013\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u001c"}, d2 = {"Lcom/wealthwise/data/dao/LeaderboardDao;", "", "deleteAllEntries", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteEntry", "entry", "Lcom/wealthwise/data/model/LeaderboardEntry;", "(Lcom/wealthwise/data/model/LeaderboardEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllEntries", "Lkotlinx/coroutines/flow/Flow;", "", "getTopEntries", "limit", "", "getTopEntriesByRole", "scenario", "", "getUnsyncedEntries", "insertEntries", "entries", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertEntry", "", "markAsSynced", "entryId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateEntry", "app_debug"})
@androidx.room.Dao()
public abstract interface LeaderboardDao {
    
    @androidx.room.Query(value = "SELECT * FROM leaderboard_entries ORDER BY netWorth DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.LeaderboardEntry>> getAllEntries();
    
    @androidx.room.Query(value = "SELECT * FROM leaderboard_entries ORDER BY netWorth DESC LIMIT :limit")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.LeaderboardEntry>> getTopEntries(int limit);
    
    @androidx.room.Query(value = "SELECT * FROM leaderboard_entries WHERE scenario = :scenario ORDER BY netWorth DESC LIMIT :limit")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.LeaderboardEntry>> getTopEntriesByRole(@org.jetbrains.annotations.NotNull()
    java.lang.String scenario, int limit);
    
    @androidx.room.Query(value = "SELECT * FROM leaderboard_entries WHERE syncedToFirebase = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnsyncedEntries(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.wealthwise.data.model.LeaderboardEntry>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertEntry(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.LeaderboardEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertEntries(@org.jetbrains.annotations.NotNull()
    java.util.List<com.wealthwise.data.model.LeaderboardEntry> entries, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateEntry(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.LeaderboardEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE leaderboard_entries SET syncedToFirebase = 1 WHERE id = :entryId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAsSynced(long entryId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteEntry(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.LeaderboardEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM leaderboard_entries")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllEntries(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}