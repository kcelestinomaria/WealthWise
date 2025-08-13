package com.wealthwise.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u001a:\u0010\b\u001a\u00020\u00012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\rH\u0007\u001a\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002\u001a\u0015\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u00a2\u0006\u0002\u0010\u0016\u001a\u0015\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u00a2\u0006\u0002\u0010\u0016\u00a8\u0006\u0018"}, d2 = {"GoalCard", "", "goal", "Lcom/wealthwise/data/model/Goal;", "numberFormat", "Ljava/text/NumberFormat;", "onClick", "Lkotlin/Function0;", "GoalsScreen", "goals", "", "onAddGoal", "onGoalClick", "Lkotlin/Function1;", "getGoalIcon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "iconName", "", "getGoalIconBackground", "Landroidx/compose/ui/graphics/Color;", "category", "Lcom/wealthwise/data/model/GoalCategory;", "(Lcom/wealthwise/data/model/GoalCategory;)J", "getGoalIconColor", "app_debug"})
public final class GoalsScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void GoalsScreen(@org.jetbrains.annotations.NotNull()
    java.util.List<com.wealthwise.data.model.Goal> goals, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddGoal, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.wealthwise.data.model.Goal, kotlin.Unit> onGoalClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void GoalCard(com.wealthwise.data.model.Goal goal, java.text.NumberFormat numberFormat, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    private static final androidx.compose.ui.graphics.vector.ImageVector getGoalIcon(java.lang.String iconName) {
        return null;
    }
    
    private static final long getGoalIconColor(com.wealthwise.data.model.GoalCategory category) {
        return 0L;
    }
    
    private static final long getGoalIconBackground(com.wealthwise.data.model.GoalCategory category) {
        return 0L;
    }
}