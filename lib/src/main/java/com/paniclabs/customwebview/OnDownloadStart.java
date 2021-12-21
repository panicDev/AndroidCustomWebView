package com.paniclabs.customwebview;

public interface OnDownloadStart {
    void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength);
}
