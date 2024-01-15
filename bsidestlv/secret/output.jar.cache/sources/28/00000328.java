package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: output.jar:androidx/constraintlayout/core/widgets/Flow.class */
public class Flow extends VirtualLayout {
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_CHAIN_NEW = 3;
    public static final int WRAP_NONE = 0;
    private ConstraintWidget[] mDisplayedWidgets;
    private int mHorizontalStyle = -1;
    private int mVerticalStyle = -1;
    private int mFirstHorizontalStyle = -1;
    private int mFirstVerticalStyle = -1;
    private int mLastHorizontalStyle = -1;
    private int mLastVerticalStyle = -1;
    private float mHorizontalBias = 0.5f;
    private float mVerticalBias = 0.5f;
    private float mFirstHorizontalBias = 0.5f;
    private float mFirstVerticalBias = 0.5f;
    private float mLastHorizontalBias = 0.5f;
    private float mLastVerticalBias = 0.5f;
    private int mHorizontalGap = 0;
    private int mVerticalGap = 0;
    private int mHorizontalAlign = 2;
    private int mVerticalAlign = 2;
    private int mWrapMode = 0;
    private int mMaxElementsWrap = -1;
    private int mOrientation = 0;
    private ArrayList<WidgetsList> mChainList = new ArrayList<>();
    private ConstraintWidget[] mAlignedBiggestElementsInRows = null;
    private ConstraintWidget[] mAlignedBiggestElementsInCols = null;
    private int[] mAlignedDimensions = null;
    private int mDisplayedWidgetsCount = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: output.jar:androidx/constraintlayout/core/widgets/Flow$WidgetsList.class */
    public class WidgetsList {
        private ConstraintAnchor mBottom;
        private ConstraintAnchor mLeft;
        private int mMax;
        private int mOrientation;
        private int mPaddingBottom;
        private int mPaddingLeft;
        private int mPaddingRight;
        private int mPaddingTop;
        private ConstraintAnchor mRight;
        private ConstraintAnchor mTop;
        private ConstraintWidget biggest = null;
        int biggestDimension = 0;
        private int mWidth = 0;
        private int mHeight = 0;
        private int mStartIndex = 0;
        private int mCount = 0;
        private int mNbMatchConstraintsWidgets = 0;

        public WidgetsList(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2) {
            this.mPaddingLeft = 0;
            this.mPaddingTop = 0;
            this.mPaddingRight = 0;
            this.mPaddingBottom = 0;
            this.mMax = 0;
            this.mOrientation = i;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = Flow.this.getPaddingLeft();
            this.mPaddingTop = Flow.this.getPaddingTop();
            this.mPaddingRight = Flow.this.getPaddingRight();
            this.mPaddingBottom = Flow.this.getPaddingBottom();
            this.mMax = i2;
        }

