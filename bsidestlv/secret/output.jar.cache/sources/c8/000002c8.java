package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

/* loaded from: output.jar:androidx/constraintlayout/core/motion/utils/Oscillator.class */
public class Oscillator {
    public static final int BOUNCE = 6;
    public static final int COS_WAVE = 5;
    public static final int CUSTOM = 7;
    public static final int REVERSE_SAW_WAVE = 4;
    public static final int SAW_WAVE = 3;
    public static final int SIN_WAVE = 0;
    public static final int SQUARE_WAVE = 1;
    public static String TAG = "Oscillator";
    public static final int TRIANGLE_WAVE = 2;
    double[] mArea;
    MonotonicCurveFit mCustomCurve;
    String mCustomType;
    int mType;
    float[] mPeriod = new float[0];
    double[] mPosition = new double[0];
    double PI2 = 6.283185307179586d;
    private boolean mNormalized = false;

    public void addPoint(double d, float f) {
        int length = this.mPeriod.length + 1;
        int binarySearch = Arrays.binarySearch(this.mPosition, d);
        int i = binarySearch;
        if (binarySearch < 0) {
            i = (-binarySearch) - 1;
        }
        this.mPosition = Arrays.copyOf(this.mPosition, length);
        this.mPeriod = Arrays.copyOf(this.mPeriod, length);
        this.mArea = new double[length];
        double[] dArr = this.mPosition;
        System.arraycopy(dArr, i, dArr, i + 1, (length - i) - 1);
        this.mPosition[i] = d;
        this.mPeriod[i] = f;
        this.mNormalized = false;
    }

    double getDP(double d) {
        double d2;
        if (d <= 0.0d) {
            d2 = 1.0E-5d;
        } else {
            d2 = d;
            if (d >= 1.0d) {
                d2 = 0.999999d;
            }
        }
        int binarySearch = Arrays.binarySearch(this.mPosition, d2);
        if (binarySearch > 0) {
            return 0.0d;
        }
        double d3 = 0.0d;
        if (binarySearch != 0) {
            int i = (-binarySearch) - 1;
            float[] fArr = this.mPeriod;
            float f = fArr[i];
            int i2 = i - 1;
            float f2 = fArr[i2];
            double d4 = f - f2;
            double[] dArr = this.mPosition;
            double d5 = dArr[i];
            double d6 = dArr[i2];
            double d7 = d4 / (d5 - d6);
            d3 = (f2 - (d7 * d6)) + (d2 * d7);
        }
        return d3;
    }

    double getP(double d) {
        double d2;
        double d3;
        if (d < 0.0d) {
            d2 = 0.0d;
        } else {
            d2 = d;
            if (d > 1.0d) {
                d2 = 1.0d;
            }
        }
        int binarySearch = Arrays.binarySearch(this.mPosition, d2);
        if (binarySearch > 0) {
            d3 = 1.0d;
        } else {
            d3 = 0.0d;
            if (binarySearch != 0) {
                int i = (-binarySearch) - 1;
                float[] fArr = this.mPeriod;
                float f = fArr[i];
                int i2 = i - 1;
                float f2 = fArr[i2];
                double d4 = f - f2;
                double[] dArr = this.mPosition;
                double d5 = dArr[i];
                double d6 = dArr[i2];
                double d7 = d4 / (d5 - d6);
                d3 = this.mArea[i2] + ((f2 - (d7 * d6)) * (d2 - d6)) + ((d7 * ((d2 * d2) - (d6 * d6))) / 2.0d);
            }
        }
        return d3;
    }

    public double getSlope(double d, double d2, double d3) {
        double p = d2 + getP(d);
        double dp = getDP(d) + d3;
        switch (this.mType) {
            case 1:
                return 0.0d;
            case 2:
                return dp * 4.0d * Math.signum((((p * 4.0d) + 3.0d) % 4.0d) - 2.0d);
            case 3:
                return dp * 2.0d;
            case 4:
                return (-dp) * 2.0d;
            case 5:
                double d4 = this.PI2;
                return (-d4) * dp * Math.sin(d4 * p);
            case 6:
                return dp * 4.0d * ((((p * 4.0d) + 2.0d) % 4.0d) - 2.0d);
            case 7:
                return this.mCustomCurve.getSlope(p % 1.0d, 0);
            default:
                double d5 = this.PI2;
                return dp * d5 * Math.cos(d5 * p);
        }
    }

    public double getValue(double d, double d2) {
        double abs;
        double p = getP(d) + d2;
        switch (this.mType) {
            case 1:
                return Math.signum(0.5d - (p % 1.0d));
            case 2:
                abs = Math.abs((((p * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((p * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                abs = ((p * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.PI2 * (d2 + p));
            case 6:
                double abs2 = 1.0d - Math.abs(((p * 4.0d) % 4.0d) - 2.0d);
                abs = abs2 * abs2;
                break;
            case 7:
                return this.mCustomCurve.getPos(p % 1.0d, 0);
            default:
                return Math.sin(this.PI2 * p);
        }
        return 1.0d - abs;
    }

    public void normalize() {
        float[] fArr;
        double d = 0.0d;
        int i = 0;
        while (true) {
            if (i >= this.mPeriod.length) {
                break;
            }
            d += fArr[i];
            i++;
        }
        double d2 = 0.0d;
        int i2 = 1;
        while (true) {
            float[] fArr2 = this.mPeriod;
            if (i2 >= fArr2.length) {
                break;
            }
            int i3 = i2 - 1;
            float f = (fArr2[i3] + fArr2[i2]) / 2.0f;
            double[] dArr = this.mPosition;
            d2 += (dArr[i2] - dArr[i3]) * f;
            i2++;
        }
        int i4 = 0;
        while (true) {
            float[] fArr3 = this.mPeriod;
            if (i4 >= fArr3.length) {
                break;
            }
            fArr3[i4] = (float) (fArr3[i4] * (d / d2));
            i4++;
        }
        this.mArea[0] = 0.0d;
        int i5 = 1;
        while (true) {
            float[] fArr4 = this.mPeriod;
            if (i5 >= fArr4.length) {
                this.mNormalized = true;
                return;
            }
            int i6 = i5 - 1;
            float f2 = (fArr4[i6] + fArr4[i5]) / 2.0f;
            double[] dArr2 = this.mPosition;
            double d3 = dArr2[i5];
            double d4 = dArr2[i6];
            double[] dArr3 = this.mArea;
            dArr3[i5] = dArr3[i6] + ((d3 - d4) * f2);
            i5++;
        }
    }

    public void setType(int i, String str) {
        this.mType = i;
        this.mCustomType = str;
        if (str != null) {
            this.mCustomCurve = MonotonicCurveFit.buildWave(str);
        }
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.mPosition) + " period=" + Arrays.toString(this.mPeriod);
    }
}