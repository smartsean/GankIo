package sean.com.gankio.utils;

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
}
