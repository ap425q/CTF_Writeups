package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.Optimizer;
import androidx.constraintlayout.solver.widgets.VirtualLayout;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class BasicMeasure {
    public static final int AT_MOST = Integer.MIN_VALUE;
    private static final boolean DEBUG = false;
    public static final int EXACTLY = 1073741824;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;
    private ConstraintWidgetContainer constraintWidgetContainer;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();
    private Measure mMeasure = new Measure();

    /* loaded from: classes.dex */
    public static class Measure {
        public static int SELF_DIMENSIONS = 0;
        public static int TRY_GIVEN_DIMENSIONS = 1;
        public static int USE_GIVEN_DIMENSIONS = 2;
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    /* loaded from: classes.dex */
    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public void updateHierarchy(ConstraintWidgetContainer layout) {
        this.mVariableDimensionsWidgets.clear();
        int childCount = layout.mChildren.size();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget widget = layout.mChildren.get(i);
            if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                this.mVariableDimensionsWidgets.add(widget);
            }
        }
        layout.invalidateGraph();
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.constraintWidgetContainer = constraintWidgetContainer;
    }

    private void measureChildren(ConstraintWidgetContainer layout) {
        int childCount = layout.mChildren.size();
        boolean optimize = layout.optimizeFor(64);
        Measurer measurer = layout.getMeasurer();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget child = layout.mChildren.get(i);
            if (!(child instanceof Guideline) && !(child instanceof Barrier) && !child.isInVirtualLayout() && (!optimize || child.horizontalRun == null || child.verticalRun == null || !child.horizontalRun.dimension.resolved || !child.verticalRun.dimension.resolved)) {
                boolean skip = false;
                ConstraintWidget.DimensionBehaviour widthBehavior = child.getDimensionBehaviour(0);
                ConstraintWidget.DimensionBehaviour heightBehavior = child.getDimensionBehaviour(1);
                if (widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultWidth != 1 && heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultHeight != 1) {
                    skip = true;
                }
                if (!skip && layout.optimizeFor(1) && !(child instanceof VirtualLayout)) {
                    if (widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultWidth == 0 && heightBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !child.isInHorizontalChain()) {
                        skip = true;
                    }
                    if (heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultHeight == 0 && widthBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !child.isInHorizontalChain()) {
                        skip = true;
                    }
                    if ((widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && child.mDimensionRatio > 0.0f) {
                        skip = true;
                    }
                }
                if (!skip) {
                    measure(measurer, child, Measure.SELF_DIMENSIONS);
                    if (layout.mMetrics != null) {
                        layout.mMetrics.measuredWidgets++;
                    }
                }
            }
        }
        measurer.didMeasures();
    }

    private void solveLinearSystem(ConstraintWidgetContainer layout, String reason, int w, int h) {
        int minWidth = layout.getMinWidth();
        int minHeight = layout.getMinHeight();
        layout.setMinWidth(0);
        layout.setMinHeight(0);
        layout.setWidth(w);
        layout.setHeight(h);
        layout.setMinWidth(minWidth);
        layout.setMinHeight(minHeight);
        this.constraintWidgetContainer.layout();
    }

    public long solverMeasure(ConstraintWidgetContainer layout, int optimizationLevel, int paddingX, int paddingY, int widthMode, int widthSize, int heightMode, int heightSize, int lastMeasureWidth, int lastMeasureHeight) {
        boolean optimize;
        boolean ratio;
        long layoutTime;
        int widthSize2;
        int heightSize2;
        int optimizations;
        int startingWidth;
        boolean optimize2;
        int sizeDependentWidgetsCount;
        int measureStrategy;
        int maxIterations;
        Measurer measurer;
        int j;
        boolean containerWrapWidth;
        int optimizations2;
        int startingWidth2;
        boolean needSolverPass;
        Measurer measurer2;
        int measuredWidth;
        boolean z;
        Measurer measurer3 = layout.getMeasurer();
        int childCount = layout.mChildren.size();
        int startingWidth3 = layout.getWidth();
        int startingHeight = layout.getHeight();
        boolean optimizeWrap = Optimizer.enabled(optimizationLevel, 128);
        boolean optimize3 = optimizeWrap || Optimizer.enabled(optimizationLevel, 64);
        if (!optimize3) {
            optimize = optimize3;
        } else {
            int i = 0;
            while (i < childCount) {
                ConstraintWidget child = layout.mChildren.get(i);
                boolean matchWidth = child.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean optimize4 = optimize3;
                boolean matchHeight = child.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean ratio2 = matchWidth && matchHeight && child.getDimensionRatio() > 0.0f;
                if (child.isInHorizontalChain() && ratio2) {
                    ratio = false;
                    break;
                } else if (child.isInVerticalChain() && ratio2) {
                    ratio = false;
                    break;
                } else {
                    boolean matchWidth2 = child instanceof VirtualLayout;
                    if (matchWidth2) {
                        ratio = false;
                        break;
                    }
                    if (!child.isInHorizontalChain() && !child.isInVerticalChain()) {
                        i++;
                        optimize3 = optimize4;
                    } else {
                        ratio = false;
                        break;
                    }
                }
            }
            optimize = optimize3;
        }
        ratio = optimize;
        if (!ratio || LinearSystem.sMetrics == null) {
            layoutTime = 0;
        } else {
            Metrics metrics = LinearSystem.sMetrics;
            layoutTime = 0;
            long layoutTime2 = metrics.measures;
            metrics.measures = layoutTime2 + 1;
        }
        boolean allSolved = false;
        boolean optimize5 = ((widthMode == 1073741824 && heightMode == 1073741824) || optimizeWrap) & ratio;
        int computations = 0;
        if (!optimize5) {
            widthSize2 = widthSize;
            heightSize2 = heightSize;
        } else {
            widthSize2 = Math.min(layout.getMaxWidth(), widthSize);
            heightSize2 = Math.min(layout.getMaxHeight(), heightSize);
            if (widthMode == 1073741824 && layout.getWidth() != widthSize2) {
                layout.setWidth(widthSize2);
                layout.invalidateGraph();
            }
            if (heightMode == 1073741824 && layout.getHeight() != heightSize2) {
                layout.setHeight(heightSize2);
                layout.invalidateGraph();
            }
            if (widthMode == 1073741824 && heightMode == 1073741824) {
                allSolved = layout.directMeasure(optimizeWrap);
                computations = 2;
                z = true;
            } else {
                allSolved = layout.directMeasureSetup(optimizeWrap);
                if (widthMode == 1073741824) {
                    allSolved &= layout.directMeasureWithOrientation(optimizeWrap, 0);
                    computations = 0 + 1;
                }
                if (heightMode != 1073741824) {
                    z = true;
                } else {
                    z = true;
                    allSolved &= layout.directMeasureWithOrientation(optimizeWrap, 1);
                    computations++;
                }
            }
            if (allSolved) {
                if (widthMode != 1073741824) {
                    z = false;
                }
                layout.updateFromRuns(z, heightMode == 1073741824);
            }
        }
        if (!allSolved || computations != 2) {
            int optimizations3 = layout.getOptimizationLevel();
            if (childCount > 0) {
                measureChildren(layout);
            }
            updateHierarchy(layout);
            int sizeDependentWidgetsCount2 = this.mVariableDimensionsWidgets.size();
            if (childCount > 0) {
                solveLinearSystem(layout, "First pass", startingWidth3, startingHeight);
            }
            if (sizeDependentWidgetsCount2 > 0) {
                boolean containerWrapWidth2 = layout.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean containerWrapHeight = layout.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                int minWidth = Math.max(layout.getWidth(), this.constraintWidgetContainer.getMinWidth());
                int minHeight = Math.max(layout.getHeight(), this.constraintWidgetContainer.getMinHeight());
                int minHeight2 = 0;
                int widthSize3 = 0;
                int minWidth2 = minWidth;
                int minHeight3 = minHeight;
                while (widthSize3 < sizeDependentWidgetsCount2) {
                    int heightSize3 = heightSize2;
                    ConstraintWidget widget = this.mVariableDimensionsWidgets.get(widthSize3);
                    int computations2 = computations;
                    if (!(widget instanceof VirtualLayout)) {
                        measurer2 = measurer3;
                        optimizations2 = optimizations3;
                        startingWidth2 = startingWidth3;
                    } else {
                        int preWidth = widget.getWidth();
                        optimizations2 = optimizations3;
                        int preHeight = widget.getHeight();
                        startingWidth2 = startingWidth3;
                        int measure = minHeight2 | measure(measurer3, widget, Measure.TRY_GIVEN_DIMENSIONS);
                        if (layout.mMetrics == null) {
                            needSolverPass = measure;
                            measurer2 = measurer3;
                        } else {
                            needSolverPass = measure;
                            measurer2 = measurer3;
                            layout.mMetrics.measuredMatchWidgets++;
                        }
                        int measuredWidth2 = widget.getWidth();
                        int measuredHeight = widget.getHeight();
                        if (measuredWidth2 != preWidth) {
                            widget.setWidth(measuredWidth2);
                            if (containerWrapWidth2 && widget.getRight() > minWidth2) {
                                int w = widget.getRight() + widget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                minWidth2 = Math.max(minWidth2, w);
                            }
                            measuredWidth = 1;
                        } else {
                            measuredWidth = needSolverPass;
                        }
                        if (measuredHeight != preHeight) {
                            widget.setHeight(measuredHeight);
                            if (containerWrapHeight && widget.getBottom() > minHeight3) {
                                int h = widget.getBottom() + widget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                minHeight3 = Math.max(minHeight3, h);
                            }
                            measuredWidth = true;
                        }
                        VirtualLayout virtualLayout = (VirtualLayout) widget;
                        minHeight2 = measuredWidth | virtualLayout.needSolverPass();
                    }
                    widthSize3++;
                    heightSize2 = heightSize3;
                    computations = computations2;
                    optimizations3 = optimizations2;
                    startingWidth3 = startingWidth2;
                    measurer3 = measurer2;
                }
                Measurer measurer4 = measurer3;
                optimizations = optimizations3;
                int startingWidth4 = startingWidth3;
                int maxIterations2 = 2;
                int j2 = 0;
                while (true) {
                    if (j2 >= maxIterations2) {
                        startingWidth = startingWidth4;
                        break;
                    }
                    int i2 = 0;
                    while (i2 < sizeDependentWidgetsCount2) {
                        ConstraintWidget widget2 = this.mVariableDimensionsWidgets.get(i2);
                        if (((widget2 instanceof Helper) && !(widget2 instanceof VirtualLayout)) || (widget2 instanceof Guideline) || widget2.getVisibility() == 8 || ((optimize5 && widget2.horizontalRun.dimension.resolved && widget2.verticalRun.dimension.resolved) || (widget2 instanceof VirtualLayout))) {
                            containerWrapWidth = containerWrapWidth2;
                            maxIterations = maxIterations2;
                            j = j2;
                            optimize2 = optimize5;
                            sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                            measurer = measurer4;
                        } else {
                            int preWidth2 = widget2.getWidth();
                            int preHeight2 = widget2.getHeight();
                            optimize2 = optimize5;
                            int preBaselineDistance = widget2.getBaselineDistance();
                            int measureStrategy2 = Measure.TRY_GIVEN_DIMENSIONS;
                            sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                            if (j2 != maxIterations2 - 1) {
                                measureStrategy = measureStrategy2;
                            } else {
                                int measureStrategy3 = Measure.USE_GIVEN_DIMENSIONS;
                                measureStrategy = measureStrategy3;
                            }
                            maxIterations = maxIterations2;
                            Measurer measurer5 = measurer4;
                            boolean hasMeasure = measure(measurer5, widget2, measureStrategy);
                            int i3 = minHeight2 | hasMeasure;
                            if (layout.mMetrics == null) {
                                measurer = measurer5;
                                j = j2;
                            } else {
                                measurer = measurer5;
                                j = j2;
                                layout.mMetrics.measuredMatchWidgets++;
                            }
                            int measuredWidth3 = widget2.getWidth();
                            int measuredHeight2 = widget2.getHeight();
                            if (measuredWidth3 == preWidth2) {
                                containerWrapWidth = containerWrapWidth2;
                            } else {
                                widget2.setWidth(measuredWidth3);
                                if (!containerWrapWidth2 || widget2.getRight() <= minWidth2) {
                                    containerWrapWidth = containerWrapWidth2;
                                } else {
                                    containerWrapWidth = containerWrapWidth2;
                                    int w2 = widget2.getRight() + widget2.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                    minWidth2 = Math.max(minWidth2, w2);
                                }
                                i3 = 1;
                            }
                            if (measuredHeight2 != preHeight2) {
                                widget2.setHeight(measuredHeight2);
                                if (containerWrapHeight && widget2.getBottom() > minHeight3) {
                                    int h2 = widget2.getBottom() + widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                    minHeight3 = Math.max(minHeight3, h2);
                                }
                                i3 = 1;
                            }
                            if (!widget2.hasBaseline() || preBaselineDistance == widget2.getBaselineDistance()) {
                                minHeight2 = i3;
                            } else {
                                minHeight2 = 1;
                            }
                        }
                        i2++;
                        optimize5 = optimize2;
                        maxIterations2 = maxIterations;
                        sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
                        j2 = j;
                        measurer4 = measurer;
                        containerWrapWidth2 = containerWrapWidth;
                    }
                    boolean containerWrapWidth3 = containerWrapWidth2;
                    int maxIterations3 = maxIterations2;
                    int j3 = j2;
                    boolean optimize6 = optimize5;
                    int sizeDependentWidgetsCount3 = sizeDependentWidgetsCount2;
                    Measurer measurer6 = measurer4;
                    if (minHeight2 != 0) {
                        solveLinearSystem(layout, "intermediate pass", startingWidth4, startingHeight);
                        minHeight2 = 0;
                        j2 = j3 + 1;
                        optimize5 = optimize6;
                        maxIterations2 = maxIterations3;
                        sizeDependentWidgetsCount2 = sizeDependentWidgetsCount3;
                        measurer4 = measurer6;
                        containerWrapWidth2 = containerWrapWidth3;
                    } else {
                        startingWidth = startingWidth4;
                        break;
                    }
                }
                if (minHeight2 != 0) {
                    solveLinearSystem(layout, "2nd pass", startingWidth, startingHeight);
                    boolean needSolverPass2 = false;
                    if (layout.getWidth() < minWidth2) {
                        layout.setWidth(minWidth2);
                        needSolverPass2 = true;
                    }
                    if (layout.getHeight() < minHeight3) {
                        layout.setHeight(minHeight3);
                        needSolverPass2 = true;
                    }
                    if (needSolverPass2) {
                        solveLinearSystem(layout, "3rd pass", startingWidth, startingHeight);
                    }
                }
            } else {
                optimizations = optimizations3;
            }
            layout.setOptimizationLevel(optimizations);
        }
        return layoutTime;
    }

    private boolean measure(Measurer measurer, ConstraintWidget widget, int measureStrategy) {
        this.mMeasure.horizontalBehavior = widget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = widget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = widget.getWidth();
        this.mMeasure.verticalDimension = widget.getHeight();
        this.mMeasure.measuredNeedsSolverPass = false;
        this.mMeasure.measureStrategy = measureStrategy;
        boolean horizontalMatchConstraints = this.mMeasure.horizontalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean verticalMatchConstraints = this.mMeasure.verticalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean horizontalUseRatio = horizontalMatchConstraints && widget.mDimensionRatio > 0.0f;
        boolean verticalUseRatio = verticalMatchConstraints && widget.mDimensionRatio > 0.0f;
        if (horizontalUseRatio && widget.mResolvedMatchConstraintDefault[0] == 4) {
            this.mMeasure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (verticalUseRatio && widget.mResolvedMatchConstraintDefault[1] == 4) {
            this.mMeasure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(widget, this.mMeasure);
        widget.setWidth(this.mMeasure.measuredWidth);
        widget.setHeight(this.mMeasure.measuredHeight);
        widget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        widget.setBaselineDistance(this.mMeasure.measuredBaseline);
        this.mMeasure.measureStrategy = Measure.SELF_DIMENSIONS;
        return this.mMeasure.measuredNeedsSolverPass;
    }
}