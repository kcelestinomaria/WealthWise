package com.wealthwise.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u001a@\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u001aD\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007\u001aB\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0003\u001a\u0018\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0003H\u0002\u001a<\u0010 \u001a\u00020\u0001*\u00020!2\u0018\u0010\"\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050$0#2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0#2\u0006\u0010&\u001a\u00020\u0005H\u0002\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\'"}, d2 = {"BreakdownRow", "", "label", "", "amount", "", "isTotal", "", "isNegative", "color", "Landroidx/compose/ui/graphics/Color;", "BreakdownRow-xwkQ0AY", "(Ljava/lang/String;DZZJ)V", "EndGameScreen", "player", "Lcom/wealthwise/data/model/Player;", "onPlayAgain", "Lkotlin/Function0;", "onViewLeaderboard", "onBackToHome", "modifier", "Landroidx/compose/ui/Modifier;", "WealthPieChart", "cash", "sacco", "mmf", "land", "reits", "debt", "getPerformanceMessage", "netWorth", "roleName", "drawPieChart", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "values", "", "Lkotlin/Pair;", "colors", "total", "app_release"})
public final class EndGameScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void EndGameScreen(@org.jetbrains.annotations.NotNull()
    com.wealthwise.data.model.Player player, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPlayAgain, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onViewLeaderboard, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBackToHome, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void WealthPieChart(double cash, double sacco, double mmf, double land, double reits, double debt, androidx.compose.ui.Modifier modifier) {
    }
    
    private static final void drawPieChart(androidx.compose.ui.graphics.drawscope.DrawScope $this$drawPieChart, java.util.List<kotlin.Pair<java.lang.String, java.lang.Double>> values, java.util.List<androidx.compose.ui.graphics.Color> colors, double total) {
    }
    
    private static final java.lang.String getPerformanceMessage(double netWorth, java.lang.String roleName) {
        return null;
    }
}