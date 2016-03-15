package app.nahehuo.com.network;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.view.KeyEvent;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.util.NetworkTools;
import app.nahehuo.com.view.CustomProgressDialog;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class HttpConnectService {
    private int resultCode = 0;
    private String url = "";
    private CustomProgressDialog pDialog;


    public void connectPost(final Activity activity, final PostCallBack callBack, RequestParams params, final String message) {
        // params.setHeader("Content-Type", "text/json");

        if (!NetworkTools.isNetworkAvailable(activity)) {
            MyToast.showToast(activity,
                    activity.getResources().getString(R.string.checkNetWork));
            return;
        }

        HttpUtils http = new HttpUtils();
        // 设置返回文本的编码， 默认编码UTF-8
        // http.configResponseTextCharset("GBK");
        http.configTimeout(30000);
        http.configSoTimeout(30000);
        http.configCurrentHttpCacheExpiry(0);

        http.send(HttpRequest.HttpMethod.POST, url, params,
                new RequestCallBack<String>() {

                    @Override public void onStart() {
                        if (message != null && !message.equals("")) {
                            // pDialog = ProgressDialog
                            // .show(activity, "", message);
                            // pDialog = CustomProgressDialog.createDialog(activity);
                            pDialog = CustomProgressDialog.createDialog(
                                    activity);
                            pDialog.setCancelable(true);
                            pDialog.setCanceledOnTouchOutside(false);
                            pDialog.show();
                            pDialog.setOnKeyListener(onKeyListener);
                        }
                    }


                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {

                    }


                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        if (message != null && !message.equals("")) {
                            pDialog.dismiss();
                        }
                        callBack.postCallBack(resultCode, responseInfo.result);
                    }


                    @Override
                    public void onFailure(HttpException error, String msg) {
                        if (message != null && !message.equals("")) {
                            pDialog.dismiss();
                        }
                    }
                });
    }


    public void connectPost(final Activity activity, final PostCallBackWithMessageId callBack, RequestParams params, final String message, final String messageId) {

        if (!NetworkTools.isNetworkAvailable(activity)) {
            MyToast.showToast(activity,
                    activity.getResources().getString(R.string.checkNetWork));
            return;
        }

        HttpUtils http = new HttpUtils();
        // 设置返回文本的编码， 默认编码UTF-8
        // http.configResponseTextCharset("GBK");
        http.configTimeout(30000);
        http.configSoTimeout(30000);
        http.configCurrentHttpCacheExpiry(0);
        http.send(HttpRequest.HttpMethod.POST, url, params,
                new RequestCallBack<String>() {
                    @Override public void onStart() {
                        if (message != null && !message.equals("")) {
                            pDialog = CustomProgressDialog.createDialog(
                                    activity);
                            pDialog.setCancelable(true);
                            pDialog.setCanceledOnTouchOutside(false);
                            pDialog.show();
                            pDialog.setOnKeyListener(onKeyListener);
                        }
                    }


                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {

                    }


                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        if (message != null && !message.equals("")) {
                            pDialog.dismiss();
                        }
                        callBack.postCallBack(resultCode, responseInfo.result,
                                messageId);
                    }


                    @Override
                    public void onFailure(HttpException error, String msg) {
                        if (message != null && !message.equals("")) {
                            pDialog.dismiss();
                        }
                    }
                });
    }


    class SSLSocketFactoryEx extends SSLSocketFactory {

        SSLContext sslContext = SSLContext.getInstance("TLS");


        public SSLSocketFactoryEx(KeyStore truststore)
                throws NoSuchAlgorithmException, KeyManagementException,
                       KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }


                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                        throws java.security.cert.CertificateException {

                }


                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                        throws java.security.cert.CertificateException {

                }
            };

            sslContext.init(null, new TrustManager[] { tm }, null);
        }


        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
                throws IOException, UnknownHostException {
            return sslContext.getSocketFactory()
                             .createSocket(socket, host, port, autoClose);
        }


        @Override public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }


    public void connectPostPairs(final Activity activity, final PostCallBack callBack, RequestParams params, final String message) {
        if (!NetworkTools.isNetworkAvailable(activity)) {
            MyToast.showToast(activity,
                    activity.getResources().getString(R.string.checkNetWork));
            return;
        }

        HttpUtils http = new HttpUtils();
        // 设置返回文本的编码， 默认编码UTF-8
        // http.configResponseTextCharset("GBK");
        http.configTimeout(30000);
        http.configSoTimeout(30000);
        http.configCurrentHttpCacheExpiry(0);
        http.send(HttpRequest.HttpMethod.POST, url, params,
                new RequestCallBack<String>() {

                    @Override public void onStart() {
                        if (message != null && !message.equals("")) {
                            // pDialog = ProgressDialog .show(activity, "", message);
                            // pDialog = CustomProgressDialog.createDialog(activity);
                            // pDialog.setCancelable(true);
                            // pDialog.setCanceledOnTouchOutside(false);
                            // pDialog.show();
                            // pDialog.setOnKeyListener(onKeyListener);
                        }
                    }


                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {

                    }


                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        if (message != null && !message.equals("")) {
                            // pDialog.dismiss();
                        }
                        callBack.postCallBack(resultCode, responseInfo.result);
                    }


                    @Override
                    public void onFailure(HttpException error, String msg) {
                        if (message != null && !message.equals("")) {
                            // pDialog.dismiss();
                        }
                    }
                });
    }


    public void connectGet(final Context context, final GetCallBack callBack, final String message) {

        if (!NetworkTools.isNetworkAvailable(context)) {
            MyToast.showToast(context,
                    context.getResources().getString(R.string.checkNetWork));
            return;
        }

        RequestParams params = new RequestParams(); // 默认编码UTF-8
        params.setHeader("Content-Type", "text/json");

        HttpUtils http = new HttpUtils();
        // 设置返回文本的编码， 默认编码UTF-8
        // http.configResponseTextCharset("GBK");
        // http.configResponseTextCharset("GBK");
        http.configCurrentHttpCacheExpiry(0); // 设置网络缓存时间
        http.send(HttpRequest.HttpMethod.GET, url, params,
                new RequestCallBack<String>() {

                    @Override public void onStart() {
                        if (message != null && !message.equals("")) {
                            // pDialog = ProgressDialog.show(context, "", message);

                            // pDialog = CustomProgressDialog.createDialog(context);
                            // pDialog.setCancelable(true);
                            // pDialog.setCanceledOnTouchOutside(false);
                            // pDialog.show();
                            // pDialog.setOnKeyListener(onKeyListener);
                        }
                    }


                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {

                    }


                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        if (message != null && !message.equals("")) {

                            // pDialog.dismiss();
                        }
                        callBack.getCallBack(resultCode, responseInfo.result);
                    }


                    @Override
                    public void onFailure(HttpException error, String msg) {
                        if (message != null && !message.equals("")) {
                            // pDialog.dismiss();
                        }
                        if (resultCode == -1) {
                            context.startActivity(
                                    new Intent(context, MainActivity.class));
                        }
                    }
                });
    }


    @SuppressWarnings({ "unused", "rawtypes" })
    public void connectDownLoad(final FileCallBack callBack, final String target) {
        HttpUtils http = new HttpUtils();
        http.configTimeout(30000);
        http.configSoTimeout(30000);
        HttpHandler handler = http.download(url, target, true, true,
                new RequestCallBack<File>() {

                    @Override public void onStart() {

                    }


                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {

                    }


                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
                        callBack.fileCallBack(resultCode,
                                responseInfo.result.getPath());
                    }


                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }
                });
    }


    public int getResultCode() {
        return resultCode;
    }


    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    private OnKeyListener onKeyListener = new OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK &&
                    event.getAction() == KeyEvent.ACTION_DOWN) {
                dismissDialog();
            }
            return false;
        }
    };


    public void dismissDialog() {
        if (null != pDialog && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }
}
