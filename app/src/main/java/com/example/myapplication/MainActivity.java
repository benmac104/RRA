
package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String count="0";
    String name;
    Button minus,plus;
    String ephone,phone;
    String sname=null;
    int minLength=7;
    String[] users = { "050","051","056","068","053","058","064","065","055","066","067","059","057", "054", "052", "059", "055" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cstrt=findViewById(R.id.Cstart);
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        EditText sname=findViewById(R.id.name);
        EditText sphone=findViewById(R.id.txtVw);

        cstrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.Cstart) {//check length phone
                    if (sphone.getText().toString().length() < 7) {
                        sphone.setError("your phone number to short!");
                    }
                    else if(sphone.getText().toString().length() > 7) {

                            sphone.setError("your phone number to long!");

                    }
                    else{
                        Intent intent=new Intent(MainActivity.this,Ordering.class);
                        name = sname.getText().toString();
                        phone = sphone.getText().toString();

                        intent.putExtra("name",name);
                        intent.putExtra("phone",ephone+phone);
                        startActivity(intent);

                    }


                }



            }
        });



    };

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        ephone = users[position];
        ((TextView)arg0.getChildAt(0)).setTextColor(Color.BLACK);

        //Toast.makeText(getApplicationContext(), "Selected User: "+users[position] ,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
        ephone="050";
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}