package androidx.arch.core.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: output.jar:androidx/arch/core/internal/SafeIterableMap.class */
public class SafeIterableMap<K, V> implements Iterable<Map.Entry<K, V>> {
    private Entry<K, V> mEnd;
    private WeakHashMap<SupportRemove<K, V>, Boolean> mIterators = new WeakHashMap<>();
    private int mSize = 0;
    Entry<K, V> mStart;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: output.jar:androidx/arch/core/internal/SafeIterableMap$AscendingIterator.class */
    public static class AscendingIterator<K, V> extends ListIterator<K, V> {
        AscendingIterator(Entry<K, V> entry, Entry<K, V> entry2) {
            super(entry, entry2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry<K, V> backward(Entry<K, V> entry) {
            return entry.mPrevious;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry<K, V> forward(Entry<K, V> entry) {
            return entry.mNext;
        }
    }

    /* loaded from: output.jar:androidx/arch/core/internal/SafeIterableMap$DescendingIterator.class */
    private static class DescendingIterator<K, V> extends ListIterator<K, V> {
        DescendingIterator(Entry<K, V> entry, Entry<K, V> entry2) {
            super(entry, entry2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry<K, V> backward(Entry<K, V> entry) {
            return entry.mNext;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry<K, V> forward(Entry<K, V> entry) {
            return entry.mPrevious;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: output.jar:androidx/arch/core/internal/SafeIterableMap$Entry.class */
    public static class Entry<K, V> implements Map.Entry<K, V> {
        final K mKey;
        Entry<K, V> mNext;
        Entry<K, V> mPrevious;
        final V mValue;

        Entry(K k, V v) {
            this.mKey = k;
            this.mValue = v;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (obj instanceof Entry) {
                Entry entry = (Entry) obj;
                if (!this.mKey.equals(entry.mKey) || !this.mValue.equals(entry.mValue)) {
                    z = false;
                }
                return z;
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.mKey;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.mValue;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.mKey.hashCode() ^ this.mValue.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.mKey + "=" + this.mValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: output.jar:androidx/arch/core/internal/SafeIterableMap$IteratorWithAdditions.class */
    public class IteratorWithAdditions implements Iterator<Map.Entry<K, V>>, SupportRemove<K, V> {
        private boolean mBeforeStart = true;
        private Entry<K, V> mCurrent;

        IteratorWithAdditions() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            boolean z = true;
            if (!this.mBeforeStart) {
                Entry<K, V> entry = this.mCurrent;
                return (entry == null || entry.mNext == null) ? false : true;
            }
            if (SafeIterableMap.this.mStart == null) {
                z = false;
            }
            return z;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            if (this.mBeforeStart) {
                this.mBeforeStart = false;
                this.mCurrent = SafeIterableMap.this.mStart;
            } else {
                Entry<K, V> entry = this.mCurrent;
                this.mCurrent = entry != null ? entry.mNext : null;
            }
            return this.mCurrent;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.SupportRemove
        public void supportRemove(Entry<K, V> entry) {
            Entry<K, V> entry2 = this.mCurrent;
            if (entry == entry2) {
                Entry<K, V> entry3 = entry2.mPrevious;
                this.mCurrent = entry3;
                this.mBeforeStart = entry3 == null;
            }
        }
    }

    /* loaded from: output.jar:androidx/arch/core/internal/SafeIterableMap$ListIterator.class */
    private static abstract class ListIterator<K, V> implements Iterator<Map.Entry<K, V>>, SupportRemove<K, V> {
        Entry<K, V> mExpectedEnd;
        Entry<K, V> mNext;

        ListIterator(Entry<K, V> entry, Entry<K, V> entry2) {
            this.mExpectedEnd = entry2;
            this.mNext = entry;
        }

        private Entry<K, V> nextNode() {
            Entry<K, V> entry = this.mNext;
            Entry<K, V> entry2 = this.mExpectedEnd;
            if (entry == entry2 || entry2 == null) {
                return null;
            }
            return forward(entry);
        }

        abstract Entry<K, V> backward(Entry<K, V> entry);

        abstract Entry<K, V> forward(Entry<K, V> entry);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.mNext != null;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            Entry<K, V> entry = this.mNext;
            this.mNext = nextNode();
            return entry;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.SupportRemove
        public void supportRemove(Entry<K, V> entry) {
            if (this.mExpectedEnd == entry && entry == this.mNext) {
                this.mNext = null;
                this.mExpectedEnd = null;
            }
            Entry<K, V> entry2 = this.mExpectedEnd;
            if (entry2 == entry) {
                this.mExpectedEnd = backward(entry2);
            }
            if (this.mNext == entry) {
                this.mNext = nextNode();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: output.jar:androidx/arch/core/internal/SafeIterableMap$SupportRemove.class */
    public interface SupportRemove<K, V> {
        void supportRemove(Entry<K, V> entry);
    }

    public Iterator<Map.Entry<K, V>> descendingIterator() {
        DescendingIterator descendingIterator = new DescendingIterator(this.mEnd, this.mStart);
        this.mIterators.put(descendingIterator, false);
        return descendingIterator;
    }

    public Map.Entry<K, V> eldest() {
        return this.mStart;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (obj instanceof SafeIterableMap) {
            SafeIterableMap safeIterableMap = (SafeIterableMap) obj;
            if (size() != safeIterableMap.size()) {
                return false;
            }
            Iterator<Map.Entry<K, V>> it = iterator();
            Iterator<Map.Entry<K, V>> it2 = safeIterableMap.iterator();
            while (it.hasNext() && it2.hasNext()) {
                Map.Entry<K, V> next = it.next();
                Map.Entry<K, V> next2 = it2.next();
                if (next == null && next2 != null) {
                    return false;
                }
                if (next != null && !next.equals(next2)) {
                    return false;
                }
            }
            if (it.hasNext() || it2.hasNext()) {
                z = false;
            }
            return z;
        }
        return false;
    }

    protected Entry<K, V> get(K k) {
        Entry<K, V> entry;
        Entry<K, V> entry2 = this.mStart;
        while (true) {
            entry = entry2;
            if (entry == null || entry.mKey.equals(k)) {
                break;
            }
            entry2 = entry.mNext;
        }
        return entry;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int i = 0;
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = i2 + it.next().hashCode();
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        AscendingIterator ascendingIterator = new AscendingIterator(this.mStart, this.mEnd);
        this.mIterators.put(ascendingIterator, false);
        return ascendingIterator;
    }

    public SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions() {
        SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions = new IteratorWithAdditions();
        this.mIterators.put(iteratorWithAdditions, false);
        return iteratorWithAdditions;
    }

    public Map.Entry<K, V> newest() {
        return this.mEnd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Entry<K, V> put(K k, V v) {
        Entry<K, V> entry = new Entry<>(k, v);
        this.mSize++;
        Entry<K, V> entry2 = this.mEnd;
        if (entry2 == null) {
            this.mStart = entry;
            this.mEnd = entry;
            return entry;
        }
        entry2.mNext = entry;
        entry.mPrevious = this.mEnd;
        this.mEnd = entry;
        return entry;
    }

    public V putIfAbsent(K k, V v) {
        Entry<K, V> entry = get(k);
        if (entry != null) {
            return entry.mValue;
        }
        put(k, v);
        return null;
    }

    public V remove(K k) {
        Entry<K, V> entry = get(k);
        if (entry == null) {
            return null;
        }
        this.mSize--;
        if (!this.mIterators.isEmpty()) {
            for (SupportRemove<K, V> supportRemove : this.mIterators.keySet()) {
                supportRemove.supportRemove(entry);
            }
        }
        if (entry.mPrevious != null) {
            entry.mPrevious.mNext = entry.mNext;
        } else {
            this.mStart = entry.mNext;
        }
        if (entry.mNext != null) {
            entry.mNext.mPrevious = entry.mPrevious;
        } else {
            this.mEnd = entry.mPrevious;
        }
        entry.mNext = null;
        entry.mPrevious = null;
        return entry.mValue;
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}