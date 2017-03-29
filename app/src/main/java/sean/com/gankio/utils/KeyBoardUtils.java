package sean.com.gankio.utils;

import android.content.Context;
import android.os.Handler;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Sean on 2017/3/29.
 */

public class KeyBoardUtils {

    /**
     * 自动打开软键盘
     *
     * @auther css
     * created at 2016/4/23 21:58
     */
    public static void openKeyboard(Handler mHandler, int s, final Context context) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, s);
    }

    /**
     * 关闭软键盘
     *
     * @param mContext 上下文
     */
    public static void closeKeybord(Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(null, 0);
    }
}