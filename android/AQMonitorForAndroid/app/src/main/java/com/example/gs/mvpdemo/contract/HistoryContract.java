package com.example.gs.mvpdemo.contract;

import com.example.gs.mvpdemo.bean.HistoryData;
import java.util.List;
/**
 * Created by GaoSheng on 2016/11/26.
 * 18:28
 *
 * @VERSION V1.4
 * com.example.gs.mvpdemo.contract
 * 契约类,定义登录用到的一些接口方法
 */

public class HistoryContract {

    public interface HistoryView {
        void displayHistory(List<HistoryData> list);
    }

    public interface HistoryPresenter {
        void getHistoryData();
    }
}
