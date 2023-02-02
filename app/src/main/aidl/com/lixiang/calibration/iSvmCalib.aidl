// iSvmCalib.aidl
package com.lixiang.calibration;

import com.lixiang.calibration.SvmCalibStateCallback;

// Declare any non-default types here with import statements

interface iSvmCalib {
    void setOnCalibrationChangedListener(SvmCalibStateCallback cb); // 注册回调接口 registerListener
    void unregisterListener(SvmCalibStateCallback cb);               // 注销回调接口


    void openCalibrationView(int airsus);    // 供远程调起标定服务
    void uploadCalibrationData();            // 上传标定数据
    void downloadCalibrationData();          // 下载外参数据，此部分建议在 SVM 实现
    void stopCalibration();                  // 停止标定， 此功能暂时没有实现
}