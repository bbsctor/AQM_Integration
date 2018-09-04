package com.example.gs.mvpdemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bin.david.form.core.SmartTable;
import com.example.gs.mvpdemo.R;
import com.example.gs.mvpdemo.base.BaseActivity;
import com.example.gs.mvpdemo.bean.HistoryData;
import com.example.gs.mvpdemo.contract.HistoryContract;
import com.example.gs.mvpdemo.contract.LoginContract;
import com.example.gs.mvpdemo.presenter.HistoryPresenter;
import com.example.gs.mvpdemo.presenter.LoginPresenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HistoryActivity extends BaseActivity<HistoryPresenter> implements HistoryContract.HistoryView {

    //@InjectView(R.id.table)
    //SmartTable table;

    private TableLayout tableLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //table.getConfig().setContentStyle(new com.bin.david.form.data.style.FontStyle(50, android.graphics.Color.BLUE));


    }

    @Override
    protected HistoryPresenter loadPresenter() {
        return new HistoryPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getHistoryData();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        ButterKnife.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    protected void otherViewClick(View view) {

    }

    @Override
    public void displayHistory(List<HistoryData> list)
    {
        //table.setData(list);

        //获取控件tableLayout
        tableLayout = (TableLayout)findViewById(R.id.table1);
        //清除表格所有行
        tableLayout.removeAllViews();
        //全部列自动填充空白处
        tableLayout.setStretchAllColumns(true);
        //生成X行，Y列的表格
        for(int i=1;i<=5;i++)
        {
            TableRow tableRow=new TableRow(HistoryActivity.this);
            for(int j=1;j<=5;j++)
            {
                //tv用于显示
                TextView tv=new TextView(HistoryActivity.this);
                //Button bt=new Button(MainActivity.this);
                //tv.setText("("+i+","+j+")");
                tv.setText("("+(list.get(i)).getAtmPressure()+")");

                tableRow.addView(tv);
            }
            //新建的TableRow添加到TableLayout

            tableLayout.addView(tableRow, new TableLayout.LayoutParams(4, 6,1));

        }
    }
}
