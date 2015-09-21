package com.wf.news.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.wf.news.bean.NewsListItemBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static com.wf.news.util.Constant.ServerIP;
import static com.wf.news.util.Constant.ServerPath;

public class Network {
	
	/**
     * @param urlString
     * @return
     */
    public static String getURLResponse(String urlString) {

		/*************** 服务器ip *******************/
    	urlString = urlString.replace("10.108.17.68", ServerIP);
		/*************** ******** *******************/

		Log.d("getURLResponse", urlString);
    	//return URLConnGet(urlString);
    	
    	return okHttpGet(urlString);
	}
    
    // HttpURLConnection get
    public static String URLConnGet(String urlString){

    	//
    	HttpURLConnection conn = null;
    	InputStream is = null;
		//
		String resultData = "";
		try {
			//
			URL url = new URL(urlString);
			//Log.d("urlStringEncode", URLEncoder.encode(urlString, "gbk"));
			
			//
			conn = (HttpURLConnection) url.openConnection();
			//
			conn.setDoInput(true);
			//
			//conn.getResponseCode();
			//
			conn.setDoOutput(true);
			//
			conn.setUseCaches(false);
			// post or get
			conn.setRequestMethod("GET");
			
			//cookie test
			conn.setRequestProperty("Cookie", "cookie0=test");
			
			//retry util suc
			while(true){
				is = conn.getInputStream();
				if(conn.getResponseCode() == 200) break;
				Thread.sleep(200);
			}
			
			// test cookie
			String key = null;
			for (int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++) {
				//System.out.print(key + ":");
				//System.out.println(conn.getHeaderField(key));
				if(conn.getHeaderField(key).contains("Cookie")){
					Log.d("cookie from server", "key :" + conn.getHeaderField(key));
				}
			}
			
			//OutputStream os =  conn.getOutputStream();
			InputStreamReader isr = new InputStreamReader(is, "gbk");
			// BufferedReader
			BufferedReader bufferReader = new BufferedReader(isr);
			String inputLine = null;
			// get response
			while (((inputLine = bufferReader.readLine()) != null)){
				resultData += inputLine + "\n";
			}		  
			//os.flush();
			//os.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				// close connection
				conn.disconnect();
			}
		}
		return resultData;
    }
    
    public static String okHttpGet(String urlString) {
    	
    	String response;
		try {
			response = run(urlString);
		} catch (IOException e) {
			response = e.getMessage();
		}
    	return response;
    }
    
    public static String okHttpPost(String urlString, HashMap<String, String> form) {
    	
    	String response;
    	FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
    	for(String key : form.keySet()){
    		formEncodingBuilder.add(key, form.get(key));
    	}
    	RequestBody formBody = formEncodingBuilder.build();
    	
		try {
			response = run(urlString, formBody);
		} catch (IOException e) {
			response = e.getMessage();
		}
    	return response;
    }
    
    static OkHttpClient client = new OkHttpClient();

    static String run(String url) throws IOException {
    	return run(url, null);
    }
    
    static String run(String url, RequestBody body) throws IOException {
        Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if(body!=null){
        	requestBuilder.post(body);
        }
        Request request = requestBuilder.build();
        Response response = client.newCall(request).execute();
        return response.body().string();
      }
    
    /**
     * getImageBitMap
     */
	public static Bitmap getImageBitMap(String url) {
		
		/*************** server ip *******************/
		url = url.replace("10.108.17.68", ServerIP);
		/*************** ******** *******************/
		
		Log.d("getImageBitMap", url);
		URL myImageUrl = null;
		Bitmap bitmap = null;
		//System.setProperty("http.keepAlive", "false");
		while (true) {
			try {
				myImageUrl = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) myImageUrl
						.openConnection();
				conn.setDoInput(true);
				conn.connect();
				InputStream is = conn.getInputStream();
				bitmap = BitmapFactory.decodeStream(is);
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// retry if failed
			if (bitmap != null)
				break;
			else
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		return bitmap;
	}
	
	/**
     * getImageBitMaps
     */
	public static Bitmap[] getImageBitMap(String... urlset) {
		Bitmap[] bitmapset = new Bitmap[urlset.length];
		//System.setProperty("http.keepAlive", "false");
		for(int i = 0; i < urlset.length; i++){
			bitmapset[i] = getImageBitMap(urlset[i]);
		}
		return bitmapset;
	}

	/*
	 * getNewsListItemFromServer
	 */
	public static ArrayList<NewsListItemBean> getNewsListItemFromServer(String before_date, String after_date) {
		// trans space into 20%
		before_date = before_date.replace(" ", "%20");
		after_date = after_date.replace(" ", "%20");
		String JsonString = getURLResponse(ServerPath + "getNewsListItem?before_date="+before_date+"&after_date="+after_date);
		return JsonParse.parseNewsListItemFromJson(JsonString);
	}

}
