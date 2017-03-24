package sean.com.gankio.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sean.com.gankio.adapter.WealModel;

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


    public List<WealModel> getWealModels(String string) {
        List<WealModel> wealModels = new ArrayList<>();
        JSONObject object = JSON.parseObject(string);
        if (object.getBoolean("error")) {
            return wealModels;
        }

        JSONArray wearArr = object.getJSONArray("results");
        for (Object o : wearArr) {
            JSONObject wealObj = JSON.parseObject(o.toString());

            WealModel wealModel = new WealModel();
            wealModel.setUrl(wealObj.getString("url"));
            wealModel.set_id(wealObj.getString("_id"));
            wealModel.setCreatedAt(wealObj.getString("createAt"));
            wealModel.setDesc(wealObj.getString("desc"));
            wealModel.setType(wealObj.getString("type"));
            wealModel.setUsed(wealObj.getBoolean("used"));
            wealModel.setWho(wealObj.getString("who"));
            wealModels.add(wealModel);
        }
        return wealModels;

    }
}
