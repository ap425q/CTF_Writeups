package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;

/* loaded from: output.jar:androidx/core/graphics/BlendModeColorFilterCompat.class */
public class BlendModeColorFilterCompat {
    private BlendModeColorFilterCompat() {
    }

    public static ColorFilter createBlendModeColorFilterCompat(int i, BlendModeCompat blendModeCompat) {
        if (Build.VERSION.SDK_INT >= 29) {
            BlendMode obtainBlendModeFromCompat = BlendModeUtils.obtainBlendModeFromCompat(blendModeCompat);
            BlendModeColorFilter blendModeColorFilter = null;
            if (obtainBlendModeFromCompat != null) {
                blendModeColorFilter = new BlendModeColorFilter(i, obtainBlendModeFromCompat);
            }
            return blendModeColorFilter;
        }
        PorterDuff.Mode obtainPorterDuffFromCompat = BlendModeUtils.obtainPorterDuffFromCompat(blendModeCompat);
        PorterDuffColorFilter porterDuffColorFilter = null;
        if (obtainPorterDuffFromCompat != null) {
            porterDuffColorFilter = new PorterDuffColorFilter(i, obtainPorterDuffFromCompat);
        }
        return porterDuffColorFilter;
    }
}