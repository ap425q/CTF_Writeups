package androidx.appcompat.app;

import android.util.AttributeSet;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: output.jar:androidx/appcompat/app/LayoutIncludeDetector.class */
class LayoutIncludeDetector {
    private final Deque<WeakReference<XmlPullParser>> mXmlParserStack = new ArrayDeque();

    private static boolean isParserOutdated(XmlPullParser xmlPullParser) {
        boolean z = true;
        if (xmlPullParser != null) {
            z = true;
            try {
                if (xmlPullParser.getEventType() != 3) {
                    z = xmlPullParser.getEventType() == 1;
                }
            } catch (XmlPullParserException e) {
                z = true;
            }
        }
        return z;
    }

    private static XmlPullParser popOutdatedAttrHolders(Deque<WeakReference<XmlPullParser>> deque) {
        while (!deque.isEmpty()) {
            XmlPullParser xmlPullParser = deque.peek().get();
            if (!isParserOutdated(xmlPullParser)) {
                return xmlPullParser;
            }
            deque.pop();
        }
        return null;
    }

    private static boolean shouldInheritContext(XmlPullParser xmlPullParser, XmlPullParser xmlPullParser2) {
        if (xmlPullParser2 == null || xmlPullParser == xmlPullParser2) {
            return false;
        }
        try {
            if (xmlPullParser2.getEventType() == 2) {
                return "include".equals(xmlPullParser2.getName());
            }
            return false;
        } catch (XmlPullParserException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean detect(AttributeSet attributeSet) {
        if (attributeSet instanceof XmlPullParser) {
            XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
            if (xmlPullParser.getDepth() == 1) {
                XmlPullParser popOutdatedAttrHolders = popOutdatedAttrHolders(this.mXmlParserStack);
                this.mXmlParserStack.push(new WeakReference<>(xmlPullParser));
                return shouldInheritContext(xmlPullParser, popOutdatedAttrHolders);
            }
            return false;
        }
        return false;
    }
}