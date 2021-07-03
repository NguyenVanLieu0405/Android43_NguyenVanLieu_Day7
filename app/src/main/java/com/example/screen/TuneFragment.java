package com.example.screen;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class TuneFragment extends Fragment {
    TextView tv_fromdefault,tv_from;

    public static TuneFragment newInstance() {

        Bundle args = new Bundle();

        TuneFragment fragment = new TuneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tune_layout,container,false);
        tv_fromdefault=view.findViewById(R.id.tv_fromdefault);
        tv_from = view.findViewById(R.id.tv_fromfile);
        tv_fromdefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] strings = {"Nexus Tune","Winphone Tune","Peep tune","Nokia Tune","Ect"};
                List<String>s  = new ArrayList<>();
                AlertDialog alertDialog = new AlertDialog.Builder((getContext()))
                        .setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    s.add(strings[which]);
                            }
                        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tv_fromdefault.setText(s.get(s.size()-1).toString());
                            }
                        }).setNegativeButton("CanCel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                alertDialog.show();
            }
        });

        return view;
    }
}
