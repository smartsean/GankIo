package sean.com.gankio.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sean.com.gankio.R;
import sean.com.gankio.ui.activity.PhotoDetailActivity;
import sean.com.gankio.utils.ImageLoader;

/**
 * Created by Sean on 2017/3/24.
 */

public class WealAdapter extends RecyclerView.Adapter<WealAdapter.ViewHolder> {

    private static final String TAG = "WealAdapter";

    private Context context;

    private List<GankIoModel> gankIoModels;


    public void setGankIoModelsNone() {
        this.gankIoModels.clear();
    }

    public void addGankIoModels(List<GankIoModel> gankIoModels) {
        this.gankIoModels.addAll(gankIoModels);
    }

    public WealAdapter(Context context, List<GankIoModel> gankIoModels) {
        this.context = context;
        this.gankIoModels = gankIoModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weal_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final GankIoModel bean = gankIoModels.get(position);

        holder.card.setTag(bean.getUrl());

        if (holder.card.getTag().equals(bean.getUrl())) {
//            Glide.with(context).load(bean.getUrl())
//                    .error(R.drawable.error_image)
//                    .into(holder.wealIv);
            ImageLoader.getInstance().loadNormal(context, holder.wealIv, bean.getUrl());
//            GlideUtil.getInstance().loadImage(context,holder.wealIv,bean.getUrl(),true);
        }
        switch (bean.getBlankLines()) {
            case 0:
                holder.dateTv.setText(bean.getDesc());
                break;
            case 1:
                holder.dateTv.setText("\n" + bean.getDesc());
                break;
            case 2:
                holder.dateTv.setText("\n" + bean.getDesc() + "\n");
                break;
        }
        holder.wealCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PhotoDetailActivity.class).putExtra("url", bean.getUrl()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (gankIoModels != null && !gankIoModels.isEmpty()) ?
                gankIoModels.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView wealIv;
        TextView dateTv;
        CardView wealCv;
        View card;


        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            wealIv = (ImageView) itemView.findViewById(R.id.weal_iv);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            wealCv = (CardView) itemView.findViewById(R.id.weal_cv);
        }
    }
}
