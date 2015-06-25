package sample.company.de.sample_project.activities;

import android.os.Bundle;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import sample.company.de.sample_project.R;
import sample.company.de.sample_project.events.ActionBarTitleEvent;
import sample.company.de.sample_project.events.FilterListEvent;
import sample.company.de.sample_project.events.MessageEvent;
import sample.company.de.sample_project.events.ShowFragmentEvent;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Subscribe
    public void handle(ShowFragmentEvent event) {
        Timber.d("ShowFragment: " + event.getFragment().toString());
        getFragmentManager().beginTransaction().replace(R.id.container, event.getFragment()).addToBackStack(null).commit();
    }

    @Override
    protected void onDestroy() {
        // Unregister
        bus.unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void handle(ActionBarTitleEvent event) {
        setTitle(event.getTitle());
    }

    @Subscribe
    public void handle(MessageEvent event) {
        Timber.d("Erhalte Message Event: " + event.getMessage());
    }

}
