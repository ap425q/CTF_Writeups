package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.State;
import java.util.Iterator;

/* loaded from: output.jar:androidx/constraintlayout/core/state/helpers/VerticalChainReference.class */
public class VerticalChainReference extends ChainReference {

    /* renamed from: androidx.constraintlayout.core.state.helpers.VerticalChainReference$1  reason: invalid class name */
    /* loaded from: output.jar:androidx/constraintlayout/core/state/helpers/VerticalChainReference$1.class */
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

    public VerticalChainReference(State state) {
        super(state, State.Helper.VERTICAL_CHAIN);
    }

    @Override // androidx.constraintlayout.core.state.HelperReference, androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public void apply() {
        ConstraintReference constraintReference;
        Iterator<Object> it = this.mReferences.iterator();
        while (it.hasNext()) {
            this.mState.constraints(it.next()).clearVertical();
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
                if (this.mTopToTop != null) {
                    constraints.topToTop(this.mTopToTop);
                } else if (this.mTopToBottom != null) {
                    constraints.topToBottom(this.mTopToBottom);
                } else {
                    constraints.topToTop(State.PARENT);
                }
                constraintReference4 = constraints;
            }
            if (constraintReference2 != null) {
                constraintReference2.bottomToTop(constraints.getKey());
                constraints.topToBottom(constraintReference2.getKey());
            }
            constraintReference2 = constraints;
            constraintReference3 = constraintReference4;
        }
        if (constraintReference2 != null) {
            if (this.mBottomToTop != null) {
                constraintReference2.bottomToTop(this.mBottomToTop);
            } else if (this.mBottomToBottom != null) {
                constraintReference2.bottomToBottom(this.mBottomToBottom);
            } else {
                constraintReference2.bottomToBottom(State.PARENT);
            }
        }
        if (constraintReference == null) {
            return;
        }
        if (this.mBias != 0.5f) {
            constraintReference.verticalBias(this.mBias);
        }
        int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$state$State$Chain[this.mStyle.ordinal()];
        if (i == 1) {
            constraintReference.setVerticalChainStyle(0);
        } else if (i == 2) {
            constraintReference.setVerticalChainStyle(1);
        } else if (i != 3) {
        } else {
            constraintReference.setVerticalChainStyle(2);
        }
    }
}