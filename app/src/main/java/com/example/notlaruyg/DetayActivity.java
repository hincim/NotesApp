package com.example.notlaruyg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.notlaruyg.databinding.ActivityDetayBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DetayActivity extends AppCompatActivity {
    private ActivityDetayBinding t;
    private Notlar not;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        t = DataBindingUtil.setContentView(this,R.layout.activity_detay);

        t.toolbar3.setTitle("Not Detay");
        setSupportActionBar(t.toolbar3);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notlar");

        not = (Notlar) getIntent().getSerializableExtra("nesne");

        t.editTextDers.setText(not.getDers_adi());
        t.editTextNot1.setText(String.valueOf(not.getNot1()));
        t.editTextNot2.setText(String.valueOf(not.getNot2()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sil:
                Snackbar.make(t.toolbar3,"Silinsin mi?",Snackbar.LENGTH_SHORT).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        myRef.child(not.getNot_id()).removeValue();

                        startActivity(new Intent(DetayActivity.this,MainActivity.class));
                        finish();
                    }
                })
            .show();
                return true;
            case R.id.action_duzenle:
                String ders_adi = t.editTextDers.getText().toString().trim();
                String not1 = t.editTextNot1.getText().toString().trim();
                String not2 = t.editTextNot2.getText().toString().trim();

                if (TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(t.toolbar3,"Ders adı giriniz",Snackbar.LENGTH_SHORT).show();
                    return false;
                }

                if (TextUtils.isEmpty(not1)){
                    Snackbar.make(t.toolbar3,"Ders adı giriniz",Snackbar.LENGTH_SHORT).show();

                    return false;
                }

                if (TextUtils.isEmpty(not2)){
                    Snackbar.make(t.toolbar3,"Ders adı giriniz",Snackbar.LENGTH_SHORT).show();

                    return false;
                }

                Map<String,Object> bilgiler = new HashMap<>();

                bilgiler.put("ders_adi",ders_adi);
                bilgiler.put("not1",Integer.parseInt(not1));
                bilgiler.put("not2",Integer.parseInt(not2));

                myRef.child(not.getNot_id()).updateChildren(bilgiler);

                startActivity(new Intent(DetayActivity.this,MainActivity.class));
                finish();
                return true;
            default:
                return false;
        }
    }
}