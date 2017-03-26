package sean.com.gankio.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import sean.com.gankio.BaseAtivity;
import sean.com.gankio.R;

public class PhotoDetailActivity extends BaseAtivity {


    private static final String TAG = "PhotoDetailActivity";
    PhotoView imageDetailPv;

    SwipeRefreshLayout imageSrl;
    private String imageUrl ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_photo_detail);
        setTitle("图片详情");
        setBackArrow();
        imageDetailPv = (PhotoView) findViewById(R.id.image_detail_pv);
        Log.d(TAG, "onCreate: "+getIntent().getStringExtra("url"));
        imageUrl = getIntent().getStringExtra("url");
        Glide.with(this).load(imageUrl)
                .error(R.drawable.no_image)
                .into(imageDetailPv);
        imageSrl = (SwipeRefreshLayout) findViewById(R.id.image_srl);
        imageSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Glide.with(PhotoDetailActivity.this).load(imageUrl)
                        .placeholder(R.drawable.i)
                        .error(R.drawable.no_image)
                        .into(imageDetailPv);
                imageDetailPv.setImageResource(R.drawable.ic_menu_send);
                imageSrl.setRefreshing(false);
            }
        });
    }
}
