package com.example.joydeephalder.abhiyantran;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.joydeephalder.abhiyantran.Modal.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users;
    private EditText editName;
    private EditText editEmail;
    private EditText editMobile;
    private EditText editPIN;
    private FrameLayout sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");


        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editMobile = findViewById(R.id.editMobile);
        editPIN = findViewById(R.id.editPIN);
        sign_up = findViewById(R.id.sign_up);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final User user = new User(
                        editName.getText().toString(),
                        editEmail.getText().toString(),
                        editMobile.getText().toString(), editPIN.getText().toString()
                );

                if (user.getMobile().length() < 10 || user.getEmail().length() < 10
                        || user.getpin().length() < 4) {
                    Toast.makeText(getApplicationContext(), "Not valid inputs", Toast.LENGTH_SHORT).show();

                } else {

                    users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String encodedEmail = user.getEmail().replace('.','%');

                            if (dataSnapshot.hasChild(encodedEmail)) {
                                Toast.makeText(getApplicationContext(), "Already registered", Toast.LENGTH_SHORT).show();
                            } else {
                                users.child(encodedEmail).setValue(user);
                                Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
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