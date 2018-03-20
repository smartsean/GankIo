package sean.com.gankio.utils;

/**
 * Created by Sean on 17/3/25.
 */

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 跟网络相关的工具类
 * Created by css on 2016/9/28.
 */
public class NetUtils {
    private NetUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static ConnectivityManager connectivityManager;

    public static NetUtils instance;

    public static NetUtils getInstance(Context context) {
        if (null == instance) {
            instance = new NetUtils(context);
        }
        return instance;
    }

    public NetUtils(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 判断网络类型
     *
     * @return
     */
    public static String getNetWorkType() {
        NetworkInfo mNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            if (ConnectivityManager.TYPE_WIFI == mNetworkInfo.getType())
                return "WIFI";
            if (ConnectivityManager.TYPE_MOBILE == mNetworkInfo.getType()) {
                switch (mNetworkInfo.getSubtype()) {
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                        return "HSUPA";
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                        return "UMTS";
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                        return "GPRS";
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                        return "EDGE";
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                        return "CDMA";
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        return "EVDO_0";
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                        return "HSPA";
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                        return "HSDPA";
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        return "EVDO_A";
                    default:
                        return "unknown";
                }
            }
        }
        return "unknown";
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {

        Intent intent;
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        activity.startActivity(intent);
    }


}