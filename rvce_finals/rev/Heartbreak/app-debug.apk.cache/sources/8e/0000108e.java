package com.google.android.material.progressindicator;

import android.animation.Animator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.progressindicator.DrawingDelegate;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class IndeterminateAnimatorDelegate<T extends Animator> {
    protected final List<DrawingDelegate.ActiveIndicator> activeIndicators = new ArrayList();
    protected IndeterminateDrawable drawable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void cancelAnimatorImmediately();

    public abstract void invalidateSpecValues();

    public abstract void registerAnimatorsCompleteCallback(Animatable2Compat.AnimationCallback animationCallback);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void requestCancelAnimatorAfterCurrentCycle();

    abstract void resetPropertiesForNewStart();

    abstract void setAnimationFraction(float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void startAnimator();

    public abstract void unregisterAnimatorsCompleteCallback();

    /* JADX INFO: Access modifiers changed from: protected */
    public IndeterminateAnimatorDelegate(int indicatorCount) {
        for (int i = 0; i < indicatorCount; i++) {
            this.activeIndicators.add(new DrawingDelegate.ActiveIndicator());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerDrawable(IndeterminateDrawable drawable) {
        this.drawable = drawable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getFractionInRange(int playtime, int start, int duration) {
        return (playtime - start) / duration;
    }
}