package kotlin.jvm.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.ExceptionsH;
import kotlin.Metadata;
import kotlin.reflect.KDeclarationContainer;

@Metadata(m29d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m28d2 = {"Lkotlin/jvm/internal/LocalVariableReference;", "Lkotlin/jvm/internal/PropertyReference0;", "()V", "get", "", "getOwner", "Lkotlin/reflect/KDeclarationContainer;", "kotlin-stdlib"}, m27k = 1, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: kotlin.jvm.internal.LocalVariableReference */
/* loaded from: classes.dex */
public class localVariableReferences extends PropertyReference0 {
    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        LocalVariableReferencesKt.notSupportedError();
        throw new ExceptionsH();
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        LocalVariableReferencesKt.notSupportedError();
        throw new ExceptionsH();
    }
}