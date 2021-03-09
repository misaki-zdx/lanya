package com.nwl.lanya.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nwl.lanya.dao.AdvertismentMapper;
import com.nwl.lanya.dto.AddvertisementDto;
import com.nwl.lanya.po.Advertisment;

@Service
public class AddvertisementService implements AbstractBaseService<AddvertisementDto>{
	
	@Autowired
	private AdvertismentMapper mapper;

	@Override
	public void list(AddvertisementDto dto) {
        List<Advertisment> advertisments = mapper.list(dto);
		Long total = mapper.countByList(dto);
		
		dto.getPageModel().setRows(advertisments);
		dto.getPageModel().setTotal(total);
	}

	@Override
	public void deleteByIds(AddvertisementDto dto) {
		String[] idStr = dto.getIds().split(",");
		for(int i=0;i<idStr.length;i++){
			String id = idStr[i];
			mapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void findById(AddvertisementDto dto) {
		String id = dto.getPo().getId();
		Advertisment addvertisement = mapper.selectByPrimaryKey(id);
		dto.setPo(addvertisement);
	}

	@Override
	public void saveOrUpdate(AddvertisementDto dto) {
		// TODO Auto-generated method stub
		Advertisment advertisment = dto.getPo();
		if(StringUtils.isEmpty(advertisment.getId())){
			advertisment.setId(UUID.randomUUID().toString());
			mapper.insert(advertisment);
		}else{
			mapper.updateByPrimaryKeySelective(advertisment);
		}
	}
	
	/**
	 * 获取数据库中所有广告数据
	 * @param dto
	 * @return
	 */
	public void getAll(AddvertisementDto dto){
		List<Advertisment> advertisments = mapper.selectByExample(null);
		dto.setPos(advertisments);
	}

}
