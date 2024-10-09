package com.example.braniumacadamy;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.frag_update_avatar);


//        btnLogin = findViewById(R.id.btn_login);


//        btnLogin.setOnClickListener(v -> {
//            Intent intent = new Intent(this, VerificationActivity.class);
//            startActivity(intent);
//        });


    }
}