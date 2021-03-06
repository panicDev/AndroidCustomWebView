package com.paniclabs.customwebview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.paniclabs.customwebview.IWebView;
import com.paniclabs.customwebview.JSBean;
import com.paniclabs.customwebview.OnDownloadStart;
import com.paniclabs.customwebview.OnScrollChangedCallBack;
import com.paniclabs.customwebview.WebviewBuilder;
import com.paniclabs.customwebview.callback.OnShowFileChooser;
import com.paniclabs.customwebview.callback.ShouldInterceptRequestInterface;
import com.paniclabs.customwebview.callback.ShouldOverrideUrlLoadingInterface;
import com.paniclabs.customwebview.callback.WebviewCallBack;

import java.util.List;
import java.util.Map;

public class CustomWebview extends WebView implements IWebView {
    private static final String Tag = CustomWebview.class.getSimpleName();

    private String currentTag = "";
    private String currentUrl = "";
    private boolean debug = false;
    private boolean isAllowFileAccess = false;
    private String userAgent;
    private int cacheMode = WebSettings.LOAD_DEFAULT;
    private boolean isShowSSLDialog = false;
    private boolean defaultWebViewClient = false;
    private int textZoom = 100;

    private CustomWebViewClient customWebViewClient;
    private WebviewCallBack webviewCallBack;
    private ShouldOverrideUrlLoadingInterface shouldOverrideUrlLoadingInterface;
    private ShouldInterceptRequestInterface shouldInterceptRequestInterface;

    private boolean defaultWebChromeClient = false;
    private CustomWebChromeClient customWebChromeClient;

    private List<JSBean> jsBeanList;

    private OnShowFileChooser onShowFileChooser;
    private OnDownloadStart onDownloadStart;

    private OnScrollChangedCallBack onScrollChangedCallBack;

    public CustomWebview(Context context) {
        super(context);
        init();
    }

    public CustomWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    @Override
    public void setWebviewBuilder(WebviewBuilder builder) {
        if (builder != null) {
            this.currentTag = builder.getTag();
            this.currentUrl = builder.getCurrentUrl();
            this.debug = builder.isDebug();
            this.isAllowFileAccess = builder.isAllowFileAccess();
            this.userAgent = builder.getUserAgent();
            this.cacheMode = builder.getCacheMode();
            this.isShowSSLDialog = builder.isShowSSLDialog();
            this.textZoom = builder.getTextZoom();

            this.defaultWebViewClient = builder.isDefaultWebViewClient();
            this.customWebViewClient = builder.getCustomWebViewClient();
            this.webviewCallBack = builder.getWebviewCallBack();
            this.shouldOverrideUrlLoadingInterface = builder.getShouldOverrideUrlLoadingInterface();
            this.shouldInterceptRequestInterface = builder.getShouldInterceptRequestInterface();

            this.defaultWebChromeClient = builder.isDefaultWebChromeClient();
            this.customWebChromeClient = builder.getCustomWebChromeClient();

            this.jsBeanList = builder.getJsBeanList();

            this.onShowFileChooser = builder.getOnShowFileChooser();
            this.onDownloadStart = builder.getOnDownloadStart();

            this.onScrollChangedCallBack = builder.getOnScrollChangedCallBack();
        }
    }

    @Override
    public void setWebviewBuilderWithBuild(WebviewBuilder builder) {
        setWebviewBuilder(builder);
        build();
    }

    @Override
    public void setWebviewBuilderWithBuildLoadUrl(WebviewBuilder builder) {
        setWebviewBuilder(builder);
        buildWithLoadUrl();
    }

    @Override
    public String getUserAgentString() {
        return this.getSettings().getUserAgentString();
    }

    @Override
    public void setUserAgentString(String userAgent) {
        this.getSettings().setUserAgentString("" + userAgent);
    }

    @Override
    public View getIView() {
        return this;
    }

    @Override
    public CustomWebview getWebView() {
        return this;
    }

