package defpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/* compiled from: Mypin.java */
/* renamed from: ResetButton  reason: default package */
/* loaded from: my-pin.jar:ResetButton.class */
class ResetButton extends JButton implements ActionListener {
    private Mypin app;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResetButton(Mypin mypin, int i, int i2, int i3, int i4) {
        super("Reset");
        this.app = mypin;
        addActionListener(this);
        setBounds(i, i2, i3, i4);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Secret.getInstance().resetInstance();
        this.app.clearOutput();
    }
}