package com.wf.news.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.wf.news.R;
import com.wf.news.util.NDKUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelloNDK extends ActionBarActivity {

    public native String getStringFromNative();

    @Bind(R.id.mTextView) TextView mTextView;
    @OnClick(R.id.mTextView) void helloJNI(){
        mTextView.setText(NDKUtil.getStringFromNative());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_ndk);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hello_ndk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
