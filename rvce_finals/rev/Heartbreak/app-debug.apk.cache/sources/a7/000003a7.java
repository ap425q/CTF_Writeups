package androidx.constraintlayout.core.widgets;

/* loaded from: classes.dex */
public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f46x;

    /* renamed from: y */
    public int f47y;

    public void setBounds(int x, int y, int width, int height) {
        this.f46x = x;
        this.f47y = y;
        this.width = width;
        this.height = height;
    }

    void grow(int w, int h) {
        this.f46x -= w;
        this.f47y -= h;
        this.width += w * 2;
        this.height += h * 2;
    }

    boolean intersects(Rectangle bounds) {
        return this.f46x >= bounds.f46x && this.f46x < bounds.f46x + bounds.width && this.f47y >= bounds.f47y && this.f47y < bounds.f47y + bounds.height;
    }

    public boolean contains(int x, int y) {
        return x >= this.f46x && x < this.f46x + this.width && y >= this.f47y && y < this.f47y + this.height;
    }

    public int getCenterX() {
        return (this.f46x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f47y + this.height) / 2;
    }
}