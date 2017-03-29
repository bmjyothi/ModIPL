package com.siliconaroma.modipl;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.siliconaroma.modipl.Adapter.ScheduleAdapter;
import com.siliconaroma.modipl.Data.MatchData;
import com.siliconaroma.modipl.database.IplDB;
import com.siliconaroma.modipl.database.IplDbHelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class IplSchedule extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";
    private ListView mScheduleList;
    private IplDB mDb;
    private IplDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipl_schedule);

        mDb = IplDB.getInstance(IplSchedule.this);
        mDbHelper = new IplDbHelper(mDb);
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        mScheduleList = (ListView) findViewById(R.id.schedule_list);

        displayScheduleList();
        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        //Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }

    private void displayScheduleList(){
        ArrayList<MatchData> adapterData =  mDbHelper.getAllMatches();
        ScheduleAdapter adapter = new ScheduleAdapter(IplSchedule.this,adapterData);
        mScheduleList.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ipl_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
