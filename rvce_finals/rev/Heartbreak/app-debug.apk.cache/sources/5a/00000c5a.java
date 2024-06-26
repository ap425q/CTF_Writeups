package androidx.transition;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
class ImageViewUtils {
    private static Field sDrawMatrixField;
    private static boolean sDrawMatrixFieldFetched;
    private static boolean sTryHiddenAnimateTransform = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void animateTransform(ImageView view, Matrix matrix) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.animateTransform(view, matrix);
        } else if (matrix == null) {
            Drawable drawable = view.getDrawable();
            if (drawable != null) {
                int vwidth = (view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight();
                int vheight = (view.getHeight() - view.getPaddingTop()) - view.getPaddingBottom();
                drawable.setBounds(0, 0, vwidth, vheight);
                view.invalidate();
            }
        } else {
            hiddenAnimateTransform(view, matrix);
        }
    }

    private static void hiddenAnimateTransform(ImageView view, Matrix matrix) {
        if (sTryHiddenAnimateTransform) {
            try {
                Api29Impl.animateTransform(view, matrix);
            } catch (NoSuchMethodError e) {
                sTryHiddenAnimateTransform = false;
            }
        }
    }

    private static void fetchDrawMatrixField() {
        if (!sDrawMatrixFieldFetched) {
            try {
                sDrawMatrixField = ImageView.class.getDeclaredField("mDrawMatrix");
                sDrawMatrixField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            sDrawMatrixFieldFetched = true;
        }
    }

    private ImageViewUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Api29Impl {
        private Api29Impl() {
        }

        static void animateTransform(ImageView imageView, Matrix matrix) {
            imageView.animateTransform(matrix);
        }
    }
}