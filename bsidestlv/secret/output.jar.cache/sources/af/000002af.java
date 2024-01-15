package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

/* loaded from: output.jar:androidx/constraintlayout/core/motion/utils/ArcCurveFit.class */
public class ArcCurveFit extends CurveFit {
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;
    Arc[] mArcs;
    private boolean mExtrapolate = true;
    private final double[] mTime;

    /* loaded from: output.jar:androidx/constraintlayout/core/motion/utils/ArcCurveFit$Arc.class */
    private static class Arc {
        private static final double EPSILON = 0.001d;
        private static final String TAG = "Arc";
        private static double[] ourPercent = new double[91];
        boolean linear;
        double mArcDistance;
        double mArcVelocity;
        double mEllipseA;
        double mEllipseB;
        double mEllipseCenterX;
        double mEllipseCenterY;
        double[] mLut;
        double mOneOverDeltaTime;
        double mTime1;
        double mTime2;
        double mTmpCosAngle;
        double mTmpSinAngle;
        boolean mVertical;
        double mX1;
        double mX2;
        double mY1;
        double mY2;

        Arc(int i, double d, double d2, double d3, double d4, double d5, double d6) {
            boolean z = false;
            this.linear = false;
            this.mVertical = i == 1 ? true : z;
            this.mTime1 = d;
            this.mTime2 = d2;
            this.mOneOverDeltaTime = 1.0d / (d2 - d);
            if (3 == i) {
                this.linear = true;
            }
            double d7 = d5 - d3;
            double d8 = d6 - d4;
            if (!this.linear && Math.abs(d7) >= EPSILON && Math.abs(d8) >= EPSILON) {
                this.mLut = new double[TypedValues.TYPE_TARGET];
                boolean z2 = this.mVertical;
                this.mEllipseA = d7 * (z2 ? -1 : 1);
                this.mEllipseB = d8 * (z2 ? 1 : -1);
                this.mEllipseCenterX = z2 ? d5 : d3;
                this.mEllipseCenterY = z2 ? d4 : d6;
                buildTable(d3, d4, d5, d6);
                this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
                return;
            }
            this.linear = true;
            this.mX1 = d3;
            this.mX2 = d5;
            this.mY1 = d4;
            this.mY2 = d6;
            double hypot = Math.hypot(d8, d7);
            this.mArcDistance = hypot;
            this.mArcVelocity = hypot * this.mOneOverDeltaTime;
            double d9 = this.mTime2;
            double d10 = this.mTime1;
            this.mEllipseCenterX = d7 / (d9 - d10);
            this.mEllipseCenterY = d8 / (d9 - d10);
        }

        private void buildTable(double d, double d2, double d3, double d4) {
            double[] dArr;
            double[] dArr2;
            int i = 0;
            double d5 = 0.0d;
            double d6 = 0.0d;
            double d7 = 0.0d;
            while (true) {
                if (i >= ourPercent.length) {
                    break;
                }
                double radians = Math.toRadians((i * 90.0d) / (dArr.length - 1));
                double sin = Math.sin(radians);
                double cos = Math.cos(radians);
                double d8 = sin * (d3 - d);
                double d9 = cos * (d2 - d4);
                if (i > 0) {
                    d5 = Math.hypot(d8 - d6, d9 - d7) + d5;
                    ourPercent[i] = d5;
                }
                i++;
                d7 = d9;
                d6 = d8;
            }
            this.mArcDistance = d5;
            int i2 = 0;
            while (true) {
                double[] dArr3 = ourPercent;
                if (i2 >= dArr3.length) {
                    break;
                }
                dArr3[i2] = dArr3[i2] / d5;
                i2++;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= this.mLut.length) {
                    return;
                }
                double length = i3 / (dArr2.length - 1);
                int binarySearch = Arrays.binarySearch(ourPercent, length);
                if (binarySearch >= 0) {
                    this.mLut[i3] = binarySearch / (ourPercent.length - 1);
                } else if (binarySearch == -1) {
                    this.mLut[i3] = 0.0d;
                } else {
                    int i4 = -binarySearch;
                    int i5 = i4 - 2;
                    double d10 = i5;
                    double[] dArr4 = ourPercent;
                    double d11 = dArr4[i5];
                    this.mLut[i3] = (d10 + ((length - d11) / (dArr4[i4 - 1] - d11))) / (dArr4.length - 1);
                }
                i3++;
            }
        }

