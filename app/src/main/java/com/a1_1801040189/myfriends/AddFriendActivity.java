package com.a1_1801040189.myfriends;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.a1_1801040189.myfriends.model.Friend;

public class AddFriendActivity extends AppCompatActivity {


    EditText inputPhoneNo,inputMail,inputName;
    ImageButton YES,NO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        YES=findViewById(R.id.Okbtn);
        NO =findViewById(R.id.cancelbtn);

        inputPhoneNo = findViewById(R.id.inputPhoneNo);
        inputMail = findViewById(R.id.inputEmail);
        inputName=findViewById(R.id.inputName);


        YES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inpName=inputName.getText().toString();
                String inpMail=inputMail.getText().toString();
                String inpAddress=inputPhoneNo.getText().toString();

                if(!inpAddress.equals("")&&!inpMail.equals("")&&!inpName.equals("")){


                    Friend f= new Friend(inpName,inpMail,inpAddress);
                    Intent i = new Intent(AddFriendActivity.this,MainActivity.class);
                    i.putExtra("f",f);
                    setResult(RESULT_OK,i);

                    finish();
                }else{
                    new AlertDialog.Builder(AddFriendActivity.this)
                            .setIcon(android.R.drawable.ic_delete).setTitle("Alert dialog")
                            .setMessage("you should complete form!")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing
                                }
                            })
                            .show();


                }
            }
        });

        NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(AddFriendActivity.this)
                        .setIcon(android.R.drawable.ic_btn_speak_now).setTitle("Confirm dialog")
                        .setMessage("Are you sure to exit without add new info!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dothing
                    }
                }).show();
            }



        });
    }
}