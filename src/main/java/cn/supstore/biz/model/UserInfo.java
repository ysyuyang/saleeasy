package cn.supstore.biz.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用户登录信息
 * @author hezw
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@JsonIgnoreProperties(ignoreUnknown = true) 
public class UserInfo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5982491506050254392L;
	/*
	 * 企业唯一标识
	 */
	private String appKey;
	/*
	 * 企业密钥
	 */
	private String appSecret;
	/*
	 * 用户ID
	 */
	private Long sysUserId;
	/*
	 * 用户编码
	 */
	private String userCode;
	/* 
	 * 登录名
	 */
	private String loginName;
	/*
	 * 所属机构ID
	 */
	private Long belongOrg;
	/*
	 * 所属机构名称
	 */
	private String belongOrgName;
	/*
	 * 用户名
	 */
	private String name;
	/*
	 * 用户头像
	 */
	private String headPic;
	private String nickName;
	private String appId;
	/*
	 * 用户类别
	 */
	private String userCate;
	/*
	 * 功能权限字串
	 */
	private List funcs;
	/*
	 * 功能模块权限 
	 */
	private List funcsChild;
	
	/*
	 * 数据权限字符串
	 */
	private String datafuncs;
	/*
	 * 简网token
	 */
	private String cuttToken;
	/*
	 * 微信token
	 */
	private String wxToken;
	/*
	 * 微信appId
	 */
	private String wxAppId;
	/*
	 * 微信secret
	 */
	private String wxSecret;
	/*
	 * 七牛上传文件令牌
	 */
	private String upToken;
	
	/*
	 * 用户登录时给个标志
	 */
	private String userToken;
	
	private String gender;
	
	private String password;
	
	private String complateStatus;//完成模块
	
	private int orgType;
	
	private String version;//版本标识,默认位0：旧版本，1：新版本(更新一次，这个标示新版本就加个1，小于当前新版本标示的都视为旧版本。20151104 zhjtao)
	
	
	private String userStatus;
	
	private String hxLoginName;		// 环信用户名
	private String hxPwd;			// 环信密码
	
	private String tel;
	
	private String userPost;// 用户岗位； 
	
	private String systemType;
	private String appVersionName;
	
	private String orgPath;//组织路径
	
	private int popwinFlag=0;//员工关怀弹窗标识 1--生日弹窗、2--入职周年弹窗、3--生日+入职弹窗
	public int getOrgType() {
		return orgType;
	}

	public void setOrgType(int orgType) {
		this.orgType = orgType;
	}

	public String getComplateStatus() {
		return complateStatus;
	}

	public void setComplateStatus(String complateStatus) {
		this.complateStatus = complateStatus;
	}

	private String gradeRank;//成长默认为初级导购1
	public String getGradeRank() {
		return gradeRank;
	}

	public void setGradeRank(String gradeRank) {
		this.gradeRank = gradeRank;
	}

	public UserInfo(){}
	
	public UserInfo(Long sysUserId, String userCode, String loginName, Long belongOrg, String belongOrgName, String name,
			String appId, String userCate, List funcs,List funcsChild,String datafuncs) {
		super();
		this.sysUserId = sysUserId;
		this.userCode = userCode;
		this.loginName = loginName;
		this.belongOrg = belongOrg;
		this.belongOrgName = belongOrgName;
		this.name = name;
		this.appId = appId;
		this.userCate = userCate;
		this.funcs = funcs;
		this.datafuncs = datafuncs;
		this.funcsChild = funcsChild;
	}
	public String getDatafuncs() {
		return datafuncs;
	}

	public void setDatafuncs(String datafuncs) {
		this.datafuncs = datafuncs;
	}
	public List getFuncsChild() {
		return funcsChild;
	}

	public void setFuncsChild(List funcsChild) {
		this.funcsChild = funcsChild;
	}

	public Long getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Long getBelongOrg() {
		return belongOrg;
	}
	public void setBelongOrg(Long belongOrg) {
		this.belongOrg = belongOrg;
	}
	public String getBelongOrgName() {
		return belongOrgName;
	}
	public void setBelongOrgName(String belongOrgName) {
		this.belongOrgName = belongOrgName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public List getFuncs() {
		return funcs;
	}
	public void setFuncs(List funcs) {
		this.funcs = funcs;
	}
	public void addFuncs(Object modId){
		this.funcs.add(modId);
	}

	public String getUserCate() {
		return userCate;
	}

	public void setUserCate(String userCate) {
		this.userCate = userCate;
	}

	public String getCuttToken() {
		return cuttToken;
	}

	public void setCuttToken(String cuttToken) {
		this.cuttToken = cuttToken;
	}

	public String getWxToken() {
		return wxToken;
	}

	public void setWxToken(String wxToken) {
		this.wxToken = wxToken;
	}

	public String getWxAppId() {
		return wxAppId;
	}

	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}

	public String getWxSecret() {
		return wxSecret;
	}

	public void setWxSecret(String wxSecret) {
		this.wxSecret = wxSecret;
	}

	public String getUpToken() {
		return upToken;
	}

	public void setUpToken(String upToken) {
		this.upToken = upToken;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getHxLoginName() {
		return hxLoginName;
	}

	public void setHxLoginName(String hxLoginName) {
		this.hxLoginName = hxLoginName;
	}

	public String getHxPwd() {
		return hxPwd;
	}

	public void setHxPwd(String hxPwd) {
		this.hxPwd = hxPwd;
	}

	public int getPopwinFlag() {
		return popwinFlag;
	}

	public void setPopwinFlag(int popwinFlag) {
		this.popwinFlag = popwinFlag;
	}

	public String getUserPost() {
		return userPost;
	}

	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getAppVersionName() {
		return appVersionName;
	}

	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
	}

	public String getOrgPath() {
		return orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
	
}
