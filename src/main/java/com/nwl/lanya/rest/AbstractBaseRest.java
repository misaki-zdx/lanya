package com.nwl.lanya.rest;

import com.nwl.lanya.common.PageModel;
import com.nwl.lanya.dto.AbstractBaseDto;

public abstract class AbstractBaseRest<T extends AbstractBaseDto>{
	
	public abstract PageModel list(Integer page,Integer rows,T dto);
	
	public abstract T deleteByIds(T dto);
	
	public abstract T findById(T dto);
	
	public abstract T saveOrUpdate(T dto);

}
