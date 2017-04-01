package sean.com.gankio.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.Timer;
import java.util.TimerTask;

import sean.com.gankio.BaseAtivity;
import sean.com.gankio.R;

public class WebViewActivity extends BaseAtivity {

    private static final String TAG = "WebViewActivity";

    private String title;
    private String url;
    private WebView webView;
    private AVLoadingIndicatorView webViewLoadingAnimation;
    private boolean isOnPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_web_view);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        setTitle(title);
        setBackArrow();
        getCommonTitle().setSelected(true);
        webView = (WebView) findViewById(R.id.web_view);
        webViewLoadingAnimation = (AVLoadingIndicatorView) findViewById(R.id.web_view_loading_animation);
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webViewLoadingAnimation.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });
    }


    /**
     * 当Activity执行onPause()时让WebView执行pause
     */
    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (webView != null) {
                webView.getClass().getMethod("onPause").invoke(webView, (Object[]) null);
                isOnPause = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当Activity执行onResume()时让WebView执行resume
     */
    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (isOnPause) {
                if (webView != null) {
                    webView.getClass().getMethod("onResume").invoke(webView, (Object[]) null);
                }
                isOnPause = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 该处的处理尤为重要:
     * 应该在内置缩放控件消失以后,再执行mWebView.destroy()
     * 否则报错WindowLeaked
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.getSettings().setBuiltInZoomControls(true);
            webView.setVisibility(View.GONE);
            long delayTime = ViewConfiguration.getZoomControlsTimeout();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    webView.destroy();
                    webView = null;
                }
            }, delayTime);

        }
        isOnPause = false;
    }


//    //销毁之前重新加载空网页，然后销毁
//    @Override
//    protected void onDestroy() {
//        if (webView != null) {
//            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
//            webView.clearHistory();
//
//            ((ViewGroup) webView.getParent()).removeView(webView);
//            webView.destroy();
//            webView = null;
//        }
//        super.onDestroy();
//    }
}
