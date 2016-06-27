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
    }

    public void addCrime(Crime crime){
        mCrimes.add(crime);
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
