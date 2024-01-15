package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.utils.FloatRect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/* loaded from: output.jar:androidx/constraintlayout/core/motion/key/MotionKeyTrigger.class */
public class MotionKeyTrigger extends MotionKey {
    public static final String CROSS = "CROSS";
    public static final int KEY_TYPE = 5;
    public static final String NEGATIVE_CROSS = "negativeCross";
    public static final String POSITIVE_CROSS = "positiveCross";
    public static final String POST_LAYOUT = "postLayout";
    private static final String TAG = "KeyTrigger";
    public static final String TRIGGER_COLLISION_ID = "triggerCollisionId";
    public static final String TRIGGER_COLLISION_VIEW = "triggerCollisionView";
    public static final String TRIGGER_ID = "triggerID";
    public static final String TRIGGER_RECEIVER = "triggerReceiver";
    public static final String TRIGGER_SLACK = "triggerSlack";
    public static final int TYPE_CROSS = 312;
    public static final int TYPE_NEGATIVE_CROSS = 310;
    public static final int TYPE_POSITIVE_CROSS = 309;
    public static final int TYPE_POST_LAYOUT = 304;
    public static final int TYPE_TRIGGER_COLLISION_ID = 307;
    public static final int TYPE_TRIGGER_COLLISION_VIEW = 306;
    public static final int TYPE_TRIGGER_ID = 308;
    public static final int TYPE_TRIGGER_RECEIVER = 311;
    public static final int TYPE_TRIGGER_SLACK = 305;
    public static final int TYPE_VIEW_TRANSITION_ON_CROSS = 301;
    public static final int TYPE_VIEW_TRANSITION_ON_NEGATIVE_CROSS = 303;
    public static final int TYPE_VIEW_TRANSITION_ON_POSITIVE_CROSS = 302;
    public static final String VIEW_TRANSITION_ON_CROSS = "viewTransitionOnCross";
    public static final String VIEW_TRANSITION_ON_NEGATIVE_CROSS = "viewTransitionOnNegativeCross";
    public static final String VIEW_TRANSITION_ON_POSITIVE_CROSS = "viewTransitionOnPositiveCross";
    private float mFireLastPos;
    private int mCurveFit = -1;
    private String mCross = null;
    private int mTriggerReceiver = UNSET;
    private String mNegativeCross = null;
    private String mPositiveCross = null;
    private int mTriggerID = UNSET;
    private int mTriggerCollisionId = UNSET;
    float mTriggerSlack = 0.1f;
    private boolean mFireCrossReset = true;
    private boolean mFireNegativeReset = true;
    private boolean mFirePositiveReset = true;
    private float mFireThreshold = Float.NaN;
    private boolean mPostLayout = false;
    int mViewTransitionOnNegativeCross = UNSET;
    int mViewTransitionOnPositiveCross = UNSET;
    int mViewTransitionOnCross = UNSET;
    FloatRect mCollisionRect = new FloatRect();
    FloatRect mTargetRect = new FloatRect();

    public MotionKeyTrigger() {
        this.mType = 5;
        this.mCustom = new HashMap<>();
    }

