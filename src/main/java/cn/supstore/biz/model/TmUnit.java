package cn.supstore.biz.model;

import java.util.Date;

public class TmUnit{
  public   Long unitId;
  public   String unitName;
  public   String unitCode;
  public   Long buildingId;
  public   Date createTime;
  public   Long createUser;
  public   Integer delFlag;
  public   String appId;

  public   Long getUnitId(){
    return unitId;
  }
  public   void setUnitId(Long UnitId){
    unitId=UnitId;
  }
  public   String getUnitName(){
    return unitName;
  }
  public   void setUnitName(String UnitName){
    unitName=UnitName;
  }
  public   String getUnitCode(){
    return unitCode;
  }
  public   void setUnitCode(String UnitCode){
    unitCode=UnitCode;
  }
  public   Long getBuildingId(){
    return buildingId;
  }
  public   void setBuildingId(Long BuildingId){
    buildingId=BuildingId;
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