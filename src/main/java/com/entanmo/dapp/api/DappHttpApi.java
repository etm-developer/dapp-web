package com.entanmo.dapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entanmo.dapp.po.Dapp;
import com.entanmo.dapp.util.HttpUtils;
import com.etm.sdk.EtmResult;
import com.etm.sdk.impl.EtmRESTService;
import com.etm.sdk.impl.ParameterMap;
import com.etm.sdk.impl.REST;

public class DappHttpApi {
	String host;
	String masterPassword;

	private static final String PREFIX = "/api/dapps";
	private static final String REGISTER_PATH = PREFIX;
	private static final String INSTALL_PATH = PREFIX + "/install";
	private static final String LAUNCH_PATH = PREFIX + "/launch";
	private static final String UNINSTALL_PATH = PREFIX + "/uninstall";
	private static final String STOP_PATH = PREFIX + "/stop";

	protected static final Logger logger = LoggerFactory.getLogger(EtmRESTService.class);
	private static final String CHAR_SET = "UTF-8";

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getMasterPassword() {
		return masterPassword;
	}

	public void setMasterPassword(String masterPassword) {
		this.masterPassword = masterPassword;
	}
	public DappHttpApi(){
		
	}

	public DappHttpApi(String host, String masterPassword) {
		super();
		this.host = host;
		this.masterPassword = masterPassword;
	}

	public EtmResult register(Dapp dapp, String secret, String secondSecret) {
		ParameterMap parameters = new ParameterMap();
		parameters.put("secret", secret);// publicKey
		parameters.put("secondSecret", secondSecret);
		parameters.put("master", this.masterPassword);
		
		parameters.put("category", dapp.getCategory());
		parameters.put("name", dapp.getName());
		parameters.put("description", dapp.getDescription());
		parameters.put("tags", dapp.getTags());
		parameters.put("type", 0);
		parameters.put("link", dapp.getLink());
		parameters.put("icon", dapp.getIcon());
		
		String parts[] = dapp.getDelegates().split(",");
		//for (String part: parts){
		parameters.put("delegates", parts);
		//}
		
		parameters.put("unlockDelegates", dapp.getUnlockDelegates());
		String url = this.host + REGISTER_PATH;
		String body = parameters.toJSONString();
		try {
			String jsonString = HttpUtils.doPut(url, body, CHAR_SET);
			logger.info("Calling register, name:{}, url:{}, ret:{}", dapp.getName(), url, jsonString);
			return EtmResult.FromJsonString(jsonString);
		} catch (Exception ex) {
			return fail(ex);
		}
	}

	protected EtmResult fail(Exception ex) {
		logger.error("rest call failed", ex);
		return EtmResult.Failed(ex);
	}

	public EtmResult install(String dappId) {
		String url = host + INSTALL_PATH;
		ParameterMap parameters = new ParameterMap();
		parameters.put("id", dappId);
		parameters.put("master", this.masterPassword);
		try {
			String jsonString = REST.post(url, parameters, null, CHAR_SET);
			logger.info("Calling install, dappId:{}, url:{}, ret:{}", dappId, url, jsonString);
			return EtmResult.FromJsonString(jsonString);
		} catch (Exception ex) {
			logger.error("call install failed, url:{}", url, ex);
			return fail(ex);
		}
	}
	
	// state 0, 所有的，  1， 已经安装的， 2， 已经启动的
	public EtmResult getList(Integer category, String name, Integer limit,
			Integer offset, String orderBy){
		//  
		String url = host + PREFIX;
//		, Integer state
//		switch (state){
//		case 1:
//			url = url + "/installed";
//			break;
//		case 2:
//			url = url + "/launched";
//			break;
//		}
		try {
			String jsonString = REST.get(url, null);
			return EtmResult.FromJsonString(jsonString);
		} catch (Exception ex) {
			return fail(ex);
		}		
	}
	
	// TODO:  launch dapp  can add params, which is array type
	public EtmResult launch(String dappId) {
		String url = host + LAUNCH_PATH;
		ParameterMap parameters = new ParameterMap();
		parameters.put("id", dappId);
		parameters.put("master", this.masterPassword);
		// params
		try {
			String jsonString = REST.post(url, parameters, null, CHAR_SET);
			logger.info("Calling launch, dappId:{}, url:{}, ret:{}", dappId, url, jsonString);
			return EtmResult.FromJsonString(jsonString);
		} catch (Exception ex) {
			logger.error("call launch failed, url:{}", url, ex);
			return fail(ex);
		}
	}

	public EtmResult uninstall(String dappId) {
		String url = host + UNINSTALL_PATH;
		ParameterMap parameters = new ParameterMap();
		parameters.put("id", dappId);
		parameters.put("master", this.masterPassword);
		// params
		try {
			String jsonString = REST.post(url, parameters, null, CHAR_SET);
			logger.info("Calling uninstall, dappId:{}, url:{}, ret:{}", dappId, url, jsonString);
			return EtmResult.FromJsonString(jsonString);
		} catch (Exception ex) {
			logger.error("call uninstall failed, url:{}", url, ex);
			return fail(ex);
		}
	}
	
	public EtmResult stop(String dappId) {
		String url = host + STOP_PATH;
		ParameterMap parameters = new ParameterMap();
		parameters.put("id", dappId);
		parameters.put("master", this.masterPassword);
		// params
		try {
			String jsonString = REST.post(url, parameters, null, CHAR_SET);
			logger.info("Calling stop, dappId:{}, url:{}, ret:{}", dappId, url, jsonString);
			return EtmResult.FromJsonString(jsonString);
		} catch (Exception ex) {
			logger.error("call stop failed, url:{}", url, ex);
			return fail(ex);
		}
	}
	
	public static void main(String[] args) {
		DappHttpApi api = new DappHttpApi("http://118.24.135.98:4196", "ytfACAMegjrK");
		String dappId = "b85ecb29e22ffdf46ec149ce36526555bfd3b8afdca9f7307b038656a3f467b2";
		EtmResult result = api.install(dappId);
		if (result.isSuccessful()) {
			System.out.println("uninstall with success");
		}else {
			System.out.println(result.getError());
		}
	}
}
