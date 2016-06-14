package com.fitmeet.steppedometer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fitmeet.plank.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StepPedometerActivity extends AppCompatActivity {

    private StepPedometerService mStepPedometerService;

    @Bind(R.id.stepInfo)
    TextView mStepInfoTv;

    @Bind(R.id.stepControl)
    Button mStepControl;

    private boolean mIsBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_pedometer);
        ButterKnife.bind(this);
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
        doBindService();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {

            mStepPedometerService = ((StepPedometerService.StepPedometerBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mStepPedometerService = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this, StepPedometerService.class),
                mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;

    }

    void doUnbindService() {
        if(mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }


}
