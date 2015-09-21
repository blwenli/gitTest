package com.wf.news.util;

import com.wf.news.bean.NewsListItemBean;

import java.util.HashMap;

public class MapPut {
	
	public static HashMap<String, Object> putNewsListItem(NewsListItemBean n){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Title", n.getTitle());
		map.put("Abstract", n.getAbstract_());
		map.put("Image", n.getBitmap());
		map.put("date", n.getDate());
		map.put("NewsLabel", n.getLabel());
		return map;
	}

}
