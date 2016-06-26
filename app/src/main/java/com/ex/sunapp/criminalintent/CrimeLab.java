package com.ex.sunapp.criminalintent;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();

        for(int i = 0;i < 100;i++){
            Crime crime = new Crime();
            crime.setTitle("crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }

    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID uuid){

        for(Crime crime : mCrimes){
            if(crime.getId().equals(uuid))
                return crime;
        }

        return null;
    }

    public static CrimeLab get(Context context){
        if(sCrimeLab == null)
            sCrimeLab = new CrimeLab(context);

        return sCrimeLab;
    }


}
