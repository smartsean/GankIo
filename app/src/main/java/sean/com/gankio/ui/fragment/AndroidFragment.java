package sean.com.gankio.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import sean.com.gankio.adapter.CommonAdapter;
import sean.com.gankio.adapter.GankIoModel;
import sean.com.gankio.common.CommonConfig;
import sean.com.gankio.controller.GankController;
import sean.com.gankio.http.Client;
import sean.com.gankio.http.HttpHolder;
import sean.com.gankio.http.service.IServiceType;
import sean.com.gankio.http.service.RequestParam;
import sean.com.gankio.utils.EndlessRecyclerOnScrollListener;


public class AndroidFragment extends BaseSupportFragment {

    private static final String TAG = "AndroidFragment";

    @BindView(R.id.common_rv)
    RecyclerView commonRv;
    @BindView(R.id.common_srl)
    SwipeRefreshLayout commonSrl;
    Unbinder unbinder;

    private Context context;
    private CommonAdapter commonAdapter;

    private int page = 1;
    private LinearLayoutManager lm;
    private String loadType;
    private final static String TYPE = "type";


    // 搜索
    private final static String SEARCH_CONTENT = "searchContent";
    private boolean isFromSearch = false;
    private String searchContent;

    public final static int ANDROID_KEY = 0X11;
    public final static int IOS_KEY = 0X12;
    public final static int APP_KEY = 0X13;
    public final static int REST_VIDEO_KEY = 0X14;
    public final static int EXPANSION_KEY = 0X15;
    public final static int FORE_END_KEY = 0X16;
    public final static int ALL_KEY = 0X17;


    public static AndroidFragment newInstance(int param1, String content) {
        AndroidFragment fragment = new AndroidFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, param1);
        args.putString(SEARCH_CONTENT, content);
        fragment.setArguments(args);
        Log.d(TAG, "newInstance: " + param1);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getActivity();
        lm = new LinearLayoutManager(context);
        initData();
        initView();
        getAndroidData();
        return view;
    }

    private void initData() {
        if (getArguments() != null) {
            int type = getArguments().getInt(TYPE);
            if (getArguments().getString(SEARCH_CONTENT) != null) {
                isFromSearch = true;
                searchContent = getArguments().getString(SEARCH_CONTENT);
            }

            switch (type) {
                case ANDROID_KEY:
                    loadType = "Android";
                    break;
                case IOS_KEY:
                    loadType = "iOS";
                    break;
                case APP_KEY:
                    loadType = "App";
                    break;
                case FORE_END_KEY:
                    loadType = "前端";
                    break;
                case EXPANSION_KEY:
                    Log.d(TAG, "onCreate: " + "拓展资源");
                    loadType = "拓展资源";
                    break;
                case REST_VIDEO_KEY:
                    Log.d(TAG, "onCreate: " + "休息视频");
                    loadType = "休息视频";
                    break;
                case ALL_KEY:
                    Log.d(TAG, "onCreate: " + "all");
                    loadType = "all";
                    break;
            }
        }
    }

    /**
     * 从网络获取要显示数据
     */
    private void getAndroidData() {
        Log.d(TAG, "getAndroidData: ");
        commonSrl.setRefreshing(true);
        if (isFromSearch) {
            new HttpHolder.PostBuilder(Client.getInstance()
                    .getHttpHolder(), context)
                    .addRequest(RequestParam.newBuilder()
                            .path("content", searchContent)
                            .path("type", loadType)
                            .path("count", String.valueOf(CommonConfig.COMMON_AOUNT))
                            .path("page", String.valueOf(page))
                            .service(IServiceType.CLASS).callGankIoForSearch()
                            .subscriber(new BaseHttpSubscriber() {
                                @Override
                                public void onNext(HttpBean httpBean) {
                                    List<GankIoModel> gankIoModels = GankController.getInstance().
                                            getGankIoWealSearchModels(httpBean.
                                                    getMessage().toString());
                                    refreshAndroidList(gankIoModels);
                                }
                            }).build())
                    .post();
        } else {
            new HttpHolder.PostBuilder(Client.getInstance()
                    .getHttpHolder(), context)
                    .addRequest(RequestParam.newBuilder()
                            .path("type", loadType)
                            .path("count", String.valueOf(CommonConfig.COMMON_AOUNT))
                            .path("page", String.valueOf(page))
                            .service(IServiceType.CLASS).callGankIo()
                            .subscriber(new BaseHttpSubscriber() {
                                @Override
                                public void onNext(HttpBean httpBean) {
                                    List<GankIoModel> gankIoModels =
                                            GankController.getInstance().
                                                    getGankIoModels(httpBean.getMessage().toString());
                                    refreshAndroidList(gankIoModels);
                                }
                            }).build())
                    .post();
        }

    }

    /**
     * 重置页面数据
     */
    private void reset() {
        page = 1;
        commonAdapter = null;
    }

    /**
     * 刷新列表
     *
     * @param gankIoModels
     */
    private void refreshAndroidList(List<GankIoModel> gankIoModels) {
        commonSrl.setRefreshing(false);
        Log.d(TAG, "page: " + page);
        if (gankIoModels == null) {
            return;
        } else {
            commonRv.setLayoutManager(lm);
            if (commonAdapter == null) {
                commonAdapter = new CommonAdapter(context, gankIoModels);
                commonRv.setAdapter(commonAdapter);

            } else {
                commonAdapter.addGankIoModels(gankIoModels);
                commonAdapter.notifyDataSetChanged();
            }

        }
    }


    /**
     * 初始化View
     */
    private void initView() {
        commonSrl.setColorSchemeResources(R.color.colorPrimary);
        commonSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reset();
                getAndroidData();
            }
        });
        commonRv.addOnScrollListener(new EndlessRecyclerOnScrollListener(lm) {
            @Override
            public void onLoadMore(int currentPage) {
                commonSrl.setRefreshing(true);
                page++;
                getAndroidData();
            }
        });

    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: " + TYPE);
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: " + TYPE);
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: " + TYPE);
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: " + TYPE);
        super.onDestroyView();
        unbinder.unbind();
    }
}
