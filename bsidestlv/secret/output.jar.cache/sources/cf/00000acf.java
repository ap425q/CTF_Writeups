package com.example.myctf;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: output.jar:com/example/myctf/MainActivity.class */
public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("myctf-native");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean checkSalt(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native String getFlag(String str, String str2);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.GetSecretButton)).setOnClickListener(new View.OnClickListener() { // from class: com.example.myctf.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditText editText = (EditText) MainActivity.this.findViewById(R.id.SaltText);
                EditText editText2 = (EditText) MainActivity.this.findViewById(R.id.PinText);
                String obj = editText.getText().toString();
                String obj2 = editText2.getText().toString();
                boolean checkSalt = MainActivity.this.checkSalt(obj);
                TextView textView = (TextView) MainActivity.this.findViewById(R.id.ResultsText);
                if (!Boolean.valueOf(checkSalt).booleanValue()) {
                    textView.setText("Salt incorrect");
                    return;
                }
                textView.setText("The secret is: " + MainActivity.this.getFlag(obj, obj2));
            }
        });
    }
}