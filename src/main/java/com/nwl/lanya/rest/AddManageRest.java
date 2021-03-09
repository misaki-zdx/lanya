package com.nwl.lanya.rest;

import com.nwl.lanya.common.MsgCode;
import com.nwl.lanya.common.PageModel;
import com.nwl.lanya.dto.AddManageDto;
import com.nwl.lanya.service.AddManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @author misaki
 */

@RestController
@RequestMapping("addManageRest")
public class AddManageRest extends AbstractBaseRest<AddManageDto> {

    @Autowired
    private AddManageServiceImpl addManageService;

    @Override
    @RequestMapping("list")
    public PageModel list(Integer page, Integer rows, AddManageDto dto) {
        try {
            PageModel pageModel = new PageModel();
            pageModel.setPageNo(page);
            pageModel.setPageSize(rows);
            pageModel.setStartIndex((page - 1) * rows);
            dto.setPageModel(pageModel);

            addManageService.list(dto);
            dto.setMsgCode(MsgCode.REQUEST_SCCESS);
            dto.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            dto.setMsgCode(MsgCode.REQUEST_FALSE);
            dto.setMsg("系统异常,请联系维护人员");
        }
        return dto.getPageModel();
    }

    @Override
    @RequestMapping("deleteByIds")
    public AddManageDto deleteByIds(AddManageDto dto) {
        try {
            addManageService.deleteByIds(dto);
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
    public AddManageDto findById(AddManageDto dto) {
        try {
            addManageService.findById(dto);
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
    public AddManageDto saveOrUpdate(AddManageDto dto) {
        try {
            addManageService.saveOrUpdate(dto);
            dto.setMsgCode(MsgCode.REQUEST_SCCESS);
            dto.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            dto.setMsgCode(MsgCode.REQUEST_FALSE);
            dto.setMsg("系统异常,请联系维护人员");
        }
        return dto;
    }

    @RequestMapping("getAll")
    public AddManageDto getAll() {
        AddManageDto dto = new AddManageDto();
        try {
            dto.setPos(addManageService.getAll());
            dto.setMsgCode(MsgCode.REQUEST_SCCESS);
            dto.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            dto.setMsgCode(MsgCode.REQUEST_FALSE);
            dto.setMsg("系统异常,请联系维护人员");
        }
        return dto;
    }

    /**
     * @param
     * @return void
     * @throws IOException
     * @throws
     * @Title: upLoadImg
     * @Description: 上传图片
     */
    @RequestMapping("upLoadImg")
    public void upLoadImg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取前端上传的base64数据
        String imgData = req.getParameter("imgData");
        //获取base64数据
        String[] imgDatas = imgData.split("base64,");
        imgData = imgDatas[1];
        //获取图片类型
        String type = imgDatas[0].split("/")[1];
        type = type.substring(0, type.length() - 1);
        //获取图片在服务器上的名字和类型
        String imgUrl = UUID.randomUUID() + "." + type;
        if (imgData != null && !"".equals(imgData)) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                //Base64解码
                byte[] bytes = decoder.decodeBuffer(imgData);
                for (int i = 0; i < bytes.length; ++i) {
                    // 调整异常数据
                    if (bytes[i] < 0) {
                        bytes[i] += 256;
                    }
                }
                //生成jpeg图片
                //新生成的图片
                String imgFilePath = "/Users/misaki/Documents/img/" + imgUrl;
                //生成图片位置
                File dest = new File(imgFilePath);
                if (!dest.getParentFile().exists()) {
                    //父路径是否存在，如果不存在，创建父路径
                    dest.getParentFile().mkdirs();
                }
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(bytes);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        resp.setContentType("text/html;charset=utf-8");
        req.setAttribute("photoUrl", imgUrl);
        resp.getWriter().write(imgUrl);
    }


}
