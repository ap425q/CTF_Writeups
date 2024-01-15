package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.State;
import java.util.Iterator;

/* loaded from: output.jar:androidx/constraintlayout/core/state/helpers/HorizontalChainReference.class */
public class HorizontalChainReference extends ChainReference {

    /* renamed from: androidx.constraintlayout.core.state.helpers.HorizontalChainReference$1  reason: invalid class name */
    /* loaded from: output.jar:androidx/constraintlayout/core/state/helpers/HorizontalChainReference$1.class */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$state$State$Chain;

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x002f -> B:19:0x001f). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x002b -> B:15:0x0014). Please submit an issue!!! */
        static {
            int[] iArr = new int[State.Chain.values().length];
            $SwitchMap$androidx$constraintlayout$core$state$State$Chain = iArr;
            try {
                iArr[State.Chain.SPREAD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$state$State$Chain[State.Chain.SPREAD_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$state$State$Chain[State.Chain.PACKED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public HorizontalChainReference(State state) {
        super(state, State.Helper.HORIZONTAL_CHAIN);
    }

    @Override // androidx.constraintlayout.core.state.HelperReference, androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public void apply() {
        ConstraintReference constraintReference;
        Iterator<Object> it = this.mReferences.iterator();
        while (it.hasNext()) {
            this.mState.constraints(it.next()).clearHorizontal();
        }
        Iterator<Object> it2 = this.mReferences.iterator();
        ConstraintReference constraintReference2 = null;
        ConstraintReference constraintReference3 = null;
        while (true) {
            constraintReference = constraintReference3;
            if (!it2.hasNext()) {
                break;
            }
            ConstraintReference constraints = this.mState.constraints(it2.next());
            ConstraintReference constraintReference4 = constraintReference;
            if (constraintReference == null) {
                if (this.mStartToStart != null) {
                    constraints.startToStart(this.mStartToStart).margin(this.mMarginStart);
                } else if (this.mStartToEnd != null) {
                    constraints.startToEnd(this.mStartToEnd).margin(this.mMarginStart);
                } else if (this.mLeftToLeft != null) {
                    constraints.startToStart(this.mLeftToLeft).margin(this.mMarginLeft);
                } else if (this.mLeftToRight != null) {
                    constraints.startToEnd(this.mLeftToRight).margin(this.mMarginLeft);
                } else {
                    constraints.startToStart(State.PARENT);
                }
                constraintReference4 = constraints;
            }
            if (constraintReference2 != null) {
                constraintReference2.endToStart(constraints.getKey());
                constraints.startToEnd(constraintReference2.getKey());
            }
            constraintReference2 = constraints;
            constraintReference3 = constraintReference4;
        }
        if (constraintReference2 != null) {
            if (this.mEndToStart != null) {
                constraintReference2.endToStart(this.mEndToStart).margin(this.mMarginEnd);
            } else if (this.mEndToEnd != null) {
                constraintReference2.endToEnd(this.mEndToEnd).margin(this.mMarginEnd);
            } else if (this.mRightToLeft != null) {
                constraintReference2.endToStart(this.mRightToLeft).margin(this.mMarginRight);
            } else if (this.mRightToRight != null) {
                constraintReference2.endToEnd(this.mRightToRight).margin(this.mMarginRight);
            } else {
                constraintReference2.endToEnd(State.PARENT);
            }
        }
        if (constraintReference == null) {
            return;
        }
        if (this.mBias != 0.5f) {
            constraintReference.horizontalBias(this.mBias);
        }
        int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$state$State$Chain[this.mStyle.ordinal()];
        if (i == 1) {
            constraintReference.setHorizontalChainStyle(0);
        } else if (i == 2) {
            constraintReference.setHorizontalChainStyle(1);
        } else if (i != 3) {
        } else {
            constraintReference.setHorizontalChainStyle(2);
        }
    }
}