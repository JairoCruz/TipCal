package edu.jairo.android.tipcal;

import android.app.Application;

/**
 * Created by TSE on 31/05/2016.
 */
public class TipCalcApp extends Application{
    private final static String ABOUT_URL = "https://www.facebook.com";

    public  String getAboutUrl() {
        return ABOUT_URL;
    }
}
