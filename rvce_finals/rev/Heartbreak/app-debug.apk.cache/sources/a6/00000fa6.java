package com.google.android.material.elevation;

import android.content.Context;
import com.google.android.material.C0558R;
import com.google.android.material.color.MaterialColors;

/* loaded from: classes.dex */
public enum SurfaceColors {
    SURFACE_0(C0558R.dimen.m3_sys_elevation_level0),
    SURFACE_1(C0558R.dimen.m3_sys_elevation_level1),
    SURFACE_2(C0558R.dimen.m3_sys_elevation_level2),
    SURFACE_3(C0558R.dimen.m3_sys_elevation_level3),
    SURFACE_4(C0558R.dimen.m3_sys_elevation_level4),
    SURFACE_5(C0558R.dimen.m3_sys_elevation_level5);
    
    private final int elevationResId;

    SurfaceColors(int elevationResId) {
        this.elevationResId = elevationResId;
    }

    public int getColor(Context context) {
        return getColorForElevation(context, context.getResources().getDimension(this.elevationResId));
    }

    public static int getColorForElevation(Context context, float elevation) {
        return new ElevationOverlayProvider(context).compositeOverlay(MaterialColors.getColor(context, C0558R.attr.colorSurface, 0), elevation);
    }
}