package p000;

import java.util.Map;
import java.util.Scanner;

/* renamed from: Main */
/* loaded from: bankchall.jar:Main.class */
public class Main {

    /* renamed from: a */
    private static int f0a;

    /* renamed from: b */
    private static long[] f1b = new long[13];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m3a(java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.Main.m3a(java.lang.String):java.lang.String");
    }

    public static void main(String[] strArr) {
        System.out.println(m2b("챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊"));
        System.out.println(m2b("찠찒찛찔찘찚찒챗찃찘챗찣찘찃찖찛찛찎찤찒찔찂찅찒찵찖찙찜\ued55"));
        System.out.println(m2b("챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊챊"));
        System.out.println();
        System.exit(m4a());
    }

    /* renamed from: a */
    private static int m4a() {
        String nextLine;
        Scanner scanner = new Scanner(System.in);
        Map of = Map.of(m2b("찂찄찒찅"), new C0000a(m2b("ꙅꌕנּ뱜鰀\uf14a"), 10.0f), m2b("찖찓찚찞찙"), new C0000a(m2b("렒\ue311蘐\uf416鱇騢鼡謴꼾︻ꁏ꤅뤃ꔍ먅ꈽ"), 100000.0f));
        while (true) {
            System.out.println(m2b("찧찛찒찖찄찒챗찒찙찃찒찅챗찎찘찂찅챗찂찄찒찅찙찖찚찒챍"));
            nextLine = scanner.nextLine();
            if (of.containsKey(nextLine) && (((((int) f1b[5]) ^ 1376134906) * ((f0a + (((int) f1b[8]) ^ 935365214)) * (f0a + (((int) f1b[8]) ^ 935365214)))) - (((int) f1b[0]) ^ 2125394058)) - f0a != 0) {
                break;
            }
            do {
                System.out.println(m2b("찢찄찒찅챗찙찘찃챗찑찘찂찙찓"));
            } while ((f0a + (((int) f1b[0]) ^ 2125394058)) % (((int) f1b[9]) ^ 2029694983) == 0);
        }
        while (true) {
            System.out.println(m2b("찧찛찒찖찄찒챗찒찙찃찒찅챗찎찘찂찅챗찇찖찄찄찀찘찅찓챍"));
            if (((C0000a) of.get(nextLine)).m0a(scanner.nextLine())) {
                if ((f0a + (((int) f1b[0]) ^ 2125394058)) % (((int) f1b[9]) ^ 2029694983) == 0) {
                }
                System.out.println("Welcome back " + nextLine + "! your balance is " + ((C0000a) of.get(nextLine)).m1a());
                return ((int) f1b[1]) ^ 316324253;
            }
            do {
                System.out.println(m2b("찾찙찔찘찅찅찒찔찃챗찇찖찄찄찀찘찅찓챖"));
            } while ((f0a + (((int) f1b[0]) ^ 2125394058)) % (((int) f1b[9]) ^ 2029694983) == 0);
        }
    }

    /* renamed from: b */
    public static String m2b(String str) {
        StringBuilder sb = new StringBuilder();
        int i = ((int) f1b[1]) ^ 316324253;
        while (i < str.length()) {
            sb.append((char) (str.charAt(i) ^ (((int) f1b[10]) ^ 538455364)));
            do {
                i++;
                f0a = ((int) f1b[11]) ^ 1099949748;
            } while ((((f0a * f0a) + f0a) + (((int) f1b[5]) ^ 1376134906)) % (((int) f1b[6]) ^ 1536615318) == 0);
        }
        return sb.toString();
    }

    static {
        f1b[0] = 2125394059;
        f1b[1] = 316324253;
        f1b[2] = 322717996;
        f1b[3] = 1817181343;
        f1b[4] = 1916880576;
        f1b[5] = 1376134909;
        f1b[6] = 1536615367;
        f1b[7] = 969122838;
        f1b[8] = 935365158;
        f1b[9] = 2029694981;
        f1b[10] = 538501427;
        f1b[11] = 1099949760;
        f1b[12] = 977807481;
        f0a = ((int) f1b[12]) ^ 1155979507;
    }
}