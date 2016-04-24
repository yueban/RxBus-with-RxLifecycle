package bigfat.com.rxbusdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import bigfat.com.rxbusdemo.R;
import bigfat.com.rxbusdemo.common.BaseActivity;
import bigfat.com.rxbusdemo.event.CommonEvent;
import bigfat.com.rxbusdemo.rxbus.RxBus;
import rx.functions.Action1;

public class Activity3 extends BaseActivity {
    private static final String TAG = Activity3.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        RxBus.postEventSticky(new CommonEvent(TAG + "_sticky"));
        RxBus.postEvent(new CommonEvent(TAG + "_normal"));

        mBusSticky
                .ofType(CommonEvent.class)
                .subscribe(new Action1<CommonEvent>() {
                    @Override
                    public void call(CommonEvent commonEvent) {
                        Log.i(TAG, commonEvent.message);
                    }
                });

        mBus
                .ofType(CommonEvent.class)
                .subscribe(new Action1<CommonEvent>() {
                    @Override
                    public void call(CommonEvent commonEvent) {
                        Log.i(TAG, commonEvent.message);
                    }
                });
    }

    public void onButtonClick(View view) {
        startActivity(new Intent(Activity3.this, MainActivity.class).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
    }
}
