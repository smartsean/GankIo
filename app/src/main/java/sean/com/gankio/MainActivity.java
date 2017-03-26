package sean.com.gankio;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sean.com.gankio.ui.fragment.AndroidFragment;
import sean.com.gankio.ui.fragment.WealFragment;

import static sean.com.gankio.R.id.weal;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int FRAGMENT_WEAL = 0X01;
    private static final int FRAGMENT_ANDROID = 0X02;
    private static final int FRAGMENT_IOS = 0X03;
    private static final int FRAGMENT_APP = 0X04;
    private static final int FRAGMENT_REST_VIDEO = 0X05;
    private static final int FRAGMENT_EXPANSION = 0X06;
    private static final int FRAGMENT_FORE_END = 0X07;


    @BindView(R.id.common_title_tv)
    TextView commonTitleTv;
    @BindView(R.id.common_title_tb)
    Toolbar commonTitleTb;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.activity_main)
    DrawerLayout activityMain;

    private WealFragment wealFragment;
    private AndroidFragment androidFragment;


    private Context context;
    FragmentManager fragmentManager = getSupportFragmentManager();
//    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        initNavigationView();
        init();
    }


    // 初始化initNavigationView
    private void initNavigationView() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                hideAllFragment(transaction);
                switch (item.getItemId()) {
                    case weal:
                        changeToWeal(transaction);
                        break;
                    case R.id.android:
                        setTitleThis(R.string.android_text);
                        androidFragment = AndroidFragment.newInstance(AndroidFragment.ANDROID_KEY);
                        transaction.add(R.id.content_fl,androidFragment);
                        break;
                    case R.id.ios:
                        setTitleThis(R.string.ios);
                        androidFragment= AndroidFragment.newInstance(AndroidFragment.IOS_KEY);
                        transaction.add(R.id.content_fl,androidFragment);
                        break;
                    case R.id.rest_video:
                        setTitleThis(R.string.rest_video_text);
                        androidFragment = AndroidFragment.newInstance(AndroidFragment.REST_VIDEO_KEY);
                        transaction.add(R.id.content_fl,androidFragment);
                        break;
                    case R.id.expansion_resources:
                        setTitleThis(R.string.expand_text);
                        androidFragment = AndroidFragment.newInstance(AndroidFragment.EXPANSION_KEY);
                        transaction.add(R.id.content_fl,androidFragment);
                        break;
                    case R.id.app:
                        setTitleThis(R.string.app_text);
                        androidFragment = AndroidFragment.newInstance(AndroidFragment.APP_KEY);
                        transaction.add(R.id.content_fl,androidFragment);
                        break;
                    case R.id.fore_end:
                        setTitleThis(R.string.expand_text);
                        androidFragment = AndroidFragment.newInstance(AndroidFragment.FORE_END_KEY);
                        transaction.add(R.id.content_fl,androidFragment);
                        break;
                }
                transaction.commitAllowingStateLoss();
                activityMain.closeDrawers();
                return true;
            }
        });
    }


    private void changeToWeal(FragmentTransaction transaction) {
        setTitleThis(R.string.weal_string);
        if (wealFragment == null) {
            wealFragment = new WealFragment();
            if (!wealFragment.isAdded()) {
                transaction.add(R.id.content_fl, wealFragment);
            }
        } else {
            transaction.show(wealFragment);
        }
    }


    private void hideAllFragment(FragmentTransaction transaction) {
        if (wealFragment != null && !wealFragment.isHidden()) {
            transaction.hide(wealFragment);
        }
        if (androidFragment != null && !androidFragment.isHidden()) {
            transaction.hide(androidFragment);
        }
    }


    private void init() {
        setSupportActionBar(commonTitleTb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            commonTitleTb.setNavigationIcon(R.mipmap.ic_menu_white_24dp);
        }
    }


    private void setTitleThis(int res) {
        commonTitleTv.setText(res);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                activityMain.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
