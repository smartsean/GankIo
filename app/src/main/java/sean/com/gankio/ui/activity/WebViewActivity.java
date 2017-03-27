package sean.com.gankio.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
                Log.d(TAG, "onPageFinished: ");
            }
        });
    }
}
