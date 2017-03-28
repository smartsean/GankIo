package sean.com.gankio;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import sean.com.gankio.adapter.ViewPagerAdapter;

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
        gankIoVp.setOffscreenPageLimit(7);
        gankIoTl.setupWithViewPager(gankIoVp);
        //设置可以滑动
        gankIoTl.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 跳转至指定位置
//        gankIoVp.setCurrentItem(6);
    }
}
