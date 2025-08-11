package com.wealthwise.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0003\u001a@\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\rH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u0010\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0003\u001a6\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0007\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001c"}, d2 = {"AssetRow", "", "title", "", "description", "amount", "", "SummaryRow", "label", "isNegative", "", "isTotal", "color", "Landroidx/compose/ui/graphics/Color;", "SummaryRow-xwkQ0AY", "(Ljava/lang/String;DZZJ)V", "TransactionCard", "transaction", "Lcom/wealthwise/data/model/Transaction;", "WalletScreen", "player", "Lcom/wealthwise/data/model/Player;", "transactions", "", "onBack", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "app_debug"})
public final class WalletScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void WalletScreen(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Player player, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wealthwise.data.model.Transaction> transactions, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AssetRow(java.lang.String title, java.lang.String description, double amount) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TransactionCard(com.wealthwise.data.model.Transaction transaction) {
    }
}