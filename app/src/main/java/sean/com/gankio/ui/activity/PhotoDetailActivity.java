package sean.com.gankio.ui.activity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import sean.com.gankio.BaseAtivity;
import sean.com.gankio.R;

public class PhotoDetailActivity extends BaseAtivity {

    PhotoView imageDetailPv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_photo_detail);
        setTitle("图片详情");
        setBackArrow();
        imageDetailPv = (PhotoView) findViewById(R.id.image_detail_pv);
        Glide.with(this).load(getIntent().getStringExtra("url"))
                .into(imageDetailPv);
    }
}
