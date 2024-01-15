package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

/* loaded from: output.jar:com/google/android/material/shape/RelativeCornerSize.class */
public final class RelativeCornerSize implements CornerSize {
    private final float percent;

    public RelativeCornerSize(float f) {
        this.percent = f;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj instanceof RelativeCornerSize) {
            if (this.percent != ((RelativeCornerSize) obj).percent) {
                z = false;
            }
            return z;
        }
        return false;
    }

    @Override // com.google.android.material.shape.CornerSize
    public float getCornerSize(RectF rectF) {
        return this.percent * rectF.height();
    }

    public float getRelativePercent() {
        return this.percent;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.percent)});
    }
}