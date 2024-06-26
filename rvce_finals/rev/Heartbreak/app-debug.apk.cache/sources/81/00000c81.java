package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.util.Consumer;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.transition.Transition;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/* loaded from: classes.dex */
public abstract class Transition implements Cloneable {
    static final boolean DBG = false;
    private static final String LOG_TAG = "Transition";
    private static final int MATCH_FIRST = 1;
    public static final int MATCH_ID = 3;
    private static final String MATCH_ID_STR = "id";
    public static final int MATCH_INSTANCE = 1;
    private static final String MATCH_INSTANCE_STR = "instance";
    public static final int MATCH_ITEM_ID = 4;
    private static final String MATCH_ITEM_ID_STR = "itemId";
    private static final int MATCH_LAST = 4;
    public static final int MATCH_NAME = 2;
    private static final String MATCH_NAME_STR = "name";
    private ArrayList<TransitionValues> mEndValuesList;
    private EpicenterCallback mEpicenterCallback;
    private TransitionListener[] mListenersCache;
    private ArrayMap<String, String> mNameOverrides;
    TransitionPropagation mPropagation;
    SeekController mSeekController;
    long mSeekOffsetInParent;
    private ArrayList<TransitionValues> mStartValuesList;
    long mTotalDuration;
    private static final Animator[] EMPTY_ANIMATOR_ARRAY = new Animator[0];
    private static final int[] DEFAULT_MATCH_ORDER = {2, 1, 3, 4};
    private static final PathMotion STRAIGHT_PATH_MOTION = new PathMotion() { // from class: androidx.transition.Transition.1
        @Override // androidx.transition.PathMotion
        public Path getPath(float startX, float startY, float endX, float endY) {
            Path path = new Path();
            path.moveTo(startX, startY);
            path.lineTo(endX, endY);
            return path;
        }
    };
    private static ThreadLocal<ArrayMap<Animator, AnimationInfo>> sRunningAnimators = new ThreadLocal<>();
    private String mName = getClass().getName();
    private long mStartDelay = -1;
    long mDuration = -1;
    private TimeInterpolator mInterpolator = null;
    ArrayList<Integer> mTargetIds = new ArrayList<>();
    ArrayList<View> mTargets = new ArrayList<>();
    private ArrayList<String> mTargetNames = null;
    private ArrayList<Class<?>> mTargetTypes = null;
    private ArrayList<Integer> mTargetIdExcludes = null;
    private ArrayList<View> mTargetExcludes = null;
    private ArrayList<Class<?>> mTargetTypeExcludes = null;
    private ArrayList<String> mTargetNameExcludes = null;
    private ArrayList<Integer> mTargetIdChildExcludes = null;
    private ArrayList<View> mTargetChildExcludes = null;
    private ArrayList<Class<?>> mTargetTypeChildExcludes = null;
    private TransitionValuesMaps mStartValues = new TransitionValuesMaps();
    private TransitionValuesMaps mEndValues = new TransitionValuesMaps();
    TransitionSet mParent = null;
    private int[] mMatchOrder = DEFAULT_MATCH_ORDER;
    boolean mCanRemoveViews = false;
    ArrayList<Animator> mCurrentAnimators = new ArrayList<>();
    private Animator[] mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
    int mNumInstances = 0;
    private boolean mPaused = false;
    boolean mEnded = false;
    private Transition mCloneParent = null;
    private ArrayList<TransitionListener> mListeners = null;
    ArrayList<Animator> mAnimators = new ArrayList<>();
    private PathMotion mPathMotion = STRAIGHT_PATH_MOTION;

    /* loaded from: classes.dex */
    public static abstract class EpicenterCallback {
        public abstract Rect onGetEpicenter(Transition transition);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface MatchOrder {
    }

    public abstract void captureEndValues(TransitionValues transitionValues);

    public abstract void captureStartValues(TransitionValues transitionValues);

    public Transition() {
    }

    public Transition(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, Styleable.TRANSITION);
        XmlResourceParser parser = (XmlResourceParser) attrs;
        long duration = TypedArrayUtils.getNamedInt(a, parser, TypedValues.TransitionType.S_DURATION, 1, -1);
        if (duration >= 0) {
            setDuration(duration);
        }
        long startDelay = TypedArrayUtils.getNamedInt(a, parser, "startDelay", 2, -1);
        if (startDelay > 0) {
            setStartDelay(startDelay);
        }
        int resId = TypedArrayUtils.getNamedResourceId(a, parser, "interpolator", 0, 0);
        if (resId > 0) {
            setInterpolator(AnimationUtils.loadInterpolator(context, resId));
        }
        String matchOrder = TypedArrayUtils.getNamedString(a, parser, "matchOrder", 3);
        if (matchOrder != null) {
            setMatchOrder(parseMatchOrder(matchOrder));
        }
        a.recycle();
    }

