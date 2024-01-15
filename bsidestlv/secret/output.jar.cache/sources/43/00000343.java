package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: output.jar:androidx/constraintlayout/core/widgets/analyzer/RunGroup.class */
public class RunGroup {
    public static final int BASELINE = 2;
    public static final int END = 1;
    public static final int START = 0;
    public static int index;
    int direction;
    WidgetRun firstRun;
    int groupIndex;
    WidgetRun lastRun;
    public int position = 0;
    public boolean dual = false;
    ArrayList<WidgetRun> runs = new ArrayList<>();

    public RunGroup(WidgetRun widgetRun, int i) {
        this.firstRun = null;
        this.lastRun = null;
        int i2 = index;
        this.groupIndex = i2;
        index = i2 + 1;
        this.firstRun = widgetRun;
        this.lastRun = widgetRun;
        this.direction = i;
    }

    private boolean defineTerminalWidget(WidgetRun widgetRun, int i) {
        if (widgetRun.widget.isTerminalWidget[i]) {
            for (Dependency dependency : widgetRun.start.dependencies) {
                if (dependency instanceof DependencyNode) {
                    DependencyNode dependencyNode = (DependencyNode) dependency;
                    if (dependencyNode.run != widgetRun && dependencyNode == dependencyNode.run.start) {
                        if (widgetRun instanceof ChainRun) {
                            Iterator<WidgetRun> it = ((ChainRun) widgetRun).widgets.iterator();
                            while (it.hasNext()) {
                                defineTerminalWidget(it.next(), i);
                            }
                        } else if (!(widgetRun instanceof HelperReferences)) {
                            widgetRun.widget.isTerminalWidget[i] = false;
                        }
                        defineTerminalWidget(dependencyNode.run, i);
                    }
                }
            }
            for (Dependency dependency2 : widgetRun.end.dependencies) {
                if (dependency2 instanceof DependencyNode) {
                    DependencyNode dependencyNode2 = (DependencyNode) dependency2;
                    if (dependencyNode2.run != widgetRun && dependencyNode2 == dependencyNode2.run.start) {
                        if (widgetRun instanceof ChainRun) {
                            Iterator<WidgetRun> it2 = ((ChainRun) widgetRun).widgets.iterator();
                            while (it2.hasNext()) {
                                defineTerminalWidget(it2.next(), i);
                            }
                        } else if (!(widgetRun instanceof HelperReferences)) {
                            widgetRun.widget.isTerminalWidget[i] = false;
                        }
                        defineTerminalWidget(dependencyNode2.run, i);
                    }
                }
            }
            return false;
        }
        return false;
    }

