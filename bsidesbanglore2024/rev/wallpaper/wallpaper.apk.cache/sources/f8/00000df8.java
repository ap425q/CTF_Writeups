package kotlin.collections;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ArraysUtilJVM {
    ArraysUtilJVM() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> List<T> asList(T[] array) {
        return Arrays.asList(array);
    }
}