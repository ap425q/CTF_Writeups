package androidx.constraintlayout.core.motion.utils;

/* loaded from: output.jar:androidx/constraintlayout/core/motion/utils/StopLogicEngine.class */
public class StopLogicEngine implements StopEngine {
    private static final float EPSILON = 1.0E-5f;
    private boolean mBackwards = false;
    private boolean mDone = false;
    private float mLastPosition;
    private int mNumberOfStages;
    private float mStage1Duration;
    private float mStage1EndPosition;
    private float mStage1Velocity;
    private float mStage2Duration;
    private float mStage2EndPosition;
    private float mStage2Velocity;
    private float mStage3Duration;
    private float mStage3EndPosition;
    private float mStage3Velocity;
    private float mStartPosition;
    private String mType;

    private float calcY(float f) {
        this.mDone = false;
        float f2 = this.mStage1Duration;
        if (f <= f2) {
            float f3 = this.mStage1Velocity;
            return (f3 * f) + ((((this.mStage2Velocity - f3) * f) * f) / (f2 * 2.0f));
        }
        int i = this.mNumberOfStages;
        if (i == 1) {
            return this.mStage1EndPosition;
        }
        float f4 = f - f2;
        float f5 = this.mStage2Duration;
        if (f4 < f5) {
            float f6 = this.mStage1EndPosition;
            float f7 = this.mStage2Velocity;
            return f6 + (f7 * f4) + ((((this.mStage3Velocity - f7) * f4) * f4) / (f5 * 2.0f));
        } else if (i == 2) {
            return this.mStage2EndPosition;
        } else {
            float f8 = f4 - f5;
            float f9 = this.mStage3Duration;
            if (f8 > f9) {
                this.mDone = true;
                return this.mStage3EndPosition;
            }
            float f10 = this.mStage2EndPosition;
            float f11 = this.mStage3Velocity;
            return (f10 + (f11 * f8)) - (((f11 * f8) * f8) / (f9 * 2.0f));
        }
    }

    private void setup(float f, float f2, float f3, float f4, float f5) {
        this.mDone = false;
        float f6 = f;
        if (f == 0.0f) {
            f6 = 1.0E-4f;
        }
        this.mStage1Velocity = f6;
        float f7 = f6 / f3;
        float f8 = (f7 * f6) / 2.0f;
        if (f6 < 0.0f) {
            float sqrt = (float) Math.sqrt((f2 - ((((-f6) / f3) * f6) / 2.0f)) * f3);
            if (sqrt < f4) {
                this.mType = "backward accelerate, decelerate";
                this.mNumberOfStages = 2;
                this.mStage1Velocity = f6;
                this.mStage2Velocity = sqrt;
                this.mStage3Velocity = 0.0f;
                float f9 = (sqrt - f6) / f3;
                this.mStage1Duration = f9;
                this.mStage2Duration = sqrt / f3;
                this.mStage1EndPosition = ((f6 + sqrt) * f9) / 2.0f;
                this.mStage2EndPosition = f2;
                this.mStage3EndPosition = f2;
                return;
            }
            this.mType = "backward accelerate cruse decelerate";
            this.mNumberOfStages = 3;
            this.mStage1Velocity = f6;
            this.mStage2Velocity = f4;
            this.mStage3Velocity = f4;
            float f10 = (f4 - f6) / f3;
            this.mStage1Duration = f10;
            float f11 = f4 / f3;
            this.mStage3Duration = f11;
            float f12 = ((f6 + f4) * f10) / 2.0f;
            float f13 = (f11 * f4) / 2.0f;
            this.mStage2Duration = ((f2 - f12) - f13) / f4;
            this.mStage1EndPosition = f12;
            this.mStage2EndPosition = f2 - f13;
            this.mStage3EndPosition = f2;
        } else if (f8 >= f2) {
            this.mType = "hard stop";
            this.mNumberOfStages = 1;
            this.mStage1Velocity = f6;
            this.mStage2Velocity = 0.0f;
            this.mStage1EndPosition = f2;
            this.mStage1Duration = (2.0f * f2) / f6;
        } else {
            float f14 = f2 - f8;
            float f15 = f14 / f6;
            if (f15 + f7 < f5) {
                this.mType = "cruse decelerate";
                this.mNumberOfStages = 2;
                this.mStage1Velocity = f6;
                this.mStage2Velocity = f6;
                this.mStage3Velocity = 0.0f;
                this.mStage1EndPosition = f14;
                this.mStage2EndPosition = f2;
                this.mStage1Duration = f15;
                this.mStage2Duration = f7;
                return;
            }
            float sqrt2 = (float) Math.sqrt((f3 * f2) + ((f6 * f6) / 2.0f));
            float f16 = (sqrt2 - f6) / f3;
            this.mStage1Duration = f16;
            float f17 = sqrt2 / f3;
            this.mStage2Duration = f17;
            if (sqrt2 < f4) {
                this.mType = "accelerate decelerate";
                this.mNumberOfStages = 2;
                this.mStage1Velocity = f6;
                this.mStage2Velocity = sqrt2;
                this.mStage3Velocity = 0.0f;
                this.mStage1Duration = f16;
                this.mStage2Duration = f17;
                this.mStage1EndPosition = ((f6 + sqrt2) * f16) / 2.0f;
                this.mStage2EndPosition = f2;
                return;
            }
            this.mType = "accelerate cruse decelerate";
            this.mNumberOfStages = 3;
            this.mStage1Velocity = f6;
            this.mStage2Velocity = f4;
            this.mStage3Velocity = f4;
            float f18 = (f4 - f6) / f3;
            this.mStage1Duration = f18;
            float f19 = f4 / f3;
            this.mStage3Duration = f19;
            float f20 = ((f6 + f4) * f18) / 2.0f;
            float f21 = (f19 * f4) / 2.0f;
            this.mStage2Duration = ((f2 - f20) - f21) / f4;
            this.mStage1EndPosition = f20;
            this.mStage2EndPosition = f2 - f21;
            this.mStage3EndPosition = f2;
        }
    }

