package com.example.appmahasiswa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<DataMahasiswa> listMahasiswa;
    private Context context;
    private SQLiteHelper helper;
    private TextView tvnama, tvnim, tvjurusan, tvalamat, tvmatkul, tvtelp;
    private LinearLayout linear;
    private ImageView hapus;

    public ListViewAdapter(List<DataMahasiswa> listMahasiswa, Context context) {
        this.listMahasiswa = listMahasiswa;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listMahasiswa.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_item, null);
        tvnama = v.findViewById(R.id.tvnama);
        tvnim = v.findViewById(R.id.tvnim);
        tvjurusan = v.findViewById(R.id.tvjurusan);
        tvalamat = v.findViewById(R.id.tvalamat);
        tvmatkul = v.findViewById(R.id.tvmatkul);
        tvtelp = v.findViewById(R.id.tvtelp);
        linear = v.findViewById(R.id.linear);
        hapus = v.findViewById(R.id.hapus);

        helper = new SQLiteHelper(context);

        tvnama.setText("Nama: "+listMahasiswa.get(position).getNama());
        tvnim.setText("Nim: "+listMahasiswa.get(position).getNim());
        tvjurusan.setText("Jurusan: "+listMahasiswa.get(position).getJurusan());
        tvalamat.setText("Alamat: "+listMahasiswa.get(position).getAlamat());
        tvmatkul.setText(("Mata Kuliah: "+listMahasiswa.get(position).getMatkul()));
        tvtelp.setText("Telepon: "+listMahasiswa.get(position).getTelpon());
        linear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, TambahActivity.class);
                intent.putExtra("id", listMahasiswa.get(position).getId());
                intent.putExtra("nama", listMahasiswa.get(position).getNama());
                intent.putExtra("nim", listMahasiswa.get(position).getNim());
                intent.putExtra("jurusan", listMahasiswa.get(position).getJurusan());
                intent.putExtra("alamat", listMahasiswa.get(position).getAlamat());
                intent.putExtra("matkul",listMahasiswa.get(position).getMatkul());
                intent.putExtra("telepon", listMahasiswa.get(position).getTelpon());
                intent.putExtra("tanda", "Ubah");
                context.startActivity(intent);
                return true;
            }
        });
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                alertDialog.setMessage("dihapus?").setPositiveButton("ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Integer isDelete = helper.deleteData(listMahasiswa.get(position).getId());
                        if(isDelete > 0){
                            Toast.makeText(context,"data berhasil dihapus", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context,"data gagal dihapus", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        return v;
    }
}
