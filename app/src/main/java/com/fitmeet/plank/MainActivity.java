package com.fitmeet.plank;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fitmeet.plank.test.presentation.view.activity.BaseActivity;
import com.sefford.circularprogressdrawable.CircularProgressDrawable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.contentContainer)
    LinearLayout mContentContainer;
    @Bind(R.id.fab)
    FloatingActionButton mFab;

    @Bind(R.id.progress)
    ImageView mProgress;

    @Bind(R.id.style1)
    Button mStyle1;

    @Bind(R.id.style2)
    Button mStyle2;

    @Bind(R.id.style3)
    Button mStyle3;

    @Bind(R.id.style4)
    Button mStyle4;

    @Bind(R.id.actionControl)
    Button mActionControl;

    @Bind(R.id.min)
    TextView min;

    @Bind(R.id.sec)
    TextView sec;

    private static final int[] BLUE = new int[] {
            R.color.light_blue_50,
            R.color.light_blue_100,
            R.color.light_blue_200,
            R.color.light_blue_300,
            R.color.light_blue_400,
            R.color.light_blue_500,
            R.color.light_blue_600,
            R.color.light_blue_700,
            R.color.light_blue_800,
            R.color.light_blue_900,
    };

    private static final int[] YELLOW = new int[] {
            R.color.light_yellow_50,
            R.color.light_yellow_100,
            R.color.light_yellow_200,
            R.color.light_yellow_300,
            R.color.light_yellow_400,
            R.color.light_yellow_500,
            R.color.light_yellow_600,
            R.color.light_yellow_700,
            R.color.light_yellow_800,
            R.color.light_yellow_900,
    };


    private static final int[] RED = new int[] {
            R.color.light_red_50,
            R.color.light_red_100,
            R.color.light_red_200,
            R.color.light_red_300,
            R.color.light_red_400,
            R.color.light_red_500,
            R.color.light_red_600,
            R.color.light_red_700,
            R.color.light_red_800,
            R.color.light_red_900,
    };

    private static final int[][] COLOR_STYLE = new int[][] {
            BLUE,
            YELLOW,
            RED
    };

    private CircularProgressDrawable mCircularProgressDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "welcome to instant run", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initCircle();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initCircle() {
        mCircularProgressDrawable = new CircularProgressDrawable.Builder()
                .setRingWidth(getResources().getDimensionPixelSize(R.dimen.plank_progress_ring_size))
                .setOutlineColor(getResources().getColor(android.R.color.holo_red_dark))
                .setRingColor(getResources().getColor(android.R.color.holo_green_light))
                .setCenterColor(getResources().getColor(getNextColor()))
                .create();
        mProgress.setImageDrawable(mCircularProgressDrawable);



    }

    private void startAnimation() {
        countDownAnimator = prepareProgressAnimation();
        countDownAnimator.start();
    }

    private static final long ONE_MINUTE = 1000 * 60;

    private static final long TEN_SEC = 1000 * 10;

    private Animator prepareProgressAnimation() {
        AnimatorSet animation = new AnimatorSet();

        final Animator indeterminateAnimation = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0, 3600);
        indeterminateAnimation.setDuration(TEN_SEC);

        Animator innerCircleAnimation = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0F, 1F);
        innerCircleAnimation.setDuration(TEN_SEC);
        innerCircleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        innerCircleAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mCircularProgressDrawable.setIndeterminate(true);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                indeterminateAnimation.end();
                mCircularProgressDrawable.setIndeterminate(false);
                mCircularProgressDrawable.setProgress(0);
