package sean.com.gankio.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.wang.avi.AVLoadingIndicatorView;

import sean.com.gankio.BaseAtivity;
import sean.com.gankio.R;

public class WebViewActivity extends BaseAtivity {

    private static final String TAG = "WebViewActivity";

    private String title;
    private String url;
    private WebView webView;
    private AVLoadingIndicatorView webViewLoadingAnimation;

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
        FrameLayout frameLayout = (FrameLayout) findViewById(android.R.id.content);
//        LinearLayout relativeLayout = (LinearLayout) frameLayout.getChildAt(0);
//        RelativeLayout relativeLayout1 = (RelativeLayout) relativeLayout.getChildAt();
//        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_web_view);
//        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(WebViewActivity.this);
//        avLoadingIndicatorView.setIndicator("BallPulseIndicator");
//        avLoadingIndicatorView.setIndicator(new BallPulseIndicator());
//        avLoadingIndicatorView.setIndicatorColor(R.color.colorPrimary);
//        relativeLayout.addView(avLoadingIndicatorView);
//        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) avLoadingIndicatorView.getLayoutParams();
//        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
//        avLoadingIndicatorView.setLayoutParams(lp);
//        avLoadingIndicatorView.setIndicatorColor(R.color.colorPrimary);
        webViewLoadingAnimation = (AVLoadingIndicatorView) findViewById(R.id.web_view_loading_animation);
        WebSettings webSettings = webView.getSettings();
//        webSettings.setSupportZoom(true);
//        webSettings.setDisplayZoomControls(true);
//        webSettings.setBuiltInZoomControls(true);
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

    @Override
    protected void onResume() {
        super.onResume();
        resumeWebView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseWebView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyWebView();
    }

    private void destroyWebView() {
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }


    private void pauseWebView() {
        if (webView == null) {
            return;
        }
        webView.onPause();
    }

    private void resumeWebView() {
        if (webView == null) {
            return;
        }
        webView.onResume();
    }


}
