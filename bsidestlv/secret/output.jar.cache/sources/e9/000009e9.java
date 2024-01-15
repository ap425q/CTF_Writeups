package androidx.transition;

import android.graphics.Rect;
import android.view.ViewGroup;

/* loaded from: output.jar:androidx/transition/SidePropagation.class */
public class SidePropagation extends VisibilityPropagation {
    private float mPropagationSpeed = 3.0f;
    private int mSide = 80;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0029, code lost:
        r17 = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002f, code lost:
        r17 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        if ((androidx.core.view.ViewCompat.getLayoutDirection(r5) == 1) != false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:
        if (r17 != false) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int distance(android.view.View r5, int r6, int r7, int r8, int r9, int r10, int r11, int r12, int r13) {
        /*
            r4 = this;
            r0 = r4
            int r0 = r0.mSide
            r14 = r0
            r0 = 0
            r15 = r0
            r0 = 1
            r16 = r0
            r0 = 1
            r17 = r0
            r0 = r14
            r1 = 8388611(0x800003, float:1.1754948E-38)
            if (r0 != r1) goto L35
            r0 = r5
            int r0 = androidx.core.view.ViewCompat.getLayoutDirection(r0)
            r1 = 1
            if (r0 != r1) goto L21
            goto L24
        L21:
            r0 = 0
            r17 = r0
        L24:
            r0 = r17
            if (r0 == 0) goto L2f
        L29:
            r0 = 5
            r17 = r0
            goto L5a
        L2f:
            r0 = 3
            r17 = r0
            goto L5a
        L35:
            r0 = r14
            r17 = r0
            r0 = r14
            r1 = 8388613(0x800005, float:1.175495E-38)
            if (r0 != r1) goto L5a
            r0 = r5
            int r0 = androidx.core.view.ViewCompat.getLayoutDirection(r0)
            r1 = 1
            if (r0 != r1) goto L4f
            r0 = r16
            r17 = r0
            goto L52
        L4f:
            r0 = 0
            r17 = r0
        L52:
            r0 = r17
            if (r0 == 0) goto L29
            goto L2f
        L5a:
            r0 = r17
            r1 = 3
            if (r0 == r1) goto Laa
            r0 = r17
            r1 = 5
            if (r0 == r1) goto L9a
            r0 = r17
            r1 = 48
            if (r0 == r1) goto L8a
            r0 = r17
            r1 = 80
            if (r0 == r1) goto L7a
            r0 = r15
            r6 = r0
            goto Lb7
        L7a:
            r0 = r7
            r1 = r11
            int r0 = r0 - r1
            r1 = r8
            r2 = r6
            int r1 = r1 - r2
            int r1 = java.lang.Math.abs(r1)
            int r0 = r0 + r1
            r6 = r0
            goto Lb7
        L8a:
            r0 = r13
            r1 = r7
            int r0 = r0 - r1
            r1 = r8
            r2 = r6
            int r1 = r1 - r2
            int r1 = java.lang.Math.abs(r1)
            int r0 = r0 + r1
            r6 = r0
            goto Lb7
        L9a:
            r0 = r6
            r1 = r10
            int r0 = r0 - r1
            r1 = r9
            r2 = r7
            int r1 = r1 - r2
            int r1 = java.lang.Math.abs(r1)
            int r0 = r0 + r1
            r6 = r0
            goto Lb7
        Laa:
            r0 = r12
            r1 = r6
            int r0 = r0 - r1
            r1 = r9
            r2 = r7
            int r1 = r1 - r2
            int r1 = java.lang.Math.abs(r1)
            int r0 = r0 + r1
            r6 = r0
        Lb7:
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.SidePropagation.distance(android.view.View, int, int, int, int, int, int, int, int):int");
    }

    private int getMaxDistance(ViewGroup viewGroup) {
        int i = this.mSide;
        return (i == 3 || i == 5 || i == 8388611 || i == 8388613) ? viewGroup.getWidth() : viewGroup.getHeight();
    }

    @Override // androidx.transition.TransitionPropagation
    public long getStartDelay(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i;
        int i2;
        int i3;
        if (transitionValues == null && transitionValues2 == null) {
            return 0L;
        }
        Rect epicenter = transition.getEpicenter();
        if (transitionValues2 == null || getViewVisibility(transitionValues) == 0) {
            i = -1;
        } else {
            transitionValues = transitionValues2;
            i = 1;
        }
        int viewX = getViewX(transitionValues);
        int viewY = getViewY(transitionValues);
        int[] iArr = new int[2];
        viewGroup.getLocationOnScreen(iArr);
        int round = iArr[0] + Math.round(viewGroup.getTranslationX());
        int round2 = iArr[1] + Math.round(viewGroup.getTranslationY());
        int width = round + viewGroup.getWidth();
        int height = round2 + viewGroup.getHeight();
        if (epicenter != null) {
            i2 = epicenter.centerX();
            i3 = epicenter.centerY();
        } else {
            i2 = (round + width) / 2;
            i3 = (round2 + height) / 2;
        }
        float distance = distance(viewGroup, viewX, viewY, i2, i3, round, round2, width, height) / getMaxDistance(viewGroup);
        long duration = transition.getDuration();
        long j = duration;
        if (duration < 0) {
            j = 300;
        }
        return Math.round((((float) (j * i)) / this.mPropagationSpeed) * distance);
    }

    public void setPropagationSpeed(float f) {
        if (f == 0.0f) {
            throw new IllegalArgumentException("propagationSpeed may not be 0");
        }
        this.mPropagationSpeed = f;
    }

    public void setSide(int i) {
        this.mSide = i;
    }
}