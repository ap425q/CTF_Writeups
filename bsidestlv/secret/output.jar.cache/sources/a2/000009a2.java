package androidx.tracing;

/* loaded from: output.jar:androidx/tracing/TraceApi18Impl.class */
final class TraceApi18Impl {
    private TraceApi18Impl() {
    }

    public static void beginSection(String str) {
        android.os.Trace.beginSection(str);
    }

    public static void endSection() {
        android.os.Trace.endSection();
    }
}