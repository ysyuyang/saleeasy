<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	    <script type="text/javascript" src="./res/jquery/jquery-1.11.2.min.js" ></script>
		<script type="text/javascript" src="./res/bootstrap/js/bootstrap.min.js" ></script>
		<script type="text/javascript" src="./res/bootstrap/js/bootstrap-treeview.js" ></script>
		<script type="text/javascript" src="./res/bootstrap/js/bootstrap-paginator.js"></script>
		<script type="text/javascript" src="./res/js/commonUtil.js"></script>
		<link rel="stylesheet" href="./res/bootstrap/css/bootstrap.css" />
		<script type="text/javascript" src="./res/layer/layer.js" ></script>
		<link rel="stylesheet" href="./res/css/index.css" />
		<link rel="stylesheet" href="./res/css/style.css" />
			<link rel="stylesheet" href="./res/css/salary.css" />
					<script type="text/javascript" src="./res/js/menu.js"></script>
		<style>
			.allleft{
				margin-left:30px;
				position:relative;
			}


			.column-data {
				width: 100%;
				display: table;
			}
			
			.column-data .column-data-row { display: table-row; }
			.column-data .column-data-row .column-data-cell {
				padding: 5px;
				display: table-cell;
				text-align: left;
				vertical-align: middle;
			}
		</style>
		<title>工资管理</title>
	</head>
	<body>
	<div class="all">
		<div class="header">
			<!-- <div class="xxylogo">
				<img src="./image/lala.png"/>
			</div> -->
			<div class="headerright">
			<span><!-- <img src="./image/group.png" style="margin-left:20px;"/> --> 超级导购.管理平台</span>
			<span></span>
			</div>
		</div>
		<div class="main">
		<!-- 	<div class="banner">
				<div class="item"></div>
			</div> -->
			<div id="main-content"  class="content allleft">
				<div class="brandtitle">
						<div class="breadtitle">
							<span><img src="./image/bread.png"/>  工资管理 > 工资发放</span>
						</div>
				</div>
				<div style="background:#ffffff;width:99%;min-height:800px;">
					<div style="margin:0px 20px;">
						<div class="childtitle">
									<span>工资发放</span>
						</div>
						<div class="caozuo1">
							<span class="caozuo">操作</span>
							<button type="button" class="clickbutton" onclick="addSalary();">新增</button>
		<!-- 					<button type="button" class="clickbutton" onclick="salaryEdit();">编辑</button> -->
							<button type="button" class="clickbutton" onclick="salaryDel();">删除</button>
								<button type="button" class="clickbutton" onclick="salaryUnOffect();">撤销</button>
