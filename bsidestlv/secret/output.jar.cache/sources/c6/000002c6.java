package androidx.constraintlayout.core.motion.utils;

/* loaded from: output.jar:androidx/constraintlayout/core/motion/utils/LinearCurveFit.class */
public class LinearCurveFit extends CurveFit {
    private static final String TAG = "LinearCurveFit";
    private boolean mExtrapolate = true;
    double[] mSlopeTemp;
    private double[] mT;
    private double mTotalLength;
    private double[][] mY;

    public LinearCurveFit(double[] dArr, double[][] dArr2) {
        this.mTotalLength = Double.NaN;
        int length = dArr.length;
        int length2 = dArr2[0].length;
        this.mSlopeTemp = new double[length2];
        this.mT = dArr;
        this.mY = dArr2;
        if (length2 <= 2) {
            return;
        }
        int i = 0;
        double d = 0.0d;
        while (true) {
            double d2 = d;
            if (i >= dArr.length) {
                this.mTotalLength = 0.0d;
                return;
            }
            double d3 = dArr2[i][0];
            if (i > 0) {
                Math.hypot(d3 - d2, d3 - d2);
            }
            i++;
            d = d3;
        }
    }

    private double getLength2D(double d) {
        if (Double.isNaN(this.mTotalLength)) {
            return 0.0d;
        }
        double[] dArr = this.mT;
        int length = dArr.length;
        if (d <= dArr[0]) {
            return 0.0d;
        }
        int i = length - 1;
        if (d >= dArr[i]) {
            return this.mTotalLength;
        }
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i2 = 0;
        double d4 = 0.0d;
        while (i2 < i) {
            double[] dArr2 = this.mY[i2];
            double d5 = dArr2[0];
            double d6 = dArr2[1];
            double d7 = d2;
            if (i2 > 0) {
                d7 = d2 + Math.hypot(d5 - d4, d6 - d3);
            }
            double[] dArr3 = this.mT;
            double d8 = dArr3[i2];
            if (d == d8) {
                return d7;
            }
            int i3 = i2 + 1;
            double d9 = dArr3[i3];
            if (d < d9) {
                double d10 = (d - d8) / (d9 - d8);
                double[][] dArr4 = this.mY;
                double[] dArr5 = dArr4[i2];
                double d11 = dArr5[0];
                double[] dArr6 = dArr4[i3];
                double d12 = dArr6[0];
                double d13 = dArr5[1];
                double d14 = dArr6[1];
                double d15 = 1.0d - d10;
                return d7 + Math.hypot(d6 - ((d13 * d15) + (d14 * d10)), d5 - ((d11 * d15) + (d12 * d10)));
            }
            i2 = i3;
            d4 = d5;
            d3 = d6;
            d2 = d7;
        }
        return 0.0d;
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double d, int i) {
        double d2;
        double d3;
        double slope;
        double[] dArr = this.mT;
        int length = dArr.length;
        int i2 = 0;
        if (this.mExtrapolate) {
            double d4 = dArr[0];
            if (d <= d4) {
                d2 = this.mY[0][i];
                d3 = d - d4;
                slope = getSlope(d4, i);
            } else {
                int i3 = length - 1;
                double d5 = dArr[i3];
                if (d >= d5) {
                    d2 = this.mY[i3][i];
                    d3 = d - d5;
                    slope = getSlope(d5, i);
                }
            }
            return d2 + (d3 * slope);
        } else if (d <= dArr[0]) {
            return this.mY[0][i];
        } else {
            int i4 = length - 1;
            if (d >= dArr[i4]) {
                return this.mY[i4][i];
            }
        }
        while (i2 < length - 1) {
            double[] dArr2 = this.mT;
            double d6 = dArr2[i2];
            if (d == d6) {
                return this.mY[i2][i];
            }
            int i5 = i2 + 1;
            double d7 = dArr2[i5];
            if (d < d7) {
                double d8 = (d - d6) / (d7 - d6);
                double[][] dArr3 = this.mY;
                return (dArr3[i2][i] * (1.0d - d8)) + (dArr3[i5][i] * d8);
            }
            i2 = i5;
        }
        return 0.0d;
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d, double[] dArr) {
        double[] dArr2 = this.mT;
        int length = dArr2.length;
        int length2 = this.mY[0].length;
        if (this.mExtrapolate) {
            double d2 = dArr2[0];
            if (d <= d2) {
                getSlope(d2, this.mSlopeTemp);
                for (int i = 0; i < length2; i++) {
                    dArr[i] = this.mY[0][i] + ((d - this.mT[0]) * this.mSlopeTemp[i]);
                }
                return;
            }
            int i2 = length - 1;
            double d3 = dArr2[i2];
            if (d >= d3) {
                getSlope(d3, this.mSlopeTemp);
                for (int i3 = 0; i3 < length2; i3++) {
                    dArr[i3] = this.mY[i2][i3] + ((d - this.mT[i2]) * this.mSlopeTemp[i3]);
                }
                return;
            }
        } else if (d <= dArr2[0]) {
            for (int i4 = 0; i4 < length2; i4++) {
                dArr[i4] = this.mY[0][i4];
            }
            return;
        } else {
            int i5 = length - 1;
            if (d >= dArr2[i5]) {
                for (int i6 = 0; i6 < length2; i6++) {
                    dArr[i6] = this.mY[i5][i6];
                }
                return;
            }
        }
        int i7 = 0;
        while (true) {
            int i8 = i7;
            if (i8 >= length - 1) {
                return;
            }
            if (d == this.mT[i8]) {
                for (int i9 = 0; i9 < length2; i9++) {
                    dArr[i9] = this.mY[i8][i9];
                }
            }
            double[] dArr3 = this.mT;
            int i10 = i8 + 1;
            double d4 = dArr3[i10];
            if (d < d4) {
                double d5 = dArr3[i8];
                double d6 = (d - d5) / (d4 - d5);
                for (int i11 = 0; i11 < length2; i11++) {
                    double[][] dArr4 = this.mY;
                    dArr[i11] = (dArr4[i8][i11] * (1.0d - d6)) + (dArr4[i10][i11] * d6);
                }
                return;
            }
            i7 = i10;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d, float[] fArr) {
        double[] dArr = this.mT;
        int length = dArr.length;
        int length2 = this.mY[0].length;
        if (this.mExtrapolate) {
            double d2 = dArr[0];
            if (d <= d2) {
                getSlope(d2, this.mSlopeTemp);
                for (int i = 0; i < length2; i++) {
                    fArr[i] = (float) (this.mY[0][i] + ((d - this.mT[0]) * this.mSlopeTemp[i]));
                }
                return;
            }
            int i2 = length - 1;
            double d3 = dArr[i2];
            if (d >= d3) {
                getSlope(d3, this.mSlopeTemp);
                for (int i3 = 0; i3 < length2; i3++) {
                    fArr[i3] = (float) (this.mY[i2][i3] + ((d - this.mT[i2]) * this.mSlopeTemp[i3]));
                }
                return;
            }
        } else if (d <= dArr[0]) {
            for (int i4 = 0; i4 < length2; i4++) {
                fArr[i4] = (float) this.mY[0][i4];
            }
            return;
        } else {
            int i5 = length - 1;
            if (d >= dArr[i5]) {
                for (int i6 = 0; i6 < length2; i6++) {
                    fArr[i6] = (float) this.mY[i5][i6];
                }
                return;
            }
        }
        int i7 = 0;
        while (true) {
            int i8 = i7;
            if (i8 >= length - 1) {
                return;
            }
            if (d == this.mT[i8]) {
                for (int i9 = 0; i9 < length2; i9++) {
                    fArr[i9] = (float) this.mY[i8][i9];
                }
            }
            double[] dArr2 = this.mT;
            int i10 = i8 + 1;
            double d4 = dArr2[i10];
            if (d < d4) {
                double d5 = dArr2[i8];
                double d6 = (d - d5) / (d4 - d5);
                for (int i11 = 0; i11 < length2; i11++) {
                    double[][] dArr3 = this.mY;
                    fArr[i11] = (float) ((dArr3[i8][i11] * (1.0d - d6)) + (dArr3[i10][i11] * d6));
                }
                return;
            }
            i7 = i10;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004e  */
    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double getSlope(double r8, int r10) {
        /*
            r7 = this;
            r0 = r7
            double[] r0 = r0.mT
            r11 = r0
            r0 = r11
            int r0 = r0.length
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r11
            r1 = 0
            r0 = r0[r1]
            r14 = r0
            r0 = r8
            r1 = r14
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L28
            r0 = r14
            r8 = r0
        L1e:
            r0 = r13
            r16 = r0
            r0 = r8
            r14 = r0
            goto L45
        L28:
            r0 = r11
            r1 = r12
            r2 = 1
            int r1 = r1 - r2
            r0 = r0[r1]
            r17 = r0
            r0 = r13
            r16 = r0
            r0 = r8
            r14 = r0
            r0 = r8
            r1 = r17
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 < 0) goto L45
            r0 = r17
            r8 = r0
            goto L1e
        L45:
            r0 = r16
            r1 = r12
            r2 = 1
            int r1 = r1 - r2
            if (r0 >= r1) goto L94
            r0 = r7
            double[] r0 = r0.mT
            r11 = r0
            r0 = r16
            r1 = 1
            int r0 = r0 + r1
            r13 = r0
            r0 = r11
            r1 = r13
            r0 = r0[r1]
            r8 = r0
            r0 = r14
            r1 = r8
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L8d
            r0 = r11
            r1 = r16
            r0 = r0[r1]
            r17 = r0
            r0 = r7
            double[][] r0 = r0.mY
            r11 = r0
            r0 = r11
            r1 = r16
            r0 = r0[r1]
            r1 = r10
            r0 = r0[r1]
            r14 = r0
            r0 = r11
            r1 = r13
            r0 = r0[r1]
            r1 = r10
            r0 = r0[r1]
            r1 = r14
            double r0 = r0 - r1
            r1 = r8
            r2 = r17
            double r1 = r1 - r2
            double r0 = r0 / r1
            return r0
        L8d:
            r0 = r13
            r16 = r0
            goto L45
        L94:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.LinearCurveFit.getSlope(double, int):double");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ae A[SYNTHETIC] */
    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getSlope(double r10, double[] r12) {
        /*
            Method dump skipped, instructions count: 175
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.LinearCurveFit.getSlope(double, double[]):void");
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mT;
    }
}