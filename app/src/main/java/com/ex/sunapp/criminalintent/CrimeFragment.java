package com.ex.sunapp.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mTitleText;
    private Button mDateButton;
    private CheckBox mSolvedCheckbox;
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);

        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

    }

    public static CrimeFragment newInstance(UUID crimeid){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeid);

        CrimeFragment cf = new CrimeFragment();
        cf.setArguments(args);

        return cf;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime,container,false);

        mDateButton = (Button) v.findViewById(R.id.crime_date_button);
        mTitleText =  (EditText) v.findViewById(R.id.crime_title);
        mSolvedCheckbox = (CheckBox) v.findViewById(R.id.crime_solved);

        //mDateButton.setText(DateFormat.getLongDateFormat(getActivity()).format(mCrime.getDate()));
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dpf = DatePickerFragment.newInstance(mCrime.getDate());
                dpf.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
                dpf.show(fm,DIALOG_DATE);
            }
        });

        mTitleText.setText(mCrime.getTitle());
        mSolvedCheckbox.setChecked(mCrime.isSolved());

        mSolvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        mTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_OK)
            return;

        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }
}