        double getDX() {
            double d = this.mEllipseA * this.mTmpCosAngle;
            double hypot = this.mArcVelocity / Math.hypot(d, (-this.mEllipseB) * this.mTmpSinAngle);
            double d2 = d;
            if (this.mVertical) {
                d2 = -d;
            }
            return d2 * hypot;
        }

        double getDY() {
            double d = this.mEllipseA;
            double d2 = this.mTmpCosAngle;
            double d3 = (-this.mEllipseB) * this.mTmpSinAngle;
            double hypot = this.mArcVelocity / Math.hypot(d * d2, d3);
            return this.mVertical ? (-d3) * hypot : d3 * hypot;
        }

        public double getLinearDX(double d) {
            return this.mEllipseCenterX;
        }

        public double getLinearDY(double d) {
            return this.mEllipseCenterY;
        }

        public double getLinearX(double d) {
            double d2 = this.mTime1;
            double d3 = this.mOneOverDeltaTime;
            double d4 = this.mX1;
            return d4 + ((d - d2) * d3 * (this.mX2 - d4));
        }

        public double getLinearY(double d) {
            double d2 = this.mTime1;
            double d3 = this.mOneOverDeltaTime;
            double d4 = this.mY1;
            return d4 + ((d - d2) * d3 * (this.mY2 - d4));
        }

        double getX() {
            return this.mEllipseCenterX + (this.mEllipseA * this.mTmpSinAngle);
        }

        double getY() {
            return this.mEllipseCenterY + (this.mEllipseB * this.mTmpCosAngle);
        }

