package sean.com.gankio.controller;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sean.com.gankio.adapter.GankIoModel;

/**
 * Created by Sean on 2017/3/24.
 */

public class GankController {


    private static GankController instance;

    public static synchronized GankController getInstance() {
        if (null == instance) {
            instance = new GankController();
        }
        return instance;
    }


    public List<GankIoModel> getGankIoModels(String string) {
        Log.d("getGankIoModels", "getGankIoModels: "+string);
        List<GankIoModel> gankIoModels = new ArrayList<>();
        JSONObject object = JSON.parseObject(string);
        if (object.getBoolean("error")) {
            return gankIoModels;
        }

        JSONArray gankIoArr = object.getJSONArray("results");
        for (Object obj : gankIoArr) {
            JSONObject gankIoObj = JSON.parseObject(obj.toString());

            GankIoModel gankIoModel = new GankIoModel();
            gankIoModel.setUrl(gankIoObj.getString("url"));
            gankIoModel.set_id(gankIoObj.getString("_id"));
            gankIoModel.setCreatedAt(gankIoObj.getString("createAt"));
            gankIoModel.setPublishedAt(gankIoObj.getString("publishedAt"));
            gankIoModel.setDesc(gankIoObj.getString("desc"));
            gankIoModel.setType(gankIoObj.getString("type"));
            if (gankIoObj.getString("images") != null) {
//                String[] imageArr = gankIoObj.getString("images").split(",");
                JSONArray jsonArray = JSON.parseArray(gankIoObj.getString("images"));
                gankIoModel.setImages(jsonArray.get(0).toString());
//                gankIoModel.setImages(imageArr[0]);
                Log.d("getGankIoModels", "getGankIoModels: "+gankIoModel.getImages());
            }
//            Log.d("getGankIoModels", "image: "+gankIoModel.getImages());
            gankIoModel.setUsed(gankIoObj.getBoolean("used"));
            gankIoModel.setWho(gankIoObj.getString("who"));
            gankIoModels.add(gankIoModel);
        }
        return gankIoModels;
    }


}
