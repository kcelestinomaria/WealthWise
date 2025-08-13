package com.wealthwise.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00102\b\b\u0002\u0010\f\u001a\u00020\rJ$\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00102\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\f\u001a\u00020\rJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0017J\u000e\u0010\u001a\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010\u001cR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/wealthwise/data/repository/LeaderboardRepository;", "", "leaderboardDao", "Lcom/wealthwise/data/dao/LeaderboardDao;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/wealthwise/data/dao/LeaderboardDao;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "leaderboardCollection", "Lcom/google/firebase/firestore/CollectionReference;", "fetchFromFirebase", "", "Lcom/wealthwise/data/model/LeaderboardEntry;", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTopEntries", "Lkotlinx/coroutines/flow/Flow;", "getTopEntriesByRole", "scenario", "", "insertEntry", "", "entry", "(Lcom/wealthwise/data/model/LeaderboardEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncToFirebase", "", "syncUnsyncedEntries", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class LeaderboardRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.wealthwise.data.dao.LeaderboardDao leaderboardDao = null;
    @org.jetbrains.annotations.Nullable()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.Nullable()
    private final com.google.firebase.firestore.CollectionReference leaderboardCollection = null;
    
    @javax.inject.Inject()
    public LeaderboardRepository(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.dao.LeaderboardDao leaderboardDao, @org.jetbrains.annotations.Nullable()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.LeaderboardEntry>> getTopEntries(int limit) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.LeaderboardEntry>> getTopEntriesByRole(@org.jetbrains.annotations.NotNull()
    java.lang.String scenario, int limit) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertEntry(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.LeaderboardEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncToFirebase(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.LeaderboardEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchFromFirebase(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.wealthwise.data.model.LeaderboardEntry>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncUnsyncedEntries(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}