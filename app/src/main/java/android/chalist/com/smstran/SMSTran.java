package android.chalist.com.smstran;

import android.app.ActivityManager;
import android.chalist.com.smstran.services.FetchSMSService;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SMSTran extends AppCompatActivity {

    private Button mSwitchServiceBtn;
    private boolean mIsFetchSMSServiceStart = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smstran);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mSwitchServiceBtn = (Button) findViewById(R.id.switchServiceBtn);
        if(isIsFetchSMSServiceStart()) {
            mSwitchServiceBtn.setText(getResources().getString(R.string.stop_service_btn));
        } else {
            mSwitchServiceBtn.setText(getResources().getString(R.string.start_service_btn));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smstran, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSwitchService(View view) {
        if(mIsFetchSMSServiceStart) {
            mSwitchServiceBtn.setText(getResources().getString(R.string.stop_service_btn));
        } else {
            mSwitchServiceBtn.setText(getResources().getString(R.string.start_service_btn));
        }
    }

    private boolean isIsFetchSMSServiceStart() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (FetchSMSService.class.getName().equals(service.service.getClassName())) {
                mIsFetchSMSServiceStart = true;
                return true;
            }
        }
        mIsFetchSMSServiceStart = false;
        return false;
    }

}
