package com.nwl.lanya.dto;

import java.io.Serializable;
import java.util.List;

import com.nwl.lanya.common.PageModel;

public abstract class AbstractBaseDto<T extends Object> implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 5221466235070551292L;
	
	protected String ids;
	protected Integer[] idArray;
	protected PageModel pageModel;
	
	protected Integer msgCode;
	protected String msg;
	
	protected T po;
	protected List<T> pos;
	
	private String columnName;
	private String columnValue;
	
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnValue() {
		return columnValue;
	}
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
	public T getPo() {
		return po;
	}
	public void setPo(T po) {
		this.po = po;
	}
	public List<T> getPos() {
		return pos;
	}
	public void setPos(List<T> pos) {
		this.pos = pos;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Integer[] getIdArray() {
		return idArray;
	}
	public void setIdArray(Integer[] idArray) {
		this.idArray = idArray;
	}
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public Integer getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(Integer msgCode) {
		this.msgCode = msgCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
