package com.fitmeet.plank;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sefford.circularprogressdrawable.CircularProgressDrawable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.contentContainer)
    RelativeLayout mContentContainer;
    @Bind(R.id.fab)
    FloatingActionButton mFab;

    @Bind(R.id.progress)
    ImageView mProgress;

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
                .setOutlineColor(getResources().getColor(android.R.color.darker_gray))
                .setRingColor(getResources().getColor(android.R.color.holo_green_light))
                .setCenterColor(getResources().getColor(android.R.color.holo_blue_dark))
                .create();
        mProgress.setImageDrawable(mCircularProgressDrawable);
        countDownAnimator = prepareProgressAnimation();
        countDownAnimator.start();


    }

    private Animator prepareProgressAnimation() {
        AnimatorSet animation = new AnimatorSet();

        final Animator indeterminateAnimation = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0, 3600);
        indeterminateAnimation.setDuration(3600);

        Animator innerCircleAnimation = ObjectAnimator.ofFloat(mCircularProgressDrawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0F, 0.75F);
        innerCircleAnimation.setDuration(3600);
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
            }
        });
        animation.playTogether(innerCircleAnimation, indeterminateAnimation);
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


    private Animator countDownAnimator;
}
