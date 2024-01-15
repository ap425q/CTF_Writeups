package androidx.constraintlayout.core.motion.utils;

/* loaded from: output.jar:androidx/constraintlayout/core/motion/utils/HyperSpline.class */
public class HyperSpline {
    double[][] mCtl;
    Cubic[][] mCurve;
    double[] mCurveLength;
    int mDimensionality;
    int mPoints;
    double mTotalLength;

    /* loaded from: output.jar:androidx/constraintlayout/core/motion/utils/HyperSpline$Cubic.class */
    public static class Cubic {
        double mA;
        double mB;
        double mC;
        double mD;

        public Cubic(double d, double d2, double d3, double d4) {
            this.mA = d;
            this.mB = d2;
            this.mC = d3;
            this.mD = d4;
        }

        public double eval(double d) {
            return (((((this.mD * d) + this.mC) * d) + this.mB) * d) + this.mA;
        }

        public double vel(double d) {
            return (((this.mD * 3.0d * d) + (this.mC * 2.0d)) * d) + this.mB;
        }
    }

    public HyperSpline() {
    }

    public HyperSpline(double[][] dArr) {
        setup(dArr);
    }

    static Cubic[] calcNaturalCubic(int i, double[] dArr) {
        double[] dArr2 = new double[i];
        double[] dArr3 = new double[i];
        double[] dArr4 = new double[i];
        int i2 = i - 1;
        int i3 = 0;
        dArr2[0] = 0.5d;
        int i4 = 1;
        for (int i5 = 1; i5 < i2; i5++) {
            dArr2[i5] = 1.0d / (4.0d - dArr2[i5 - 1]);
        }
        int i6 = i2 - 1;
        dArr2[i2] = 1.0d / (2.0d - dArr2[i6]);
        dArr3[0] = (dArr[1] - dArr[0]) * 3.0d * dArr2[0];
        while (true) {
            int i7 = i4;
            if (i7 >= i2) {
                break;
            }
            i4 = i7 + 1;
            int i8 = i7 - 1;
            dArr3[i7] = (((dArr[i4] - dArr[i8]) * 3.0d) - dArr3[i8]) * dArr2[i7];
        }
        double d = (((dArr[i2] - dArr[i6]) * 3.0d) - dArr3[i6]) * dArr2[i2];
        dArr3[i2] = d;
        dArr4[i2] = d;
        for (int i9 = i6; i9 >= 0; i9--) {
            dArr4[i9] = dArr3[i9] - (dArr2[i9] * dArr4[i9 + 1]);
        }
        Cubic[] cubicArr = new Cubic[i2];
        while (true) {
            int i10 = i3;
            if (i10 >= i2) {
                return cubicArr;
            }
            double d2 = dArr[i10];
            double d3 = (float) d2;
            double d4 = dArr4[i10];
            i3 = i10 + 1;
            double d5 = dArr[i3];
            double d6 = dArr4[i3];
            cubicArr[i10] = new Cubic(d3, d4, (((d5 - d2) * 3.0d) - (d4 * 2.0d)) - d6, ((d2 - d5) * 2.0d) + d4 + d6);
        }
    }

    public double approxLength(Cubic[] cubicArr) {
        double d;
        int i;
        double d2;
        int length = cubicArr.length;
        double[] dArr = new double[cubicArr.length];
        double d3 = 0.0d;
        double d4 = 0.0d;
        while (true) {
            d = d4;
            d2 = 0.0d;
            if (d3 >= 1.0d) {
                break;
            }
            double d5 = 0.0d;
            for (int i2 = 0; i2 < cubicArr.length; i2++) {
                double d6 = dArr[i2];
                double eval = cubicArr[i2].eval(d3);
                dArr[i2] = eval;
                double d7 = d6 - eval;
                d5 += d7 * d7;
            }
            double d8 = d;
            if (d3 > 0.0d) {
                d8 = d + Math.sqrt(d5);
            }
            d3 += 0.1d;
            d4 = d8;
        }
        for (i = 0; i < cubicArr.length; i++) {
            double d9 = dArr[i];
            double eval2 = cubicArr[i].eval(1.0d);
            dArr[i] = eval2;
            double d10 = d9 - eval2;
            d2 += d10 * d10;
        }
        return d + Math.sqrt(d2);
    }

