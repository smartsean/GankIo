package sean.com.gankio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import sean.com.gankio.common.CommonConfig;

/**
 * Created by Sean on 2017/4/25.
 */

public class IndexActivity extends AppCompatActivity {
    protected long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > CommonConfig.EXIT_WAIT_TIME) {
                Toast.makeText(IndexActivity.this, getResources().getString(R.string.edit_toast), Toast.LENGTH_SHORT).show();
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
