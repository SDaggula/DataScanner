package com.verifone.nfcdemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by SreeL1 on 2/13/2018.
 */

public class ReceiverActivity extends Activity {
    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        mTextView = (TextView) findViewById(R.id.text_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES);

            NdefMessage message = (NdefMessage) rawMessages[0]; // only one message transferred
            String receivedText = new String(message.getRecords()[0].getPayload());
            mTextView.setText(receivedText);
            openPlayStore(receivedText);
        } else
            mTextView.setText("Waiting for NDEF Message");
    }

    private void openPlayStore(String app) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");

        // package name and activity
        ComponentName comp = new ComponentName("com.android.vending",
                "com.google.android.finsky.activities.LaunchUrlHandlerActivity");
        launchIntent.setComponent(comp);

        // sample to open Nike app
//        if(app.contains("nike"))
            launchIntent.setData(Uri.parse("market://details?id=com.nike.omega"));
        startActivity(launchIntent);
    }
}
