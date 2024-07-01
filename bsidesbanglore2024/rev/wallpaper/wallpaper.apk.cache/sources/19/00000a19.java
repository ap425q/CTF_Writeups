package com.example.wallpaper;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainActivity.kt */
@Metadata(m20d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u0016*\u00020\u00042\u0006\u0010\u0017\u001a\u00020\nR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, m19d2 = {"Lcom/example/wallpaper/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "pics", "", "getPics", "()[I", "setPics", "([I)V", "rotten", "", "getRotten", "()I", "nextImage", "", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "prevImage", "rotate", "", "distance", "app_debug"}, m18k = 1, m17mv = {1, 5, 1}, m15xi = 48)
/* loaded from: classes3.dex */
public final class MainActivity extends AppCompatActivity {
    private int[] pics = {com.example.froyo.R.drawable.duck, com.example.froyo.R.drawable.nature1, com.example.froyo.R.drawable.future, com.example.froyo.R.drawable.nature2, com.example.froyo.R.drawable.stars};
    private final int rotten = com.example.froyo.R.drawable.wallpaper;

    public final int[] getPics() {
        return this.pics;
    }

    public final void setPics(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.pics = iArr;
    }

    public final List<Integer> rotate(int[] $this$rotate, int distance) {
        Intrinsics.checkNotNullParameter($this$rotate, "<this>");
        List it = ArraysKt.toList($this$rotate);
        Collections.rotate(it, distance);
        return it;
    }

    public final int getRotten() {
        return this.rotten;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.froyo.R.layout.activity_main);
        View findViewById = findViewById(com.example.froyo.R.id.imageView2);
        if (findViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        ImageView imageView = (ImageView) findViewById;
        imageView.setImageResource(com.example.froyo.R.drawable.duck);
    }

    public final void nextImage(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        View findViewById = findViewById(com.example.froyo.R.id.imageView2);
        if (findViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        ImageView imageView = (ImageView) findViewById;
        List tmp = rotate(this.pics, 1);
        this.pics = CollectionsKt.toIntArray(tmp);
        imageView.setImageResource(tmp.get(0).intValue());
    }

    public final void prevImage(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        View findViewById = findViewById(com.example.froyo.R.id.imageView2);
        if (findViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        ImageView imageView = (ImageView) findViewById;
        List tmp = rotate(this.pics, -1);
        this.pics = CollectionsKt.toIntArray(tmp);
        imageView.setImageResource(tmp.get(0).intValue());
    }
}