<!-- 							<button type="button" class="clickbutton">导出</button> -->
							<div style="float:right;margin-right:20px;">
								<input type="text" class="inputClass" id="name" placeholder="方案号、店铺名"  onkeypress="if (event.keyCode == 13) getList();"/>&nbsp;&nbsp;
								<button type="button" onclick="getList(1);" class="serchbutton">搜索</button>
							</div>
	
						</div>
	
						<div class="data-list">
							<div class="data-list-row">
								<div class="data-list dis-table">
									<div class="dis-table-cell" style="width:3%;">序号</div>
									<div class="dis-table-cell" style="width:7%;">
										<input class="checkbox" id ="titlecheckbox" type="checkbox" onclick="allSel(this);" style="margin-left:10px;"/>
									</div>
									<div class="dis-table-cell" style="width:15%;">方案号</div>
									<div class="dis-table-cell" style="width:15%;">店铺</div>
									<div class="dis-table-cell" style="width:8%;">月份</div>
									<div class="dis-table-cell" style="width:12%;">创建人</div>
									<div class="dis-table-cell" style="width:8%;">总金额</div>
									<div class="dis-table-cell" style="width:14%;">创建时间</div>
									<div class="dis-table-cell" style="width:18%;">操作</div>
								</div>
							</div>
							<div class="data-list-row source">
						<!-- 		<div class="data-list dis-table">
									<div style="width: 6px;float: left;margin-bottom: 18px;">
										<input style="margin:4px -4px 0" type="checkbox"/>
									</div>
									<div class="dis-table-cell" style="width:18%;"></div>
									<div class="dis-table-cell" style="width:18%;"></div>
									<div class="dis-table-cell" style="width:18%;"></div>
									<div class="dis-table-cell" style="width:15%;"></div>
									<div class="dis-table-cell" style="width:10%;"></div>
								</div> -->
							</div>
						</div>
						<div class="pagehtml">
				
						</div>
						<div style="clear:both;"></div>
		<div class="tishi" style="display:none;width:98%;height:100px;text-align:center;position:absolute;margin-top: 20px;">
			<span style="font-size:16px;color:#2d2d2d;"><img src="./image/tishi.png" style="width:30px;height:30px;"/> 没有查询到符合条件的记录</span>
		</div>
		
				 
					 	<!-- 查审页面 -->
			<div id="selAuditDiv" style="display: none;padding: 0px 10px;width: 650px;">
				<input type="hidden" id="programmeNum"/>
				<h3>审批意见</h3>
				<div style="height:450px;overflow: auto;">
					<div class="column-data">数据记载中...</div>
				</div>
				<div id="page3" class="page">
					<div class="btn-group btn-group-sm" role="group">
						<button type="button" class="btn btn-default" onclick="lastPage3()">上一页</button>
	  					<button type="button" class="btn btn-default" onclick="nextPage3()">下一页</button>
					</div>
					<span>当前第<input type="text" id="nowPage" value="1" style="width:30px;"/>页， 共<span id="totalPage">0</span>页，共<span id="totalCount">0</span>条记录</span>
				</div>
			</div>
			
		
					</div>
			  </div>
		   </div>
		</div>
	</div>
	<div class="addSalary">
		<div class="reportadd_title">
			<span style="margin-bottom:30px;" class="addoredit">工资发放  > 新增</span>
			<span style="float:right;margin-right: 10px;"><a href="#" onclick="closeAddDiv();" id="closdButton"><img src="./image/close.png"/></a></span>
		</div>
		<div class="salarysaveandedit" style="margin-top: 10%;margin-left: 18%;width:60%;">
				<div>
					<span>方案号：</span>
					<input type="text" id="formCode">
					<input type="hidden" id="salaryFormId">
				</div>
				<div>
					<span>店铺：</span>
					<select  class="areaselect" id="storeName">
						<option value="">请选择</option>
					<!-- 	<option value="超级导购测试版">超级导购测试版</option> -->
					</select>
				</div>
				<div>
					<span>年份：</span>
					<select  class="yearselect" id="moneyYear">
						<option value="">请选择</option>
					</select>
				</div>
				<div>
					<span>月份：</span>
					<select  class="salesselect" id="moneyMonth">
						<option value="">请选择</option>
						<option value="1">1月</option>
						<option value="2">2月</option>
						<option value="3">3月</option>
						<option value="4">4月</option>
						<option value="5">5月</option>
						<option value="6">6月</option>
						<option value="7">7月</option>
						<option value="8">8月</option>
						<option value="9">9月</option>
						<option value="10">10月</option>
						<option value="11">11月</option>
						<option value="12">12月</option>
					</select>
				</div>
				<button class="next_button" onclick="next_operation();">下一步</button>
				<input type="hidden" id="userId">
				<input type="hidden" id="token">
		</div>
	</div>
	</body>
