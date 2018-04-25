package cn.supstore.biz.model;

 public class TmCommonAttr{
  public   Long attrId;
  public   String attrName;
  public   Integer delFlag;
  public   String appId;

  public   Long getAttrId(){
    return attrId;
  }
  public   void setAttrId(Long AttrId){
    attrId=AttrId;
  }
  public   String getAttrName(){
    return attrName;
  }
  public   void setAttrName(String AttrName){
    attrName=AttrName;
  }
  public   Integer getDelFlag(){
    return delFlag;
  }
  public   void setDelFlag(Integer DelFlag){
    delFlag=DelFlag;
  }
  public   String getAppId(){
    return appId;
  }
  public   void setAppId(String AppId){
    appId=AppId;
  }

}