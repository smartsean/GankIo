package sean.com.gankio.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhj.syringe.core.response.BaseHttpSubscriber;
import com.zhj.syringe.core.response.HttpBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sean.com.gankio.BaseSupportFragment;
import sean.com.gankio.R;
import sean.com.gankio.adapter.GankIoModel;
import sean.com.gankio.adapter.WealAdapter;
import sean.com.gankio.controller.GankController;
import sean.com.gankio.http.Client;
import sean.com.gankio.http.HttpHolder;
import sean.com.gankio.http.service.IServiceType;
import sean.com.gankio.http.service.RequestParam;

public class WealFragment extends BaseSupportFragment {

    private static final String TAG = "WealFragment";

    @BindView(R.id.weal_rv)
    RecyclerView wealRv;
    @BindView(R.id.weal_srl)
    SwipeRefreshLayout wealSrl;
    Unbinder unbinder;

    private Context context;
    private WealAdapter wealAdapter;
    private static final int PRELOAD_SIZE = 6;
    private boolean mIsFirstTimeTouchBottom = true;
    private int page = 1;
    private List<GankIoModel> gankIoModels;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weal, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getActivity();
        initView();
        gankIoModels = new ArrayList<>();
        StaggeredGridLayoutManager sglm;
        sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        wealAdapter = new WealAdapter(context, gankIoModels);
        wealRv.setLayoutManager(sglm);
        wealRv.setAdapter(wealAdapter);
        wealRv.addOnScrollListener(getOnBottomListener(sglm));

        getWealData(page);
        return view;
    }

    private void initView() {
        wealSrl.setColorSchemeResources(R.color.colorPrimary);
        wealSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                gankIoModels.clear();
                Log.d(TAG, "onRefresh: ");
                getWealData(page);
            }
        });
    }

    /**
     * 获取数据
     */
    private void getWealData(final int page) {
        new HttpHolder.PostBuilder(Client.getInstance()
                .getHttpHolder(), context)
                .addRequest(RequestParam.newBuilder()
                        .path("type", "福利")
                        .path("count", "10")
                        .path("page", String.valueOf(page))
                        .service(IServiceType.CLASS).callGankIo()
                        .subscriber(new BaseHttpSubscriber() {
                            @Override
                            public void onNext(HttpBean httpBean) {
                                if (wealSrl.isRefreshing()) {
                                    wealSrl.setRefreshing(false);
                                }
                                Log.d(TAG, "onNext: " + page);
                                gankIoModels =
                                        GankController.getInstance().
                                                getGankIoModels(httpBean.
                                                        getMessage().
                                                        toString());
                                Log.d(TAG, "onNext: "+gankIoModels.toString());
                                refreshWealList(gankIoModels);
                            }
                        }).build())
                .post();
    }


    RecyclerView.OnScrollListener getOnBottomListener(final StaggeredGridLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                Log.d(TAG, "onScrolled: ");
                boolean isBottom = layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1] >=
                        wealAdapter.getItemCount() - PRELOAD_SIZE;
                if (!wealSrl.isRefreshing() && isBottom) {
                    if (!mIsFirstTimeTouchBottom) {
                        page += 1;
                        Log.d(TAG, "onScrolled: 22");
                        wealSrl.setRefreshing(true);
                        getWealData(page);
                    } else {
                        Log.d(TAG, "onScrolled: 333");
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }

    /**
     * 刷新数据
     *
     * @param gankIoModels
     */
    private void refreshWealList(List<GankIoModel> gankIoModels) {
        if (gankIoModels == null) {
            return;
        }
        wealAdapter.addGankIoModels(gankIoModels);
        wealAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
