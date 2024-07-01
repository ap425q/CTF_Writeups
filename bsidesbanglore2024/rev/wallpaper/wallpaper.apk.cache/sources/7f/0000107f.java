package kotlin.text;

import java.util.Set;
import kotlin.Metadata;

@Metadata(m20d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0087\b¨\u0006\u0007"}, m19d2 = {"toRegex", "Lkotlin/text/Regex;", "", "options", "", "Lkotlin/text/RegexOption;", "option", "kotlin-stdlib"}, m18k = 5, m17mv = {1, 5, 1}, m15xi = 1, m14xs = "kotlin/text/StringsKt")
/* renamed from: kotlin.text.StringsKt__RegexExtensionsKt */
/* loaded from: classes.dex */
class RegexExtensions extends RegexExtensionsJVM {
    private static final Regex toRegex(String $this$toRegex) {
        return new Regex($this$toRegex);
    }

    private static final Regex toRegex(String $this$toRegex, RegexOption option) {
        return new Regex($this$toRegex, option);
    }

    private static final Regex toRegex(String $this$toRegex, Set<? extends RegexOption> set) {
        return new Regex($this$toRegex, set);
    }
}