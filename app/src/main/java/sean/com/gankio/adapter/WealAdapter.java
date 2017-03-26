package sean.com.gankio.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import sean.com.gankio.R;
import sean.com.gankio.ui.activity.PhotoDetailActivity;

import static android.content.ContentValues.TAG;

/**
 * Created by Sean on 2017/3/24.
 */

public class WealAdapter extends RecyclerView.Adapter<WealAdapter.ViewHolder> {
    private Context context;
    private List<GankIoModel> gankIoModels;

    public List<GankIoModel> getGankIoModels() {
        return gankIoModels;
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
        Log.d(TAG, "onBindViewHolder: "+bean.getUrl());
        Glide.with(context).load(bean.getUrl())
                .into(holder.wealIv);
        holder.wealCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PhotoDetailActivity.class).putExtra("url",bean.getUrl()));
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
        CardView wealCv;


        public ViewHolder(View itemView) {
            super(itemView);
            wealIv = (ImageView) itemView.findViewById(R.id.weal_iv);
            wealCv = (CardView) itemView.findViewById(R.id.weal_cv);
        }
    }
}
