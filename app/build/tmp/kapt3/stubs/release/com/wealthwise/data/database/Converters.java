package com.wealthwise.data.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0007\u00a8\u0006\u000e"}, d2 = {"Lcom/wealthwise/data/database/Converters;", "", "()V", "fromRole", "", "role", "Lcom/wealthwise/data/model/Role;", "fromTransactionType", "type", "Lcom/wealthwise/data/model/TransactionType;", "toRole", "roleName", "toTransactionType", "typeName", "app_release"})
public final class Converters {
    
    public Converters() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromRole(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Role role) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.wealthwise.data.model.Role toRole(@org.jetbrains.annotations.NotNull()
    java.lang.String roleName) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromTransactionType(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.TransactionType type) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.wealthwise.data.model.TransactionType toTransactionType(@org.jetbrains.annotations.NotNull()
    java.lang.String typeName) {
        return null;
    }
}