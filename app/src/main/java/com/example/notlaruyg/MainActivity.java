package com.example.notlaruyg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notlaruyg.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding t;
    private NotlarAdapter adapter;
    private ArrayList<Notlar> notlarArrayList;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        t = DataBindingUtil.setContentView(this,R.layout.activity_main);

        t.toolbar.setTitle("Not Uygulaması");
        setSupportActionBar(t.toolbar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notlar");


        t.rv.setHasFixedSize(true);
        t.rv.setLayoutManager(new LinearLayoutManager(this));

        notlarArrayList = new ArrayList<>();

        adapter = new NotlarAdapter(this,notlarArrayList);

        t.rv.setAdapter(adapter);

        tumNotlar();

        t.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NotKayitActivity.class));
            }
        });
    }

    public void tumNotlar(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                notlarArrayList.clear();

                double toplam = 0.0;

                for (DataSnapshot d:snapshot.getChildren()){

                    Notlar not = d.getValue(Notlar.class);
                    not.setNot_id(d.getKey());

                    notlarArrayList.add(not);

                    toplam += (not.getNot1()+not.getNot2())/2;
                }
                adapter.notifyDataSetChanged();

                t.toolbar.setSubtitle("Ortalama : "+toplam / notlarArrayList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}