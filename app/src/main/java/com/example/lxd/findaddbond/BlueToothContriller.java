package com.example.lxd.findaddbond;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

/**
 * 蓝牙控制器
 */

public class BlueToothContriller {
    private BluetoothAdapter mAdapter;

    public BlueToothContriller() {
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean isSupportBlueTooth() {
        if (mAdapter != null) {
            return true;
        } else {
            return false;
        }


    }

    public boolean getBluetoothStatus() {

        return mAdapter != null ? mAdapter.isEnabled() : false;

    }

    public void turnOnBluetooth(Activity activity, int requestCode) {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(intent, requestCode);
      //  mAdapter.enable();

    }

    public void turnOffBluetooth(){
        mAdapter.disable();


    }
}
