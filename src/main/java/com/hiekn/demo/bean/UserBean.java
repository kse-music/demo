package com.hiekn.demo.bean;

import org.hibernate.validator.constraints.NotBlank;

public class UserBean {
	
	@NotBlank(message="用户名不能为空") 
	private String name;
	private String title;
	private Integer num;
	private String content;
	private String search;
	private String nosearch;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getNosearch() {
		return nosearch;
	}
	public void setNosearch(String nosearch) {
		this.nosearch = nosearch;
	}
	
}
