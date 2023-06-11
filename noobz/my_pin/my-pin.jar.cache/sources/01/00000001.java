package defpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/* compiled from: Mypin.java */
/* renamed from: PinButton  reason: default package */
/* loaded from: my-pin.jar:PinButton.class */
class PinButton extends JButton implements ActionListener {
    Mypin app;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PinButton(Mypin mypin, String str, int i, int i2, int i3, int i4) {
        super(str);
        this.app = mypin;
        addActionListener(this);
        setBounds(i, i2, i3, i4);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Secret.getInstance().process(getText().charAt(0));
        this.app.updateOutput();
    }
}