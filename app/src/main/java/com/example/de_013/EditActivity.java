package com.example.de_013;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.de_013.models.Phong;
import com.example.de_013.utils.DateUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class EditActivity extends AppCompatActivity {
    EditText edtMa, edtNguoidat, edtNgaydat, edtSodem;
    Button btnSua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
        handle();
    }

    public void init() {
        edtMa = findViewById(R.id.edtMa);
        edtNguoidat = findViewById(R.id.edtNguoidat);
        edtNgaydat = findViewById(R.id.edtNgaydat);
        edtSodem = findViewById(R.id.edtSodem);
        btnSua = findViewById(R.id.btnSua);
    }

    public void handle() {
        Intent intent = getIntent();
        ArrayList<Phong> dsPhong = intent.getParcelableArrayListExtra("DSP");
        int index = (int) intent.getSerializableExtra("INDEX");
        Phong phong = dsPhong.get(index);
        edtMa.setText(phong.getMa());
        edtNguoidat.setText(phong.getNguoidat());
        edtNgaydat.setText(DateUtil.format(phong.getNgaydat()) + "");
        edtSodem.setText(phong.getSodem() + "");

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = edtMa.getText().toString();
                String nguoidat = edtNguoidat.getText().toString();
                String dateStr = edtNgaydat.getText().toString();
                int sodem = Integer.parseInt(edtSodem.getText().toString());
                Date ngaydat = null;
                try {
                    ngaydat = DateUtil.parseStringToDate(dateStr);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                phong.setMa(ma);
                phong.setNguoidat(nguoidat);
                phong.setNgaydat(ngaydat);
                phong.setSodem(sodem);
                intent.putParcelableArrayListExtra("DSP", dsPhong);
                setResult(33,intent);
                finish();
            }
        });
    }
}