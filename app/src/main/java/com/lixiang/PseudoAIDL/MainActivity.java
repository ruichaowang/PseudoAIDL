package com.lixiang.PseudoAIDL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lixiang.calibration.SvmCalibStateCallback;
import com.lixiang.calibration.iSvmCalib;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "模拟的诊断程序";
    boolean calib_service_binded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void Bind_service() {
        ComponentName componetName = new ComponentName(
                "com.lixiang.calibration",  //这个参数是另外一个app的包名
                "com.lixiang.calibration.SvmCalibService");   //这个是要启动的Service的全路径名

        Intent intent = new Intent();
        intent.setComponent(componetName);
        bindService(intent, mConnection_remote, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart!");

        Bind_service();
    }


    /** 链接 calib service */
    private iSvmCalib m_CalibService;
    private SvmCalibStateCallback m_SvmCalibStateCallback = new SvmCalibStateCallback.Stub() {
        @Override
        public void oncalibrationStateChanged(int state) throws RemoteException {
            Log.i(TAG,"client 收到的标定的结果" + state);         // 这里可以新增一个增加 toast 的功能

            /*标定成功后可以选择关闭 service 比如在此我设置了拿到结果后 5s 后关闭service*/
            Thread t_close_calib = new Thread(() -> {
                try {
                    Thread.currentThread().sleep(99000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    m_CalibService.stopCalibration();
                    m_CalibService.unregisterListener(m_SvmCalibStateCallback);
                    unbindService(mConnection_remote);
                    calib_service_binded = false;
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
            t_close_calib.start();
        }

        @Override
        public void onCalibrationButttonClicked(boolean isConfirm) throws RemoteException {
            Log.i(TAG,"开启标定程序");
        }

        @Override
        public void onUploadStateChanged(int state) throws RemoteException {
            Log.i(TAG,"tbd");
        }

        @Override
        public void onDownloadStateChanged(int state) throws RemoteException {
            Log.i(TAG,"tbd");
        }
    };

    private ServiceConnection mConnection_remote = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            m_CalibService = iSvmCalib.Stub.asInterface(service);
            calib_service_binded = true;
            Log.i(TAG, "Calib service Connected");

            try {
                m_CalibService.setOnCalibrationChangedListener(m_SvmCalibStateCallback);    // 需要保证 connect 后再执行回调的注册
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            m_CalibService = null;
            boolean calib_service_binded = false;
            Log.e(TAG, "Calib service has unexpectedly disconnected");
        }
    };


    /** 按钮的实现 */
    public void click_upload(View v) throws RemoteException {
        if (false == calib_service_binded) {return;}    //必须保证绑定后才可以运行
        m_CalibService.uploadCalibrationData();
    }

    public void click_calib_2(View v) throws RemoteException {

        if (false == calib_service_binded) {return;}    //必须保证绑定后才可以运行
        Thread t_calib = new Thread(() -> {
            try {
                m_CalibService.openCalibrationView(2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        t_calib.start();
    }

    public void click_calib_3(View v) throws RemoteException {

        if (false == calib_service_binded) {return;}    //必须保证绑定后才可以运行
        Thread t_calib = new Thread(() -> {
            try {
                m_CalibService.openCalibrationView(3);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        t_calib.start();
    }

    public void click_calib_4(View v) throws RemoteException {

        if (false == calib_service_binded) {return;}    //必须保证绑定后才可以运行
        Thread t_calib = new Thread(() -> {
            try {
                m_CalibService.openCalibrationView(4);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        t_calib.start();
    }

    public void click_calib_5(View v) throws RemoteException {

        if (false == calib_service_binded) {return;}    //必须保证绑定后才可以运行
        Thread t_calib = new Thread(() -> {
            try {
                m_CalibService.stopCalibration();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        t_calib.start();
    }

    public void click_calib_6(View v) throws RemoteException {
        Bind_service();
    }
}