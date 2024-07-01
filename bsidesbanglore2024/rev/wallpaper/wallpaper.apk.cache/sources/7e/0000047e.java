package androidx.core.graphics;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m20d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u001a\r\u0010\u0004\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\u0004\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\r\u0010\u0007\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\u0007\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\r\u0010\b\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\b\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\r\u0010\t\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\t\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0086\n\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\u000eH\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\rH\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H\u0086\n\u001a\u0015\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f\u001a\u0015\u0010\u0012\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\f\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\rH\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H\u0086\n\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\u0014\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0006H\u0086\n\u001a\u0015\u0010\u0014\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0005H\u0086\n\u001a\r\u0010\u0016\u001a\u00020\u0001*\u00020\u0003H\u0086\b\u001a\r\u0010\u0017\u001a\u00020\u0003*\u00020\u0001H\u0086\b\u001a\r\u0010\u0018\u001a\u00020\u0011*\u00020\u0001H\u0086\b\u001a\r\u0010\u0018\u001a\u00020\u0011*\u00020\u0003H\u0086\b\u001a\u0015\u0010\u0019\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u0086\b\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\f¨\u0006\u001d"}, m19d2 = {"and", "Landroid/graphics/Rect;", "r", "Landroid/graphics/RectF;", "component1", "", "", "component2", "component3", "component4", "contains", "", "p", "Landroid/graphics/Point;", "Landroid/graphics/PointF;", "minus", "xy", "Landroid/graphics/Region;", "or", "plus", "times", "factor", "toRect", "toRectF", "toRegion", "transform", "m", "Landroid/graphics/Matrix;", "xor", "core-ktx_release"}, m18k = 2, m17mv = {1, 5, 1}, m15xi = 48)
/* renamed from: androidx.core.graphics.RectKt */
/* loaded from: classes.dex */
public final class Rect {
    public static final int component1(android.graphics.Rect $this$component1) {
        Intrinsics.checkNotNullParameter($this$component1, "<this>");
        return $this$component1.left;
    }

    public static final int component2(android.graphics.Rect $this$component2) {
        Intrinsics.checkNotNullParameter($this$component2, "<this>");
        return $this$component2.top;
    }

    public static final int component3(android.graphics.Rect $this$component3) {
        Intrinsics.checkNotNullParameter($this$component3, "<this>");
        return $this$component3.right;
    }

    public static final int component4(android.graphics.Rect $this$component4) {
        Intrinsics.checkNotNullParameter($this$component4, "<this>");
        return $this$component4.bottom;
    }

    public static final float component1(RectF $this$component1) {
        Intrinsics.checkNotNullParameter($this$component1, "<this>");
        return $this$component1.left;
    }

    public static final float component2(RectF $this$component2) {
        Intrinsics.checkNotNullParameter($this$component2, "<this>");
        return $this$component2.top;
    }

    public static final float component3(RectF $this$component3) {
        Intrinsics.checkNotNullParameter($this$component3, "<this>");
        return $this$component3.right;
    }

    public static final float component4(RectF $this$component4) {
        Intrinsics.checkNotNullParameter($this$component4, "<this>");
        return $this$component4.bottom;
    }

