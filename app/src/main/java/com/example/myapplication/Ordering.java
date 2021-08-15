package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Random;

import static android.view.View.GONE;
import static android.view.View.OnClickListener;
import static android.view.View.VISIBLE;

public class Ordering extends AppCompatActivity {
    ScrollView scrollView;
    TextView tvDate;
    EditText etTime;
    TextView amountp,cchild;
    SeekBar seekBar,seekBarc;
    EditText cpop;
    Button rate;
    DatePickerDialog.OnDateSetListener setListener;
    boolean istimeset;
    TimePickerDialog picker;
    EditText eText;
    RadioButton inside,outside;
    LinearLayout linearLayout;
    Button reset,up,done;
    RadioGroup rg;
    String counter,ccounter,date,time,result;
    CheckBox vegan, penut, gluten;
    FloatingActionButton floatingActionButton, floatingActionButton2;
    Boolean isDateChanged = false;

    FloatingActionButton mAddAlarmFab, mAddPersonFab;
    ExtendedFloatingActionButton mAddFab;
    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering);
        scrollView=findViewById(R.id.scroll);
        rate= findViewById(R.id.rate);

        String name = getIntent().getExtras().getString("name");
        String phone = getIntent().getExtras().getString("phone");
        TextView tvName = findViewById(R.id.name);
        TextView tvAge = findViewById(R.id.tvAge);
        tvName.setText(getString(R.string.hello) +name);
        istimeset=false;

        mAddFab = findViewById(R.id.add_fab);
        mAddAlarmFab = findViewById(R.id.add_alarm_fab);
        mAddPersonFab = findViewById(R.id.add_person_fab);
        // texts as GONE
        mAddAlarmFab.setVisibility(GONE);
        mAddPersonFab.setVisibility(GONE);
        // invisible
        isAllFabsVisible = false;
        // shrinked state initially
        mAddFab.shrink();
        mAddFab.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {
                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs VISIBLE.
                            mAddAlarmFab.show();
                            mAddPersonFab.show();
                            // Now extend the parent FAB, as
                            // user clicks on the shrinked
                            // parent FAB
                            mAddFab.extend();
                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = true;
                        } else {
                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            mAddAlarmFab.hide();
                            mAddPersonFab.hide();
                            // Set the FAB to shrink after user
                            // closes all the sub FABs
                            mAddFab.shrink();
                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = false;
                        }
                    }
                });
        mAddPersonFab.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText
                                (Ordering.this,getString(R.string.fill_all_the_field),
                                        Toast.LENGTH_SHORT).show();
                    }
                });
        mAddAlarmFab.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText

                                (Ordering.this,getString(R.string.create_by_Ben_Machlev),
                                        Toast.LENGTH_SHORT).show();
                    }
                });

        vegan = (CheckBox)findViewById(R.id.vegan);
        penut = (CheckBox)findViewById(R.id.nut);
        gluten = (CheckBox)findViewById(R.id.glu);
        inside=findViewById(R.id.inside);
        outside=findViewById(R.id.outside);
        //scrolling
        linearLayout = findViewById(R.id.linearLayout1);
        reset=findViewById(R.id.reset);
        done=findViewById(R.id.done);
        done.setEnabled(false);
        result =getString(R.string.you_will_sit);
        rg = (RadioGroup) findViewById(R.id.locate);

         done.setOnClickListener(new OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {
                    scrollView.setVisibility(VISIBLE);
                    scrollView.fullScroll(scrollView.FOCUS_DOWN);
                    done.setVisibility(GONE);
                    reset.setVisibility(VISIBLE);
                    tvAge.setText(getString(R.string.your_number) + phone);


                    TextView order = findViewById(R.id.order);
                    order.setText(getString(R.string.your_order_on) + date + getString(R.string.at) + time);
                    TextView position = findViewById(R.id.posi);
                    result += (inside.isChecked()) ? getString(R.string.inside) : (outside.isChecked()) ? getString(R.string.outside) : "";
                    position.setText(result);

                    if (vegan.isChecked()) {
                        ImageView imageView = new ImageView(Ordering.this);

                        // setting the image in the layout
                        imageView.setImageResource(R.drawable.smallvegan);

                        // calling addview with width and height
                        addvieW(imageView, 200, 200);

                    }
                    if (gluten.isChecked()) {
                        ImageView imageView = new ImageView(Ordering.this);

                        // setting the image in the layout
                        imageView.setImageResource(R.drawable.smallgluten);

                        // calling addview with width and height
                        addvieW(imageView, 200, 200);

                    }
                    if (penut.isChecked()) {
                        ImageView imageView = new ImageView(Ordering.this);

                        // setting the image in the layout
                        imageView.setImageResource(R.drawable.smallpeanut);

                        // calling addview with width and height
                        addvieW(imageView, 200, 200);

                    }

                    // initialising new layout

                        for (int i = 0; i < Integer.parseInt(cpop.getText().toString()); i++) {
                            ImageView imageView = new ImageView(Ordering.this);

                            // setting the image in the layout
                            imageView.setImageResource(R.drawable.mancolor);

                            // calling addview with width and height
                            addvieW(imageView, 200, 200);


                        }

                        for (int i = 0; i < Integer.parseInt(ccounter); i++) {
                            ImageView imageView = new ImageView(Ordering.this);

                            // setting the image in the layout
                            imageView.setImageResource(R.drawable.babycolor);

                            // calling addview with width and height
                            addvieW(imageView, 200, 200);


                        }

                    LinearLayout lButton = new LinearLayout(Ordering.this);
                    TextView ts = findViewById(R.id.Tscroll);
                    Button bup = new Button(Ordering.this);

                    // bup.setBackground(arrow_up_float);
                    lButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    lButton.setGravity(Gravity.CENTER);


                    bup.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow, 0, 0, 0);
                    bup.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            scrollView.scrollTo(2, 2);
                        }
                    });

                    linearLayout.addView(bup);

                }
            });

        reset.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v==reset) {
                    Intent i = new Intent(Ordering.this, MainActivity.class); //change it to your main class
                    //the following 2 tags are for clearing the backStack and start fresh
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                    startActivity(i);

                }
            }
        });



        rate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        tvDate=findViewById(R.id.date);
        etTime=findViewById(R.id.time);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

