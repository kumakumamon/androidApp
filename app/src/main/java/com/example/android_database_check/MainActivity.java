package com.example.android_database_check;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.sendButton);
        button.setOnClickListener(new MyOnClickListener());
    }


    private class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            reference.child("message").setValue("こんにちは,CTC", null);
            reference.child("aaa").setValue("120", null);
            reference.child("bbb").setValue("100", null);
            reference.child("ccc").setValue("300", null);
            reference.child("ddd").setValue("400", null);
            reference.child("message").addValueEventListener(listener);
            reference.child("aaa").addValueEventListener(listener);
            reference.child("bbb").addValueEventListener(listener);
            reference.child("ccc").addValueEventListener(listener);
        }

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    System.out.println("データを受信しました。" + snapshot.getKey() + "=" + snapshot.getValue());
                    String message = (String)snapshot.getValue();
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(message);

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("データ受信がキャンセルされました。" + error.toString());
            }
        };
    }
}