        private void recomputeDimensions() {
            this.mWidth = 0;
            this.mHeight = 0;
            this.biggest = null;
            this.biggestDimension = 0;
            int i = this.mCount;
            for (int i2 = 0; i2 < i && this.mStartIndex + i2 < Flow.this.mDisplayedWidgetsCount; i2++) {
                ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i2];
                if (this.mOrientation == 0) {
                    int width = constraintWidget.getWidth();
                    int i3 = Flow.this.mHorizontalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        i3 = 0;
                    }
                    this.mWidth += width + i3;
                    int widgetHeight = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    if (this.biggest == null || this.biggestDimension < widgetHeight) {
                        this.biggest = constraintWidget;
                        this.biggestDimension = widgetHeight;
                        this.mHeight = widgetHeight;
                    }
                } else {
                    int widgetWidth = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                    int widgetHeight2 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    int i4 = Flow.this.mVerticalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        i4 = 0;
                    }
                    this.mHeight += widgetHeight2 + i4;
                    if (this.biggest == null || this.biggestDimension < widgetWidth) {
                        this.biggest = constraintWidget;
                        this.biggestDimension = widgetWidth;
                        this.mWidth = widgetWidth;
                    }
                }
            }
        }

        public void add(ConstraintWidget constraintWidget) {
            int i = 0;
            if (this.mOrientation == 0) {
                int widgetWidth = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    widgetWidth = 0;
                }
                int i2 = Flow.this.mHorizontalGap;
                if (constraintWidget.getVisibility() == 8) {
                    i2 = 0;
                }
                this.mWidth += widgetWidth + i2;
                int widgetHeight = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (this.biggest == null || this.biggestDimension < widgetHeight) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = widgetHeight;
                    this.mHeight = widgetHeight;
                }
            } else {
                int widgetWidth2 = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                int widgetHeight2 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    widgetHeight2 = 0;
                }
                int i3 = Flow.this.mVerticalGap;
                if (constraintWidget.getVisibility() != 8) {
                    i = i3;
                }
                this.mHeight += widgetHeight2 + i;
                if (this.biggest == null || this.biggestDimension < widgetWidth2) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = widgetWidth2;
                    this.mWidth = widgetWidth2;
                }
            }
            this.mCount++;
        }

        public void clear() {
            this.biggestDimension = 0;
            this.biggest = null;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
            this.mNbMatchConstraintsWidgets = 0;
        }

        public void createConstraints(boolean z, int i, boolean z2) {
            int i2;
            int i3;
            float f;
            ConstraintWidget constraintWidget;
            int i4;
            float f2;
            float f3;
            float f4;
            int i5 = this.mCount;
            for (int i6 = 0; i6 < i5 && this.mStartIndex + i6 < Flow.this.mDisplayedWidgetsCount; i6++) {
                ConstraintWidget constraintWidget2 = Flow.this.mDisplayedWidgets[this.mStartIndex + i6];
                if (constraintWidget2 != null) {
                    constraintWidget2.resetAnchors();
                }
            }
            if (i5 == 0 || this.biggest == null) {
                return;
            }
            boolean z3 = z2 && i == 0;
            int i7 = 0;
            int i8 = -1;
            int i9 = -1;
            while (true) {
                i2 = i9;
                if (i7 >= i5) {
                    break;
                }
                int i10 = z ? (i5 - 1) - i7 : i7;
                if (this.mStartIndex + i10 >= Flow.this.mDisplayedWidgetsCount) {
                    break;
                }
                ConstraintWidget constraintWidget3 = Flow.this.mDisplayedWidgets[this.mStartIndex + i10];
                int i11 = i8;
                int i12 = i2;
                if (constraintWidget3 != null) {
                    i11 = i8;
                    i12 = i2;
                    if (constraintWidget3.getVisibility() == 0) {
                        int i13 = i8;
                        if (i8 == -1) {
                            i13 = i7;
                        }
                        i12 = i7;
                        i11 = i13;
                    }
                }
                i7++;
                i8 = i11;
                i9 = i12;
            }
            ConstraintWidget constraintWidget4 = null;
            if (this.mOrientation != 0) {
                ConstraintWidget constraintWidget5 = this.biggest;
                constraintWidget5.setHorizontalChainStyle(Flow.this.mHorizontalStyle);
                int i14 = this.mPaddingLeft;
                int i15 = i14;
                if (i > 0) {
                    i15 = i14 + Flow.this.mHorizontalGap;
                }
                if (z) {
                    constraintWidget5.mRight.connect(this.mRight, i15);
                    if (z2) {
                        constraintWidget5.mLeft.connect(this.mLeft, this.mPaddingRight);
                    }
                    if (i > 0) {
                        this.mRight.mOwner.mLeft.connect(constraintWidget5.mRight, 0);
                    }
                } else {
                    constraintWidget5.mLeft.connect(this.mLeft, i15);
                    if (z2) {
                        constraintWidget5.mRight.connect(this.mRight, this.mPaddingRight);
                    }
                    if (i > 0) {
                        this.mLeft.mOwner.mRight.connect(constraintWidget5.mLeft, 0);
                    }
                }
                ConstraintWidget constraintWidget6 = null;
                for (int i16 = 0; i16 < i5 && this.mStartIndex + i16 < Flow.this.mDisplayedWidgetsCount; i16++) {
                    ConstraintWidget constraintWidget7 = Flow.this.mDisplayedWidgets[this.mStartIndex + i16];
                    if (constraintWidget7 != null) {
                        if (i16 == 0) {
                            constraintWidget7.connect(constraintWidget7.mTop, this.mTop, this.mPaddingTop);
                            int i17 = Flow.this.mVerticalStyle;
                            float f5 = Flow.this.mVerticalBias;
                            if (this.mStartIndex != 0 || Flow.this.mFirstVerticalStyle == -1) {
                                i3 = i17;
                                f = f5;
                                if (z2) {
                                    i3 = i17;
                                    f = f5;
                                    if (Flow.this.mLastVerticalStyle != -1) {
                                        i3 = Flow.this.mLastVerticalStyle;
                                        f = Flow.this.mLastVerticalBias;
                                    }
                                }
                            } else {
                                i3 = Flow.this.mFirstVerticalStyle;
                                f = Flow.this.mFirstVerticalBias;
                            }
                            constraintWidget7.setVerticalChainStyle(i3);
                            constraintWidget7.setVerticalBiasPercent(f);
                        }
                        if (i16 == i5 - 1) {
                            constraintWidget7.connect(constraintWidget7.mBottom, this.mBottom, this.mPaddingBottom);
                        }
                        if (constraintWidget6 != null) {
                            constraintWidget7.mTop.connect(constraintWidget6.mBottom, Flow.this.mVerticalGap);
                            if (i16 == i8) {
                                constraintWidget7.mTop.setGoneMargin(this.mPaddingTop);
                            }
                            constraintWidget6.mBottom.connect(constraintWidget7.mTop, 0);
                            if (i16 == i2 + 1) {
                                constraintWidget6.mBottom.setGoneMargin(this.mPaddingBottom);
                            }
                        }
                        if (constraintWidget7 != constraintWidget5) {
                            if (z) {
                                int i18 = Flow.this.mHorizontalAlign;
                                if (i18 == 0) {
                                    constraintWidget7.mRight.connect(constraintWidget5.mRight, 0);
                                } else if (i18 == 1) {
                                    constraintWidget7.mLeft.connect(constraintWidget5.mLeft, 0);
                                } else if (i18 == 2) {
                                    constraintWidget7.mLeft.connect(constraintWidget5.mLeft, 0);
                                    constraintWidget7.mRight.connect(constraintWidget5.mRight, 0);
                                }
                            } else {
                                int i19 = Flow.this.mHorizontalAlign;
                                if (i19 == 0) {
                                    constraintWidget7.mLeft.connect(constraintWidget5.mLeft, 0);
                                } else if (i19 == 1) {
                                    constraintWidget7.mRight.connect(constraintWidget5.mRight, 0);
                                } else if (i19 == 2) {
                                    if (z3) {
                                        constraintWidget7.mLeft.connect(this.mLeft, this.mPaddingLeft);
                                        constraintWidget7.mRight.connect(this.mRight, this.mPaddingRight);
                                    } else {
                                        constraintWidget7.mLeft.connect(constraintWidget5.mLeft, 0);
                                        constraintWidget7.mRight.connect(constraintWidget5.mRight, 0);
                                    }
                                }
                            }
                        }
                        constraintWidget6 = constraintWidget7;
                    }
                }
                return;
            }
            ConstraintWidget constraintWidget8 = this.biggest;
            constraintWidget8.setVerticalChainStyle(Flow.this.mVerticalStyle);
            int i20 = this.mPaddingTop;
            int i21 = i20;
            if (i > 0) {
                i21 = i20 + Flow.this.mVerticalGap;
            }
            constraintWidget8.mTop.connect(this.mTop, i21);
            if (z2) {
                constraintWidget8.mBottom.connect(this.mBottom, this.mPaddingBottom);
            }
            if (i > 0) {
                this.mTop.mOwner.mBottom.connect(constraintWidget8.mTop, 0);
            }
            if (Flow.this.mVerticalAlign == 3 && !constraintWidget8.hasBaseline()) {
                for (int i22 = 0; i22 < i5; i22++) {
                    int i23 = z ? (i5 - 1) - i22 : i22;
                    if (this.mStartIndex + i23 >= Flow.this.mDisplayedWidgetsCount) {
                        break;
                    }
                    constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i23];
                    if (constraintWidget.hasBaseline()) {
                        break;
                    }
                }
            }
            constraintWidget = constraintWidget8;
            for (int i24 = 0; i24 < i5; i24++) {
                int i25 = z ? (i5 - 1) - i24 : i24;
                if (this.mStartIndex + i25 >= Flow.this.mDisplayedWidgetsCount) {
                    return;
                }
                ConstraintWidget constraintWidget9 = Flow.this.mDisplayedWidgets[this.mStartIndex + i25];
                if (constraintWidget9 != null) {
                    if (i24 == 0) {
                        constraintWidget9.connect(constraintWidget9.mLeft, this.mLeft, this.mPaddingLeft);
                    }
                    if (i25 == 0) {
                        int i26 = Flow.this.mHorizontalStyle;
                        float f6 = Flow.this.mHorizontalBias;
                        float f7 = f6;
                        if (z) {
                            f7 = 1.0f - f6;
                        }
                        if (this.mStartIndex != 0 || Flow.this.mFirstHorizontalStyle == -1) {
                            i4 = i26;
                            f2 = f7;
                            if (z2) {
                                i4 = i26;
                                f2 = f7;
                                if (Flow.this.mLastHorizontalStyle != -1) {
                                    i4 = Flow.this.mLastHorizontalStyle;
                                    if (z) {
                                        f4 = Flow.this.mLastHorizontalBias;
                                        f3 = 1.0f - f4;
                                        f2 = f3;
                                    } else {
                                        f3 = Flow.this.mLastHorizontalBias;
                                        f2 = f3;
                                    }
                                }
                            }
                        } else {
                            i4 = Flow.this.mFirstHorizontalStyle;
                            if (z) {
                                f4 = Flow.this.mFirstHorizontalBias;
                                f3 = 1.0f - f4;
                                f2 = f3;
                            } else {
                                f3 = Flow.this.mFirstHorizontalBias;
                                f2 = f3;
                            }
                        }
                        constraintWidget9.setHorizontalChainStyle(i4);
                        constraintWidget9.setHorizontalBiasPercent(f2);
                    }
                    if (i24 == i5 - 1) {
                        constraintWidget9.connect(constraintWidget9.mRight, this.mRight, this.mPaddingRight);
                    }
                    if (constraintWidget4 != null) {
                        constraintWidget9.mLeft.connect(constraintWidget4.mRight, Flow.this.mHorizontalGap);
                        if (i24 == i8) {
                            constraintWidget9.mLeft.setGoneMargin(this.mPaddingLeft);
                        }
                        constraintWidget4.mRight.connect(constraintWidget9.mLeft, 0);
                        if (i24 == i2 + 1) {
                            constraintWidget4.mRight.setGoneMargin(this.mPaddingRight);
                        }
                    }
                    if (constraintWidget9 == constraintWidget8) {
                        constraintWidget4 = constraintWidget9;
                    } else if (Flow.this.mVerticalAlign == 3 && constraintWidget.hasBaseline() && constraintWidget9 != constraintWidget && constraintWidget9.hasBaseline()) {
                        constraintWidget9.mBaseline.connect(constraintWidget.mBaseline, 0);
                        constraintWidget4 = constraintWidget9;
                    } else {
                        int i27 = Flow.this.mVerticalAlign;
                        if (i27 == 0) {
                            constraintWidget9.mTop.connect(constraintWidget8.mTop, 0);
                            constraintWidget4 = constraintWidget9;
                        } else if (i27 == 1) {
                            constraintWidget9.mBottom.connect(constraintWidget8.mBottom, 0);
                            constraintWidget4 = constraintWidget9;
                        } else if (z3) {
                            constraintWidget9.mTop.connect(this.mTop, this.mPaddingTop);
                            constraintWidget9.mBottom.connect(this.mBottom, this.mPaddingBottom);
                            constraintWidget4 = constraintWidget9;
                        } else {
                            constraintWidget9.mTop.connect(constraintWidget8.mTop, 0);
                            constraintWidget9.mBottom.connect(constraintWidget8.mBottom, 0);
                            constraintWidget4 = constraintWidget9;
                        }
                    }
                }
            }
        }

        public int getHeight() {
            return this.mOrientation == 1 ? this.mHeight - Flow.this.mVerticalGap : this.mHeight;
        }

        public int getWidth() {
            return this.mOrientation == 0 ? this.mWidth - Flow.this.mHorizontalGap : this.mWidth;
        }

        public void measureMatchConstraints(int i) {
            int i2 = this.mNbMatchConstraintsWidgets;
            if (i2 == 0) {
                return;
            }
            int i3 = this.mCount;
            int i4 = i / i2;
            for (int i5 = 0; i5 < i3 && this.mStartIndex + i5 < Flow.this.mDisplayedWidgetsCount; i5++) {
                ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i5];
                if (this.mOrientation == 0) {
                    if (constraintWidget != null && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                        Flow.this.measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i4, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                    }
                } else if (constraintWidget != null && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                    Flow.this.measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i4);
                }
            }
            recomputeDimensions();
        }

        public void setStartIndex(int i) {
            this.mStartIndex = i;
        }

        public void setup(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2, int i3, int i4, int i5, int i6) {
            this.mOrientation = i;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = i2;
            this.mPaddingTop = i3;
            this.mPaddingRight = i4;
            this.mPaddingBottom = i5;
            this.mMax = i6;
        }
    }

    private void createAlignedConstraints(boolean z) {
        ConstraintWidget constraintWidget;
        int i;
        if (this.mAlignedDimensions == null || this.mAlignedBiggestElementsInCols == null || this.mAlignedBiggestElementsInRows == null) {
            return;
        }
        for (int i2 = 0; i2 < this.mDisplayedWidgetsCount; i2++) {
            this.mDisplayedWidgets[i2].resetAnchors();
        }
        int[] iArr = this.mAlignedDimensions;
        int i3 = iArr[0];
        int i4 = iArr[1];
        float f = this.mHorizontalBias;
        ConstraintWidget constraintWidget2 = null;
        int i5 = 0;
        while (i5 < i3) {
            if (z) {
                i = (i3 - i5) - 1;
                f = 1.0f - this.mHorizontalBias;
            } else {
                i = i5;
            }
            ConstraintWidget constraintWidget3 = this.mAlignedBiggestElementsInCols[i];
            ConstraintWidget constraintWidget4 = constraintWidget2;
            if (constraintWidget3 != null) {
                if (constraintWidget3.getVisibility() == 8) {
                    constraintWidget4 = constraintWidget2;
                } else {
                    if (i5 == 0) {
                        constraintWidget3.connect(constraintWidget3.mLeft, this.mLeft, getPaddingLeft());
                        constraintWidget3.setHorizontalChainStyle(this.mHorizontalStyle);
                        constraintWidget3.setHorizontalBiasPercent(f);
                    }
                    if (i5 == i3 - 1) {
                        constraintWidget3.connect(constraintWidget3.mRight, this.mRight, getPaddingRight());
                    }
                    if (i5 > 0 && constraintWidget2 != null) {
                        constraintWidget3.connect(constraintWidget3.mLeft, constraintWidget2.mRight, this.mHorizontalGap);
                        constraintWidget2.connect(constraintWidget2.mRight, constraintWidget3.mLeft, 0);
                    }
                    constraintWidget4 = constraintWidget3;
                }
            }
            i5++;
            constraintWidget2 = constraintWidget4;
        }
        int i6 = 0;
        while (true) {
            ConstraintWidget constraintWidget5 = constraintWidget2;
            if (i6 >= i4) {
                break;
            }
            ConstraintWidget constraintWidget6 = this.mAlignedBiggestElementsInRows[i6];
            constraintWidget2 = constraintWidget5;
            if (constraintWidget6 != null) {
                if (constraintWidget6.getVisibility() == 8) {
                    constraintWidget2 = constraintWidget5;
                } else {
                    if (i6 == 0) {
                        constraintWidget6.connect(constraintWidget6.mTop, this.mTop, getPaddingTop());
                        constraintWidget6.setVerticalChainStyle(this.mVerticalStyle);
                        constraintWidget6.setVerticalBiasPercent(this.mVerticalBias);
                    }
                    if (i6 == i4 - 1) {
                        constraintWidget6.connect(constraintWidget6.mBottom, this.mBottom, getPaddingBottom());
                    }
                    if (i6 > 0 && constraintWidget5 != null) {
                        constraintWidget6.connect(constraintWidget6.mTop, constraintWidget5.mBottom, this.mVerticalGap);
                        constraintWidget5.connect(constraintWidget5.mBottom, constraintWidget6.mTop, 0);
                    }
                    constraintWidget2 = constraintWidget6;
                }
            }
            i6++;
        }
        for (int i7 = 0; i7 < i3; i7++) {
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = (i8 * i3) + i7;
                if (this.mOrientation == 1) {
                    i9 = (i7 * i4) + i8;
                }
                ConstraintWidget[] constraintWidgetArr = this.mDisplayedWidgets;
                if (i9 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i9]) != null && constraintWidget.getVisibility() != 8) {
                    ConstraintWidget constraintWidget7 = this.mAlignedBiggestElementsInCols[i7];
                    ConstraintWidget constraintWidget8 = this.mAlignedBiggestElementsInRows[i8];
                    if (constraintWidget != constraintWidget7) {
                        constraintWidget.connect(constraintWidget.mLeft, constraintWidget7.mLeft, 0);
                        constraintWidget.connect(constraintWidget.mRight, constraintWidget7.mRight, 0);
                    }
                    if (constraintWidget != constraintWidget8) {
                        constraintWidget.connect(constraintWidget.mTop, constraintWidget8.mTop, 0);
                        constraintWidget.connect(constraintWidget.mBottom, constraintWidget8.mBottom, 0);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getWidgetHeight(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (constraintWidget.mMatchConstraintDefaultHeight == 0) {
                return 0;
            }
            if (constraintWidget.mMatchConstraintDefaultHeight == 2) {
                int i2 = (int) (constraintWidget.mMatchConstraintPercentHeight * i);
                if (i2 != constraintWidget.getHeight()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i2);
                }
                return i2;
            } else if (constraintWidget.mMatchConstraintDefaultHeight == 1) {
                return constraintWidget.getHeight();
            } else {
                if (constraintWidget.mMatchConstraintDefaultHeight == 3) {
                    return (int) ((constraintWidget.getWidth() * constraintWidget.mDimensionRatio) + 0.5f);
                }
            }
        }
        return constraintWidget.getHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getWidgetWidth(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (constraintWidget.mMatchConstraintDefaultWidth == 0) {
                return 0;
            }
            if (constraintWidget.mMatchConstraintDefaultWidth == 2) {
                int i2 = (int) (constraintWidget.mMatchConstraintPercentWidth * i);
                if (i2 != constraintWidget.getWidth()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i2, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                }
                return i2;
            } else if (constraintWidget.mMatchConstraintDefaultWidth == 1) {
                return constraintWidget.getWidth();
            } else {
                if (constraintWidget.mMatchConstraintDefaultWidth == 3) {
                    return (int) ((constraintWidget.getHeight() * constraintWidget.mDimensionRatio) + 0.5f);
                }
            }
        }
        return constraintWidget.getWidth();
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x011a, code lost:
        if (r8 != 1) goto L133;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0144  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:122:0x030a -> B:50:0x012e). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:124:0x0318 -> B:50:0x012e). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:127:0x032d -> B:50:0x012e). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:129:0x033b -> B:50:0x012e). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void measureAligned(androidx.constraintlayout.core.widgets.ConstraintWidget[] r6, int r7, int r8, int r9, int[] r10) {
        /*
            Method dump skipped, instructions count: 852
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.Flow.measureAligned(androidx.constraintlayout.core.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    private void measureChainWrap(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        int i4;
        WidgetsList widgetsList;
        int paddingRight;
        int paddingBottom;
        WidgetsList widgetsList2;
        if (i == 0) {
            return;
        }
        this.mChainList.clear();
        WidgetsList widgetsList3 = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
        this.mChainList.add(widgetsList3);
        if (i2 != 0) {
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (true) {
                int i8 = i5;
                i4 = i8;
                if (i6 >= i) {
                    break;
                }
                ConstraintWidget constraintWidget = constraintWidgetArr[i6];
                int widgetHeight = getWidgetHeight(constraintWidget, i3);
                i5 = i8;
                if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i5 = i8 + 1;
                }
                boolean z = (i7 == i3 || (this.mVerticalGap + i7) + widgetHeight > i3) && widgetsList3.biggest != null;
                boolean z2 = z;
                if (!z) {
                    z2 = z;
                    if (i6 > 0) {
                        int i9 = this.mMaxElementsWrap;
                        z2 = z;
                        if (i9 > 0) {
                            z2 = z;
                            if (i6 % i9 == 0) {
                                z2 = true;
                            }
                        }
                    }
                }
                if (z2) {
                    widgetsList = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                    widgetsList.setStartIndex(i6);
                    this.mChainList.add(widgetsList);
                } else {
                    widgetsList = widgetsList3;
                    if (i6 > 0) {
                        i7 += this.mVerticalGap + widgetHeight;
                        widgetsList3.add(constraintWidget);
                        i6++;
                    }
                }
                i7 = widgetHeight;
                widgetsList3 = widgetsList;
                widgetsList3.add(constraintWidget);
                i6++;
            }
        } else {
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            while (true) {
                int i13 = i10;
                i4 = i13;
                if (i11 >= i) {
                    break;
                }
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i11];
                int widgetWidth = getWidgetWidth(constraintWidget2, i3);
                i10 = i13;
                if (constraintWidget2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i10 = i13 + 1;
                }
                boolean z3 = (i12 == i3 || (this.mHorizontalGap + i12) + widgetWidth > i3) && widgetsList3.biggest != null;
                boolean z4 = z3;
                if (!z3) {
                    z4 = z3;
                    if (i11 > 0) {
                        int i14 = this.mMaxElementsWrap;
                        z4 = z3;
                        if (i14 > 0) {
                            z4 = z3;
                            if (i11 % i14 == 0) {
                                z4 = true;
                            }
                        }
                    }
                }
                if (z4) {
                    widgetsList2 = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                    widgetsList2.setStartIndex(i11);
                    this.mChainList.add(widgetsList2);
                } else {
                    widgetsList2 = widgetsList3;
                    if (i11 > 0) {
                        i12 += this.mHorizontalGap + widgetWidth;
                        widgetsList3.add(constraintWidget2);
                        i11++;
                    }
                }
                i12 = widgetWidth;
                widgetsList3 = widgetsList2;
                widgetsList3.add(constraintWidget2);
                i11++;
            }
        }
        int size = this.mChainList.size();
        ConstraintAnchor constraintAnchor = this.mLeft;
        ConstraintAnchor constraintAnchor2 = this.mTop;
        ConstraintAnchor constraintAnchor3 = this.mRight;
        ConstraintAnchor constraintAnchor4 = this.mBottom;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight2 = getPaddingRight();
        int paddingBottom2 = getPaddingBottom();
        boolean z5 = getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i4 > 0 && z5) {
            for (int i15 = 0; i15 < size; i15++) {
                WidgetsList widgetsList4 = this.mChainList.get(i15);
                if (i2 == 0) {
                    widgetsList4.measureMatchConstraints(i3 - widgetsList4.getWidth());
                } else {
                    widgetsList4.measureMatchConstraints(i3 - widgetsList4.getHeight());
                }
            }
        }
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        while (true) {
            int i19 = i17;
            if (i18 >= size) {
                iArr[0] = i19;
                iArr[1] = i16;
                return;
            }
            WidgetsList widgetsList5 = this.mChainList.get(i18);
            if (i2 == 0) {
                if (i18 < size - 1) {
                    constraintAnchor4 = this.mChainList.get(i18 + 1).biggest.mTop;
                    paddingBottom = 0;
                } else {
                    constraintAnchor4 = this.mBottom;
                    paddingBottom = getPaddingBottom();
                }
                ConstraintAnchor constraintAnchor5 = widgetsList5.biggest.mBottom;
                widgetsList5.setup(i2, constraintAnchor, constraintAnchor2, constraintAnchor3, constraintAnchor4, paddingLeft, paddingTop, paddingRight2, paddingBottom, i3);
                int max = Math.max(i19, widgetsList5.getWidth());
                int height = i16 + widgetsList5.getHeight();
                i16 = height;
                if (i18 > 0) {
                    i16 = height + this.mVerticalGap;
                }
                constraintAnchor2 = constraintAnchor5;
                paddingBottom2 = paddingBottom;
                i17 = max;
                paddingTop = 0;
            } else {
                int i20 = i18;
                if (i20 < size - 1) {
                    constraintAnchor3 = this.mChainList.get(i20 + 1).biggest.mLeft;
                    paddingRight = 0;
                } else {
                    constraintAnchor3 = this.mRight;
                    paddingRight = getPaddingRight();
                }
                ConstraintAnchor constraintAnchor6 = widgetsList5.biggest.mRight;
                widgetsList5.setup(i2, constraintAnchor, constraintAnchor2, constraintAnchor3, constraintAnchor4, paddingLeft, paddingTop, paddingRight, paddingBottom2, i3);
                int width = i19 + widgetsList5.getWidth();
                int max2 = Math.max(i16, widgetsList5.getHeight());
                int i21 = width;
                if (i20 > 0) {
                    i21 = width + this.mHorizontalGap;
                }
                paddingRight2 = paddingRight;
                constraintAnchor = constraintAnchor6;
                i17 = i21;
                i16 = max2;
                paddingLeft = 0;
            }
            i18++;
        }
    }

    private void measureChainWrap_new(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        int paddingRight;
        int paddingBottom;
        int i6;
        if (i == 0) {
            return;
        }
        this.mChainList.clear();
        WidgetsList widgetsList = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
        this.mChainList.add(widgetsList);
        if (i2 != 0) {
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            while (true) {
                WidgetsList widgetsList2 = widgetsList;
                i4 = i8;
                if (i9 >= i) {
                    break;
                }
                ConstraintWidget constraintWidget = constraintWidgetArr[i9];
                int widgetHeight = getWidgetHeight(constraintWidget, i3);
                int i10 = i8;
                if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i10 = i8 + 1;
                }
                boolean z = (i7 == i3 || (this.mVerticalGap + i7) + widgetHeight > i3) && widgetsList2.biggest != null;
                boolean z2 = z;
                if (!z) {
                    z2 = z;
                    if (i9 > 0) {
                        int i11 = this.mMaxElementsWrap;
                        z2 = z;
                        if (i11 > 0) {
                            z2 = z;
                            if (i11 < 0) {
                                z2 = true;
                            }
                        }
                    }
                }
                if (z2) {
                    widgetsList = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                    widgetsList.setStartIndex(i9);
                    this.mChainList.add(widgetsList);
                } else {
                    widgetsList = widgetsList2;
                    if (i9 > 0) {
                        i5 = i7 + this.mVerticalGap + widgetHeight;
                        widgetsList = widgetsList2;
                        widgetsList.add(constraintWidget);
                        i9++;
                        i7 = i5;
                        i8 = i10;
                    }
                }
                i5 = widgetHeight;
                widgetsList.add(constraintWidget);
                i9++;
                i7 = i5;
                i8 = i10;
            }
        } else {
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            while (true) {
                i4 = i13;
                if (i14 >= i) {
                    break;
                }
                int i16 = i12 + 1;
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i14];
                int widgetWidth = getWidgetWidth(constraintWidget2, i3);
                int i17 = i13;
                if (constraintWidget2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i17 = i13 + 1;
                }
                boolean z3 = (i15 == i3 || (this.mHorizontalGap + i15) + widgetWidth > i3) && widgetsList.biggest != null;
                boolean z4 = z3;
                if (!z3) {
                    z4 = z3;
                    if (i14 > 0) {
                        int i18 = this.mMaxElementsWrap;
                        z4 = z3;
                        if (i18 > 0) {
                            z4 = z3;
                            if (i16 > i18) {
                                z4 = true;
                            }
                        }
                    }
                }
                if (z4) {
                    widgetsList = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                    widgetsList.setStartIndex(i14);
                    this.mChainList.add(widgetsList);
                    i6 = i16;
                    i15 = widgetWidth;
                } else {
                    i15 = i14 > 0 ? i15 + this.mHorizontalGap + widgetWidth : widgetWidth;
                    i6 = 0;
                }
                widgetsList.add(constraintWidget2);
                i14++;
                i12 = i6;
                i13 = i17;
            }
        }
        int size = this.mChainList.size();
        ConstraintAnchor constraintAnchor = this.mLeft;
        ConstraintAnchor constraintAnchor2 = this.mTop;
        ConstraintAnchor constraintAnchor3 = this.mRight;
        ConstraintAnchor constraintAnchor4 = this.mBottom;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight2 = getPaddingRight();
        int paddingBottom2 = getPaddingBottom();
        boolean z5 = getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i4 > 0 && z5) {
            for (int i19 = 0; i19 < size; i19++) {
                WidgetsList widgetsList3 = this.mChainList.get(i19);
                if (i2 == 0) {
                    widgetsList3.measureMatchConstraints(i3 - widgetsList3.getWidth());
                } else {
                    widgetsList3.measureMatchConstraints(i3 - widgetsList3.getHeight());
                }
            }
        }
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        while (true) {
            int i23 = i21;
            if (i22 >= size) {
                iArr[0] = i23;
                iArr[1] = i20;
                return;
            }
            WidgetsList widgetsList4 = this.mChainList.get(i22);
            if (i2 == 0) {
                if (i22 < size - 1) {
                    constraintAnchor4 = this.mChainList.get(i22 + 1).biggest.mTop;
                    paddingBottom = 0;
                } else {
                    constraintAnchor4 = this.mBottom;
                    paddingBottom = getPaddingBottom();
                }
                ConstraintAnchor constraintAnchor5 = widgetsList4.biggest.mBottom;
                widgetsList4.setup(i2, constraintAnchor, constraintAnchor2, constraintAnchor3, constraintAnchor4, paddingLeft, paddingTop, paddingRight2, paddingBottom, i3);
                int max = Math.max(i23, widgetsList4.getWidth());
                int height = i20 + widgetsList4.getHeight();
                i20 = height;
                if (i22 > 0) {
                    i20 = height + this.mVerticalGap;
                }
                constraintAnchor2 = constraintAnchor5;
                paddingBottom2 = paddingBottom;
                i21 = max;
                paddingTop = 0;
            } else {
                int i24 = i22;
                if (i24 < size - 1) {
                    constraintAnchor3 = this.mChainList.get(i24 + 1).biggest.mLeft;
                    paddingRight = 0;
                } else {
                    constraintAnchor3 = this.mRight;
                    paddingRight = getPaddingRight();
                }
                ConstraintAnchor constraintAnchor6 = widgetsList4.biggest.mRight;
                widgetsList4.setup(i2, constraintAnchor, constraintAnchor2, constraintAnchor3, constraintAnchor4, paddingLeft, paddingTop, paddingRight, paddingBottom2, i3);
                int width = i23 + widgetsList4.getWidth();
                int max2 = Math.max(i20, widgetsList4.getHeight());
                int i25 = width;
                if (i24 > 0) {
                    i25 = width + this.mHorizontalGap;
                }
                paddingRight2 = paddingRight;
                constraintAnchor = constraintAnchor6;
                i21 = i25;
                i20 = max2;
                paddingLeft = 0;
            }
            i22++;
        }
    }

    private void measureNoWrap(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        WidgetsList widgetsList;
        if (i == 0) {
            return;
        }
        if (this.mChainList.size() == 0) {
            widgetsList = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
            this.mChainList.add(widgetsList);
        } else {
            widgetsList = this.mChainList.get(0);
            widgetsList.clear();
            widgetsList.setup(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom(), i3);
        }
        for (int i4 = 0; i4 < i; i4++) {
            widgetsList.add(constraintWidgetArr[i4]);
        }
        iArr[0] = widgetsList.getWidth();
        iArr[1] = widgetsList.getHeight();
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z) {
        super.addToSolver(linearSystem, z);
        boolean z2 = getParent() != null && ((ConstraintWidgetContainer) getParent()).isRtl();
        int i = this.mWrapMode;
        if (i != 0) {
            if (i == 1) {
                int size = this.mChainList.size();
                int i2 = 0;
                while (i2 < size) {
                    this.mChainList.get(i2).createConstraints(z2, i2, i2 == size - 1);
                    i2++;
                }
            } else if (i == 2) {
                createAlignedConstraints(z2);
            } else if (i == 3) {
                int size2 = this.mChainList.size();
                int i3 = 0;
                while (i3 < size2) {
                    this.mChainList.get(i3).createConstraints(z2, i3, i3 == size2 - 1);
                    i3++;
                }
            }
        } else if (this.mChainList.size() > 0) {
            this.mChainList.get(0).createConstraints(z2, 0, true);
        }
        needsCallbackFromSolver(false);
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        Flow flow = (Flow) constraintWidget;
        this.mHorizontalStyle = flow.mHorizontalStyle;
        this.mVerticalStyle = flow.mVerticalStyle;
        this.mFirstHorizontalStyle = flow.mFirstHorizontalStyle;
        this.mFirstVerticalStyle = flow.mFirstVerticalStyle;
        this.mLastHorizontalStyle = flow.mLastHorizontalStyle;
        this.mLastVerticalStyle = flow.mLastVerticalStyle;
        this.mHorizontalBias = flow.mHorizontalBias;
        this.mVerticalBias = flow.mVerticalBias;
        this.mFirstHorizontalBias = flow.mFirstHorizontalBias;
        this.mFirstVerticalBias = flow.mFirstVerticalBias;
        this.mLastHorizontalBias = flow.mLastHorizontalBias;
        this.mLastVerticalBias = flow.mLastVerticalBias;
        this.mHorizontalGap = flow.mHorizontalGap;
        this.mVerticalGap = flow.mVerticalGap;
        this.mHorizontalAlign = flow.mHorizontalAlign;
        this.mVerticalAlign = flow.mVerticalAlign;
        this.mWrapMode = flow.mWrapMode;
        this.mMaxElementsWrap = flow.mMaxElementsWrap;
        this.mOrientation = flow.mOrientation;
    }

    public float getMaxElementsWrap() {
        return this.mMaxElementsWrap;
    }

    @Override // androidx.constraintlayout.core.widgets.VirtualLayout
    public void measure(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (this.mWidgetsCount > 0 && !measureChildren()) {
            setMeasure(0, 0);
            needsCallbackFromSolver(false);
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int[] iArr = new int[2];
        int i7 = (i2 - paddingLeft) - paddingRight;
        int i8 = this.mOrientation;
        if (i8 == 1) {
            i7 = (i4 - paddingTop) - paddingBottom;
        }
        if (i8 == 0) {
            if (this.mHorizontalStyle == -1) {
                this.mHorizontalStyle = 0;
            }
            if (this.mVerticalStyle == -1) {
                this.mVerticalStyle = 0;
            }
        } else {
            if (this.mHorizontalStyle == -1) {
                this.mHorizontalStyle = 0;
            }
            if (this.mVerticalStyle == -1) {
                this.mVerticalStyle = 0;
            }
        }
        ConstraintWidget[] constraintWidgetArr = this.mWidgets;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            i5 = i10;
            if (i9 >= this.mWidgetsCount) {
                break;
            }
            int i11 = i5;
            if (this.mWidgets[i9].getVisibility() == 8) {
                i11 = i5 + 1;
            }
            i9++;
            i10 = i11;
        }
        int i12 = this.mWidgetsCount;
        if (i5 > 0) {
            constraintWidgetArr = new ConstraintWidget[this.mWidgetsCount - i5];
            int i13 = 0;
            int i14 = 0;
            while (true) {
                i6 = i14;
                if (i13 >= this.mWidgetsCount) {
                    break;
                }
                ConstraintWidget constraintWidget = this.mWidgets[i13];
                int i15 = i6;
                if (constraintWidget.getVisibility() != 8) {
                    constraintWidgetArr[i6] = constraintWidget;
                    i15 = i6 + 1;
                }
                i13++;
                i14 = i15;
            }
            i12 = i6;
        }
        this.mDisplayedWidgets = constraintWidgetArr;
        this.mDisplayedWidgetsCount = i12;
        int i16 = this.mWrapMode;
        if (i16 == 0) {
            measureNoWrap(constraintWidgetArr, i12, this.mOrientation, i7, iArr);
        } else if (i16 == 1) {
            measureChainWrap(constraintWidgetArr, i12, this.mOrientation, i7, iArr);
        } else if (i16 == 2) {
            measureAligned(constraintWidgetArr, i12, this.mOrientation, i7, iArr);
        } else if (i16 == 3) {
            measureChainWrap_new(constraintWidgetArr, i12, this.mOrientation, i7, iArr);
        }
        boolean z = true;
        int i17 = iArr[0] + paddingLeft + paddingRight;
        int i18 = iArr[1] + paddingTop + paddingBottom;
        int min = i == 1073741824 ? i2 : i == Integer.MIN_VALUE ? Math.min(i17, i2) : i == 0 ? i17 : 0;
        int min2 = i3 == 1073741824 ? i4 : i3 == Integer.MIN_VALUE ? Math.min(i18, i4) : i3 == 0 ? i18 : 0;
        setMeasure(min, min2);
        setWidth(min);
        setHeight(min2);
        if (this.mWidgetsCount <= 0) {
            z = false;
        }
        needsCallbackFromSolver(z);
    }

    public void setFirstHorizontalBias(float f) {
        this.mFirstHorizontalBias = f;
    }

    public void setFirstHorizontalStyle(int i) {
        this.mFirstHorizontalStyle = i;
    }

    public void setFirstVerticalBias(float f) {
        this.mFirstVerticalBias = f;
    }

    public void setFirstVerticalStyle(int i) {
        this.mFirstVerticalStyle = i;
    }

    public void setHorizontalAlign(int i) {
        this.mHorizontalAlign = i;
    }

    public void setHorizontalBias(float f) {
        this.mHorizontalBias = f;
    }

    public void setHorizontalGap(int i) {
        this.mHorizontalGap = i;
    }

    public void setHorizontalStyle(int i) {
        this.mHorizontalStyle = i;
    }

    public void setLastHorizontalBias(float f) {
        this.mLastHorizontalBias = f;
    }

    public void setLastHorizontalStyle(int i) {
        this.mLastHorizontalStyle = i;
    }

    public void setLastVerticalBias(float f) {
        this.mLastVerticalBias = f;
    }

    public void setLastVerticalStyle(int i) {
        this.mLastVerticalStyle = i;
    }

    public void setMaxElementsWrap(int i) {
        this.mMaxElementsWrap = i;
    }

    public void setOrientation(int i) {
        this.mOrientation = i;
    }

    public void setVerticalAlign(int i) {
        this.mVerticalAlign = i;
    }

    public void setVerticalBias(float f) {
        this.mVerticalBias = f;
    }

    public void setVerticalGap(int i) {
        this.mVerticalGap = i;
    }

    public void setVerticalStyle(int i) {
        this.mVerticalStyle = i;
    }

    public void setWrapMode(int i) {
        this.mWrapMode = i;
    }
}