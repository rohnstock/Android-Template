package sample.company.de.sample_project.events;

import android.app.Fragment;

public final class ShowFragmentEvent {
    private Fragment fragment;

    public ShowFragmentEvent(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }
}