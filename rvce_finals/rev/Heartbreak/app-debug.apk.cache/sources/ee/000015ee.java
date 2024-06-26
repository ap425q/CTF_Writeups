package kotlin.text;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMarkers;

/* compiled from: MatchResult.kt */
@Metadata(m29d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0005H¦\u0002¨\u0006\u0006"}, m28d2 = {"Lkotlin/text/MatchGroupCollection;", "", "Lkotlin/text/MatchGroup;", "get", "index", "", "kotlin-stdlib"}, m27k = 1, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public interface MatchGroupCollection extends Collection<MatchGroup>, KMarkers {
    MatchGroup get(int i);
}