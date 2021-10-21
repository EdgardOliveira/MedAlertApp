package br.com.technologies.venom.medalertapp;

import android.app.Application;

public class MedAlertApplication extends Application {

    private static AppRepository appRepository;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static AppRepository getAppRepository(Application application) {
        if (appRepository == null) {
            appRepository = new AppRepository(application);
        }
        return appRepository;
    }
}
