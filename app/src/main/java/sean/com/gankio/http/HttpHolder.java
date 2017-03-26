package sean.com.gankio.http;

import android.content.Context;

import com.zhj.syringe.core.BaseHttpHolder;
import com.zhj.syringe.core.service.BaseServiceManager;

/**
 * Created by Sean on 2017/3/23.
 */

public class HttpHolder extends BaseHttpHolder {


    public HttpHolder(BaseServiceManager baseServiceManager) {
        super(baseServiceManager);
    }

    public static final class PostBuilder extends BasePostBuilder {
        private Context context;

        public PostBuilder(BaseHttpHolder holder, Context context) {
            super(holder);
            this.context = context;
        }

        @Override
        public void post() {
//            if (!NetUtils.getInstance(context).isConnected(context)) {
//                return;
//                Log.d(TAG, "post: ");
//                Toast.makeText(context, "網絡不可用", Toast.LENGTH_SHORT).show();
//            }else {
                super.post();
//            }

        }
    }
}
