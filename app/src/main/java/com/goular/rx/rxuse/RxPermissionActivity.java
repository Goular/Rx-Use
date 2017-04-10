package com.goular.rx.rxuse;

import android.Manifest;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import com.goular.rx.R;
import com.jakewharton.rxbinding.view.RxView;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.IOException;

public class RxPermissionActivity extends AppCompatActivity {

    private static final String TAG = "RxPermissions";

    private Camera camera;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_permission);

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);

        //添加动态权限的管理
        RxView.clicks(findViewById(R.id.enableCamera))
                .compose(rxPermissions.ensureEach(Manifest.permission.CAMERA))
                .subscribe(permission -> {
                    Logger.i(TAG, "Permission result " + permission);
                    if (permission.granted) {
                        //如果已经授权了
                        releaseCamera();
                        camera = Camera.open(0);
                        try {
                            camera.setPreviewDisplay(surfaceView.getHolder());
                            camera.startPreview();
                        } catch (IOException e) {
                            Log.e(TAG, "Error while trying to display the camera preview", e);
                        }
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        //拒绝请求，同时已经设置了不能再次询问权限请求，此时仅能进入应用管理页面来进行开启权限管理
                        // Denied permission without ask never again
                        Toast.makeText(RxPermissionActivity.this,
                                "Denied permission without ask never again",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        //拒绝请求，同时伴有不在询问请求的checkbox内容页面
                        // Denied permission with ask never again
                        // Need to go to the settings
                        Toast.makeText(RxPermissionActivity.this,
                                "Permission denied, can't enable the camera",
                                Toast.LENGTH_SHORT).show();
                    }
                }, throwable -> {
                    Logger.e(TAG, "onError", throwable);
                }, () -> {
                    Logger.e(TAG, "OnComplete");
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseCamera();
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
