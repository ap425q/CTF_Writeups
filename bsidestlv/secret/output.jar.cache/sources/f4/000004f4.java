package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import androidx.core.util.Pair;

/* loaded from: output.jar:androidx/core/graphics/PaintCompat.class */
public final class PaintCompat {
    private static final String EM_STRING = "m";
    private static final String TOFU_STRING = "��";
    private static final ThreadLocal<Pair<Rect, Rect>> sRectThreadLocal = new ThreadLocal<>();

    private PaintCompat() {
    }

    public static boolean hasGlyph(Paint paint, String str) {
        return paint.hasGlyph(str);
    }

    private static Pair<Rect, Rect> obtainEmptyRects() {
        ThreadLocal<Pair<Rect, Rect>> threadLocal = sRectThreadLocal;
        Pair<Rect, Rect> pair = threadLocal.get();
        if (pair == null) {
            pair = new Pair<>(new Rect(), new Rect());
            threadLocal.set(pair);
        } else {
            pair.first.setEmpty();
            pair.second.setEmpty();
        }
        return pair;
    }

    public static boolean setBlendMode(Paint paint, BlendModeCompat blendModeCompat) {
        boolean z = true;
        if (Build.VERSION.SDK_INT >= 29) {
            BlendMode blendMode = null;
            if (blendModeCompat != null) {
                blendMode = BlendModeUtils.obtainBlendModeFromCompat(blendModeCompat);
            }
            paint.setBlendMode(blendMode);
            return true;
        } else if (blendModeCompat == null) {
            paint.setXfermode(null);
            return true;
        } else {
            PorterDuff.Mode obtainPorterDuffFromCompat = BlendModeUtils.obtainPorterDuffFromCompat(blendModeCompat);
            PorterDuffXfermode porterDuffXfermode = null;
            if (obtainPorterDuffFromCompat != null) {
                porterDuffXfermode = new PorterDuffXfermode(obtainPorterDuffFromCompat);
            }
            paint.setXfermode(porterDuffXfermode);
            if (obtainPorterDuffFromCompat == null) {
                z = false;
            }
            return z;
        }
    }
}