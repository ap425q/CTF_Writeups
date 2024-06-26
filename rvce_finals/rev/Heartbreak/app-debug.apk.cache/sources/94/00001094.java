package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import android.view.animation.Interpolator;
import androidx.core.math.MathUtils;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;
import com.google.android.material.C0558R;
import com.google.android.material.progressindicator.DrawingDelegate;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class LinearIndeterminateDisjointAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {
    private static final int TOTAL_DURATION_IN_MS = 1800;
    private float animationFraction;
    private ObjectAnimator animator;
    Animatable2Compat.AnimationCallback animatorCompleteCallback;
    private final BaseProgressIndicatorSpec baseSpec;
    private ObjectAnimator completeEndAnimator;
    private boolean dirtyColors;
    private int indicatorColorIndex;
    private final Interpolator[] interpolatorArray;
    private static final int[] DURATION_TO_MOVE_SEGMENT_ENDS = {533, 567, 850, 750};
    private static final int[] DELAY_TO_MOVE_SEGMENT_ENDS = {1267, 1000, 333, 0};
    private static final Property<LinearIndeterminateDisjointAnimatorDelegate, Float> ANIMATION_FRACTION = new Property<LinearIndeterminateDisjointAnimatorDelegate, Float>(Float.class, "animationFraction") { // from class: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate.3
        @Override // android.util.Property
        public Float get(LinearIndeterminateDisjointAnimatorDelegate delegate) {
            return Float.valueOf(delegate.getAnimationFraction());
        }

        @Override // android.util.Property
        public void set(LinearIndeterminateDisjointAnimatorDelegate delegate, Float value) {
            delegate.setAnimationFraction(value.floatValue());
        }
    };

    public LinearIndeterminateDisjointAnimatorDelegate(Context context, LinearProgressIndicatorSpec spec) {
        super(2);
        this.indicatorColorIndex = 0;
        this.animatorCompleteCallback = null;
        this.baseSpec = spec;
        this.interpolatorArray = new Interpolator[]{AnimationUtilsCompat.loadInterpolator(context, C0558R.anim.linear_indeterminate_line1_head_interpolator), AnimationUtilsCompat.loadInterpolator(context, C0558R.anim.linear_indeterminate_line1_tail_interpolator), AnimationUtilsCompat.loadInterpolator(context, C0558R.anim.linear_indeterminate_line2_head_interpolator), AnimationUtilsCompat.loadInterpolator(context, C0558R.anim.linear_indeterminate_line2_tail_interpolator)};
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
            this.animator.setDuration(1800L);
            this.animator.setInterpolator(null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animation) {
                    super.onAnimationRepeat(animation);
                    LinearIndeterminateDisjointAnimatorDelegate.this.indicatorColorIndex = (LinearIndeterminateDisjointAnimatorDelegate.this.indicatorColorIndex + 1) % LinearIndeterminateDisjointAnimatorDelegate.this.baseSpec.indicatorColors.length;
                    LinearIndeterminateDisjointAnimatorDelegate.this.dirtyColors = true;
                }
            });
        }
        if (this.completeEndAnimator == null) {
            this.completeEndAnimator = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, 1.0f);
            this.completeEndAnimator.setDuration(1800L);
            this.completeEndAnimator.setInterpolator(null);
            this.completeEndAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.LinearIndeterminateDisjointAnimatorDelegate.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    LinearIndeterminateDisjointAnimatorDelegate.this.cancelAnimatorImmediately();
                    if (LinearIndeterminateDisjointAnimatorDelegate.this.animatorCompleteCallback != null) {
                        LinearIndeterminateDisjointAnimatorDelegate.this.animatorCompleteCallback.onAnimationEnd(LinearIndeterminateDisjointAnimatorDelegate.this.drawable);
                    }
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
        if (this.completeEndAnimator == null || this.completeEndAnimator.isRunning()) {
            return;
        }
        cancelAnimatorImmediately();
        if (this.drawable.isVisible()) {
            this.completeEndAnimator.setFloatValues(this.animationFraction, 1.0f);
            this.completeEndAnimator.setDuration((1.0f - this.animationFraction) * 1800.0f);
            this.completeEndAnimator.start();
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void invalidateSpecValues() {
        resetPropertiesForNewStart();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void registerAnimatorsCompleteCallback(Animatable2Compat.AnimationCallback callback) {
        this.animatorCompleteCallback = callback;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void unregisterAnimatorsCompleteCallback() {
        this.animatorCompleteCallback = null;
    }

    private void updateSegmentPositions(int playtime) {
        for (int i = 0; i < this.activeIndicators.size(); i++) {
            DrawingDelegate.ActiveIndicator indicator = this.activeIndicators.get(i);
            float fraction = getFractionInRange(playtime, DELAY_TO_MOVE_SEGMENT_ENDS[i * 2], DURATION_TO_MOVE_SEGMENT_ENDS[i * 2]);
            indicator.startFraction = MathUtils.clamp(this.interpolatorArray[i * 2].getInterpolation(fraction), 0.0f, 1.0f);
            float fraction2 = getFractionInRange(playtime, DELAY_TO_MOVE_SEGMENT_ENDS[(i * 2) + 1], DURATION_TO_MOVE_SEGMENT_ENDS[(i * 2) + 1]);
            indicator.endFraction = MathUtils.clamp(this.interpolatorArray[(i * 2) + 1].getInterpolation(fraction2), 0.0f, 1.0f);
        }
    }

    private void maybeUpdateSegmentColors() {
        if (this.dirtyColors) {
            for (DrawingDelegate.ActiveIndicator indicator : this.activeIndicators) {
                indicator.color = this.baseSpec.indicatorColors[this.indicatorColorIndex];
            }
            this.dirtyColors = false;
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void resetPropertiesForNewStart() {
        this.indicatorColorIndex = 0;
        for (DrawingDelegate.ActiveIndicator indicator : this.activeIndicators) {
            indicator.color = this.baseSpec.indicatorColors[0];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getAnimationFraction() {
        return this.animationFraction;
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    void setAnimationFraction(float fraction) {
        this.animationFraction = fraction;
        int playtime = (int) (this.animationFraction * 1800.0f);
        updateSegmentPositions(playtime);
        maybeUpdateSegmentColors();
        this.drawable.invalidateSelf();
    }
}