//package com.entanmo.dapp.api;
//
//
//
//import com.alibaba.fastjson.JSONObject;
//
//import java.util.Map;
//
//public class EtmResult {
//    public boolean isSuccessful() {
//        return successful;
//    }
//
//    public String getError() {
//        return error;
//    }
//
//    public String getRawJson() {
//        return rawJson;
//    }
//    public Exception getException() {
//        return exception;
//    }
//
//    private boolean successful = false;
//    private String error = null;
//    private String rawJson = null;
//    private Exception exception;
//    private JSONObject jsonObject;
//
//    public boolean isLocalError(){
//        return exception != null;
//    }
//
//    public Map<String, Object> parseMap(){
//        return JSONObject.parseObject(rawJson);
//    }
//
//    public <T> T parseObject(Class<T>  classzz){
//        return jsonObject == null ?
//                null :
//                jsonObject.toJavaObject(classzz);
//    }
//
//    public String toString(){
//        return String.format("success:%s, error:%s, exception:%s rawJson:%s",
//                this.successful,
//                this.error,
//                this.exception,
//                this.rawJson);
//    }
//
//    public static EtmResult FromJsonString(String jsonString){
//        EtmResult result = new EtmResult();
//
//        result.rawJson = jsonString;
//        result.jsonObject = JSONObject.parseObject(jsonString);
//
//        JSONObject json = result.jsonObject;
//        if (json.containsKey("success")){
//            result.successful = json.getBoolean("success");
//        }
//
//        if (json.containsKey("error")){
//            result.error = json.getString("error");
//        }
//
//        return result;
//    }
//
//    public static EtmResult Failed(Exception ex){
//        EtmResult result = new EtmResult();
//        result.successful = false;
//        result.error = ex.getMessage();
//        result.exception = ex;
//
//        return result;
//    }
//}
