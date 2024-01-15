package androidx.constraintlayout.core;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: output.jar:androidx/constraintlayout/core/LinearSystem.class */
public class LinearSystem {
    public static long ARRAY_ROW_CREATION = 0;
    public static final boolean DEBUG = false;
    private static final boolean DEBUG_CONSTRAINTS = false;
    public static final boolean FULL_DEBUG = false;
    public static final boolean MEASURE = false;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0;
    public static boolean OPTIMIZED_ENGINE = false;
    private static int POOL_SIZE = 1000;
    public static boolean SIMPLIFY_SYNONYMS = true;
    public static boolean SKIP_COLUMNS = true;
    public static boolean USE_BASIC_SYNONYMS = true;
    public static boolean USE_DEPENDENCY_ORDERING = false;
    public static boolean USE_SYNONYMS = true;
    public static Metrics sMetrics;
    final Cache mCache;
    private Row mGoal;
    ArrayRow[] mRows;
    private Row mTempGoal;
    public boolean hasSimpleDefinition = false;
    int mVariablesID = 0;
    private HashMap<String, SolverVariable> mVariables = null;
    private int TABLE_SIZE = 32;
    private int mMaxColumns = 32;
    public boolean graphOptimizer = false;
    public boolean newgraphOptimizer = false;
    private boolean[] mAlreadyTestedCandidates = new boolean[32];
    int mNumColumns = 1;
    int mNumRows = 0;
    private int mMaxRows = 32;
    private SolverVariable[] mPoolVariables = new SolverVariable[POOL_SIZE];
    private int mPoolVariablesCount = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: output.jar:androidx/constraintlayout/core/LinearSystem$Row.class */
    public interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(Row row);

        boolean isEmpty();

