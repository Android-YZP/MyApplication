package com.com1075.myapplication;

import com.com1075.library.base.BaseActivity;
import com.com1075.library.base.BasePresenter;
import com.com1075.library.http.exception.ApiException;
import com.com1075.library.http.observer.HttpRxObservable;
import com.com1075.library.http.observer.HttpRxObserver;
import com.com1075.library.http.retrofit.HttpRequest;
import com.com1075.library.utils.StringUtils;
import com.com1075.myapplication.Api.ApiUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by 姚中平 on 2018/1/11.
 */

public class TestPresenter extends BasePresenter<ITestContract, BaseActivity> {
    private final String TAG = TestPresenter.class.getSimpleName();

    public TestPresenter(ITestContract view, BaseActivity activity) {
        super(view, activity);
    }

    /**
     * 获取信息
     *
     * @author 姚中平
     */
    public void HttpLogin(String phone) {

        //构建请求数据
        Map<String, Object> request = HttpRequest.getRequest();
        request.put("phone", "17625017026");
        request.put("password", StringUtils.md5("qqqqqq" + "17625017026".substring(0, 5)));

        HttpRxObserver httpRxObserver = new HttpRxObserver(TAG + "getInfo") {

            @Override
            protected void onStart(Disposable d) {
                if (getView() != null)
                    getView().LoadingData();
            }

            @Override
            protected void onError(ApiException e) {
                // 给当前打印的换一个单独的tag名
                if (getView() != null) {
                    getView().LoadingDataFail(e.getMsg());
                }
            }

            @Override
            protected void onSuccess(Object response) {
                Logger.json(response + "");
                HttpRequest bean = new Gson().fromJson(response.toString(), HttpRequest.class);
                if (getView() != null) {
                    getView().LoadingDataSuccess(response.toString());
                }
            }
        };

        HttpRxObservable.getObservable(ApiUtils.getTestApi().login(request), getActivity()).subscribe(httpRxObserver);
    }


}
