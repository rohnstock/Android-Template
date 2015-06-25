package sample.company.de.sample_project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import sample.company.de.sample_project.R;
import sample.company.de.sample_project.events.ActionBarTitleEvent;
import sample.company.de.sample_project.events.MessageEvent;

public class ShowMessageDetailsFragment extends BaseFragment {

    private static final String MESSAGE_ID = "MESSAGE_ID";

    private String messageId;

    public static final ShowMessageDetailsFragment newInstance(String id) {
        ShowMessageDetailsFragment f = new ShowMessageDetailsFragment();

        // set bundle properties
        Bundle bdl = new Bundle(1);
        bdl.putString(MESSAGE_ID, id);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // restore bundle properties
        this.messageId = getArguments().getString(MESSAGE_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment_layout, container, false);
        EventBus.getDefault().post(new ActionBarTitleEvent("zweites fragment mit ID: " + this.messageId));
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @OnClick(R.id.second_button)
    public void secondButtonClick() {
        EventBus.getDefault().post(new MessageEvent("2. Button"));
    }
}