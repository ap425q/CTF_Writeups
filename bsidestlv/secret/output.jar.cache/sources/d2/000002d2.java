package androidx.constraintlayout.core.motion.utils;

/* loaded from: output.jar:androidx/constraintlayout/core/motion/utils/StopEngine.class */
public interface StopEngine {
    String debug(String str, float f);

    float getInterpolation(float f);

    float getVelocity();

    float getVelocity(float f);

    boolean isStopped();
}