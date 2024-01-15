package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;

/* loaded from: output.jar:androidx/constraintlayout/core/widgets/analyzer/Grouping.class */
public class Grouping {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_GROUPING = false;

    public static WidgetGroup findDependents(ConstraintWidget constraintWidget, int i, ArrayList<WidgetGroup> arrayList, WidgetGroup widgetGroup) {
        WidgetGroup widgetGroup2;
        int i2 = i == 0 ? constraintWidget.horizontalGroup : constraintWidget.verticalGroup;
        if (i2 != -1 && (widgetGroup == null || i2 != widgetGroup.id)) {
            int i3 = 0;
            while (true) {
                widgetGroup2 = widgetGroup;
                if (i3 >= arrayList.size()) {
                    break;
                }
                widgetGroup2 = arrayList.get(i3);
                if (widgetGroup2.getId() != i2) {
                    i3++;
                } else if (widgetGroup != null) {
                    widgetGroup.moveTo(i, widgetGroup2);
                    arrayList.remove(widgetGroup);
                }
            }
        } else {
            widgetGroup2 = widgetGroup;
            if (i2 != -1) {
                return widgetGroup;
            }
        }
        WidgetGroup widgetGroup3 = widgetGroup2;
        if (widgetGroup2 == null) {
            WidgetGroup widgetGroup4 = widgetGroup2;
            if (constraintWidget instanceof HelperWidget) {
                int findGroupInDependents = ((HelperWidget) constraintWidget).findGroupInDependents(i);
                widgetGroup4 = widgetGroup2;
                if (findGroupInDependents != -1) {
                    int i4 = 0;
                    while (true) {
                        widgetGroup4 = widgetGroup2;
                        if (i4 >= arrayList.size()) {
                            break;
                        }
                        widgetGroup4 = arrayList.get(i4);
                        if (widgetGroup4.getId() == findGroupInDependents) {
                            break;
                        }
                        i4++;
                    }
                }
            }
            WidgetGroup widgetGroup5 = widgetGroup4;
            if (widgetGroup4 == null) {
                widgetGroup5 = new WidgetGroup(i);
            }
            arrayList.add(widgetGroup5);
            widgetGroup3 = widgetGroup5;
        }
        if (widgetGroup3.add(constraintWidget)) {
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                ConstraintAnchor anchor = guideline.getAnchor();
                int i5 = 0;
                if (guideline.getOrientation() == 0) {
                    i5 = 1;
                }
                anchor.findDependents(i5, arrayList, widgetGroup3);
            }
            if (i == 0) {
                constraintWidget.horizontalGroup = widgetGroup3.getId();
                constraintWidget.mLeft.findDependents(i, arrayList, widgetGroup3);
                constraintWidget.mRight.findDependents(i, arrayList, widgetGroup3);
            } else {
                constraintWidget.verticalGroup = widgetGroup3.getId();
                constraintWidget.mTop.findDependents(i, arrayList, widgetGroup3);
                constraintWidget.mBaseline.findDependents(i, arrayList, widgetGroup3);
                constraintWidget.mBottom.findDependents(i, arrayList, widgetGroup3);
            }
            constraintWidget.mCenter.findDependents(i, arrayList, widgetGroup3);
        }
        return widgetGroup3;
    }

    private static WidgetGroup findGroup(ArrayList<WidgetGroup> arrayList, int i) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            WidgetGroup widgetGroup = arrayList.get(i2);
            if (i == widgetGroup.id) {
                return widgetGroup;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:197:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x069d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean simpleSolvingPass(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r6, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer r7) {
        /*
            Method dump skipped, instructions count: 1712
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Grouping.simpleSolvingPass(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer):boolean");
    }

    public static boolean validInGroup(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, ConstraintWidget.DimensionBehaviour dimensionBehaviour3, ConstraintWidget.DimensionBehaviour dimensionBehaviour4) {
        return (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour2 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT));
    }
}