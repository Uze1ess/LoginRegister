package com.example.revesiontest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.revesiontest.Database.DatabaseConnection;
import com.example.revesiontest.Model.User;
import com.example.revesiontest.Repository.UserRepository;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView btnRegister;
    private EditText editUsername, editPassword;
    private UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userRepository = new UserRepository(this);
        btnLogin = findViewById(R.id.btnLogin);
        editUsername = findViewById(R.id.txtUsername);
        editPassword = findViewById(R.id.txtPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchRegister();
            }
        });
    }
    protected void switchRegister(){
        Intent resgister = new Intent(LoginActivity.this,ResgisterActivity.class);
        startActivity(resgister);
    }
    protected void userLogin(){
        try{
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();
            User user = new User(username,password);
            boolean check = userRepository.checkifexist(user);
            if(check){
                Toast.makeText(LoginActivity.this,"Succesfull",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(LoginActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}