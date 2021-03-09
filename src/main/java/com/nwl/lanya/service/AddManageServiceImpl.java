package com.nwl.lanya.service;

import com.nwl.lanya.dao.AddmanageMapper;
import com.nwl.lanya.dto.AddManageDto;
import com.nwl.lanya.po.Addmanage;
import com.nwl.lanya.po.AddmanageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * @author misaki
 */
@Service
public class AddManageServiceImpl implements AbstractBaseService<AddManageDto> {

    @Autowired
    private AddmanageMapper addmanageMapper;

    @Override
    public void list(AddManageDto dto) {
        List<Addmanage> addmanageList = addmanageMapper.list(dto);
        Long total = addmanageMapper.countByList();
        dto.getPageModel().setRows(addmanageList);
        dto.getPageModel().setTotal(total);

    }

    @Override
    public void deleteByIds(AddManageDto dto) {
        String[] ids = dto.getIds().split(",");
        for (String id : ids) {
            addmanageMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void findById(AddManageDto dto) {
        dto.setPo(addmanageMapper.selectByPrimaryKey(dto.getPo().getId()));
    }

    @Override
    public void saveOrUpdate(AddManageDto dto) {
        String id = dto.getPo().getId();
        if (null != id && !"".equals(id)){
            addmanageMapper.updateByPrimaryKeySelective(dto.getPo());
        }else {
            dto.getPo().setId(UUID.randomUUID().toString());
            addmanageMapper.insert(dto.getPo());
        }
    }

    public List<Addmanage> getAll(){
        return addmanageMapper.selectByExample(new AddmanageExample());
    }
}
