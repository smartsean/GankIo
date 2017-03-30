package sean.com.gankio;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import sean.com.gankio.ui.fragment.AndroidFragment;
import sean.com.gankio.ui.fragment.WealFragment;
import sean.com.gankio.utils.KeyBoardUtils;
import sean.com.gankio.utils.ScreenUtils;

import static sean.com.gankio.R.id.weal;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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
    private AndroidFragment allFragment;
    private AndroidFragment restVideoFragment;
    private AndroidFragment expandFragment;
    private AndroidFragment foreEndFragment;

    /**
     * 用来搜索的fragment
     */
    private AndroidFragment searchOtherFragment;
    private WealFragment searchWealFragment;


    private Context context;
    private FragmentManager fragmentManager;
    private PopupWindow popupWindow;

    private String selectType;

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
            iosFragment = AndroidFragment.newInstance(showType, null);
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
            restVideoFragment = AndroidFragment.newInstance(showType, null);
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
        androidFragment = AndroidFragment.newInstance(showType, null);
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
        if (searchWealFragment != null && !searchWealFragment.isHidden()) {
            transaction.hide(searchWealFragment);
        }
        if (searchOtherFragment != null && !searchOtherFragment.isHidden()) {
            transaction.hide(searchOtherFragment);
        }
    }

    private void init() {
        setSupportActionBar(commonTitleTb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            commonTitleTb.setNavigationIcon(R.drawable.common_menu_ic);
        }
    }


    private void setTitleThis(int res) {
        commonTitleTv.setText(res);
    }

    private void setTitleThis(String title) {
        commonTitleTv.setText(title);
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
            case R.id.search_item:
                getPopupWindow();
                // 设置相对View的偏移，1、相对的view，2、相对view的x方向偏移，3、相对view的y方向偏移
                popupWindow.showAsDropDown(new View(this), 0, ScreenUtils.getStatusHeight(MainActivity.this));
                //打开软键盘
                KeyBoardUtils.openKeyboard(new Handler(), 0, context);
                break;
            case R.id.change_style_item:
                startActivity(new Intent(context, Main2Activity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 初始化PopupWindow
     */
    protected void initPopupWindow() {
        String searchContent;
        final View view = getLayoutInflater().inflate(R.layout.search_popup, null, false);
        int height = commonTitleTb.getHeight();
        final EditText searchEt = (EditText) view.findViewById(R.id.search_et);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, height, true);
        final TextView selectTypeTv = (TextView) view.findViewById(R.id.select_type_tv);
        final RelativeLayout selectTypeRl = (RelativeLayout) view.findViewById(R.id.select_type_rl);
        popupWindow.setFocusable(true);//设置外部点击取消
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.AnimBottom);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });

        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (TextUtils.isEmpty(searchEt.getText().toString())) {
                        return false;
                    } else {
                        getSearchData(selectType, searchEt.getText().toString());
                    }
                    popupWindow.dismiss();
                }
                return false;
            }
        });
        selectTypeRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(context)
                        .title("请选择要搜索的类型")
                        .items(R.array.types)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                selectTypeTv.setText(text);
                                selectType = text.toString();
                                KeyBoardUtils.openKeyboard(new Handler(), 300, context);
                            }
                        })
                        .show();
            }
        });

        view.findViewById(R.id.search_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSearchData(selectType, searchEt.getText().toString());
                popupWindow.dismiss();
            }
        });
        // PopupWindow的消失事件监听，消失的时候，关闭软键盘
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                KeyBoardUtils.closeKeybord(context);
            }
        });
    }

    private void getSearchData(String searchType, String content) {
        searchType = TextUtils.isEmpty(searchType) ? getResourcesString(R.string.all_text) : searchType;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        if (searchType.equals(getString(R.string.weal_string))) {
            changeToSearchWeal(transaction, R.string.weal_string, content);
        } else {
            changeToSearchCommon(transaction, searchType, content);
        }
    }

    /**
     * 这个是单一的Fragment，可以不用每次都新建
     *
     * @param transaction
     * @param type
     */
    private void changeToSearchWeal(FragmentTransaction transaction, int type, String content) {
        setTitleThis(type);
        if (searchWealFragment == null) {
            searchWealFragment = WealFragment.newInstance(content);
            if (!searchWealFragment.isAdded()) {
                transaction.add(R.id.content_fl, searchWealFragment);
            }
        } else {
            transaction.show(searchWealFragment);
        }
        transaction.commit();
    }

    /**
     * 这个是公共的Fragment，需要根据每次的类型获取
     *
     * @param transaction
     * @param type
     */
    private void changeToSearchCommon(FragmentTransaction transaction, String type, String content) {
        setTitleThis(type);
        int searchType = AndroidFragment.ALL_KEY;
        if (type.equals(getResourcesString(R.string.all_text))) {
            searchType = AndroidFragment.ALL_KEY;
        } else if (type.equals(getResourcesString(R.string.android_text))) {
            searchType = AndroidFragment.ANDROID_KEY;
        } else if (type.equals(getResourcesString(R.string.ios))) {
            searchType = AndroidFragment.IOS_KEY;
        } else if (type == getResourcesString(R.string.app_text)) {
            searchType = AndroidFragment.APP_KEY;
        } else if (type == getResourcesString(R.string.rest_video_text)) {
            searchType = AndroidFragment.REST_VIDEO_KEY;
        } else if (type == getResourcesString(R.string.expand_text)) {
            searchType = AndroidFragment.EXPANSION_KEY;
        } else if (type == getResourcesString(R.string.fore_end_text)) {
            searchType = AndroidFragment.FORE_END_KEY;
        }
        androidFragment = AndroidFragment.newInstance(searchType, content);
        transaction.add(R.id.content_fl, androidFragment);
        transaction.commit();
    }

    private String getResourcesString(int id) {
        return getResources().getString(id);
    }


    /**
     * 获取PopipWinsow实例
     */
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopupWindow();
        }
    }
}
