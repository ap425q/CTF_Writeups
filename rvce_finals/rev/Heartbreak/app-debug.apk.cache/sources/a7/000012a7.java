package kotlin;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m29d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004¨\u0006\t"}, m28d2 = {"lazy", "Lkotlin/Lazy;", "T", "initializer", "Lkotlin/Function0;", "lock", "", "mode", "Lkotlin/LazyThreadSafetyMode;", "kotlin-stdlib"}, m27k = 5, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX, m23xs = "kotlin/LazyKt")
/* renamed from: kotlin.LazyKt__LazyJVMKt */
/* loaded from: classes.dex */
public class LazyJVM {

    /* compiled from: LazyJVM.kt */
    @Metadata(m27k = 3, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: kotlin.LazyKt__LazyJVMKt$WhenMappings */
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LazyThreadSafetyMode.values().length];
            try {
                iArr[LazyThreadSafetyMode.SYNCHRONIZED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LazyThreadSafetyMode.PUBLICATION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LazyThreadSafetyMode.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final <T> Lazy<T> lazy(Functions<? extends T> initializer) {
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        return new SynchronizedLazyImpl(initializer, null, 2, null);
    }

    public static final <T> Lazy<T> lazy(LazyThreadSafetyMode mode, Functions<? extends T> initializer) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        switch (WhenMappings.$EnumSwitchMapping$0[mode.ordinal()]) {
            case 1:
                return new SynchronizedLazyImpl(initializer, null, 2, null);
            case 2:
                return new SafePublicationLazyImpl(initializer);
            case 3:
                return new UnsafeLazyImpl(initializer);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final <T> Lazy<T> lazy(Object lock, Functions<? extends T> initializer) {
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        return new SynchronizedLazyImpl(initializer, lock);
    }
}