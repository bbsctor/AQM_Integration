package com.example.gs.mvpdemo.presenter;

import com.example.gs.mvpdemo.activity.HistoryActivity;
import com.example.gs.mvpdemo.activity.LoginActivity;
import com.example.gs.mvpdemo.base.BasePresenter;
import com.example.gs.mvpdemo.bean.HistoryData;
import com.example.gs.mvpdemo.contract.HistoryContract;
import com.example.gs.mvpdemo.contract.LoginContract;
import com.example.gs.mvpdemo.model.HistoryDataModel;
import com.example.gs.mvpdemo.model.LoginModel;
import com.example.gs.mvpdemo.mvp.IModel;
import com.example.gs.mvpdemo.utils.LogUtils;

import java.util.HashMap;
import java.util.List;

public class HistoryPresenter extends BasePresenter<HistoryActivity> implements
        HistoryContract.HistoryPresenter
{
    public void getHistoryData() {
        ((HistoryDataModel) getiModelMap().get("history")).getData(new HistoryDataModel.DataHint() {
            @Override
            public void useableData(List<HistoryData> list) {
                getIView().displayHistory(list);  //成功
            }

            @Override
            public void failInfo(String str) {

            }
        });
    }


    @Override
    public HashMap<String, IModel> getiModelMap() {
        return loadModelMap(new HistoryDataModel());
    }

    @Override
    public HashMap<String, IModel> loadModelMap(IModel... models) {
        HashMap<String, IModel> map = new HashMap<>();
        map.put("history", models[0]);
        return map;
    }
}

