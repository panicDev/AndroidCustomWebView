package com.paniclabs.customwebview;

import android.text.TextUtils;
import android.webkit.WebSettings;

import com.paniclabs.customwebview.callback.OnShowFileChooser;
import com.paniclabs.customwebview.callback.ShouldInterceptRequestInterface;
import com.paniclabs.customwebview.callback.ShouldOverrideUrlLoadingInterface;
import com.paniclabs.customwebview.callback.WebviewCallBack;
import com.paniclabs.customwebview.widget.CustomWebChromeClient;
import com.paniclabs.customwebview.widget.CustomWebViewClient;

import java.util.ArrayList;
import java.util.List;

public class WebviewBuilder {
    private String tag = "";
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

    public WebviewBuilder() {
    }

    public WebviewBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public WebviewBuilder setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
        return this;
    }


    public WebviewBuilder setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    /**
     * 设置是否开启AllowFileAccess
     *
     * @param isAllowFileAccess 是否开启AllowFileAccess
     * @return CustomWebview
     */
    public WebviewBuilder setAllowFileAccess(boolean isAllowFileAccess) {
        this.isAllowFileAccess = isAllowFileAccess;
        return this;
    }

    /**
     * 设置UserAgent
     *
     * @param userAgent userAgent
     * @return WebviewBuilder
     */
    public WebviewBuilder setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * 设置CacheMode
     *
     * @param cacheMode cacheMode
     * @return WebviewBuilder
     */
    public WebviewBuilder setCacheMode(int cacheMode) {
        this.cacheMode = cacheMode;
        return this;
    }

    /**
     * 设置是否提示SSL证书失败的Dialog
     *
     * @param showSSLDialog 是否开启提示，默认false
     * @return WebviewBuilder
     */
    public WebviewBuilder setShowSSLDialog(boolean showSSLDialog) {
        this.isShowSSLDialog = showSSLDialog;
        return this;
    }

    /**
     * 设置是否使用默认的WebViewClient，不设置则不使用，用户可以自行设置自己的.true则表示使用
     *
     * @param defaultWebViewClient defaultWebViewClient
     * @return WebviewBuilder
     */
    public WebviewBuilder setDefaultWebViewClient(boolean defaultWebViewClient) {
        this.defaultWebViewClient = defaultWebViewClient;
        return this;
    }

    /**
     * 设置页面的文本缩放百分比。默认值为100
     *
     * @param textZoom textZoom
     * @return WebviewBuilder
     */
    public WebviewBuilder setTextZoom(int textZoom) {
        this.textZoom = textZoom;
        return this;
    }

    /**
     * 设置customWebViewClient
     *
     * @param customWebViewClient customWebViewClient
     * @return WebviewBuilder
     */
    public WebviewBuilder setCustomWebViewClient(CustomWebViewClient customWebViewClient) {
        this.customWebViewClient = customWebViewClient;
        return this;
    }

    public WebviewBuilder setWebiewCallBack(WebviewCallBack callBack) {
        this.webviewCallBack = callBack;
        return this;
    }

    public WebviewBuilder setShouldOverrideUrlLoadingInterface(ShouldOverrideUrlLoadingInterface shouldOverrideUrlLoadingInterface) {
        this.shouldOverrideUrlLoadingInterface = shouldOverrideUrlLoadingInterface;
        return this;
    }

    public WebviewBuilder setShouldInterceptRequestInterface(ShouldInterceptRequestInterface shouldInterceptRequestInterface) {
        this.shouldInterceptRequestInterface = shouldInterceptRequestInterface;
        return this;
    }

    public WebviewBuilder setDefaultWebChromeClient(boolean defaultWebChromeClient) {
        this.defaultWebChromeClient = defaultWebChromeClient;
        return this;
    }

    public WebviewBuilder setCustomWebChromeClient(CustomWebChromeClient customWebChromeClient) {
        this.customWebChromeClient = customWebChromeClient;
        return this;
    }

    public WebviewBuilder addJSInterface(Object mapClazz, String objName) {
        if (jsBeanList == null) {
            jsBeanList = new ArrayList<>();
        }
        JSBean jsBean = new JSBean();
        jsBean.setMapClazz(mapClazz);
        jsBean.setObjName(objName);
        jsBeanList.add(jsBean);
        return this;
    }

    public WebviewBuilder setOnShowFileChooser(OnShowFileChooser onShowFileChooser) {
        this.onShowFileChooser = onShowFileChooser;
        return this;
    }

    public WebviewBuilder setOnDownloadStart(OnDownloadStart onDownloadStart) {
        this.onDownloadStart = onDownloadStart;
        return this;
    }

    public WebviewBuilder setOnScrollChangedCallBack(OnScrollChangedCallBack onScrollChangedCallBack) {
        this.onScrollChangedCallBack = onScrollChangedCallBack;
        return this;
    }

    @Override
    public String toString() {
        return "WebviewBuilder{" +
                "currentUrl='" + currentUrl + '\'' +
                ", debug=" + debug +
                ", userAgent='" + userAgent + '\'' +
                ", cacheMode=" + cacheMode +
                ", isShowSSLDialog=" + isShowSSLDialog +
                ", defaultWebViewClient=" + defaultWebViewClient +
                ", textZoom=" + textZoom +
                ", customWebViewClient=" + customWebViewClient +
                ", webviewCallBack=" + webviewCallBack +
                ", shouldOverrideUrlLoadingInterface=" + shouldOverrideUrlLoadingInterface +
                ", shouldInterceptRequestInterface=" + shouldInterceptRequestInterface +
                ", defaultWebChromeClient=" + defaultWebChromeClient +
                ", customWebChromeClient=" + customWebChromeClient +
                ", jsBeanList=" + jsBeanList +
                ", onShowFileChooser=" + onShowFileChooser +
                '}';
    }

    public String getTag() {
        return tag;
    }

    public String getCurrentUrl() {
        return TextUtils.isEmpty(currentUrl) ? "" : currentUrl;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isAllowFileAccess() {
        return isAllowFileAccess;
    }

    public String getUserAgent() {
        return TextUtils.isEmpty(userAgent) ? "" : userAgent;
    }

    public int getCacheMode() {
        return cacheMode;
    }

    public boolean isShowSSLDialog() {
        return isShowSSLDialog;
    }

    public boolean isDefaultWebViewClient() {
        return defaultWebViewClient;
    }

    public CustomWebViewClient getCustomWebViewClient() {
        return customWebViewClient;
    }

    public WebviewCallBack getWebviewCallBack() {
        return webviewCallBack;
    }

    public ShouldOverrideUrlLoadingInterface getShouldOverrideUrlLoadingInterface() {
        return shouldOverrideUrlLoadingInterface;
    }

    public ShouldInterceptRequestInterface getShouldInterceptRequestInterface() {
        return shouldInterceptRequestInterface;
    }

    public boolean isDefaultWebChromeClient() {
        return defaultWebChromeClient;
    }

    public CustomWebChromeClient getCustomWebChromeClient() {
        return customWebChromeClient;
    }

    public List<JSBean> getJsBeanList() {
        return jsBeanList;
    }


    public int getTextZoom() {
        return textZoom;
    }

    public void setWebviewCallBack(WebviewCallBack webviewCallBack) {
        this.webviewCallBack = webviewCallBack;
    }

    public void setJsBeanList(List<JSBean> jsBeanList) {
        this.jsBeanList = jsBeanList;
    }

    public OnShowFileChooser getOnShowFileChooser() {
        return onShowFileChooser;
    }

    public OnDownloadStart getOnDownloadStart() {
        return onDownloadStart;
    }

    public OnScrollChangedCallBack getOnScrollChangedCallBack() {
        return onScrollChangedCallBack;
    }
}
