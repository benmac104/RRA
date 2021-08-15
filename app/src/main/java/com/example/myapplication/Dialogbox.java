package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Random;

public class Dialogbox extends AppCompatDialogFragment {
     TextView textView;
     LinearLayout ld;
    private RatingBar rBar;
    private TextView tView;
    private Button btn;
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.latout_dialog,null);
        rBar = view.findViewById(R.id.ratingBar1);
        tView = view.findViewById(R.id.textview1);
       // String rating=String.valueOf(rBar.getRating());
        ld=view.findViewById(R.id.co);
        colorrandom(ld);
        ImageView imageView = new ImageView(getActivity());

        // setting the image in the layout
        imageView.setImageResource(R.drawable.tnx);

        // calling addview with width and height
        //addvieW(imageView, 200, 200);

        builder.setView(view)
                .setTitle(getResources().getString(R.string.Rate_us))
                .setNegativeButton(getResources().getString(R.string.cancel),new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getActivity(), getResources().getString(R.string.hope_to_see_you_again), Toast.LENGTH_SHORT).show();

                    }
                })
                .setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        if( rBar.getRating()>3) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.thank_you), Toast.LENGTH_SHORT).show(); }
                    else {
                            Toast.makeText(getActivity(), getResources().getString(R.string.we_will_improve_us), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        return builder.create();
        }
    public void colorrandom(LinearLayout linearLayout) {

        // Initialising the Random();
        Random random = new Random();

        // adding the random background color
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

        // setting the background color
        linearLayout.setBackgroundColor(color);
    }
}
