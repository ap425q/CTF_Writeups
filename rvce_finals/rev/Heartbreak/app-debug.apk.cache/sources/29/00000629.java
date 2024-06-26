package androidx.core.graphics;

import android.graphics.Matrix;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(m29d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006H\u0086\b¨\u0006\u0007"}, m28d2 = {"transform", "", "Landroid/graphics/Shader;", "block", "Lkotlin/Function1;", "Landroid/graphics/Matrix;", "Lkotlin/ExtensionFunctionType;", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.graphics.ShaderKt */
/* loaded from: classes.dex */
public final class Shader {
    public static final void transform(android.graphics.Shader $this$transform, Function1<? super Matrix, Unit> function1) {
        Matrix matrix = new Matrix();
        $this$transform.getLocalMatrix(matrix);
        function1.invoke(matrix);
        $this$transform.setLocalMatrix(matrix);
    }
}