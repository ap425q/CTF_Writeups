package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

/* loaded from: output.jar:androidx/constraintlayout/core/motion/utils/MonotonicCurveFit.class */
public class MonotonicCurveFit extends CurveFit {
    private static final String TAG = "MonotonicCurveFit";
    private boolean mExtrapolate = true;
    double[] mSlopeTemp;
    private double[] mT;
    private double[][] mTangent;
    private double[][] mY;

    public MonotonicCurveFit(double[] dArr, double[][] dArr2) {
        int length = dArr.length;
        int length2 = dArr2[0].length;
        this.mSlopeTemp = new double[length2];
        int i = length - 1;
        double[][] dArr3 = new double[i][length2];
        double[][] dArr4 = new double[length][length2];
        for (int i2 = 0; i2 < length2; i2++) {
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < i) {
                    int i5 = i4 + 1;
                    double d = dArr[i5];
                    double d2 = dArr[i4];
                    double[] dArr5 = dArr3[i4];
                    double d3 = (dArr2[i5][i2] - dArr2[i4][i2]) / (d - d2);
                    dArr5[i2] = d3;
                    if (i4 == 0) {
                        dArr4[i4][i2] = d3;
                    } else {
                        dArr4[i4][i2] = (dArr3[i4 - 1][i2] + d3) * 0.5d;
                    }
                    i3 = i5;
                }
            }
            dArr4[i][i2] = dArr3[length - 2][i2];
        }
        for (int i6 = 0; i6 < i; i6++) {
            for (int i7 = 0; i7 < length2; i7++) {
                double d4 = dArr3[i6][i7];
                if (d4 == 0.0d) {
                    dArr4[i6][i7] = 0.0d;
                    dArr4[i6 + 1][i7] = 0.0d;
                } else {
                    double d5 = dArr4[i6][i7] / d4;
                    int i8 = i6 + 1;
                    double d6 = dArr4[i8][i7] / d4;
                    double hypot = Math.hypot(d5, d6);
                    if (hypot > 9.0d) {
                        double d7 = 3.0d / hypot;
                        double[] dArr6 = dArr4[i6];
                        double[] dArr7 = dArr3[i6];
                        dArr6[i7] = d5 * d7 * dArr7[i7];
                        dArr4[i8][i7] = d7 * d6 * dArr7[i7];
                    }
                }
            }
        }
        this.mT = dArr;
        this.mY = dArr2;
        this.mTangent = dArr4;
    }

    public static MonotonicCurveFit buildWave(String str) {
        double[] dArr = new double[str.length() / 2];
        int indexOf = str.indexOf(40) + 1;
        int indexOf2 = str.indexOf(44, indexOf);
        int i = 0;
        while (indexOf2 != -1) {
            dArr[i] = Double.parseDouble(str.substring(indexOf, indexOf2).trim());
            indexOf = indexOf2 + 1;
            indexOf2 = str.indexOf(44, indexOf);
            i++;
        }
        dArr[i] = Double.parseDouble(str.substring(indexOf, str.indexOf(41, indexOf)).trim());
        return buildWave(Arrays.copyOf(dArr, i + 1));
    }

    private static MonotonicCurveFit buildWave(double[] dArr) {
        int length = (dArr.length * 3) - 2;
        int length2 = dArr.length - 1;
        double d = 1.0d / length2;
        double[][] dArr2 = new double[length][1];
        double[] dArr3 = new double[length];
        for (int i = 0; i < dArr.length; i++) {
            double d2 = dArr[i];
            int i2 = i + length2;
            dArr2[i2][0] = d2;
            double d3 = i * d;
            dArr3[i2] = d3;
            if (i > 0) {
                int i3 = (length2 * 2) + i;
                dArr2[i3][0] = d2 + 1.0d;
                dArr3[i3] = d3 + 1.0d;
                int i4 = i - 1;
                dArr2[i4][0] = (d2 - 1.0d) - d;
                dArr3[i4] = (d3 - 1.0d) - d;
            }
        }
        return new MonotonicCurveFit(dArr3, dArr2);
    }

    private static double diff(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d2 * d2;
        double d8 = d2 * 6.0d;
        double d9 = 3.0d * d;
        return ((((((((((-6.0d) * d7) * d4) + (d8 * d4)) + ((6.0d * d7) * d3)) - (d8 * d3)) + ((d9 * d6) * d7)) + ((d9 * d5) * d7)) - (((2.0d * d) * d6) * d2)) - (((4.0d * d) * d5) * d2)) + (d * d5);
    }

    private static double interpolate(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d2 * d2;
        double d8 = d7 * d2;
        double d9 = 3.0d * d7;
        double d10 = d * d6;
        double d11 = d * d5;
        return (((((((((((-2.0d) * d8) * d4) + (d9 * d4)) + ((d8 * 2.0d) * d3)) - (d9 * d3)) + d3) + (d10 * d8)) + (d8 * d11)) - (d10 * d7)) - (((d * 2.0d) * d5) * d7)) + (d11 * d2);
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double d, int i) {
        double slope;
        double d2;
        double d3;
        double[] dArr = this.mT;
        int length = dArr.length;
        int i2 = 0;
        if (this.mExtrapolate) {
            double d4 = dArr[0];
            if (d <= d4) {
                d3 = d - d4;
                d2 = this.mY[0][i];
                slope = getSlope(d4, i);
            } else {
                int i3 = length - 1;
                double d5 = dArr[i3];
                if (d >= d5) {
                    double d6 = this.mY[i3][i];
                    double d7 = d - d5;
                    slope = getSlope(d5, i);
                    d2 = d6;
                    d3 = d7;
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
            double d8 = dArr2[i2];
            if (d == d8) {
                return this.mY[i2][i];
            }
            int i5 = i2 + 1;
            double d9 = dArr2[i5];
            if (d < d9) {
                double d10 = d9 - d8;
                double d11 = (d - d8) / d10;
                double[][] dArr3 = this.mY;
                double d12 = dArr3[i2][i];
                double d13 = dArr3[i5][i];
                double[][] dArr4 = this.mTangent;
                return interpolate(d10, d11, d12, d13, dArr4[i2][i], dArr4[i5][i]);
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
                double d6 = d4 - d5;
                double d7 = (d - d5) / d6;
                for (int i11 = 0; i11 < length2; i11++) {
                    double[][] dArr4 = this.mY;
                    double d8 = dArr4[i8][i11];
                    double d9 = dArr4[i10][i11];
                    double[][] dArr5 = this.mTangent;
                    dArr[i11] = interpolate(d6, d7, d8, d9, dArr5[i8][i11], dArr5[i10][i11]);
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
                double d6 = d4 - d5;
                double d7 = (d - d5) / d6;
                for (int i11 = 0; i11 < length2; i11++) {
                    double[][] dArr3 = this.mY;
                    double d8 = dArr3[i8][i11];
                    double d9 = dArr3[i10][i11];
                    double[][] dArr4 = this.mTangent;
                    fArr[i11] = (float) interpolate(d6, d7, d8, d9, dArr4[i8][i11], dArr4[i10][i11]);
                }
                return;
            }
            i7 = i10;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getSlope(double d, int i) {
        double[] dArr = this.mT;
        int length = dArr.length;
        int i2 = 0;
        double d2 = dArr[0];
        if (d < d2) {
            d = d2;
        } else {
            double d3 = dArr[length - 1];
            if (d >= d3) {
                d = d3;
            }
        }
        while (i2 < length - 1) {
            double[] dArr2 = this.mT;
            int i3 = i2 + 1;
            double d4 = dArr2[i3];
            if (d <= d4) {
                double d5 = dArr2[i2];
                double d6 = d4 - d5;
                double d7 = (d - d5) / d6;
                double[][] dArr3 = this.mY;
                double d8 = dArr3[i2][i];
                double d9 = dArr3[i3][i];
                double[][] dArr4 = this.mTangent;
                return diff(d6, d7, d8, d9, dArr4[i2][i], dArr4[i3][i]) / d6;
            }
            i2 = i3;
        }
        return 0.0d;
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getSlope(double d, double[] dArr) {
        double[] dArr2 = this.mT;
        int length = dArr2.length;
        int length2 = this.mY[0].length;
        double d2 = dArr2[0];
        if (d <= d2) {
            d = d2;
        } else {
            double d3 = dArr2[length - 1];
            if (d >= d3) {
                d = d3;
            }
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length - 1) {
                return;
            }
            double[] dArr3 = this.mT;
            int i3 = i2 + 1;
            double d4 = dArr3[i3];
            if (d <= d4) {
                double d5 = dArr3[i2];
                double d6 = d4 - d5;
                double d7 = (d - d5) / d6;
                for (int i4 = 0; i4 < length2; i4++) {
                    double[][] dArr4 = this.mY;
                    double d8 = dArr4[i2][i4];
                    double d9 = dArr4[i3][i4];
                    double[][] dArr5 = this.mTangent;
                    dArr[i4] = diff(d6, d7, d8, d9, dArr5[i2][i4], dArr5[i3][i4]) / d6;
                }
                return;
            }
            i = i3;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mT;
    }
}