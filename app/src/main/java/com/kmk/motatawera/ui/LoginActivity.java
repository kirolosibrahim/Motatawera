package com.kmk.motatawera.ui;


import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;

import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kmk.motatawera.MainActivity;
import com.kmk.motatawera.R;

public class LoginActivity extends AppCompatActivity {

    //Press Back Button Val
    private long lastPressedTime;
    private static final int PERIOD = 2000;

    //View
    EditText text_id, text_password;


    //Connect To FireStore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    //Firebase Auth
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    //Checking Success
    boolean isSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //FindView
        text_id = findViewById(R.id.editTextTextPersonName);
        text_password = findViewById(R.id.editTextTextPassword);

        //Login Button on Click Method
        findViewById(R.id.btn_login).setOnClickListener(v -> {
           //Calling Method
            Validationdata();
        });


    }


    //Validation Method
    private void Validationdata() {

        String id = text_id.getText().toString().trim();
        String password = text_password.getText().toString().trim();

        if (id.isEmpty()) {
            Toast.makeText(this, "pleas enter your id", Toast.LENGTH_SHORT).show();

        } else if (password.isEmpty()) {
            Toast.makeText(this, "Pleas enter your password", Toast.LENGTH_SHORT).show();
        } else {

            db.collection("student")
                    .addSnapshotListener((value, error) -> {
                        if (error == null) {
                            if (value == null) {
                                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
                            } else {
                                for (DocumentChange documentChange : value.getDocumentChanges()) {

                                    String idDb = documentChange
                                            .getDocument()
                                            .getId();
                                    String passwordDb = documentChange
                                            .getDocument()
                                            .getString("student_password");

                                    assert idDb != null;


                                    if (idDb.equals(id)) {
                                        assert passwordDb != null;
                                        if (passwordDb.equals(password)) {

                                            isSuccess = true;
                                            break;
                                        } else {
                                            isSuccess = false;
                                        }
                                    } else {
                                        isSuccess = false;
                                    }

                                }
                                if (isSuccess) {
                                   startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                } else {
                                    Toast.makeText(this, "No Found data", Toast.LENGTH_SHORT).show();
                                }

                            }


                        } else {
                            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    });

        }

    }

// Back Button Pressed
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    if (event.getDownTime() - lastPressedTime < PERIOD) {
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);

                        builder.setTitle("Exit");
                        builder.setMessage("Do You Want To Close the Application");
                        builder.setPositiveButton("Return", (dialog, which) -> {
                            dialog.dismiss();

                        });

                        builder.setNegativeButton("Close", (dialog, which) -> {
                            dialog.dismiss();
                            this.finishAffinity();

                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCanceledOnTouchOutside(true);
                        alertDialog.show();
                    }

                    lastPressedTime = event.getEventTime();
                    event.isCanceled();
            }
        return true;
    }
}


