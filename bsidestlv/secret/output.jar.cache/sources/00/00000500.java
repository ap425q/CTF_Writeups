package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: output.jar:androidx/core/graphics/TypefaceCompatApi29Impl.class */
public class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        Typeface typeface;
        FontFamily.Builder builder;
        try {
            FontResourcesParserCompat.FontFileResourceEntry[] entries = fontFamilyFilesResourceEntry.getEntries();
            int length = entries.length;
            builder = null;
            int i2 = 0;
            while (true) {
                int i3 = 1;
                if (i2 >= length) {
                    break;
                }
                FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry = entries[i2];
                try {
                    Font.Builder weight = new Font.Builder(resources, fontFileResourceEntry.getResourceId()).setWeight(fontFileResourceEntry.getWeight());
                    if (!fontFileResourceEntry.isItalic()) {
                        i3 = 0;
                    }
                    Font build = weight.setSlant(i3).setTtcIndex(fontFileResourceEntry.getTtcIndex()).setFontVariationSettings(fontFileResourceEntry.getVariationSettings()).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(build);
                    } else {
                        builder.addFont(build);
                    }
                } catch (IOException e) {
                }
                i2++;
            }
        } catch (Exception e2) {
            typeface = null;
        }
        if (builder == null) {
            return null;
        }
        int i4 = (i & 1) != 0 ? 700 : 400;
        int i5 = 0;
        if ((i & 2) != 0) {
            i5 = 1;
        }
        typeface = new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle(i4, i5)).build();
        return typeface;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003e, code lost:
        if (r0 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00aa, code lost:
        if (r0 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00db, code lost:
        if (r6 != null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00de, code lost:
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e8, code lost:
        if ((r9 & 1) == 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00eb, code lost:
        r13 = 700;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f3, code lost:
        r13 = 400;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00f8, code lost:
        r14 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0100, code lost:
        if ((r9 & 2) == 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0103, code lost:
        r14 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0124, code lost:
        return new android.graphics.Typeface.CustomFallbackBuilder(r6.build()).setStyle(new android.graphics.fonts.FontStyle(r13, r14)).build();
     */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r6, android.os.CancellationSignal r7, androidx.core.provider.FontsContractCompat.FontInfo[] r8, int r9) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatApi29Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, androidx.core.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        try {
            Font build = new Font.Builder(resources, i).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(build).build()).setStyle(build.getStyle()).build();
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}