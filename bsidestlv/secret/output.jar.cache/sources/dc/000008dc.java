package androidx.recyclerview.widget;

import androidx.core.util.Pools;
import androidx.recyclerview.widget.OpReorderer;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: output.jar:androidx/recyclerview/widget/AdapterHelper.class */
public class AdapterHelper implements OpReorderer.Callback {
    private static final boolean DEBUG = false;
    static final int POSITION_TYPE_INVISIBLE = 0;
    static final int POSITION_TYPE_NEW_OR_LAID_OUT = 1;
    private static final String TAG = "AHT";
    final Callback mCallback;
    final boolean mDisableRecycler;
    private int mExistingUpdateTypes;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList<UpdateOp> mPendingUpdates;
    final ArrayList<UpdateOp> mPostponedList;
    private Pools.Pool<UpdateOp> mUpdateOpPool;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: output.jar:androidx/recyclerview/widget/AdapterHelper$Callback.class */
    public interface Callback {
        RecyclerView.ViewHolder findViewHolder(int i);

        void markViewHoldersUpdated(int i, int i2, Object obj);

        void offsetPositionsForAdd(int i, int i2);

        void offsetPositionsForMove(int i, int i2);

        void offsetPositionsForRemovingInvisible(int i, int i2);

        void offsetPositionsForRemovingLaidOutOrNewView(int i, int i2);

        void onDispatchFirstPass(UpdateOp updateOp);

        void onDispatchSecondPass(UpdateOp updateOp);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: output.jar:androidx/recyclerview/widget/AdapterHelper$UpdateOp.class */
    public static class UpdateOp {
        static final int ADD = 1;
        static final int MOVE = 8;
        static final int POOL_SIZE = 30;
        static final int REMOVE = 2;
        static final int UPDATE = 4;
        int cmd;
        int itemCount;
        Object payload;
        int positionStart;

        UpdateOp(int i, int i2, int i3, Object obj) {
            this.cmd = i;
            this.positionStart = i2;
            this.itemCount = i3;
            this.payload = obj;
        }

        String cmdToString() {
            int i = this.cmd;
            return i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UpdateOp updateOp = (UpdateOp) obj;
            int i = this.cmd;
            if (i != updateOp.cmd) {
                return false;
            }
            if (i == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == updateOp.positionStart && this.positionStart == updateOp.itemCount) {
                return true;
            }
            if (this.itemCount == updateOp.itemCount && this.positionStart == updateOp.positionStart) {
                Object obj2 = this.payload;
                return obj2 != null ? obj2.equals(updateOp.payload) : updateOp.payload == null;
            }
            return false;
        }

