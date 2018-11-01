package com.example.joydeephalder.abhiyantran;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    TextView signView;
    EditText userEmail;
    EditText userPIN;
    FrameLayout login;

    FirebaseDatabase database2;
    DatabaseReference users2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signView = findViewById(R.id.sign);
        signView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });


        userEmail = findViewById(R.id.userEmail);
        userPIN = findViewById(R.id.userPIN);
        login = findViewById(R.id.login);


        database2 = FirebaseDatabase.getInstance();
        users2 = database2.getReference("Users");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String User_Email = userEmail.getText().toString();
                final String User_Pin = userPIN.getText().toString();

                if (User_Email.length() < 10 || User_Pin.length() < 4) {
                    Toast.makeText(getApplicationContext(), "invalid inputs", Toast.LENGTH_SHORT).show();
                } else {
                    users2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String decodedEmail = User_Email.replace('.','%');
                            if (dataSnapshot.hasChild(decodedEmail)) {

                                String database_pin = dataSnapshot.child(decodedEmail).child("pin").getValue().toString();

                                if (database_pin.equals(User_Pin)) {
                                    Toast.makeText(getApplicationContext(), "successfully logged in", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Not registered yet", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }
        });

    }

}
