package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: output.jar:androidx/constraintlayout/core/widgets/analyzer/ChainRun.class */
public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList<WidgetRun> widgets;

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.widgets = new ArrayList<>();
        this.orientation = i;
        build();
    }

    private void build() {
        ConstraintWidget constraintWidget = this.widget;
        ConstraintWidget previousChainMember = constraintWidget.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget2 = previousChainMember;
            if (constraintWidget2 == null) {
                break;
            }
            constraintWidget = constraintWidget2;
            previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        }
        this.widget = constraintWidget;
        this.widgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = nextChainMember;
            if (constraintWidget3 == null) {
                break;
            }
            this.widgets.add(constraintWidget3.getRun(this.orientation));
            nextChainMember = constraintWidget3.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            if (this.orientation == 0) {
                next.widget.horizontalChainRun = this;
            } else if (this.orientation == 1) {
                next.widget.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl()) && this.widgets.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.widgets;
            this.widget = arrayList.get(arrayList.size() - 1).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.widgets.get(size);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void apply() {
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
        int size = this.widgets.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = this.widgets.get(0).widget;
        ConstraintWidget constraintWidget2 = this.widgets.get(size - 1).widget;
        if (this.orientation == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
            DependencyNode target = getTarget(constraintAnchor, 0);
            int margin = constraintAnchor.getMargin();
            ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
            if (firstVisibleWidget != null) {
                margin = firstVisibleWidget.mLeft.getMargin();
            }
            if (target != null) {
                addTarget(this.start, target, margin);
            }
            DependencyNode target2 = getTarget(constraintAnchor2, 0);
            int margin2 = constraintAnchor2.getMargin();
            ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
            if (lastVisibleWidget != null) {
                margin2 = lastVisibleWidget.mRight.getMargin();
            }
            if (target2 != null) {
                addTarget(this.end, target2, -margin2);
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
            DependencyNode target3 = getTarget(constraintAnchor3, 1);
            int margin3 = constraintAnchor3.getMargin();
            ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
            if (firstVisibleWidget2 != null) {
                margin3 = firstVisibleWidget2.mTop.getMargin();
            }
            if (target3 != null) {
                addTarget(this.start, target3, margin3);
            }
            DependencyNode target4 = getTarget(constraintAnchor4, 1);
            int margin4 = constraintAnchor4.getMargin();
            ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
            if (lastVisibleWidget2 != null) {
                margin4 = lastVisibleWidget2.mBottom.getMargin();
            }
            if (target4 != null) {
                addTarget(this.end, target4, -margin4);
            }
        }
        this.start.updateDelegate = this;
        this.end.updateDelegate = this;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i).applyToWidget();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        WidgetRun widgetRun;
        int size = this.widgets.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            j = j + widgetRun.start.margin + this.widgets.get(i).getWrapDimension() + widgetRun.end.margin;
        }
        return j;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i = 0; i < size; i++) {
            if (!this.widgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            sb.append("<");
            sb.append(it.next());
            sb.append("> ");
        }
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        float f;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z;
        int i10;
        int i11;
        int i12;
        int i13;
        float f2;
        int i14;
        int i15;
        if (!this.start.resolved || !this.end.resolved) {
            return;
        }
        ConstraintWidget parent = this.widget.getParent();
        boolean isRtl = parent instanceof ConstraintWidgetContainer ? ((ConstraintWidgetContainer) parent).isRtl() : false;
        int i16 = this.end.value - this.start.value;
        int size = this.widgets.size();
        int i17 = 0;
        while (true) {
            if (i17 >= size) {
                i = -1;
                break;
            }
            i = i17;
            if (this.widgets.get(i17).widget.getVisibility() != 8) {
                break;
            }
            i17++;
        }
        int i18 = size - 1;
        int i19 = i18;
        while (true) {
            i2 = -1;
            if (i19 < 0) {
                break;
            }
            if (this.widgets.get(i19).widget.getVisibility() != 8) {
                i2 = i19;
                break;
            }
            i19--;
        }
        for (int i20 = 0; i20 < 2; i20++) {
            int i21 = 0;
            i4 = 0;
            int i22 = 0;
            int i23 = 0;
            f = 0.0f;
            while (i21 < size) {
                WidgetRun widgetRun = this.widgets.get(i21);
                if (widgetRun.widget.getVisibility() == 8) {
                    i14 = i4;
                    i15 = i22;
                } else {
                    int i24 = i23 + 1;
                    int i25 = i4;
                    if (i21 > 0) {
                        i25 = i4;
                        if (i21 >= i) {
                            i25 = i4 + widgetRun.start.margin;
                        }
                    }
                    int i26 = widgetRun.dimension.value;
                    boolean z2 = widgetRun.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (!z2) {
                        if (widgetRun.matchConstraintsType == 1 && i20 == 0) {
                            i11 = i22 + 1;
                            i12 = widgetRun.dimension.wrapValue;
                        } else {
                            z = z2;
                            i10 = i26;
                            i11 = i22;
                            if (widgetRun.dimension.resolved) {
                                i11 = i22;
                                i12 = i26;
                            }
                        }
                        z = true;
                        i10 = i12;
                    } else if (this.orientation == 0 && !widgetRun.widget.horizontalRun.dimension.resolved) {
                        return;
                    } else {
                        z = z2;
                        i10 = i26;
                        i11 = i22;
                        if (this.orientation == 1) {
                            z = z2;
                            i10 = i26;
                            i11 = i22;
                            if (!widgetRun.widget.verticalRun.dimension.resolved) {
                                return;
                            }
                        }
                    }
                    if (z) {
                        i13 = i25 + i10;
                        f2 = f;
                    } else {
                        int i27 = i11 + 1;
                        float f3 = widgetRun.widget.mWeight[this.orientation];
                        i13 = i25;
                        i11 = i27;
                        f2 = f;
                        if (f3 >= 0.0f) {
                            f2 = f + f3;
                            i13 = i25;
                            i11 = i27;
                        }
                    }
                    i14 = i13;
                    i15 = i11;
                    i23 = i24;
                    f = f2;
                    if (i21 < i18) {
                        i14 = i13;
                        i15 = i11;
                        i23 = i24;
                        f = f2;
                        if (i21 < i2) {
                            i14 = i13 + (-widgetRun.end.margin);
                            f = f2;
                            i23 = i24;
                            i15 = i11;
                        }
                    }
                }
                i21++;
                i4 = i14;
                i22 = i15;
            }
            if (i4 < i16 || i22 == 0) {
                i3 = i23;
                i5 = i22;
                break;
            }
        }
        i3 = 0;
        i4 = 0;
        i5 = 0;
        f = 0.0f;
        int i28 = this.start.value;
        if (isRtl) {
            i28 = this.end.value;
        }
        int i29 = i28;
        if (i4 > i16) {
            i29 = isRtl ? i28 + ((int) (((i4 - i16) / 2.0f) + 0.5f)) : i28 - ((int) (((i4 - i16) / 2.0f) + 0.5f));
        }
        if (i5 > 0) {
            float f4 = i16 - i4;
            int i30 = (int) ((f4 / i5) + 0.5f);
            int i31 = 0;
            int i32 = i29;
            for (int i33 = 0; i33 < size; i33++) {
                WidgetRun widgetRun2 = this.widgets.get(i33);
                if (widgetRun2.widget.getVisibility() != 8 && widgetRun2.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !widgetRun2.dimension.resolved) {
                    int i34 = f > 0.0f ? (int) (((widgetRun2.widget.mWeight[this.orientation] * f4) / f) + 0.5f) : i30;
                    if (this.orientation == 0) {
                        i8 = widgetRun2.widget.mMatchConstraintMaxWidth;
                        i9 = widgetRun2.widget.mMatchConstraintMinWidth;
                    } else {
                        i8 = widgetRun2.widget.mMatchConstraintMaxHeight;
                        i9 = widgetRun2.widget.mMatchConstraintMinHeight;
                    }
                    int max = Math.max(i9, widgetRun2.matchConstraintsType == 1 ? Math.min(i34, widgetRun2.dimension.wrapValue) : i34);
                    int i35 = max;
                    if (i8 > 0) {
                        i35 = Math.min(i8, max);
                    }
                    int i36 = i34;
                    int i37 = i31;
                    if (i35 != i34) {
                        i37 = i31 + 1;
                        i36 = i35;
                    }
                    widgetRun2.dimension.resolve(i36);
                    i31 = i37;
                }
            }
            if (i31 > 0) {
                int i38 = i5 - i31;
                i7 = 0;
                for (int i39 = 0; i39 < size; i39++) {
                    WidgetRun widgetRun3 = this.widgets.get(i39);
                    if (widgetRun3.widget.getVisibility() != 8) {
                        int i40 = i7;
                        if (i39 > 0) {
                            i40 = i7;
                            if (i39 >= i) {
                                i40 = i7 + widgetRun3.start.margin;
                            }
                        }
                        int i41 = i40 + widgetRun3.dimension.value;
                        i7 = i41;
                        if (i39 < i18) {
                            i7 = i41;
                            if (i39 < i2) {
                                i7 = i41 + (-widgetRun3.end.margin);
                            }
                        }
                    }
                }
                i5 = i38;
            } else {
                i7 = i4;
            }
            if (this.chainStyle == 2 && i31 == 0) {
                this.chainStyle = 0;
                i4 = i7;
                i6 = i5;
                i29 = i32;
            } else {
                i4 = i7;
                i6 = i5;
                i29 = i32;
            }
        } else {
            i6 = i5;
        }
        if (i4 > i16) {
            this.chainStyle = 2;
        }
        if (i3 > 0 && i6 == 0 && i == i2) {
            this.chainStyle = 2;
        }
        int i42 = this.chainStyle;
        if (i42 != 1) {
            if (i42 == 0) {
                int i43 = (i16 - i4) / (i3 + 1);
                if (i6 > 0) {
                    i43 = 0;
                }
                for (int i44 = 0; i44 < size; i44++) {
                    WidgetRun widgetRun4 = this.widgets.get(isRtl ? size - (i44 + 1) : i44);
                    if (widgetRun4.widget.getVisibility() == 8) {
                        widgetRun4.start.resolve(i29);
                        widgetRun4.end.resolve(i29);
                    } else {
                        int i45 = isRtl ? i29 - i43 : i29 + i43;
                        int i46 = i45;
                        if (i44 > 0) {
                            i46 = i45;
                            if (i44 >= i) {
                                i46 = isRtl ? i45 - widgetRun4.start.margin : i45 + widgetRun4.start.margin;
                            }
                        }
                        if (isRtl) {
                            widgetRun4.end.resolve(i46);
                        } else {
                            widgetRun4.start.resolve(i46);
                        }
                        int i47 = widgetRun4.dimension.value;
                        int i48 = i47;
                        if (widgetRun4.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                            i48 = i47;
                            if (widgetRun4.matchConstraintsType == 1) {
                                i48 = Math.min(i47, widgetRun4.dimension.wrapValue);
                            }
                        }
                        int i49 = isRtl ? i46 - i48 : i46 + i48;
                        if (isRtl) {
                            widgetRun4.start.resolve(i49);
                        } else {
                            widgetRun4.end.resolve(i49);
                        }
                        i29 = i49;
                        if (i44 < i18) {
                            i29 = i49;
                            if (i44 < i2) {
                                i29 = isRtl ? i49 - (-widgetRun4.end.margin) : i49 + (-widgetRun4.end.margin);
                            }
                        }
                    }
                }
                return;
            } else if (i42 == 2) {
                float horizontalBiasPercent = this.orientation == 0 ? this.widget.getHorizontalBiasPercent() : this.widget.getVerticalBiasPercent();
                float f5 = horizontalBiasPercent;
                if (isRtl) {
                    f5 = 1.0f - horizontalBiasPercent;
                }
                int i50 = (((int) (((i16 - i4) * f5) + 0.5f)) < 0 || i6 > 0) ? 0 : 0;
                int i51 = isRtl ? i29 - i50 : i29 + i50;
                for (int i52 = 0; i52 < size; i52++) {
                    WidgetRun widgetRun5 = this.widgets.get(isRtl ? size - (i52 + 1) : i52);
                    if (widgetRun5.widget.getVisibility() == 8) {
                        widgetRun5.start.resolve(i51);
                        widgetRun5.end.resolve(i51);
                    } else {
                        int i53 = i51;
                        if (i52 > 0) {
                            i53 = i51;
                            if (i52 >= i) {
                                i53 = isRtl ? i51 - widgetRun5.start.margin : i51 + widgetRun5.start.margin;
                            }
                        }
                        if (isRtl) {
                            widgetRun5.end.resolve(i53);
                        } else {
                            widgetRun5.start.resolve(i53);
                        }
                        int i54 = widgetRun5.dimension.value;
                        if (widgetRun5.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun5.matchConstraintsType == 1) {
                            i54 = widgetRun5.dimension.wrapValue;
                        }
                        int i55 = isRtl ? i53 - i54 : i53 + i54;
                        if (isRtl) {
                            widgetRun5.start.resolve(i55);
                        } else {
                            widgetRun5.end.resolve(i55);
                        }
                        i51 = i55;
                        if (i52 < i18) {
                            i51 = i55;
                            if (i52 < i2) {
                                i51 = isRtl ? i55 - (-widgetRun5.end.margin) : i55 + (-widgetRun5.end.margin);
                            }
                        }
                    }
                }
                return;
            } else {
                return;
            }
        }
        int i56 = i3 > 1 ? (i16 - i4) / (i3 - 1) : i3 == 1 ? (i16 - i4) / 2 : 0;
        if (i6 > 0) {
            i56 = 0;
        }
        int i57 = 0;
        while (true) {
            int i58 = i29;
            if (i57 >= size) {
                return;
            }
            WidgetRun widgetRun6 = this.widgets.get(isRtl ? size - (i57 + 1) : i57);
            if (widgetRun6.widget.getVisibility() == 8) {
                widgetRun6.start.resolve(i58);
                widgetRun6.end.resolve(i58);
                i29 = i58;
            } else {
                int i59 = i58;
                if (i57 > 0) {
                    i59 = isRtl ? i58 - i56 : i58 + i56;
                }
                int i60 = i59;
                if (i57 > 0) {
                    i60 = i59;
                    if (i57 >= i) {
                        i60 = isRtl ? i59 - widgetRun6.start.margin : i59 + widgetRun6.start.margin;
                    }
                }
                if (isRtl) {
                    widgetRun6.end.resolve(i60);
                } else {
                    widgetRun6.start.resolve(i60);
                }
                int i61 = widgetRun6.dimension.value;
                int i62 = i61;
                if (widgetRun6.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i62 = i61;
                    if (widgetRun6.matchConstraintsType == 1) {
                        i62 = widgetRun6.dimension.wrapValue;
                    }
                }
                int i63 = isRtl ? i60 - i62 : i60 + i62;
                if (isRtl) {
                    widgetRun6.start.resolve(i63);
                } else {
                    widgetRun6.end.resolve(i63);
                }
                widgetRun6.resolved = true;
                i29 = i63;
                if (i57 < i18) {
                    i29 = i63;
                    if (i57 < i2) {
                        i29 = isRtl ? i63 - (-widgetRun6.end.margin) : i63 + (-widgetRun6.end.margin);
                    }
                }
            }
            i57++;
        }
    }
}