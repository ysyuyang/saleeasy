package cn.supstore.biz.model;

import java.util.Date;

public class TmHouse{
  public   Long houseId;
  public   String houseName;
  public   String houseCode;
  public   Long unitId;
  public   Date createTime;
  public   Long createUser;
  public   Integer delFlag;
  public   String appId;

  public   Long getHouseId(){
    return houseId;
  }
  public   void setHouseId(Long HouseId){
    houseId=HouseId;
  }
  public   String getHouseName(){
    return houseName;
  }
  public   void setHouseName(String HouseName){
    houseName=HouseName;
  }
  public   String getHouseCode(){
    return houseCode;
  }
  public   void setHouseCode(String HouseCode){
    houseCode=HouseCode;
  }
  public   Long getUnitId(){
    return unitId;
  }
  public   void setUnitId(Long UnitId){
    unitId=UnitId;
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