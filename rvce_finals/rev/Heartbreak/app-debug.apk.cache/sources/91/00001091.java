package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.progressindicator.DrawingDelegate;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class LinearIndeterminateContiguousAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {
    private static final Property<LinearIndeterminateContiguousAnimatorDelegate, Float> ANIMATION_FRACTION = new Property<LinearIndeterminateContiguousAnimatorDelegate, Float>(Float.class, "animationFraction") { // from class: com.google.android.material.progressindicator.LinearIndeterminateContiguousAnimatorDelegate.2
        @Override // android.util.Property
        public Float get(LinearIndeterminateContiguousAnimatorDelegate delegate) {
            return Float.valueOf(delegate.getAnimationFraction());
        }

        @Override // android.util.Property
        public void set(LinearIndeterminateContiguousAnimatorDelegate delegate, Float value) {
            delegate.setAnimationFraction(value.floatValue());
        }
    };
    private static final int DURATION_PER_CYCLE_IN_MS = 333;
    private static final int TOTAL_DURATION_IN_MS = 667;
    private float animationFraction;
    private ObjectAnimator animator;
    private final BaseProgressIndicatorSpec baseSpec;
    private boolean dirtyColors;
    private FastOutSlowInInterpolator interpolator;
    private int newIndicatorColorIndex;

    public LinearIndeterminateContiguousAnimatorDelegate(LinearProgressIndicatorSpec spec) {
        super(3);
        this.newIndicatorColorIndex = 1;
        this.baseSpec = spec;
        this.interpolator = new FastOutSlowInInterpolator();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void startAnimator() {
        maybeInitializeAnimators();
        resetPropertiesForNewStart();
        this.animator.start();
    }

    private void maybeInitializeAnimators() {
        if (this.animator == null) {
            this.animator = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, 0.0f, 1.0f);
            this.animator.setDuration(333L);
            this.animator.setInterpolator(null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.LinearIndeterminateContiguousAnimatorDelegate.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animation) {
                    super.onAnimationRepeat(animation);
                    LinearIndeterminateContiguousAnimatorDelegate.this.newIndicatorColorIndex = (LinearIndeterminateContiguousAnimatorDelegate.this.newIndicatorColorIndex + 1) % LinearIndeterminateContiguousAnimatorDelegate.this.baseSpec.indicatorColors.length;
                    LinearIndeterminateContiguousAnimatorDelegate.this.dirtyColors = true;
                }
            });
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void cancelAnimatorImmediately() {
        if (this.animator != null) {
            this.animator.cancel();
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void requestCancelAnimatorAfterCurrentCycle() {
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void invalidateSpecValues() {
        resetPropertiesForNewStart();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void registerAnimatorsCompleteCallback(Animatable2Compat.AnimationCallback callback) {
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void unregisterAnimatorsCompleteCallback() {
    }

    private void updateSegmentPositions(int playtime) {
        this.activeIndicators.get(0).startFraction = 0.0f;
        float fraction = getFractionInRange(playtime, 0, TOTAL_DURATION_IN_MS);
        float interpolation = this.interpolator.getInterpolation(fraction);
        this.activeIndicators.get(1).startFraction = interpolation;
        this.activeIndicators.get(0).endFraction = interpolation;
        float interpolation2 = this.interpolator.getInterpolation(fraction + 0.49925038f);
        this.activeIndicators.get(2).startFraction = interpolation2;
        this.activeIndicators.get(1).endFraction = interpolation2;
        this.activeIndicators.get(2).endFraction = 1.0f;
    }

    private void maybeUpdateSegmentColors() {
        if (this.dirtyColors && this.activeIndicators.get(1).endFraction < 1.0f) {
            this.activeIndicators.get(2).color = this.activeIndicators.get(1).color;
            this.activeIndicators.get(1).color = this.activeIndicators.get(0).color;
            this.activeIndicators.get(0).color = this.baseSpec.indicatorColors[this.newIndicatorColorIndex];
            this.dirtyColors = false;
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void resetPropertiesForNewStart() {
        this.dirtyColors = true;
        this.newIndicatorColorIndex = 1;
        for (DrawingDelegate.ActiveIndicator indicator : this.activeIndicators) {
            indicator.color = this.baseSpec.indicatorColors[0];
            indicator.gapSize = this.baseSpec.indicatorTrackGapSize / 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getAnimationFraction() {
        return this.animationFraction;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void setAnimationFraction(float value) {
        this.animationFraction = value;
        int playtime = (int) (this.animationFraction * 333.0f);
        updateSegmentPositions(playtime);
        maybeUpdateSegmentColors();
        this.drawable.invalidateSelf();
    }
}