package com.bnrc.fireflies.net.callBack;

import android.os.Handler;
import android.util.Log;

import com.bnrc.fireflies.app.ConfigType;
import com.bnrc.fireflies.app.Fireflies;
import com.bnrc.fireflies.net.RestCreator;
import com.bnrc.fireflies.ui.loader.FirefliesLoader;
import com.bnrc.fireflies.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by apple on 2018/4/9.
 */

public final class RequestCallbacks implements Callback<String> {

    private static final String TAG = "RequestCallbacks";

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = Fireflies.getHandler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        onRequestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        onRequestFinish();
    }

    private void onRequestFinish() {
        final long delayed = Fireflies.getConfiguration(ConfigType.LOADER_DELAYED);
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestCreator.getParams().clear();
                    FirefliesLoader.stopLoading();
                }
            }, delayed);
        }
    }
}
