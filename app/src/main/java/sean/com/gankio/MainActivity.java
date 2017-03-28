package sean.com.gankio;

import android.content.Context;
import android.content.Intent;
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
    private AndroidFragment iosFragment;
    private AndroidFragment restVideoFragment;


    private Context context;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        context = this;
        initNavigationView();
        init();
        firstInShowWealFragment(savedInstanceState);
    }

    /**
     * 首次进入显示福利Fragment
     */
    private void firstInShowWealFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            wealFragment = new WealFragment();
            transaction.add(R.id.content_fl, wealFragment);
            transaction.commit();
        }
    }


    /**
     * 初始化initNavigationView
     */
    private void initNavigationView() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                hideAllFragment(transaction);
                switch (item.getItemId()) {
                    case weal:
                        changeToWeal(transaction, R.string.weal_string);
                        break;
                    case R.id.android:
                        changeToCommon(transaction, R.string.android_text, AndroidFragment.ANDROID_KEY);
                        break;
                    case R.id.ios:
                        changeToIos(transaction, R.string.ios, AndroidFragment.IOS_KEY);
//                        changeToCommon(transaction, R.string.ios, AndroidFragment.IOS_KEY);
                        break;
                    case R.id.rest_video:
                        changeToRestVideo(transaction, R.string.rest_video_text, AndroidFragment.REST_VIDEO_KEY);
//                        changeToCommon(transaction, R.string.rest_video_text, AndroidFragment.REST_VIDEO_KEY);
                        break;
                    case R.id.expansion_resources:
                        changeToCommon(transaction, R.string.expand_text, AndroidFragment.EXPANSION_KEY);
                        break;
                    case R.id.app:
                        changeToCommon(transaction, R.string.app_text, AndroidFragment.APP_KEY);
                        break;
                    case R.id.fore_end:
                        changeToCommon(transaction, R.string.expand_text, AndroidFragment.FORE_END_KEY);
                        break;
                }
                transaction.commit();
                activityMain.closeDrawers();
                return true;
            }
        });
    }


    /**
     * 这个是单一的Fragment，可以不用每次都新建
     *
     * @param transaction
     * @param type
     */
    private void changeToWeal(FragmentTransaction transaction, int type) {
        setTitleThis(type);
        if (wealFragment == null) {
            wealFragment = new WealFragment();
            if (!wealFragment.isAdded()) {
                transaction.add(R.id.content_fl, wealFragment);
            }
        } else {
            transaction.show(wealFragment);
        }
    }

    /**
     * 这个是单一的Fragment，可以不用每次都新建
     *
     * @param transaction
     * @param type
     */
    private void changeToIos(FragmentTransaction transaction, int type, int showType) {
        setTitleThis(type);
        if (iosFragment == null) {
            iosFragment = AndroidFragment.newInstance(showType);
            if (!iosFragment.isAdded()) {
                transaction.add(R.id.content_fl, iosFragment);
            }
        } else {
            transaction.show(iosFragment);
        }
    }

    /**
     * 这个是单一的Fragment，可以不用每次都新建
     *
     * @param transaction
     * @param type
     */
    private void changeToRestVideo(FragmentTransaction transaction, int type, int showType) {
        setTitleThis(type);
        if (restVideoFragment == null) {
            restVideoFragment = AndroidFragment.newInstance(showType);
            if (!restVideoFragment.isAdded()) {
                transaction.add(R.id.content_fl, restVideoFragment);
            }
        } else {
            transaction.show(restVideoFragment);
        }
    }

    /**
     * 这个是公共的Fragment，需要根据每次的类型获取
     *
     * @param transaction
     * @param type
     * @param showType
     */
    private void changeToCommon(FragmentTransaction transaction, int type, int showType) {
        setTitleThis(type);
        androidFragment = AndroidFragment.newInstance(showType);
        transaction.add(R.id.content_fl, androidFragment);
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (wealFragment != null && !wealFragment.isHidden()) {
            transaction.hide(wealFragment);
        }
        if (androidFragment != null && !androidFragment.isHidden()) {
            transaction.hide(androidFragment);
        }
        if (iosFragment != null && !iosFragment.isHidden()) {
            transaction.hide(iosFragment);
        }
        if (restVideoFragment != null && !restVideoFragment.isHidden()) {
            transaction.hide(restVideoFragment);
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
            case R.id.change_style_item:
                startActivity(new Intent(context, Main2Activity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