        public int hashCode() {
            return (((this.cmd * 31) + this.positionStart) * 31) + this.itemCount;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + ",p:" + this.payload + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterHelper(Callback callback) {
        this(callback, false);
    }

    AdapterHelper(Callback callback, boolean z) {
        this.mUpdateOpPool = new Pools.SimplePool(30);
        this.mPendingUpdates = new ArrayList<>();
        this.mPostponedList = new ArrayList<>();
        this.mExistingUpdateTypes = 0;
        this.mCallback = callback;
        this.mDisableRecycler = z;
        this.mOpReorderer = new OpReorderer(this);
    }

    private void applyAdd(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void applyMove(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void applyRemove(UpdateOp updateOp) {
        int i;
        boolean z;
        int i2;
        boolean z2;
        int i3 = updateOp.positionStart;
        int i4 = updateOp.positionStart + updateOp.itemCount;
        int i5 = updateOp.positionStart;
        boolean z3 = true;
        int i6 = 0;
        while (true) {
            i = i6;
            if (i5 >= i4) {
                break;
            }
            if (this.mCallback.findViewHolder(i5) != null || canFindInPreLayout(i5)) {
                if (z3) {
                    z = false;
                } else {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(2, i3, i, null));
                    z = true;
                }
                z3 = true;
            } else {
                if (z3) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(2, i3, i, null));
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = z2;
                z3 = false;
            }
            if (z) {
                i5 -= i;
                i4 -= i;
                i2 = 1;
            } else {
                i2 = i + 1;
            }
            i5++;
            i6 = i2;
        }
        UpdateOp updateOp2 = updateOp;
        if (i != updateOp.itemCount) {
            recycleUpdateOp(updateOp);
            updateOp2 = obtainUpdateOp(2, i3, i, null);
        }
        if (z3) {
            postponeAndUpdateViewHolders(updateOp2);
        } else {
            dispatchAndUpdateViewHolders(updateOp2);
        }
    }

    private void applyUpdate(UpdateOp updateOp) {
        int i;
        int i2;
        boolean z;
        int i3 = updateOp.positionStart;
        int i4 = updateOp.positionStart;
        int i5 = updateOp.itemCount;
        int i6 = updateOp.positionStart;
        boolean z2 = true;
        int i7 = 0;
        while (true) {
            i = i7;
            if (i6 >= i4 + i5) {
                break;
            }
            if (this.mCallback.findViewHolder(i6) != null || canFindInPreLayout(i6)) {
                int i8 = i3;
                i2 = i;
                if (!z2) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(4, i3, i, updateOp.payload));
                    i8 = i6;
                    i2 = 0;
                }
                z = true;
                i3 = i8;
            } else {
                int i9 = i3;
                int i10 = i;
                if (z2) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(4, i3, i, updateOp.payload));
                    i9 = i6;
                    i10 = 0;
                }
                z = false;
                i3 = i9;
                i2 = i10;
            }
            i6++;
            z2 = z;
            i7 = i2 + 1;
        }
        UpdateOp updateOp2 = updateOp;
        if (i != updateOp.itemCount) {
            Object obj = updateOp.payload;
            recycleUpdateOp(updateOp);
            updateOp2 = obtainUpdateOp(4, i3, i, obj);
        }
        if (z2) {
            postponeAndUpdateViewHolders(updateOp2);
        } else {
            dispatchAndUpdateViewHolders(updateOp2);
        }
    }

    private boolean canFindInPreLayout(int i) {
        int size = this.mPostponedList.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = this.mPostponedList.get(i2);
            if (updateOp.cmd == 8) {
                if (findPositionOffset(updateOp.itemCount, i2 + 1) == i) {
                    return true;
                }
            } else if (updateOp.cmd == 1) {
                int i3 = updateOp.positionStart;
                int i4 = updateOp.itemCount;
                for (int i5 = updateOp.positionStart; i5 < i3 + i4; i5++) {
                    if (findPositionOffset(i5, i2 + 1) == i) {
                        return true;
                    }
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void dispatchAndUpdateViewHolders(UpdateOp updateOp) {
        int i;
        if (updateOp.cmd == 1 || updateOp.cmd == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int updatePositionWithPostponed = updatePositionWithPostponed(updateOp.positionStart, updateOp.cmd);
        int i2 = updateOp.positionStart;
        int i3 = updateOp.cmd;
        if (i3 == 2) {
            i = 0;
        } else if (i3 != 4) {
            throw new IllegalArgumentException("op should be remove or update." + updateOp);
        } else {
            i = 1;
        }
        int i4 = 1;
        for (int i5 = 1; i5 < updateOp.itemCount; i5++) {
            int updatePositionWithPostponed2 = updatePositionWithPostponed(updateOp.positionStart + (i * i5), updateOp.cmd);
            int i6 = updateOp.cmd;
            if (i6 == 2 ? updatePositionWithPostponed2 == updatePositionWithPostponed : i6 == 4 && updatePositionWithPostponed2 == updatePositionWithPostponed + 1) {
                i4++;
            } else {
                UpdateOp obtainUpdateOp = obtainUpdateOp(updateOp.cmd, updatePositionWithPostponed, i4, updateOp.payload);
                dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp, i2);
                recycleUpdateOp(obtainUpdateOp);
                int i7 = i2;
                if (updateOp.cmd == 4) {
                    i7 = i2 + i4;
                }
                i4 = 1;
                i2 = i7;
                updatePositionWithPostponed = updatePositionWithPostponed2;
            }
        }
        Object obj = updateOp.payload;
        recycleUpdateOp(updateOp);
        if (i4 > 0) {
            UpdateOp obtainUpdateOp2 = obtainUpdateOp(updateOp.cmd, updatePositionWithPostponed, i4, obj);
            dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp2, i2);
            recycleUpdateOp(obtainUpdateOp2);
        }
    }

    private void postponeAndUpdateViewHolders(UpdateOp updateOp) {
        this.mPostponedList.add(updateOp);
        int i = updateOp.cmd;
        if (i == 1) {
            this.mCallback.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
        } else if (i == 2) {
            this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(updateOp.positionStart, updateOp.itemCount);
        } else if (i == 4) {
            this.mCallback.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
        } else if (i != 8) {
            throw new IllegalArgumentException("Unknown update op type for " + updateOp);
        } else {
            this.mCallback.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
        }
    }

    private int updatePositionWithPostponed(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int size = this.mPostponedList.size() - 1;
        while (true) {
            i3 = i;
            if (size < 0) {
                break;
            }
            UpdateOp updateOp = this.mPostponedList.get(size);
            if (updateOp.cmd == 8) {
                if (updateOp.positionStart < updateOp.itemCount) {
                    i4 = updateOp.positionStart;
                    i5 = updateOp.itemCount;
                } else {
                    i4 = updateOp.itemCount;
                    i5 = updateOp.positionStart;
                }
                if (i3 < i4 || i3 > i5) {
                    i = i3;
                    if (i3 < updateOp.positionStart) {
                        if (i2 == 1) {
                            updateOp.positionStart++;
                            updateOp.itemCount++;
                            i = i3;
                        } else {
                            i = i3;
                            if (i2 == 2) {
                                updateOp.positionStart--;
                                updateOp.itemCount--;
                                i = i3;
                            }
                        }
                    }
                } else if (i4 == updateOp.positionStart) {
                    if (i2 == 1) {
                        updateOp.itemCount++;
                    } else if (i2 == 2) {
                        updateOp.itemCount--;
                    }
                    i = i3 + 1;
                } else {
                    if (i2 == 1) {
                        updateOp.positionStart++;
                    } else if (i2 == 2) {
                        updateOp.positionStart--;
                    }
                    i = i3 - 1;
                }
            } else if (updateOp.positionStart <= i3) {
                if (updateOp.cmd == 1) {
                    i = i3 - updateOp.itemCount;
                } else {
                    i = i3;
                    if (updateOp.cmd == 2) {
                        i = i3 + updateOp.itemCount;
                    }
                }
            } else if (i2 == 1) {
                updateOp.positionStart++;
                i = i3;
            } else {
                i = i3;
                if (i2 == 2) {
                    updateOp.positionStart--;
                    i = i3;
                }
            }
            size--;
        }
        for (int size2 = this.mPostponedList.size() - 1; size2 >= 0; size2--) {
            UpdateOp updateOp2 = this.mPostponedList.get(size2);
            if (updateOp2.cmd == 8) {
                if (updateOp2.itemCount == updateOp2.positionStart || updateOp2.itemCount < 0) {
                    this.mPostponedList.remove(size2);
                    recycleUpdateOp(updateOp2);
                }
            } else if (updateOp2.itemCount <= 0) {
                this.mPostponedList.remove(size2);
                recycleUpdateOp(updateOp2);
            }
        }
        return i3;
    }

    AdapterHelper addUpdateOp(UpdateOp... updateOpArr) {
        Collections.addAll(this.mPendingUpdates, updateOpArr);
        return this;
    }

    public int applyPendingUpdatesToPosition(int i) {
        int size = this.mPendingUpdates.size();
        int i2 = 0;
        while (true) {
            int i3 = i;
            if (i2 >= size) {
                return i3;
            }
            UpdateOp updateOp = this.mPendingUpdates.get(i2);
            int i4 = updateOp.cmd;
            if (i4 == 1) {
                i = i3;
                if (updateOp.positionStart <= i3) {
                    i = i3 + updateOp.itemCount;
                }
            } else if (i4 == 2) {
                i = i3;
                if (updateOp.positionStart > i3) {
                    continue;
                } else if (updateOp.positionStart + updateOp.itemCount > i3) {
                    return -1;
                } else {
                    i = i3 - updateOp.itemCount;
                }
            } else if (i4 != 8) {
                i = i3;
            } else if (updateOp.positionStart == i3) {
                i = updateOp.itemCount;
            } else {
                int i5 = i3;
                if (updateOp.positionStart < i3) {
                    i5 = i3 - 1;
                }
                i = i5;
                if (updateOp.itemCount <= i5) {
                    i = i5 + 1;
                }
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void consumePostponedUpdates() {
        int size = this.mPostponedList.size();
        for (int i = 0; i < size; i++) {
            this.mCallback.onDispatchSecondPass(this.mPostponedList.get(i));
        }
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int size = this.mPendingUpdates.size();
        for (int i = 0; i < size; i++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i);
            int i2 = updateOp.cmd;
            if (i2 == 1) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
            } else if (i2 == 2) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForRemovingInvisible(updateOp.positionStart, updateOp.itemCount);
            } else if (i2 == 4) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
            } else if (i2 == 8) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.mExistingUpdateTypes = 0;
    }

    void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateOp, int i) {
        this.mCallback.onDispatchFirstPass(updateOp);
        int i2 = updateOp.cmd;
        if (i2 == 2) {
            this.mCallback.offsetPositionsForRemovingInvisible(i, updateOp.itemCount);
        } else if (i2 != 4) {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        } else {
            this.mCallback.markViewHoldersUpdated(i, updateOp.itemCount, updateOp.payload);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int findPositionOffset(int i) {
        return findPositionOffset(i, 0);
    }

    int findPositionOffset(int i, int i2) {
        int size = this.mPostponedList.size();
        int i3 = i2;
        while (true) {
            int i4 = i;
            if (i3 >= size) {
                return i4;
            }
            UpdateOp updateOp = this.mPostponedList.get(i3);
            if (updateOp.cmd != 8) {
                i = i4;
                if (updateOp.positionStart > i4) {
                    continue;
                } else if (updateOp.cmd != 2) {
                    i = i4;
                    if (updateOp.cmd == 1) {
                        i = i4 + updateOp.itemCount;
                    }
                } else if (i4 < updateOp.positionStart + updateOp.itemCount) {
                    return -1;
                } else {
                    i = i4 - updateOp.itemCount;
                }
            } else if (updateOp.positionStart == i4) {
                i = updateOp.itemCount;
            } else {
                int i5 = i4;
                if (updateOp.positionStart < i4) {
                    i5 = i4 - 1;
                }
                i = i5;
                if (updateOp.itemCount <= i5) {
                    i = i5 + 1;
                }
            }
            i3++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasAnyUpdateTypes(int i) {
        return (i & this.mExistingUpdateTypes) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasPendingUpdates() {
        return this.mPendingUpdates.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasUpdates() {
        return (this.mPostponedList.isEmpty() || this.mPendingUpdates.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.OpReorderer.Callback
    public UpdateOp obtainUpdateOp(int i, int i2, int i3, Object obj) {
        UpdateOp updateOp;
        UpdateOp acquire = this.mUpdateOpPool.acquire();
        if (acquire == null) {
            updateOp = new UpdateOp(i, i2, i3, obj);
        } else {
            acquire.cmd = i;
            acquire.positionStart = i2;
            acquire.itemCount = i3;
            acquire.payload = obj;
            updateOp = acquire;
        }
        return updateOp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeChanged(int i, int i2, Object obj) {
        boolean z = false;
        if (i2 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(4, i, i2, obj));
        this.mExistingUpdateTypes |= 4;
        if (this.mPendingUpdates.size() == 1) {
            z = true;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeInserted(int i, int i2) {
        boolean z = false;
        if (i2 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(1, i, i2, null));
        this.mExistingUpdateTypes |= 1;
        if (this.mPendingUpdates.size() == 1) {
            z = true;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeMoved(int i, int i2, int i3) {
        boolean z = false;
        if (i == i2) {
            return false;
        }
        if (i3 == 1) {
            this.mPendingUpdates.add(obtainUpdateOp(8, i, i2, null));
            this.mExistingUpdateTypes |= 8;
            if (this.mPendingUpdates.size() == 1) {
                z = true;
            }
            return z;
        }
        throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeRemoved(int i, int i2) {
        boolean z = false;
        if (i2 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(2, i, i2, null));
        this.mExistingUpdateTypes |= 2;
        if (this.mPendingUpdates.size() == 1) {
            z = true;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void preProcess() {
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        int size = this.mPendingUpdates.size();
        for (int i = 0; i < size; i++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i);
            int i2 = updateOp.cmd;
            if (i2 == 1) {
                applyAdd(updateOp);
            } else if (i2 == 2) {
                applyRemove(updateOp);
            } else if (i2 == 4) {
                applyUpdate(updateOp);
            } else if (i2 == 8) {
                applyMove(updateOp);
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.mPendingUpdates.clear();
    }

    @Override // androidx.recyclerview.widget.OpReorderer.Callback
    public void recycleUpdateOp(UpdateOp updateOp) {
        if (this.mDisableRecycler) {
            return;
        }
        updateOp.payload = null;
        this.mUpdateOpPool.release(updateOp);
    }

    void recycleUpdateOpsAndClearList(List<UpdateOp> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            recycleUpdateOp(list.get(i));
        }
        list.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }
}