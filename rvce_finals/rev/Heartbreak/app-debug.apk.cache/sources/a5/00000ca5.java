package androidx.transition;

import java.util.Arrays;

/* loaded from: classes.dex */
class VelocityTracker1D {
    private static final int ASSUME_POINTER_MOVE_STOPPED_MILLIS = 40;
    private static final int HISTORY_SIZE = 20;
    private static final int HORIZON_MILLIS = 100;
    private long[] mTimeSamples = new long[20];
    private float[] mDataSamples = new float[20];
    private int mIndex = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VelocityTracker1D() {
        Arrays.fill(this.mTimeSamples, Long.MIN_VALUE);
    }

    public void addDataPoint(long timeMillis, float data) {
        this.mIndex = (this.mIndex + 1) % 20;
        this.mTimeSamples[this.mIndex] = timeMillis;
        this.mDataSamples[this.mIndex] = data;
    }

    public void resetTracking() {
        this.mIndex = 0;
        Arrays.fill(this.mTimeSamples, Long.MIN_VALUE);
        Arrays.fill(this.mDataSamples, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float calculateVelocity() {
        int sampleCount;
        int sampleCount2;
        int sampleCount3 = 0;
        int index = this.mIndex;
        float f = 0.0f;
        if (index == 0 && this.mTimeSamples[index] == Long.MIN_VALUE) {
            return 0.0f;
        }
        long newestTime = this.mTimeSamples[index];
        long previousTime = newestTime;
        do {
            long sampleTime = this.mTimeSamples[index];
            if (sampleTime != Long.MIN_VALUE) {
                float age = (float) (newestTime - sampleTime);
                float delta = (float) Math.abs(sampleTime - previousTime);
                previousTime = sampleTime;
                if (age > 100.0f || delta > 40.0f) {
                    break;
                }
                index = (index == 0 ? 20 : index) - 1;
                sampleCount3++;
            } else {
                break;
            }
        } while (sampleCount3 < 20);
        if (sampleCount3 < 2) {
            return 0.0f;
        }
        if (sampleCount3 == 2) {
            int prevIndex = this.mIndex == 0 ? 19 : this.mIndex - 1;
            float timeDiff = (float) (this.mTimeSamples[this.mIndex] - this.mTimeSamples[prevIndex]);
            if (timeDiff == 0.0f) {
                return 0.0f;
            }
            float dataDiff = this.mDataSamples[this.mIndex] - this.mDataSamples[prevIndex];
            return (dataDiff / timeDiff) * 1000.0f;
        }
        float work = 0.0f;
        int startIndex = (((this.mIndex - sampleCount3) + 20) + 1) % 20;
        int endIndex = ((this.mIndex + 1) + 20) % 20;
        long previousTime2 = this.mTimeSamples[startIndex];
        float previousData = this.mDataSamples[startIndex];
        int i = (startIndex + 1) % 20;
        while (i != endIndex) {
            long time = this.mTimeSamples[i];
            int i2 = i;
            long timeDelta = time - previousTime2;
            if (((float) timeDelta) == f) {
                sampleCount = sampleCount3;
                sampleCount2 = i2;
            } else {
                float data = this.mDataSamples[i2];
                float vPrev = kineticEnergyToVelocity(work);
                float dataPointsDelta = data - previousData;
                float vCurr = dataPointsDelta / ((float) timeDelta);
                work += (vCurr - vPrev) * Math.abs(vCurr);
                sampleCount = sampleCount3;
                sampleCount2 = i2;
                if (sampleCount2 == startIndex + 1) {
                    work *= 0.5f;
                }
                previousTime2 = time;
                previousData = data;
            }
            i = (sampleCount2 + 1) % 20;
            sampleCount3 = sampleCount;
            f = 0.0f;
        }
        return kineticEnergyToVelocity(work) * 1000.0f;
    }

    private float kineticEnergyToVelocity(float kineticEnergy) {
        return (float) (Math.signum(kineticEnergy) * Math.sqrt(Math.abs(kineticEnergy) * 2.0f));
    }
}