//
//                mCircularProgressDrawable.setCenterColor(getNextColor());
//                countDownAnimator = prepareProgressAnimation();
//                countDownAnimator.start();
                if(isCounting) {
                    initCircle();
                    startAnimation();
                }

            }
        });
        animation.playTogether(innerCircleAnimation);
        return animation;
    }

    private Animator prepareProgressAnimation1() {
        AnimatorSet animation = new AnimatorSet();

        Animator firstBounce = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, mCircularProgressDrawable.getCircleScale(), 0.88f);
        firstBounce.setDuration(300);
        firstBounce.setInterpolator(new CycleInterpolator(1));

        Animator secondBounce = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0.75F, 0.83F);
        secondBounce.setDuration(300);
        secondBounce.setInterpolator(new CycleInterpolator(1));

        Animator thirdBounce = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0.75F, 0.80F);
        thirdBounce.setDuration(300);
        thirdBounce.setInterpolator(new CycleInterpolator(1));

        animation.playSequentially(firstBounce, secondBounce, thirdBounce);

        return animation;
    }

    private Animator prepareProgressAnimation2() {
        AnimatorSet animation = new AnimatorSet();
        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0F, 1F);

        progressAnimation.setDuration(3600);
        progressAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(mCircularProgressDrawable, CircularProgressDrawable.RING_COLOR_PROPERTY,
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_green_light));
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setDuration(3600);

        animation.playTogether(progressAnimation, colorAnimator);
        return animation;
    }

    private Animator prepareAnimation3() {
        AnimatorSet animation = new AnimatorSet();

        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0.75F, 0F);
        progressAnimation.setDuration(1200);
        progressAnimation.setInterpolator(new AnticipateInterpolator());

        Animator innerCircleAnimation = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0.75F, 0F);
        innerCircleAnimation.setDuration(1200);
        innerCircleAnimation.setInterpolator(new AnticipateInterpolator());

        ObjectAnimator invertedProgress = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0F, 0.75F);
        invertedProgress.setDuration(1200);
        invertedProgress.setStartDelay(3200);
        invertedProgress.setInterpolator(new OvershootInterpolator());

        Animator invertedCircle = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0F, 0.75F);
        invertedCircle.setDuration(1200);
        invertedCircle.setStartDelay(3200);
        invertedCircle.setInterpolator(new OvershootInterpolator());

        animation.playTogether(progressAnimation, innerCircleAnimation, invertedProgress, invertedCircle);

        return animation;
    }


    private Animator countDownAnimator;

    @OnClick(R.id.style1)
    void changeStyle1() {
        countDownAnimator = prepareProgressAnimation();
        countDownAnimator.start();
    }

    @OnClick(R.id.style2)
    void changeStyle2() {
        countDownAnimator = prepareProgressAnimation1();
        countDownAnimator.start();
    }

    @OnClick(R.id.style3)
    void changeStyle3() {
        countDownAnimator = prepareProgressAnimation2();
        countDownAnimator.start();
    }

    @OnClick(R.id.style4)
    void changeStyle4() {
        countDownAnimator = prepareAnimation3();
        countDownAnimator.start();
    }


    //目前一共30种颜色循环展示
    private int currentColorIndex = 0;

    private int getNextColor() {
        if(currentColorIndex >= 30)
            currentColorIndex = 0;
        int color = COLOR_STYLE[currentColorIndex/10][currentColorIndex%10];
        currentColorIndex++;
        return color;
    }

    private boolean isCounting = false;
    @OnClick(R.id.actionControl)
    void control() {
        if(isCounting) {
            mHandler.removeMessages(100);
            isCounting = false;
            stopAnimation();
            mActionControl.setText("start");
            clearTimeStatus();

        } else {
            secCount = 0;
            currentColorIndex = 0;
            startAnimation();
            isCounting = true;
            mActionControl.setText("stop");
            mHandler.sendEmptyMessageDelayed(100, 1000);
        }
    }

    private void stopAnimation() {
        countDownAnimator.cancel();
    }

    private void clearTimeStatus() {
        min.setText("00");
        sec.setText("00");
    }

    private int secCount = 0;

    private static final int MIN_INDEX = 60;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(isCounting) {
                secCount++;
                int secDisplay = secCount % 60;
                if(secDisplay < 10)
                    sec.setText("0"+secDisplay);
                else
                    sec.setText(String.valueOf(secDisplay));

                int minDisplay = secCount / 60;
                if(minDisplay < 10)
                    min.setText("0" + minDisplay);
                else
                    min.setText(String.valueOf(minDisplay));

                mHandler.sendEmptyMessageDelayed(100, 1000);
            }

        }
    };


}
