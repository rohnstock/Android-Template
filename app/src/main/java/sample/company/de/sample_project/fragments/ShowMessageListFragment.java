package sample.company.de.sample_project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import butterknife.OnItemClick;
import de.greenrobot.event.EventBus;
import sample.company.de.sample_project.R;
import sample.company.de.sample_project.adapter.MessageListAdapter;
import sample.company.de.sample_project.enums.FilterType;
import sample.company.de.sample_project.events.ActionBarTitleEvent;
import sample.company.de.sample_project.events.FilterListEvent;
import sample.company.de.sample_project.events.ShowFragmentEvent;
import sample.company.de.sample_project.models.MessageListModel;
import timber.log.Timber;

public class ShowMessageListFragment extends BaseFragment {

    @InjectView(R.id.messageList)
    ListView messageList;

    @InjectViews({ R.id.filter_open, R.id.filter_in_edit, R.id.filter_closed })
    List<Button> filterButtons;

    private MessageListAdapter adapterA;

    private List<FilterType> activatedTypes = new LinkedList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment_layout, container, false);

        ButterKnife.inject(this, view);

        EventBus.getDefault().post(new ActionBarTitleEvent("Übersicht: Meldungen"));

        this.initActivatedTypes();
        adapterA = new MessageListAdapter(getActivity(), this.createData(), this.activatedTypes);
        messageList.setAdapter(adapterA);

        return view;
    }

    private List<MessageListModel> createData() {
        // Create and populate a List of planet names.
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        List<MessageListModel> list = new LinkedList<>();
        for (int i = 0; i < planets.length; i++) {
            FilterType type = FilterType.OPEN;
            if(i%2 == 0)
                type = FilterType.IN_EDIT;
            list.add(new MessageListModel(i, planets[i], type));
        }

        return list;
    }

    private void initActivatedTypes() {
        this.activatedTypes = new LinkedList<>();

        this.activatedTypes.add(FilterType.OPEN);
        this.activatedTypes.add(FilterType.IN_EDIT);
        this.activatedTypes.add(FilterType.CLOSED);
    }

    @OnItemClick(R.id.messageList)
    public void onItemClick(int pos) {
        Timber.d("Pos: " + pos + " - " + this.adapterA.getItem(pos).getMessageText());
        EventBus.getDefault().post(new ShowFragmentEvent(ShowMessageDetailsFragment.newInstance(this.adapterA.getItem(pos).getId() + "")));
    }

    @OnClick({R.id.filter_open, R.id.filter_in_edit, R.id.filter_closed})
    public void filterClick(Button button) {
        // toggle button
        this.toggleButton((Button) this.getView().findViewById(button.getId()));

        switch (button.getId()) {
            case R.id.filter_open:
                EventBus.getDefault().post(new FilterListEvent(FilterType.OPEN));
                break;

            case R.id.filter_in_edit:
                EventBus.getDefault().post(new FilterListEvent(FilterType.IN_EDIT));
                break;

            case R.id.filter_closed:
                EventBus.getDefault().post(new FilterListEvent(FilterType.CLOSED));
                break;
        }

        // update listview data
        adapterA.filterMessages(this.activatedTypes);
        adapterA.notifyDataSetChanged();
    }

    // use toggle button!
    private void toggleButton(Button button) {
        // set default value
        if(button.getTag(R.id.filter_open) == null)
            button.setTag(R.id.filter_open, true);

        boolean statusEnabled = (boolean) button.getTag(R.id.filter_open);
        Timber.d("Status: " + statusEnabled);
        statusEnabled = !statusEnabled;
        button.setTag(R.id.filter_open, statusEnabled);

        // set non-active button style
        button.setBackgroundColor(getResources().getColor(R.color.red));

        // set active button style
        if(statusEnabled) {
            // active
            button.setBackgroundColor(getResources().getColor(R.color.blue));
        }
    }

}