package com.wealthwise.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000e2\u0006\u0010\n\u001a\u00020\u000bH\'J$\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\'J$\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\'J\u0016\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u0017\u001a\u00020\u00032\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u001b"}, d2 = {"Lcom/wealthwise/data/dao/TransactionDao;", "", "deleteAllTransactions", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTransaction", "transaction", "Lcom/wealthwise/data/model/Transaction;", "(Lcom/wealthwise/data/model/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTransactionsByPlayer", "playerId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionsByPlayer", "Lkotlinx/coroutines/flow/Flow;", "", "getTransactionsByPlayerAndDay", "day", "", "getTransactionsByPlayerAndType", "type", "Lcom/wealthwise/data/model/TransactionType;", "insertTransaction", "insertTransactions", "transactions", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTransaction", "app_release"})
@androidx.room.Dao()
public abstract interface TransactionDao {
    
    @androidx.room.Query(value = "SELECT * FROM transactions WHERE playerId = :playerId ORDER BY day DESC, timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.Transaction>> getTransactionsByPlayer(long playerId);
    
    @androidx.room.Query(value = "SELECT * FROM transactions WHERE playerId = :playerId AND day = :day ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.Transaction>> getTransactionsByPlayerAndDay(long playerId, int day);
    
    @androidx.room.Query(value = "SELECT * FROM transactions WHERE playerId = :playerId AND type = :type ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.wealthwise.data.model.Transaction>> getTransactionsByPlayerAndType(long playerId, @org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.TransactionType type);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTransactions(@org.jetbrains.annotations.NotNull()
    java.util.List<com.wealthwise.data.model.Transaction> transactions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTransaction(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTransaction(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM transactions WHERE playerId = :playerId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTransactionsByPlayer(long playerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM transactions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllTransactions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}