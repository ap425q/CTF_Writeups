package androidx.core.graphics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m20d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0014\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u001a\u001a\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003\u001a\u001a\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u001a\u0015\u0010\f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0086\n\u001a\r\u0010\u000e\u001a\u00020\u000f*\u00020\u0001H\u0086\b¨\u0006\u0010"}, m19d2 = {"rotationMatrix", "Landroid/graphics/Matrix;", "degrees", "", "px", "py", "scaleMatrix", "sx", "sy", "translationMatrix", "tx", "ty", "times", "m", "values", "", "core-ktx_release"}, m18k = 2, m17mv = {1, 5, 1}, m15xi = 48)
/* renamed from: androidx.core.graphics.MatrixKt */
/* loaded from: classes.dex */
public final class Matrix {
    public static final android.graphics.Matrix times(android.graphics.Matrix $this$times, android.graphics.Matrix m) {
        Intrinsics.checkNotNullParameter($this$times, "<this>");
        Intrinsics.checkNotNullParameter(m, "m");
        android.graphics.Matrix $this$times_u24lambda_u2d0 = new android.graphics.Matrix($this$times);
        $this$times_u24lambda_u2d0.preConcat(m);
        return $this$times_u24lambda_u2d0;
    }

    public static final float[] values(android.graphics.Matrix $this$values) {
        Intrinsics.checkNotNullParameter($this$values, "<this>");
        float[] $this$values_u24lambda_u2d1 = new float[9];
        $this$values.getValues($this$values_u24lambda_u2d1);
        return $this$values_u24lambda_u2d1;
    }

    public static /* synthetic */ android.graphics.Matrix translationMatrix$default(float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        return translationMatrix(f, f2);
    }

    public static final android.graphics.Matrix translationMatrix(float tx, float ty) {
        android.graphics.Matrix $this$translationMatrix_u24lambda_u2d2 = new android.graphics.Matrix();
        $this$translationMatrix_u24lambda_u2d2.setTranslate(tx, ty);
        return $this$translationMatrix_u24lambda_u2d2;
    }

    public static /* synthetic */ android.graphics.Matrix scaleMatrix$default(float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        if ((i & 2) != 0) {
            f2 = 1.0f;
        }
        return scaleMatrix(f, f2);
    }

    public static final android.graphics.Matrix scaleMatrix(float sx, float sy) {
        android.graphics.Matrix $this$scaleMatrix_u24lambda_u2d3 = new android.graphics.Matrix();
        $this$scaleMatrix_u24lambda_u2d3.setScale(sx, sy);
        return $this$scaleMatrix_u24lambda_u2d3;
    }

    public static /* synthetic */ android.graphics.Matrix rotationMatrix$default(float f, float f2, float f3, int i, Object obj) {
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        if ((i & 4) != 0) {
            f3 = 0.0f;
        }
        return rotationMatrix(f, f2, f3);
    }

    public static final android.graphics.Matrix rotationMatrix(float degrees, float px, float py) {
        android.graphics.Matrix $this$rotationMatrix_u24lambda_u2d4 = new android.graphics.Matrix();
        $this$rotationMatrix_u24lambda_u2d4.setRotate(degrees, px, py);
        return $this$rotationMatrix_u24lambda_u2d4;
    }
}