package androidx.transition;

/* loaded from: output.jar:androidx/transition/Styleable.class */
class Styleable {
    static final int[] TRANSITION_TARGET = {16842799, 16843740, 16843841, 16843842, 16843853, 16843854};
    static final int[] TRANSITION_MANAGER = {16843741, 16843742, 16843743};
    static final int[] TRANSITION = {16843073, 16843160, 16843746, 16843855};
    static final int[] CHANGE_BOUNDS = {16843983};
    static final int[] VISIBILITY_TRANSITION = {16843900};
    static final int[] FADE = {16843745};
    static final int[] CHANGE_TRANSFORM = {16843964, 16843965};
    static final int[] SLIDE = {16843824};
    static final int[] TRANSITION_SET = {16843744};
    static final int[] ARC_MOTION = {16843901, 16843902, 16843903};
    static final int[] PATTERN_PATH_MOTION = {16843978};

    /* loaded from: output.jar:androidx/transition/Styleable$ArcMotion.class */
    interface ArcMotion {
        public static final int MAXIMUM_ANGLE = 2;
        public static final int MINIMUM_HORIZONTAL_ANGLE = 0;
        public static final int MINIMUM_VERTICAL_ANGLE = 1;
    }

    /* loaded from: output.jar:androidx/transition/Styleable$ChangeBounds.class */
    interface ChangeBounds {
        public static final int RESIZE_CLIP = 0;
    }

    /* loaded from: output.jar:androidx/transition/Styleable$ChangeTransform.class */
    interface ChangeTransform {
        public static final int REPARENT = 0;
        public static final int REPARENT_WITH_OVERLAY = 1;
    }

    /* loaded from: output.jar:androidx/transition/Styleable$Fade.class */
    interface Fade {
        public static final int FADING_MODE = 0;
    }

    /* loaded from: output.jar:androidx/transition/Styleable$PatternPathMotion.class */
    interface PatternPathMotion {
        public static final int PATTERN_PATH_DATA = 0;
    }

    /* loaded from: output.jar:androidx/transition/Styleable$Slide.class */
    interface Slide {
        public static final int SLIDE_EDGE = 0;
    }

    /* loaded from: output.jar:androidx/transition/Styleable$Transition.class */
    interface Transition {
        public static final int DURATION = 1;
        public static final int INTERPOLATOR = 0;
        public static final int MATCH_ORDER = 3;
        public static final int START_DELAY = 2;
    }

    /* loaded from: output.jar:androidx/transition/Styleable$TransitionManager.class */
    interface TransitionManager {
        public static final int FROM_SCENE = 0;
        public static final int TO_SCENE = 1;
        public static final int TRANSITION = 2;
    }

    /* loaded from: output.jar:androidx/transition/Styleable$TransitionSet.class */
    interface TransitionSet {
        public static final int TRANSITION_ORDERING = 0;
    }

    /* loaded from: output.jar:androidx/transition/Styleable$TransitionTarget.class */
    interface TransitionTarget {
        public static final int EXCLUDE_CLASS = 3;
        public static final int EXCLUDE_ID = 2;
        public static final int EXCLUDE_NAME = 5;
        public static final int TARGET_CLASS = 0;
        public static final int TARGET_ID = 1;
        public static final int TARGET_NAME = 4;
    }

    /* loaded from: output.jar:androidx/transition/Styleable$VisibilityTransition.class */
    interface VisibilityTransition {
        public static final int TRANSITION_VISIBILITY_MODE = 0;
    }

    private Styleable() {
    }
}