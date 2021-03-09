package com.nwl.lanya.rest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nwl.lanya.common.MsgCode;
import com.nwl.lanya.common.PageModel;
import com.nwl.lanya.common.PropertiesUtil;
import com.nwl.lanya.dto.AddvertisementDto;
import com.nwl.lanya.service.AddvertisementService;

import sun.misc.BASE64Decoder;

@RestController
@RequestMapping("addvertisementRest")
public class AddvertisementRest extends AbstractBaseRest<AddvertisementDto>{
	
	@Autowired
	private AddvertisementService service;
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	@RequestMapping("list")
	@Override
	public PageModel list(Integer page, Integer rows, AddvertisementDto dto) {
		try {
			PageModel pageModel = new PageModel();
			pageModel.setPageNo(page);
			pageModel.setPageSize(rows);
			pageModel.setStartIndex((page-1)*rows);
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

	@RequestMapping("deleteByIds")
	@Override
	public AddvertisementDto deleteByIds(AddvertisementDto dto) {
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

	@RequestMapping("findById")
	@Override
	public AddvertisementDto findById(AddvertisementDto dto) {
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

	@RequestMapping("saveOrUpdate")
	@Override
	public AddvertisementDto saveOrUpdate(AddvertisementDto dto) {
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
	
	/**
	 * @throws IOException 
	 * 
	* @Title: upLoadImg 
	* @Description: 上传图片
	* @param    
	* @return void    
	* @throws
	 */
	@RequestMapping("upLoadImg")
	public void upLoadImg(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String imgData = req.getParameter("imgData");
		String[] imgDatas = imgData.split("base64,");
		imgData = imgDatas[1];
		String type = imgDatas[0].split("/")[1];
		type = type.substring(0,type.length()-1);
		
		 String imgUrl = UUID.randomUUID()+"."+type;
		 if (imgData != null&&!imgData.equals("")){
			    BASE64Decoder decoder = new BASE64Decoder();
		        try {
		            //Base64解码
		        	 byte[] bytes = decoder.decodeBuffer(imgData);
		             for (int i = 0; i < bytes.length; ++i) {
		                 if (bytes[i] < 0) {// 调整异常数据
		                     bytes[i] += 256;
		                 }
		             }
		            //生成jpeg图片
		            String imgFilePath = propertiesUtil.getImgSoureUrl()+imgUrl;//新生成的图片
		            OutputStream out = new FileOutputStream(imgFilePath);
		            out.write(bytes);
		            out.flush();
		            out.close();
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
		 }
	    
	     resp.setContentType("text/html;charset=utf-8");
		 resp.getWriter().write(propertiesUtil.getImgHttpUrl()+imgUrl);
	}
	
	/**
	 * 获取数据库中所有广告数据
	 * @param dto
	 * @return
	 */
	@RequestMapping("getAll")
	public AddvertisementDto getAll(AddvertisementDto dto){
		try {
			service.getAll(dto);
			
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
