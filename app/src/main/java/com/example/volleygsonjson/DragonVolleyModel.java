package com.example.volleygsonjson;


import com.google.gson.annotations.SerializedName;

public class DragonVolleyModel {

    @SerializedName("type")
    private String mName;
    @SerializedName("hp")
    private int mYear;
    @SerializedName("description")
    private String mConsole;


    public DragonVolleyModel(final String mName, final int mYear, final String mConsole){
        setmName(mName);
        setmYear(mYear);
        setmConsole(mConsole);
    }

    public String getmName(){ return mName; }
    public int getmYear(){return mYear;}
    public String getmConsole(){return mConsole;}

    public void setmName(final String mName){this.mName = mName;}
    public void setmYear(final int mYear){this.mYear = mYear;}
    public void setmConsole(final String mConsole){this.mConsole = mConsole;}


}
