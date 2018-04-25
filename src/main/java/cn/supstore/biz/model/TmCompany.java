package cn.supstore.biz.model;

import java.util.Date;

public class TmCompany{
  public   Long companyId;
  public   String companyName;
  public   Date createTime;
  public   Long createUser;
  public   Integer delFlag;
  public   String companyCode;

  public   Long getCompanyId(){
    return companyId;
  }
  public   void setCompanyId(Long CompanyId){
    companyId=CompanyId;
  }
  public   String getCompanyName(){
    return companyName;
  }
  public   void setCompanyName(String CompanyName){
    companyName=CompanyName;
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
  public   String getCompanyCode(){
    return companyCode;
  }
  public   void setCompanyCode(String CompanyCode){
    companyCode=CompanyCode;
  }

}