    public double getPos(double d, int i) {
        double[] dArr;
        double d2 = d * this.mTotalLength;
        int i2 = 0;
        while (true) {
            dArr = this.mCurveLength;
            if (i2 >= dArr.length - 1) {
                break;
            }
            double d3 = dArr[i2];
            if (d3 >= d2) {
                break;
            }
            d2 -= d3;
            i2++;
        }
        return this.mCurve[i][i2].eval(d2 / dArr[i2]);
    }

    public void getPos(double d, double[] dArr) {
        int i;
        double d2 = d * this.mTotalLength;
        int i2 = 0;
        while (true) {
            double[] dArr2 = this.mCurveLength;
            i = 0;
            if (i2 >= dArr2.length - 1) {
                break;
            }
            double d3 = dArr2[i2];
            i = 0;
            if (d3 >= d2) {
                break;
            }
            d2 -= d3;
            i2++;
        }
        while (i < dArr.length) {
            dArr[i] = this.mCurve[i][i2].eval(d2 / this.mCurveLength[i2]);
            i++;
        }
    }

    public void getPos(double d, float[] fArr) {
        int i;
        double d2 = d * this.mTotalLength;
        int i2 = 0;
        while (true) {
            double[] dArr = this.mCurveLength;
            i = 0;
            if (i2 >= dArr.length - 1) {
                break;
            }
            double d3 = dArr[i2];
            i = 0;
            if (d3 >= d2) {
                break;
            }
            d2 -= d3;
            i2++;
        }
        while (i < fArr.length) {
            fArr[i] = (float) this.mCurve[i][i2].eval(d2 / this.mCurveLength[i2]);
            i++;
        }
    }

    public void getVelocity(double d, double[] dArr) {
        int i;
        double d2 = d * this.mTotalLength;
        int i2 = 0;
        while (true) {
            double[] dArr2 = this.mCurveLength;
            i = 0;
            if (i2 >= dArr2.length - 1) {
                break;
            }
            double d3 = dArr2[i2];
            i = 0;
            if (d3 >= d2) {
                break;
            }
            d2 -= d3;
            i2++;
        }
        while (i < dArr.length) {
            dArr[i] = this.mCurve[i][i2].vel(d2 / this.mCurveLength[i2]);
            i++;
        }
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [androidx.constraintlayout.core.motion.utils.HyperSpline$Cubic[], androidx.constraintlayout.core.motion.utils.HyperSpline$Cubic[][]] */
    public void setup(double[][] dArr) {
        int i;
        int length = dArr[0].length;
        this.mDimensionality = length;
        int length2 = dArr.length;
        this.mPoints = length2;
        this.mCtl = new double[length][length2];
        this.mCurve = new Cubic[this.mDimensionality];
        for (int i2 = 0; i2 < this.mDimensionality; i2++) {
            for (int i3 = 0; i3 < this.mPoints; i3++) {
                this.mCtl[i2][i3] = dArr[i3][i2];
            }
        }
        int i4 = 0;
        while (true) {
            i = this.mDimensionality;
            if (i4 >= i) {
                break;
            }
            Cubic[][] cubicArr = this.mCurve;
            double[] dArr2 = this.mCtl[i4];
            cubicArr[i4] = calcNaturalCubic(dArr2.length, dArr2);
            i4++;
        }
        this.mCurveLength = new double[this.mPoints - 1];
        this.mTotalLength = 0.0d;
        Cubic[] cubicArr2 = new Cubic[i];
        for (int i5 = 0; i5 < this.mCurveLength.length; i5++) {
            for (int i6 = 0; i6 < this.mDimensionality; i6++) {
                cubicArr2[i6] = this.mCurve[i6][i5];
            }
            double d = this.mTotalLength;
            double[] dArr3 = this.mCurveLength;
            double approxLength = approxLength(cubicArr2);
            dArr3[i5] = approxLength;
            this.mTotalLength = d + approxLength;
        }
    }
}