    public static final android.graphics.Rect plus(android.graphics.Rect $this$plus, android.graphics.Rect r) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Intrinsics.checkNotNullParameter(r, "r");
        android.graphics.Rect $this$plus_u24lambda_u2d0 = new android.graphics.Rect($this$plus);
        $this$plus_u24lambda_u2d0.union(r);
        return $this$plus_u24lambda_u2d0;
    }

    public static final RectF plus(RectF $this$plus, RectF r) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Intrinsics.checkNotNullParameter(r, "r");
        RectF $this$plus_u24lambda_u2d1 = new RectF($this$plus);
        $this$plus_u24lambda_u2d1.union(r);
        return $this$plus_u24lambda_u2d1;
    }

    public static final android.graphics.Rect plus(android.graphics.Rect $this$plus, int xy) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        android.graphics.Rect $this$plus_u24lambda_u2d2 = new android.graphics.Rect($this$plus);
        $this$plus_u24lambda_u2d2.offset(xy, xy);
        return $this$plus_u24lambda_u2d2;
    }

    public static final RectF plus(RectF $this$plus, float xy) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        RectF $this$plus_u24lambda_u2d3 = new RectF($this$plus);
        $this$plus_u24lambda_u2d3.offset(xy, xy);
        return $this$plus_u24lambda_u2d3;
    }

    public static final android.graphics.Rect plus(android.graphics.Rect $this$plus, Point xy) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Intrinsics.checkNotNullParameter(xy, "xy");
        android.graphics.Rect $this$plus_u24lambda_u2d4 = new android.graphics.Rect($this$plus);
        $this$plus_u24lambda_u2d4.offset(xy.x, xy.y);
        return $this$plus_u24lambda_u2d4;
    }

    public static final RectF plus(RectF $this$plus, PointF xy) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Intrinsics.checkNotNullParameter(xy, "xy");
        RectF $this$plus_u24lambda_u2d5 = new RectF($this$plus);
        $this$plus_u24lambda_u2d5.offset(xy.x, xy.y);
        return $this$plus_u24lambda_u2d5;
    }

    public static final Region minus(android.graphics.Rect $this$minus, android.graphics.Rect r) {
        Intrinsics.checkNotNullParameter($this$minus, "<this>");
        Intrinsics.checkNotNullParameter(r, "r");
        Region $this$minus_u24lambda_u2d6 = new Region($this$minus);
        $this$minus_u24lambda_u2d6.op(r, Region.Op.DIFFERENCE);
        return $this$minus_u24lambda_u2d6;
    }

    public static final Region minus(RectF $this$minus, RectF r) {
        Intrinsics.checkNotNullParameter($this$minus, "<this>");
        Intrinsics.checkNotNullParameter(r, "r");
        android.graphics.Rect r$iv = new android.graphics.Rect();
        $this$minus.roundOut(r$iv);
        Region $this$minus_u24lambda_u2d7 = new Region(r$iv);
        android.graphics.Rect r$iv2 = new android.graphics.Rect();
        r.roundOut(r$iv2);
        $this$minus_u24lambda_u2d7.op(r$iv2, Region.Op.DIFFERENCE);
        return $this$minus_u24lambda_u2d7;
    }

    public static final android.graphics.Rect minus(android.graphics.Rect $this$minus, int xy) {
        Intrinsics.checkNotNullParameter($this$minus, "<this>");
        android.graphics.Rect $this$minus_u24lambda_u2d8 = new android.graphics.Rect($this$minus);
        $this$minus_u24lambda_u2d8.offset(-xy, -xy);
        return $this$minus_u24lambda_u2d8;
    }

    public static final RectF minus(RectF $this$minus, float xy) {
        Intrinsics.checkNotNullParameter($this$minus, "<this>");
        RectF $this$minus_u24lambda_u2d9 = new RectF($this$minus);
        $this$minus_u24lambda_u2d9.offset(-xy, -xy);
        return $this$minus_u24lambda_u2d9;
    }

    public static final android.graphics.Rect minus(android.graphics.Rect $this$minus, Point xy) {
        Intrinsics.checkNotNullParameter($this$minus, "<this>");
        Intrinsics.checkNotNullParameter(xy, "xy");
        android.graphics.Rect $this$minus_u24lambda_u2d10 = new android.graphics.Rect($this$minus);
        $this$minus_u24lambda_u2d10.offset(-xy.x, -xy.y);
        return $this$minus_u24lambda_u2d10;
    }

    public static final RectF minus(RectF $this$minus, PointF xy) {
        Intrinsics.checkNotNullParameter($this$minus, "<this>");
        Intrinsics.checkNotNullParameter(xy, "xy");
        RectF $this$minus_u24lambda_u2d11 = new RectF($this$minus);
        $this$minus_u24lambda_u2d11.offset(-xy.x, -xy.y);
        return $this$minus_u24lambda_u2d11;
    }

    public static final android.graphics.Rect times(android.graphics.Rect $this$times, int factor) {
        Intrinsics.checkNotNullParameter($this$times, "<this>");
        android.graphics.Rect $this$times_u24lambda_u2d12 = new android.graphics.Rect($this$times);
        $this$times_u24lambda_u2d12.top *= factor;
        $this$times_u24lambda_u2d12.left *= factor;
        $this$times_u24lambda_u2d12.right *= factor;
        $this$times_u24lambda_u2d12.bottom *= factor;
        return $this$times_u24lambda_u2d12;
    }

    public static final RectF times(RectF $this$times, int factor) {
        Intrinsics.checkNotNullParameter($this$times, "<this>");
        float factor$iv = factor;
        RectF $this$times_u24lambda_u2d13$iv = new RectF($this$times);
        $this$times_u24lambda_u2d13$iv.top *= factor$iv;
        $this$times_u24lambda_u2d13$iv.left *= factor$iv;
        $this$times_u24lambda_u2d13$iv.right *= factor$iv;
        $this$times_u24lambda_u2d13$iv.bottom *= factor$iv;
        return $this$times_u24lambda_u2d13$iv;
    }

    public static final RectF times(RectF $this$times, float factor) {
        Intrinsics.checkNotNullParameter($this$times, "<this>");
        RectF $this$times_u24lambda_u2d13 = new RectF($this$times);
        $this$times_u24lambda_u2d13.top *= factor;
        $this$times_u24lambda_u2d13.left *= factor;
        $this$times_u24lambda_u2d13.right *= factor;
        $this$times_u24lambda_u2d13.bottom *= factor;
        return $this$times_u24lambda_u2d13;
    }

    /* renamed from: or */
    public static final android.graphics.Rect m45or(android.graphics.Rect $this$or, android.graphics.Rect r) {
        Intrinsics.checkNotNullParameter($this$or, "<this>");
        Intrinsics.checkNotNullParameter(r, "r");
        android.graphics.Rect $this$plus_u24lambda_u2d0$iv = new android.graphics.Rect($this$or);
        $this$plus_u24lambda_u2d0$iv.union(r);
        return $this$plus_u24lambda_u2d0$iv;
    }

    /* renamed from: or */
    public static final RectF m44or(RectF $this$or, RectF r) {
        Intrinsics.checkNotNullParameter($this$or, "<this>");
        Intrinsics.checkNotNullParameter(r, "r");
        RectF $this$plus_u24lambda_u2d1$iv = new RectF($this$or);
        $this$plus_u24lambda_u2d1$iv.union(r);
        return $this$plus_u24lambda_u2d1$iv;
    }

    public static final android.graphics.Rect and(android.graphics.Rect $this$and, android.graphics.Rect r) {
        Intrinsics.checkNotNullParameter($this$and, "<this>");
        Intrinsics.checkNotNullParameter(r, "r");
        android.graphics.Rect $this$and_u24lambda_u2d14 = new android.graphics.Rect($this$and);
        $this$and_u24lambda_u2d14.intersect(r);
        return $this$and_u24lambda_u2d14;
    }

    public static final RectF and(RectF $this$and, RectF r) {
        Intrinsics.checkNotNullParameter($this$and, "<this>");
        Intrinsics.checkNotNullParameter(r, "r");
        RectF $this$and_u24lambda_u2d15 = new RectF($this$and);
        $this$and_u24lambda_u2d15.intersect(r);
        return $this$and_u24lambda_u2d15;
    }

    public static final Region xor(android.graphics.Rect $this$xor, android.graphics.Rect r) {
        Intrinsics.checkNotNullParameter($this$xor, "<this>");
        Intrinsics.checkNotNullParameter(r, "r");
        Region $this$xor_u24lambda_u2d16 = new Region($this$xor);
        $this$xor_u24lambda_u2d16.op(r, Region.Op.XOR);
        return $this$xor_u24lambda_u2d16;
    }

    public static final Region xor(RectF $this$xor, RectF r) {
        Intrinsics.checkNotNullParameter($this$xor, "<this>");
        Intrinsics.checkNotNullParameter(r, "r");
        android.graphics.Rect r$iv = new android.graphics.Rect();
        $this$xor.roundOut(r$iv);
        Region $this$xor_u24lambda_u2d17 = new Region(r$iv);
        android.graphics.Rect r$iv2 = new android.graphics.Rect();
        r.roundOut(r$iv2);
        $this$xor_u24lambda_u2d17.op(r$iv2, Region.Op.XOR);
        return $this$xor_u24lambda_u2d17;
    }

    public static final boolean contains(android.graphics.Rect $this$contains, Point p) {
        Intrinsics.checkNotNullParameter($this$contains, "<this>");
        Intrinsics.checkNotNullParameter(p, "p");
        return $this$contains.contains(p.x, p.y);
    }

    public static final boolean contains(RectF $this$contains, PointF p) {
        Intrinsics.checkNotNullParameter($this$contains, "<this>");
        Intrinsics.checkNotNullParameter(p, "p");
        return $this$contains.contains(p.x, p.y);
    }

    public static final RectF toRectF(android.graphics.Rect $this$toRectF) {
        Intrinsics.checkNotNullParameter($this$toRectF, "<this>");
        return new RectF($this$toRectF);
    }

    public static final android.graphics.Rect toRect(RectF $this$toRect) {
        Intrinsics.checkNotNullParameter($this$toRect, "<this>");
        android.graphics.Rect r = new android.graphics.Rect();
        $this$toRect.roundOut(r);
        return r;
    }

    public static final Region toRegion(android.graphics.Rect $this$toRegion) {
        Intrinsics.checkNotNullParameter($this$toRegion, "<this>");
        return new Region($this$toRegion);
    }

    public static final Region toRegion(RectF $this$toRegion) {
        Intrinsics.checkNotNullParameter($this$toRegion, "<this>");
        android.graphics.Rect r$iv = new android.graphics.Rect();
        $this$toRegion.roundOut(r$iv);
        return new Region(r$iv);
    }

    public static final RectF transform(RectF $this$transform, Matrix m) {
        Intrinsics.checkNotNullParameter($this$transform, "<this>");
        Intrinsics.checkNotNullParameter(m, "m");
        m.mapRect($this$transform);
        return $this$transform;
    }
}