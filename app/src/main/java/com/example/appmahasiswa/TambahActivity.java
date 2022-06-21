package com.example.appmahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {
    private EditText etnama, etnim, etjurusan, etalamat, etmatkul, ettelp;
    private Button btntambah;
    private SQLiteHelper helper;
    private String pilih = "Tambah";
    private String id, nama, nim, jurusan, alamat, matkul, telepon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etnama = findViewById(R.id.etnama);
        etnim = findViewById(R.id.etnim);
        etjurusan = findViewById(R.id.etjurusan);
        etalamat = findViewById(R.id.etalamat);
        etmatkul = findViewById(R.id.etmatkul);
        ettelp = findViewById(R.id.ettelp);
        btntambah = findViewById(R.id.btntambah);

        helper = new SQLiteHelper(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            getSupportActionBar().setTitle("Ubah Data");
            id = bundle.getString("id");
            nama = bundle.getString("nama");
            nim = bundle.getString("nim");
            jurusan = bundle.getString("jurusan");
            alamat = bundle.getString("alamat");
            matkul = bundle.getString("mata kuliah");
            telepon = bundle.getString(("telepon"));
            pilih = bundle.getString("tanda");

            etnama.setText(nama);
            etnim.setText(nim);
            etjurusan.setText(jurusan);
            etalamat.setText(alamat);
            etmatkul.setText(matkul);
            ettelp.setText(telepon);
        } else {
            getSupportActionBar().setTitle("tambah data");
        }

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etnama.getText().toString();
                String nim = etnim.getText().toString();
                String jurusan = etjurusan.getText().toString();
                String alamat = etalamat.getText().toString();
                String matkul = etmatkul.getText().toString();
                String telpon = ettelp.getText().toString();

                if (TextUtils.isEmpty(nama)){
                    etnama.setError("Data tidak boleh kosong");
                    etnama.requestFocus();
                } else if(TextUtils.isEmpty(nim)){
                    etnim.setError("Data tidak boleh kosong");
                    etnim.requestFocus();
                } else if(TextUtils.isEmpty(jurusan)){
                    etjurusan.setError("Data tidak boleh kosong");
                    etjurusan.requestFocus();
                } else if(TextUtils.isEmpty(alamat)){
                    etalamat.setError("Data tidak boleh kosong");
                    etalamat.requestFocus();
                } else if(TextUtils.isEmpty(matkul)) {
                    etmatkul.setError("Data tidak boleh kosong");
                    etmatkul.requestFocus();
                } else if(TextUtils.isEmpty(telpon)) {
                    ettelp.setError("Data tidak boleh kosong");
                    ettelp.requestFocus();
                }else {
                    if (pilih.equals("Tambah")){
                        boolean isInsert = helper.insertData(nama, nim, jurusan, alamat, matkul, telepon);
                        if (isInsert){
                            Toast.makeText(TambahActivity.this, "data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            kosong();
                            startActivity(new Intent(TambahActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            Toast.makeText(TambahActivity.this, "data gagal disimpan", Toast.LENGTH_SHORT).show();
                            kosong();
                            startActivity(new Intent(TambahActivity.this, HomeActivity.class));
                            finish();
                        }
                    }else{
                        boolean isUpdate = helper.updateData(
                                id,
                                nama,
                                nim,
                                jurusan,
                                alamat,
                                matkul,
                                telepon
                        );
                        if (isUpdate){
                            Toast.makeText(TambahActivity.this, "data berhasil diubah", Toast.LENGTH_SHORT).show();
                            kosong();
                            startActivity(new Intent(TambahActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            Toast.makeText(TambahActivity.this, "data gagal diubah", Toast.LENGTH_SHORT).show();
                            kosong();
                            startActivity(new Intent(TambahActivity.this, HomeActivity.class));
                            finish();
                        }

                    }
                }
            }
        });
    }
    private void kosong(){
        etnama.setText(null);
        etnim.setText(null);
        etjurusan.setText(null);
        etalamat.setText(null);
        etmatkul.setText(null);
        ettelp.setText(null);
    }
}