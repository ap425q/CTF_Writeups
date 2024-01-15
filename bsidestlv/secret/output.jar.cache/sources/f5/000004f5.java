package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import java.util.ArrayList;

/* loaded from: output.jar:androidx/core/graphics/PathParser.class */
public class PathParser {
    private static final String LOGTAG = "PathParser";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: output.jar:androidx/core/graphics/PathParser$ExtractFloatResult.class */
    public static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        ExtractFloatResult() {
        }
    }

    /* loaded from: output.jar:androidx/core/graphics/PathParser$PathDataNode.class */
    public static class PathDataNode {
        public float[] mParams;
        public char mType;

        PathDataNode(char c, float[] fArr) {
            this.mType = c;
            this.mParams = fArr;
        }

        PathDataNode(PathDataNode pathDataNode) {
            this.mType = pathDataNode.mType;
            float[] fArr = pathDataNode.mParams;
            this.mParams = PathParser.copyOfRange(fArr, 0, fArr.length);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x03ad, code lost:
            if (r28 == 'T') goto L92;
         */
        /* JADX WARN: Code restructure failed: missing block: B:99:0x041c, code lost:
            if (r28 == 'S') goto L102;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static void addCommand(android.graphics.Path r11, float[] r12, char r13, char r14, float[] r15) {
            /*
                Method dump skipped, instructions count: 2136
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PathParser.PathDataNode.addCommand(android.graphics.Path, float[], char, char, float[]):void");
        }

        private static void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
            int ceil = (int) Math.ceil(Math.abs((d9 * 4.0d) / 3.141592653589793d));
            double cos = Math.cos(d7);
            double sin = Math.sin(d7);
            double cos2 = Math.cos(d8);
            double sin2 = Math.sin(d8);
            double d10 = -d3;
            double d11 = d10 * cos;
            double d12 = d4 * sin;
            double d13 = d10 * sin;
            double d14 = d4 * cos;
            double d15 = d9 / ceil;
            int i = 0;
            double d16 = d5;
            double d17 = d8;
            double d18 = (d11 * sin2) - (d12 * cos2);
            double d19 = (sin2 * d13) + (cos2 * d14);
            double d20 = d6;
            while (i < ceil) {
                double d21 = d17 + d15;
                double sin3 = Math.sin(d21);
                double cos3 = Math.cos(d21);
                double d22 = (d + ((d3 * cos) * cos3)) - (d12 * sin3);
                double d23 = d2 + (d3 * sin * cos3) + (d14 * sin3);
                double d24 = (d11 * sin3) - (d12 * cos3);
                double d25 = (sin3 * d13) + (cos3 * d14);
                double d26 = d21 - d17;
                double tan = Math.tan(d26 / 2.0d);
                double sin4 = (Math.sin(d26) * (Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d)) / 3.0d;
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) (d16 + (d18 * sin4)), (float) (d20 + (d19 * sin4)), (float) (d22 - (sin4 * d24)), (float) (d23 - (sin4 * d25)), (float) d22, (float) d23);
                i++;
                d16 = d22;
                d17 = d21;
                d19 = d25;
                d18 = d24;
                d20 = d23;
            }
        }

        private static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d;
            double d2;
            double radians = Math.toRadians(f7);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d3 = f;
            double d4 = f2;
            double d5 = f5;
            double d6 = ((d3 * cos) + (d4 * sin)) / d5;
            double d7 = f6;
            double d8 = (((-f) * sin) + (d4 * cos)) / d7;
            double d9 = f4;
            double d10 = ((f3 * cos) + (d9 * sin)) / d5;
            double d11 = (((-f3) * sin) + (d9 * cos)) / d7;
            double d12 = d6 - d10;
            double d13 = d8 - d11;
            double d14 = (d6 + d10) / 2.0d;
            double d15 = (d8 + d11) / 2.0d;
            double d16 = (d12 * d12) + (d13 * d13);
            if (d16 == 0.0d) {
                Log.w(PathParser.LOGTAG, " Points are coincident");
                return;
            }
            double d17 = (1.0d / d16) - 0.25d;
            if (d17 < 0.0d) {
                Log.w(PathParser.LOGTAG, "Points are too far apart " + d16);
                float sqrt = (float) (Math.sqrt(d16) / 1.99999d);
                drawArc(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
                return;
            }
            double sqrt2 = Math.sqrt(d17);
            double d18 = d12 * sqrt2;
            double d19 = sqrt2 * d13;
            if (z == z2) {
                d = d14 - d19;
                d2 = d15 + d18;
            } else {
                d = d14 + d19;
                d2 = d15 - d18;
            }
            double atan2 = Math.atan2(d8 - d2, d6 - d);
            double atan22 = Math.atan2(d11 - d2, d10 - d) - atan2;
            int i = (atan22 > 0.0d ? 1 : (atan22 == 0.0d ? 0 : -1));
            double d20 = atan22;
            if (z2 != (i >= 0)) {
                d20 = i > 0 ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
            }
            double d21 = d * d5;
            double d22 = d2 * d7;
            arcToBezier(path, (d21 * cos) - (d22 * sin), (d21 * sin) + (d22 * cos), d5, d7, d3, d4, radians, atan2, d20);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] fArr = new float[6];
            char c = 'm';
            int i = 0;
            while (true) {
                char c2 = c;
                if (i >= pathDataNodeArr.length) {
                    return;
                }
                PathDataNode pathDataNode = pathDataNodeArr[i];
                addCommand(path, fArr, c2, pathDataNode.mType, pathDataNode.mParams);
                c = pathDataNodeArr[i].mType;
                i++;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f) {
            this.mType = pathDataNode.mType;
            int i = 0;
            while (true) {
                float[] fArr = pathDataNode.mParams;
                if (i >= fArr.length) {
                    return;
                }
                this.mParams[i] = (fArr[i] * (1.0f - f)) + (pathDataNode2.mParams[i] * f);
                i++;
            }
        }
    }

    private PathParser() {
    }

    private static void addNode(ArrayList<PathDataNode> arrayList, char c, float[] fArr) {
        arrayList.add(new PathDataNode(c, fArr));
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            if (pathDataNodeArr[i].mType != pathDataNodeArr2[i].mType || pathDataNodeArr[i].mParams.length != pathDataNodeArr2[i].mParams.length) {
                return false;
            }
        }
        return true;
    }

    static float[] copyOfRange(float[] fArr, int i, int i2) {
        if (i <= i2) {
            int length = fArr.length;
            if (i < 0 || i > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            float[] fArr2 = new float[i3];
            System.arraycopy(fArr, i, fArr2, 0, min);
            return fArr2;
        }
        throw new IllegalArgumentException();
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 0;
        while (i < str.length()) {
            int nextStart = nextStart(str, i);
            String trim = str.substring(i2, nextStart).trim();
            if (trim.length() > 0) {
                addNode(arrayList, trim.charAt(0), getFloats(trim));
            }
            i2 = nextStart;
            i = nextStart + 1;
        }
        if (i - i2 == 1 && i2 < str.length()) {
            addNode(arrayList, str.charAt(i2), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        PathDataNode[] createNodesFromPathData = createNodesFromPathData(str);
        if (createNodesFromPathData != null) {
            try {
                PathDataNode.nodesToPath(createNodesFromPathData, path);
                return path;
            } catch (RuntimeException e) {
                throw new RuntimeException("Error in parsing " + str, e);
            }
        }
        return null;
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        if (pathDataNodeArr == null) {
            return null;
        }
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            pathDataNodeArr2[i] = new PathDataNode(pathDataNodeArr[i]);
        }
        return pathDataNodeArr2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a1 A[LOOP:0: B:3:0x001a->B:29:0x00a1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void extract(java.lang.String r3, int r4, androidx.core.graphics.PathParser.ExtractFloatResult r5) {
        /*
            r0 = r5
            r1 = 0
            r0.mEndWithNegOrDot = r1
            r0 = r4
            r6 = r0
            r0 = 0
            r7 = r0
            r0 = r7
            r8 = r0
            r0 = r8
            r9 = r0
            r0 = r8
            r10 = r0
            r0 = r7
            r8 = r0
        L1a:
            r0 = r6
            r1 = r3
            int r1 = r1.length()
            if (r0 >= r1) goto La7
            r0 = r3
            r1 = r6
            char r0 = r0.charAt(r1)
            r7 = r0
            r0 = r7
            r1 = 32
            if (r0 == r1) goto L93
            r0 = r7
            r1 = 69
            if (r0 == r1) goto L8d
            r0 = r7
            r1 = 101(0x65, float:1.42E-43)
            if (r0 == r1) goto L8d
            r0 = r7
            switch(r0) {
                case 44: goto L93;
                case 45: goto L75;
                case 46: goto L5f;
                default: goto L5c;
            }
        L5c:
            goto L87
        L5f:
            r0 = r10
            if (r0 != 0) goto L6d
            r0 = 0
            r8 = r0
            r0 = 1
            r10 = r0
            goto L99
        L6d:
            r0 = r5
            r1 = 1
            r0.mEndWithNegOrDot = r1
            goto L93
        L75:
            r0 = r6
            r1 = r4
            if (r0 == r1) goto L87
            r0 = r8
            if (r0 != 0) goto L87
            r0 = r5
            r1 = 1
            r0.mEndWithNegOrDot = r1
            goto L93
        L87:
            r0 = 0
            r8 = r0
            goto L99
        L8d:
            r0 = 1
            r8 = r0
            goto L99
        L93:
            r0 = 0
            r8 = r0
            r0 = 1
            r9 = r0
        L99:
            r0 = r9
            if (r0 == 0) goto La1
            goto La7
        La1:
            int r6 = r6 + 1
            goto L1a
        La7:
            r0 = r5
            r1 = r6
            r0.mEndPosition = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PathParser.extract(java.lang.String, int, androidx.core.graphics.PathParser$ExtractFloatResult):void");
    }

    private static float[] getFloats(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            ExtractFloatResult extractFloatResult = new ExtractFloatResult();
            int length = str.length();
            int i = 1;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i >= length) {
                    return copyOfRange(fArr, 0, i3);
                }
                extract(str, i, extractFloatResult);
                int i4 = extractFloatResult.mEndPosition;
                int i5 = i3;
                if (i < i4) {
                    fArr[i3] = Float.parseFloat(str.substring(i, i4));
                    i5 = i3 + 1;
                }
                if (extractFloatResult.mEndWithNegOrDot) {
                    i = i4;
                    i2 = i5;
                } else {
                    i = i4 + 1;
                    i2 = i5;
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("error in parsing \"" + str + "\"", e);
        }
    }

    public static boolean interpolatePathDataNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2, PathDataNode[] pathDataNodeArr3, float f) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr3 == null) {
            throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes cannot be null");
        }
        if (pathDataNodeArr.length == pathDataNodeArr2.length && pathDataNodeArr2.length == pathDataNodeArr3.length) {
            if (canMorph(pathDataNodeArr2, pathDataNodeArr3)) {
                for (int i = 0; i < pathDataNodeArr.length; i++) {
                    pathDataNodeArr[i].interpolatePathDataNode(pathDataNodeArr2[i], pathDataNodeArr3[i], f);
                }
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes must have the same length");
    }

    private static int nextStart(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                return i;
            }
            i++;
        }
        return i;
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int i = 0; i < pathDataNodeArr2.length; i++) {
            pathDataNodeArr[i].mType = pathDataNodeArr2[i].mType;
            for (int i2 = 0; i2 < pathDataNodeArr2[i].mParams.length; i2++) {
                pathDataNodeArr[i].mParams[i2] = pathDataNodeArr2[i].mParams[i2];
            }
        }
    }
}