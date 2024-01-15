package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;

/* loaded from: output.jar:androidx/transition/ChangeClipBounds.class */
public class ChangeClipBounds extends Transition {
    private static final String PROPNAME_BOUNDS = "android:clipBounds:bounds";
    private static final String PROPNAME_CLIP = "android:clipBounds:clip";
    private static final String[] sTransitionProperties = {PROPNAME_CLIP};

    public ChangeClipBounds() {
    }

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view.getVisibility() == 8) {
            return;
        }
        Rect clipBounds = ViewCompat.getClipBounds(view);
        transitionValues.values.put(PROPNAME_CLIP, clipBounds);
        if (clipBounds == null) {
            transitionValues.values.put(PROPNAME_BOUNDS, new Rect(0, 0, view.getWidth(), view.getHeight()));
        }
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        Rect rect;
        Rect rect2;
        ObjectAnimator objectAnimator = null;
        if (transitionValues != null) {
            objectAnimator = null;
            if (transitionValues2 != null) {
                objectAnimator = null;
                if (transitionValues.values.containsKey(PROPNAME_CLIP)) {
                    if (transitionValues2.values.containsKey(PROPNAME_CLIP)) {
                        Rect rect3 = (Rect) transitionValues.values.get(PROPNAME_CLIP);
                        Rect rect4 = (Rect) transitionValues2.values.get(PROPNAME_CLIP);
                        boolean z = rect4 == null;
                        if (rect3 == null && rect4 == null) {
                            return null;
                        }
                        if (rect3 == null) {
                            rect = (Rect) transitionValues.values.get(PROPNAME_BOUNDS);
                            rect2 = rect4;
                        } else {
                            rect = rect3;
                            rect2 = rect4;
                            if (rect4 == null) {
                                rect2 = (Rect) transitionValues2.values.get(PROPNAME_BOUNDS);
                                rect = rect3;
                            }
                        }
                        if (rect.equals(rect2)) {
                            return null;
                        }
                        ViewCompat.setClipBounds(transitionValues2.view, rect);
                        ObjectAnimator ofObject = ObjectAnimator.ofObject(transitionValues2.view, (Property<View, V>) ViewUtils.CLIP_BOUNDS, (TypeEvaluator) new RectEvaluator(new Rect()), (Object[]) new Rect[]{rect, rect2});
                        objectAnimator = ofObject;
                        if (z) {
                            final View view = transitionValues2.view;
                            ofObject.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.ChangeClipBounds.1
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator) {
                                    ViewCompat.setClipBounds(view, null);
                                }
                            });
                            objectAnimator = ofObject;
                        }
                    } else {
                        objectAnimator = null;
                    }
                }
            }
        }
        return objectAnimator;
    }

    @Override // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }
}