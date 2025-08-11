package com.wealthwise.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u000eJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\fH\u00c6\u0003JW\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\u00072\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\fH\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0018R\u0013\u0010\r\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016\u00a8\u0006\'"}, d2 = {"Lcom/wealthwise/viewmodel/GameUiState;", "", "currentPlayer", "Lcom/wealthwise/data/model/Player;", "currentEvent", "Lcom/wealthwise/data/model/TurnEvent;", "gameCompleted", "", "dailyIncome", "", "isLoading", "errorMessage", "", "lastDecisionResult", "(Lcom/wealthwise/data/model/Player;Lcom/wealthwise/data/model/TurnEvent;ZDZLjava/lang/String;Ljava/lang/String;)V", "getCurrentEvent", "()Lcom/wealthwise/data/model/TurnEvent;", "getCurrentPlayer", "()Lcom/wealthwise/data/model/Player;", "getDailyIncome", "()D", "getErrorMessage", "()Ljava/lang/String;", "getGameCompleted", "()Z", "getLastDecisionResult", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_release"})
public final class GameUiState {
    @org.jetbrains.annotations.Nullable()
    private final com.wealthwise.data.model.Player currentPlayer = null;
    @org.jetbrains.annotations.Nullable()
    private final com.wealthwise.data.model.TurnEvent currentEvent = null;
    private final boolean gameCompleted = false;
    private final double dailyIncome = 0.0;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String errorMessage = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastDecisionResult = null;
    
    public GameUiState(@org.jetbrains.annotations.Nullable()
    com.wealthwise.data.model.Player currentPlayer, @org.jetbrains.annotations.Nullable()
    com.wealthwise.data.model.TurnEvent currentEvent, boolean gameCompleted, double dailyIncome, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage, @org.jetbrains.annotations.Nullable()
    java.lang.String lastDecisionResult) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wealthwise.data.model.Player getCurrentPlayer() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wealthwise.data.model.TurnEvent getCurrentEvent() {
        return null;
    }
    
    public final boolean getGameCompleted() {
        return false;
    }
    
    public final double getDailyIncome() {
        return 0.0;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastDecisionResult() {
        return null;
    }
    
    public GameUiState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wealthwise.data.model.Player component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wealthwise.data.model.TurnEvent component2() {
        return null;
    }
    
    public final boolean component3() {
        return false;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthwise.viewmodel.GameUiState copy(@org.jetbrains.annotations.Nullable()
    com.wealthwise.data.model.Player currentPlayer, @org.jetbrains.annotations.Nullable()
    com.wealthwise.data.model.TurnEvent currentEvent, boolean gameCompleted, double dailyIncome, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage, @org.jetbrains.annotations.Nullable()
    java.lang.String lastDecisionResult) {
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
}