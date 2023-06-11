package defpackage;

import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JLabel;

/* renamed from: Mypin  reason: default package */
/* loaded from: my-pin.jar:Mypin.class */
public class Mypin {
    private JFrame frame = new JFrame("Guess the pin");
    private JLabel output = new JLabel();

    Mypin() {
        this.output.setBounds(20, 250, 360, 20);
        JLabel jLabel = new JLabel("Your data:");
        jLabel.setBounds(20, 235, 100, 20);
        this.frame.add(jLabel);
        this.frame.add(this.output);
        this.frame.add(new PinButton(this, "0", 43, 10, 150, 150));
        this.frame.add(new PinButton(this, "1", 198, 10, 150, 150));
        this.frame.add(new ResetButton(this, 43, 165, 305, 50));
        this.frame.setSize(400, 350);
        this.frame.setLayout((LayoutManager) null);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(3);
    }

    public static void main(String[] strArr) {
        new Mypin();
    }

    public void clearOutput() {
        this.output.setText("");
    }

    public void updateOutput() {
        this.output.setText(Secret.getInstance().getData());
    }
}