    private static int[] parseMatchOrder(String matchOrderString) {
        StringTokenizer st = new StringTokenizer(matchOrderString, ",");
        int[] matches = new int[st.countTokens()];
        int index = 0;
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (MATCH_ID_STR.equalsIgnoreCase(token)) {
                matches[index] = 3;
            } else if (MATCH_INSTANCE_STR.equalsIgnoreCase(token)) {
                matches[index] = 1;
            } else if (MATCH_NAME_STR.equalsIgnoreCase(token)) {
                matches[index] = 2;
            } else if (MATCH_ITEM_ID_STR.equalsIgnoreCase(token)) {
                matches[index] = 4;
            } else if (token.isEmpty()) {
                int[] smallerMatches = new int[matches.length - 1];
                System.arraycopy(matches, 0, smallerMatches, 0, index);
                matches = smallerMatches;
                index--;
            } else {
                throw new InflateException("Unknown match type in matchOrder: '" + token + "'");
            }
            index++;
        }
        return matches;
    }

    public final Transition getRootTransition() {
        if (this.mParent != null) {
            return this.mParent.getRootTransition();
        }
        return this;
    }

    public Transition setDuration(long duration) {
        this.mDuration = duration;
        return this;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public Transition setStartDelay(long startDelay) {
        this.mStartDelay = startDelay;
        return this;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public Transition setInterpolator(TimeInterpolator interpolator) {
        this.mInterpolator = interpolator;
        return this;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        return null;
    }

    public TransitionSeekController createSeekController() {
        this.mSeekController = new SeekController();
        addListener(this.mSeekController);
        return this.mSeekController;
    }

    public void setMatchOrder(int... matches) {
        if (matches == null || matches.length == 0) {
            this.mMatchOrder = DEFAULT_MATCH_ORDER;
            return;
        }
        for (int i = 0; i < matches.length; i++) {
            int match = matches[i];
            if (!isValidMatch(match)) {
                throw new IllegalArgumentException("matches contains invalid value");
            }
            if (alreadyContains(matches, i)) {
                throw new IllegalArgumentException("matches contains a duplicate value");
            }
        }
        this.mMatchOrder = (int[]) matches.clone();
    }

    private static boolean isValidMatch(int match) {
        return match >= 1 && match <= 4;
    }

    private static boolean alreadyContains(int[] array, int searchIndex) {
        int value = array[searchIndex];
        for (int i = 0; i < searchIndex; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    private void matchInstances(ArrayMap<View, TransitionValues> unmatchedStart, ArrayMap<View, TransitionValues> unmatchedEnd) {
        TransitionValues end;
        for (int i = unmatchedStart.size() - 1; i >= 0; i--) {
            View view = unmatchedStart.keyAt(i);
            if (view != null && isValidTarget(view) && (end = unmatchedEnd.remove(view)) != null && isValidTarget(end.view)) {
                TransitionValues start = unmatchedStart.removeAt(i);
                this.mStartValuesList.add(start);
                this.mEndValuesList.add(end);
            }
        }
    }

    private void matchItemIds(ArrayMap<View, TransitionValues> unmatchedStart, ArrayMap<View, TransitionValues> unmatchedEnd, LongSparseArray<View> startItemIds, LongSparseArray<View> endItemIds) {
        View endView;
        int numStartIds = startItemIds.size();
        for (int i = 0; i < numStartIds; i++) {
            View startView = startItemIds.valueAt(i);
            if (startView != null && isValidTarget(startView) && (endView = endItemIds.get(startItemIds.keyAt(i))) != null && isValidTarget(endView)) {
                TransitionValues startValues = unmatchedStart.get(startView);
                TransitionValues endValues = unmatchedEnd.get(endView);
                if (startValues != null && endValues != null) {
                    this.mStartValuesList.add(startValues);
                    this.mEndValuesList.add(endValues);
                    unmatchedStart.remove(startView);
                    unmatchedEnd.remove(endView);
                }
            }
        }
    }

    private void matchIds(ArrayMap<View, TransitionValues> unmatchedStart, ArrayMap<View, TransitionValues> unmatchedEnd, SparseArray<View> startIds, SparseArray<View> endIds) {
        View endView;
        int numStartIds = startIds.size();
        for (int i = 0; i < numStartIds; i++) {
            View startView = startIds.valueAt(i);
            if (startView != null && isValidTarget(startView) && (endView = endIds.get(startIds.keyAt(i))) != null && isValidTarget(endView)) {
                TransitionValues startValues = unmatchedStart.get(startView);
                TransitionValues endValues = unmatchedEnd.get(endView);
                if (startValues != null && endValues != null) {
                    this.mStartValuesList.add(startValues);
                    this.mEndValuesList.add(endValues);
                    unmatchedStart.remove(startView);
                    unmatchedEnd.remove(endView);
                }
            }
        }
    }

    private void matchNames(ArrayMap<View, TransitionValues> unmatchedStart, ArrayMap<View, TransitionValues> unmatchedEnd, ArrayMap<String, View> startNames, ArrayMap<String, View> endNames) {
        View endView;
        int numStartNames = startNames.size();
        for (int i = 0; i < numStartNames; i++) {
            View startView = startNames.valueAt(i);
            if (startView != null && isValidTarget(startView) && (endView = endNames.get(startNames.keyAt(i))) != null && isValidTarget(endView)) {
                TransitionValues startValues = unmatchedStart.get(startView);
                TransitionValues endValues = unmatchedEnd.get(endView);
                if (startValues != null && endValues != null) {
                    this.mStartValuesList.add(startValues);
                    this.mEndValuesList.add(endValues);
                    unmatchedStart.remove(startView);
                    unmatchedEnd.remove(endView);
                }
            }
        }
    }

    private void addUnmatched(ArrayMap<View, TransitionValues> unmatchedStart, ArrayMap<View, TransitionValues> unmatchedEnd) {
        for (int i = 0; i < unmatchedStart.size(); i++) {
            TransitionValues start = unmatchedStart.valueAt(i);
            if (isValidTarget(start.view)) {
                this.mStartValuesList.add(start);
                this.mEndValuesList.add(null);
            }
        }
        for (int i2 = 0; i2 < unmatchedEnd.size(); i2++) {
            TransitionValues end = unmatchedEnd.valueAt(i2);
            if (isValidTarget(end.view)) {
                this.mEndValuesList.add(end);
                this.mStartValuesList.add(null);
            }
        }
    }

    private void matchStartAndEnd(TransitionValuesMaps startValues, TransitionValuesMaps endValues) {
        ArrayMap<View, TransitionValues> unmatchedStart = new ArrayMap<>(startValues.mViewValues);
        ArrayMap<View, TransitionValues> unmatchedEnd = new ArrayMap<>(endValues.mViewValues);
        for (int i = 0; i < this.mMatchOrder.length; i++) {
            switch (this.mMatchOrder[i]) {
                case 1:
                    matchInstances(unmatchedStart, unmatchedEnd);
                    break;
                case 2:
                    matchNames(unmatchedStart, unmatchedEnd, startValues.mNameValues, endValues.mNameValues);
                    break;
                case 3:
                    matchIds(unmatchedStart, unmatchedEnd, startValues.mIdValues, endValues.mIdValues);
                    break;
                case 4:
                    matchItemIds(unmatchedStart, unmatchedEnd, startValues.mItemIdValues, endValues.mItemIdValues);
                    break;
            }
        }
        addUnmatched(unmatchedStart, unmatchedEnd);
    }

    public void createAnimators(ViewGroup sceneRoot, TransitionValuesMaps startValues, TransitionValuesMaps endValues, ArrayList<TransitionValues> startValuesList, ArrayList<TransitionValues> endValuesList) {
        TransitionValues start;
        int startValuesListCount;
        boolean hasSeekController;
        int i;
        View view;
        Animator animator;
        long minStartDelay;
        Animator animator2;
        Animator animator3;
        int numExistingAnims;
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        long minStartDelay2 = Long.MAX_VALUE;
        SparseIntArray startDelays = new SparseIntArray();
        int startValuesListCount2 = startValuesList.size();
        boolean hasSeekController2 = getRootTransition().mSeekController != null;
        int i2 = 0;
        while (i2 < startValuesListCount2) {
            TransitionValues start2 = startValuesList.get(i2);
            TransitionValues end = endValuesList.get(i2);
            if (start2 != null && !start2.mTargetedTransitions.contains(this)) {
                start = null;
            } else {
                start = start2;
            }
            if (end != null && !end.mTargetedTransitions.contains(this)) {
                end = null;
            }
            if (start == null && end == null) {
                startValuesListCount = startValuesListCount2;
                hasSeekController = hasSeekController2;
                i = i2;
            } else {
                boolean isChanged = start == null || end == null || isTransitionRequired(start, end);
                if (isChanged) {
                    Animator animator4 = createAnimator(sceneRoot, start, end);
                    if (animator4 != null) {
                        TransitionValues newValues = null;
                        if (end != null) {
                            view = end.view;
                            String[] properties = getTransitionProperties();
                            if (properties != null) {
                                animator2 = animator4;
                                if (properties.length <= 0) {
                                    startValuesListCount = startValuesListCount2;
                                    hasSeekController = hasSeekController2;
                                    i = i2;
                                } else {
                                    TransitionValues infoValues = new TransitionValues(view);
                                    startValuesListCount = startValuesListCount2;
                                    TransitionValues newValues2 = endValues.mViewValues.get(view);
                                    if (newValues2 != null) {
                                        int j = 0;
                                        while (j < properties.length) {
                                            infoValues.values.put(properties[j], newValues2.values.get(properties[j]));
                                            j++;
                                            newValues2 = newValues2;
                                            i2 = i2;
                                            hasSeekController2 = hasSeekController2;
                                        }
                                        hasSeekController = hasSeekController2;
                                        i = i2;
                                    } else {
                                        hasSeekController = hasSeekController2;
                                        i = i2;
                                    }
                                    int numExistingAnims2 = runningAnimators.size();
                                    int j2 = 0;
                                    while (true) {
                                        if (j2 >= numExistingAnims2) {
                                            newValues = infoValues;
                                            animator3 = animator2;
                                            break;
                                        }
                                        Animator anim = runningAnimators.keyAt(j2);
                                        AnimationInfo info = runningAnimators.get(anim);
                                        if (info.mValues == null || info.mView != view) {
                                            numExistingAnims = numExistingAnims2;
                                        } else {
                                            numExistingAnims = numExistingAnims2;
                                            if (info.mName.equals(getName()) && info.mValues.equals(infoValues)) {
                                                newValues = infoValues;
                                                animator3 = null;
                                                break;
                                            }
                                        }
                                        j2++;
                                        numExistingAnims2 = numExistingAnims;
                                    }
                                    animator = animator3;
                                }
                            } else {
                                animator2 = animator4;
                                startValuesListCount = startValuesListCount2;
                                hasSeekController = hasSeekController2;
                                i = i2;
                            }
                            animator3 = animator2;
                            animator = animator3;
                        } else {
                            startValuesListCount = startValuesListCount2;
                            hasSeekController = hasSeekController2;
                            i = i2;
                            view = start.view;
                            animator = animator4;
                        }
                        if (animator != null) {
                            if (this.mPropagation != null) {
                                long delay = this.mPropagation.getStartDelay(sceneRoot, this, start, end);
                                startDelays.put(this.mAnimators.size(), (int) delay);
                                minStartDelay = Math.min(delay, minStartDelay2);
                            } else {
                                minStartDelay = minStartDelay2;
                            }
                            AnimationInfo info2 = new AnimationInfo(view, getName(), this, sceneRoot.getWindowId(), newValues, animator);
                            if (hasSeekController) {
                                AnimatorSet set = new AnimatorSet();
                                set.play(animator);
                                animator = set;
                            }
                            runningAnimators.put(animator, info2);
                            this.mAnimators.add(animator);
                            minStartDelay2 = minStartDelay;
                        }
                    } else {
                        startValuesListCount = startValuesListCount2;
                        hasSeekController = hasSeekController2;
                        i = i2;
                    }
                } else {
                    startValuesListCount = startValuesListCount2;
                    hasSeekController = hasSeekController2;
                    i = i2;
                }
            }
            i2 = i + 1;
            startValuesListCount2 = startValuesListCount;
            hasSeekController2 = hasSeekController;
        }
        if (startDelays.size() != 0) {
            for (int i3 = 0; i3 < startDelays.size(); i3++) {
                int index = startDelays.keyAt(i3);
                AnimationInfo info3 = runningAnimators.get(this.mAnimators.get(index));
                info3.mAnimator.setStartDelay((startDelays.valueAt(i3) - minStartDelay2) + info3.mAnimator.getStartDelay());
            }
        }
    }

    public boolean isValidTarget(View target) {
        int targetId = target.getId();
        if (this.mTargetIdExcludes == null || !this.mTargetIdExcludes.contains(Integer.valueOf(targetId))) {
            if (this.mTargetExcludes == null || !this.mTargetExcludes.contains(target)) {
                if (this.mTargetTypeExcludes != null) {
                    int numTypes = this.mTargetTypeExcludes.size();
                    for (int i = 0; i < numTypes; i++) {
                        Class<?> type = this.mTargetTypeExcludes.get(i);
                        if (type.isInstance(target)) {
                            return false;
                        }
                    }
                }
                if (this.mTargetNameExcludes == null || ViewCompat.getTransitionName(target) == null || !this.mTargetNameExcludes.contains(ViewCompat.getTransitionName(target))) {
                    if ((this.mTargetIds.size() == 0 && this.mTargets.size() == 0 && ((this.mTargetTypes == null || this.mTargetTypes.isEmpty()) && (this.mTargetNames == null || this.mTargetNames.isEmpty()))) || this.mTargetIds.contains(Integer.valueOf(targetId)) || this.mTargets.contains(target)) {
                        return true;
                    }
                    if (this.mTargetNames == null || !this.mTargetNames.contains(ViewCompat.getTransitionName(target))) {
                        if (this.mTargetTypes != null) {
                            for (int i2 = 0; i2 < this.mTargetTypes.size(); i2++) {
                                if (this.mTargetTypes.get(i2).isInstance(target)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private static ArrayMap<Animator, AnimationInfo> getRunningAnimators() {
        ArrayMap<Animator, AnimationInfo> runningAnimators = sRunningAnimators.get();
        if (runningAnimators == null) {
            ArrayMap<Animator, AnimationInfo> runningAnimators2 = new ArrayMap<>();
            sRunningAnimators.set(runningAnimators2);
            return runningAnimators2;
        }
        return runningAnimators;
    }

    public void runAnimators() {
        start();
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        Iterator<Animator> it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator anim = it.next();
            if (runningAnimators.containsKey(anim)) {
                start();
                runAnimator(anim, runningAnimators);
            }
        }
        this.mAnimators.clear();
        end();
    }

    private void runAnimator(Animator animator, final ArrayMap<Animator, AnimationInfo> runningAnimators) {
        if (animator != null) {
            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.Transition.2
                {
                    Transition.this = this;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    Transition.this.mCurrentAnimators.add(animation);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    runningAnimators.remove(animation);
                    Transition.this.mCurrentAnimators.remove(animation);
                }
            });
            animate(animator);
        }
    }

    public void prepareAnimatorsForSeeking() {
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        this.mTotalDuration = 0L;
        for (int i = 0; i < this.mAnimators.size(); i++) {
            Animator anim = this.mAnimators.get(i);
            AnimationInfo info = runningAnimators.get(anim);
            if (anim != null && info != null) {
                if (getDuration() >= 0) {
                    info.mAnimator.setDuration(getDuration());
                }
                if (getStartDelay() >= 0) {
                    info.mAnimator.setStartDelay(getStartDelay() + info.mAnimator.getStartDelay());
                }
                if (getInterpolator() != null) {
                    info.mAnimator.setInterpolator(getInterpolator());
                }
                this.mCurrentAnimators.add(anim);
                this.mTotalDuration = Math.max(this.mTotalDuration, Impl26.getTotalDuration(anim));
            }
        }
        this.mAnimators.clear();
    }

    public Transition addTarget(View target) {
        this.mTargets.add(target);
        return this;
    }

    public Transition addTarget(int targetId) {
        if (targetId != 0) {
            this.mTargetIds.add(Integer.valueOf(targetId));
        }
        return this;
    }

    public Transition addTarget(String targetName) {
        if (this.mTargetNames == null) {
            this.mTargetNames = new ArrayList<>();
        }
        this.mTargetNames.add(targetName);
        return this;
    }

    public Transition addTarget(Class<?> targetType) {
        if (this.mTargetTypes == null) {
            this.mTargetTypes = new ArrayList<>();
        }
        this.mTargetTypes.add(targetType);
        return this;
    }

    public Transition removeTarget(View target) {
        this.mTargets.remove(target);
        return this;
    }

    public Transition removeTarget(int targetId) {
        if (targetId != 0) {
            this.mTargetIds.remove(Integer.valueOf(targetId));
        }
        return this;
    }

    public Transition removeTarget(String targetName) {
        if (this.mTargetNames != null) {
            this.mTargetNames.remove(targetName);
        }
        return this;
    }

    public Transition removeTarget(Class<?> target) {
        if (this.mTargetTypes != null) {
            this.mTargetTypes.remove(target);
        }
        return this;
    }

    private static <T> ArrayList<T> excludeObject(ArrayList<T> list, T target, boolean exclude) {
        if (target != null) {
            if (exclude) {
                return ArrayListManager.add(list, target);
            }
            return ArrayListManager.remove(list, target);
        }
        return list;
    }

    public Transition excludeTarget(View target, boolean exclude) {
        this.mTargetExcludes = excludeView(this.mTargetExcludes, target, exclude);
        return this;
    }

    public Transition excludeTarget(int targetId, boolean exclude) {
        this.mTargetIdExcludes = excludeId(this.mTargetIdExcludes, targetId, exclude);
        return this;
    }

    public Transition excludeTarget(String targetName, boolean exclude) {
        this.mTargetNameExcludes = excludeObject(this.mTargetNameExcludes, targetName, exclude);
        return this;
    }

    public Transition excludeChildren(View target, boolean exclude) {
        this.mTargetChildExcludes = excludeView(this.mTargetChildExcludes, target, exclude);
        return this;
    }

    public Transition excludeChildren(int targetId, boolean exclude) {
        this.mTargetIdChildExcludes = excludeId(this.mTargetIdChildExcludes, targetId, exclude);
        return this;
    }

    private ArrayList<Integer> excludeId(ArrayList<Integer> list, int targetId, boolean exclude) {
        if (targetId > 0) {
            if (exclude) {
                return ArrayListManager.add(list, Integer.valueOf(targetId));
            }
            return ArrayListManager.remove(list, Integer.valueOf(targetId));
        }
        return list;
    }

    private ArrayList<View> excludeView(ArrayList<View> list, View target, boolean exclude) {
        if (target != null) {
            if (exclude) {
                return ArrayListManager.add(list, target);
            }
            return ArrayListManager.remove(list, target);
        }
        return list;
    }

    public Transition excludeTarget(Class<?> type, boolean exclude) {
        this.mTargetTypeExcludes = excludeType(this.mTargetTypeExcludes, type, exclude);
        return this;
    }

    public Transition excludeChildren(Class<?> type, boolean exclude) {
        this.mTargetTypeChildExcludes = excludeType(this.mTargetTypeChildExcludes, type, exclude);
        return this;
    }

    private ArrayList<Class<?>> excludeType(ArrayList<Class<?>> list, Class<?> type, boolean exclude) {
        if (type != null) {
            if (exclude) {
                return ArrayListManager.add(list, type);
            }
            return ArrayListManager.remove(list, type);
        }
        return list;
    }

    public List<Integer> getTargetIds() {
        return this.mTargetIds;
    }

    public List<View> getTargets() {
        return this.mTargets;
    }

    public List<String> getTargetNames() {
        return this.mTargetNames;
    }

    public List<Class<?>> getTargetTypes() {
        return this.mTargetTypes;
    }

    public boolean isSeekingSupported() {
        return false;
    }

    public void captureValues(ViewGroup sceneRoot, boolean start) {
        clearValues(start);
        if ((this.mTargetIds.size() > 0 || this.mTargets.size() > 0) && ((this.mTargetNames == null || this.mTargetNames.isEmpty()) && (this.mTargetTypes == null || this.mTargetTypes.isEmpty()))) {
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                int id = this.mTargetIds.get(i).intValue();
                View view = sceneRoot.findViewById(id);
                if (view != null) {
                    TransitionValues values = new TransitionValues(view);
                    if (start) {
                        captureStartValues(values);
                    } else {
                        captureEndValues(values);
                    }
                    values.mTargetedTransitions.add(this);
                    capturePropagationValues(values);
                    if (start) {
                        addViewValues(this.mStartValues, view, values);
                    } else {
                        addViewValues(this.mEndValues, view, values);
                    }
                }
            }
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                View view2 = this.mTargets.get(i2);
                TransitionValues values2 = new TransitionValues(view2);
                if (start) {
                    captureStartValues(values2);
                } else {
                    captureEndValues(values2);
                }
                values2.mTargetedTransitions.add(this);
                capturePropagationValues(values2);
                if (start) {
                    addViewValues(this.mStartValues, view2, values2);
                } else {
                    addViewValues(this.mEndValues, view2, values2);
                }
            }
        } else {
            captureHierarchy(sceneRoot, start);
        }
        if (!start && this.mNameOverrides != null) {
            int numOverrides = this.mNameOverrides.size();
            ArrayList<View> overriddenViews = new ArrayList<>(numOverrides);
            for (int i3 = 0; i3 < numOverrides; i3++) {
                String fromName = this.mNameOverrides.keyAt(i3);
                overriddenViews.add(this.mStartValues.mNameValues.remove(fromName));
            }
            for (int i4 = 0; i4 < numOverrides; i4++) {
                View view3 = overriddenViews.get(i4);
                if (view3 != null) {
                    String toName = this.mNameOverrides.valueAt(i4);
                    this.mStartValues.mNameValues.put(toName, view3);
                }
            }
        }
    }

    private static void addViewValues(TransitionValuesMaps transitionValuesMaps, View view, TransitionValues transitionValues) {
        transitionValuesMaps.mViewValues.put(view, transitionValues);
        int id = view.getId();
        if (id >= 0) {
            if (transitionValuesMaps.mIdValues.indexOfKey(id) >= 0) {
                transitionValuesMaps.mIdValues.put(id, null);
            } else {
                transitionValuesMaps.mIdValues.put(id, view);
            }
        }
        String name = ViewCompat.getTransitionName(view);
        if (name != null) {
            if (transitionValuesMaps.mNameValues.containsKey(name)) {
                transitionValuesMaps.mNameValues.put(name, null);
            } else {
                transitionValuesMaps.mNameValues.put(name, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listview = (ListView) view.getParent();
            if (listview.getAdapter().hasStableIds()) {
                int position = listview.getPositionForView(view);
                long itemId = listview.getItemIdAtPosition(position);
                if (transitionValuesMaps.mItemIdValues.indexOfKey(itemId) >= 0) {
                    View alreadyMatched = transitionValuesMaps.mItemIdValues.get(itemId);
                    if (alreadyMatched != null) {
                        alreadyMatched.setHasTransientState(false);
                        transitionValuesMaps.mItemIdValues.put(itemId, null);
                        return;
                    }
                    return;
                }
                view.setHasTransientState(true);
                transitionValuesMaps.mItemIdValues.put(itemId, view);
            }
        }
    }

    public void clearValues(boolean start) {
        if (start) {
            this.mStartValues.mViewValues.clear();
            this.mStartValues.mIdValues.clear();
            this.mStartValues.mItemIdValues.clear();
            return;
        }
        this.mEndValues.mViewValues.clear();
        this.mEndValues.mIdValues.clear();
        this.mEndValues.mItemIdValues.clear();
    }

    private void captureHierarchy(View view, boolean start) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        if (this.mTargetIdExcludes != null && this.mTargetIdExcludes.contains(Integer.valueOf(id))) {
            return;
        }
        if (this.mTargetExcludes != null && this.mTargetExcludes.contains(view)) {
            return;
        }
        if (this.mTargetTypeExcludes != null) {
            int numTypes = this.mTargetTypeExcludes.size();
            for (int i = 0; i < numTypes; i++) {
                if (this.mTargetTypeExcludes.get(i).isInstance(view)) {
                    return;
                }
            }
        }
        if (view.getParent() instanceof ViewGroup) {
            TransitionValues values = new TransitionValues(view);
            if (start) {
                captureStartValues(values);
            } else {
                captureEndValues(values);
            }
            values.mTargetedTransitions.add(this);
            capturePropagationValues(values);
            if (start) {
                addViewValues(this.mStartValues, view, values);
            } else {
                addViewValues(this.mEndValues, view, values);
            }
        }
        if (view instanceof ViewGroup) {
            if (this.mTargetIdChildExcludes != null && this.mTargetIdChildExcludes.contains(Integer.valueOf(id))) {
                return;
            }
            if (this.mTargetChildExcludes != null && this.mTargetChildExcludes.contains(view)) {
                return;
            }
            if (this.mTargetTypeChildExcludes != null) {
                int numTypes2 = this.mTargetTypeChildExcludes.size();
                for (int i2 = 0; i2 < numTypes2; i2++) {
                    if (this.mTargetTypeChildExcludes.get(i2).isInstance(view)) {
                        return;
                    }
                }
            }
            ViewGroup parent = (ViewGroup) view;
            for (int i3 = 0; i3 < parent.getChildCount(); i3++) {
                captureHierarchy(parent.getChildAt(i3), start);
            }
        }
    }

    public TransitionValues getTransitionValues(View view, boolean start) {
        if (this.mParent != null) {
            return this.mParent.getTransitionValues(view, start);
        }
        TransitionValuesMaps valuesMaps = start ? this.mStartValues : this.mEndValues;
        return valuesMaps.mViewValues.get(view);
    }

    public TransitionValues getMatchedTransitionValues(View view, boolean viewInStart) {
        if (this.mParent != null) {
            return this.mParent.getMatchedTransitionValues(view, viewInStart);
        }
        ArrayList<TransitionValues> lookIn = viewInStart ? this.mStartValuesList : this.mEndValuesList;
        if (lookIn == null) {
            return null;
        }
        int count = lookIn.size();
        int index = -1;
        int i = 0;
        while (true) {
            if (i >= count) {
                break;
            }
            TransitionValues values = lookIn.get(i);
            if (values == null) {
                return null;
            }
            if (values.view != view) {
                i++;
            } else {
                index = i;
                break;
            }
        }
        if (index < 0) {
            return null;
        }
        ArrayList<TransitionValues> matchIn = viewInStart ? this.mEndValuesList : this.mStartValuesList;
        return matchIn.get(index);
    }

    public void pause(View sceneRoot) {
        if (!this.mEnded) {
            int numAnimators = this.mCurrentAnimators.size();
            Animator[] cache = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
            this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
            for (int i = numAnimators - 1; i >= 0; i--) {
                Animator animator = cache[i];
                cache[i] = null;
                animator.pause();
            }
            this.mAnimatorCache = cache;
            notifyListeners(TransitionNotification.ON_PAUSE, false);
            this.mPaused = true;
        }
    }

    public void resume(View sceneRoot) {
        if (this.mPaused) {
            if (!this.mEnded) {
                int numAnimators = this.mCurrentAnimators.size();
                Animator[] cache = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
                this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
                for (int i = numAnimators - 1; i >= 0; i--) {
                    Animator animator = cache[i];
                    cache[i] = null;
                    animator.resume();
                }
                this.mAnimatorCache = cache;
                notifyListeners(TransitionNotification.ON_RESUME, false);
            }
            this.mPaused = false;
        }
    }

    public boolean hasAnimators() {
        return !this.mCurrentAnimators.isEmpty();
    }

    public void playTransition(ViewGroup sceneRoot) {
        AnimationInfo oldInfo;
        boolean cancel;
        this.mStartValuesList = new ArrayList<>();
        this.mEndValuesList = new ArrayList<>();
        matchStartAndEnd(this.mStartValues, this.mEndValues);
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        int numOldAnims = runningAnimators.size();
        WindowId windowId = sceneRoot.getWindowId();
        for (int i = numOldAnims - 1; i >= 0; i--) {
            Animator anim = runningAnimators.keyAt(i);
            if (anim != null && (oldInfo = runningAnimators.get(anim)) != null && oldInfo.mView != null && windowId.equals(oldInfo.mWindowId)) {
                TransitionValues oldValues = oldInfo.mValues;
                View oldView = oldInfo.mView;
                TransitionValues startValues = getTransitionValues(oldView, true);
                TransitionValues endValues = getMatchedTransitionValues(oldView, true);
                if (startValues == null && endValues == null) {
                    endValues = this.mEndValues.mViewValues.get(oldView);
                }
                if ((startValues != null || endValues != null) && oldInfo.mTransition.isTransitionRequired(oldValues, endValues)) {
                    cancel = true;
                } else {
                    cancel = false;
                }
                if (cancel) {
                    Transition transition = oldInfo.mTransition;
                    if (transition.getRootTransition().mSeekController != null) {
                        anim.cancel();
                        transition.mCurrentAnimators.remove(anim);
                        runningAnimators.remove(anim);
                        if (transition.mCurrentAnimators.size() == 0) {
                            transition.notifyListeners(TransitionNotification.ON_CANCEL, false);
                            if (!transition.mEnded) {
                                transition.mEnded = true;
                                transition.notifyListeners(TransitionNotification.ON_END, false);
                            }
                        }
                    } else if (anim.isRunning() || anim.isStarted()) {
                        anim.cancel();
                    } else {
                        runningAnimators.remove(anim);
                    }
                }
            }
        }
        createAnimators(sceneRoot, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
        if (this.mSeekController == null) {
            runAnimators();
        } else if (Build.VERSION.SDK_INT >= 34) {
            prepareAnimatorsForSeeking();
            this.mSeekController.initPlayTime();
            this.mSeekController.ready();
        }
    }

    public boolean isTransitionRequired(TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return false;
        }
        String[] properties = getTransitionProperties();
        if (properties != null) {
            for (String property : properties) {
                if (isValueChanged(startValues, endValues, property)) {
                    return true;
                }
            }
            return false;
        }
        for (String key : startValues.values.keySet()) {
            if (isValueChanged(startValues, endValues, key)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValueChanged(TransitionValues oldValues, TransitionValues newValues, String key) {
        Object oldValue = oldValues.values.get(key);
        Object newValue = newValues.values.get(key);
        if (oldValue == null && newValue == null) {
            return false;
        }
        if (oldValue == null || newValue == null) {
            return true;
        }
        boolean changed = !oldValue.equals(newValue);
        return changed;
    }

    protected void animate(Animator animator) {
        if (animator == null) {
            end();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(getStartDelay() + animator.getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.Transition.3
            {
                Transition.this = this;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Transition.this.end();
                animation.removeListener(this);
            }
        });
        animator.start();
    }

    public void start() {
        if (this.mNumInstances == 0) {
            notifyListeners(TransitionNotification.ON_START, false);
            this.mEnded = false;
        }
        this.mNumInstances++;
    }

    public void end() {
        this.mNumInstances--;
        if (this.mNumInstances == 0) {
            notifyListeners(TransitionNotification.ON_END, false);
            for (int i = 0; i < this.mStartValues.mItemIdValues.size(); i++) {
                View view = this.mStartValues.mItemIdValues.valueAt(i);
                if (view != null) {
                    view.setHasTransientState(false);
                }
            }
            for (int i2 = 0; i2 < this.mEndValues.mItemIdValues.size(); i2++) {
                View view2 = this.mEndValues.mItemIdValues.valueAt(i2);
                if (view2 != null) {
                    view2.setHasTransientState(false);
                }
            }
            this.mEnded = true;
        }
    }

    public void forceToEnd(ViewGroup sceneRoot) {
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        int numOldAnims = runningAnimators.size();
        if (sceneRoot == null || numOldAnims == 0) {
            return;
        }
        WindowId windowId = sceneRoot.getWindowId();
        ArrayMap<Animator, AnimationInfo> oldAnimators = new ArrayMap<>(runningAnimators);
        runningAnimators.clear();
        for (int i = numOldAnims - 1; i >= 0; i--) {
            AnimationInfo info = oldAnimators.valueAt(i);
            if (info.mView != null && windowId.equals(info.mWindowId)) {
                Animator anim = oldAnimators.keyAt(i);
                anim.end();
            }
        }
    }

    public void cancel() {
        int numAnimators = this.mCurrentAnimators.size();
        Animator[] cache = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
        this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
        for (int i = numAnimators - 1; i >= 0; i--) {
            Animator animator = cache[i];
            cache[i] = null;
            animator.cancel();
        }
        this.mAnimatorCache = cache;
        notifyListeners(TransitionNotification.ON_CANCEL, false);
    }

    public Transition addListener(TransitionListener listener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(listener);
        return this;
    }

    public Transition removeListener(TransitionListener listener) {
        if (this.mListeners == null) {
            return this;
        }
        if (!this.mListeners.remove(listener) && this.mCloneParent != null) {
            this.mCloneParent.removeListener(listener);
        }
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
        return this;
    }

    public void setPathMotion(PathMotion pathMotion) {
        if (pathMotion == null) {
            this.mPathMotion = STRAIGHT_PATH_MOTION;
        } else {
            this.mPathMotion = pathMotion;
        }
    }

    public PathMotion getPathMotion() {
        return this.mPathMotion;
    }

    public void setEpicenterCallback(EpicenterCallback epicenterCallback) {
        this.mEpicenterCallback = epicenterCallback;
    }

    public EpicenterCallback getEpicenterCallback() {
        return this.mEpicenterCallback;
    }

    public Rect getEpicenter() {
        if (this.mEpicenterCallback == null) {
            return null;
        }
        return this.mEpicenterCallback.onGetEpicenter(this);
    }

    public void setPropagation(TransitionPropagation transitionPropagation) {
        this.mPropagation = transitionPropagation;
    }

    public TransitionPropagation getPropagation() {
        return this.mPropagation;
    }

    public void capturePropagationValues(TransitionValues transitionValues) {
        String[] propertyNames;
        if (this.mPropagation == null || transitionValues.values.isEmpty() || (propertyNames = this.mPropagation.getPropagationProperties()) == null) {
            return;
        }
        boolean containsAll = true;
        int i = 0;
        while (true) {
            if (i >= propertyNames.length) {
                break;
            } else if (transitionValues.values.containsKey(propertyNames[i])) {
                i++;
            } else {
                containsAll = false;
                break;
            }
        }
        if (!containsAll) {
            this.mPropagation.captureValues(transitionValues);
        }
    }

    public void setCanRemoveViews(boolean canRemoveViews) {
        this.mCanRemoveViews = canRemoveViews;
    }

    public String toString() {
        return toString("");
    }

    @Override // 
    /* renamed from: clone */
    public Transition mo212clone() {
        try {
            Transition clone = (Transition) super.clone();
            clone.mAnimators = new ArrayList<>();
            clone.mStartValues = new TransitionValuesMaps();
            clone.mEndValues = new TransitionValuesMaps();
            clone.mStartValuesList = null;
            clone.mEndValuesList = null;
            clone.mSeekController = null;
            clone.mCloneParent = this;
            clone.mListeners = null;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return this.mName;
    }

    public void notifyListeners(TransitionNotification notification, boolean isReversed) {
        notifyFromTransition(this, notification, isReversed);
    }

    private void notifyFromTransition(Transition transition, TransitionNotification notification, boolean isReversed) {
        if (this.mCloneParent != null) {
            this.mCloneParent.notifyFromTransition(transition, notification, isReversed);
        }
        if (this.mListeners != null && !this.mListeners.isEmpty()) {
            int size = this.mListeners.size();
            TransitionListener[] listeners = this.mListenersCache == null ? new TransitionListener[size] : this.mListenersCache;
            this.mListenersCache = null;
            TransitionListener[] listeners2 = (TransitionListener[]) this.mListeners.toArray(listeners);
            for (int i = 0; i < size; i++) {
                notification.notifyListener(listeners2[i], transition, isReversed);
                listeners2[i] = null;
            }
            this.mListenersCache = listeners2;
        }
    }

    public final long getTotalDurationMillis() {
        return this.mTotalDuration;
    }

    public void setCurrentPlayTimeMillis(long playTimeMillis, long lastPlayTimeMillis) {
        long duration = getTotalDurationMillis();
        boolean isReversed = playTimeMillis < lastPlayTimeMillis;
        if ((lastPlayTimeMillis < 0 && playTimeMillis >= 0) || (lastPlayTimeMillis > duration && playTimeMillis <= duration)) {
            this.mEnded = false;
            notifyListeners(TransitionNotification.ON_START, isReversed);
        }
        Animator[] cache = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
        this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
        int i = 0;
        for (int numAnimators = this.mCurrentAnimators.size(); i < numAnimators; numAnimators = numAnimators) {
            Animator animator = cache[i];
            cache[i] = null;
            long animDuration = Impl26.getTotalDuration(animator);
            boolean isReversed2 = isReversed;
            long playTime = Math.min(Math.max(0L, playTimeMillis), animDuration);
            Impl26.setCurrentPlayTime(animator, playTime);
            i++;
            isReversed = isReversed2;
        }
        boolean isReversed3 = isReversed;
        this.mAnimatorCache = cache;
        if ((playTimeMillis > duration && lastPlayTimeMillis <= duration) || (playTimeMillis < 0 && lastPlayTimeMillis >= 0)) {
            if (playTimeMillis > duration) {
                this.mEnded = true;
            }
            notifyListeners(TransitionNotification.ON_END, isReversed3);
        }
    }

    public String toString(String indent) {
        StringBuilder result = new StringBuilder(indent).append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(": ");
        if (this.mDuration != -1) {
            result.append("dur(").append(this.mDuration).append(") ");
        }
        if (this.mStartDelay != -1) {
            result.append("dly(").append(this.mStartDelay).append(") ");
        }
        if (this.mInterpolator != null) {
            result.append("interp(").append(this.mInterpolator).append(") ");
        }
        if (this.mTargetIds.size() > 0 || this.mTargets.size() > 0) {
            result.append("tgts(");
            if (this.mTargetIds.size() > 0) {
                for (int i = 0; i < this.mTargetIds.size(); i++) {
                    if (i > 0) {
                        result.append(", ");
                    }
                    result.append(this.mTargetIds.get(i));
                }
            }
            if (this.mTargets.size() > 0) {
                for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                    if (i2 > 0) {
                        result.append(", ");
                    }
                    result.append(this.mTargets.get(i2));
                }
            }
            result.append(")");
        }
        return result.toString();
    }

    /* loaded from: classes.dex */
    public interface TransitionListener {
        void onTransitionCancel(Transition transition);

        void onTransitionEnd(Transition transition);

        void onTransitionPause(Transition transition);

        void onTransitionResume(Transition transition);

        void onTransitionStart(Transition transition);

        default void onTransitionStart(Transition transition, boolean isReverse) {
            onTransitionStart(transition);
        }

        default void onTransitionEnd(Transition transition, boolean isReverse) {
            onTransitionEnd(transition);
        }
    }

    /* loaded from: classes.dex */
    public static class AnimationInfo {
        Animator mAnimator;
        String mName;
        Transition mTransition;
        TransitionValues mValues;
        View mView;
        WindowId mWindowId;

        AnimationInfo(View view, String name, Transition transition, WindowId windowId, TransitionValues values, Animator animator) {
            this.mView = view;
            this.mName = name;
            this.mValues = values;
            this.mWindowId = windowId;
            this.mTransition = transition;
            this.mAnimator = animator;
        }
    }

    /* loaded from: classes.dex */
    public static class ArrayListManager {
        private ArrayListManager() {
        }

        static <T> ArrayList<T> add(ArrayList<T> list, T item) {
            if (list == null) {
                list = new ArrayList<>();
            }
            if (!list.contains(item)) {
                list.add(item);
            }
            return list;
        }

        static <T> ArrayList<T> remove(ArrayList<T> list, T item) {
            if (list != null) {
                list.remove(item);
                if (list.isEmpty()) {
                    return null;
                }
                return list;
            }
            return list;
        }
    }

    /* loaded from: classes.dex */
    public interface TransitionNotification {
        public static final TransitionNotification ON_START = new TransitionNotification() { // from class: androidx.transition.Transition$TransitionNotification$$ExternalSyntheticLambda0
            @Override // androidx.transition.Transition.TransitionNotification
            public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                transitionListener.onTransitionStart(transition, z);
            }
        };
        public static final TransitionNotification ON_END = new TransitionNotification() { // from class: androidx.transition.Transition$TransitionNotification$$ExternalSyntheticLambda1
            @Override // androidx.transition.Transition.TransitionNotification
            public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                transitionListener.onTransitionEnd(transition, z);
            }
        };
        public static final TransitionNotification ON_CANCEL = new TransitionNotification() { // from class: androidx.transition.Transition$TransitionNotification$$ExternalSyntheticLambda2
            @Override // androidx.transition.Transition.TransitionNotification
            public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                Transition.TransitionNotification.lambda$static$0(transitionListener, transition, z);
            }
        };
        public static final TransitionNotification ON_PAUSE = new TransitionNotification() { // from class: androidx.transition.Transition$TransitionNotification$$ExternalSyntheticLambda3
            @Override // androidx.transition.Transition.TransitionNotification
            public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                Transition.TransitionNotification.lambda$static$1(transitionListener, transition, z);
            }
        };
        public static final TransitionNotification ON_RESUME = new TransitionNotification() { // from class: androidx.transition.Transition$TransitionNotification$$ExternalSyntheticLambda4
            @Override // androidx.transition.Transition.TransitionNotification
            public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                Transition.TransitionNotification.lambda$static$2(transitionListener, transition, z);
            }
        };

        void notifyListener(TransitionListener transitionListener, Transition transition, boolean z);

        static /* synthetic */ void lambda$static$0(TransitionListener listener, Transition transition, boolean isReversed) {
            listener.onTransitionCancel(transition);
        }

        static /* synthetic */ void lambda$static$1(TransitionListener listener, Transition transition, boolean isReversed) {
            listener.onTransitionPause(transition);
        }

        static /* synthetic */ void lambda$static$2(TransitionListener listener, Transition transition, boolean isReversed) {
            listener.onTransitionResume(transition);
        }
    }

    /* loaded from: classes.dex */
    public static class Impl26 {
        private Impl26() {
        }

        static long getTotalDuration(Animator animator) {
            return animator.getTotalDuration();
        }

        static void setCurrentPlayTime(Animator animator, long playTimeMillis) {
            ((AnimatorSet) animator).setCurrentPlayTime(playTimeMillis);
        }
    }

    /* loaded from: classes.dex */
    public class SeekController extends TransitionListenerAdapter implements TransitionSeekController, DynamicAnimation.OnAnimationUpdateListener {
        private boolean mIsCanceled;
        private boolean mIsReady;
        private Runnable mResetToStartState;
        private SpringAnimation mSpringAnimation;
        private long mCurrentPlayTime = -1;
        private ArrayList<Consumer<TransitionSeekController>> mOnReadyListeners = null;
        private ArrayList<Consumer<TransitionSeekController>> mOnProgressListeners = null;
        private Consumer<TransitionSeekController>[] mListenerCache = null;
        private final VelocityTracker1D mVelocityTracker = new VelocityTracker1D();

        SeekController() {
            Transition.this = this$0;
        }

        @Override // androidx.transition.TransitionSeekController
        public long getDurationMillis() {
            return Transition.this.getTotalDurationMillis();
        }

        @Override // androidx.transition.TransitionSeekController
        public long getCurrentPlayTimeMillis() {
            return Math.min(getDurationMillis(), Math.max(0L, this.mCurrentPlayTime));
        }

        @Override // androidx.transition.TransitionSeekController
        public float getCurrentFraction() {
            return ((float) getCurrentPlayTimeMillis()) / ((float) getDurationMillis());
        }

        @Override // androidx.transition.TransitionSeekController
        public boolean isReady() {
            return this.mIsReady;
        }

        public void ready() {
            this.mIsReady = true;
            if (this.mOnReadyListeners != null) {
                ArrayList<Consumer<TransitionSeekController>> onReadyListeners = this.mOnReadyListeners;
                this.mOnReadyListeners = null;
                for (int i = 0; i < onReadyListeners.size(); i++) {
                    onReadyListeners.get(i).accept(this);
                }
            }
            callProgressListeners();
        }

        @Override // androidx.transition.TransitionSeekController
        public void setCurrentPlayTimeMillis(long playTimeMillis) {
            if (this.mSpringAnimation != null) {
                throw new IllegalStateException("setCurrentPlayTimeMillis() called after animation has been started");
            }
            if (playTimeMillis == this.mCurrentPlayTime || !isReady()) {
                return;
            }
            long targetPlayTime = playTimeMillis;
            if (!this.mIsCanceled) {
                if (targetPlayTime == 0 && this.mCurrentPlayTime > 0) {
                    targetPlayTime = -1;
                } else {
                    long duration = getDurationMillis();
                    if (targetPlayTime == duration && this.mCurrentPlayTime < duration) {
                        targetPlayTime = 1 + duration;
                    }
                }
                if (targetPlayTime != this.mCurrentPlayTime) {
                    Transition.this.setCurrentPlayTimeMillis(targetPlayTime, this.mCurrentPlayTime);
                    this.mCurrentPlayTime = targetPlayTime;
                }
            }
            callProgressListeners();
            this.mVelocityTracker.addDataPoint(AnimationUtils.currentAnimationTimeMillis(), (float) targetPlayTime);
        }

        void initPlayTime() {
            long playTime = getDurationMillis() == 0 ? 1L : 0L;
            Transition.this.setCurrentPlayTimeMillis(playTime, this.mCurrentPlayTime);
            this.mCurrentPlayTime = playTime;
        }

        @Override // androidx.transition.TransitionSeekController
        public void setCurrentFraction(float fraction) {
            if (this.mSpringAnimation != null) {
                throw new IllegalStateException("setCurrentFraction() called after animation has been started");
            }
            setCurrentPlayTimeMillis(((float) getDurationMillis()) * fraction);
        }

        @Override // androidx.transition.TransitionSeekController
        public void addOnReadyListener(Consumer<TransitionSeekController> onReadyListener) {
            if (isReady()) {
                onReadyListener.accept(this);
                return;
            }
            if (this.mOnReadyListeners == null) {
                this.mOnReadyListeners = new ArrayList<>();
            }
            this.mOnReadyListeners.add(onReadyListener);
        }

        @Override // androidx.transition.TransitionSeekController
        public void removeOnReadyListener(Consumer<TransitionSeekController> onReadyListener) {
            if (this.mOnReadyListeners != null) {
                this.mOnReadyListeners.remove(onReadyListener);
                if (this.mOnReadyListeners.isEmpty()) {
                    this.mOnReadyListeners = null;
                }
            }
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition transition) {
            this.mIsCanceled = true;
        }

        @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
        public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
            long time = Math.max(-1L, Math.min(getDurationMillis() + 1, Math.round(value)));
            Transition.this.setCurrentPlayTimeMillis(time, this.mCurrentPlayTime);
            this.mCurrentPlayTime = time;
            callProgressListeners();
        }

        private void ensureAnimation() {
            if (this.mSpringAnimation != null) {
                return;
            }
            this.mVelocityTracker.addDataPoint(AnimationUtils.currentAnimationTimeMillis(), (float) this.mCurrentPlayTime);
            this.mSpringAnimation = new SpringAnimation(new FloatValueHolder());
            SpringForce springForce = new SpringForce();
            springForce.setDampingRatio(1.0f);
            springForce.setStiffness(200.0f);
            this.mSpringAnimation.setSpring(springForce);
            this.mSpringAnimation.setStartValue((float) this.mCurrentPlayTime);
            this.mSpringAnimation.addUpdateListener(this);
            this.mSpringAnimation.setStartVelocity(this.mVelocityTracker.calculateVelocity());
            this.mSpringAnimation.setMaxValue((float) (getDurationMillis() + 1));
            this.mSpringAnimation.setMinValue(-1.0f);
            this.mSpringAnimation.setMinimumVisibleChange(4.0f);
            this.mSpringAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: androidx.transition.Transition$SeekController$$ExternalSyntheticLambda0
                @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
                public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                    Transition.SeekController.this.m124x76b2d240(dynamicAnimation, z, f, f2);
                }
            });
        }

        /* renamed from: lambda$ensureAnimation$0$androidx-transition-Transition$SeekController */
        public /* synthetic */ void m124x76b2d240(DynamicAnimation anim, boolean canceled, float value, float velocity) {
            if (!canceled) {
                boolean isReversed = value < 1.0f;
                if (isReversed) {
                    long duration = getDurationMillis();
                    Transition child = ((TransitionSet) Transition.this).getTransitionAt(0);
                    Transition cloneParent = child.mCloneParent;
                    child.mCloneParent = null;
                    Transition.this.setCurrentPlayTimeMillis(-1L, this.mCurrentPlayTime);
                    Transition.this.setCurrentPlayTimeMillis(duration, -1L);
                    this.mCurrentPlayTime = duration;
                    if (this.mResetToStartState != null) {
                        this.mResetToStartState.run();
                    }
                    Transition.this.mAnimators.clear();
                    if (cloneParent != null) {
                        cloneParent.notifyListeners(TransitionNotification.ON_END, true);
                        return;
                    }
                    return;
                }
                Transition.this.notifyListeners(TransitionNotification.ON_END, false);
            }
        }

        @Override // androidx.transition.TransitionSeekController
        public void animateToEnd() {
            ensureAnimation();
            this.mSpringAnimation.animateToFinalPosition((float) (getDurationMillis() + 1));
        }

        @Override // androidx.transition.TransitionSeekController
        public void animateToStart(Runnable resetToStartState) {
            this.mResetToStartState = resetToStartState;
            ensureAnimation();
            this.mSpringAnimation.animateToFinalPosition(0.0f);
        }

        @Override // androidx.transition.TransitionSeekController
        public void addOnProgressChangedListener(Consumer<TransitionSeekController> consumer) {
            if (this.mOnProgressListeners == null) {
                this.mOnProgressListeners = new ArrayList<>();
            }
            this.mOnProgressListeners.add(consumer);
        }

        @Override // androidx.transition.TransitionSeekController
        public void removeOnProgressChangedListener(Consumer<TransitionSeekController> consumer) {
            if (this.mOnProgressListeners != null) {
                this.mOnProgressListeners.remove(consumer);
            }
        }

        private void callProgressListeners() {
            if (this.mOnProgressListeners == null || this.mOnProgressListeners.isEmpty()) {
                return;
            }
            int size = this.mOnProgressListeners.size();
            if (this.mListenerCache == null) {
                this.mListenerCache = new Consumer[size];
            }
            Consumer<TransitionSeekController>[] cache = (Consumer[]) this.mOnProgressListeners.toArray(this.mListenerCache);
            this.mListenerCache = null;
            for (int i = 0; i < size; i++) {
                cache[i].accept(this);
                cache[i] = null;
            }
            this.mListenerCache = cache;
        }
    }
}