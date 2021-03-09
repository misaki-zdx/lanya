package com.nwl.lanya.common;

import com.nwl.lanya.dto.AbstractBaseDto;
import com.nwl.lanya.service.AbstractBaseService;

/**
 * @author misaki
 */

public class RestCommonCode {
    public static PageModel list(Integer page, Integer rows, AbstractBaseDto<?> dto, AbstractBaseService service) {
        try {
            PageModel pageModel = new PageModel();
            pageModel.setPageNo(page);
            pageModel.setPageSize(rows);
            pageModel.setStartIndex((page - 1) * rows);
            dto.setPageModel(pageModel);

            service.list(dto);
            dto.setMsgCode(MsgCode.REQUEST_SCCESS);
            dto.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            dto.setMsgCode(MsgCode.REQUEST_FALSE);
            dto.setMsg("系统异常,请联系维护人员");
        }
        return dto.getPageModel();
    }
}
