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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weal, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getActivity();
        initView();
        getWealData();
        return view;
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    private void initView() {
        wealSrl.setColorSchemeResources(R.color.colorPrimary);
        wealSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWealData();
            }
        });
    }

    /**
     * 获取数据
     */
    private void getWealData() {
        wealSrl.setRefreshing(true);
        new HttpHolder.PostBuilder(Client.getInstance()
                .getHttpHolder(), context)
                .addRequest(RequestParam.newBuilder()
                        .path("type", "福利")
                        .path("count", "10")
                        .path("page", "1")
                        .service(IServiceType.CLASS).callGankIo()
                        .subscriber(new BaseHttpSubscriber() {
                            @Override
                            public void onNext(HttpBean httpBean) {
                                if (wealSrl.isRefreshing()) {
                                    wealSrl.setRefreshing(false);
                                }
                                List<GankIoModel> gankIoModels =
                                        GankController.getInstance().
                                                getGankIoModels(httpBean.
                                                        getMessage().
                                                        toString());
                                refreshWealList(gankIoModels);
                            }
                        }).build())
                .post();
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
        StaggeredGridLayoutManager sglm =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        wealRv.setLayoutManager(sglm);
        wealAdapter = new WealAdapter(context, gankIoModels);
        wealRv.setAdapter(wealAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
