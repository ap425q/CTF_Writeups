package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;

/* loaded from: classes.dex */
abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {
    S spec;

    /* loaded from: classes.dex */
    protected static class ActiveIndicator {
        int color;
        float endFraction;
        int gapSize;
        float startFraction;
    }

    abstract void adjustCanvas(Canvas canvas, Rect rect, float f, boolean z, boolean z2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void drawStopIndicator(Canvas canvas, Paint paint, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void fillIndicator(Canvas canvas, Paint paint, ActiveIndicator activeIndicator, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void fillTrack(Canvas canvas, Paint paint, float f, float f2, int i, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getPreferredHeight();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getPreferredWidth();

    public DrawingDelegate(S spec) {
        this.spec = spec;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void validateSpecAndAdjustCanvas(Canvas canvas, Rect bounds, float trackThicknessFraction, boolean isShowing, boolean isHiding) {
        this.spec.validateSpec();
        adjustCanvas(canvas, bounds, trackThicknessFraction, isShowing, isHiding);
    }
}