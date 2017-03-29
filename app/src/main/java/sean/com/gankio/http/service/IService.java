package sean.com.gankio.http.service;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import syringe.Service;

/**
 * Created by Sean on 2017/3/23.
 */
@Service
public interface IService {

    @GET("data/{type}/{count}/{page}")
    Observable<String> callGankIo(@Path("type") String type, @Path("count") String count, @Path("page") String page);

    @GET("search/query/{content}/category/{type}/count/{count}/page/{page}")
    Observable<String> callGankIoForSearch(@Path("content") String content, @Path("type") String type, @Path("count") String count, @Path("page") String page);

}
