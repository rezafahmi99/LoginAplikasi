package com.example.loginaplikasi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    public static final String FILENAME = "login";
    EditText editUser, editPassword, editEmail, editNamaLengkap, editSekolah, editAlamat;
    Button Simpan;
    TextView TextViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextViewPassword = findViewById(R.id.textViewPassword);
        editUser = findViewById(R.id.userName);
        editPassword = findViewById(R.id.password);
        editEmail = findViewById(R.id.email);
        editNamaLengkap = findViewById(R.id.namaLengkap);
        editSekolah = findViewById(R.id.sekolah);
        editAlamat = findViewById(R.id.alamat);
        Simpan = findViewById(R.id.btnSimpan);

        Simpan.setVisibility(View.GONE);
        editUser.setEnabled(false);
        editPassword.setVisibility(View.GONE);
        TextViewPassword.setVisibility(View.GONE);
        editEmail.setEnabled(false);
        editSekolah.setEnabled(false);
        editAlamat.setEnabled(false);
        bacaFileLogin();
    }
    void bacaFileLogin(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
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
                System.out.println("Error" +e.getMessage());
            }
            String data= text.toString();
            String[] dataUser = data.split(";");
            bacaDataUser(dataUser[0]);
        }
    }
    void bacaDataUser(String fileName){
        File sdcard = getFilesDir();
        File file = new File(sdcard,fileName);
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
                System.out.println("Error" +e.getMessage());
            }
            String data =text.toString();
            String[] dataUser = data.split(";");

            editUser.setText(dataUser[0]);
            editEmail.setText(dataUser[2]);
            editNamaLengkap.setText(dataUser[3]);
            editSekolah.setText(dataUser[4]);
            editAlamat.setText(dataUser[5]);
        }else {
            Toast.makeText(this, "User Tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                tampilkanDialogKonfirmasiLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()){
            file.delete();
        }
    }
    void tampilkanDialogKonfirmasiLogout(){
        new AlertDialog.Builder(this).setTitle("Logout")
                .setMessage("Apakah anda yakin ingin Logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        hapusFile();
                        Intent intent = new Intent(MainActivity2.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

}