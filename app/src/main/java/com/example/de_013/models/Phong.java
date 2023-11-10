package com.example.de_013.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.de_013.utils.DateUtil;

import java.util.Date;

public class Phong implements Parcelable {
    private String ma;
    private String nguoidat;
    private Date ngaydat;
    private int sodem;

    public Phong(String ma, String nguoidat, Date ngaydat, int sodem) {
        this.ma = ma;
        this.nguoidat = nguoidat;
        this.ngaydat = ngaydat;
        this.sodem = sodem;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getNguoidat() {
        return nguoidat;
    }

    public void setNguoidat(String nguoidat) {
        this.nguoidat = nguoidat;
    }

    public Date getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(Date ngaydat) {
        this.ngaydat = ngaydat;
    }

    public int getSodem() {
        return sodem;
    }

    public void setSodem(int sodem) {
        this.sodem = sodem;
    }

    protected Phong(Parcel in) {
        ma = in.readString();
        nguoidat = in.readString();
        ngaydat = new Date(in.readLong());
        sodem = in.readInt();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ma);
        dest.writeString(nguoidat);
        dest.writeLong(ngaydat.getTime());
        dest.writeInt(sodem);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Phong> CREATOR = new Creator<Phong>() {
        @Override
        public Phong createFromParcel(Parcel in) {
            return new Phong(in);
        }

        @Override
        public Phong[] newArray(int size) {
            return new Phong[size];
        }
    };

    @Override
    public String toString() {
        return "Mã đặt: " + ma + "\n"
                + "Người đặt: " + nguoidat + "\n"
                + "Ngày đặt: " + DateUtil.format(ngaydat) + "\n"
                + "Số đếm: " + sodem;
    }
}
