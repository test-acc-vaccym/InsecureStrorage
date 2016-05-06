package com.primeauth.insecurestrorage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.securepreferences.SecurePreferences;

public class MainActivity extends AppCompatActivity {
    EditText entertext;
    TextView inSecure;
    Activity activity = (Activity) this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entertext = (EditText) findViewById(R.id.enterText);
        inSecure = (TextView) findViewById(R.id.inSecData);
    }

    public void onStoreClick(View view) {
        String value = "none";
        value = entertext.getText().toString();
        SharedPreferences pref = getSharedPreferences("MyInSec", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("value", value);
        editor.commit();
        String returnvalue = pref.getString("value", "none");
        inSecure.setText(returnvalue);
        new setSecureText(getApplicationContext(),activity,value).execute();
    }
}

class setSecureText extends AsyncTask<Void, String, String> {
    TextView secure;
    Context mContext;
    String unEnc="none";
    String storeValue="none";
    Activity mActivity;

    public setSecureText(Context mContext,Activity activity,String value) {
        this.mContext = mContext;
        storeValue=value;
        mActivity = activity;
    }

    @Override
    protected String doInBackground(Void... params) {
        String dev_id = getDevID();
        SecurePreferences securePrefs = new SecurePreferences(mContext, dev_id, "MyInSec");
        SharedPreferences.Editor secureEditor = securePrefs.edit();
        secureEditor.putString("secValue",storeValue);
        secureEditor.commit();
        unEnc = securePrefs.getString("secValue","none");
        return unEnc;
    }

    protected void onPostExecute(String res) {
        secure = (TextView) mActivity.findViewById(R.id.secData);
        secure.setText(res);
    }

    private String getDevID() {
        String dev_id = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        return dev_id;
    }
}
