package androidx.core.graphics;

import android.graphics.Path;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

@Metadata(m29d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f\u001a\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f¨\u0006\f"}, m28d2 = {"and", "Landroid/graphics/Path;", "p", "flatten", "", "Landroidx/core/graphics/PathSegment;", "error", "", "minus", "or", "plus", "xor", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.graphics.PathKt */
/* loaded from: classes.dex */
public final class Path {
    public static /* synthetic */ Iterable flatten$default(android.graphics.Path path, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.5f;
        }
        return flatten(path, f);
    }

    public static final Iterable<PathSegment> flatten(android.graphics.Path $this$flatten, float error) {
        return PathUtils.flatten($this$flatten, error);
    }

    public static final android.graphics.Path plus(android.graphics.Path $this$plus, android.graphics.Path p) {
        android.graphics.Path $this$plus_u24lambda_u240 = new android.graphics.Path($this$plus);
        $this$plus_u24lambda_u240.op(p, Path.Op.UNION);
        return $this$plus_u24lambda_u240;
    }

    public static final android.graphics.Path minus(android.graphics.Path $this$minus, android.graphics.Path p) {
        android.graphics.Path $this$minus_u24lambda_u241 = new android.graphics.Path($this$minus);
        $this$minus_u24lambda_u241.op(p, Path.Op.DIFFERENCE);
        return $this$minus_u24lambda_u241;
    }

    /* renamed from: or */
    public static final android.graphics.Path m160or(android.graphics.Path $this$or, android.graphics.Path p) {
        android.graphics.Path $this$plus_u24lambda_u240$iv = new android.graphics.Path($this$or);
        $this$plus_u24lambda_u240$iv.op(p, Path.Op.UNION);
        return $this$plus_u24lambda_u240$iv;
    }

    public static final android.graphics.Path and(android.graphics.Path $this$and, android.graphics.Path p) {
        android.graphics.Path $this$and_u24lambda_u242 = new android.graphics.Path();
        $this$and_u24lambda_u242.op($this$and, p, Path.Op.INTERSECT);
        return $this$and_u24lambda_u242;
    }

    public static final android.graphics.Path xor(android.graphics.Path $this$xor, android.graphics.Path p) {
        android.graphics.Path $this$xor_u24lambda_u243 = new android.graphics.Path($this$xor);
        $this$xor_u24lambda_u243.op(p, Path.Op.XOR);
        return $this$xor_u24lambda_u243;
    }
}