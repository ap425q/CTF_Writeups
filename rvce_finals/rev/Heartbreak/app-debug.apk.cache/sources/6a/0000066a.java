package androidx.core.location;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

@Metadata(m29d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\n\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0086\n¨\u0006\u0004"}, m28d2 = {"component1", "", "Landroid/location/Location;", "component2", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.location.LocationKt */
/* loaded from: classes.dex */
public final class Location {
    public static final double component1(android.location.Location $this$component1) {
        return $this$component1.getLatitude();
    }

    public static final double component2(android.location.Location $this$component2) {
        return $this$component2.getLongitude();
    }
}