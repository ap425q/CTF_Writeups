package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.ChainHead;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Direct {
    private static final boolean APPLY_MATCH_PARENT = false;
    private static final boolean DEBUG = false;
    private static BasicMeasure.Measure measure = new BasicMeasure.Measure();

    public static void solvingPass(ConstraintWidgetContainer layout, BasicMeasure.Measurer measurer) {
        int i;
        ConstraintWidget.DimensionBehaviour horizontal = layout.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour vertical = layout.getVerticalDimensionBehaviour();
        layout.resetFinalResolution();
        ArrayList<ConstraintWidget> children = layout.getChildren();
        int count = children.size();
        for (int i2 = 0; i2 < count; i2++) {
            children.get(i2).resetFinalResolution();
        }
        boolean isRtl = layout.isRtl();
        if (horizontal == ConstraintWidget.DimensionBehaviour.FIXED) {
            layout.setFinalHorizontal(0, layout.getWidth());
        } else {
            layout.setFinalLeft(0);
        }
        boolean hasGuideline = false;
        boolean hasBarrier = false;
        int i3 = 0;
        while (true) {
            i = -1;
            if (i3 >= count) {
                break;
            }
            ConstraintWidget child = children.get(i3);
            if (child instanceof Guideline) {
                Guideline guideline = (Guideline) child;
                if (guideline.getOrientation() == 1) {
                    if (guideline.getRelativeBegin() != -1) {
                        guideline.setFinalValue(guideline.getRelativeBegin());
                    } else if (guideline.getRelativeEnd() != -1 && layout.isResolvedHorizontally()) {
                        guideline.setFinalValue(layout.getWidth() - guideline.getRelativeEnd());
                    } else if (layout.isResolvedHorizontally()) {
                        int position = (int) ((guideline.getRelativePercent() * layout.getWidth()) + 0.5f);
                        guideline.setFinalValue(position);
                    }
                    hasGuideline = true;
                }
            } else if ((child instanceof Barrier) && ((Barrier) child).getOrientation() == 0) {
                hasBarrier = true;
            }
            i3++;
        }
        if (hasGuideline) {
            for (int i4 = 0; i4 < count; i4++) {
                ConstraintWidget child2 = children.get(i4);
                if (child2 instanceof Guideline) {
                    Guideline guideline2 = (Guideline) child2;
                    if (guideline2.getOrientation() == 1) {
                        horizontalSolvingPass(guideline2, measurer, isRtl);
                    }
                }
            }
        }
        horizontalSolvingPass(layout, measurer, isRtl);
        if (hasBarrier) {
            for (int i5 = 0; i5 < count; i5++) {
                ConstraintWidget child3 = children.get(i5);
                if (child3 instanceof Barrier) {
                    Barrier barrier = (Barrier) child3;
                    if (barrier.getOrientation() == 0) {
                        solveBarrier(barrier, measurer, 0, isRtl);
                    }
                }
            }
        }
        if (vertical == ConstraintWidget.DimensionBehaviour.FIXED) {
            layout.setFinalVertical(0, layout.getHeight());
        } else {
            layout.setFinalTop(0);
        }
        boolean hasGuideline2 = false;
        boolean hasBarrier2 = false;
        int i6 = 0;
        while (i6 < count) {
            ConstraintWidget child4 = children.get(i6);
            if (child4 instanceof Guideline) {
                Guideline guideline3 = (Guideline) child4;
                if (guideline3.getOrientation() == 0) {
                    if (guideline3.getRelativeBegin() != i) {
                        guideline3.setFinalValue(guideline3.getRelativeBegin());
                    } else if (guideline3.getRelativeEnd() != i && layout.isResolvedVertically()) {
                        guideline3.setFinalValue(layout.getHeight() - guideline3.getRelativeEnd());
                    } else if (layout.isResolvedVertically()) {
                        int position2 = (int) ((guideline3.getRelativePercent() * layout.getHeight()) + 0.5f);
                        guideline3.setFinalValue(position2);
                    }
                    hasGuideline2 = true;
                }
            } else if ((child4 instanceof Barrier) && ((Barrier) child4).getOrientation() == 1) {
                hasBarrier2 = true;
            }
            i6++;
            i = -1;
        }
        if (hasGuideline2) {
            for (int i7 = 0; i7 < count; i7++) {
                ConstraintWidget child5 = children.get(i7);
                if (child5 instanceof Guideline) {
                    Guideline guideline4 = (Guideline) child5;
                    if (guideline4.getOrientation() == 0) {
                        verticalSolvingPass(guideline4, measurer);
                    }
                }
            }
        }
        verticalSolvingPass(layout, measurer);
        if (hasBarrier2) {
            for (int i8 = 0; i8 < count; i8++) {
                ConstraintWidget child6 = children.get(i8);
                if (child6 instanceof Barrier) {
                    Barrier barrier2 = (Barrier) child6;
                    if (barrier2.getOrientation() == 1) {
                        solveBarrier(barrier2, measurer, 1, isRtl);
                    }
                }
            }
        }
        for (int i9 = 0; i9 < count; i9++) {
            ConstraintWidget child7 = children.get(i9);
            if (child7.isMeasureRequested() && canMeasure(child7)) {
                ConstraintWidgetContainer.measure(child7, measurer, measure, BasicMeasure.Measure.SELF_DIMENSIONS);
                horizontalSolvingPass(child7, measurer, isRtl);
                verticalSolvingPass(child7, measurer);
            }
        }
    }

    private static void solveBarrier(Barrier barrier, BasicMeasure.Measurer measurer, int orientation, boolean isRtl) {
        if (barrier.allSolved()) {
            if (orientation == 0) {
                horizontalSolvingPass(barrier, measurer, isRtl);
            } else {
                verticalSolvingPass(barrier, measurer);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:124:0x01e5, code lost:
        if (r11.getDimensionRatio() != 0.0f) goto L155;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void horizontalSolvingPass(androidx.constraintlayout.solver.widgets.ConstraintWidget r19, androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measurer r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 611
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.Direct.horizontalSolvingPass(androidx.constraintlayout.solver.widgets.ConstraintWidget, androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure$Measurer, boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:122:0x01d7, code lost:
        if (r10.getDimensionRatio() != 0.0f) goto L151;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void verticalSolvingPass(androidx.constraintlayout.solver.widgets.ConstraintWidget r18, androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure.Measurer r19) {
        /*
            Method dump skipped, instructions count: 686
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.Direct.verticalSolvingPass(androidx.constraintlayout.solver.widgets.ConstraintWidget, androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure$Measurer):void");
    }

    private static void solveHorizontalCenterConstraints(BasicMeasure.Measurer measurer, ConstraintWidget widget, boolean isRtl) {
        float bias = widget.getHorizontalBiasPercent();
        int start = widget.mLeft.mTarget.getFinalValue();
        int end = widget.mRight.mTarget.getFinalValue();
        int s1 = widget.mLeft.getMargin() + start;
        int s2 = end - widget.mRight.getMargin();
        if (start == end) {
            bias = 0.5f;
            s1 = start;
            s2 = end;
        }
        int width = widget.getWidth();
        int distance = (s2 - s1) - width;
        if (s1 > s2) {
            distance = (s1 - s2) - width;
        }
        int d1 = (int) ((distance * bias) + 0.5f);
        int x1 = s1 + d1;
        int x2 = x1 + width;
        if (s1 > s2) {
            x1 = s1 + d1;
            x2 = x1 - width;
        }
        widget.setFinalHorizontal(x1, x2);
        horizontalSolvingPass(widget, measurer, isRtl);
    }

    private static void solveVerticalCenterConstraints(BasicMeasure.Measurer measurer, ConstraintWidget widget) {
        float bias = widget.getVerticalBiasPercent();
        int start = widget.mTop.mTarget.getFinalValue();
        int end = widget.mBottom.mTarget.getFinalValue();
        int s1 = widget.mTop.getMargin() + start;
        int s2 = end - widget.mBottom.getMargin();
        if (start == end) {
            bias = 0.5f;
            s1 = start;
            s2 = end;
        }
        int height = widget.getHeight();
        int distance = (s2 - s1) - height;
        if (s1 > s2) {
            distance = (s1 - s2) - height;
        }
        int d1 = (int) ((distance * bias) + 0.5f);
        int y1 = s1 + d1;
        int y2 = y1 + height;
        if (s1 > s2) {
            y1 = s1 - d1;
            y2 = y1 - height;
        }
        widget.setFinalVertical(y1, y2);
        verticalSolvingPass(widget, measurer);
    }

    private static void solveHorizontalMatchConstraint(ConstraintWidget layout, BasicMeasure.Measurer measurer, ConstraintWidget widget, boolean isRtl) {
        int parentWidth;
        float bias = widget.getHorizontalBiasPercent();
        int s1 = widget.mLeft.mTarget.getFinalValue() + widget.mLeft.getMargin();
        int s2 = widget.mRight.mTarget.getFinalValue() - widget.mRight.getMargin();
        if (s2 >= s1) {
            int width = widget.getWidth();
            if (widget.getVisibility() != 8) {
                if (widget.mMatchConstraintDefaultWidth == 2) {
                    if (layout instanceof ConstraintWidgetContainer) {
                        parentWidth = layout.getWidth();
                    } else {
                        parentWidth = layout.getParent().getWidth();
                    }
                    width = (int) (widget.getHorizontalBiasPercent() * 0.5f * parentWidth);
                } else if (widget.mMatchConstraintDefaultWidth == 0) {
                    width = s2 - s1;
                }
                width = Math.max(widget.mMatchConstraintMinWidth, width);
                if (widget.mMatchConstraintMaxWidth > 0) {
                    width = Math.min(widget.mMatchConstraintMaxWidth, width);
                }
            }
            int distance = (s2 - s1) - width;
            int d1 = (int) ((distance * bias) + 0.5f);
            int x1 = s1 + d1;
            int x2 = x1 + width;
            widget.setFinalHorizontal(x1, x2);
            horizontalSolvingPass(widget, measurer, isRtl);
        }
    }

    private static void solveVerticalMatchConstraint(ConstraintWidget layout, BasicMeasure.Measurer measurer, ConstraintWidget widget) {
        int parentHeight;
        float bias = widget.getVerticalBiasPercent();
        int s1 = widget.mTop.mTarget.getFinalValue() + widget.mTop.getMargin();
        int s2 = widget.mBottom.mTarget.getFinalValue() - widget.mBottom.getMargin();
        if (s2 >= s1) {
            int height = widget.getHeight();
            if (widget.getVisibility() != 8) {
                if (widget.mMatchConstraintDefaultHeight == 2) {
                    if (layout instanceof ConstraintWidgetContainer) {
                        parentHeight = layout.getHeight();
                    } else {
                        parentHeight = layout.getParent().getHeight();
                    }
                    height = (int) (bias * 0.5f * parentHeight);
                } else if (widget.mMatchConstraintDefaultHeight == 0) {
                    height = s2 - s1;
                }
                height = Math.max(widget.mMatchConstraintMinHeight, height);
                if (widget.mMatchConstraintMaxHeight > 0) {
                    height = Math.min(widget.mMatchConstraintMaxHeight, height);
                }
            }
            int distance = (s2 - s1) - height;
            int d1 = (int) ((distance * bias) + 0.5f);
            int y1 = s1 + d1;
            int y2 = y1 + height;
            widget.setFinalVertical(y1, y2);
            verticalSolvingPass(widget, measurer);
        }
    }

    private static boolean canMeasure(ConstraintWidget layout) {
        ConstraintWidget.DimensionBehaviour horizontalBehaviour = layout.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalBehaviour = layout.getVerticalDimensionBehaviour();
        ConstraintWidgetContainer parent = layout.getParent() != null ? (ConstraintWidgetContainer) layout.getParent() : null;
        if (parent == null || parent.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.FIXED) {
        }
        if (parent == null || parent.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.FIXED) {
        }
        boolean isHorizontalFixed = horizontalBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || horizontalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (horizontalBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && layout.mMatchConstraintDefaultWidth == 0 && layout.mDimensionRatio == 0.0f && layout.hasDanglingDimension(0)) || layout.isResolvedHorizontally();
        boolean isVerticalFixed = verticalBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || verticalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (verticalBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && layout.mMatchConstraintDefaultHeight == 0 && layout.mDimensionRatio == 0.0f && layout.hasDanglingDimension(1)) || layout.isResolvedVertically();
        if (layout.mDimensionRatio <= 0.0f || !(isHorizontalFixed || isVerticalFixed)) {
            return isHorizontalFixed && isVerticalFixed;
        }
        return true;
    }

    public static boolean solveChain(ConstraintWidgetContainer container, LinearSystem system, int orientation, int offset, ChainHead chainHead, boolean isChainSpread, boolean isChainSpreadInside, boolean isChainPacked) {
        int startPoint;
        int endPoint;
        int distance;
        ConstraintWidget widget;
        int i;
        int gap;
        int current;
        int gap2;
        float bias;
        BasicMeasure.Measure measure2;
        int totalSize;
        ConstraintWidget next;
        if (isChainPacked) {
            return false;
        }
        if (orientation == 0) {
            if (!container.isResolvedHorizontally()) {
                return false;
            }
        } else if (!container.isResolvedVertically()) {
            return false;
        }
        boolean isRtl = container.isRtl();
        ConstraintWidget first = chainHead.getFirst();
        ConstraintWidget last = chainHead.getLast();
        ConstraintWidget firstVisibleWidget = chainHead.getFirstVisibleWidget();
        ConstraintWidget lastVisibleWidget = chainHead.getLastVisibleWidget();
        ConstraintWidget head = chainHead.getHead();
        ConstraintWidget widget2 = first;
        ConstraintWidget next2 = null;
        boolean done = false;
        ConstraintAnchor begin = first.mListAnchors[offset];
        ConstraintAnchor end = last.mListAnchors[offset + 1];
        if (begin.mTarget == null || end.mTarget == null || !begin.mTarget.hasFinalValue() || !end.mTarget.hasFinalValue() || firstVisibleWidget == null || lastVisibleWidget == null || (distance = (endPoint = end.mTarget.getFinalValue() - lastVisibleWidget.mListAnchors[offset + 1].getMargin()) - (startPoint = begin.mTarget.getFinalValue() + firstVisibleWidget.mListAnchors[offset].getMargin())) <= 0) {
            return false;
        }
        int totalSize2 = 0;
        BasicMeasure.Measure measure3 = new BasicMeasure.Measure();
        int numWidgets = 0;
        int numVisibleWidgets = 0;
        while (!done) {
            ConstraintAnchor begin2 = widget2.mListAnchors[offset];
            boolean canMeasure = canMeasure(widget2);
            if (!canMeasure) {
                return false;
            }
            ConstraintWidget last2 = last;
            if (widget2.mListDimensionBehaviors[orientation] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                return false;
            }
            if (widget2.isMeasureRequested()) {
                measure2 = measure3;
                ConstraintWidgetContainer.measure(widget2, container.getMeasurer(), measure2, BasicMeasure.Measure.SELF_DIMENSIONS);
            } else {
                measure2 = measure3;
            }
            int totalSize3 = totalSize2 + widget2.mListAnchors[offset].getMargin();
            if (orientation == 0) {
                totalSize = totalSize3 + widget2.getWidth();
            } else {
                totalSize = totalSize3 + widget2.getHeight();
            }
            totalSize2 = totalSize + widget2.mListAnchors[offset + 1].getMargin();
            numWidgets++;
            int numWidgets2 = widget2.getVisibility();
            if (numWidgets2 != 8) {
                numVisibleWidgets++;
            }
            ConstraintAnchor nextAnchor = widget2.mListAnchors[offset + 1].mTarget;
            if (nextAnchor != null) {
                ConstraintWidget next3 = nextAnchor.mOwner;
                next = (next3.mListAnchors[offset].mTarget == null || next3.mListAnchors[offset].mTarget.mOwner != widget2) ? null : next3;
            } else {
                next = null;
            }
            if (next != null) {
                widget2 = next;
            } else {
                done = true;
            }
            measure3 = measure2;
            last = last2;
            next2 = next;
        }
        ConstraintWidget next4 = next2;
        int numWidgets3 = numWidgets;
        int numVisibleWidgets2 = numVisibleWidgets;
        if (numVisibleWidgets2 == 0 || numVisibleWidgets2 != numWidgets3 || distance < totalSize2) {
            return false;
        }
        int gap3 = distance - totalSize2;
        if (isChainSpread) {
            widget = widget2;
            gap = gap3 / (numVisibleWidgets2 + 1);
            i = 1;
        } else {
            if (!isChainSpreadInside) {
                widget = widget2;
                i = 1;
            } else if (numVisibleWidgets2 <= 2) {
                widget = widget2;
                i = 1;
            } else {
                widget = widget2;
                i = 1;
                gap = (gap3 / numVisibleWidgets2) - 1;
            }
            gap = gap3;
        }
        if (numVisibleWidgets2 == i) {
            if (orientation == 0) {
                bias = head.getHorizontalBiasPercent();
            } else {
                bias = head.getVerticalBiasPercent();
            }
            int p1 = (int) (startPoint + 0.5f + (gap * bias));
            if (orientation == 0) {
                firstVisibleWidget.setFinalHorizontal(p1, firstVisibleWidget.getWidth() + p1);
            } else {
                firstVisibleWidget.setFinalVertical(p1, firstVisibleWidget.getHeight() + p1);
            }
            horizontalSolvingPass(firstVisibleWidget, container.getMeasurer(), isRtl);
            return true;
        } else if (!isChainSpread) {
            if (!isChainSpreadInside) {
                return true;
            }
            if (numVisibleWidgets2 == 2) {
                if (orientation == 0) {
                    firstVisibleWidget.setFinalHorizontal(startPoint, firstVisibleWidget.getWidth() + startPoint);
                    lastVisibleWidget.setFinalHorizontal(endPoint - lastVisibleWidget.getWidth(), endPoint);
                    horizontalSolvingPass(firstVisibleWidget, container.getMeasurer(), isRtl);
                    horizontalSolvingPass(lastVisibleWidget, container.getMeasurer(), isRtl);
                    return true;
                }
                firstVisibleWidget.setFinalVertical(startPoint, firstVisibleWidget.getHeight() + startPoint);
                lastVisibleWidget.setFinalVertical(endPoint - lastVisibleWidget.getHeight(), endPoint);
                verticalSolvingPass(firstVisibleWidget, container.getMeasurer());
                verticalSolvingPass(lastVisibleWidget, container.getMeasurer());
                return true;
            }
            return false;
        } else {
            int current2 = startPoint + gap;
            ConstraintWidget widget3 = first;
            boolean done2 = false;
            while (!done2) {
                ConstraintAnchor begin3 = widget3.mListAnchors[offset];
                ConstraintWidget first2 = first;
                if (widget3.getVisibility() == 8) {
                    if (orientation == 0) {
                        widget3.setFinalHorizontal(current2, current2);
                        horizontalSolvingPass(widget3, container.getMeasurer(), isRtl);
                    } else {
                        widget3.setFinalVertical(current2, current2);
                        verticalSolvingPass(widget3, container.getMeasurer());
                    }
                } else {
                    int current3 = current2 + widget3.mListAnchors[offset].getMargin();
                    if (orientation == 0) {
                        widget3.setFinalHorizontal(current3, widget3.getWidth() + current3);
                        horizontalSolvingPass(widget3, container.getMeasurer(), isRtl);
                        current = current3 + widget3.getWidth();
                    } else {
                        widget3.setFinalVertical(current3, widget3.getHeight() + current3);
                        verticalSolvingPass(widget3, container.getMeasurer());
                        current = current3 + widget3.getHeight();
                    }
                    current2 = current + widget3.mListAnchors[offset + 1].getMargin() + gap;
                }
                widget3.addToSolver(system, false);
                ConstraintAnchor nextAnchor2 = widget3.mListAnchors[offset + 1].mTarget;
                if (nextAnchor2 != null) {
                    gap2 = gap;
                    ConstraintWidget next5 = nextAnchor2.mOwner;
                    next4 = (next5.mListAnchors[offset].mTarget == null || next5.mListAnchors[offset].mTarget.mOwner != widget3) ? null : next5;
                } else {
                    gap2 = gap;
                    next4 = null;
                }
                if (next4 != null) {
                    widget3 = next4;
                } else {
                    done2 = true;
                }
                first = first2;
                gap = gap2;
            }
            return true;
        }
    }
}