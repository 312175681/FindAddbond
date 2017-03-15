package com.example.lxd.findaddbond;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  static final int REQUEST_CODE=0;
    private  BlueToothContriller mController = new BlueToothContriller();
    private Toast mToast;

    private BroadcastReceiver receiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
int state=intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,-1);
            switch (state){
                case BluetoothAdapter.STATE_OFF:
                    showToast("off");
                    break;
                case BluetoothAdapter.STATE_ON:
                    showToast("ON");
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    showToast("oning");
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    showToast("offing");
                    break;
                default:
                    showToast("unknow");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter=new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
;
        registerReceiver(receiver,filter);
    }

    public void isSupportBluetooth(View view) {
        boolean ret =mController.isSupportBlueTooth();
        showToast("支持"+ret);
    }

    public void isBluetoothenable(View view) {
        boolean ret=mController.getBluetoothStatus();
        showToast("bu支持"+ret);
    }

    public void turnOffBluetooth(View view) {
       mController.turnOffBluetooth();
    }

    public void requestTurnOnBluetooth(View view) {

        mController.turnOffBluetooth(this,REQUEST_CODE);
    }
    private  void  showToast(String text){
        if(mToast==null){
            mToast=Toast.makeText(this,text,Toast.LENGTH_LONG);
        }else {
            mToast.setText(text);
        }
        mToast.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            showToast("chenggong");
        }else {
            showToast("default");
        }
    }
}
