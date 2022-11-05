package com.example.notlaruyg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.notlaruyg.databinding.ActivityNotKayitBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NotKayitActivity extends AppCompatActivity {
    private ActivityNotKayitBinding t;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        t = DataBindingUtil.setContentView(this,R.layout.activity_not_kayit);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notlar");

        t.toolbar2.setTitle("Not Kayıt");
        setSupportActionBar(t.toolbar2);



        t.buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ders_adi = t.editTextDers.getText().toString().trim();
                String not1 = t.editTextNot1.getText().toString().trim();
                String not2 = t.editTextNot2.getText().toString().trim();

                if (TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(t.toolbar2,"Ders adı giriniz",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(not1)){
                    Snackbar.make(t.toolbar2,"Ders adı giriniz",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(not2)){
                    Snackbar.make(t.toolbar2,"Ders adı giriniz",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                Notlar not = new Notlar("",ders_adi,Integer.parseInt(not1),Integer.parseInt(not2));

                myRef.push().setValue(not);

                startActivity(new Intent(NotKayitActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}