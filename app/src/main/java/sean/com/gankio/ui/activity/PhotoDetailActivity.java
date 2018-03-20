package sean.com.gankio.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sean.com.gankio.BaseAtivity;
import sean.com.gankio.R;

public class PhotoDetailActivity extends BaseAtivity {

    private static final String TAG = "PhotoDetailActivity";

    @BindView(R.id.image_detail_pv)
    PhotoView imageDetailPv;
    @BindView(R.id.image_loading_animation)
    AVLoadingIndicatorView imageLoadingAnimation;

    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_photo_detail);
        ButterKnife.bind(this);
        setTitle("图片详情");
        setBackArrow();
        imageUrl = getIntent().getStringExtra("url");
        Glide.with(this).load(imageUrl)
                .thumbnail(0.5f)
                .error(R.drawable.error_image)
                .listener(glideRequestListener)
                .crossFade()
                .into(imageDetailPv);
    }

    /**
     * Glide的监听
     */
    private RequestListener<String, GlideDrawable> glideRequestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            Toast.makeText(PhotoDetailActivity.this, "图片加载失败，请稍后再试。。。", Toast.LENGTH_SHORT).show();
            loadCompleted();
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            loadCompleted();
            return false;
        }
    };

    /**
     * 图片加载完成隐藏loading和显示图片
     */
    private void loadCompleted() {
        imageDetailPv.setVisibility(View.VISIBLE);
        imageLoadingAnimation.setVisibility(View.GONE);
    }

}
