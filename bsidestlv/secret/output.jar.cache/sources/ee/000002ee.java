package androidx.constraintlayout.core.parser;

import androidx.constraintlayout.widget.ConstraintLayout;

/* loaded from: output.jar:androidx/constraintlayout/core/parser/CLParser.class */
public class CLParser {
    static boolean DEBUG = false;
    private boolean hasComment = false;
    private int lineNumber;
    private String mContent;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.core.parser.CLParser$1  reason: invalid class name */
    /* loaded from: output.jar:androidx/constraintlayout/core/parser/CLParser$1.class */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE;

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x004d -> B:37:0x0014). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0051 -> B:33:0x001f). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0055 -> B:31:0x002a). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0059 -> B:27:0x0035). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x005d -> B:35:0x0040). Please submit an issue!!! */
        static {
            int[] iArr = new int[TYPE.values().length];
            $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE = iArr;
            try {
                iArr[TYPE.OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE[TYPE.ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE[TYPE.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE[TYPE.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE[TYPE.KEY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE[TYPE.TOKEN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: output.jar:androidx/constraintlayout/core/parser/CLParser$TYPE.class */
    public enum TYPE {
        UNKNOWN,
        OBJECT,
        ARRAY,
        NUMBER,
        STRING,
        KEY,
        TOKEN
    }

    public CLParser(String str) {
        this.mContent = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [androidx.constraintlayout.core.parser.CLElement] */
    /* JADX WARN: Type inference failed for: r0v12, types: [androidx.constraintlayout.core.parser.CLElement] */
    /* JADX WARN: Type inference failed for: r0v14, types: [androidx.constraintlayout.core.parser.CLElement] */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.constraintlayout.core.parser.CLElement] */
    /* JADX WARN: Type inference failed for: r0v8, types: [androidx.constraintlayout.core.parser.CLElement] */
    private CLElement createElement(CLElement cLElement, int i, TYPE type, boolean z, char[] cArr) {
        CLObject allocate;
        if (DEBUG) {
            System.out.println("CREATE " + type + " at " + cArr[i]);
        }
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE[type.ordinal()]) {
            case 1:
                allocate = CLObject.allocate(cArr);
                i++;
                break;
            case 2:
                allocate = CLArray.allocate(cArr);
                i++;
                break;
            case 3:
                allocate = CLString.allocate(cArr);
                break;
            case 4:
                allocate = CLNumber.allocate(cArr);
                break;
            case 5:
                allocate = CLKey.allocate(cArr);
                break;
            case 6:
                allocate = CLToken.allocate(cArr);
                break;
            default:
                allocate = null;
                break;
        }
        if (allocate == null) {
            return null;
        }
        allocate.setLine(this.lineNumber);
        if (z) {
            allocate.setStart(i);
        }
        if (cLElement instanceof CLContainer) {
            allocate.setContainer((CLContainer) cLElement);
        }
        return allocate;
    }

    private CLElement getNextJsonElement(int i, char c, CLElement cLElement, char[] cArr) throws CLParsingException {
        CLElement cLElement2 = cLElement;
        if (c != '\t') {
            cLElement2 = cLElement;
            if (c != '\n') {
                cLElement2 = cLElement;
                if (c != '\r') {
                    cLElement2 = cLElement;
                    if (c != ' ') {
                        if (c == '\"' || c == '\'') {
                            cLElement2 = cLElement instanceof CLObject ? createElement(cLElement, i, TYPE.KEY, true, cArr) : createElement(cLElement, i, TYPE.STRING, true, cArr);
                        } else if (c != '[') {
                            if (c != ']') {
                                if (c == '{') {
                                    cLElement2 = createElement(cLElement, i, TYPE.OBJECT, true, cArr);
                                } else if (c != '}') {
                                    cLElement2 = cLElement;
                                    switch (c) {
                                        case '+':
                                        case '-':
                                        case '.':
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                                        case '2':
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                                        case '8':
                                        case '9':
                                            cLElement2 = createElement(cLElement, i, TYPE.NUMBER, true, cArr);
                                            break;
                                        case ',':
                                        case ':':
                                            break;
                                        case '/':
                                            int i2 = i + 1;
                                            cLElement2 = cLElement;
                                            if (i2 < cArr.length) {
                                                cLElement2 = cLElement;
                                                if (cArr[i2] == '/') {
                                                    this.hasComment = true;
                                                    cLElement2 = cLElement;
                                                    break;
                                                }
                                            }
                                            break;
                                        default:
                                            if (!(cLElement instanceof CLContainer) || (cLElement instanceof CLObject)) {
                                                cLElement2 = createElement(cLElement, i, TYPE.KEY, true, cArr);
                                                break;
                                            } else {
                                                cLElement2 = createElement(cLElement, i, TYPE.TOKEN, true, cArr);
                                                CLToken cLToken = (CLToken) cLElement2;
                                                if (!cLToken.validate(c, i)) {
                                                    throw new CLParsingException("incorrect token <" + c + "> at line " + this.lineNumber, cLToken);
                                                }
                                            }
                                            break;
                                    }
                                }
                            }
                            cLElement.setEnd(i - 1);
                            cLElement2 = cLElement.getContainer();
                            cLElement2.setEnd(i);
                        } else {
                            cLElement2 = createElement(cLElement, i, TYPE.ARRAY, true, cArr);
                        }
                    }
                }
            }
        }
        return cLElement2;
    }

    public static CLObject parse(String str) throws CLParsingException {
        return new CLParser(str).parse();
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x02a0, code lost:
        if (r13 == null) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02a8, code lost:
        if (r13.isDone() != false) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x02b0, code lost:
        if ((r13 instanceof androidx.constraintlayout.core.parser.CLString) == false) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x02b3, code lost:
        r13.setStart(((int) r13.start) + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x02c4, code lost:
        r13.setEnd(r0 - 1);
        r13 = r13.getContainer();
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02da, code lost:
        if (androidx.constraintlayout.core.parser.CLParser.DEBUG == false) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02dd, code lost:
        java.lang.System.out.println("Root: " + r0.toJSON());
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02f9, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x021d, code lost:
        if (r0 == ':') goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x023c, code lost:
        if (r0 == ']') goto L94;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.constraintlayout.core.parser.CLObject parse() throws androidx.constraintlayout.core.parser.CLParsingException {
        /*
            Method dump skipped, instructions count: 773
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.parser.CLParser.parse():androidx.constraintlayout.core.parser.CLObject");
    }
}