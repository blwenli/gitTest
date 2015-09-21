package com.wf.news.bean;

import android.graphics.Bitmap;

public class NewsListItemBean {

	private long id;
	private String news_id;
	private String title;
	private String abstract_;
	private String cache_img;
	private String date;
	private String label;
	private Bitmap bitmap;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getAbstract_() {
		return abstract_;
	}
	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}
	public String getCache_img() {
		return cache_img;
	}
	public void setCache_img(String cache_img) {
		this.cache_img = cache_img;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
}
