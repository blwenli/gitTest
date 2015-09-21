package com.wf.news.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;

import com.wf.news.util.Network;

/**
 * Created by Kesson on 2015/9/20.
 */
public class NetworkService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NetworkService() {
        super("NetworkService");
    }

    public static String latestReqTime = "0000-00-00 00:00:00";			// 请求时间
    public static String oldestLocalTime = "9999-99-99 99:99:99";		// 中文

    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra("url");
        String form = intent.getStringExtra("form");
        DowloadTask dowloadTask = new DowloadTask();
        dowloadTask.execute(url);
    }

    // optional below



    class DowloadTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {							// Runs on the UI thread before doInBackground.
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {		// perform a computation on a background thread
            Network.getNewsListItemFromServer(latestReqTime, oldestLocalTime);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {	// Runs on the UI thread after publishProgress is invoked
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {			// Runs on the UI thread after doInBackground

            Intent cmpltIntent = new Intent("com.wf.news.Network.ReqCmpltBrodcast");
            cmpltIntent.putExtra("msg", "RequestCompleted");
            sendBroadcast(cmpltIntent);
            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