        double lookup(double d) {
            if (d <= 0.0d) {
                return 0.0d;
            }
            if (d >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.mLut;
            double length = d * (dArr.length - 1);
            int i = (int) length;
            double d2 = i;
            double d3 = dArr[i];
            return d3 + ((length - d2) * (dArr[i + 1] - d3));
        }

        void setPoint(double d) {
            double lookup = lookup((this.mVertical ? this.mTime2 - d : d - this.mTime1) * this.mOneOverDeltaTime) * 1.5707963267948966d;
            this.mTmpSinAngle = Math.sin(lookup);
            this.mTmpCosAngle = Math.cos(lookup);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0054, code lost:
        if (r22 == 1) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ArcCurveFit(int[] r19, double[] r20, double[][] r21) {
        /*
            Method dump skipped, instructions count: 194
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.ArcCurveFit.<init>(int[], double[], double[][]):void");
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double d, int i) {
        int i2;
        double d2;
        double d3;
        double linearY;
        double linearDY;
        double y;
        double dy;
        if (this.mExtrapolate) {
            if (d < this.mArcs[0].mTime1) {
                double d4 = this.mArcs[0].mTime1;
                d3 = d - this.mArcs[0].mTime1;
                if (!this.mArcs[0].linear) {
                    this.mArcs[0].setPoint(d4);
                    if (i == 0) {
                        y = this.mArcs[0].getX();
                        dy = this.mArcs[0].getDX();
                    } else {
                        y = this.mArcs[0].getY();
                        dy = this.mArcs[0].getDY();
                    }
                    return y + (d3 * dy);
                } else if (i == 0) {
                    linearY = this.mArcs[0].getLinearX(d4);
                    linearDY = this.mArcs[0].getLinearDX(d4);
                } else {
                    linearY = this.mArcs[0].getLinearY(d4);
                    linearDY = this.mArcs[0].getLinearDY(d4);
                }
            } else {
                Arc[] arcArr = this.mArcs;
                i2 = 0;
                d2 = d;
                if (d > arcArr[arcArr.length - 1].mTime2) {
                    Arc[] arcArr2 = this.mArcs;
                    double d5 = arcArr2[arcArr2.length - 1].mTime2;
                    d3 = d - d5;
                    Arc[] arcArr3 = this.mArcs;
                    int length = arcArr3.length - 1;
                    if (i == 0) {
                        linearY = arcArr3[length].getLinearX(d5);
                        linearDY = this.mArcs[length].getLinearDX(d5);
                    } else {
                        linearY = arcArr3[length].getLinearY(d5);
                        linearDY = this.mArcs[length].getLinearDY(d5);
                    }
                }
            }
            return linearY + (d3 * linearDY);
        } else if (d < this.mArcs[0].mTime1) {
            d2 = this.mArcs[0].mTime1;
            i2 = 0;
        } else {
            Arc[] arcArr4 = this.mArcs;
            i2 = 0;
            d2 = d;
            if (d > arcArr4[arcArr4.length - 1].mTime2) {
                Arc[] arcArr5 = this.mArcs;
                d2 = arcArr5[arcArr5.length - 1].mTime2;
                i2 = 0;
            }
        }
        while (true) {
            Arc[] arcArr6 = this.mArcs;
            if (i2 >= arcArr6.length) {
                return Double.NaN;
            }
            if (d2 <= arcArr6[i2].mTime2) {
                if (this.mArcs[i2].linear) {
                    return i == 0 ? this.mArcs[i2].getLinearX(d2) : this.mArcs[i2].getLinearY(d2);
                }
                this.mArcs[i2].setPoint(d2);
                return i == 0 ? this.mArcs[i2].getX() : this.mArcs[i2].getY();
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d, double[] dArr) {
        double d2;
        if (!this.mExtrapolate) {
            double d3 = d;
            if (d < this.mArcs[0].mTime1) {
                d3 = this.mArcs[0].mTime1;
            }
            Arc[] arcArr = this.mArcs;
            d2 = d3;
            if (d3 > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                d2 = arcArr2[arcArr2.length - 1].mTime2;
            }
        } else if (d < this.mArcs[0].mTime1) {
            double d4 = this.mArcs[0].mTime1;
            double d5 = d - this.mArcs[0].mTime1;
            if (this.mArcs[0].linear) {
                dArr[0] = this.mArcs[0].getLinearX(d4) + (this.mArcs[0].getLinearDX(d4) * d5);
                dArr[1] = this.mArcs[0].getLinearY(d4) + (d5 * this.mArcs[0].getLinearDY(d4));
                return;
            }
            this.mArcs[0].setPoint(d4);
            dArr[0] = this.mArcs[0].getX() + (this.mArcs[0].getDX() * d5);
            dArr[1] = this.mArcs[0].getY() + (d5 * this.mArcs[0].getDY());
            return;
        } else {
            Arc[] arcArr3 = this.mArcs;
            d2 = d;
            if (d > arcArr3[arcArr3.length - 1].mTime2) {
                Arc[] arcArr4 = this.mArcs;
                double d6 = arcArr4[arcArr4.length - 1].mTime2;
                double d7 = d - d6;
                Arc[] arcArr5 = this.mArcs;
                int length = arcArr5.length - 1;
                if (arcArr5[length].linear) {
                    dArr[0] = this.mArcs[length].getLinearX(d6) + (this.mArcs[length].getLinearDX(d6) * d7);
                    dArr[1] = this.mArcs[length].getLinearY(d6) + (d7 * this.mArcs[length].getLinearDY(d6));
                    return;
                }
                this.mArcs[length].setPoint(d);
                dArr[0] = this.mArcs[length].getX() + (this.mArcs[length].getDX() * d7);
                dArr[1] = this.mArcs[length].getY() + (d7 * this.mArcs[length].getDY());
                return;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr6 = this.mArcs;
            if (i >= arcArr6.length) {
                return;
            }
            if (d2 <= arcArr6[i].mTime2) {
                if (this.mArcs[i].linear) {
                    dArr[0] = this.mArcs[i].getLinearX(d2);
                    dArr[1] = this.mArcs[i].getLinearY(d2);
                    return;
                }
                this.mArcs[i].setPoint(d2);
                dArr[0] = this.mArcs[i].getX();
                dArr[1] = this.mArcs[i].getY();
                return;
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d, float[] fArr) {
        double d2;
        if (this.mExtrapolate) {
            if (d < this.mArcs[0].mTime1) {
                double d3 = this.mArcs[0].mTime1;
                double d4 = d - this.mArcs[0].mTime1;
                if (this.mArcs[0].linear) {
                    fArr[0] = (float) (this.mArcs[0].getLinearX(d3) + (this.mArcs[0].getLinearDX(d3) * d4));
                    fArr[1] = (float) (this.mArcs[0].getLinearY(d3) + (d4 * this.mArcs[0].getLinearDY(d3)));
                    return;
                }
                this.mArcs[0].setPoint(d3);
                fArr[0] = (float) (this.mArcs[0].getX() + (this.mArcs[0].getDX() * d4));
                fArr[1] = (float) (this.mArcs[0].getY() + (d4 * this.mArcs[0].getDY()));
                return;
            }
            Arc[] arcArr = this.mArcs;
            d2 = d;
            if (d > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                double d5 = arcArr2[arcArr2.length - 1].mTime2;
                double d6 = d - d5;
                Arc[] arcArr3 = this.mArcs;
                int length = arcArr3.length - 1;
                if (arcArr3[length].linear) {
                    fArr[0] = (float) (this.mArcs[length].getLinearX(d5) + (this.mArcs[length].getLinearDX(d5) * d6));
                    fArr[1] = (float) (this.mArcs[length].getLinearY(d5) + (d6 * this.mArcs[length].getLinearDY(d5)));
                    return;
                }
                this.mArcs[length].setPoint(d);
                fArr[0] = (float) this.mArcs[length].getX();
                fArr[1] = (float) this.mArcs[length].getY();
                return;
            }
        } else if (d < this.mArcs[0].mTime1) {
            d2 = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr4 = this.mArcs;
            d2 = d;
            if (d > arcArr4[arcArr4.length - 1].mTime2) {
                Arc[] arcArr5 = this.mArcs;
                d2 = arcArr5[arcArr5.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr6 = this.mArcs;
            if (i >= arcArr6.length) {
                return;
            }
            if (d2 <= arcArr6[i].mTime2) {
                if (this.mArcs[i].linear) {
                    fArr[0] = (float) this.mArcs[i].getLinearX(d2);
                    fArr[1] = (float) this.mArcs[i].getLinearY(d2);
                    return;
                }
                this.mArcs[i].setPoint(d2);
                fArr[0] = (float) this.mArcs[i].getX();
                fArr[1] = (float) this.mArcs[i].getY();
                return;
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getSlope(double d, int i) {
        double d2 = d;
        if (d < this.mArcs[0].mTime1) {
            d2 = this.mArcs[0].mTime1;
        }
        Arc[] arcArr = this.mArcs;
        int i2 = 0;
        double d3 = d2;
        if (d2 > arcArr[arcArr.length - 1].mTime2) {
            Arc[] arcArr2 = this.mArcs;
            d3 = arcArr2[arcArr2.length - 1].mTime2;
            i2 = 0;
        }
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i2 >= arcArr3.length) {
                return Double.NaN;
            }
            if (d3 <= arcArr3[i2].mTime2) {
                if (this.mArcs[i2].linear) {
                    return i == 0 ? this.mArcs[i2].getLinearDX(d3) : this.mArcs[i2].getLinearDY(d3);
                }
                this.mArcs[i2].setPoint(d3);
                return i == 0 ? this.mArcs[i2].getDX() : this.mArcs[i2].getDY();
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getSlope(double d, double[] dArr) {
        double d2;
        if (d < this.mArcs[0].mTime1) {
            d2 = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            d2 = d;
            if (d > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                d2 = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (d2 <= arcArr3[i].mTime2) {
                if (this.mArcs[i].linear) {
                    dArr[0] = this.mArcs[i].getLinearDX(d2);
                    dArr[1] = this.mArcs[i].getLinearDY(d2);
                    return;
                }
                this.mArcs[i].setPoint(d2);
                dArr[0] = this.mArcs[i].getDX();
                dArr[1] = this.mArcs[i].getDY();
                return;
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mTime;
    }
}