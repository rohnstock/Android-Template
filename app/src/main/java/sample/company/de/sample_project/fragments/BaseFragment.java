package sample.company.de.sample_project.fragments;

import android.app.Fragment;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import sample.company.de.sample_project.events.MessageEvent;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onResume() {
        EventBus.getDefault().register(this);

        super.onResume();
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Subscribe
    public void handle(MessageEvent e) {}
}