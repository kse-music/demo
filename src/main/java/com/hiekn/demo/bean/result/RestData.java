package com.hiekn.demo.bean.result;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestData<T> {

	private List<T> rsData;
	private Long rsCount;
	
	
	public RestData() {
	}

	public RestData(T data){
		List<T> rsData = new ArrayList<>(1);
		rsData.add(data);
		this.rsData = rsData;
	}
	
	public RestData(List<T> rsData){
		this.rsData = rsData;
	}
	
	public RestData(List<T> rsData, Integer count){
		this(rsData);
		this.rsCount = Long.valueOf(count);
	}
	
	public RestData(List<T> rsData, Long count){
		this(rsData);
		this.rsCount = count;
	}
	
	public List<T> getRsData() {
		return rsData;
	}
	public void setRsData(List<T> rsData) {
		this.rsData = rsData;
	}
	public Long getRsCount() {
		return rsCount;
	}
	public void setRsCount(Long rsCount) {
		this.rsCount = rsCount;
	}
	
}
