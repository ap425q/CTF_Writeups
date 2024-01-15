package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: output.jar:androidx/constraintlayout/core/widgets/analyzer/HelperReferences.class */
public class HelperReferences extends WidgetRun {
    public HelperReferences(ConstraintWidget constraintWidget) {
        super(constraintWidget);
    }

    private void addDependency(DependencyNode dependencyNode) {
        this.start.dependencies.add(dependencyNode);
        dependencyNode.targets.add(this.start);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void apply() {
        if (this.widget instanceof Barrier) {
            this.start.delegateToWidgetRun = true;
            Barrier barrier = (Barrier) this.widget;
            int barrierType = barrier.getBarrierType();
            boolean allowsGoneWidget = barrier.getAllowsGoneWidget();
            if (barrierType == 0) {
                this.start.type = DependencyNode.Type.LEFT;
                for (int i = 0; i < barrier.mWidgetsCount; i++) {
                    ConstraintWidget constraintWidget = barrier.mWidgets[i];
                    if (allowsGoneWidget || constraintWidget.getVisibility() != 8) {
                        DependencyNode dependencyNode = constraintWidget.horizontalRun.start;
                        dependencyNode.dependencies.add(this.start);
                        this.start.targets.add(dependencyNode);
                    }
                }
                addDependency(this.widget.horizontalRun.start);
                addDependency(this.widget.horizontalRun.end);
            } else if (barrierType == 1) {
                this.start.type = DependencyNode.Type.RIGHT;
                for (int i2 = 0; i2 < barrier.mWidgetsCount; i2++) {
                    ConstraintWidget constraintWidget2 = barrier.mWidgets[i2];
                    if (allowsGoneWidget || constraintWidget2.getVisibility() != 8) {
                        DependencyNode dependencyNode2 = constraintWidget2.horizontalRun.end;
                        dependencyNode2.dependencies.add(this.start);
                        this.start.targets.add(dependencyNode2);
                    }
                }
                addDependency(this.widget.horizontalRun.start);
                addDependency(this.widget.horizontalRun.end);
            } else if (barrierType == 2) {
                this.start.type = DependencyNode.Type.TOP;
                for (int i3 = 0; i3 < barrier.mWidgetsCount; i3++) {
                    ConstraintWidget constraintWidget3 = barrier.mWidgets[i3];
                    if (allowsGoneWidget || constraintWidget3.getVisibility() != 8) {
                        DependencyNode dependencyNode3 = constraintWidget3.verticalRun.start;
                        dependencyNode3.dependencies.add(this.start);
                        this.start.targets.add(dependencyNode3);
                    }
                }
                addDependency(this.widget.verticalRun.start);
                addDependency(this.widget.verticalRun.end);
            } else if (barrierType == 3) {
                this.start.type = DependencyNode.Type.BOTTOM;
                for (int i4 = 0; i4 < barrier.mWidgetsCount; i4++) {
                    ConstraintWidget constraintWidget4 = barrier.mWidgets[i4];
                    if (allowsGoneWidget || constraintWidget4.getVisibility() != 8) {
                        DependencyNode dependencyNode4 = constraintWidget4.verticalRun.end;
                        dependencyNode4.dependencies.add(this.start);
                        this.start.targets.add(dependencyNode4);
                    }
                }
                addDependency(this.widget.verticalRun.start);
                addDependency(this.widget.verticalRun.end);
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if (this.widget instanceof Barrier) {
            int barrierType = ((Barrier) this.widget).getBarrierType();
            if (barrierType == 0 || barrierType == 1) {
                this.widget.setX(this.start.value);
            } else {
                this.widget.setY(this.start.value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void clear() {
        this.runGroup = null;
        this.start.clear();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void reset() {
        this.start.resolved = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0045, code lost:
        if (r0 < r9) goto L15;
     */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r5) {
        /*
            r4 = this;
            r0 = r4
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r0.widget
            androidx.constraintlayout.core.widgets.Barrier r0 = (androidx.constraintlayout.core.widgets.Barrier) r0
            r6 = r0
            r0 = r6
            int r0 = r0.getBarrierType()
            r7 = r0
            r0 = r4
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.start
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r0 = r0.targets
            java.util.Iterator r0 = r0.iterator()
            r5 = r0
            r0 = 0
            r8 = r0
            r0 = -1
            r9 = r0
        L20:
            r0 = r5
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L62
            r0 = r5
            java.lang.Object r0 = r0.next()
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r0
            int r0 = r0.value
            r10 = r0
            r0 = r9
            r1 = -1
            if (r0 == r1) goto L48
            r0 = r9
            r11 = r0
            r0 = r10
            r1 = r9
            if (r0 >= r1) goto L4c
        L48:
            r0 = r10
            r11 = r0
        L4c:
            r0 = r11
            r9 = r0
            r0 = r8
            r1 = r10
            if (r0 >= r1) goto L20
            r0 = r10
            r8 = r0
            r0 = r11
            r9 = r0
            goto L20
        L62:
            r0 = r7
            if (r0 == 0) goto L7f
            r0 = r7
            r1 = 2
            if (r0 != r1) goto L6e
            goto L7f
        L6e:
            r0 = r4
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.start
            r1 = r8
            r2 = r6
            int r2 = r2.getMargin()
            int r1 = r1 + r2
            r0.resolve(r1)
            goto L8d
        L7f:
            r0 = r4
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.start
            r1 = r9
            r2 = r6
            int r2 = r2.getMargin()
            int r1 = r1 + r2
            r0.resolve(r1)
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.HelperReferences.update(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }
}