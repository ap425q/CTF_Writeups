package androidx.constraintlayout.core.state;

/* loaded from: output.jar:androidx/constraintlayout/core/state/RegistryCallback.class */
public interface RegistryCallback {
    String currentLayoutInformation();

    String currentMotionScene();

    long getLastModified();

    void onDimensions(int i, int i2);

    void onNewMotionScene(String str);

    void onProgress(float f);

    void setDrawDebug(int i);

    void setLayoutInformationMode(int i);
}