package br.com.kleberxavier.digitalvolunteers;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Kai on 16/09/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