//open date
        tvDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        Ordering.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                eText.setVisibility(VISIBLE);


            }
        });

        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                date=day+"/"+month+"/"+year;
                tvDate.setTextColor(Color.BLACK);
                tvDate.setText(date);
            }
        };
        tvDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //open time
        eText=findViewById(R.id.editText1);
        eText.setText(getString(R.string.set_time));
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(Ordering.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                time=sHour+":"+sMinute;
                                eText.setText(sHour + ":" + sMinute);
                                eText.setTextColor(Color.BLACK);
                                istimeset=true;
                                checkText();

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        eText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkText();
                Toast.makeText(Ordering.this,"chcel "+eText.getText(),Toast.LENGTH_SHORT);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//amount people
        cpop=findViewById(R.id.cpop);
        cpop.setText("1");
    //children
        seekBarc=findViewById(R.id.seekbarchild);
        cchild=findViewById(R.id.pop);
        ccounter="0";
        seekBarc.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cchild.setText(String.valueOf(progress)+getString(R.string.kids));
                ccounter = String.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setProgress(0);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void openDialog(){
        Dialogbox dem=new Dialogbox();
        dem.show(getSupportFragmentManager(),"test dialog");
    }
    public void colorrandom(ImageView imageView) {

        // Initialising the Random();
        Random random = new Random();

        // adding the random background color
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

        // setting the background color
        imageView.setBackgroundColor(color);
    }

    private void addvieW(ImageView imageView, int width, int height) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        // setting the margin in linearlayout
        params.setMargins(0, 10, 0, 10);
        imageView.setLayoutParams(params);

        // adding the image in layout
        linearLayout.addView(imageView);
    }

    private boolean isEmpty(EditText etText) {
        if (cpop.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
    public void onRadioButtonClicked(View view) {
        checkText();
        boolean checked = ((RadioButton) view).isChecked();
        String str="";
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.inside:
                if(checked)
                    str = getString(R.string.inside);
                break;
            case R.id.outside:
                if(checked)
                    str = getString(R.string.outside);
                break;

        }
    }


    private void checkText() {
        if(!eText.getText().toString().isEmpty() && (rg.getCheckedRadioButtonId() != -1) && !tvDate.getText().toString().isEmpty() && istimeset && (!time.matches(" ")))
        {
            done.setEnabled(true);
        }
    }
}