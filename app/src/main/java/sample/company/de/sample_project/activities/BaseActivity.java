package sample.company.de.sample_project.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import de.greenrobot.event.EventBus;
import sample.company.de.sample_project.R;
import sample.company.de.sample_project.fragments.ShowMessageListFragment;

public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setIcon(R.drawable.abc_list_longpressed_holo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EventBus.getDefault().register(this);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new ShowMessageListFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        this.navigateBack();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        Drawable aboutDrawable = new IconDrawable(this, Iconify.IconValue.fa_share)
                .colorRes(R.color.blue)
                .actionBarSize();
        menu.findItem(R.id.share).setIcon(aboutDrawable);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.navigateBack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateBack() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
