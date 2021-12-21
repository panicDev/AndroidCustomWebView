package com.example.administrator.myapplication;

import android.Manifest;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.paniclabs.customwebview.IWebView;
import com.paniclabs.customwebview.WebviewBuilder;
import com.paniclabs.customwebview.WebviewStrategy;
import com.paniclabs.customwebview.callback.WebviewCallBack;
import com.paniclabs.customwebview.widget.CustomWebview;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.List;

public class MainActivityNew extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private FrameLayout web_layout;
    private WebviewStrategy webviewStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainnew);
        web_layout = findViewById(R.id.web_layout);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setEnabled(false);
        PermissionX.init(this)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                        if (allGranted) {
                            initWebview();
                        } else {
                            onBackPressed();
                        }
                    }
                });
    }


    private void initWebview() {
        web_layout.addView(getIWebview().getIView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        getIWebview()
                .setWebviewBuilderWithBuildLoadUrl(getWebviewBuilder()
                );
    }

    private WebviewStrategy getWebviewStrategy() {
        if (webviewStrategy == null || webviewStrategy.getiWebView() == null) {
            IWebView iWebView;
            iWebView = new CustomWebview(this);
            webviewStrategy = new WebviewStrategy(iWebView);
        }
        return webviewStrategy;
    }

    private IWebView getIWebview() {
        return getWebviewStrategy().getiWebView();
    }

    private WebviewBuilder getWebviewBuilder() {
        return new WebviewBuilder()
                .setDebug(true)
                .setCurrentUrl("https://f.nhzj.com/wap/esf/index")
                .setUserAgent("")
                .setCacheMode(-1)
                .setShowSSLDialog(true)
                .setDefaultWebViewClient(true)
                .setDefaultWebChromeClient(true)
                .addJSInterface(new JsCallJava(), "test")
                .addJSInterface(new JsCallJava(), "test1")
                .setWebiewCallBack(new WebviewCallBack() {
                    @Override
                    public void onPageStarted(String url, Bitmap favicon) {
                        super.onPageStarted(url, favicon);
                    }

                    @Override
                    public void onPageFinished(String url) {
                        super.onPageFinished(url);
                    }

                    @Override
                    public void onLoadResource(String url) {
                        super.onLoadResource(url);
                    }

                    @Override
                    public void onProgressChanged(int newProgress) {
                        super.onProgressChanged(newProgress);
                    }

                    @Override
                    public void onReceivedTitle(String title) {
                        super.onReceivedTitle(title);
                    }

                    @Override
                    public void onJsAlert(String url, String message, JsResult result) {
                        super.onJsAlert(url, message, result);
                    }

                    @Override
                    public void onReceivedSslError(SslErrorHandler handler, SslError error) {
                        super.onReceivedSslError(handler, error);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (getIWebview().canGoback2()) {
            getIWebview().goBack2();
        } else {
            super.onBackPressed();
        }
    }
}
