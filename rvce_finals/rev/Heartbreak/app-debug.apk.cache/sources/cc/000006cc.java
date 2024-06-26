package androidx.core.p003os;

import android.os.PersistableBundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Tuples;

/* compiled from: PersistableBundle.kt */
@Metadata(m29d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0007\u001a=\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0007¢\u0006\u0002\u0010\u0007\u001a\u001a\u0010\b\u001a\u00020\u0001*\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\tH\u0007¨\u0006\n"}, m28d2 = {"persistableBundleOf", "Landroid/os/PersistableBundle;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/PersistableBundle;", "toPersistableBundle", "", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.os.PersistableBundleKt */
/* loaded from: classes.dex */
public final class PersistableBundleKt {
    public static final PersistableBundle persistableBundleOf(Tuples<String, ? extends Object>... tuplesArr) {
        PersistableBundle persistableBundle = PersistableBundle.createPersistableBundle(tuplesArr.length);
        for (Tuples<String, ? extends Object> tuples : tuplesArr) {
            String key = tuples.component1();
            Object value = tuples.component2();
            PersistableBundle.putValue(persistableBundle, key, value);
        }
        return persistableBundle;
    }

    public static final PersistableBundle persistableBundleOf() {
        return PersistableBundle.createPersistableBundle(0);
    }

    public static final PersistableBundle toPersistableBundle(Map<String, ? extends Object> map) {
        PersistableBundle persistableBundle = PersistableBundle.createPersistableBundle(map.size());
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            PersistableBundle.putValue(persistableBundle, key, value);
        }
        return persistableBundle;
    }
}