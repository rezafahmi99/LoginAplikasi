package com.example.loginaplikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    public static final String FILENAME = "login";

    EditText editUsername, editPassword;
    Button Login, Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUsername = findViewById(R.id.inputUser);
        editPassword = findViewById(R.id.inputpswd);
        Login = findViewById(R.id.btnLogin);
        Register = findViewById(R.id.btnRegister);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
        void simpanFileLogin(){
        String isiFile = editUsername.getText().toString() + ";" + editPassword.getText().toString();
            File file = new File(getFilesDir(), FILENAME);

            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file, false);
                outputStream.write(isiFile.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Login Berhasil" , Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        void login(){
        File sdcard = getFilesDir();
        File file = new File(sdcard,editUsername.getText().toString());
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error"+e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");

            if (dataUser[1].equals(editPassword.getText().toString())){
                simpanFileLogin();
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Password Tidak sesuai", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }
}