package com.tkj17b.kel12;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity{

    //membuat variable untuk memanggil widget

    Toolbar toolbar;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_nama;
    RadioButton radio_member;
    RadioGroup radiobuttongroup;
    EditText jam_waktu;
    Spinner kece_patan;
    Button btn_hitung;
    Button btn_keluar;
    Spinner kategori;
    double harga;
    double diskon;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mengisi variabel yang sudah dibuat dan diisi oleh id xml

        txt_nama = (EditText) findViewById(R.id.txtnama);
        kece_patan = (Spinner) findViewById(R.id.speed);
        btn_hitung = (Button) findViewById(R.id.btnhitung);
        btn_keluar = (Button) findViewById(R.id.btnkeluar);
        btn_keluar.setOnClickListener(new View.OnClickListener(){
            public  void  onClick(View view){
                showDialog();
            }
        });
        btn_hitung.setOnClickListener(new View.OnClickListener(){
            public  void  onClick(View view){
                logic();
            }
        });


    }

    public void  showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Yakin Mau Keluar?");
        alertDialogBuilder
                .setMessage("Jika Yakin Klik Ya")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void  logic(){

        txt_nama = (EditText) findViewById(R.id.txtnama);
        kategori = (Spinner) findViewById(R.id.speed);
        String kat = kategori.getSelectedItem().toString();
        String nama =  txt_nama.getText().toString();
        jam_waktu = (EditText) findViewById(R.id.jam);
        radiobuttongroup = (RadioGroup) findViewById(R.id.radiomembergroup);

        int memberid = radiobuttongroup.getCheckedRadioButtonId();
        radio_member = (RadioButton) findViewById(memberid);

        String member = radio_member.getText().toString();
        double waktumain = Integer.parseInt(jam_waktu.getText().toString());
        if (member.equals("Member")){
            harga = 5000;
            diskon = 0;
        }else if(member.equals("Non-Member")) {


            if (kat.equals("Super")) {
                harga = 10000;
                if (waktumain > 10) {
                    diskon = 0.07;
                } else {
                    diskon = 0;
                }
            } else if (kat.equals("Middle")) {
                harga = 7500;
                if (waktumain > 8) {
                    diskon = 0.07;
                } else {
                    diskon = 0;
                }
            } else if (kat.equals("Low")) {
                harga = 5000;
                if (waktumain > 5) {
                    diskon = 0.05;
                } else {
                    diskon = 0;
                }
            }
        }
        double totalHarga = waktumain * harga;
        double totalDiskon = totalHarga * diskon;
        double totalAkhir = totalHarga - totalDiskon;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Detail Pembayaran");
        alertDialogBuilder
                .setMessage("Nama  : " + nama + "\n Kategori : " + kat +"\n Status : "+ member +"\n Harga : " + totalAkhir)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)

                .setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }





}