    private long traverseEnd(DependencyNode dependencyNode, long j) {
        long j2;
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun instanceof HelperReferences) {
            return j;
        }
        int size = dependencyNode.dependencies.size();
        int i = 0;
        long j3 = j;
        while (true) {
            j2 = j3;
            if (i >= size) {
                break;
            }
            Dependency dependency = dependencyNode.dependencies.get(i);
            long j4 = j2;
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                j4 = dependencyNode2.run == widgetRun ? j2 : Math.min(j2, traverseEnd(dependencyNode2, dependencyNode2.margin + j));
            }
            i++;
            j3 = j4;
        }
        long j5 = j2;
        if (dependencyNode == widgetRun.end) {
            long wrapDimension = widgetRun.getWrapDimension();
            DependencyNode dependencyNode3 = widgetRun.start;
            long j6 = j - wrapDimension;
            j5 = Math.min(Math.min(j2, traverseEnd(dependencyNode3, j6)), j6 - widgetRun.start.margin);
        }
        return j5;
    }

    private long traverseStart(DependencyNode dependencyNode, long j) {
        long j2;
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun instanceof HelperReferences) {
            return j;
        }
        int size = dependencyNode.dependencies.size();
        int i = 0;
        long j3 = j;
        while (true) {
            j2 = j3;
            if (i >= size) {
                break;
            }
            Dependency dependency = dependencyNode.dependencies.get(i);
            long j4 = j2;
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                j4 = dependencyNode2.run == widgetRun ? j2 : Math.max(j2, traverseStart(dependencyNode2, dependencyNode2.margin + j));
            }
            i++;
            j3 = j4;
        }
        long j5 = j2;
        if (dependencyNode == widgetRun.start) {
            long wrapDimension = widgetRun.getWrapDimension();
            DependencyNode dependencyNode3 = widgetRun.end;
            long j6 = j + wrapDimension;
            j5 = Math.max(Math.max(j2, traverseStart(dependencyNode3, j6)), j6 - widgetRun.end.margin);
        }
        return j5;
    }

    public void add(WidgetRun widgetRun) {
        this.runs.add(widgetRun);
        this.lastRun = widgetRun;
    }

    public long computeWrapSize(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        long wrapDimension;
        int i2;
        long max;
        WidgetRun widgetRun = this.firstRun;
        long j = 0;
        if (widgetRun instanceof ChainRun) {
            if (((ChainRun) widgetRun).orientation != i) {
                return 0L;
            }
        } else if (i == 0) {
            if (!(widgetRun instanceof HorizontalWidgetRun)) {
                return 0L;
            }
        } else if (!(widgetRun instanceof VerticalWidgetRun)) {
            return 0L;
        }
        DependencyNode dependencyNode = i == 0 ? constraintWidgetContainer.horizontalRun.start : constraintWidgetContainer.verticalRun.start;
        DependencyNode dependencyNode2 = i == 0 ? constraintWidgetContainer.horizontalRun.end : constraintWidgetContainer.verticalRun.end;
        boolean contains = this.firstRun.start.targets.contains(dependencyNode);
        boolean contains2 = this.firstRun.end.targets.contains(dependencyNode2);
        long wrapDimension2 = this.firstRun.getWrapDimension();
        if (!contains || !contains2) {
            if (contains) {
                max = Math.max(traverseStart(this.firstRun.start, this.firstRun.start.margin), this.firstRun.start.margin + wrapDimension2);
            } else if (contains2) {
                max = Math.max(-traverseEnd(this.firstRun.end, this.firstRun.end.margin), (-this.firstRun.end.margin) + wrapDimension2);
            } else {
                wrapDimension = this.firstRun.start.margin + this.firstRun.getWrapDimension();
                i2 = this.firstRun.end.margin;
            }
            return max;
        }
        long traverseStart = traverseStart(this.firstRun.start, 0L);
        long traverseEnd = traverseEnd(this.firstRun.end, 0L);
        long j2 = traverseStart - wrapDimension2;
        long j3 = j2;
        if (j2 >= (-this.firstRun.end.margin)) {
            j3 = j2 + this.firstRun.end.margin;
        }
        long j4 = ((-traverseEnd) - wrapDimension2) - this.firstRun.start.margin;
        long j5 = j4;
        if (j4 >= this.firstRun.start.margin) {
            j5 = j4 - this.firstRun.start.margin;
        }
        float biasPercent = this.firstRun.widget.getBiasPercent(i);
        if (biasPercent > 0.0f) {
            j = (((float) j5) / biasPercent) + (((float) j3) / (1.0f - biasPercent));
        }
        float f = (float) j;
        wrapDimension = this.firstRun.start.margin + (f * biasPercent) + 0.5f + wrapDimension2 + (f * (1.0f - biasPercent)) + 0.5f;
        i2 = this.firstRun.end.margin;
        max = wrapDimension - i2;
        return max;
    }

    public void defineTerminalWidgets(boolean z, boolean z2) {
        if (z) {
            WidgetRun widgetRun = this.firstRun;
            if (widgetRun instanceof HorizontalWidgetRun) {
                defineTerminalWidget(widgetRun, 0);
            }
        }
        if (z2) {
            WidgetRun widgetRun2 = this.firstRun;
            if (widgetRun2 instanceof VerticalWidgetRun) {
                defineTerminalWidget(widgetRun2, 1);
            }
        }
    }
}