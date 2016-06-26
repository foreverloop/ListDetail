package com.ex.sunapp.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.ex.sunapp.criminalintent.crime_id";

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        Log.i("before crfr","uuid=" + crimeId);
        return CrimeFragment.newInstance(crimeId);
    }

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent i = new Intent(packageContext,CrimeActivity.class);
        Log.i("before act intent","new intent for activity:" + crimeId);
        i.putExtra(EXTRA_CRIME_ID,crimeId);
        return i;
    }
}
