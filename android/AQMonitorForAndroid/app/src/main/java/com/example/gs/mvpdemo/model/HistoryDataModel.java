package com.example.gs.mvpdemo.model;

import android.support.annotation.NonNull;

import com.example.gs.mvpdemo.ProApplication;
import com.example.gs.mvpdemo.base.BaseModel;
import com.example.gs.mvpdemo.bean.HistoryData;
import com.example.gs.mvpdemo.bean.LoginBean;
import com.example.gs.mvpdemo.bean.PageUtils;
import com.example.gs.mvpdemo.exception.ApiException;
import com.example.gs.mvpdemo.subscriber.CommonSubscriber;
import com.example.gs.mvpdemo.transformer.CommonTransformer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.internal.LinkedTreeMap;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryDataModel extends BaseModel {
    public PageUtils result;

    protected List<HistoryData> convert(List<LinkedTreeMap> list)
    {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }).create();

        List<HistoryData> outList = new ArrayList<HistoryData>();
        for(int i = 0; i < list.size(); i++)
        {
            String jsonString = gson.toJson(list.get(i));
            HistoryData bean = gson.fromJson(jsonString,HistoryData.class);
            outList.add(bean);
        }
        return outList;
    }

    public PageUtils getData(@NonNull final HistoryDataModel.DataHint
            dataHint) {


        httpService.getHistoryData()
                .compose(new CommonTransformer<PageUtils>())
                .subscribe(new CommonSubscriber<PageUtils>(ProApplication.getmContext()) {
                    @Override
                    public void onNext(PageUtils data) {
                        result = data;
                        List<HistoryData> list = convert((List<LinkedTreeMap>)data.getList());
                        dataHint.useableData(list);
                    }

                    @Override
                    protected void onError(ApiException e) {
                        super.onError(e);
                        result = null;
                        dataHint.failInfo(e.getMessage());
                    }
                });
        return result;
    }

    //通过接口产生信息回调
    public interface DataHint {
        void useableData(List<HistoryData> list);

        void failInfo(String str);

    }
}
