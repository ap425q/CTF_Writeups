package androidx.concurrent.futures;

import java.util.concurrent.Executor;

/* loaded from: output.jar:androidx/concurrent/futures/DirectExecutor.class */
enum DirectExecutor implements Executor {
    INSTANCE;

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        runnable.run();
    }

    @Override // java.lang.Enum
    public String toString() {
        return "DirectExecutor";
    }
}