    @Override
    public void setAcceptThirdPartyCookies(boolean isAccept) {
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(this, isAccept);
        }
        cookieManager.setAcceptCookie(isAccept);
    }

    public void initWebViewSettings() {
        if (debug) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }
        if (!TextUtils.isEmpty(currentTag)) {
            setTag(currentTag);
        }
        WebSettings webSettings = this.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (!TextUtils.isEmpty(userAgent)) {
            webSettings.setUserAgentString(userAgent);
        }
        webSettings.setTextZoom(textZoom);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(isAllowFileAccess);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(false);
            webSettings.setAllowUniversalAccessFromFileURLs(false);
        }
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setLoadWithOverviewMode(false);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setSavePassword(false);
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        webSettings.setAppCachePath(getContext().getDir("appcache", 0).getPath());
        webSettings.setDatabasePath(getContext().getDir("databases", 0).getPath());
        webSettings.setGeolocationDatabasePath(getContext().getDir("geolocation", 0)
                .getPath());
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(cacheMode);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
//        this.requestFocus();//???????????????????????????view???????????????????????????
//        this.requestFocusFromTouch();//???????????????????????????view???????????????????????????
//        this.setFocusableInTouchMode(true);
    }

    private void initWebViewClient() {
        if (defaultWebViewClient) {//???????????????????????????CustomWebViewClient
            if (customWebViewClient == null) {
                customWebViewClient = new CustomWebViewClient(isShowSSLDialog);
                this.customWebViewClient.setWebviewCallBack(webviewCallBack);
                this.customWebViewClient.setShouldOverrideUrlLoadingInterface(shouldOverrideUrlLoadingInterface);
                this.customWebViewClient.setShouldInterceptRequestInterface(shouldInterceptRequestInterface);
            }
            this.setWebViewClient(customWebViewClient);
        } else {
            if (customWebViewClient != null) {
                this.customWebViewClient.setWebviewCallBack(webviewCallBack);
                this.customWebViewClient.setShouldOverrideUrlLoadingInterface(shouldOverrideUrlLoadingInterface);
                this.customWebViewClient.setShouldInterceptRequestInterface(shouldInterceptRequestInterface);
                this.setWebViewClient(customWebViewClient);
            } else {
                Log.e(Tag, "???????????? WebViewClient ?????????");
            }
        }
    }

    private void initWebChromeClient() {
        if (defaultWebChromeClient) {//???????????????????????????CustomChromeClient
            if (customWebChromeClient == null) {
                customWebChromeClient = new CustomWebChromeClient(webviewCallBack, onShowFileChooser);
                this.customWebChromeClient.setWebviewCallBack(webviewCallBack);
            }
            this.setWebChromeClient(customWebChromeClient);
        } else {
            if (customWebChromeClient != null) {
                this.customWebChromeClient.setWebviewCallBack(webviewCallBack);
                this.setWebChromeClient(customWebChromeClient);
            } else {
                Log.e(Tag, "???????????? WebChromeClient ?????????");
            }
        }
    }

    @SuppressLint("JavascriptInterface")
    private void initJavascriptInterface() {
        if (jsBeanList != null && !jsBeanList.isEmpty()) {
            for (int i = 0; i < jsBeanList.size(); i++) {
                this.addJavascriptInterface(jsBeanList.get(i).getMapClazz(), jsBeanList.get(i).getObjName());
            }
        }
    }

    @Override
    public boolean canGoback2() {
        return this.canGoBack();
    }

    @Override
    public void goBack2() {
        this.goBack();
    }

    @Override
    public int getScrollY2() {
        return this.getScrollY();
    }

    @Override
    public void scrollTo2(int x, int y) {
        this.scrollTo(x, y);
    }

    @Override
    public void loadUrl2(String url) {
        if (!TextUtils.isEmpty(url)) {
            this.loadUrl(url);
        }
    }

    @Override
    public void loadUrl2(String url, Map<String, String> additionalHttpHeaders) {
        if (!TextUtils.isEmpty(url)) {
            this.loadUrl(url, additionalHttpHeaders);
        }
    }

    @Override
    public void loadData2(String data, @Nullable String mimeType, @Nullable String encoding) {
        this.loadData(data, mimeType, encoding);
    }

    @Override
    public void loadDataWithBaseURL2(@Nullable String baseUrl, String data, @Nullable String mimeType, @Nullable String encoding, @Nullable String historyUrl) {
        this.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    @Override
    public String getUrl2() {
        return "" + this.getUrl();
    }

    @Override
    public void reload2() {
        this.reload();
    }

    @Override
    public String getTitle2() {
        return this.getTitle();
    }

    @Override
    public HitTestResult getHitTestResult2() {
        return this.getHitTestResult();
    }

    @Override
    public void build() {
        initWebViewSettings();
        initWebViewClient();
        initWebChromeClient();
        initDownLoadListener();
        initJavascriptInterface();
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollChangedCallBack != null) {
            onScrollChangedCallBack.onScrollChanged(l, t, oldl, oldt);
        }
    }


    private void initDownLoadListener() {
        if (onDownloadStart != null) {
            this.setDownloadListener(new DownloadListener() {
                @Override
                public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                    if (onDownloadStart != null) {
                        onDownloadStart.onDownloadStart(url, userAgent, contentDisposition, mimetype, contentLength);
                    }
                }
            });
        }
    }


    @Override
    public void buildWithLoadUrl() {
        build();
        loadUrl2(currentUrl);
    }

    @Override
    public void loadJavaScript(String javascript, ValueCallback valueCallback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.evaluateJavascript("" + javascript, valueCallback);
        } else {
            this.loadUrl("" + javascript);
        }
    }

    @Override
    public void destroy2() {
        try {
            if (jsBeanList != null && !jsBeanList.isEmpty()) {
                for (int i = 0; i < jsBeanList.size(); i++) {
                    this.removeJavascriptInterface(jsBeanList.get(i).getObjName());
                }
            }
            this.removeAllViews();
            this.setVisibility(GONE);
            this.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
