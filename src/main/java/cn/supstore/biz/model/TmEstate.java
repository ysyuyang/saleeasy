package cn.supstore.biz.model;

import java.util.Date;

public class TmEstate{
  public   Long estateId;
  public   String estateName;
  public   Date createTime;
  public   Long createUser;
  public   String tel;
  public   String address;
  public   Integer delFlag;
  public   String appId;

  public   Long getEstateId(){
    return estateId;
  }
  public   void setEstateId(Long EstateId){
    estateId=EstateId;
  }
  public   String getEstateName(){
    return estateName;
  }
  public   void setEstateName(String EstateName){
    estateName=EstateName;
  }
  public   Date getCreateTime(){
    return createTime;
  }
  public   void setCreateTime(Date CreateTime){
    createTime=CreateTime;
  }
  public   Long getCreateUser(){
    return createUser;
  }
  public   void setCreateUser(Long CreateUser){
    createUser=CreateUser;
  }
  public   String getTel(){
    return tel;
  }
  public   void setTel(String Tel){
    tel=Tel;
  }
  public   String getAddress(){
    return address;
  }
  public   void setAddress(String Address){
    address=Address;
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