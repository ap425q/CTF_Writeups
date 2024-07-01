package androidx.core.database.sqlite;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m20d1 = {"\u0000\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, m19d2 = {"transaction", "T", "Landroid/database/sqlite/SQLiteDatabase;", "exclusive", "", "body", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroid/database/sqlite/SQLiteDatabase;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "core-ktx_release"}, m18k = 2, m17mv = {1, 5, 1}, m15xi = 48)
/* renamed from: androidx.core.database.sqlite.SQLiteDatabaseKt */
/* loaded from: classes.dex */
public final class SQLiteDatabase {
    public static /* synthetic */ Object transaction$default(android.database.sqlite.SQLiteDatabase $this$transaction_u24default, boolean exclusive, Function1 body, int i, Object obj) {
        if ((i & 1) != 0) {
            exclusive = true;
        }
        Intrinsics.checkNotNullParameter($this$transaction_u24default, "<this>");
        Intrinsics.checkNotNullParameter(body, "body");
        if (exclusive) {
            $this$transaction_u24default.beginTransaction();
        } else {
            $this$transaction_u24default.beginTransactionNonExclusive();
        }
        try {
            Object invoke = body.invoke($this$transaction_u24default);
            $this$transaction_u24default.setTransactionSuccessful();
            return invoke;
        } finally {
            InlineMarker.finallyStart(1);
            $this$transaction_u24default.endTransaction();
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T transaction(android.database.sqlite.SQLiteDatabase $this$transaction, boolean exclusive, Function1<? super android.database.sqlite.SQLiteDatabase, ? extends T> body) {
        Intrinsics.checkNotNullParameter($this$transaction, "<this>");
        Intrinsics.checkNotNullParameter(body, "body");
        if (exclusive) {
            $this$transaction.beginTransaction();
        } else {
            $this$transaction.beginTransactionNonExclusive();
        }
        try {
            T invoke = body.invoke($this$transaction);
            $this$transaction.setTransactionSuccessful();
            return invoke;
        } finally {
            InlineMarker.finallyStart(1);
            $this$transaction.endTransaction();
            InlineMarker.finallyEnd(1);
        }
    }
}