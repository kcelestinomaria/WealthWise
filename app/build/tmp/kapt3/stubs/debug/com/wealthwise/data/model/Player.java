package com.wealthwise.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b*\b\u0087\b\u0018\u0000 ;2\u00020\u0001:\u0001;Bm\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0014J\u0006\u0010\'\u001a\u00020\tJ\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0010H\u00c6\u0003J\t\u0010*\u001a\u00020\u0012H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\t\u0010-\u001a\u00020\u0007H\u00c6\u0003J\t\u0010.\u001a\u00020\tH\u00c6\u0003J\t\u0010/\u001a\u00020\tH\u00c6\u0003J\t\u00100\u001a\u00020\tH\u00c6\u0003J\t\u00101\u001a\u00020\tH\u00c6\u0003J\t\u00102\u001a\u00020\tH\u00c6\u0003J\t\u00103\u001a\u00020\tH\u00c6\u0003J\u0081\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0003H\u00c6\u0001J\u0013\u00105\u001a\u00020\u00122\b\u00106\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u0006\u00107\u001a\u00020\tJ\u0006\u00108\u001a\u00020\tJ\t\u00109\u001a\u00020\u0010H\u00d6\u0001J\t\u0010:\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\r\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0016\u00a8\u0006<"}, d2 = {"Lcom/wealthwise/data/model/Player;", "", "id", "", "name", "", "role", "Lcom/wealthwise/data/model/Role;", "cash", "", "sacco", "mmf", "land", "reits", "debt", "currentDay", "", "gameCompleted", "", "createdAt", "(JLjava/lang/String;Lcom/wealthwise/data/model/Role;DDDDDDIZJ)V", "getCash", "()D", "getCreatedAt", "()J", "getCurrentDay", "()I", "getDebt", "getGameCompleted", "()Z", "getId", "getLand", "getMmf", "getName", "()Ljava/lang/String;", "getReits", "getRole", "()Lcom/wealthwise/data/model/Role;", "getSacco", "calculateNetWorth", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "getTotalAssets", "getTotalLiabilities", "hashCode", "toString", "Companion", "app_debug"})
@androidx.room.Entity(tableName = "players")
public final class Player {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wealthwise.data.model.Role role = null;
    private final double cash = 0.0;
    private final double sacco = 0.0;
    private final double mmf = 0.0;
    private final double land = 0.0;
    private final double reits = 0.0;
    private final double debt = 0.0;
    private final int currentDay = 0;
    private final boolean gameCompleted = false;
    private final long createdAt = 0L;
    @org.jetbrains.annotations.NotNull()
    public static final com.wealthwise.data.model.Player.Companion Companion = null;
    
    public Player(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Role role, double cash, double sacco, double mmf, double land, double reits, double debt, int currentDay, boolean gameCompleted, long createdAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthwise.data.model.Role getRole() {
        return null;
    }
    
    public final double getCash() {
        return 0.0;
    }
    
    public final double getSacco() {
        return 0.0;
    }
    
    public final double getMmf() {
        return 0.0;
    }
    
    public final double getLand() {
        return 0.0;
    }
    
    public final double getReits() {
        return 0.0;
    }
    
    public final double getDebt() {
        return 0.0;
    }
    
    public final int getCurrentDay() {
        return 0;
    }
    
    public final boolean getGameCompleted() {
        return false;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final double calculateNetWorth() {
        return 0.0;
    }
    
    public final double getTotalAssets() {
        return 0.0;
    }
    
    public final double getTotalLiabilities() {
        return 0.0;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final long component12() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthwise.data.model.Role component3() {
        return null;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthwise.data.model.Player copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Role role, double cash, double sacco, double mmf, double land, double reits, double debt, int currentDay, boolean gameCompleted, long createdAt) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lcom/wealthwise/data/model/Player$Companion;", "", "()V", "createNew", "Lcom/wealthwise/data/model/Player;", "name", "", "role", "Lcom/wealthwise/data/model/Role;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wealthwise.data.model.Player createNew(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        com.wealthwise.data.model.Role role) {
            return null;
        }
    }
}