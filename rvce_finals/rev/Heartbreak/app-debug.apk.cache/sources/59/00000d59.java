package com.example.bombastic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.location.LocationRequestCompat;
import com.example.bombastic.databinding.ActivityMainBinding;

/* loaded from: classes3.dex */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public String pop = Encrypt();
    private int Wnsdjkywuedc = 0;
    public String encryptedText = this.pop;
    public String key = "fakekey1234qwert6";

    public native String Encrypt();

    public native String hello();

    public native String stringFromJNI();

    static /* synthetic */ int access$008(MainActivity x0) {
        int i = x0.Wnsdjkywuedc;
        x0.Wnsdjkywuedc = i + 1;
        return i;
    }

    static {
        System.loadLibrary("bombastic");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String IreallyLovedHerGuys13(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((((ch - base) + 13) % 26) + base);
            }
            result.append(ch);
        }
        return result.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String SheWasMyCrushX(String input) {
        char[] inputChars = input.toCharArray();
        for (int i = 0; i < inputChars.length; i++) {
            inputChars[i] = (char) (inputChars[i] ^ '-');
        }
        return new String(inputChars);
    }

    public String BorkenHeart(int number) {
        int[] numbers = {49, 52, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 55, LocationRequestCompat.QUALITY_LOW_POWER, 51, 107, 51, 121, 98, 114, 48, 55, LocationRequestCompat.QUALITY_LOW_POWER, 51, 114};
        StringBuilder sb = new StringBuilder();
        for (int num : numbers) {
            char letter = (char) num;
            sb.append(letter);
        }
        return IreallyLovedHerGuys13(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        this.binding.textView.setOnClickListener(new View.OnClickListener() { // from class: com.example.bombastic.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MainActivity.access$008(MainActivity.this);
                if (MainActivity.this.Wnsdjkywuedc == 40) {
                    try {
                        String AndSheLeftMe = MainActivity.this.IreallyLovedHerGuys13(AESUtils.decrypt(MainActivity.this.encryptedText, MainActivity.this.key));
                        String AndIWasLeftAlone = MainActivity.this.SheWasMyCrushX(AndSheLeftMe);
                        Toast.makeText(MainActivity.this, "Decryption Sucess", 0).show();
                        Log.d("This was My Lovely Story", AndIWasLeftAlone);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Decryption failed Try Harder..! ", 0).show();
                    }
                    MainActivity.this.Wnsdjkywuedc = 0;
                }
            }
        });
    }
}