package androidx.core.graphics;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

@Metadata(m29d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\f\u001a\r\u0010\u0004\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\u0004\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\r\u0010\u0007\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\u0007\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\r\u0010\b\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\b\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\r\u0010\t\u001a\u00020\u0005*\u00020\u0001H\u0086\n\u001a\r\u0010\t\u001a\u00020\u0006*\u00020\u0003H\u0086\n\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0086\n\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\u000eH\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\rH\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\u000f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H\u0086\n\u001a\u0015\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f\u001a\u0015\u0010\u0012\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\f\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\rH\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H\u0086\n\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\u0014\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0006H\u0086\n\u001a\u0015\u0010\u0014\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0005H\u0086\n\u001a\r\u0010\u0016\u001a\u00020\u0001*\u00020\u0003H\u0086\b\u001a\r\u0010\u0017\u001a\u00020\u0003*\u00020\u0001H\u0086\b\u001a\r\u0010\u0018\u001a\u00020\u0011*\u00020\u0001H\u0086\b\u001a\r\u0010\u0018\u001a\u00020\u0011*\u00020\u0003H\u0086\b\u001a\u0015\u0010\u0019\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u0086\b\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\f¨\u0006\u001d"}, m28d2 = {"and", "Landroid/graphics/Rect;", "r", "Landroid/graphics/RectF;", "component1", "", "", "component2", "component3", "component4", "contains", "", "p", "Landroid/graphics/Point;", "Landroid/graphics/PointF;", "minus", "xy", "Landroid/graphics/Region;", "or", "plus", "times", "factor", "toRect", "toRectF", "toRegion", "transform", "m", "Landroid/graphics/Matrix;", "xor", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.graphics.RectKt */
/* loaded from: classes.dex */
public final class Rect {
    public static final int component1(android.graphics.Rect $this$component1) {
        return $this$component1.left;
    }

    public static final int component2(android.graphics.Rect $this$component2) {
        return $this$component2.top;
    }

    public static final int component3(android.graphics.Rect $this$component3) {
        return $this$component3.right;
    }

    public static final int component4(android.graphics.Rect $this$component4) {
        return $this$component4.bottom;
    }

    public static final float component1(RectF $this$component1) {
        return $this$component1.left;
    }

    public static final float component2(RectF $this$component2) {
        return $this$component2.top;
    }

    public static final float component3(RectF $this$component3) {
        return $this$component3.right;
    }

    public static final float component4(RectF $this$component4) {
        return $this$component4.bottom;
    }

    public static final android.graphics.Rect plus(android.graphics.Rect $this$plus, android.graphics.Rect r) {
        android.graphics.Rect $this$plus_u24lambda_u240 = new android.graphics.Rect($this$plus);
        $this$plus_u24lambda_u240.union(r);
        return $this$plus_u24lambda_u240;
    }

    public static final RectF plus(RectF $this$plus, RectF r) {
        RectF $this$plus_u24lambda_u241 = new RectF($this$plus);
        $this$plus_u24lambda_u241.union(r);
        return $this$plus_u24lambda_u241;
    }

    public static final android.graphics.Rect plus(android.graphics.Rect $this$plus, int xy) {
        android.graphics.Rect $this$plus_u24lambda_u242 = new android.graphics.Rect($this$plus);
        $this$plus_u24lambda_u242.offset(xy, xy);
        return $this$plus_u24lambda_u242;
    }

    public static final RectF plus(RectF $this$plus, float xy) {
        RectF $this$plus_u24lambda_u243 = new RectF($this$plus);
        $this$plus_u24lambda_u243.offset(xy, xy);
        return $this$plus_u24lambda_u243;
    }

    public static final android.graphics.Rect plus(android.graphics.Rect $this$plus, Point xy) {
        android.graphics.Rect $this$plus_u24lambda_u244 = new android.graphics.Rect($this$plus);
        $this$plus_u24lambda_u244.offset(xy.x, xy.y);
        return $this$plus_u24lambda_u244;
    }

    public static final RectF plus(RectF $this$plus, PointF xy) {
        RectF $this$plus_u24lambda_u245 = new RectF($this$plus);
        $this$plus_u24lambda_u245.offset(xy.x, xy.y);
        return $this$plus_u24lambda_u245;
    }

    public static final Region minus(android.graphics.Rect $this$minus, android.graphics.Rect r) {
        Region $this$minus_u24lambda_u246 = new Region($this$minus);
        $this$minus_u24lambda_u246.op(r, Region.Op.DIFFERENCE);
        return $this$minus_u24lambda_u246;
    }

    public static final Region minus(RectF $this$minus, RectF r) {
        android.graphics.Rect r$iv = new android.graphics.Rect();
        $this$minus.roundOut(r$iv);
        Region $this$minus_u24lambda_u247 = new Region(r$iv);
        android.graphics.Rect r$iv2 = new android.graphics.Rect();
        r.roundOut(r$iv2);
        $this$minus_u24lambda_u247.op(r$iv2, Region.Op.DIFFERENCE);
        return $this$minus_u24lambda_u247;
    }

    public static final android.graphics.Rect minus(android.graphics.Rect $this$minus, int xy) {
        android.graphics.Rect $this$minus_u24lambda_u248 = new android.graphics.Rect($this$minus);
        $this$minus_u24lambda_u248.offset(-xy, -xy);
        return $this$minus_u24lambda_u248;
    }

    public static final RectF minus(RectF $this$minus, float xy) {
        RectF $this$minus_u24lambda_u249 = new RectF($this$minus);
        $this$minus_u24lambda_u249.offset(-xy, -xy);
        return $this$minus_u24lambda_u249;
    }

    public static final android.graphics.Rect minus(android.graphics.Rect $this$minus, Point xy) {
        android.graphics.Rect $this$minus_u24lambda_u2410 = new android.graphics.Rect($this$minus);
        $this$minus_u24lambda_u2410.offset(-xy.x, -xy.y);
        return $this$minus_u24lambda_u2410;
    }

    public static final RectF minus(RectF $this$minus, PointF xy) {
        RectF $this$minus_u24lambda_u2411 = new RectF($this$minus);
        $this$minus_u24lambda_u2411.offset(-xy.x, -xy.y);
        return $this$minus_u24lambda_u2411;
    }

    public static final android.graphics.Rect times(android.graphics.Rect $this$times, int factor) {
        android.graphics.Rect $this$times_u24lambda_u2412 = new android.graphics.Rect($this$times);
        $this$times_u24lambda_u2412.top *= factor;
        $this$times_u24lambda_u2412.left *= factor;
        $this$times_u24lambda_u2412.right *= factor;
        $this$times_u24lambda_u2412.bottom *= factor;
        return $this$times_u24lambda_u2412;
    }

    public static final RectF times(RectF $this$times, int factor) {
        float factor$iv = factor;
        RectF $this$times_u24lambda_u2413$iv = new RectF($this$times);
        $this$times_u24lambda_u2413$iv.top *= factor$iv;
        $this$times_u24lambda_u2413$iv.left *= factor$iv;
        $this$times_u24lambda_u2413$iv.right *= factor$iv;
        $this$times_u24lambda_u2413$iv.bottom *= factor$iv;
        return $this$times_u24lambda_u2413$iv;
    }

    public static final RectF times(RectF $this$times, float factor) {
        RectF $this$times_u24lambda_u2413 = new RectF($this$times);
        $this$times_u24lambda_u2413.top *= factor;
        $this$times_u24lambda_u2413.left *= factor;
        $this$times_u24lambda_u2413.right *= factor;
        $this$times_u24lambda_u2413.bottom *= factor;
        return $this$times_u24lambda_u2413;
    }

    /* renamed from: or */
    public static final android.graphics.Rect m159or(android.graphics.Rect $this$or, android.graphics.Rect r) {
        android.graphics.Rect $this$plus_u24lambda_u240$iv = new android.graphics.Rect($this$or);
        $this$plus_u24lambda_u240$iv.union(r);
        return $this$plus_u24lambda_u240$iv;
    }

    /* renamed from: or */
    public static final RectF m158or(RectF $this$or, RectF r) {
        RectF $this$plus_u24lambda_u241$iv = new RectF($this$or);
        $this$plus_u24lambda_u241$iv.union(r);
        return $this$plus_u24lambda_u241$iv;
    }

    public static final android.graphics.Rect and(android.graphics.Rect $this$and, android.graphics.Rect r) {
        android.graphics.Rect $this$and_u24lambda_u2414 = new android.graphics.Rect($this$and);
        $this$and_u24lambda_u2414.intersect(r);
        return $this$and_u24lambda_u2414;
    }

    public static final RectF and(RectF $this$and, RectF r) {
        RectF $this$and_u24lambda_u2415 = new RectF($this$and);
        $this$and_u24lambda_u2415.intersect(r);
        return $this$and_u24lambda_u2415;
    }

    public static final Region xor(android.graphics.Rect $this$xor, android.graphics.Rect r) {
        Region $this$xor_u24lambda_u2416 = new Region($this$xor);
        $this$xor_u24lambda_u2416.op(r, Region.Op.XOR);
        return $this$xor_u24lambda_u2416;
    }

    public static final Region xor(RectF $this$xor, RectF r) {
        android.graphics.Rect r$iv = new android.graphics.Rect();
        $this$xor.roundOut(r$iv);
        Region $this$xor_u24lambda_u2417 = new Region(r$iv);
        android.graphics.Rect r$iv2 = new android.graphics.Rect();
        r.roundOut(r$iv2);
        $this$xor_u24lambda_u2417.op(r$iv2, Region.Op.XOR);
        return $this$xor_u24lambda_u2417;
    }

    public static final boolean contains(android.graphics.Rect $this$contains, Point p) {
        return $this$contains.contains(p.x, p.y);
    }

    public static final boolean contains(RectF $this$contains, PointF p) {
        return $this$contains.contains(p.x, p.y);
    }

    public static final RectF toRectF(android.graphics.Rect $this$toRectF) {
        return new RectF($this$toRectF);
    }

    public static final android.graphics.Rect toRect(RectF $this$toRect) {
        android.graphics.Rect r = new android.graphics.Rect();
        $this$toRect.roundOut(r);
        return r;
    }

    public static final Region toRegion(android.graphics.Rect $this$toRegion) {
        return new Region($this$toRegion);
    }

    public static final Region toRegion(RectF $this$toRegion) {
        android.graphics.Rect r$iv = new android.graphics.Rect();
        $this$toRegion.roundOut(r$iv);
        return new Region(r$iv);
    }

    public static final RectF transform(RectF $this$transform, Matrix m) {
        m.mapRect($this$transform);
        return $this$transform;
    }
}