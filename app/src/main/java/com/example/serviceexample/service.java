package com.example.serviceexample;

import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class service extends Service {
    private static final String TAG = "serviceTest";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ComponentName componentName = new ComponentName(this, DeviceAdminRec.class);

        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

        if (devicePolicyManager != null) {
            Log.d(TAG, "onStartCommand: Device Policy Manager != null");
            if (devicePolicyManager.isAdminActive(componentName)) {
                Log.d(TAG, "onStartCommand: ADMIN IS ACTIVE");
            }
            devicePolicyManager.lockNow();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
