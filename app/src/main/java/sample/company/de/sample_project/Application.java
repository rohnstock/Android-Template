package sample.company.de.sample_project;

import timber.log.Timber;

/**
 * Created by rohnstock on 22.06.15.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
