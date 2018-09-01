package com.entanmo.dapp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.entanmo.dapp.api.DappHttpApi;
import com.entanmo.dapp.dao.enums.DappStateEnum;
//import com.entanmo.dapp.api.EtmResult;
import com.entanmo.dapp.dao.enums.MemberTypeEnum;
import com.entanmo.dapp.dao.enums.ResponseEnum;
import com.entanmo.dapp.param.DappParam;
import com.entanmo.dapp.po.Dapp;
import com.entanmo.dapp.po.EtmNode;
import com.entanmo.dapp.po.Member;
import com.entanmo.dapp.service.DappService;
import com.entanmo.dapp.service.EtmNodeService;
import com.entanmo.dapp.util.HttpUtils;
import com.entanmo.dapp.vo.BaseResponse;
import com.entanmo.dapp.vo.PaginationResult;
import com.etm.sdk.EtmResult;

/**
 * 
 * 控制层
 * 
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/dapp")
@Controller
public class DappController extends BaseController {

	@Autowired
	private DappService dappService;
	
	@Autowired
	private EtmNodeService etmNodeService;
	
	@Autowired
	private DappHttpApi dappHttpApi;

	private static final Logger logger = LoggerFactory.getLogger(DappController.class);

	@Value("#{config['dapp.upload.dir']}")
	private String uploadPath = "c:/upload";

	@Value("#{config['dapp.url.prefix']}")
	private String linkPrefix = "http://resources.entanmo.com/dapp/uploads";

	@RequestMapping(path = "/putDapp.do")
	@ResponseBody
	public BaseResponse putDapp(HttpServletRequest req, String name, String description) throws Exception {
		String content = HttpUtils.readPostContent(req);
		BaseResponse response = new BaseResponse();
		response.setCode(ResponseEnum.SUCCESS.getCode());
		response.setData(content);
		return response;
	}

	/**
	 * 
	 * 分页查询方法
	 * 
	 */
	@RequestMapping("/getList.do")
	@ResponseBody
	public BaseResponse getList(HttpServletRequest req, String name, Integer category, Integer creatorId, Integer state,
			Integer pageNo, Integer pageSize) {

		BaseResponse resp = new BaseResponse();
		Member loginUser = getLoginMember(req);
		if (loginUser == null) {
			resp.setResp(ResponseEnum.NOT_LOGIN);
			return resp;
		}

		if (loginUser.getUserType() == MemberTypeEnum.PROVIDER.getType()) {
			resp.setResp(ResponseEnum.NO_RIGHTS);
			return resp;
		}

		DappParam param = new DappParam();
		param.setPageNo(pageNo);
		param.setPageSize(pageSize);
		param.setState(state);
		if (!StringUtils.isEmpty(name)){
			param.setNameFuzzy(name);
		}
		param.setCreatorId(creatorId);
		param.setCategory(category);
		param.setOrderBy("updateTime desc");

		if (loginUser.getUserType() == MemberTypeEnum.DEVELOPER.getType()) {
			param.setCreatorId(loginUser.getMemberId());
		}

		PaginationResult<Dapp> result = this.dappService.findListByPage(param);
		resp.setMessage("查询成功");
		resp.setData(result);
		return resp;
	}

	private boolean saveFile(MultipartFile file, String savePath) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 转存文件
				file.transferTo(new File(savePath));
				// readZipFile(savePath);
				return true;
			} catch (Exception e) {
				logger.error("save zip file failed", e);
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@RequestMapping("/create.do")
	@ResponseBody
	public BaseResponse create(HttpServletRequest request, Dapp dapp) {
		BaseResponse resp = new BaseResponse();
		
		if (StringUtils.isEmpty(dapp.getName())){
			resp.setResp(ResponseEnum.BAD_PARAM);
			resp.setMessage("名称不能为空");
			return resp;
		}
		
		if (StringUtils.isEmpty(dapp.getDelegates())){
			resp.setResp(ResponseEnum.BAD_PARAM);
			resp.setMessage("delegate列表不能为空");
			return resp;
		}
		
		if (StringUtils.isEmpty(dapp.getSecrets())){
			resp.setResp(ResponseEnum.BAD_PARAM);
			resp.setMessage("delegate主密码不能为空");
			return resp;
		}
		
		int len = dapp.getDelegates().split(",").length;
		if (len < 5){
			resp.setResp(ResponseEnum.BAD_PARAM);
			resp.setMessage("delegate数量至少为5");
			return resp;
		}
		if (dapp.getSecrets().split(",").length != len){
			resp.setResp(ResponseEnum.BAD_PARAM);
			resp.setMessage("delegate数量和主密码数量必须一致");
			return resp;
		}
		
		if (dapp.getUnlockDelegates() == null || dapp.getUnlockDelegates() > len ){
			resp.setResp(ResponseEnum.BAD_PARAM);
			resp.setMessage("交易认可需要人数无效或超过delegate数量");
			return resp;
		}
		
		if (StringUtils.isEmpty(dapp.getLink())){
			resp.setResp(ResponseEnum.BAD_PARAM);
			resp.setMessage("请上传代码文件");
			return resp;
		}
		
		if (StringUtils.isEmpty(dapp.getIcon())){
			resp.setResp(ResponseEnum.BAD_PARAM);
			resp.setMessage("请上传图标文件");
			return resp;
		}
		
		Date now = new Date();
		dapp.setCreateTime(now);
		dapp.setUpdateTime(now);
		try {
			dappService.add(dapp);
		}catch (Exception e){
			logger.error("add dapp fail", e);
		}
		resp.setResp(ResponseEnum.SUCCESS);
		resp.setData(dapp.getDappId());
		return resp;
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public BaseResponse update(HttpServletRequest request, Dapp dapp) {
		BaseResponse resp = new BaseResponse();
		
		Integer dappId = dapp.getDappId();
		
		if (dappId == null) {
			resp.setResp(ResponseEnum.BAD_PARAM);
			resp.setMessage("参数错误");
			return resp;
		}
		Dapp saved = dappService.getDappByPrimaryKey(dappId);
		if (saved == null) {
			resp.setResp(ResponseEnum.BAD_PARAM);
			resp.setMessage("dapp不存在");
			return resp;
		}
		Date now = new Date();
		dapp.setUpdateTime(now);
		try {
			dappService.update(dapp);
		}catch (Exception e){
			logger.error("add dapp fail", e);
		}
		resp.setResp(ResponseEnum.SUCCESS);
		resp.setMessage("更新成功");
		return resp;
	}

	@RequestMapping("/upload.do")
	@ResponseBody
	public BaseResponse upload(HttpServletRequest request, MultipartFile files) {
		BaseResponse resp = new BaseResponse();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		String resourceUrl = "";
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator<String> iterator = multiRequest.getFileNames();
			String md5 = null;
			while (iterator.hasNext()) {
				MultipartFile multipartFile = multiRequest.getFile(iterator.next().toString());
				try {
					InputStream inputStream = multipartFile.getInputStream();
					md5 = DigestUtils.md5Hex(inputStream);
					inputStream.close();
				} catch (Exception e) {
					logger.error("upload get file md5 fail", e);
					resp.setCode(ResponseEnum.LOGIC_ERROR.getCode());
					resp.setMessage("获取文件md5失败");
					return resp;
				}
				
				String originalName = multipartFile.getOriginalFilename();
				String extension = originalName.substring(originalName.lastIndexOf('.'));
				File destDir = null;
				if (originalName.endsWith(".zip")){
					destDir = new File(uploadPath, "zip");
					resourceUrl = resourceUrl + "/zip";
					
				}else if (originalName.endsWith(".png") || originalName.endsWith(".jpg") 
						|| originalName.endsWith(".jpeg")){
					destDir = new File(uploadPath, "images");
					resourceUrl = resourceUrl + "/images";
				}
				destDir.mkdirs();
				File destFile = new File(destDir, md5 + extension);
				if (!destFile.exists()) {
					saveFile(multipartFile, destFile.getAbsolutePath());
				}
				resourceUrl = resourceUrl + "/" + md5 + extension;
				break;
			}
		}
		resp.setData(linkPrefix + resourceUrl);
		resp.setResp(ResponseEnum.SUCCESS);
		return resp;
	}
	
	@RequestMapping("/install.do")
	@ResponseBody
	public BaseResponse install(HttpServletRequest req, Integer dappId) {
		BaseResponse resp = new BaseResponse();
		Member member = this.getLoginMember(req);
		if (member == null) {
			resp.setResp(ResponseEnum.NOT_LOGIN);
			return resp;
		}
		if (member.getUserType() != MemberTypeEnum.DEVELOPER.getType()) {
			resp.setResp(ResponseEnum.NO_RIGHTS);
			return resp;
		}
		Dapp dapp = dappService.getDappByPrimaryKey(dappId);
		if (dapp == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("Dapp不存在");
			return resp;
		}
		if (!dapp.getCreatorId().equals(member.getMemberId()) ) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
		    resp.setMessage("不能操作他人的dapp");
			return resp;
		}
		if (dapp.getState() != DappStateEnum.REGISTERED.getType() &&
				dapp.getState() != DappStateEnum.REMOVED.getType()) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
		    resp.setMessage("只有注册的或已经移除的dapp 才能安装");
			return resp;
		}
		
		EtmResult result = dappHttpApi.install(dapp.getTransactionId());
		if (!result.isSuccessful()) {
			resp.setCode(ResponseEnum.LOGIC_ERROR.getCode());
		    resp.setMessage(result.getError());
			return resp;
		}else {
			JSONObject json = new JSONObject(result.getRawJson());

			dapp.setState(DappStateEnum.INSTALLED.getType());
			dapp.setUpdateTime(new Date());
			dappService.update(dapp);
		}
		resp.setResp(ResponseEnum.SUCCESS);
		return resp;
	}
	
	@RequestMapping("/launch.do")
	@ResponseBody
	public BaseResponse launch(HttpServletRequest req, Integer dappId) {
		BaseResponse resp = new BaseResponse();
		Member member = this.getLoginMember(req);
		if (member == null) {
			resp.setResp(ResponseEnum.NOT_LOGIN);
			return resp;
		}
		if (member.getUserType() != MemberTypeEnum.DEVELOPER.getType()) {
			resp.setResp(ResponseEnum.NO_RIGHTS);
			return resp;
		}
		Dapp dapp = dappService.getDappByPrimaryKey(dappId);
		if (dapp == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("Dapp不存在");
			return resp;
		}
		if (!dapp.getCreatorId().equals(member.getMemberId()) ) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
		    resp.setMessage("不能操作他人的dapp");
			return resp;
		}
		if (dapp.getState() != DappStateEnum.INSTALLED.getType() &&
				dapp.getState() != DappStateEnum.STOPED.getType()) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
		    resp.setMessage("只有注册的dapp或者已经停止的应用才能启动");
			return resp;
		}
		
		EtmResult result = dappHttpApi.launch(dapp.getTransactionId());
		if (!result.isSuccessful()) {
			resp.setCode(ResponseEnum.LOGIC_ERROR.getCode());
		    resp.setMessage(result.getError());
			return resp;
		}else {
			JSONObject json = new JSONObject(result.getRawJson());

			dapp.setState(DappStateEnum.LAUNCHED.getType());
			dapp.setUpdateTime(new Date());
			dappService.update(dapp);
		}
		resp.setResp(ResponseEnum.SUCCESS);
		return resp;
	}
	
	@RequestMapping("/uninstall.do")
	@ResponseBody
	public BaseResponse uninstall(HttpServletRequest req, Integer dappId) {
		BaseResponse resp = new BaseResponse();
		Member member = this.getLoginMember(req);
		if (member == null) {
			resp.setResp(ResponseEnum.NOT_LOGIN);
			return resp;
		}
		if (member.getUserType() != MemberTypeEnum.DEVELOPER.getType()) {
			resp.setResp(ResponseEnum.NO_RIGHTS);
			return resp;
		}
		Dapp dapp = dappService.getDappByPrimaryKey(dappId);
		if (dapp == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("Dapp不存在");
			return resp;
		}
		if (!dapp.getCreatorId().equals(member.getMemberId()) ) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
		    resp.setMessage("不能操作他人的dapp");
			return resp;
		}
		if (dapp.getState() != DappStateEnum.INSTALLED.getType() &&
				dapp.getState() != DappStateEnum.LAUNCHED.getType()) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
		    resp.setMessage("只有已安装或启动的dapp才能启动");
			return resp;
		}
		
		EtmResult result = dappHttpApi.uninstall(dapp.getTransactionId());
		if (!result.isSuccessful()) {
			resp.setCode(ResponseEnum.LOGIC_ERROR.getCode());
		    resp.setMessage(result.getError());
			return resp;
		}else {
			JSONObject json = new JSONObject(result.getRawJson());

			dapp.setState(DappStateEnum.LAUNCHED.getType());
			dapp.setUpdateTime(new Date());
			dappService.update(dapp);
		}
		resp.setResp(ResponseEnum.SUCCESS);
		return resp;
	}
	
	@RequestMapping("/stop.do")
	@ResponseBody
	public BaseResponse stop(HttpServletRequest req, Integer dappId) {
		BaseResponse resp = new BaseResponse();
		Member member = this.getLoginMember(req);
		if (member == null) {
			resp.setResp(ResponseEnum.NOT_LOGIN);
			return resp;
		}
		if (member.getUserType() != MemberTypeEnum.DEVELOPER.getType()) {
			resp.setResp(ResponseEnum.NO_RIGHTS);
			return resp;
		}
		Dapp dapp = dappService.getDappByPrimaryKey(dappId);
		if (dapp == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("Dapp不存在");
			return resp;
		}
		if (!dapp.getCreatorId().equals(member.getMemberId()) ) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
		    resp.setMessage("不能操作他人的dapp");
			return resp;
		}
		if (dapp.getState() != DappStateEnum.LAUNCHED.getType()) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
		    resp.setMessage("只有已启动的应用才能启动");
			return resp;
		}
		
		EtmResult result = dappHttpApi.launch(dapp.getTransactionId());
		if (!result.isSuccessful()) {
			resp.setCode(ResponseEnum.LOGIC_ERROR.getCode());
		    resp.setMessage(result.getError());
			return resp;
		}else {
			JSONObject json = new JSONObject(result.getRawJson());

			dapp.setState(DappStateEnum.LAUNCHED.getType());
			dapp.setUpdateTime(new Date());
			dappService.update(dapp);
		}
		resp.setResp(ResponseEnum.SUCCESS);
		return resp;
	}
	
	@RequestMapping("/updateState.do")
	@ResponseBody
	public BaseResponse updateState(HttpServletRequest req, Integer dappId, Integer state) {
		BaseResponse resp = new BaseResponse();
		Member member = this.getLoginMember(req);
		if (member == null) {
			resp.setResp(ResponseEnum.NOT_LOGIN);
			return resp;
		}
//		if (member.getUserType() != MemberTypeEnum.DEVELOPER.getType()) {
//			resp.setResp(ResponseEnum.NO_RIGHTS);
//			return resp;
//		}
		Dapp dapp = dappService.getDappByPrimaryKey(dappId);
		if (dapp == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("Dapp不存在");
			return resp;
		}
//		if (!dapp.getCreatorId().equals(member.getMemberId()) ) {
//			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
//		    resp.setMessage("不能操作他人的dapp");
//			return resp;
//		}
		DappStateEnum currentState = DappStateEnum.getByType(dapp.getState());
		
		DappStateEnum stateEnum = DappStateEnum.getByType(state);
		if (stateEnum == null || stateEnum == DappStateEnum.CREATED ||
				stateEnum == DappStateEnum.REGISTERED){
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
		    resp.setMessage("state参数错误");
			return resp;
		}
		if (currentState == stateEnum) {
			resp.setResp(ResponseEnum.SUCCESS);
			return resp;
		}
		EtmResult result = null;
		selectRandomNode();
		switch (stateEnum){
		  case INSTALLED: {
			if (currentState != DappStateEnum.REGISTERED &&
					currentState != DappStateEnum.REMOVED){
				resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			    resp.setMessage("只有已注册的或已经移除的dapp才能安装");
				return resp;
			}
			result = dappHttpApi.install(dapp.getTransactionId());
			break;
		  }
		  case LAUNCHED: {
			if (currentState != DappStateEnum.INSTALLED &&
					currentState != DappStateEnum.STOPED){
				resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			    resp.setMessage("只有已安装或已经停止的dapp才能启动");
				return resp;
			}
			result = dappHttpApi.launch(dapp.getTransactionId());
			break;
		  }
		  case STOPED: {
				if (currentState != DappStateEnum.LAUNCHED){
					resp.setCode(ResponseEnum.BAD_PARAM.getCode());
				    resp.setMessage("只有已启动的dapp才能停止");
					return resp;
				}
				result = dappHttpApi.stop(dapp.getTransactionId());
				break;
			  }
		  case REMOVED: {
				if (currentState != DappStateEnum.INSTALLED &&
						currentState != DappStateEnum.LAUNCHED &&
						currentState != DappStateEnum.STOPED){
					resp.setCode(ResponseEnum.BAD_PARAM.getCode());
				    resp.setMessage("只有已启动或已经停止的dapp才能卸载");
					return resp;
				}
				result = dappHttpApi.uninstall(dapp.getTransactionId());
				break;
			  }
		}
		
		if (!result.isSuccessful()) {
			resp.setCode(ResponseEnum.LOGIC_ERROR.getCode());
		    resp.setMessage(result.getError());
			return resp;
		}else {
			JSONObject json = new JSONObject(result.getRawJson());

			dapp.setState(state);
			dapp.setUpdateTime(new Date());
			dappService.update(dapp);
		}
		resp.setResp(ResponseEnum.SUCCESS);
		return resp;
	}
	
	@RequestMapping("/register.do")
	@ResponseBody
	public BaseResponse register(HttpServletRequest req, String secret,
			String secondSecret, Integer dappId) {
		BaseResponse resp = new BaseResponse();
		if (StringUtils.isEmpty(secret) || dappId == null || dappId <= 0) {
			resp.setResp(ResponseEnum.BAD_PARAM);
			return resp;
		}
		
		Member member = this.getLoginMember(req);
		if (member == null) {
			resp.setResp(ResponseEnum.NOT_LOGIN);
			return resp;
		}
//		if (member.getUserType() != MemberTypeEnum.DEVELOPER.getType()) {
//			resp.setResp(ResponseEnum.NO_RIGHTS);
//			return resp;
//		}
		Dapp dapp = dappService.getDappByPrimaryKey(dappId);
		if (dapp == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("Dapp不存在");
			return resp;
		}
//		if (!dapp.getCreatorId().equals(member.getMemberId()) ) {
//			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
//		    resp.setMessage("不能操作他人的dapp");
//			return resp;
//		}
		selectRandomNode();
		EtmResult result = dappHttpApi.register(dapp, secret, secondSecret);
		if (!result.isSuccessful()) {
			resp.setCode(ResponseEnum.LOGIC_ERROR.getCode());
		    resp.setMessage(result.getError());
			return resp;
		}else {
			JSONObject json = new JSONObject(result.getRawJson());
			//success:true, error:null, exception:null rawJson:{"success":true,"transaction":{"type":5,"amount":0,"senderPublicKey":"fd6df6dc35852ac7edcc081eb5195718e0c77a6ad4f8157eeb78c865fa83efc4","requesterPublicKey":null,"timestamp":67416642,"asset":{"dapp":{"category":1,"name":"cctime3","description":"news","tags":"news","type":0,"link":"http://116.211.100.207/dapp/upload/zip/e11e06cbab9d67572ed9bbd8848a6caa.zip","icon":"http://116.211.100.207/dapp/upload/images/897c565e4e14453dcc3379f74a5fca26.png","delegates":["db18d5799944030f76b6ce0879b1ca4b0c2c1cee51f53ce9b43f78259950c2fd","590e28d2964b0aa4d7c7b98faee4676d467606c6761f7f41f99c52bb4813b5e4","bfe511158d674c3a1e21111223a49770bee93611d998e88a5d2ea3145de2b68b","7bbf62931cf3c596591a580212631aff51d6bc0577c54769953caadb23f6ab00","452df9213aedb3b9fed6db3e2ea9f49d3db226e2dac01828bc3dcd73b7a953b4"],"unlockDelegates":3}},"recipientId":null,"signature":"8b29da99d4bde9dbcde41b869e878e1f6890485373cd67e551fe793f770e8d86464e74c9dc42cfa6c7ad55662ba476567818be91587132a77b6a1148f87f7704","id":"bfa7db447779a4132e52f705fd5a2e818b6201b9fbc44a5dac4fb73018a3c1e4","fee":10000000000,"senderId":"7286541193277597873"}}
			String transactionId = json.getJSONObject("transaction").getString("id");
			dapp.setTransactionId(transactionId);
			dapp.setState(DappStateEnum.REGISTERED.getType());
			dapp.setUpdateTime(new Date());
			dappService.update(dapp);
		}
		resp.setResp(ResponseEnum.SUCCESS);
		return resp;
	}
	
	private void selectRandomNode(){
		List<EtmNode> nodeList = etmNodeService.getRandomUsableNode(1);
		if (nodeList.isEmpty()){
			return;
		}
		EtmNode node = nodeList.get(0);
		String host = "http://" + node.getIp() + ":" + node.getPort();
		dappHttpApi.setHost(host);
		dappHttpApi.setMasterPassword(node.getMasterPassword());
	}
	
	

	@RequestMapping("/uploadByFile.do")
	@ResponseBody
	public BaseResponse uploadByFile(HttpServletRequest req, @RequestParam("files") MultipartFile file, String md5) {
		BaseResponse resp = new BaseResponse();
		Member loginUser = getLoginMember(req);
		if (loginUser == null) {
			resp.setResp(ResponseEnum.NOT_LOGIN);
			return resp;
		}

		if (loginUser.getUserType() == MemberTypeEnum.PROVIDER.getType()) {
			resp.setResp(ResponseEnum.NO_RIGHTS);
			return resp;
		}
		//
		// if (StringUtils.isEmpty(md5)) {
		// resp.setCode(ResponseEnum.BAD_PARAM.getCode());
		// resp.setMessage("文件md5不能为空");
		// return resp;
		// }

		File f = new File(uploadPath, md5 + ".zip");
		String destPath = f.getAbsolutePath();
		String originalName = file.getOriginalFilename();
		if (!originalName.endsWith(".zip")) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("文件必须为zip文件");
			return resp;
		}
		Dapp dapp = null;
		try {
			// String path = "E:/upload/";
			// 判断file数组不能为空并且长度大于0
			if (file != null) {
				// 循环获取file数组中得文件
				if (saveFile(file, uploadPath)) {
					// String destPath = path + dapp.getName() + ".zip";
					dapp = readZipFile(destPath);
					if (dapp == null) {
						new File(destPath).delete();
						resp.setCode(ResponseEnum.BAD_PARAM.getCode());
						resp.setMessage("压缩文件格式不正确");
						return resp;
					}

					DappParam param = new DappParam();
					param.setName(dapp.getName());
					if (dappService.getCount(param) > 0) {
						new File(destPath).delete();
						resp.setCode(ResponseEnum.BAD_PARAM.getCode());
						resp.setMessage("同名应用已经存在");
						return resp;
					}
					dapp.setCreateTime(new Date());
					// dapp.setCreatorId(loginUser.getMemberId());
					dapp.setCreatorId(0);
					dapp.setLink(linkPrefix + dapp.getName() + ".zip");
					dappService.add(dapp);
				}
			}
		} catch (Exception e) {
			resp.setCode(ResponseEnum.LOGIC_ERROR.getCode());
			resp.setMessage("操作失败");
			logger.error("upload file fail", e);
			return resp;
		}
		resp.setMessage("上传成功");
		return resp;
	}

	public static Dapp readZipFile(String file) throws Exception {
		ZipFile zf = new ZipFile(file);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		Dapp dapp = null;
		String secrets = null;
		while ((ze = zin.getNextEntry()) != null) {
			String name = ze.getName();

			if (ze.isDirectory()) {

			} else {
				if (name.equals("config.json") || name.equals("dapp.json")) {
					long size = ze.getSize();
					StringBuffer buffer = new StringBuffer();
					if (size > 0) {
						BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
						String line;
						while ((line = br.readLine()) != null) {
							buffer.append(line);
						}
						br.close();
					}
					JSONObject json = new JSONObject(buffer.toString());
					if (name.equals("dapp.json")) {
						dapp = new Dapp();
						dapp.parseFromJson(json);
					} else {
						if (json.optJSONArray("secrets") != null) {
							secrets = json.getJSONArray("secrets").toString();
						}
					}
				}
			}
			zin.closeEntry();
		}
		zin.close();
		in.close();
		zf.close();
		if (dapp != null) {
			dapp.setSecrets(secrets);
		}
		return dapp;
	}

	@RequestMapping("/uploadWithDetail.do")
	@ResponseBody
	public BaseResponse uploadWithDetail() {
		return null;
	}
}
