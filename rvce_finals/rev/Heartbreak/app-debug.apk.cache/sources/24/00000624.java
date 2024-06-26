package androidx.core.graphics;

import android.graphics.PointF;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

@Metadata(m29d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\f\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\n\u001a\r\u0010\u0000\u001a\u00020\u0003*\u00020\u0004H\u0086\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0086\n\u001a\r\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0086\n\u001a\u0015\u0010\b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\b\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\b\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\n\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\u000b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0086\n\u001a\u0015\u0010\u000b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\n\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\f\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0003H\u0086\n\u001a\r\u0010\r\u001a\u00020\u0002*\u00020\u0004H\u0086\b\u001a\r\u0010\u000e\u001a\u00020\u0004*\u00020\u0002H\u0086\b\u001a\r\u0010\u000f\u001a\u00020\u0002*\u00020\u0002H\u0086\n\u001a\r\u0010\u000f\u001a\u00020\u0004*\u00020\u0004H\u0086\n¨\u0006\u0010"}, m28d2 = {"component1", "", "Landroid/graphics/Point;", "", "Landroid/graphics/PointF;", "component2", "div", "scalar", "minus", "p", "xy", "plus", "times", "toPoint", "toPointF", "unaryMinus", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.graphics.PointKt */
/* loaded from: classes.dex */
public final class Point {
    public static final int component1(android.graphics.Point $this$component1) {
        return $this$component1.x;
    }

    public static final int component2(android.graphics.Point $this$component2) {
        return $this$component2.y;
    }

    public static final float component1(PointF $this$component1) {
        return $this$component1.x;
    }

    public static final float component2(PointF $this$component2) {
        return $this$component2.y;
    }

    public static final android.graphics.Point plus(android.graphics.Point $this$plus, android.graphics.Point p) {
        android.graphics.Point $this$plus_u24lambda_u240 = new android.graphics.Point($this$plus.x, $this$plus.y);
        $this$plus_u24lambda_u240.offset(p.x, p.y);
        return $this$plus_u24lambda_u240;
    }

    public static final PointF plus(PointF $this$plus, PointF p) {
        PointF $this$plus_u24lambda_u241 = new PointF($this$plus.x, $this$plus.y);
        $this$plus_u24lambda_u241.offset(p.x, p.y);
        return $this$plus_u24lambda_u241;
    }

    public static final android.graphics.Point plus(android.graphics.Point $this$plus, int xy) {
        android.graphics.Point $this$plus_u24lambda_u242 = new android.graphics.Point($this$plus.x, $this$plus.y);
        $this$plus_u24lambda_u242.offset(xy, xy);
        return $this$plus_u24lambda_u242;
    }

    public static final PointF plus(PointF $this$plus, float xy) {
        PointF $this$plus_u24lambda_u243 = new PointF($this$plus.x, $this$plus.y);
        $this$plus_u24lambda_u243.offset(xy, xy);
        return $this$plus_u24lambda_u243;
    }

    public static final android.graphics.Point minus(android.graphics.Point $this$minus, android.graphics.Point p) {
        android.graphics.Point $this$minus_u24lambda_u244 = new android.graphics.Point($this$minus.x, $this$minus.y);
        $this$minus_u24lambda_u244.offset(-p.x, -p.y);
        return $this$minus_u24lambda_u244;
    }

    public static final PointF minus(PointF $this$minus, PointF p) {
        PointF $this$minus_u24lambda_u245 = new PointF($this$minus.x, $this$minus.y);
        $this$minus_u24lambda_u245.offset(-p.x, -p.y);
        return $this$minus_u24lambda_u245;
    }

    public static final android.graphics.Point minus(android.graphics.Point $this$minus, int xy) {
        android.graphics.Point $this$minus_u24lambda_u246 = new android.graphics.Point($this$minus.x, $this$minus.y);
        $this$minus_u24lambda_u246.offset(-xy, -xy);
        return $this$minus_u24lambda_u246;
    }

    public static final PointF minus(PointF $this$minus, float xy) {
        PointF $this$minus_u24lambda_u247 = new PointF($this$minus.x, $this$minus.y);
        $this$minus_u24lambda_u247.offset(-xy, -xy);
        return $this$minus_u24lambda_u247;
    }

    public static final android.graphics.Point unaryMinus(android.graphics.Point $this$unaryMinus) {
        return new android.graphics.Point(-$this$unaryMinus.x, -$this$unaryMinus.y);
    }

    public static final PointF unaryMinus(PointF $this$unaryMinus) {
        return new PointF(-$this$unaryMinus.x, -$this$unaryMinus.y);
    }

    public static final android.graphics.Point times(android.graphics.Point $this$times, float scalar) {
        return new android.graphics.Point(Math.round($this$times.x * scalar), Math.round($this$times.y * scalar));
    }

    public static final PointF times(PointF $this$times, float scalar) {
        return new PointF($this$times.x * scalar, $this$times.y * scalar);
    }

    public static final android.graphics.Point div(android.graphics.Point $this$div, float scalar) {
        return new android.graphics.Point(Math.round($this$div.x / scalar), Math.round($this$div.y / scalar));
    }

    public static final PointF div(PointF $this$div, float scalar) {
        return new PointF($this$div.x / scalar, $this$div.y / scalar);
    }

    public static final PointF toPointF(android.graphics.Point $this$toPointF) {
        return new PointF($this$toPointF);
    }

    public static final android.graphics.Point toPoint(PointF $this$toPoint) {
        return new android.graphics.Point((int) $this$toPoint.x, (int) $this$toPoint.y);
    }
}