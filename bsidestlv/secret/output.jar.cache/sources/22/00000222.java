package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: output.jar:androidx/appcompat/widget/TintContextWrapper.class */
public class TintContextWrapper extends ContextWrapper {
    private static final Object CACHE_LOCK = new Object();
    private static ArrayList<WeakReference<TintContextWrapper>> sCache;
    private final Resources mResources;
    private final Resources.Theme mTheme;

    private TintContextWrapper(Context context) {
        super(context);
        if (!VectorEnabledTintResources.shouldBeUsed()) {
            this.mResources = new TintResources(this, context.getResources());
            this.mTheme = null;
            return;
        }
        VectorEnabledTintResources vectorEnabledTintResources = new VectorEnabledTintResources(this, context.getResources());
        this.mResources = vectorEnabledTintResources;
        Resources.Theme newTheme = vectorEnabledTintResources.newTheme();
        this.mTheme = newTheme;
        newTheme.setTo(context.getTheme());
    }

    private static boolean shouldWrap(Context context) {
        boolean z = false;
        if (!(context instanceof TintContextWrapper)) {
            z = false;
            if (!(context.getResources() instanceof TintResources)) {
                if (context.getResources() instanceof VectorEnabledTintResources) {
                    z = false;
                } else {
                    z = false;
                    if (VectorEnabledTintResources.shouldBeUsed()) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public static Context wrap(Context context) {
        if (shouldWrap(context)) {
            synchronized (CACHE_LOCK) {
                ArrayList<WeakReference<TintContextWrapper>> arrayList = sCache;
                if (arrayList == null) {
                    sCache = new ArrayList<>();
                } else {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        WeakReference<TintContextWrapper> weakReference = sCache.get(size);
                        if (weakReference == null || weakReference.get() == null) {
                            sCache.remove(size);
                        }
                    }
                    for (int size2 = sCache.size() - 1; size2 >= 0; size2--) {
                        WeakReference<TintContextWrapper> weakReference2 = sCache.get(size2);
                        TintContextWrapper tintContextWrapper = weakReference2 != null ? weakReference2.get() : null;
                        if (tintContextWrapper != null && tintContextWrapper.getBaseContext() == context) {
                            return tintContextWrapper;
                        }
                    }
                }
                TintContextWrapper tintContextWrapper2 = new TintContextWrapper(context);
                sCache.add(new WeakReference<>(tintContextWrapper2));
                return tintContextWrapper2;
            }
        }
        return context;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.mResources.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.mResources;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.mTheme;
        Resources.Theme theme2 = theme;
        if (theme == null) {
            theme2 = super.getTheme();
        }
        return theme2;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.mTheme;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}