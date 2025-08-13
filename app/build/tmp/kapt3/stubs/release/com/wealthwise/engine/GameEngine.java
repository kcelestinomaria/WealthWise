package com.wealthwise.engine;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\u0016"}, d2 = {"Lcom/wealthwise/engine/GameEngine;", "", "()V", "applyDecisionToPlayer", "Lcom/wealthwise/data/model/Player;", "player", "option", "Lcom/wealthwise/data/model/DecisionOption;", "createTransaction", "Lcom/wealthwise/data/model/Transaction;", "playerId", "", "day", "", "generateDailyEvent", "Lcom/wealthwise/data/model/TurnEvent;", "role", "Lcom/wealthwise/data/model/Role;", "generateDailyIncome", "", "processDecision", "Lcom/wealthwise/engine/GameResult;", "app_release"})
public final class GameEngine {
    @org.jetbrains.annotations.NotNull()
    public static final com.wealthwise.engine.GameEngine INSTANCE = null;
    
    private GameEngine() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthwise.engine.GameResult processDecision(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Player player, @org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.DecisionOption option) {
        return null;
    }
    
    private final com.wealthwise.data.model.Player applyDecisionToPlayer(com.wealthwise.data.model.Player player, com.wealthwise.data.model.DecisionOption option) {
        return null;
    }
    
    private final com.wealthwise.data.model.Transaction createTransaction(long playerId, int day, com.wealthwise.data.model.DecisionOption option) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wealthwise.data.model.TurnEvent generateDailyEvent(int day, @org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Role role) {
        return null;
    }
    
    public final double generateDailyIncome(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Role role, int day) {
        return 0.0;
    }
}