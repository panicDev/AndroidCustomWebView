package com.paniclabs.customwebview.callback;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;

public abstract class WebviewCallBack {
    public void onPageStarted(String url, Bitmap favicon) {
    }

    public void onPageFinished(String url) {
    }

    public void onLoadResource(String url) {
    }

    public void onProgressChanged(int newProgress) {
    }

    public void onReceivedTitle(String title) {

    }

    public void onJsAlert(String url, String message, final JsResult result) {
    }

    public void onReceivedSslError(final SslErrorHandler handler, SslError error) {
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
    }

    public void onHideCustomView() {
    }

    public void onPermissionRequest(PermissionRequest request) {
    }

    public void onPermissionRequestCanceled(PermissionRequest request) {
    }

    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
    }

    public void onConsoleMessage(ConsoleMessage consoleMessage) {
    }
}
