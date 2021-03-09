package com.nwl.lanya.rest;

import com.nwl.lanya.common.MsgCode;
import com.nwl.lanya.common.PageModel;
import com.nwl.lanya.common.RestCommonCode;
import com.nwl.lanya.dto.LabelInfoDto;
import com.nwl.lanya.po.LabelInfo;
import com.nwl.lanya.service.LabelInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author misaki
 */

@RestController
@RequestMapping("labelInfoRest")
public class LabelInfoRest extends AbstractBaseRest<LabelInfoDto> {

    @Autowired
    private LabelInfoServiceImpl service;

    @Override
    @RequestMapping("list")
    public PageModel list(Integer page, Integer rows, LabelInfoDto dto) {
        return RestCommonCode.list(page, rows, dto, service);
    }

    @Override
    @RequestMapping("delete")
    public LabelInfoDto deleteByIds(LabelInfoDto dto) {
        try {
            service.deleteByIds(dto);
            dto.setMsgCode(MsgCode.REQUEST_SCCESS);
            dto.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            dto.setMsgCode(MsgCode.REQUEST_FALSE);
            dto.setMsg("系统异常,请联系维护人员");
        }
        return dto;
    }

    @Override
    @RequestMapping("findById")
    public LabelInfoDto findById(LabelInfoDto dto) {
        try {
            service.findById(dto);
            dto.setMsgCode(MsgCode.REQUEST_SCCESS);
            dto.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            dto.setMsgCode(MsgCode.REQUEST_FALSE);
            dto.setMsg("系统异常,请联系维护人员");
        }
        return dto;
    }

    @Override
    @RequestMapping("saveOrUpdate")
    public LabelInfoDto saveOrUpdate(LabelInfoDto dto) {
        try {
            service.saveOrUpdate(dto);
            dto.setMsgCode(MsgCode.REQUEST_SCCESS);
            dto.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            dto.setMsgCode(MsgCode.REQUEST_FALSE);
            dto.setMsg("系统异常,请联系维护人员");
        }
        return dto;
    }

    @RequestMapping("getHotLabelInfos")
    public LabelInfoDto getHotLabelInfos(){
        LabelInfoDto dto = new LabelInfoDto();
        try {
            dto.setPos(service.getHotLabelInfos());
            dto.setMsgCode(MsgCode.REQUEST_SCCESS);
            dto.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            dto.setMsgCode(MsgCode.REQUEST_FALSE);
            dto.setMsg("系统异常,请联系维护人员");
        }
        return dto;
    }

}
