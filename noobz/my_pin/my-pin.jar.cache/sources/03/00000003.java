package defpackage;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Mypin.java */
/* renamed from: Secret  reason: default package */
/* loaded from: my-pin.jar:Secret.class */
public class Secret {
    private static Secret instance = new Secret();
    private int cnt = 1;
    private int[] mydata = {0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0};
    private int[] box = new int[this.mydata.length / 9];

    private Secret() {
    }

    public static Secret getInstance() {
        return instance;
    }

    public void resetInstance() {
        instance = new Secret();
    }

    public void process(char c) {
        if (this.cnt > 9) {
            return;
        }
        int length = this.mydata.length / 9;
        for (int i = 1; i <= length; i++) {
            int i2 = (9 * i) - this.cnt;
            int i3 = this.box[i - 1] + this.mydata[i2] + (c - '0');
            this.mydata[i2] = i3 % 2;
            if (i3 >= 2) {
                this.box[i - 1] = 1;
            } else {
                this.box[i - 1] = 0;
            }
        }
        this.cnt++;
    }

    private String misteri(int i) {
        String str = "";
        int i2 = 0;
        int i3 = 1;
        while (i > 0) {
            i2 |= (i & 1) << ((i3 % 8) - 1);
            i >>= 1;
            if (i3 % 8 == 0) {
                if (32 <= i2 && i2 < 128) {
                    str = ((char) i2) + str;
                }
                i2 = 0;
            }
            i3++;
        }
        return ((char) i2) + str;
    }

    public String getData() {
        int length = this.mydata.length / 9;
        String str = "";
        int i = 5;
        int i2 = 0;
        for (int i3 = 1; i3 <= length; i3++) {
            int i4 = 0;
            int i5 = 1;
            for (int i6 = 1; i6 <= 8; i6++) {
                i4 += this.mydata[(9 * i3) - i6] * i5;
                i5 <<= 1;
            }
            i--;
            i2 = (int) (i2 + ((i4 - 33) * Math.pow(85.0d, i)));
            if (i == 0) {
                str = str + misteri(i2);
                i2 = 0;
                i = 5;
            }
        }
        while (i > 0) {
            i--;
            i2 = (int) (i2 + (84.0d * Math.pow(85.0d, i)));
        }
        return str + misteri(i2);
    }
}