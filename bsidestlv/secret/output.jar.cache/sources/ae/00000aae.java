package androidx.viewpager2.adapter;

import android.os.Parcelable;

/* loaded from: output.jar:androidx/viewpager2/adapter/StatefulAdapter.class */
public interface StatefulAdapter {
    void restoreState(Parcelable parcelable);

    Parcelable saveState();
}