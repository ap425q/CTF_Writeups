package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: output.jar:androidx/fragment/app/FragmentLayoutInflaterFactory.class */
public class FragmentLayoutInflaterFactory implements LayoutInflater.Factory2 {
    private static final String TAG = "FragmentManager";
    final FragmentManager mFragmentManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentLayoutInflaterFactory(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        Fragment fragment;
        FragmentStateManager fragmentStateManager;
        if (FragmentContainerView.class.getName().equals(str)) {
            return new FragmentContainerView(context, attributeSet, this.mFragmentManager);
        }
        boolean equals = "fragment".equals(str);
        Fragment fragment2 = null;
        if (equals) {
            String attributeValue = attributeSet.getAttributeValue(null, "class");
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Fragment);
            String str2 = attributeValue;
            if (attributeValue == null) {
                str2 = obtainStyledAttributes.getString(R.styleable.Fragment_android_name);
            }
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.Fragment_android_id, -1);
            String string = obtainStyledAttributes.getString(R.styleable.Fragment_android_tag);
            obtainStyledAttributes.recycle();
            if (str2 == null || !FragmentFactory.isFragmentClass(context.getClassLoader(), str2)) {
                return null;
            }
            int id = view != null ? view.getId() : 0;
            if (id == -1 && resourceId == -1 && string == null) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
            }
            if (resourceId != -1) {
                fragment2 = this.mFragmentManager.findFragmentById(resourceId);
            }
            Fragment fragment3 = fragment2;
            if (fragment2 == null) {
                fragment3 = fragment2;
                if (string != null) {
                    fragment3 = this.mFragmentManager.findFragmentByTag(string);
                }
            }
            Fragment fragment4 = fragment3;
            if (fragment3 == null) {
                fragment4 = fragment3;
                if (id != -1) {
                    fragment4 = this.mFragmentManager.findFragmentById(id);
                }
            }
            if (fragment4 == null) {
                Fragment instantiate = this.mFragmentManager.getFragmentFactory().instantiate(context.getClassLoader(), str2);
                instantiate.mFromLayout = true;
                instantiate.mFragmentId = resourceId != 0 ? resourceId : id;
                instantiate.mContainerId = id;
                instantiate.mTag = string;
                instantiate.mInLayout = true;
                instantiate.mFragmentManager = this.mFragmentManager;
                instantiate.mHost = this.mFragmentManager.getHost();
                instantiate.onInflate(this.mFragmentManager.getHost().getContext(), attributeSet, instantiate.mSavedFragmentState);
                FragmentStateManager addFragment = this.mFragmentManager.addFragment(instantiate);
                fragment = instantiate;
                fragmentStateManager = addFragment;
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(TAG, "Fragment " + instantiate + " has been inflated via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
                    fragment = instantiate;
                    fragmentStateManager = addFragment;
                }
            } else if (fragment4.mInLayout) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            } else {
                fragment4.mInLayout = true;
                fragment4.mFragmentManager = this.mFragmentManager;
                fragment4.mHost = this.mFragmentManager.getHost();
                fragment4.onInflate(this.mFragmentManager.getHost().getContext(), attributeSet, fragment4.mSavedFragmentState);
                FragmentStateManager createOrGetFragmentStateManager = this.mFragmentManager.createOrGetFragmentStateManager(fragment4);
                fragment = fragment4;
                fragmentStateManager = createOrGetFragmentStateManager;
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(TAG, "Retained Fragment " + fragment4 + " has been re-attached via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
                    fragmentStateManager = createOrGetFragmentStateManager;
                    fragment = fragment4;
                }
            }
            fragment.mContainer = (ViewGroup) view;
            fragmentStateManager.moveToExpectedState();
            fragmentStateManager.ensureInflatedView();
            if (fragment.mView != null) {
                if (resourceId != 0) {
                    fragment.mView.setId(resourceId);
                }
                if (fragment.mView.getTag() == null) {
                    fragment.mView.setTag(string);
                }
                final FragmentStateManager fragmentStateManager2 = fragmentStateManager;
                fragment.mView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.fragment.app.FragmentLayoutInflaterFactory.1
                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view2) {
                        Fragment fragment5 = fragmentStateManager2.getFragment();
                        fragmentStateManager2.moveToExpectedState();
                        SpecialEffectsController.getOrCreateController((ViewGroup) fragment5.mView.getParent(), FragmentLayoutInflaterFactory.this.mFragmentManager).forceCompleteAllOperations();
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View view2) {
                    }
                });
                return fragment.mView;
            }
            throw new IllegalStateException("Fragment " + str2 + " did not create a view.");
        }
        return null;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}