package app.nahehuo.com.network;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.callback.Callback;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/7.
 */
public class JsonObjectCallback extends Callback<String>{

    @Override public String parseNetworkResponse(Response response)
            throws IOException {
        return response.body().string();
    }


    @Override public void onError(Request request, Exception e) {

    }


    @Override public void onResponse(String response) {

    }
}
