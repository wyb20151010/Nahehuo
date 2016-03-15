package app.nahehuo.com.network;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.callback.Callback;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/7.
 */
public class GsonCallBack<T> extends Callback<T> {

    private Class<T> mClass;
    private Gson mGson;


    public GsonCallBack(Class<T> aClass) {
        mClass = aClass;
        mGson = new Gson();
    }


    @Override public T parseNetworkResponse(Response response)
            throws IOException {

        return mGson.fromJson(response.body().string(), mClass);
    }


    @Override public void onError(Request request, Exception e) {

    }


    @Override public void onResponse(T response) {

    }
}
