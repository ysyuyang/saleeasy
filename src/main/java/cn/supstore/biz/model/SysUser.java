package cn.supstore.biz.model;

import java.util.Date;

public class SysUser{
  public   Long sysUserId;
  public   String loginName;
  public   String password;
  public   String name;
  public   String gender;
  public   String tel;
  public   Date createTime;
  public   Date editTime;
  public   String createUser;
  public   String userCate;
  public   String email;
  public   String appId;
  public   Long qq;
  public   String wx;
  public   String headPic;
  public   String nickName;
  public   Date birthday;
  public   Integer userStauts;

  public   Long getSysUserId(){
    return sysUserId;
  }
  public   void setSysUserId(Long SysUserId){
    sysUserId=SysUserId;
  }
  public   String getLoginName(){
    return loginName;
  }
  public   void setLoginName(String LoginName){
    loginName=LoginName;
  }
  public   String getPassword(){
    return password;
  }
  public   void setPassword(String Password){
    password=Password;
  }
  public   String getName(){
    return name;
  }
  public   void setName(String Name){
    name=Name;
  }
  public   String getGender(){
    return gender;
  }
  public   void setGender(String Gender){
    gender=Gender;
  }
  public   String getTel(){
    return tel;
  }
  public   void setTel(String Tel){
    tel=Tel;
  }
  public   Date getCreateTime(){
    return createTime;
  }
  public   void setCreateTime(Date CreateTime){
    createTime=CreateTime;
  }
  public   Date getEditTime(){
    return editTime;
  }
  public   void setEditTime(Date EditTime){
    editTime=EditTime;
  }
  public   String getCreateUser(){
    return createUser;
  }
  public   void setCreateUser(String CreateUser){
    createUser=CreateUser;
  }
  public   String getUserCate(){
    return userCate;
  }
  public   void setUserCate(String UserCate){
    userCate=UserCate;
  }
  public   String getEmail(){
    return email;
  }
  public   void setEmail(String Email){
    email=Email;
  }
  public   String getAppId(){
    return appId;
  }
  public   void setAppId(String AppId){
    appId=AppId;
  }
  public   Long getQq(){
    return qq;
  }
  public   void setQq(Long Qq){
    qq=Qq;
  }
  public   String getWx(){
    return wx;
  }
  public   void setWx(String Wx){
    wx=Wx;
  }
  public   String getHeadPic(){
    return headPic;
  }
  public   void setHeadPic(String HeadPic){
    headPic=HeadPic;
  }
  public   String getNickName(){
    return nickName;
  }
  public   void setNickName(String NickName){
    nickName=NickName;
  }
  public   Date getBirthday(){
    return birthday;
  }
  public   void setBirthday(Date Birthday){
    birthday=Birthday;
  }
  public   Integer getUserStauts(){
    return userStauts;
  }
  public   void setUserStauts(Integer UserStauts){
    userStauts=UserStauts;
  }

}