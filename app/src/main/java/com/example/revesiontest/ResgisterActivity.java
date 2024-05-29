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

import com.example.revesiontest.Model.User;
import com.example.revesiontest.Repository.UserRepository;

public class ResgisterActivity extends AppCompatActivity {
    private TextView btnLogin;
    private EditText editUsername, editPassword, editConfirmPassword;
    private Button btnRegister;
    private UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resgister);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        editUsername = findViewById(R.id.txtUsername);
        editPassword = findViewById(R.id.txtPassword);
        editConfirmPassword = findViewById(R.id.txtPasswordRepeat);

        userRepository = new UserRepository(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swicthLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }
        });

    }
    protected void swicthLogin(){
        Intent login = new Intent(ResgisterActivity.this,LoginActivity.class);
        startActivity(login);
    }
    protected void userRegister(){
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        String passwordConfirm = editConfirmPassword.getText().toString();
        User user = new User(username,password);
        boolean check = userRepository.checkifexist(user);
        if(!check){
            if(password.equals(passwordConfirm)){
                userRepository.addUser(user);
            }
            else{
                Toast.makeText(this,"Confirm password again",Toast.LENGTH_SHORT).show();
            }
        }
    }
}