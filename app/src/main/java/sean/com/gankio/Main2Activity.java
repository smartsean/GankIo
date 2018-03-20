package sean.com.gankio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import sean.com.gankio.adapter.ViewPagerAdapter;
import sean.com.gankio.common.CommonConfig;

public class Main2Activity extends BaseAtivity {

    private static final String TAG = "Main2Activity";

    @BindView(R.id.gank_io_tl)
    TabLayout gankIoTl;
    @BindView(R.id.gank_io_vp)
    ViewPager gankIoVp;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main2);
        ButterKnife.bind(this);
        context = this;
        setToolBar(R.id.main2_title_tb, R.id.main2_title_tv);
        setBackArrow();
        setTitle(getString(R.string.second_style));
        gankIoVp.setAdapter(new ViewPagerAdapter(context, getSupportFragmentManager()));
        // 设置缓存
        gankIoVp.setOffscreenPageLimit(7);
        gankIoTl.setupWithViewPager(gankIoVp);
        //设置可以滑动
        gankIoTl.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 跳转至指定位置
//        gankIoVp.setCurrentItem(6);


        // ViewPager切换时NestedScrollView滑动到顶部
        gankIoVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                ((NestedScrollView) findViewById(R.id.nestedScrollView)).scrollTo(0, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > CommonConfig.EXIT_WAIT_TIME) {
                Toast.makeText(Main2Activity.this, getResources().getString(R.string.edit_toast), Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                exit();
            }
            return true;
        }
        return false;
    }

    private void exit() {

        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
        finish();
    }
}
