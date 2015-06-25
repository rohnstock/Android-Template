package sample.company.de.sample_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import sample.company.de.sample_project.enums.FilterType;
import sample.company.de.sample_project.events.FilterListEvent;
import sample.company.de.sample_project.events.MessageEvent;
import sample.company.de.sample_project.models.MessageListModel;
import timber.log.Timber;

public class MessageListAdapter extends BaseAdapter {

    private List<MessageListModel> listOriginal;
    private List<MessageListModel> listCurrent;
    private List<FilterType> activatedTypes;
    private Context ctx;

    public MessageListAdapter(Context ctx, List<MessageListModel> listOriginal, List<FilterType> activatedTypes) {
        EventBus.getDefault().register(this);

        this.ctx = ctx;
        this.listOriginal = listOriginal;
        this.listCurrent = new LinkedList<>();
        this.activatedTypes = activatedTypes;
        this.filterMessages(this.activatedTypes);
        if (listOriginal == null)
            return;
    }

    public int getCount() {
        return this.listCurrent.size();
    }

    public MessageListModel getItem(int pos) {
        if(this.listCurrent.size() <= 0)
            return null;

        return this.listCurrent.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public  View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(this.ctx).inflate(
                android.R.layout.simple_list_item_1, parent, false);

        ((TextView) view.findViewById(android.R.id.text1)).setText(getItem(position).getMessageText());

        return view;
    }

    public void filterMessages(List<FilterType> activatedTypes) {
        this.activatedTypes = activatedTypes;

        this.updateList();
    }

    private void updateList() {
        this.listCurrent = new LinkedList<>();

        if(this.activatedTypes.size() <= 0)
            return;

        for(MessageListModel m : this.listOriginal) {
            if(this.activatedTypes.contains( m.getMessageType() ))
                this.listCurrent.add(m);
        }
    }

    @Subscribe
    public void handle(FilterListEvent type) {
        this.checkFilter(this.activatedTypes, type.getFilterType());
    }

    private void checkFilter(List<FilterType> types, FilterType filter) {
        if(types.contains(filter)) {
            types.remove(filter);
            return;
        }

        types.add(filter);
    }

}
