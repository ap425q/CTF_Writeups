package com.example.bombastic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.bombastic.C0553R;

/* loaded from: classes3.dex */
public final class ActivityMainBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView textView;

    private ActivityMainBinding(ConstraintLayout rootView, TextView textView) {
        this.rootView = rootView;
        this.textView = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(C0553R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityMainBinding bind(View rootView) {
        int id = C0553R.C0556id.textView;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, id);
        if (textView != null) {
            return new ActivityMainBinding((ConstraintLayout) rootView, textView);
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}