        void updateFromFinalVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z);

        void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z);

        void updateFromSystem(LinearSystem linearSystem);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: output.jar:androidx/constraintlayout/core/LinearSystem$ValuesRow.class */
    public class ValuesRow extends ArrayRow {
        public ValuesRow(Cache cache) {
            this.variables = new SolverVariableValues(this, cache);
        }
    }

    public LinearSystem() {
        this.mRows = null;
        this.mRows = new ArrayRow[32];
        releaseRows();
        Cache cache = new Cache();
        this.mCache = cache;
        this.mGoal = new PriorityGoalRow(cache);
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(cache);
        } else {
            this.mTempGoal = new ArrayRow(cache);
        }
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type, String str) {
        SolverVariable solverVariable;
        SolverVariable acquire = this.mCache.solverVariablePool.acquire();
        if (acquire == null) {
            SolverVariable solverVariable2 = new SolverVariable(type, str);
            solverVariable2.setType(type, str);
            solverVariable = solverVariable2;
        } else {
            acquire.reset();
            acquire.setType(type, str);
            solverVariable = acquire;
        }
        int i = this.mPoolVariablesCount;
        int i2 = POOL_SIZE;
        if (i >= i2) {
            int i3 = i2 * 2;
            POOL_SIZE = i3;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, i3);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i4 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i4 + 1;
        solverVariableArr[i4] = solverVariable;
        return solverVariable;
    }

    private void addError(ArrayRow arrayRow) {
        arrayRow.addError(this, 0);
    }

    private final void addRow(ArrayRow arrayRow) {
        int i;
        if (SIMPLIFY_SYNONYMS && arrayRow.isSimpleDefinition) {
            arrayRow.variable.setFinalValue(this, arrayRow.constantValue);
        } else {
            this.mRows[this.mNumRows] = arrayRow;
            arrayRow.variable.definitionId = this.mNumRows;
            this.mNumRows++;
            arrayRow.variable.updateReferencesWithNewDefinition(this, arrayRow);
        }
        if (!SIMPLIFY_SYNONYMS || !this.hasSimpleDefinition) {
            return;
        }
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.mNumRows) {
                this.hasSimpleDefinition = false;
                return;
            }
            if (this.mRows[i3] == null) {
                System.out.println("WTF");
            }
            ArrayRow arrayRow2 = this.mRows[i3];
            int i4 = i3;
            if (arrayRow2 != null) {
                i4 = i3;
                if (arrayRow2.isSimpleDefinition) {
                    ArrayRow arrayRow3 = this.mRows[i3];
                    arrayRow3.variable.setFinalValue(this, arrayRow3.constantValue);
                    if (OPTIMIZED_ENGINE) {
                        this.mCache.optimizedArrayRowPool.release(arrayRow3);
                    } else {
                        this.mCache.arrayRowPool.release(arrayRow3);
                    }
                    this.mRows[i3] = null;
                    int i5 = i3 + 1;
                    int i6 = i5;
                    while (true) {
                        i = this.mNumRows;
                        if (i5 >= i) {
                            break;
                        }
                        ArrayRow[] arrayRowArr = this.mRows;
                        int i7 = i5 - 1;
                        ArrayRow arrayRow4 = arrayRowArr[i5];
                        arrayRowArr[i7] = arrayRow4;
                        if (arrayRow4.variable.definitionId == i5) {
                            this.mRows[i7].variable.definitionId = i7;
                        }
                        i6 = i5;
                        i5++;
                    }
                    if (i6 < i) {
                        this.mRows[i6] = null;
                    }
                    this.mNumRows = i - 1;
                    i4 = i3 - 1;
                }
            }
            i2 = i4 + 1;
        }
    }

    private void addSingleError(ArrayRow arrayRow, int i) {
        addSingleError(arrayRow, i, 0);
    }

    private void computeValues() {
        for (int i = 0; i < this.mNumRows; i++) {
            ArrayRow arrayRow = this.mRows[i];
            arrayRow.variable.computedValue = arrayRow.constantValue;
        }
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        return linearSystem.createRow().createRowDimensionPercent(solverVariable, solverVariable2, f);
    }

    private SolverVariable createVariable(String str, SolverVariable.Type type) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.variables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(type, null);
        acquireSolverVariable.setName(str);
        int i = this.mVariablesID + 1;
        this.mVariablesID = i;
        this.mNumColumns++;
        acquireSolverVariable.id = i;
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        this.mVariables.put(str, acquireSolverVariable);
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    private void displayRows() {
        displaySolverVariables();
        String str = "";
        for (int i = 0; i < this.mNumRows; i++) {
            str = (str + this.mRows[i]) + "\n";
        }
        System.out.println(str + this.mGoal + "\n");
    }

    private void displaySolverVariables() {
        System.out.println("Display Rows (" + this.mNumRows + "x" + this.mNumColumns + ")\n");
    }

    private int enforceBFS(Row row) throws Exception {
        boolean z;
        int i;
        float f;
        int i2;
        int i3;
        int i4;
        float f2;
        int i5;
        int i6;
        int i7;
        int i8;
        float f3;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13 = 0;
        while (true) {
            if (i13 >= this.mNumRows) {
                z = false;
                break;
            } else if (this.mRows[i13].variable.mType != SolverVariable.Type.UNRESTRICTED && this.mRows[i13].constantValue < 0.0f) {
                z = true;
                break;
            } else {
                i13++;
            }
        }
        if (z) {
            boolean z2 = false;
            i = 0;
            while (!z2) {
                Metrics metrics = sMetrics;
                if (metrics != null) {
                    metrics.bfs++;
                }
                int i14 = i + 1;
                float f4 = Float.MAX_VALUE;
                int i15 = 0;
                int i16 = -1;
                int i17 = -1;
                int i18 = 0;
                while (true) {
                    int i19 = i18;
                    if (i15 >= this.mNumRows) {
                        break;
                    }
                    ArrayRow arrayRow = this.mRows[i15];
                    if (arrayRow.variable.mType == SolverVariable.Type.UNRESTRICTED) {
                        f = f4;
                        i2 = i16;
                        i3 = i17;
                        i4 = i19;
                    } else if (arrayRow.isSimpleDefinition) {
                        f = f4;
                        i2 = i16;
                        i3 = i17;
                        i4 = i19;
                    } else {
                        f = f4;
                        i2 = i16;
                        i3 = i17;
                        i4 = i19;
                        if (arrayRow.constantValue < 0.0f) {
                            if (SKIP_COLUMNS) {
                                int currentSize = arrayRow.variables.getCurrentSize();
                                int i20 = 0;
                                while (true) {
                                    f = f4;
                                    i2 = i16;
                                    i3 = i17;
                                    i4 = i19;
                                    if (i20 < currentSize) {
                                        SolverVariable variable = arrayRow.variables.getVariable(i20);
                                        float f5 = arrayRow.variables.get(variable);
                                        if (f5 <= 0.0f) {
                                            f3 = f4;
                                            i9 = i16;
                                            i10 = i17;
                                            i11 = i19;
                                        } else {
                                            int i21 = i16;
                                            int i22 = 0;
                                            while (true) {
                                                f3 = f4;
                                                i9 = i21;
                                                i10 = i17;
                                                i11 = i19;
                                                if (i22 < 9) {
                                                    float f6 = variable.strengthVector[i22] / f5;
                                                    if (f6 >= f4 || i22 != i19) {
                                                        i12 = i19;
                                                        if (i22 <= i19) {
                                                            i22++;
                                                            i19 = i12;
                                                        }
                                                    }
                                                    i17 = variable.id;
                                                    i12 = i22;
                                                    i21 = i15;
                                                    f4 = f6;
                                                    i22++;
                                                    i19 = i12;
                                                }
                                            }
                                        }
                                        i20++;
                                        f4 = f3;
                                        i16 = i9;
                                        i17 = i10;
                                        i19 = i11;
                                    }
                                }
                            } else {
                                int i23 = 1;
                                while (true) {
                                    f = f4;
                                    i2 = i16;
                                    i3 = i17;
                                    i4 = i19;
                                    if (i23 < this.mNumColumns) {
                                        SolverVariable solverVariable = this.mCache.mIndexedVariables[i23];
                                        float f7 = arrayRow.variables.get(solverVariable);
                                        if (f7 <= 0.0f) {
                                            f2 = f4;
                                            i5 = i16;
                                            i6 = i17;
                                            i7 = i19;
                                        } else {
                                            int i24 = i16;
                                            int i25 = 0;
                                            while (true) {
                                                f2 = f4;
                                                i5 = i24;
                                                i6 = i17;
                                                i7 = i19;
                                                if (i25 < 9) {
                                                    float f8 = solverVariable.strengthVector[i25] / f7;
                                                    if (f8 >= f4 || i25 != i19) {
                                                        i8 = i19;
                                                        if (i25 <= i19) {
                                                            i25++;
                                                            i19 = i8;
                                                        }
                                                    }
                                                    i17 = i23;
                                                    i8 = i25;
                                                    i24 = i15;
                                                    f4 = f8;
                                                    i25++;
                                                    i19 = i8;
                                                }
                                            }
                                        }
                                        i23++;
                                        f4 = f2;
                                        i16 = i5;
                                        i17 = i6;
                                        i19 = i7;
                                    }
                                }
                            }
                        }
                    }
                    i15++;
                    f4 = f;
                    i16 = i2;
                    i17 = i3;
                    i18 = i4;
                }
                if (i16 != -1) {
                    ArrayRow arrayRow2 = this.mRows[i16];
                    arrayRow2.variable.definitionId = -1;
                    Metrics metrics2 = sMetrics;
                    if (metrics2 != null) {
                        metrics2.pivots++;
                    }
                    arrayRow2.pivot(this.mCache.mIndexedVariables[i17]);
                    arrayRow2.variable.definitionId = i16;
                    arrayRow2.variable.updateReferencesWithNewDefinition(this, arrayRow2);
                } else {
                    z2 = true;
                }
                i = i14;
                if (i14 > this.mNumColumns / 2) {
                    z2 = true;
                    i = i14;
                }
            }
        } else {
            i = 0;
        }
        return i;
    }

    private String getDisplaySize(int i) {
        int i2 = i * 4;
        int i3 = i2 / 1024;
        int i4 = i3 / 1024;
        return i4 > 0 ? "" + i4 + " Mb" : i3 > 0 ? "" + i3 + " Kb" : "" + i2 + " bytes";
    }

    private String getDisplayStrength(int i) {
        return i == 1 ? "LOW" : i == 2 ? "MEDIUM" : i == 3 ? "HIGH" : i == 4 ? "HIGHEST" : i == 5 ? "EQUALITY" : i == 8 ? "FIXED" : i == 6 ? "BARRIER" : "NONE";
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    private void increaseTableSize() {
        int i = this.TABLE_SIZE * 2;
        this.TABLE_SIZE = i;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, i);
        Cache cache = this.mCache;
        cache.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(cache.mIndexedVariables, this.TABLE_SIZE);
        int i2 = this.TABLE_SIZE;
        this.mAlreadyTestedCandidates = new boolean[i2];
        this.mMaxColumns = i2;
        this.mMaxRows = i2;
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.tableSizeIncrease++;
            Metrics metrics2 = sMetrics;
            metrics2.maxTableSize = Math.max(metrics2.maxTableSize, this.TABLE_SIZE);
            Metrics metrics3 = sMetrics;
            metrics3.lastTableSize = metrics3.maxTableSize;
        }
    }

    private final int optimize(Row row, boolean z) {
        int i;
        float f;
        int i2;
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.optimize++;
        }
        for (int i3 = 0; i3 < this.mNumColumns; i3++) {
            this.mAlreadyTestedCandidates[i3] = false;
        }
        boolean z2 = false;
        int i4 = 0;
        while (!z2) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.iterations++;
            }
            int i5 = i4 + 1;
            if (i5 >= this.mNumColumns * 2) {
                return i5;
            }
            if (row.getKey() != null) {
                this.mAlreadyTestedCandidates[row.getKey().id] = true;
            }
            SolverVariable pivotCandidate = row.getPivotCandidate(this, this.mAlreadyTestedCandidates);
            if (pivotCandidate != null) {
                if (this.mAlreadyTestedCandidates[pivotCandidate.id]) {
                    return i5;
                }
                this.mAlreadyTestedCandidates[pivotCandidate.id] = true;
            }
            if (pivotCandidate != null) {
                float f2 = Float.MAX_VALUE;
                int i6 = 0;
                int i7 = -1;
                while (true) {
                    i = i7;
                    if (i6 >= this.mNumRows) {
                        break;
                    }
                    ArrayRow arrayRow = this.mRows[i6];
                    if (arrayRow.variable.mType == SolverVariable.Type.UNRESTRICTED) {
                        f = f2;
                        i2 = i;
                    } else if (arrayRow.isSimpleDefinition) {
                        f = f2;
                        i2 = i;
                    } else {
                        f = f2;
                        i2 = i;
                        if (arrayRow.hasVariable(pivotCandidate)) {
                            float f3 = arrayRow.variables.get(pivotCandidate);
                            f = f2;
                            i2 = i;
                            if (f3 < 0.0f) {
                                float f4 = (-arrayRow.constantValue) / f3;
                                f = f2;
                                i2 = i;
                                if (f4 < f2) {
                                    i2 = i6;
                                    f = f4;
                                }
                            }
                        }
                    }
                    i6++;
                    f2 = f;
                    i7 = i2;
                }
                i4 = i5;
                if (i > -1) {
                    ArrayRow arrayRow2 = this.mRows[i];
                    arrayRow2.variable.definitionId = -1;
                    Metrics metrics3 = sMetrics;
                    if (metrics3 != null) {
                        metrics3.pivots++;
                    }
                    arrayRow2.pivot(pivotCandidate);
                    arrayRow2.variable.definitionId = i;
                    arrayRow2.variable.updateReferencesWithNewDefinition(this, arrayRow2);
                    i4 = i5;
                }
            } else {
                z2 = true;
                i4 = i5;
            }
        }
        return i4;
    }

    private void releaseRows() {
        if (!OPTIMIZED_ENGINE) {
            for (int i = 0; i < this.mNumRows; i++) {
                ArrayRow arrayRow = this.mRows[i];
                if (arrayRow != null) {
                    this.mCache.arrayRowPool.release(arrayRow);
                }
                this.mRows[i] = null;
            }
            return;
        }
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            ArrayRow arrayRow2 = this.mRows[i2];
            if (arrayRow2 != null) {
                this.mCache.optimizedArrayRowPool.release(arrayRow2);
            }
            this.mRows[i2] = null;
        }
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f, int i) {
        SolverVariable createObjectVariable = createObjectVariable(constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable createObjectVariable2 = createObjectVariable(constraintWidget.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable createObjectVariable3 = createObjectVariable(constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable createObjectVariable4 = createObjectVariable(constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM));
        SolverVariable createObjectVariable5 = createObjectVariable(constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable createObjectVariable6 = createObjectVariable(constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable createObjectVariable7 = createObjectVariable(constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable createObjectVariable8 = createObjectVariable(constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM));
        ArrayRow createRow = createRow();
        double d = f;
        double sin = Math.sin(d);
        double d2 = i;
        createRow.createRowWithAngle(createObjectVariable2, createObjectVariable4, createObjectVariable6, createObjectVariable8, (float) (sin * d2));
        addConstraint(createRow);
        ArrayRow createRow2 = createRow();
        createRow2.createRowWithAngle(createObjectVariable, createObjectVariable3, createObjectVariable5, createObjectVariable7, (float) (Math.cos(d) * d2));
        addConstraint(createRow2);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        ArrayRow createRow = createRow();
        createRow.createRowCentering(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (i3 != 8) {
            createRow.addError(this, i3);
        }
        addConstraint(createRow);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x012d A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addConstraint(androidx.constraintlayout.core.ArrayRow r7) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.LinearSystem.addConstraint(androidx.constraintlayout.core.ArrayRow):void");
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        if (USE_BASIC_SYNONYMS && i2 == 8 && solverVariable2.isFinalValue && solverVariable.definitionId == -1) {
            solverVariable.setFinalValue(this, solverVariable2.computedValue + i);
            return null;
        }
        ArrayRow createRow = createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i);
        if (i2 != 8) {
            createRow.addError(this, i2);
        }
        addConstraint(createRow);
        return createRow;
    }

    public void addEquality(SolverVariable solverVariable, int i) {
        if (USE_BASIC_SYNONYMS && solverVariable.definitionId == -1) {
            float f = i;
            solverVariable.setFinalValue(this, f);
            for (int i2 = 0; i2 < this.mVariablesID + 1; i2++) {
                SolverVariable solverVariable2 = this.mCache.mIndexedVariables[i2];
                if (solverVariable2 != null && solverVariable2.isSynonym && solverVariable2.synonym == solverVariable.id) {
                    solverVariable2.setFinalValue(this, solverVariable2.synonymDelta + f);
                }
            }
            return;
        }
        int i3 = solverVariable.definitionId;
        if (solverVariable.definitionId == -1) {
            ArrayRow createRow = createRow();
            createRow.createRowDefinition(solverVariable, i);
            addConstraint(createRow);
            return;
        }
        ArrayRow arrayRow = this.mRows[i3];
        if (arrayRow.isSimpleDefinition) {
            arrayRow.constantValue = i;
        } else if (arrayRow.variables.getCurrentSize() == 0) {
            arrayRow.isSimpleDefinition = true;
            arrayRow.constantValue = i;
        } else {
            ArrayRow createRow2 = createRow();
            createRow2.createRowEquals(solverVariable, i);
            addConstraint(createRow2);
        }
    }

    public void addGreaterBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        addConstraint(createRow);
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 8) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * (-1.0f)), i2);
        }
        addConstraint(createRow);
    }

    public void addLowerBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        addConstraint(createRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 8) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * (-1.0f)), i2);
        }
        addConstraint(createRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i) {
        ArrayRow createRow = createRow();
        createRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i != 8) {
            createRow.addError(this, i);
        }
        addConstraint(createRow);
    }

    void addSingleError(ArrayRow arrayRow, int i, int i2) {
        arrayRow.addSingleError(createErrorVariable(i2, null), i);
    }

    public void addSynonym(SolverVariable solverVariable, SolverVariable solverVariable2, int i) {
        if (solverVariable.definitionId != -1 || i != 0) {
            addEquality(solverVariable, solverVariable2, i, 8);
            return;
        }
        SolverVariable solverVariable3 = solverVariable2;
        if (solverVariable2.isSynonym) {
            float f = solverVariable2.synonymDelta;
            solverVariable3 = this.mCache.mIndexedVariables[solverVariable2.synonym];
        }
        if (!solverVariable.isSynonym) {
            solverVariable.setSynonym(this, solverVariable3, 0.0f);
            return;
        }
        float f2 = solverVariable.synonymDelta;
        SolverVariable solverVariable4 = this.mCache.mIndexedVariables[solverVariable.synonym];
    }

    final void cleanupRows() {
        int i;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.mNumRows) {
                return;
            }
            ArrayRow arrayRow = this.mRows[i3];
            if (arrayRow.variables.getCurrentSize() == 0) {
                arrayRow.isSimpleDefinition = true;
            }
            int i4 = i3;
            if (arrayRow.isSimpleDefinition) {
                arrayRow.variable.computedValue = arrayRow.constantValue;
                arrayRow.variable.removeFromRow(arrayRow);
                int i5 = i3;
                while (true) {
                    int i6 = i5;
                    i = this.mNumRows;
                    if (i6 >= i - 1) {
                        break;
                    }
                    ArrayRow[] arrayRowArr = this.mRows;
                    int i7 = i6 + 1;
                    arrayRowArr[i6] = arrayRowArr[i7];
                    i5 = i7;
                }
                this.mRows[i - 1] = null;
                this.mNumRows = i - 1;
                i4 = i3 - 1;
                if (OPTIMIZED_ENGINE) {
                    this.mCache.optimizedArrayRowPool.release(arrayRow);
                } else {
                    this.mCache.arrayRowPool.release(arrayRow);
                }
            }
            i2 = i4 + 1;
        }
    }

    public SolverVariable createErrorVariable(int i, String str) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.errors++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.ERROR, str);
        int i2 = this.mVariablesID + 1;
        this.mVariablesID = i2;
        this.mNumColumns++;
        acquireSolverVariable.id = i2;
        acquireSolverVariable.strength = i;
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        this.mGoal.addError(acquireSolverVariable);
        return acquireSolverVariable;
    }

    public SolverVariable createExtraVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.extravariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        int i = this.mVariablesID + 1;
        this.mVariablesID = i;
        this.mNumColumns++;
        acquireSolverVariable.id = i;
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x005e, code lost:
        if (r4.mCache.mIndexedVariables[r5.id] == null) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.constraintlayout.core.SolverVariable createObjectVariable(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 0
            r6 = r0
            r0 = r5
            if (r0 != 0) goto L8
            r0 = 0
            return r0
        L8:
            r0 = r4
            int r0 = r0.mNumColumns
            r1 = 1
            int r0 = r0 + r1
            r1 = r4
            int r1 = r1.mMaxColumns
            if (r0 < r1) goto L19
            r0 = r4
            r0.increaseTableSize()
        L19:
            r0 = r5
            boolean r0 = r0 instanceof androidx.constraintlayout.core.widgets.ConstraintAnchor
            if (r0 == 0) goto La1
            r0 = r5
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r0
            r7 = r0
            r0 = r7
            androidx.constraintlayout.core.SolverVariable r0 = r0.getSolverVariable()
            r6 = r0
            r0 = r6
            r5 = r0
            r0 = r6
            if (r0 != 0) goto L3d
            r0 = r7
            r1 = r4
            androidx.constraintlayout.core.Cache r1 = r1.mCache
            r0.resetSolverVariable(r1)
            r0 = r7
            androidx.constraintlayout.core.SolverVariable r0 = r0.getSolverVariable()
            r5 = r0
        L3d:
            r0 = r5
            int r0 = r0.id
            r1 = -1
            if (r0 == r1) goto L61
            r0 = r5
            int r0 = r0.id
            r1 = r4
            int r1 = r1.mVariablesID
            if (r0 > r1) goto L61
            r0 = r5
            r6 = r0
            r0 = r4
            androidx.constraintlayout.core.Cache r0 = r0.mCache
            androidx.constraintlayout.core.SolverVariable[] r0 = r0.mIndexedVariables
            r1 = r5
            int r1 = r1.id
            r0 = r0[r1]
            if (r0 != 0) goto La1
        L61:
            r0 = r5
            int r0 = r0.id
            r1 = -1
            if (r0 == r1) goto L6d
            r0 = r5
            r0.reset()
        L6d:
            r0 = r4
            int r0 = r0.mVariablesID
            r1 = 1
            int r0 = r0 + r1
            r8 = r0
            r0 = r4
            r1 = r8
            r0.mVariablesID = r1
            r0 = r4
            r1 = r4
            int r1 = r1.mNumColumns
            r2 = 1
            int r1 = r1 + r2
            r0.mNumColumns = r1
            r0 = r5
            r1 = r8
            r0.id = r1
            r0 = r5
            androidx.constraintlayout.core.SolverVariable$Type r1 = androidx.constraintlayout.core.SolverVariable.Type.UNRESTRICTED
            r0.mType = r1
            r0 = r4
            androidx.constraintlayout.core.Cache r0 = r0.mCache
            androidx.constraintlayout.core.SolverVariable[] r0 = r0.mIndexedVariables
            r1 = r4
            int r1 = r1.mVariablesID
            r2 = r5
            r0[r1] = r2
            r0 = r5
            r6 = r0
        La1:
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.LinearSystem.createObjectVariable(java.lang.Object):androidx.constraintlayout.core.SolverVariable");
    }

    public ArrayRow createRow() {
        ValuesRow acquire;
        if (OPTIMIZED_ENGINE) {
            acquire = this.mCache.optimizedArrayRowPool.acquire();
            if (acquire == null) {
                acquire = new ValuesRow(this.mCache);
                OPTIMIZED_ARRAY_ROW_CREATION++;
            } else {
                acquire.reset();
            }
        } else {
            acquire = this.mCache.arrayRowPool.acquire();
            if (acquire == null) {
                acquire = new ArrayRow(this.mCache);
                ARRAY_ROW_CREATION++;
            } else {
                acquire.reset();
            }
        }
        SolverVariable.increaseErrorId();
        return acquire;
    }

    public SolverVariable createSlackVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.slackvariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        int i = this.mVariablesID + 1;
        this.mVariablesID = i;
        this.mNumColumns++;
        acquireSolverVariable.id = i;
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public void displayReadableRows() {
        displaySolverVariables();
        String str = " num vars " + this.mVariablesID + "\n";
        int i = 0;
        while (i < this.mVariablesID + 1) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[i];
            String str2 = str;
            if (solverVariable != null) {
                str2 = str;
                if (solverVariable.isFinalValue) {
                    str2 = str + " $[" + i + "] => " + solverVariable + " = " + solverVariable.computedValue + "\n";
                }
            }
            i++;
            str = str2;
        }
        String str3 = str + "\n";
        int i2 = 0;
        while (i2 < this.mVariablesID + 1) {
            SolverVariable solverVariable2 = this.mCache.mIndexedVariables[i2];
            String str4 = str3;
            if (solverVariable2 != null) {
                str4 = str3;
                if (solverVariable2.isSynonym) {
                    str4 = str3 + " ~[" + i2 + "] => " + solverVariable2 + " = " + this.mCache.mIndexedVariables[solverVariable2.synonym] + " + " + solverVariable2.synonymDelta + "\n";
                }
            }
            i2++;
            str3 = str4;
        }
        String str5 = str3 + "\n\n #  ";
        for (int i3 = 0; i3 < this.mNumRows; i3++) {
            str5 = (str5 + this.mRows[i3].toReadableString()) + "\n #  ";
        }
        String str6 = str5;
        if (this.mGoal != null) {
            str6 = str5 + "Goal: " + this.mGoal + "\n";
        }
        System.out.println(str6);
    }

    void displaySystemInformation() {
        int i;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = i3;
            if (i2 >= this.TABLE_SIZE) {
                break;
            }
            ArrayRow arrayRow = this.mRows[i2];
            int i4 = i;
            if (arrayRow != null) {
                i4 = i + arrayRow.sizeInBytes();
            }
            i2++;
            i3 = i4;
        }
        int i5 = 0;
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i5 >= this.mNumRows) {
                PrintStream printStream = System.out;
                StringBuilder append = new StringBuilder("Linear System -> Table size: ").append(this.TABLE_SIZE).append(" (");
                int i8 = this.TABLE_SIZE;
                printStream.println(append.append(getDisplaySize(i8 * i8)).append(") -- row sizes: ").append(getDisplaySize(i)).append(", actual size: ").append(getDisplaySize(i7)).append(" rows: ").append(this.mNumRows).append("/").append(this.mMaxRows).append(" cols: ").append(this.mNumColumns).append("/").append(this.mMaxColumns).append(" 0 occupied cells, ").append(getDisplaySize(0)).toString());
                return;
            }
            ArrayRow arrayRow2 = this.mRows[i5];
            int i9 = i7;
            if (arrayRow2 != null) {
                i9 = i7 + arrayRow2.sizeInBytes();
            }
            i5++;
            i6 = i9;
        }
    }

    public void displayVariablesReadableRows() {
        displaySolverVariables();
        String str = "";
        int i = 0;
        while (i < this.mNumRows) {
            String str2 = str;
            if (this.mRows[i].variable.mType == SolverVariable.Type.UNRESTRICTED) {
                str2 = (str + this.mRows[i].toReadableString()) + "\n";
            }
            i++;
            str = str2;
        }
        System.out.println(str + this.mGoal + "\n");
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public Cache getCache() {
        return this.mCache;
    }

    Row getGoal() {
        return this.mGoal;
    }

    public int getMemoryUsed() {
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i >= this.mNumRows) {
                return i3;
            }
            ArrayRow arrayRow = this.mRows[i];
            int i4 = i3;
            if (arrayRow != null) {
                i4 = i3 + arrayRow.sizeInBytes();
            }
            i++;
            i2 = i4;
        }
    }

    public int getNumEquations() {
        return this.mNumRows;
    }

    public int getNumVariables() {
        return this.mVariablesID;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).getSolverVariable();
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    ArrayRow getRow(int i) {
        return this.mRows[i];
    }

    float getValueFor(String str) {
        SolverVariable variable = getVariable(str, SolverVariable.Type.UNRESTRICTED);
        if (variable == null) {
            return 0.0f;
        }
        return variable.computedValue;
    }

    SolverVariable getVariable(String str, SolverVariable.Type type) {
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        SolverVariable solverVariable = this.mVariables.get(str);
        SolverVariable solverVariable2 = solverVariable;
        if (solverVariable == null) {
            solverVariable2 = createVariable(str, type);
        }
        return solverVariable2;
    }

    public void minimize() throws Exception {
        boolean z;
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimize++;
        }
        if (this.mGoal.isEmpty()) {
            computeValues();
        } else if (!this.graphOptimizer && !this.newgraphOptimizer) {
            minimizeGoal(this.mGoal);
        } else {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.graphOptimizer++;
            }
            int i = 0;
            while (true) {
                if (i >= this.mNumRows) {
                    z = true;
                    break;
                } else if (!this.mRows[i].isSimpleDefinition) {
                    z = false;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                minimizeGoal(this.mGoal);
                return;
            }
            Metrics metrics3 = sMetrics;
            if (metrics3 != null) {
                metrics3.fullySolved++;
            }
            computeValues();
        }
    }

    void minimizeGoal(Row row) throws Exception {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimizeGoal++;
            Metrics metrics2 = sMetrics;
            metrics2.maxVariables = Math.max(metrics2.maxVariables, this.mNumColumns);
            Metrics metrics3 = sMetrics;
            metrics3.maxRows = Math.max(metrics3.maxRows, this.mNumRows);
        }
        enforceBFS(row);
        optimize(row, false);
        computeValues();
    }

    public void removeRow(ArrayRow arrayRow) {
        int i;
        if (!arrayRow.isSimpleDefinition || arrayRow.variable == null) {
            return;
        }
        if (arrayRow.variable.definitionId != -1) {
            int i2 = arrayRow.variable.definitionId;
            while (true) {
                int i3 = i2;
                i = this.mNumRows;
                if (i3 >= i - 1) {
                    break;
                }
                int i4 = i3 + 1;
                SolverVariable solverVariable = this.mRows[i4].variable;
                if (solverVariable.definitionId == i4) {
                    solverVariable.definitionId = i3;
                }
                ArrayRow[] arrayRowArr = this.mRows;
                arrayRowArr[i3] = arrayRowArr[i4];
                i2 = i4;
            }
            this.mNumRows = i - 1;
        }
        if (!arrayRow.variable.isFinalValue) {
            arrayRow.variable.setFinalValue(this, arrayRow.constantValue);
        }
        if (OPTIMIZED_ENGINE) {
            this.mCache.optimizedArrayRowPool.release(arrayRow);
        } else {
            this.mCache.arrayRowPool.release(arrayRow);
        }
    }

    public void reset() {
        for (int i = 0; i < this.mCache.mIndexedVariables.length; i++) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[i];
            if (solverVariable != null) {
                solverVariable.reset();
            }
        }
        this.mCache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, (Object) null);
        HashMap<String, SolverVariable> hashMap = this.mVariables;
        if (hashMap != null) {
            hashMap.clear();
        }
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            ArrayRow arrayRow = this.mRows[i2];
            if (arrayRow != null) {
                arrayRow.used = false;
            }
        }
        releaseRows();
        this.mNumRows = 0;
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(this.mCache);
        } else {
            this.mTempGoal = new ArrayRow(this.mCache);
        }
    }
}