package com.paniclabs.customwebview;

import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.paniclabs.customwebview.widget.CustomWebview;

import java.util.Map;

public interface IWebView {
    void setWebviewBuilder(WebviewBuilder builder);

    void setWebviewBuilderWithBuild(WebviewBuilder builder);

    void setWebviewBuilderWithBuildLoadUrl(WebviewBuilder builder);

    String getUserAgentString();

    void setUserAgentString(String userAgent);

    View getIView();

    CustomWebview getWebView();

    void setAcceptThirdPartyCookies(boolean isAccept);

    boolean canGoback2();

    void goBack2();

    int getScrollY2();

    void scrollTo2(int x, int y);

    void loadUrl2(String url);

    void loadUrl2(String url, Map<String, String> additionalHttpHeaders);

    void loadData2(String data, @Nullable String mimeType, @Nullable String encoding);

    void loadDataWithBaseURL2(@Nullable String baseUrl, String data, @Nullable String mimeType, @Nullable String encoding, @Nullable String historyUrl);

    String getUrl2();

    void reload2();

    String getTitle2();

    WebView.HitTestResult getHitTestResult2();


    void build();

    void buildWithLoadUrl();

    void loadJavaScript(String javascript, ValueCallback valueCallback);

    void destroy2();

}