    public void config(float f, float f2, float f3, float f4, float f5, float f6) {
        boolean z = false;
        this.mDone = false;
        this.mStartPosition = f;
        if (f > f2) {
            z = true;
        }
        this.mBackwards = z;
        if (z) {
            setup(-f3, f - f2, f5, f6, f4);
        } else {
            setup(f3, f2 - f, f5, f6, f4);
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public String debug(String str, float f) {
        String str2 = ((str + " ===== " + this.mType + "\n") + str + (this.mBackwards ? "backwards" : "forward ") + " time = " + f + "  stages " + this.mNumberOfStages + "\n") + str + " dur " + this.mStage1Duration + " vel " + this.mStage1Velocity + " pos " + this.mStage1EndPosition + "\n";
        String str3 = str2;
        if (this.mNumberOfStages > 1) {
            str3 = str2 + str + " dur " + this.mStage2Duration + " vel " + this.mStage2Velocity + " pos " + this.mStage2EndPosition + "\n";
        }
        String str4 = str3;
        if (this.mNumberOfStages > 2) {
            str4 = str3 + str + " dur " + this.mStage3Duration + " vel " + this.mStage3Velocity + " pos " + this.mStage3EndPosition + "\n";
        }
        float f2 = this.mStage1Duration;
        if (f <= f2) {
            return str4 + str + "stage 0\n";
        }
        int i = this.mNumberOfStages;
        if (i == 1) {
            return str4 + str + "end stage 0\n";
        }
        float f3 = f - f2;
        float f4 = this.mStage2Duration;
        return f3 < f4 ? str4 + str + " stage 1\n" : i == 2 ? str4 + str + "end stage 1\n" : f3 - f4 < this.mStage3Duration ? str4 + str + " stage 2\n" : str4 + str + " end stage 2\n";
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getInterpolation(float f) {
        float calcY = calcY(f);
        this.mLastPosition = f;
        return this.mBackwards ? this.mStartPosition - calcY : this.mStartPosition + calcY;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity() {
        return this.mBackwards ? -getVelocity(this.mLastPosition) : getVelocity(this.mLastPosition);
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity(float f) {
        float f2 = this.mStage1Duration;
        if (f <= f2) {
            float f3 = this.mStage1Velocity;
            return f3 + (((this.mStage2Velocity - f3) * f) / f2);
        }
        int i = this.mNumberOfStages;
        if (i == 1) {
            return 0.0f;
        }
        float f4 = f - f2;
        float f5 = this.mStage2Duration;
        if (f4 < f5) {
            float f6 = this.mStage2Velocity;
            return f6 + (((this.mStage3Velocity - f6) * f4) / f5);
        } else if (i == 2) {
            return this.mStage2EndPosition;
        } else {
            float f7 = f4 - f5;
            float f8 = this.mStage3Duration;
            if (f7 < f8) {
                float f9 = this.mStage3Velocity;
                return f9 - ((f7 * f9) / f8);
            }
            return this.mStage3EndPosition;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public boolean isStopped() {
        return getVelocity() < EPSILON && Math.abs(this.mStage3EndPosition - this.mLastPosition) < EPSILON;
    }
}