    private void fireCustom(String str, MotionWidget motionWidget) {
        boolean z = str.length() == 1;
        String str2 = str;
        if (!z) {
            str2 = str.substring(1).toLowerCase(Locale.ROOT);
        }
        for (String str3 : this.mCustom.keySet()) {
            String lowerCase = str3.toLowerCase(Locale.ROOT);
            if (z || lowerCase.matches(str2)) {
                CustomVariable customVariable = this.mCustom.get(str3);
                if (customVariable != null) {
                    customVariable.applyToWidget(motionWidget);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /* renamed from: clone */
    public MotionKey mo78clone() {
        return new MotionKeyTrigger().copy((MotionKey) this);
    }

    public void conditionallyFire(float f, MotionWidget motionWidget) {
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKeyTrigger copy(MotionKey motionKey) {
        super.copy(motionKey);
        MotionKeyTrigger motionKeyTrigger = (MotionKeyTrigger) motionKey;
        this.mCurveFit = motionKeyTrigger.mCurveFit;
        this.mCross = motionKeyTrigger.mCross;
        this.mTriggerReceiver = motionKeyTrigger.mTriggerReceiver;
        this.mNegativeCross = motionKeyTrigger.mNegativeCross;
        this.mPositiveCross = motionKeyTrigger.mPositiveCross;
        this.mTriggerID = motionKeyTrigger.mTriggerID;
        this.mTriggerCollisionId = motionKeyTrigger.mTriggerCollisionId;
        this.mTriggerSlack = motionKeyTrigger.mTriggerSlack;
        this.mFireCrossReset = motionKeyTrigger.mFireCrossReset;
        this.mFireNegativeReset = motionKeyTrigger.mFireNegativeReset;
        this.mFirePositiveReset = motionKeyTrigger.mFirePositiveReset;
        this.mFireThreshold = motionKeyTrigger.mFireThreshold;
        this.mFireLastPos = motionKeyTrigger.mFireLastPos;
        this.mPostLayout = motionKeyTrigger.mPostLayout;
        this.mCollisionRect = motionKeyTrigger.mCollisionRect;
        this.mTargetRect = motionKeyTrigger.mTargetRect;
        return this;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void getAttributeNames(HashSet<String> hashSet) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        boolean z;
        str.hashCode();
        switch (str.hashCode()) {
            case -1594793529:
                if (str.equals("positiveCross")) {
                    z = false;
                    break;
                }
                z = true;
                break;
            case -966421266:
                if (str.equals("viewTransitionOnPositiveCross")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case -786670827:
                if (str.equals("triggerCollisionId")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case -648752941:
                if (str.equals("triggerID")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case -638126837:
                if (str.equals("negativeCross")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case -76025313:
                if (str.equals("triggerCollisionView")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case -9754574:
                if (str.equals("viewTransitionOnNegativeCross")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 364489912:
                if (str.equals("triggerSlack")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1301930599:
                if (str.equals("viewTransitionOnCross")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1401391082:
                if (str.equals("postLayout")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1535404999:
                if (str.equals("triggerReceiver")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            default:
                z = true;
                break;
        }
        switch (z) {
            case false:
                return 309;
            case true:
                return 302;
            case true:
                return 307;
            case true:
                return 308;
            case true:
                return 310;
            case true:
                return 306;
            case true:
                return 303;
            case true:
                return 305;
            case true:
                return 301;
            case true:
                return 304;
            case true:
                return 311;
            default:
                return -1;
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, float f) {
        if (i != 305) {
            return super.setValue(i, f);
        }
        this.mTriggerSlack = f;
        return true;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, int i2) {
        if (i == 307) {
            this.mTriggerCollisionId = i2;
            return true;
        } else if (i == 308) {
            this.mTriggerID = toInt(Integer.valueOf(i2));
            return true;
        } else if (i == 311) {
            this.mTriggerReceiver = i2;
            return true;
        } else {
            switch (i) {
                case 301:
                    this.mViewTransitionOnCross = i2;
                    return true;
                case 302:
                    this.mViewTransitionOnPositiveCross = i2;
                    return true;
                case 303:
                    this.mViewTransitionOnNegativeCross = i2;
                    return true;
                default:
                    return super.setValue(i, i2);
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, String str) {
        if (i == 309) {
            this.mPositiveCross = str;
            return true;
        } else if (i == 310) {
            this.mNegativeCross = str;
            return true;
        } else if (i != 312) {
            return super.setValue(i, str);
        } else {
            this.mCross = str;
            return true;
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, boolean z) {
        if (i != 304) {
            return super.setValue(i, z);
        }
        this.mPostLayout = z;
        return true;
    }
}