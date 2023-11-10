package com.example.de_013;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.de_013.models.Phong;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ArrayList<Phong> dsPhong;
    ArrayAdapter<Phong> adapter;
    ListView lvDanhSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fakeData();
        handleEvent();
    }

    public void init() {
        lvDanhSach = findViewById(R.id.lvDanhSach);
        dsPhong = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dsPhong);
        lvDanhSach.setAdapter(adapter);
    }

    public void fakeData() {
        Phong p1 = new Phong("P001", "Trần Văn Sĩ", new Date(), 3);
        Phong p2 = new Phong("P002", "Võ Quốc Thắng", new Date(), 5);
        Phong p3 = new Phong("P003", "Trinh Trinh", new Date(), 10);
        Phong p4 = new Phong("P004", "Biện Nguyễn Tuyết Mai", new Date(), 2);
        Phong p5 = new Phong("P005", "Thảo Nobita", new Date(), 7);
        dsPhong.add(p1);
        dsPhong.add(p2);
        dsPhong.add(p3);
        dsPhong.add(p4);
        dsPhong.add(p5);

    }

    public void handleEvent() {
        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xóa sách");
                builder.setMessage("Bạn có chắc chắn?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Phong phong = dsPhong.get(position);
                        dsPhong.remove(phong);
                        adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
                return true;
            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                ArrayList<Phong> dsp = dsPhong;
                intent.putParcelableArrayListExtra("DSP", dsp);
                intent.putExtra("INDEX", position);
                startActivityForResult(intent,99);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==99 && resultCode==33) {
            ArrayList<Phong> dsPhongUpdate = data.getParcelableArrayListExtra("DSP");
            if(dsPhongUpdate!=null) {
                dsPhong.clear();
                dsPhong.addAll(dsPhongUpdate);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
