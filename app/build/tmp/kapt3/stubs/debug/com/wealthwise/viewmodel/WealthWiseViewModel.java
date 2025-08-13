package com.wealthwise.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u000fJ\u0006\u0010\u0012\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u000fJ\u0006\u0010\u0019\u001a\u00020\u000fJ\u0006\u0010\u001a\u001a\u00020\u000fJ\u000e\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u001c\u001a\u00020\u000fJ\u0006\u0010\u001d\u001a\u00020\u000fJ\u000e\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\u000fJ\u0006\u0010\"\u001a\u00020\u000fJ\u0006\u0010#\u001a\u00020\u000fJ\u000e\u0010$\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b\u00a8\u0006%"}, d2 = {"Lcom/wealthwise/viewmodel/WealthWiseViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_gameState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/wealthwise/data/model/GameState;", "_uiState", "Lcom/wealthwise/viewmodel/WealthWiseUiState;", "gameState", "Lkotlinx/coroutines/flow/StateFlow;", "getGameState", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "addMonthlyIncome", "", "advanceDay", "clearMessages", "completeOnboarding", "formatCurrency", "", "amount", "", "getNetWorth", "hideInvestmentDialog", "hideLoanDialog", "hidePayBillsDialog", "invest", "payBills", "resetGame", "selectCareer", "career", "Lcom/wealthwise/data/model/Career;", "showInvestmentDialog", "showLoanDialog", "showPayBillsDialog", "takeLoan", "app_debug"})
public final class WealthWiseViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.wealthwise.viewmodel.WealthWiseUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.wealthwise.viewmodel.WealthWiseUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.wealthwise.data.model.GameState> _gameState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.wealthwise.data.model.GameState> gameState = null;
    
    public WealthWiseViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.wealthwise.viewmodel.WealthWiseUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.wealthwise.data.model.GameState> getGameState() {
        return null;
    }
    
    public final void selectCareer(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Career career) {
    }
    
    public final void completeOnboarding() {
    }
    
    public final void showPayBillsDialog() {
    }
    
    public final void hidePayBillsDialog() {
    }
    
    public final void showInvestmentDialog() {
    }
    
    public final void hideInvestmentDialog() {
    }
    
    public final void showLoanDialog() {
    }
    
    public final void hideLoanDialog() {
    }
    
    public final void payBills() {
    }
    
    public final void invest(int amount) {
    }
    
    public final void takeLoan(int amount) {
    }
    
    public final void advanceDay() {
    }
    
    public final int getNetWorth() {
        return 0;
    }
    
    public final void clearMessages() {
    }
    
    public final void resetGame() {
    }
    
    public final void addMonthlyIncome() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatCurrency(int amount) {
        return null;
    }
}