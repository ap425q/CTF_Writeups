package kotlin.text;

import kotlin.Annotations;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m20d1 = {"\u0000\\\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\bH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\tH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\nH\u0087\b\u001a%\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000e\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\b\u001a-\u0010\u000b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a-\u0010\u000b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a\u0014\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u0012H\u0007\u001a\u001d\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u00122\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u00122\b\u0010\u0003\u001a\u0004\u0018\u00010\u000fH\u0087\b\u001a\u0014\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0007\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0014H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0015H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\fH\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u000fH\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\bH\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\tH\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\nH\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0016H\u0087\b\u001a%\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000e\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\b\u001a\u0014\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0007\u001a\u001d\u0010\u0018\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\bH\u0087\b\u001a%\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a5\u0010\u001b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a5\u0010\u001b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a!\u0010\u001c\u001a\u00020\u001d*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\n\u001a-\u0010\u001e\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0016H\u0087\b\u001a7\u0010\u001f\u001a\u00020\u001d*\u00060\u0001j\u0002`\u00022\u0006\u0010 \u001a\u00020\f2\b\b\u0002\u0010!\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\bH\u0087\b¨\u0006\""}, m19d2 = {"appendLine", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "value", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "appendRange", "", "startIndex", "endIndex", "", "appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "", "", "", "", "clear", "deleteAt", "index", "deleteRange", "insertRange", "set", "", "setRange", "toCharArray", "destination", "destinationOffset", "kotlin-stdlib"}, m18k = 5, m17mv = {1, 5, 1}, m15xi = 1, m14xs = "kotlin/text/StringsKt")
/* renamed from: kotlin.text.StringsKt__StringBuilderJVMKt */
/* loaded from: classes.dex */
class StringBuilderJVM extends RegexExtensions {
    public static final StringBuilder clear(StringBuilder clear) {
        Intrinsics.checkNotNullParameter(clear, "$this$clear");
        clear.setLength(0);
        return clear;
    }

    private static final void set(StringBuilder set, int index, char value) {
        Intrinsics.checkNotNullParameter(set, "$this$set");
        set.setCharAt(index, value);
    }

    private static final StringBuilder setRange(StringBuilder $this$setRange, int startIndex, int endIndex, String value) {
        StringBuilder replace = $this$setRange.replace(startIndex, endIndex, value);
        Intrinsics.checkNotNullExpressionValue(replace, "this.replace(startIndex, endIndex, value)");
        return replace;
    }

    private static final StringBuilder deleteAt(StringBuilder $this$deleteAt, int index) {
        StringBuilder deleteCharAt = $this$deleteAt.deleteCharAt(index);
        Intrinsics.checkNotNullExpressionValue(deleteCharAt, "this.deleteCharAt(index)");
        return deleteCharAt;
    }

    private static final StringBuilder deleteRange(StringBuilder $this$deleteRange, int startIndex, int endIndex) {
        StringBuilder delete = $this$deleteRange.delete(startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(delete, "this.delete(startIndex, endIndex)");
        return delete;
    }

    static /* synthetic */ void toCharArray$default(StringBuilder $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex, int i, Object obj) {
        if ((i & 2) != 0) {
            destinationOffset = 0;
        }
        if ((i & 4) != 0) {
            startIndex = 0;
        }
        if ((i & 8) != 0) {
            endIndex = $this$toCharArray.length();
        }
        $this$toCharArray.getChars(startIndex, endIndex, destination, destinationOffset);
    }

    private static final void toCharArray(StringBuilder $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex) {
        $this$toCharArray.getChars(startIndex, endIndex, destination, destinationOffset);
    }

    private static final StringBuilder appendRange(StringBuilder $this$appendRange, char[] value, int startIndex, int endIndex) {
        $this$appendRange.append(value, startIndex, endIndex - startIndex);
        Intrinsics.checkNotNullExpressionValue($this$appendRange, "this.append(value, start…x, endIndex - startIndex)");
        return $this$appendRange;
    }

    private static final StringBuilder appendRange(StringBuilder $this$appendRange, CharSequence value, int startIndex, int endIndex) {
        $this$appendRange.append(value, startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue($this$appendRange, "this.append(value, startIndex, endIndex)");
        return $this$appendRange;
    }

    private static final StringBuilder insertRange(StringBuilder $this$insertRange, int index, char[] value, int startIndex, int endIndex) {
        StringBuilder insert = $this$insertRange.insert(index, value, startIndex, endIndex - startIndex);
        Intrinsics.checkNotNullExpressionValue(insert, "this.insert(index, value…x, endIndex - startIndex)");
        return insert;
    }

    private static final StringBuilder insertRange(StringBuilder $this$insertRange, int index, CharSequence value, int startIndex, int endIndex) {
        StringBuilder insert = $this$insertRange.insert(index, value, startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(insert, "this.insert(index, value, startIndex, endIndex)");
        return insert;
    }

    private static final StringBuilder appendLine(StringBuilder $this$appendLine, StringBuffer value) {
        $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append(value)");
        $this$appendLine.append('\n');
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append('\\n')");
        return $this$appendLine;
    }

    private static final StringBuilder appendLine(StringBuilder $this$appendLine, StringBuilder value) {
        $this$appendLine.append((CharSequence) value);
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append(value)");
        $this$appendLine.append('\n');
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append('\\n')");
        return $this$appendLine;
    }

    private static final StringBuilder appendLine(StringBuilder $this$appendLine, int value) {
        $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append(value)");
        $this$appendLine.append('\n');
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append('\\n')");
        return $this$appendLine;
    }

    private static final StringBuilder appendLine(StringBuilder $this$appendLine, short value) {
        $this$appendLine.append((int) value);
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append(value.toInt())");
        $this$appendLine.append('\n');
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append('\\n')");
        return $this$appendLine;
    }

    private static final StringBuilder appendLine(StringBuilder $this$appendLine, byte value) {
        $this$appendLine.append((int) value);
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append(value.toInt())");
        $this$appendLine.append('\n');
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append('\\n')");
        return $this$appendLine;
    }

    private static final StringBuilder appendLine(StringBuilder $this$appendLine, long value) {
        $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append(value)");
        $this$appendLine.append('\n');
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append('\\n')");
        return $this$appendLine;
    }

    private static final StringBuilder appendLine(StringBuilder $this$appendLine, float value) {
        $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append(value)");
        $this$appendLine.append('\n');
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append('\\n')");
        return $this$appendLine;
    }

    private static final StringBuilder appendLine(StringBuilder $this$appendLine, double value) {
        $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append(value)");
        $this$appendLine.append('\n');
        Intrinsics.checkNotNullExpressionValue($this$appendLine, "append('\\n')");
        return $this$appendLine;
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine()", imports = {}))
    public static final Appendable appendln(Appendable appendln) {
        Intrinsics.checkNotNullParameter(appendln, "$this$appendln");
        Appendable append = appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkNotNullExpressionValue(append, "append(SystemProperties.LINE_SEPARATOR)");
        return append;
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final Appendable appendln(Appendable $this$appendln, CharSequence value) {
        Appendable append = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(append, "append(value)");
        return StringsKt.appendln(append);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final Appendable appendln(Appendable $this$appendln, char value) {
        Appendable append = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(append, "append(value)");
        return StringsKt.appendln(append);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine()", imports = {}))
    public static final StringBuilder appendln(StringBuilder appendln) {
        Intrinsics.checkNotNullParameter(appendln, "$this$appendln");
        appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkNotNullExpressionValue(appendln, "append(SystemProperties.LINE_SEPARATOR)");
        return appendln;
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, StringBuffer value) {
        $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, CharSequence value) {
        $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, String value) {
        $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, Object value) {
        $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, StringBuilder value) {
        $this$appendln.append((CharSequence) value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, char[] value) {
        $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, char value) {
        $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, boolean value) {
        $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, int value) {
        $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, short value) {
        $this$appendln.append((int) value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value.toInt())");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, byte value) {
        $this$appendln.append((int) value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value.toInt())");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, long value) {
        $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, float value) {
        $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }

    @Annotations(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder $this$appendln, double value) {
        $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue($this$appendln, "append(value)");
        return StringsKt.appendln($this$appendln);
    }
}