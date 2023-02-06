// SvmCalibStateCallback.aidl
package com.lixiang.calibration;

interface SvmCalibStateCallback {
/**
   * 标定成功 : 0
   * 标定失败 : 1
   * 标定中 : 2
   * 参数错误 : 3
   */
   const int CALIBRATION_STATE_SUCCESS = 0;
   const int CALIBRATION_STATE_FAILED = 1;
   const int CALIBRATION_STATE_WORKING = 2;
   const int CALIBRATION_STATE_PARAM_ERROR = 3;

  /**
   * 成功 : 0
   * 打包解压错误 : 998
   * 文件不存在 : 999
   * 未知类型，具体原因需要根据错误信息判断 : 1000
   * Binder 未连接 : 1001
   * 云端错误具体原因需要根据错误信息判断 : 1002
   * updateJob() createJob() jobDone() 前没有调用 registerJob() : 1003
   * 参数异常，参数为 null 或者 空字符串 : 1004
   * 没有调用 DataCenterManager.getInstance().connect() : 1005
   * RemoteException；Binder IPC 调用异常 : 1006
   * 没有网络连接 : 1007
   */
   const int NET_STATE_SUCCESS = 0;
   const int NET_STATE_ERROR_ZIP = 998;
   const int NET_STATE_ERROR_FILE_NOT_EXISTS = 999;
   const int NET_STATE_ERROR_UNKONWN = 1000;
   const int NET_STATE_ERROR_BINDER = 1001;
   const int NET_STATE_ERROR_CLOUD = 1002;
   const int NET_STATE_ERROR_METHOD = 1003;
   const int NET_STATE_ERROR_PARAMS = 1004;
   const int NET_STATE_ERROR_CONNECTION = 1005;
   const int NET_STATE_ERROR_IPC = 1006;
   const int NET_STATE_ERROR_NETWORK = 1007;

   const int POWER_STATE_OFF = 0;
   const int POWER_STATE_ON = 1;


    void oncalibrationStateChanged(int state);            // 返回状态以及 error code： 0 成功
    void onCalibrationButttonClicked(boolean isConfirm);  // 标定按钮点击回调
    void onUploadStateChanged(int state);           // 标定数据上传结果回调
    void onDownloadStateChanged(int state);         // 标定数据下载结果回调
}