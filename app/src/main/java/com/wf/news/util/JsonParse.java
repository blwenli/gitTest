package com.wf.news.util;

import android.util.Log;

import com.wf.news.bean.NewsListItemBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//JSON format{"password":"123","resultTree":["str1","str2","str3"]}

public class JsonParse {

	public static ArrayList<NewsListItemBean> parseNewsListItemFromJson(String JsonString) {
		Log.d("parseNewsListItem", JsonString);
		ArrayList<NewsListItemBean> list = new ArrayList<NewsListItemBean>();
		try {
			JSONObject jsonObject = new JSONObject(JsonString);
			JSONArray array = jsonObject.getJSONArray("newsListItems");
			for(int i = 0; i < array.length(); i++){
				JSONObject item = array.getJSONObject(i);
				NewsListItemBean n = new NewsListItemBean();
				n.setId(item.getInt("id"));
				n.setNews_id(item.getString("news_id"));
				n.setTitle(item.getString("title"));
				n.setAbstract_(item.getString("abstract_"));
				n.setCache_img(item.getString("cache_img"));
				n.setDate(item.getString("date"));
				n.setLabel(item.getString("label"));
				list.add(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
