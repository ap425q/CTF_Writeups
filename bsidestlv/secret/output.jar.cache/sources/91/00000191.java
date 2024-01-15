package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.graphics.ColorUtils;

/* loaded from: output.jar:androidx/appcompat/widget/AppCompatDrawableManager.class */
public final class AppCompatDrawableManager {
    private static final boolean DEBUG = false;
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static AppCompatDrawableManager INSTANCE;
    private static final String TAG = "AppCompatDrawableManag";
    private ResourceManagerInternal mResourceManager;

    public static AppCompatDrawableManager get() {
        AppCompatDrawableManager appCompatDrawableManager;
        synchronized (AppCompatDrawableManager.class) {
            try {
                if (INSTANCE == null) {
                    preload();
                }
                appCompatDrawableManager = INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        return appCompatDrawableManager;
    }

    public static PorterDuffColorFilter getPorterDuffColorFilter(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        synchronized (AppCompatDrawableManager.class) {
            try {
                porterDuffColorFilter = ResourceManagerInternal.getPorterDuffColorFilter(i, mode);
            } catch (Throwable th) {
                throw th;
            }
        }
        return porterDuffColorFilter;
    }

    public static void preload() {
        synchronized (AppCompatDrawableManager.class) {
            try {
                if (INSTANCE == null) {
                    AppCompatDrawableManager appCompatDrawableManager = new AppCompatDrawableManager();
                    INSTANCE = appCompatDrawableManager;
                    appCompatDrawableManager.mResourceManager = ResourceManagerInternal.get();
                    INSTANCE.mResourceManager.setHooks(new ResourceManagerInternal.ResourceManagerHooks() { // from class: androidx.appcompat.widget.AppCompatDrawableManager.1
                        private final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL = {R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
                        private final int[] TINT_COLOR_CONTROL_NORMAL = {R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
                        private final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED = {R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl, R.drawable.abc_text_select_handle_middle_mtrl, R.drawable.abc_text_select_handle_right_mtrl};
                        private final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
                        private final int[] TINT_COLOR_CONTROL_STATE_LIST = {R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
                        private final int[] TINT_CHECKABLE_BUTTON_LIST = {R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};

                        private boolean arrayContains(int[] iArr, int i) {
                            for (int i2 : iArr) {
                                if (i2 == i) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        private ColorStateList createBorderlessButtonColorStateList(Context context) {
                            return createButtonColorStateList(context, 0);
                        }

                        /* JADX WARN: Type inference failed for: r2v1, types: [int[], int[][]] */
                        private ColorStateList createButtonColorStateList(Context context, int i) {
                            int themeAttrColor = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlHighlight);
                            int disabledThemeAttrColor = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorButtonNormal);
                            int[] iArr = ThemeUtils.DISABLED_STATE_SET;
                            int[] iArr2 = ThemeUtils.PRESSED_STATE_SET;
                            int compositeColors = ColorUtils.compositeColors(themeAttrColor, i);
                            return new ColorStateList(new int[]{iArr, iArr2, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.EMPTY_STATE_SET}, new int[]{disabledThemeAttrColor, compositeColors, ColorUtils.compositeColors(themeAttrColor, i), i});
                        }

                        private ColorStateList createColoredButtonColorStateList(Context context) {
                            return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R.attr.colorAccent));
                        }

                        private ColorStateList createDefaultButtonColorStateList(Context context) {
                            return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R.attr.colorButtonNormal));
                        }

                        /* JADX WARN: Type inference failed for: r0v1, types: [int[], int[][]] */
                        private ColorStateList createSwitchThumbColorStateList(Context context) {
                            ?? r0 = new int[3];
                            int[] iArr = new int[3];
                            ColorStateList themeAttrColorStateList = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorSwitchThumbNormal);
                            if (themeAttrColorStateList == null || !themeAttrColorStateList.isStateful()) {
                                r0[0] = ThemeUtils.DISABLED_STATE_SET;
                                iArr[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
                                r0[1] = ThemeUtils.CHECKED_STATE_SET;
                                iArr[1] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
                                r0[2] = ThemeUtils.EMPTY_STATE_SET;
                                iArr[2] = ThemeUtils.getThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
                            } else {
                                int[] iArr2 = ThemeUtils.DISABLED_STATE_SET;
                                r0[0] = iArr2;
                                iArr[0] = themeAttrColorStateList.getColorForState(iArr2, 0);
                                r0[1] = ThemeUtils.CHECKED_STATE_SET;
                                iArr[1] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
                                r0[2] = ThemeUtils.EMPTY_STATE_SET;
                                iArr[2] = themeAttrColorStateList.getDefaultColor();
                            }
                            return new ColorStateList(r0, iArr);
                        }

                        private LayerDrawable getRatingBarLayerDrawable(ResourceManagerInternal resourceManagerInternal, Context context, int i) {
                            BitmapDrawable bitmapDrawable;
                            BitmapDrawable bitmapDrawable2;
                            BitmapDrawable bitmapDrawable3;
                            int dimensionPixelSize = context.getResources().getDimensionPixelSize(i);
                            Drawable drawable = resourceManagerInternal.getDrawable(context, R.drawable.abc_star_black_48dp);
                            Drawable drawable2 = resourceManagerInternal.getDrawable(context, R.drawable.abc_star_half_black_48dp);
                            if ((drawable instanceof BitmapDrawable) && drawable.getIntrinsicWidth() == dimensionPixelSize && drawable.getIntrinsicHeight() == dimensionPixelSize) {
                                bitmapDrawable = (BitmapDrawable) drawable;
                                bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
                            } else {
                                Bitmap createBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                                Canvas canvas = new Canvas(createBitmap);
                                drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                                drawable.draw(canvas);
                                bitmapDrawable = new BitmapDrawable(createBitmap);
                                bitmapDrawable2 = new BitmapDrawable(createBitmap);
                            }
                            bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
                            if ((drawable2 instanceof BitmapDrawable) && drawable2.getIntrinsicWidth() == dimensionPixelSize && drawable2.getIntrinsicHeight() == dimensionPixelSize) {
                                bitmapDrawable3 = (BitmapDrawable) drawable2;
                            } else {
                                Bitmap createBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                                Canvas canvas2 = new Canvas(createBitmap2);
                                drawable2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                                drawable2.draw(canvas2);
                                bitmapDrawable3 = new BitmapDrawable(createBitmap2);
                            }
                            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
                            layerDrawable.setId(0, 16908288);
                            layerDrawable.setId(1, 16908303);
                            layerDrawable.setId(2, 16908301);
                            return layerDrawable;
                        }

                        private void setPorterDuffColorFilter(Drawable drawable, int i, PorterDuff.Mode mode) {
                            Drawable drawable2 = drawable;
                            if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
                                drawable2 = drawable.mutate();
                            }
                            PorterDuff.Mode mode2 = mode;
                            if (mode == null) {
                                mode2 = AppCompatDrawableManager.DEFAULT_MODE;
                            }
                            drawable2.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(i, mode2));
                        }

                        @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                        public Drawable createDrawableFor(ResourceManagerInternal resourceManagerInternal, Context context, int i) {
                            if (i == R.drawable.abc_cab_background_top_material) {
                                return new LayerDrawable(new Drawable[]{resourceManagerInternal.getDrawable(context, R.drawable.abc_cab_background_internal_bg), resourceManagerInternal.getDrawable(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
                            }
                            if (i == R.drawable.abc_ratingbar_material) {
                                return getRatingBarLayerDrawable(resourceManagerInternal, context, R.dimen.abc_star_big);
                            }
                            if (i == R.drawable.abc_ratingbar_indicator_material) {
                                return getRatingBarLayerDrawable(resourceManagerInternal, context, R.dimen.abc_star_medium);
                            }
                            if (i == R.drawable.abc_ratingbar_small_material) {
                                return getRatingBarLayerDrawable(resourceManagerInternal, context, R.dimen.abc_star_small);
                            }
                            return null;
                        }

                        @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                        public ColorStateList getTintListForDrawableRes(Context context, int i) {
                            if (i == R.drawable.abc_edit_text_material) {
                                return AppCompatResources.getColorStateList(context, R.color.abc_tint_edittext);
                            }
                            if (i == R.drawable.abc_switch_track_mtrl_alpha) {
                                return AppCompatResources.getColorStateList(context, R.color.abc_tint_switch_track);
                            }
                            if (i == R.drawable.abc_switch_thumb_material) {
                                return createSwitchThumbColorStateList(context);
                            }
                            if (i == R.drawable.abc_btn_default_mtrl_shape) {
                                return createDefaultButtonColorStateList(context);
                            }
                            if (i == R.drawable.abc_btn_borderless_material) {
                                return createBorderlessButtonColorStateList(context);
                            }
                            if (i == R.drawable.abc_btn_colored_material) {
                                return createColoredButtonColorStateList(context);
                            }
                            if (i == R.drawable.abc_spinner_mtrl_am_alpha || i == R.drawable.abc_spinner_textfield_background_material) {
                                return AppCompatResources.getColorStateList(context, R.color.abc_tint_spinner);
                            }
                            if (arrayContains(this.TINT_COLOR_CONTROL_NORMAL, i)) {
                                return ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorControlNormal);
                            }
                            if (arrayContains(this.TINT_COLOR_CONTROL_STATE_LIST, i)) {
                                return AppCompatResources.getColorStateList(context, R.color.abc_tint_default);
                            }
                            if (arrayContains(this.TINT_CHECKABLE_BUTTON_LIST, i)) {
                                return AppCompatResources.getColorStateList(context, R.color.abc_tint_btn_checkable);
                            }
                            if (i == R.drawable.abc_seekbar_thumb_material) {
                                return AppCompatResources.getColorStateList(context, R.color.abc_tint_seek_thumb);
                            }
                            return null;
                        }

                        @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                        public PorterDuff.Mode getTintModeForDrawableRes(int i) {
                            return i == R.drawable.abc_switch_thumb_material ? PorterDuff.Mode.MULTIPLY : null;
                        }

                        @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                        public boolean tintDrawable(Context context, int i, Drawable drawable) {
                            if (i == R.drawable.abc_seekbar_track_material) {
                                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                                setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                                setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                                setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                                return true;
                            } else if (i == R.drawable.abc_ratingbar_material || i == R.drawable.abc_ratingbar_indicator_material || i == R.drawable.abc_ratingbar_small_material) {
                                LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
                                setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                                setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                                setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                                return true;
                            } else {
                                return false;
                            }
                        }

                        /* JADX WARN: Removed duplicated region for block: B:23:0x008a  */
                        /* JADX WARN: Removed duplicated region for block: B:31:0x00b7 A[RETURN] */
                        @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public boolean tintDrawableUsingColorFilter(android.content.Context r5, int r6, android.graphics.drawable.Drawable r7) {
                            /*
                                r4 = this;
                                android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.AppCompatDrawableManager.access$000()
                                r8 = r0
                                r0 = r8
                                r9 = r0
                                r0 = r4
                                r1 = r4
                                int[] r1 = r1.COLORFILTER_TINT_COLOR_CONTROL_NORMAL
                                r2 = r6
                                boolean r0 = r0.arrayContains(r1, r2)
                                if (r0 == 0) goto L26
                                int r0 = androidx.appcompat.R.attr.colorControlNormal
                                r10 = r0
                            L1a:
                                r0 = 1
                                r11 = r0
                            L1d:
                                r0 = r8
                                r9 = r0
                                r0 = -1
                                r6 = r0
                                goto L85
                            L26:
                                r0 = r4
                                r1 = r4
                                int[] r1 = r1.COLORFILTER_COLOR_CONTROL_ACTIVATED
                                r2 = r6
                                boolean r0 = r0.arrayContains(r1, r2)
                                if (r0 == 0) goto L3a
                                int r0 = androidx.appcompat.R.attr.colorControlActivated
                                r10 = r0
                                goto L1a
                            L3a:
                                r0 = r4
                                r1 = r4
                                int[] r1 = r1.COLORFILTER_COLOR_BACKGROUND_MULTIPLY
                                r2 = r6
                                boolean r0 = r0.arrayContains(r1, r2)
                                if (r0 == 0) goto L58
                                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                                r9 = r0
                            L4b:
                                r0 = -1
                                r6 = r0
                                r0 = 16842801(0x1010031, float:2.3693695E-38)
                                r10 = r0
                                r0 = 1
                                r11 = r0
                                goto L85
                            L58:
                                r0 = r6
                                int r1 = androidx.appcompat.R.drawable.abc_list_divider_mtrl_alpha
                                if (r0 != r1) goto L71
                                r0 = 1109603123(0x42233333, float:40.8)
                                int r0 = java.lang.Math.round(r0)
                                r6 = r0
                                r0 = 1
                                r11 = r0
                                r0 = 16842800(0x1010030, float:2.3693693E-38)
                                r10 = r0
                                goto L85
                            L71:
                                r0 = r6
                                int r1 = androidx.appcompat.R.drawable.abc_dialog_material_background
                                if (r0 != r1) goto L7b
                                goto L4b
                            L7b:
                                r0 = 0
                                r10 = r0
                                r0 = r10
                                r11 = r0
                                goto L1d
                            L85:
                                r0 = r11
                                if (r0 == 0) goto Lb7
                                r0 = r7
                                r8 = r0
                                r0 = r7
                                boolean r0 = androidx.appcompat.widget.DrawableUtils.canSafelyMutateDrawable(r0)
                                if (r0 == 0) goto L9a
                                r0 = r7
                                android.graphics.drawable.Drawable r0 = r0.mutate()
                                r8 = r0
                            L9a:
                                r0 = r8
                                r1 = r5
                                r2 = r10
                                int r1 = androidx.appcompat.widget.ThemeUtils.getThemeAttrColor(r1, r2)
                                r2 = r9
                                android.graphics.PorterDuffColorFilter r1 = androidx.appcompat.widget.AppCompatDrawableManager.getPorterDuffColorFilter(r1, r2)
                                r0.setColorFilter(r1)
                                r0 = r6
                                r1 = -1
                                if (r0 == r1) goto Lb5
                                r0 = r8
                                r1 = r6
                                r0.setAlpha(r1)
                            Lb5:
                                r0 = 1
                                return r0
                            Lb7:
                                r0 = 0
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatDrawableManager.AnonymousClass1.tintDrawableUsingColorFilter(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
                        }
                    });
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void tintDrawable(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        ResourceManagerInternal.tintDrawable(drawable, tintInfo, iArr);
    }

    public Drawable getDrawable(Context context, int i) {
        Drawable drawable;
        synchronized (this) {
            drawable = this.mResourceManager.getDrawable(context, i);
        }
        return drawable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Drawable getDrawable(Context context, int i, boolean z) {
        Drawable drawable;
        synchronized (this) {
            drawable = this.mResourceManager.getDrawable(context, i, z);
        }
        return drawable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList getTintList(Context context, int i) {
        ColorStateList tintList;
        synchronized (this) {
            tintList = this.mResourceManager.getTintList(context, i);
        }
        return tintList;
    }

    public void onConfigurationChanged(Context context) {
        synchronized (this) {
            this.mResourceManager.onConfigurationChanged(context);
        }
    }

    Drawable onDrawableLoadedFromResources(Context context, VectorEnabledTintResources vectorEnabledTintResources, int i) {
        Drawable onDrawableLoadedFromResources;
        synchronized (this) {
            onDrawableLoadedFromResources = this.mResourceManager.onDrawableLoadedFromResources(context, vectorEnabledTintResources, i);
        }
        return onDrawableLoadedFromResources;
    }

    boolean tintDrawableUsingColorFilter(Context context, int i, Drawable drawable) {
        return this.mResourceManager.tintDrawableUsingColorFilter(context, i, drawable);
    }
}