package com.paniclabs.customwebview;


public class WebviewStrategy {

    private IWebView iWebView;

    public WebviewStrategy(IWebView iWebView) {
        this.iWebView = iWebView;
    }

    public IWebView getiWebView() {
        return iWebView;
    }
}
