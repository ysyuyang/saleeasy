package cn.supstore.biz.model.type;


public class Common {
	
	// 人员类型 （0销售员 1 销售主管 2后台管理员 3 区域经理 4.督导）
	public enum UserType {sales, manager ,admin,areaManager,Steering}
	
	// 假单审批类型（0通过 1 不通过 2待审批 3失效）
	public enum OffworkStatus {pass, reject, wait, Invalid}
	
	// 休假类型（0请假，1休假）
	public enum OffworkType {leave,vacation}
	
	// 模板状态发布（0未发布 1 已发布）
	public enum ReleaseStatus{notreleased, released}
	// 销售类型（店销：shopsales  自带：ownsales）
	public enum salesType{shopsales, ownsales}
	// 品牌类型 自营 selfbrand 竞品 compentbrand
	public enum brandType{selfbrand, compentbrand}

	// 提交表单类型 0我品 1竞品 2连台
	public enum recordType{selfrecord, compentrecord, banquetcompent}
	
	// 签到类型 0签到 1签离
	public enum clockType{sign, signout}
	
	
	// 调动状态0等待 1成功 2失败
	public enum moveStatus {wait, success, fail}
	
	// 工资发放状态0未生效 1已生效
	public enum salaryStatus {erroreffect, successeffect}
	
	// 分值状态（0，未执行1，正执行2，已失效）
	public enum scoreStatus {unexecuted, executed, Invalid}
	// 目标状态0.草稿1.生效
	public enum targetStatus {unexecuted, executed}
	// 计分类型 0.瓶1.分
	public enum countType{bottle, score}
	// 是否考核
	public enum keyType{yes, no}
	// 是否启用
	public enum displayStatus{Disable, Enable}
	
	//登录返回code（101，登录成功；102，账号或密码错误；103，帐号被注销；104，帐号不存在）
	public static int SUCCESS_CODE = 101;
	public static int ACCOUNTS_ERROR_CODE = 102;
	public static int ACCOUNTS_CAN_CODE = 103;
	public static int ACCOUNTS_NO_EXIST = 104;
	
	
	//接口session失效时间
	public static int MEM_CACHE_TIME_API = 30 * 60;
	
	//超导传过来的token失效时间
	public static int TOKEN_DISTORY_TIME = 60*60*10;
	
	/**
	 * 审核状态
	 * @author Administrator
	 * @param adopt 通过；withhold 暂扣；to_audit 待审核；
	 */
	public enum ReviewStatus{adopt, withhold, to_audit}
	
}
