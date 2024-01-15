package androidx.core.os;

import android.content.res.Configuration;

/* loaded from: output.jar:androidx/core/os/ConfigurationCompat.class */
public final class ConfigurationCompat {
    private ConfigurationCompat() {
    }

    public static LocaleListCompat getLocales(Configuration configuration) {
        return LocaleListCompat.wrap(configuration.getLocales());
    }
}