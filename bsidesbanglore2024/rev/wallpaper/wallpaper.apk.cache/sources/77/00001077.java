package kotlin.text;

import kotlin.Metadata;

@Metadata(m20d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m19d2 = {"Lkotlin/text/ScreenFloatValueRegEx;", "", "()V", "value", "Lkotlin/text/Regex;", "kotlin-stdlib"}, m18k = 1, m17mv = {1, 5, 1})
/* renamed from: kotlin.text.ScreenFloatValueRegEx */
/* loaded from: classes.dex */
final class StringNumberConversionsJVM {
    public static final StringNumberConversionsJVM INSTANCE = new StringNumberConversionsJVM();
    public static final Regex value;

    static {
        String Exp = "[eE][+-]?(\\p{Digit}+)";
        String HexString = "(0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+))";
        String Number = "((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)(" + Exp + ")?)|(\\.((\\p{Digit}+))(" + Exp + ")?)|((" + HexString + ")[pP][+-]?(\\p{Digit}+))";
        String fpRegex = "[\\x00-\\x20]*[+-]?(NaN|Infinity|((" + Number + ")[fFdD]?))[\\x00-\\x20]*";
        value = new Regex(fpRegex);
    }

    private StringNumberConversionsJVM() {
    }
}