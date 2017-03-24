package sean.com.gankio;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.zhj.syringe.core.response.BaseHttpSubscriber;
import com.zhj.syringe.core.response.HttpBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import sean.com.gankio.http.Client;
import sean.com.gankio.http.HttpHolder;
import sean.com.gankio.http.service.IServiceType;
import sean.com.gankio.http.service.RequestParam;
import sean.com.gankio.ui.fragment.AndroidFragment;
import sean.com.gankio.ui.fragment.AppFragment;
import sean.com.gankio.ui.fragment.ExpansionFragment;
import sean.com.gankio.ui.fragment.ForeEndFragment;
import sean.com.gankio.ui.fragment.IosFragment;
import sean.com.gankio.ui.fragment.RestVideoFragment;
import sean.com.gankio.ui.fragment.WealFragment;

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
    private IosFragment iosFragment;
    private AppFragment appFragment;
    private RestVideoFragment restVideoFragment;
    private ExpansionFragment expansionFragment;
    private ForeEndFragment foreEndFragment;


    private Context context;
    final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        initNavigationView();
        init();
    }

    private void init() {
        setSupportActionBar(commonTitleTb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            commonTitleTb.setNavigationIcon(R.mipmap.ic_menu_white_24dp);
        }
        wealFragment = new WealFragment();
        transaction.add(R.id.content_fl,wealFragment);
        transaction.commitNowAllowingStateLoss();

    }


    private void setTitleThis(int res){
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

    // 初始化initNavigationView
    private void initNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.weal);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                hideAllFragment(transaction);
                switch (item.getItemId()) {
                    case R.id.weal:
                        setTitleThis(R.string.weal_string);
                        if (wealFragment == null){
                            wealFragment = new WealFragment();
                            transaction.add(R.id.content_fl,wealFragment);
                        }else {
                            transaction.show(wealFragment);
                        }

//                        getWeal();
                        break;
                    case R.id.android:
//                        getAndroid();
                        getWeal();
                        break;
                    case R.id.ios:
                        getIos();
                        break;
                    case R.id.all:
                        getAll();
                        break;
                }
                transaction.commitNowAllowingStateLoss();
                activityMain.closeDrawers();
                return true;
            }
        });
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (wealFragment != null && !wealFragment.isHidden()){
            transaction.hide(wealFragment);
        }
    }


    /**
     * 获取福利
     */
    private void getWeal() {
        new HttpHolder.PostBuilder(Client.getInstance()
                .getHttpHolder(), context)
                .addRequest(RequestParam.newBuilder()
                        .path("type", "福利")
                        .path("count", "10")
                        .path("page", "1")
                        .service(IServiceType.CLASS).callGankIo()
                        .subscriber(new BaseHttpSubscriber() {
                            @Override
                            public void onNext(HttpBean httpBean) {
                                Log.d(TAG, "onNext: " + httpBean.getMessage().toString());
                            }
                        }).build())

                .post();
    }

    /**
     * 获取福利
     */
    private void getAndroid() {
        new HttpHolder.PostBuilder(Client.getInstance()
                .getHttpHolder(), context)
                .addRequest(RequestParam.newBuilder()
                        .path("type", "Android")
                        .path("count", "10")
                        .path("page", "1")
                        .service(IServiceType.CLASS).callGankIo()
                        .subscriber(new BaseHttpSubscriber() {
                            @Override
                            public void onNext(HttpBean httpBean) {
                                Log.d(TAG, "onNext: " + httpBean.getMessage().toString());
                            }
                        }).build())

                .post();
    }

    /**
     * 获取福利
     */
    private void getIos() {
        new HttpHolder.PostBuilder(Client.getInstance()
                .getHttpHolder(), context)
                .addRequest(RequestParam.newBuilder()
                        .path("type", "iOS")
                        .path("count", "10")
                        .path("page", "1")
                        .service(IServiceType.CLASS).callGankIo()
                        .subscriber(new BaseHttpSubscriber() {
                            @Override
                            public void onNext(HttpBean httpBean) {
                                Log.d(TAG, "onNext: " + httpBean.getMessage().toString());
                            }
                        }).build())

                .post();
    }

    /**
     * 获取福利
     */
    private void getAll() {
        new HttpHolder.PostBuilder(Client.getInstance()
                .getHttpHolder(), context)
                .addRequest(RequestParam.newBuilder()
                        .path("type", "all")
                        .path("count", "10")
                        .path("page", "1")
                        .service(IServiceType.CLASS).callGankIo()
                        .subscriber(new BaseHttpSubscriber() {
                            @Override
                            public void onNext(HttpBean httpBean) {
                                Log.d(TAG, "onNext: " + httpBean.getMessage().toString());
                            }
                        }).build())

                .post();
    }
}
