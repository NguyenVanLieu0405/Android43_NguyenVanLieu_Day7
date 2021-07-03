package com.example.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.screen.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ISave{
    ActivityMainBinding binding;
    SavePresenter savePresenter;

    Calendar myCalendar = Calendar.getInstance();



    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view,
                              int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            binding.tvDate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
        }
    };
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            binding.tvTime.setText(hourOfDay+":"+minute);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
//        getFragment(BuildFragment.newInstance());
        savePresenter =new SavePresenter(this);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getBaseContext(), v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.show();
            }
        });

        List<String> list = new ArrayList<>();
        list.add("Work");
        list.add("Family");
        list.add("Friend");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        binding.spnType.setAdapter(adapter);
        binding.ctvTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings = {"Family", "Game", "Android", "VTC", "Friend"};
                boolean[] booleans = {false, false, false, false, false};

                List<String> s = new ArrayList<>();
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("Choose Tags:")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                s.add(strings[which]);
                            }
                        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String str = "";
                                for (int i = 0; i < s.size(); i++) {
                                    if (i == s.size() - 1)
                                        str = str + s.get(i).toString();
                                    else
                                        str = str + s.get(i).toString() + ", ";
                                }

                                binding.ctvTag.setText(str);
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                alertDialog.show();
            }
        });
        binding.ctvWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                boolean[] booleans = {false, false, false, false, false, false, false};

                List<String> listWeek = new ArrayList<>();
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("Choose Tags:")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                listWeek.add(strings[which]);
                            }
                        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String str = "";
                                for (int i = 0; i < listWeek.size(); i++) {
                                    if (i == listWeek.size() - 1)
                                        str = str + listWeek.get(i).toString();
                                    else
                                        str = str + listWeek.get(i).toString() + ", ";
                                }

                                binding.ctvWeek.setText(str);
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                alertDialog.show();
            }
        });
        binding.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, d,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        binding.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this, t, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();

            }
        });
        binding.btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragment(TuneFragment.newInstance());

            }
        });
        binding.tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.edtTitle.getText().toString();
                String des = binding.edtDes.getText().toString();
                String time = binding.tvTime.getText().toString();
                String date = binding.tvDate.getText().toString();
                String type = binding.spnType.toString();
                String tags = binding.ctvTag.getText().toString();
                String weeks = binding.ctvWeek.getText().toString();
                savePresenter.onSave(title,des,time,date,type,tags,weeks);

            }
        });


    }

    private void getFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
    }


    @Override
    public void onSaveSuccessful(String mess) {
        Toast.makeText(getBaseContext(),mess,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSaveError(String mess) {
        Toast.makeText(getBaseContext(),mess,Toast.LENGTH_LONG).show();
    }
}