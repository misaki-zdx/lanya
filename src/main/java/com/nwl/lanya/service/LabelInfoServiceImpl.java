package com.nwl.lanya.service;

import com.nwl.lanya.dao.LabelInfoMapper;
import com.nwl.lanya.dto.AbstractBaseDto;
import com.nwl.lanya.dto.LabelInfoDto;
import com.nwl.lanya.po.Addmanage;
import com.nwl.lanya.po.LabelInfo;
import com.sun.tools.javac.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author misaki
 */

@Service
public class LabelInfoServiceImpl implements AbstractBaseService<LabelInfoDto> {

    @Autowired
    private LabelInfoMapper mapper;

    @Override
    public void list(LabelInfoDto dto) {
        List<LabelInfo> labelInfos = mapper.list(dto);
        Long total = mapper.countByList(dto);
        dto.getPageModel().setRows(labelInfos);
        dto.getPageModel().setTotal(total);
    }

    @Override
    public void deleteByIds(LabelInfoDto dto) {
        String ids = dto.getIds();
        String[] strings = ids.split(",");
        for (String id : strings) {
            mapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void findById(LabelInfoDto dto) {
       dto.setPo(mapper.selectByPrimaryKey(dto.getPo().getId()));
    }

    @Override
    public void saveOrUpdate(LabelInfoDto dto) {
        String id = dto.getPo().getId();
        if (null != id && !"".equals(id)){
            mapper.updateByPrimaryKey(dto.getPo());
        }else {
            dto.getPo().setId(UUID.randomUUID().toString());
            mapper.insert(dto.getPo());
        }
    }

    public List<LabelInfo> getHotLabelInfos() {
        return mapper.selectHotLabels();
    }
}
