package cn.supstore.biz.model;

import java.util.Date;

public class TtConfigAttr{
  public   Long configId;
  public   Long attrId;
  public   String appId;
  public   Date invalidTime;

  public   Long getConfigId(){
    return configId;
  }
  public   void setConfigId(Long ConfigId){
    configId=ConfigId;
  }
  public   Long getAttrId(){
    return attrId;
  }
  public   void setAttrId(Long AttrId){
    attrId=AttrId;
  }
  public   String getAppId(){
    return appId;
  }
  public   void setAppId(String AppId){
    appId=AppId;
  }
  public   Date getInvalidTime(){
    return invalidTime;
  }
  public   void setInvalidTime(Date InvalidTime){
    invalidTime=InvalidTime;
  }

}