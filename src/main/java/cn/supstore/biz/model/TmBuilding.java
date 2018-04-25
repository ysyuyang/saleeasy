package cn.supstore.biz.model;

import java.util.Date;

public class TmBuilding{
  public   Long buildingId;
  public   String buildingName;
  public   String buildingCode;
  public   Date createTime;
  public   Long createUser;
  public   Integer delFlag;
  public   String appId;

  public   Long getBuildingId(){
    return buildingId;
  }
  public   void setBuildingId(Long BuildingId){
    buildingId=BuildingId;
  }
  public   String getBuildingName(){
    return buildingName;
  }
  public   void setBuildingName(String BuildingName){
    buildingName=BuildingName;
  }
  public   String getBuildingCode(){
    return buildingCode;
  }
  public   void setBuildingCode(String BuildingCode){
    buildingCode=BuildingCode;
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