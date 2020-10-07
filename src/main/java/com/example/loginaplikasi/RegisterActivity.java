package com.example.loginaplikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class RegisterActivity extends AppCompatActivity {
    public static final String FILENAME = "login";
    EditText editUser, editPassword, editEmail, editNamaLengkap, editSekolah, editAlamat;
    Button Simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editUser = findViewById(R.id.userName);
        editPassword = findViewById(R.id.password);
        editEmail = findViewById(R.id.email);
        editNamaLengkap = findViewById(R.id.namaLengkap);
        editSekolah = findViewById(R.id.sekolah);
        editAlamat = findViewById(R.id.alamat);
        Simpan = findViewById(R.id.btnSimpan);

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidation()){
                    simpanFileData();
                }else {
                    Toast.makeText(RegisterActivity.this, "Mohon lengkapi seluruh data", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    boolean isValidation(){

        if (editUser.getText().toString().equals("") ||
                editPassword.getText().toString().equals("") ||
                editEmail.getText().toString().equals("") ||
                editNamaLengkap.getText().toString().equals("") ||
                editSekolah.getText().toString().equals("") ||
                editAlamat.getText().toString().equals("") ) {
            return false;
        }else {
            return true;
        }
    }
    void simpanFileData(){
        String isiFile  = editUser.getText().toString() + ";" +
                editPassword.getText().toString() + ";" +
                editEmail.getText().toString() + ";" +
                editNamaLengkap.getText().toString() + ";" +
                editSekolah.getText().toString() + ";" +
                editAlamat.getText().toString();
        File file = new File(getFilesDir(),editUser.getText().toString());
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
        Toast.makeText(this, "Register Berhasil",Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}