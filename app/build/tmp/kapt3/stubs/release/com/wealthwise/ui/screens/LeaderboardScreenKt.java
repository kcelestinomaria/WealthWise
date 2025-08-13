package com.wealthwise.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0003\u001a \u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0003\u001aL\u0010\u000e\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u00102\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001a\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003H\u0002\u00a8\u0006\u001a"}, d2 = {"DetailItem", "", "label", "", "amount", "", "isNegative", "", "LeaderboardEntryCard", "rank", "", "entry", "Lcom/wealthwise/data/model/LeaderboardEntry;", "isTopThree", "LeaderboardScreen", "leaderboardEntries", "", "isLoading", "isRefreshing", "onBack", "Lkotlin/Function0;", "onRefresh", "modifier", "Landroidx/compose/ui/Modifier;", "getRoleEmoji", "scenario", "app_release"})
public final class LeaderboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void LeaderboardScreen(@org.jetbrains.annotations.NotNull()
    java.util.List<com.wealthwise.data.model.LeaderboardEntry> leaderboardEntries, boolean isLoading, boolean isRefreshing, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRefresh, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LeaderboardEntryCard(int rank, com.wealthwise.data.model.LeaderboardEntry entry, boolean isTopThree) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DetailItem(java.lang.String label, double amount, boolean isNegative) {
    }
    
    private static final java.lang.String getRoleEmoji(java.lang.String scenario) {
        return null;
    }
}