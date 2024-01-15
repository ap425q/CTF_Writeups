package com.google.android.material.textfield;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.Filterable;
import android.widget.ListAdapter;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.ListPopupWindow;
import com.google.android.material.R;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: output.jar:com/google/android/material/textfield/MaterialAutoCompleteTextView.class */
public class MaterialAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    private static final int MAX_ITEMS_MEASURED = 15;
    private final AccessibilityManager accessibilityManager;
    private final ListPopupWindow modalListPopup;
    private final Rect tempRect;

    public MaterialAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.autoCompleteTextViewStyle);
    }

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, 0), attributeSet, i);
        this.tempRect = new Rect();
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R.styleable.MaterialAutoCompleteTextView, i, R.style.Widget_AppCompat_AutoCompleteTextView, new int[0]);
        if (obtainStyledAttributes.hasValue(R.styleable.MaterialAutoCompleteTextView_android_inputType) && obtainStyledAttributes.getInt(R.styleable.MaterialAutoCompleteTextView_android_inputType, 0) == 0) {
            setKeyListener(null);
        }
        this.accessibilityManager = (AccessibilityManager) context2.getSystemService("accessibility");
        ListPopupWindow listPopupWindow = new ListPopupWindow(context2);
        this.modalListPopup = listPopupWindow;
        listPopupWindow.setModal(true);
        listPopupWindow.setAnchorView(this);
        listPopupWindow.setInputMethodMode(2);
        listPopupWindow.setAdapter(getAdapter());
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.google.android.material.textfield.MaterialAutoCompleteTextView.1
            /* JADX WARN: Code restructure failed: missing block: B:11:0x003c, code lost:
                if (r10 < 0) goto L11;
             */
            @Override // android.widget.AdapterView.OnItemClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onItemClick(android.widget.AdapterView<?> r8, android.view.View r9, int r10, long r11) {
                /*
                    r7 = this;
                    r0 = r10
                    if (r0 >= 0) goto L12
                    r0 = r7
                    com.google.android.material.textfield.MaterialAutoCompleteTextView r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.this
                    androidx.appcompat.widget.ListPopupWindow r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.access$000(r0)
                    java.lang.Object r0 = r0.getSelectedItem()
                    r8 = r0
                    goto L20
                L12:
                    r0 = r7
                    com.google.android.material.textfield.MaterialAutoCompleteTextView r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.this
                    android.widget.ListAdapter r0 = r0.getAdapter()
                    r1 = r10
                    java.lang.Object r0 = r0.getItem(r1)
                    r8 = r0
                L20:
                    r0 = r7
                    com.google.android.material.textfield.MaterialAutoCompleteTextView r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.this
                    r1 = r8
                    com.google.android.material.textfield.MaterialAutoCompleteTextView.access$100(r0, r1)
                    r0 = r7
                    com.google.android.material.textfield.MaterialAutoCompleteTextView r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.this
                    android.widget.AdapterView$OnItemClickListener r0 = r0.getOnItemClickListener()
                    r8 = r0
                    r0 = r8
                    if (r0 == 0) goto L77
                    r0 = r9
                    if (r0 == 0) goto L3f
                    r0 = r10
                    r13 = r0
                    r0 = r10
                    if (r0 >= 0) goto L62
                L3f:
                    r0 = r7
                    com.google.android.material.textfield.MaterialAutoCompleteTextView r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.this
                    androidx.appcompat.widget.ListPopupWindow r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.access$000(r0)
                    android.view.View r0 = r0.getSelectedView()
                    r9 = r0
                    r0 = r7
                    com.google.android.material.textfield.MaterialAutoCompleteTextView r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.this
                    androidx.appcompat.widget.ListPopupWindow r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.access$000(r0)
                    int r0 = r0.getSelectedItemPosition()
                    r13 = r0
                    r0 = r7
                    com.google.android.material.textfield.MaterialAutoCompleteTextView r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.this
                    androidx.appcompat.widget.ListPopupWindow r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.access$000(r0)
                    long r0 = r0.getSelectedItemId()
                    r11 = r0
                L62:
                    r0 = r8
                    r1 = r7
                    com.google.android.material.textfield.MaterialAutoCompleteTextView r1 = com.google.android.material.textfield.MaterialAutoCompleteTextView.this
                    androidx.appcompat.widget.ListPopupWindow r1 = com.google.android.material.textfield.MaterialAutoCompleteTextView.access$000(r1)
                    android.widget.ListView r1 = r1.getListView()
                    r2 = r9
                    r3 = r13
                    r4 = r11
                    r0.onItemClick(r1, r2, r3, r4)
                L77:
                    r0 = r7
                    com.google.android.material.textfield.MaterialAutoCompleteTextView r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.this
                    androidx.appcompat.widget.ListPopupWindow r0 = com.google.android.material.textfield.MaterialAutoCompleteTextView.access$000(r0)
                    r0.dismiss()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.MaterialAutoCompleteTextView.AnonymousClass1.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
            }
        });
        obtainStyledAttributes.recycle();
    }

    private TextInputLayout findTextInputLayoutAncestor() {
        ViewParent parent = getParent();
        while (true) {
            ViewParent viewParent = parent;
            if (viewParent == null) {
                return null;
            }
            if (viewParent instanceof TextInputLayout) {
                return (TextInputLayout) viewParent;
            }
            parent = viewParent.getParent();
        }
    }

    private int measureContentWidth() {
        ListAdapter adapter = getAdapter();
        TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
        int i = 0;
        if (adapter == null || findTextInputLayoutAncestor == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int min = Math.min(adapter.getCount(), Math.max(0, this.modalListPopup.getSelectedItemPosition()) + 15);
        int max = Math.max(0, min - 15);
        View view = null;
        int i2 = 0;
        while (max < min) {
            int itemViewType = adapter.getItemViewType(max);
            int i3 = i;
            if (itemViewType != i) {
                view = null;
                i3 = itemViewType;
            }
            view = adapter.getView(max, view, findTextInputLayoutAncestor);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i2 = Math.max(i2, view.getMeasuredWidth());
            max++;
            i = i3;
        }
        Drawable background = this.modalListPopup.getBackground();
        int i4 = i2;
        if (background != null) {
            background.getPadding(this.tempRect);
            i4 = i2 + this.tempRect.left + this.tempRect.right;
        }
        return i4 + findTextInputLayoutAncestor.getEndIconView().getMeasuredWidth();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends ListAdapter & Filterable> void updateText(Object obj) {
        setText(convertSelectionToString(obj), false);
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
        return (findTextInputLayoutAncestor == null || !findTextInputLayoutAncestor.isProvidingHint()) ? super.getHint() : findTextInputLayoutAncestor.getHint();
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
        if (findTextInputLayoutAncestor != null && findTextInputLayoutAncestor.isProvidingHint() && super.getHint() == null && ManufacturerUtils.isMeizuDevice()) {
            setHint("");
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), measureContentWidth()), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public <T extends ListAdapter & Filterable> void setAdapter(T t) {
        super.setAdapter(t);
        this.modalListPopup.setAdapter(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public void showDropDown() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.showDropDown();
        } else {
            this.modalListPopup.show();
        }
    }
}