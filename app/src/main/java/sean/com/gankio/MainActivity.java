package sean.com.gankio;

import android.os.Bundle;

public class MainActivity extends BaseAtivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);
        setTitle("我是首页");
    }
}
