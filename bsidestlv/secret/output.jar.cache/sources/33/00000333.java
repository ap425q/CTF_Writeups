package androidx.constraintlayout.core.widgets.analyzer;

/* loaded from: output.jar:androidx/constraintlayout/core/widgets/analyzer/BaselineDimensionDependency.class */
class BaselineDimensionDependency extends DimensionDependency {
    public BaselineDimensionDependency(WidgetRun widgetRun) {
        super(widgetRun);
    }

    public void update(DependencyNode dependencyNode) {
        ((VerticalWidgetRun) this.run).baseline.margin = this.run.widget.getBaselineDistance();
        this.resolved = true;
    }
}