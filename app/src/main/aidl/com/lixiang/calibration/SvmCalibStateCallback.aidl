// SvmCalibStateCallback.aidl
package com.lixiang.calibration;

interface SvmCalibStateCallback {
    void oncalibrationStateChanged(int state);            // 返回状态以及 error code： 0 成功
    void onCalibrationButttonClicked(boolean isConfirm);  // 标定按钮点击回调
    void onUploadStateChanged(boolean success);           // 标定数据上传结果回调
    void onDownloadStateChanged(boolean success);         // 标定数据下载结果回调
}