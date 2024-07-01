package androidx.constraintlayout.solver.widgets;

/* loaded from: classes.dex */
public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f40x;

    /* renamed from: y */
    public int f41y;

    public void setBounds(int x, int y, int width, int height) {
        this.f40x = x;
        this.f41y = y;
        this.width = width;
        this.height = height;
    }

    void grow(int w, int h) {
        this.f40x -= w;
        this.f41y -= h;
        this.width += w * 2;
        this.height += h * 2;
    }

    boolean intersects(Rectangle bounds) {
        int i;
        int i2;
        int i3 = this.f40x;
        int i4 = bounds.f40x;
        return i3 >= i4 && i3 < i4 + bounds.width && (i = this.f41y) >= (i2 = bounds.f41y) && i < i2 + bounds.height;
    }

    public boolean contains(int x, int y) {
        int i;
        int i2 = this.f40x;
        return x >= i2 && x < i2 + this.width && y >= (i = this.f41y) && y < i + this.height;
    }

    public int getCenterX() {
        return (this.f40x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f41y + this.height) / 2;
    }
}