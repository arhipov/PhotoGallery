package com.example.photo_gallery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

public abstract class VisibleFragment extends Fragment {
    private static final String TAG="VisibleFargment";

    private BroadcastReceiver mOnShowNotification= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            /*Toast.makeText(getActivity(),
                    "Got a broadcast: "+intent.getAction(),
                    Toast.LENGTH_LONG)
                    .show();*/
            Log.i(TAG, "canceling notification");
            setResultCode(Activity.RESULT_CANCELED);
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        IntentFilter filter= new IntentFilter(PollService.ACTION_SHOW_NOTIFICATION);
        getActivity().registerReceiver(mOnShowNotification, filter,
                PollService.PEM_PRIVATE, null);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mOnShowNotification);
    }
}
