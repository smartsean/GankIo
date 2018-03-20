package sean.com.gankio.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import sean.com.gankio.R;

/**
 * Created by Sean on 2017/4/1.
 */

public class ImageLoader {

    private static ImageLoader mInstance;

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }


    public void loadNormal(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .error(R.drawable.error_image)
                .into(imageView);
    }
}
