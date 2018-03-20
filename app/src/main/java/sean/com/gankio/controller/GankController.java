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


    /**
     * 正常读取列表拿到网站数据
     *
     * @param string
     * @return
     */
    public List<GankIoModel> getGankIoModels(String string) {
        Log.d("getGankIoModels", "getGankIoModels: " + string);
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
                JSONArray jsonArray = JSON.parseArray(gankIoObj.getString("images"));
                gankIoModel.setImages(jsonArray.get(0).toString());
            }
            if (gankIoModel.getType().equals("福利")) {
                gankIoModel.setBlankLines((int) (Math.random() * 10) % 3);
            }
            gankIoModel.setUsed(gankIoObj.getBoolean("used"));
            gankIoModel.setWho(gankIoObj.getString("who"));
            gankIoModels.add(gankIoModel);
        }
        return gankIoModels;
    }

    /**
     * 搜索福利得到的结果
     *
     * @param string
     * @return
     */
    public List<GankIoModel> getGankIoWealSearchModels(String string) {
        Log.d("getGankIoModels", "getGankIoModels: " + string);
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
            gankIoModel.setGanhuo_id(gankIoObj.getString("ganhuo_id"));
            gankIoModel.setPublishedAt(gankIoObj.getString("publishedAt"));
            gankIoModel.setReadability(gankIoObj.getString("readability"));
            gankIoModel.setType(gankIoObj.getString("type"));
            gankIoModel.setDesc(gankIoObj.getString("desc"));
            gankIoModel.setWho(gankIoObj.getString("who"));
            if (gankIoModel.getType().equals("福利")) {
                gankIoModel.setBlankLines((int) (Math.random() * 10) % 3);
            }
            gankIoModel.setImages(gankIoModel.getUrl());
            gankIoModels.add(gankIoModel);
        }
        return gankIoModels;
    }


}