<script type="text/javascript">
	var arrId = [];
	$(function(){
		$("#main-content").before(menuhtml());
		$("#userId").val(getParameterByName('userId'));
		$("#token").val(getParameterByName('token'));
		getList();
		var a=getParameterByName('a');
		var b=getParameterByName('b');
		if(a=="f"){
			$(".item").children()[5].click();
			if(b=="a1"){
				 $(".two:eq(0)").css({"background":"rgba(255, 86, 81,.2)","color":"#000000"});
			}
		}
		//设置年份的选择 
		var myDate= new Date(); 
		var startYear=myDate.getFullYear()-1;//起始年份 
		var endYear=myDate.getFullYear()+50;//结束年份 
		var obj=document.getElementById('moneyYear');
		for (var i=startYear;i<=endYear;i++) 
		{ 
		obj.options.add(new Option(i,i)); 
		} 
	})

	//关闭
	function closeAddDiv(){
		$(".addSalary").hide();
		window.location.reload();
	}
	function next_operation(){
		var salaryFormId= $("#salaryFormId").val();
		var formCode =  $("#formCode").val();
    	var moneyMonth =  $("#moneyMonth").val();
    	var moneyYear =  $("#moneyYear").val();
		var oId = $(".areaselect option:selected").attr('oId'); //selectid
    	var storeName = $(".areaselect option:selected").val();
    	//var brandType = $("input[name='brandtype']:checked").val();
    	if(formCode== null ||formCode == ''){
   			layer.msg("方案号不能为空", {icon:2,time:3000});
			return;
		}
    	if(moneyMonth== null ||moneyMonth == ''){
   			layer.msg("财务月不能为空", {icon:2,time:3000});
			return;
		}
    	if(moneyYear== null ||moneyYear == ''){
   			layer.msg("财务年不能为空", {icon:2,time:3000});
			return;
		}
    	if(storeName== null ||storeName == ''){
   			layer.msg("所属店铺不能为空", {icon:2,time:3000});
			return;
		}
    	var userId=$("#userId").val();
    	var token=$("#token").val();
    	var id="402880a75765663d015765678e4a0000";
    	var obj = JSON.stringify({salaryFormId:salaryFormId,formCode:formCode,moneyMonth:moneyMonth,moneyYear:moneyYear,
    		 storeName:storeName,managerId:userId,storeId:oId,creater:userId});	
    	
    	
    	$.ajax({
	          type: "GET",
	          url: "/shopguide-GJ/api/salary/getFormcodeFlag",
	          dataType: "json",
	          data:{code:formCode,formId:salaryFormId},
	          success: function(data1){
	        	  if(data1){
	        		  $.ajax({
	        	          type: "POST",
	        	          url: "/shopguide-GJ/api/salary/createOrUpdateSalaryFrom",
	        	          contentType: "application/json; charset=utf-8",
	        	          dataType: "json",
	        	          data:obj,
	        	          success: function(data){
	        	        	  if(data!=null){
	        	        			 layer.msg("保存成功", {icon:1,time:3000});
	        	    				//window.location.href="salary_add.html?managerId="+data.managerId+"&salaryFormId="+data.salaryFormId+"&month="+data.moneyMonth+"&year="+data.moneyYear;
	        	    				window.location.href="salary_detail_list.html?salaryFormId="+data.salaryFormId+"&month="+data.moneyMonth+"&year="+data.moneyYear+"&token="+token+"&userId="+userId+"&managerId="+data.managerId+"&storeId="+data.storeId+"&a=f&b=a1"; 
	        	        	  }else{
	        	        			 layer.msg("你所选中的店铺该月份已有工资记录，请检查", {icon:2,time:3000}); 
	        	        		 }
	        			},error : function(e){
	        				layer.msg("保存失败", {icon:2,time:3000});
	        			}
	        		});
	        	  }else{
	        		  layer.msg("该方案号已被使用，请检查", {icon:2,time:3000}); 
	        	  }
	          }
		});
	}
	function funTest(salaryFormId,moneyMonth,moneyYear,managerId,storeId){
		var userId=$("#userId").val();
		var token=$("#token").val();
		window.location.href="salary_detail_list.html?salaryFormId="+salaryFormId+"&month="+moneyMonth+"&year="+moneyYear+"&userId="+userId+"&token="+token+"&managerId="+managerId+"&storeId="+storeId+"&a=f&b=a1&c=detail";
	}
	//查询事件
	function getList(pageId){
		var name=$("#name").val();
		var pagenow=$("#pagenow").val();
		var pageSizenow=$("#pageSize").val();
		var maxPage=$("#maxPage").val();
		if(parseInt(maxPage) > 0){
			if(parseInt(pagenow) > parseInt(maxPage)){
				layer.msg("输入值超过最大页数，请重新输入", {icon: 2,time:3000});
				$("#pagenow").val(1);
				return;
			}else if(parseInt(pagenow) <= 0){
				layer.msg("请重新输入正确的页数", {icon: 2,time:3000});
				$("#pagenow").val(1);
				return;
			}
		}
		if(pageId > 0) {
			pagenow = pageId;
		}
		var userId=$("#userId").val();
		var obj = {userId:userId,name:name,pageSize:pageSizenow,nowPage:pagenow};
		$.ajax({
	          type: "GET",
	          url: "/shopguide-GJ/api/salary/getSalaryFromPage",
	          contentType: "application/json; charset=utf-8",
	          dataType: "json",
	          data:obj,
	          success: function(data){
	        	  if(data.list.length <= 0){
		        		$(".pagehtml").hide();
		        		$(".tishi").show();
		        	  }else{
		        		  $(".tishi").hide();
		        	    $(".pagehtml").show();
		        	 }
	        	  console.log(data);
	      	  var html="";
			  		$.each(data.list, function(i, val){
			  			var outStr="";                           	
		  				outStr +='<a onclick="salaryEdit(this,&quot;'+val.salaryFormId+'&quot;);">编辑 </a>&nbsp; | &nbsp;<a onclick="salaryDelKey(&quot;'+val.salaryFormId+'&quot;);"> 删除 </a>&nbsp; | &nbsp;<a onclick="funTest(&quot;'+val.salaryFormId+'&quot;'+","+'&quot;' + val.moneyMonth+'&quot;'+","+'&quot;' + val.moneyYear+'&quot;'+","+'&quot;'+ val.managerId+'&quot;'+","+'&quot;'+val.storeId+'&quot;)">详情 </a> |&nbsp;<a href="#" onclick="selAudit(&quot;'+val.formCode+'&quot;); return false;">查审</a>';
			  			var datestr=val.createTime.substring(0,19);
			  			html += '<div class="data-list dis-table" style="border-bottom:1px solid #dbdbdb"> \
			  				<div class="dis-table-cell" style="width:3%;padding-left: 7px;">'+(data.pageSize*(data.nowPage-1)+i+1)+'</div>\
								<div style="width: 7%;margin-bottom: 18px;">\
									<input type="checkbox" style="margin-top:20px;margin-left:10px;" class="checkbox_class" onclick="singleSel(this)" id="'+val.salaryFormId+'"/>\
								</div>\
								<div class="dis-table-cell" style="width:15%;">'+val.formCode+'</div>\
								<div class="dis-table-cell" style="width:15%;">'+val.orgName+'</div>\
								<div class="dis-table-cell" style="width:8%;">'+val.moneyYear+'年'+val.moneyMonth+'月</div>\
								<div class="dis-table-cell" style="width:12%;">'+val.name+'</div>\
								<div class="dis-table-cell" style="width:8%;">'+val.allamount+'</div>\
								<div class="dis-table-cell" style="width:14%;">'+datestr+'</div>\
								<div class="dis-table-cell" style="width:18%;">\
								<span>\
								'+outStr+'\
								</span>\
								</div>\
								</div>';
						});
	  			$(".source").html(html);
		      	setPaging(data.totalCount,data.maxPage,data.nowPage);
	  			$("#pagenow").val(data.nowPage);
	  			$("#pageSize").val(data.pageSize);
	  			$("#maxPage").val(data.maxPage);
	  			//$("#name").val("");
	  			arrId = [];
	  			$("#titlecheckbox").prop("checked",false);
		    	$(".totalMax").text('共'+data.maxPage+'页');
		      	$(".totalCount").text(data.totalCount+"条");
			},error : function(e){
				layer.msg("查询失败", {icon: 2,time:3000});
			}
		});
	}
	
	//删除
	function salaryDelKey(id){
		var pageId=$("#pagenow").val();
		if(id.length!=0){
			var ids=id;
				layer.confirm('您确定要删除当前记录吗？', {
				    btn: ['确认','取消'] //按钮
				}, function(){
					$.ajax({
				          type: "POST",
				          url: "/shopguide-GJ/api/salary/delete/"+ids,
				          contentType: "application/json; charset=utf-8",
				          dataType: "json",
				          success: function(data){
				        	  if(data!=null){
					          	 if(data.salaryFormId==null || data.salaryFormId==""){
				        			  layer.msg("你所选的表单有已生效工资条，不能删除", {icon:2,time:3000});
				        		  }else{
				        			  layer.msg("删除成功", {icon:1,time:3000});
				        			  getList(pageId);
				        		  }
				        	  }else{
				        		layer.msg("删除失败", {icon: 2,time:3000});
				        	  }
				  		}
					});
				}, function(){
				});
		}
	}
	
	//删除
	function salaryDel(){
		var pageId=$("#pagenow").val();
		if(arrId.length==0){
		//	openDiv("请选择你所删除的记录");
		layer.msg("请选择你所删除的记录", {icon: 2,time:3000});
		}else{	
			var ids=arrId.join(",");
				layer.confirm('您确定要删除当前记录吗？', {
				    btn: ['确认','取消'] //按钮
				}, function(){
					$.ajax({
				          type: "POST",
				          url: "/shopguide-GJ/api/salary/delete/"+ids,
				          contentType: "application/json; charset=utf-8",
				          dataType: "json",
				          success: function(data){
				        	  if(data!=null){
					          	 if(data.salaryFormId==null || data.salaryFormId==""){
				        			  layer.msg("你所选的表单有已生效工资条，不能删除", {icon:2,time:3000});
				        		  }else{
				        			  layer.msg("删除成功", {icon:1,time:3000});
				        			  getList(pageId);
				        		  }
				        	  }else{
				        		  layer.msg("你所选的表单有已生效工资条，不能删除", {icon:2,time:3000});
				        	  }
				  		}
					});
				}, function(){
				});
		}
	}
	//失效
	function salaryUnOffect(){
		var pageId=$("#pagenow").val();
		if(arrId.length==0){
		layer.msg("请选择你所撤销的记录", {icon: 2,time:3000});
		}else{	
			var ids=arrId.join(",");
				layer.confirm('撤销后需要重新生效,你确定要撤销当前记录吗？', {
				    btn: ['是','否'] //按钮
				}, function(){
					$.ajax({
				          type: "POST",
				          url: "/shopguide-GJ/api/salary/InvalidSalary/"+ids,
				          contentType: "application/json; charset=utf-8",
				          dataType: "json",
				          success: function(data){
				        	  if(data!=null){
					        		  layer.msg("撤销成功", {icon:1,time:3000});
				        			  getList(pageId);
					        	  }else{
					        		  layer.msg("你所选的表单有已通过审核，不能撤销", {icon:2,time:3000});
					        	  }
				  		}
					});
				}, function(){
				});
		}
	}
	
	//编辑
	function salaryEdit(self,id){
	/* 	if(arrId.length==0){
			layer.msg("请选择你所编辑的记录", {icon: 2,time:3000});
		}else if(arrId.length>1){
			layer.msg("只能选择一条内容编辑", {icon: 2,time:3000});
		}else{ */
			var ids=arrId.join(",");
			$.ajax({
		          type: "POST",
		          url: "/shopguide-GJ/api/salary/checkByProgrammeNum/"+id,
		          contentType: "application/json; charset=utf-8",
		          dataType: "json",
		          success: function(data){
		        	 if(data){
		        		 layer.msg("该条工资记录已审核通过不能编辑！", {icon: 2,time:3000});
		        	 }else{
		        		 $.ajax({
		   		          type: "POST",
		   		          url: "/shopguide-GJ/api/salary/getSalaryFormById/"+id,
		   		          contentType: "application/json; charset=utf-8",
		   		          dataType: "json",
		   		          success: function(data){
		   		        	  console.log(data);
		   		        	 if(data!=null){
		   		        		 addSalary();
		   		        			$(".salesselect").find("option[value='"+data.moneyMonth+"']").attr("selected",true); 
		   		        			$(".salesselect").attr("disabled","disabled").css("background-color", "#f0f0f0");
		   		        			$(".yearselect").find("option[value='"+data.moneyYear+"']").attr("selected",true); 
		   		        			$(".yearselect").attr("disabled","disabled").css("background-color", "#f0f0f0");
		   		        			setTimeout(function(){
		   		        				$(".areaselect").find("option[oId='"+data.storeId+"']").attr("selected",true); 
		   			        			$(".areaselect").attr("disabled", "disabled").css("background-color", "#f0f0f0");
		   				  			},200);
		   		        			
		   		        			$("#formCode").val(data.formCode);
		   		        			 $("#salaryFormId").val(data.salaryFormId);
		   		        	//	window.location.href="salary_add.html?managerId="+data.managerId+"&salaryFormId="+data.salaryFormId+"&month="+data.moneyMonth+"&year="+data.moneyYear;	
		   		        	 }else{
		   			        		layer.msg("查询失败", {icon: 2,time:3000});
		   			        	  }
		   		      	},error : function(e){
		   					layer.msg("查询失败", {icon: 2,time:3000});
		   				}
		   			});
			       }
		      	},error : function(e){
					layer.msg("查询失败", {icon: 2,time:3000});
				}
			});
			
		//}
	}


	function addSalary(){
		$(".addSalary").show();
		 
		 if($("#salaryFormId").val()!=null){
			 $(".addoredit").html(" 工资发放 > 编辑");
		 }
		var id=$("#userId").val();
		$.ajax({
	          type: "GET",
	          url: "/shopguide-GJ/api/area/getAllArea",
	          dataType: "json",
	          data:{userId:userId},
	          success: function(data){
	        	  console.log(data);
	      		var html="<option value=''>请选择</option>";
			  	 	$.each(data, function(i, val){
			  	 	
				  	 		html +='<option oId='+val.sales_area_id+'>'+val.area+'</option>';  	
				  	 	 
			  		}); 
	      			$(".areaselect").html(html);
				}
		});
	}
	
	
	var PAGE2 = {
			pageSize : 10,
			nowPage : 1,
			totalCount : 0,
			totalPage : 0
		};
	var PAGE3 = {
			pageSize : 10,
			nowPage : 1,
			totalCount : 0,
			totalPage : 0
		};
		
		// 查审页面数据模板
		var SELAUDITHTML = '<div class="column-data-row">\
			<div class="column-data-cell" style="width:85px;">${createTime}</div>\
			<div class="column-data-cell" style="width:50px;"><img src="${createrHeadPic}" width="50px" height="50px"/></div>\
			<div class="column-data-cell">\
				<div class="column-data-row">\
					<div class="column-data-cell">${createrName}</div>\
				</div>\
				<div class="column-data-row"><div class="column-data-cell">${reviewExplain}</div></div>\
			</div>\
			<div class="column-data-cell" style="width:55px;">${reviewStatus}</div>\
		</div>';
		
		function getSearch3() {
			var Token=	getParameterByName('token');
			return {
				"accessToken" : Token,
				"programmeNum" : $("#selAuditDiv #programmeNum").val() ||'',
				"pageSize" : PAGE3.pageSize,
				"nowPage" : PAGE3.nowPage
				// "nowPage" : (page && ~~page>1) ? page : 1
			};
		};
		
		// 查审详情
		function selAudit(_programmeNum) {
			var index = layer.open({
			  type: 1,
			  title: '查审详情-方案号:'+_programmeNum,
			  closeBtn: 1,
			  area: ['auto', '650px'],
			  skin: 'layui-layer', //没有背景色
			  shadeClose: false,
			  content: $('#selAuditDiv'),
			  btn: ['取消'],
			  yes: function(index) {
				  layer.close(index);
			  },
			  end:function() {
				  $("#selAuditDiv .column-data").html('数据加载中...');
			  }
			});
			$("#selAuditDiv #programmeNum").val(_programmeNum);
			initSelAudit(1);
		};
		
		// 初始化查审数据
		function initSelAudit(page) {
			page && (PAGE3.nowPage = page);
			var search = getSearch3();
			$.ajax({
				type: "GET",
				url: "/shopguide-GJ/api/salary/salaryOrder/review/history",
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				data: search,
				success: function(data) {
					if(data != null) {
						var _list = data.list;
						if(_list && _list.length>0) {
							var _rowhtml = [];
							
							$.each(_list, function(i, val) {
								
								var _programmeNum = val.programmeNum;
								var _reviewStatus = val.reviewStatus;
								var _reviewExplain = val.reviewExplain;
								var _createTime = val.createTime;
								var _createrHeadPic = val.createrHeadPic || './image/default-head.png';
								var _createrName = val.createrName;
								
								if(_reviewStatus == null || _reviewStatus =='') {
									_reviewStatus = '待审核'
								} else if(_reviewStatus == 'adopt') {
									_reviewStatus = '通过'
								} else if(_reviewStatus == 'withhold') {
									_reviewStatus = '不通过'
								}
								
								_rowhtml.push(SELAUDITHTML
										.replace(/\${programmeNum}/g, _programmeNum)
										.replace(/\${reviewStatus}/g, _reviewStatus)
										.replace(/\${reviewExplain}/g, _reviewExplain)
										.replace(/\${createTime}/g, _createTime.replace("T", " "))
										.replace(/\${createrHeadPic}/g, _createrHeadPic)
										.replace(/\${createrName}/g, _createrName));
								
							});
							$("#selAuditDiv .column-data").html(_rowhtml.join(''));
						} else {
							$("#selAuditDiv .column-data").html('暂无数据');
						}
						PAGE3.totalCount = data.totalCount;
						PAGE3.totalPage = data.maxPage;
						initPage3();
					}
				}, 
				error: function(err) {
					if(err.status == 401) {
						layer.msg("未登录或登录信息失效", {icon:2,time:3000});
					} else {
						layer.msg("数据获取失败", {icon:2,time:3000});
					}
				}
			});
		};
		// 上一页
		function lastPage3() {
			if(PAGE3.nowPage > 1) {
				PAGE3.nowPage --;
				initSelAudit();
			}
		};
		
		//下一页
		function nextPage3() {
			if(PAGE3.nowPage < PAGE2.totalPage) {
				PAGE3.nowPage ++;
				initSelAudit();
			}
		};
		function initPage3() {
			$("#selAuditDiv #nowPage").val(PAGE3.nowPage);
			$("#selAuditDiv #totalPage").html(PAGE3.totalPage);
			$("#selAuditDiv #totalCount").html(PAGE3.totalCount);
		};
	
	</script>
</html>
