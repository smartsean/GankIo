package sean.com.gankio.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sean.com.gankio.R;
import sean.com.gankio.ui.fragment.AndroidFragment;
import sean.com.gankio.ui.fragment.WealFragment;

/**
 * Created by Sean on 2017/3/28.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "ViewPagerAdapter";

    private static final int PAGE_COUNT = 7;
    private Context context;
    private List<Fragment> fragments;
    private List<String> titles;

    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        fragments = getFragments();
        titles = getTitles();
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: position" + position);
        switch (position) {
            case 0:
                Log.d(TAG, "getItem: weal");
                return fragments.get(0);
            case 1:
                Log.d(TAG, "getItem: ANDROID_KEY");
                return fragments.get(1);
            case 2:
                Log.d(TAG, "getItem: IOS_KEY");
                return fragments.get(2);
            case 3:
                return fragments.get(3);
            case 4:
                return fragments.get(4);
            case 5:
                return fragments.get(5);
            case 6:
                return fragments.get(6);
        }
        Log.d(TAG, "getItem: ");
        return null;
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new WealFragment());
        fragments.add(AndroidFragment.newInstance(AndroidFragment.ANDROID_KEY));
        fragments.add(AndroidFragment.newInstance(AndroidFragment.IOS_KEY));
        fragments.add(AndroidFragment.newInstance(AndroidFragment.APP_KEY));
        fragments.add(AndroidFragment.newInstance(AndroidFragment.REST_VIDEO_KEY));
        fragments.add(AndroidFragment.newInstance(AndroidFragment.EXPANSION_KEY));
        fragments.add(AndroidFragment.newInstance(AndroidFragment.FORE_END_KEY));
        return fragments;
    }

    private List<String> getTitles() {
        List<String> titles = new ArrayList<>();
        titles.add(context.getString(R.string.weal_string));
        titles.add(context.getString(R.string.android_text));
        titles.add(context.getString(R.string.ios));
        titles.add(context.getString(R.string.app_text));
        titles.add(context.getString(R.string.rest_video_text));
        titles.add(context.getString(R.string.expand_text));
        titles.add(context.getString(R.string.fore_end_text));
        return titles;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (titles != null && titles.size() > 0) {
            title = titles.get(position);
        }
        return title;
    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        ((ViewPager) container).removeView(fragments.get(position));
//    }


//    @Override
//    public Fragment getItem(int position) {
//        int showType;
//        switch (position) {
//            case 0:
//                Log.d(TAG, "getItem: weal");
//                return new WealFragment();
//            case 1:
//                Log.d(TAG, "getItem: ANDROID_KEY");
//                showType = AndroidFragment.ANDROID_KEY;
//                return AndroidFragment.newInstance(showType);
//            case 2:
//                Log.d(TAG, "getItem: IOS_KEY");
//                showType = AndroidFragment.IOS_KEY;
//                return IosFragment.newInstance(showType);
//            case 3:
//                showType = AndroidFragment.APP_KEY;
//                return AndroidFragment.newInstance(showType);
//            case 4:
//                showType = AndroidFragment.REST_VIDEO_KEY;
//                return AndroidFragment.newInstance(showType);
//            case 5:
//                showType = AndroidFragment.EXPANSION_KEY;
//                return AndroidFragment.newInstance(showType);
//            case 6:
//                showType = AndroidFragment.FORE_END_KEY;
//                return AndroidFragment.newInstance(showType);
//        }
//        Log.d(TAG, "getItem: ");
//        return new WealFragment();
//    }


//    @Override
//    public CharSequence getPageTitle(int position) {
//        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_menu_camera);
//        String title = "福利";
//        switch (position) {
//            case 0:
//                drawable = ContextCompat.getDrawable(context, R.drawable.ic_menu_camera);
//                title = "福利";
//                break;
//            case 1:
//                drawable = ContextCompat.getDrawable(context, R.mipmap.android);
//                title = "安卓";
//                break;
//            case 2:
//                drawable = ContextCompat.getDrawable(context, R.drawable.ic_menu_camera);
//                title = "IOS";
//                break;
//            case 3:
//                drawable = ContextCompat.getDrawable(context, R.drawable.ic_menu_camera);
//                title = "App";
//                break;
//            case 4:
//                drawable = ContextCompat.getDrawable(context, R.drawable.ic_menu_camera);
//                title = "视频";
//                break;
//            case 5:
//                drawable = ContextCompat.getDrawable(context, R.drawable.ic_menu_camera);
//                title = "扩展";
//                break;
//            case 6:
//                drawable = ContextCompat.getDrawable(context, R.drawable.ic_menu_camera);
//                title = "前端";
//                break;
//        }
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
//        SpannableString spannableString = new SpannableString(" " + title);
//        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        return spannableString;
//    }
}
