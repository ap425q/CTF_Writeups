package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes.dex */
public class Schlick extends Easing {
    private static final boolean DEBUG = false;
    double eps;

    /* renamed from: mS */
    double f35mS;

    /* renamed from: mT */
    double f36mT;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Schlick(String configString) {
        this.str = configString;
        int start = configString.indexOf(40);
        int off1 = configString.indexOf(44, start);
        this.f35mS = Double.parseDouble(configString.substring(start + 1, off1).trim());
        int off2 = configString.indexOf(44, off1 + 1);
        this.f36mT = Double.parseDouble(configString.substring(off1 + 1, off2).trim());
    }

    private double func(double x) {
        if (x < this.f36mT) {
            return (this.f36mT * x) / ((this.f35mS * (this.f36mT - x)) + x);
        }
        return ((1.0d - this.f36mT) * (x - 1.0d)) / ((1.0d - x) - (this.f35mS * (this.f36mT - x)));
    }

    private double dfunc(double x) {
        if (x < this.f36mT) {
            return ((this.f35mS * this.f36mT) * this.f36mT) / (((this.f35mS * (this.f36mT - x)) + x) * ((this.f35mS * (this.f36mT - x)) + x));
        }
        return ((this.f35mS * (this.f36mT - 1.0d)) * (this.f36mT - 1.0d)) / (((((-this.f35mS) * (this.f36mT - x)) - x) + 1.0d) * ((((-this.f35mS) * (this.f36mT - x)) - x) + 1.0d));
    }

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public double getDiff(double x) {
        return dfunc(x);
    }

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public double get(double x) {
        return func(x);
    }
}