package sean.com.gankio.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import sean.com.gankio.R;
import sean.com.gankio.ui.activity.PhotoDetailActivity;

/**
 * Created by Sean on 17/3/25.
 */

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.CommonViewHolder> {


    private Context context;
    private List<GankIoModel> gankIoModels;

    public List<GankIoModel> getGankIoModels() {
        return gankIoModels;
    }

    public void setGankIoModels(List<GankIoModel> gankIoModels) {
        this.gankIoModels = gankIoModels;
    }

    public void addGankIoModels(List<GankIoModel> gankIoModels) {
        this.gankIoModels.addAll(gankIoModels);
    }

    public CommonAdapter(Context context, List<GankIoModel> gankIoModels) {
        this.context = context;
        this.gankIoModels = gankIoModels;
    }

    onItemClickImp onItemClickImp;

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_item, parent, false);
        CommonViewHolder commonViewHolder = new CommonViewHolder(view);
        return commonViewHolder;
    }

    public interface onItemClickImp {
        void setOnItemClick();
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        final GankIoModel bean = gankIoModels.get(position);
        holder.authorTv.setText(TextUtils.isEmpty(bean.getWho()) ? "作者未知" : bean.getWho());
        holder.publishTv.setText(TextUtils.isEmpty(bean.getPublishedAt()) ? "111" : bean.getPublishedAt().substring(0, 10));
        holder.descTv.setText(TextUtils.isEmpty(bean.getDesc()) ? "作者未知" : bean.getDesc());
        Glide.with(context).load(bean.getImages()).error(R.drawable.no_image).into(holder.imagesIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new WebViewActivity());
            }
        });
        holder.imagesIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PhotoDetailActivity.class).putExtra("url",bean.getImages()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (gankIoModels != null && !gankIoModels.isEmpty()) ?
                gankIoModels.size() : 0;
    }

    class CommonViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        ImageView imagesIv;
        TextView authorTv;
        TextView publishTv;
        TextView descTv;


        public CommonViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imagesIv = (ImageView) itemView.findViewById(R.id.images_iv);
            authorTv = (TextView) itemView.findViewById(R.id.author_tv);
            publishTv = (TextView) itemView.findViewById(R.id.publish_time_tv);
            descTv = (TextView) itemView.findViewById(R.id.desc_tv);
        }
    }


    /**
     * 底部加载更多
     */
    class FooterViewHolder extends RecyclerView.ViewHolder {
        AVLoadingIndicatorView footLoadingView;

        public FooterViewHolder(View itemView) {
            super(itemView);
            footLoadingView = (AVLoadingIndicatorView) itemView.findViewById(R.id.foot_loading_view);
        }
    }
}
