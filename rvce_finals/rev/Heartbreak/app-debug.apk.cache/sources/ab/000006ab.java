package androidx.core.p003os;

import android.content.res.Configuration;
import android.os.LocaleList;

/* renamed from: androidx.core.os.ConfigurationCompat */
/* loaded from: classes.dex */
public final class ConfigurationCompat {
    private ConfigurationCompat() {
    }

    public static LocaleListCompat getLocales(Configuration configuration) {
        return LocaleListCompat.wrap(Api24Impl.getLocales(configuration));
    }

    public static void setLocales(Configuration configuration, LocaleListCompat locales) {
        Api24Impl.setLocales(configuration, locales);
    }

    /* renamed from: androidx.core.os.ConfigurationCompat$Api24Impl */
    /* loaded from: classes.dex */
    static class Api24Impl {
        private Api24Impl() {
        }

        static LocaleList getLocales(Configuration configuration) {
            return configuration.getLocales();
        }

        static void setLocales(Configuration configuration, LocaleListCompat locales) {
            configuration.setLocales((LocaleList) locales.unwrap());
        }
    }
}