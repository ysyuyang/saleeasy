	function getParameterByName(name) {  
	    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);  
	    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));  
	}
	var userId = getParameterByName('userId');
	var token = getParameterByName('token');
function menuhtml(){
	/*	var html = "<div id='banner' class='banner'>\
						<div class='item'>\
							<div onselectstart='return false;' onclick='selItem(this,0)' style='padding-top:15px;'><img src='../image/salesunchecked.png' title='我的团队' style='width:38px;height:36px;margin:15px 6px'/><span>我的团队</span></div>\
							<div onselectstart='return false;' onclick='selItem(this,1)'><img src='../image/task.png' title='任务管理' style='width:26px;height:24px;margin:14px 12px;'/><span>任务管理</span></div>\
							<div onselectstart='return false;' onclick='selItem(this,2)'><img src='../image/reportunchecked.png' title='报表中心' style='width:41px;height:40px;margin:13px 6px;'/><span>报表中心</span></div>\
							<div onselectstart='return false;' onclick='selItem(this,3)'><img src='../image/salaryunchecked.png' title='工资管理' style='width:39px;height:38px;margin:13px 6px;'/><span>工资管理</span></div>\
						</div>\
				  <div id='childBanner' style='display:none;background:#F3F3F4;width:150px;' class='banner'>\
					<div class='item1'>\
					</div>\
					</div>";*/
		
		
		var html = "<div id='banner' class='banner'>\
			<div class='item'>\
				<div onselectstart='return false;' onclick='selItem(this,0)' style='padding-top:15px;'><img id='before1' src='../image/saleschecked.png' style='width:20px;height:20px;margin:10px 24px;display:none;'/><img id='before' src='../image/salesunchecked.png' style='width:20px;height:20px;margin:10px 24px'/><span>我的团队</span><img  id='after' src='../image/menudown.png' style='width:12px;height:6px;margin-right:10px;float:right;margin-top:17px;'/>\
					<div class='item1' id='childBanner'>\
					</div>\
				</div>\
				<div onselectstart='return false;' onclick='selItem(this,1)'><img id='before1' src='../image/untask.png' style='width:20px;height:20px;margin:10px 24px;display:none;'/><img id='before' src='../image/task.png' style='width:20px;height:20px;margin:10px 24px'/><span>任务管理</span><img  id='after' src='../image/menudown.png' style='width:12px;height:6px;margin-right:10px;float:right;margin-top:17px;'/>\
					<div class='item1' id='childBanner'>\
					</div>\
				</div>\
				<div onselectstart='return false;' onclick='selItem(this,2)'><img id='before1' src='../image/reportchecked.png' style='width:20px;height:20px;margin:10px 24px;display:none;'/><img id='before' src='../image/reportunchecked.png' style='width:20px;height:20px;margin:10px 24px'/><span>报表中心</span><img  id='after' src='../image/menudown.png' style='width:12px;height:6px;margin-right:10px;float:right;margin-top:17px;'/>\
					<div class='item1' id='childBanner'>\
					</div>\
				</div>\
				<div onselectstart='return false;' onclick='selItem(this,3)'><img id='before1' src='../image/salarychecked.png' style='width:20px;height:20px;margin:10px 24px;display:none;'/><img id='before' src='../image/salaryunchecked.png' style='width:20px;height:20px;margin:10px 24px'/><span>工资管理</span><img  id='after' src='../image/menudown.png' style='width:12px;height:6px;margin-right:10px;float:right;margin-top:17px;'/>\
					<div class='item1' id='childBanner'>\
					</div>\
				</div>\
			</div>";

		return html;
	}










//<div onselectstart='return false;' onclick='selItem(this,4)'><img src='../image/salaryunchecked.png' title='工资管理' style='width:43px;height:41px;margin:15px 18px;'/><span>工资管理</span></div>\
	//	<div onselectstart='return false;' onclick='selItem(this,5)'><img src='../image/setunchecked.png' title='系统设置' style='width:43px;height:41px;margin:15px 18px;'/><span>系统设置</span></div>\
	//<div onselectstart='return false;' onclick='selItem(this,5)'><img src='../image/reportunchecked.png' title='报表中心' style='width:43px;height:41px;margin:15px 18px;'/><span>报表中心</span></div>
	/*function openItem() {
		var showButtonPic = $('#showButtonPic').val();
		if(showButtonPic == 'falseStr'){
			$('#menuPic').attr('src','../image/vertical.png');
			$('#menuPic').css({"margin": "5px 60px"});
			$('#showButtonPic').val('trueStr');
		}else{
			$('#menuPic').attr('src','../image/transverse.png');
			$('#menuPic').css({"margin": "5px 5px"});
			$('#showButtonPic').val('falseStr');
		}
		$('#banner').toggleClass('checked');
		$('#main-content').toggleClass('sel0');
	};
	*/
	var itemArr = [ 
	   			[{name:'人员信息', url:'sales_info.html?userId='+userId+'&token='+token+'&a=a&b=a1'}, {name:'网点派单', url:'snode_work.html?userId='+userId+'&token='+token+'&a=a&b=a2'}, {name:'人员调出', url:'move_out.html?userId='+userId+'&token='+token+'&a=a&b=a3'}, {name:'人员调入', url:'move_in.html?userId='+userId+'&token='+token+'&a=a&b=a4'}],
	   			[{name:'分值设置', url:'product_score.html?userId='+userId+'&token='+token+'&a=b&b=a1'},{name:'其他考核', url:'other_assessment.html?userId='+userId+'&token='+token+'&a=b&b=a2'},{name:'任务发放', url:'target.html?userId='+userId+'&token='+token+'&a=b&b=a3'}],
	  		    [{name:'人员信息', url:'sales_info_report.html?userId='+userId+'&token='+token+'&a=c&b=a1'},{name:'签到详情', url:'clock_detail_report.html?userId='+userId+'&token='+token+'&a=c&b=a2'},
	  		    	{name:'月签到详情', url:'clock_day_report.html?userId='+userId+'&token='+token+'&a=c&b=a3'},
	  		     {name:'员工销量明细（店销）', url:'shopsales_report.html?userId='+userId+'&token='+token+'&a=c&b=a4'},{name:'员工销量明细（自带）', url:'ownsales_report.html?userId='+userId+'&token='+token+'&a=c&b=a5'},
	  		     {name:'网点销量明细（店销）', url:'shopsalessnode_report.html?userId='+userId+'&token='+token+'&a=c&b=a6'},{name:'网点销量明细（自带）', url:'ownsalessnode_report.html?userId='+userId+'&token='+token+'&a=c&b=a7'},
	  	/*	     {name:'其他产品销量', url:'other_report.html?userId='+userId+'&token='+token+'&a=c&b=a8'},*/
	  		   {name:'连台自带', url:'banquetownsales_report.html?userId='+userId+'&token='+token+'&a=c&b=a8'},{name:'连台店销', url:'banquetshopsales_report.html?userId='+userId+'&token='+token+'&a=c&b=a9'}
	  		 ,{name:'休假申请', url:'offwork_report.html?userId='+userId+'&token='+token+'&a=c&b=a10'},{name:'今日总结', url:'feedback_report.html?userId='+userId+'&token='+token+'&a=c&b=a11'}
	  		,{name:'离职人员销量', url:'delsales_report.html?userId='+userId+'&token='+token+'&a=c&b=a12'}],
	   			[{name:'工资发放', url:'salary.html?userId='+userId+'&token='+token+'&a=d&b=a1'},{name:'月签到详情', url:'clock_day_reportday.html?userId='+userId+'&token='+token+'&a=d&b=a2'}],
	   		];
		function selItem(self, id) {
	   		/*var title =	$(self).find('span').html();*/
	   			var _class = $(self).attr("class");
	   			if(_class != null && _class.split(' ').indexOf('sel')>-1) {
	   				$(self).find("#after").attr("src", "../image/menudown.png");
	   				$(self).find("#before").show();
	   				$(self).find("#before1").hide();
	   				$(self).removeClass("sel");
	   				$(self).find('#childBanner').hide();
	   				$('#main-content').removeClass('sel1');
	   			} else {
	   				$(self).find("#after").attr("src", "../image/menuup.png");
	   				$(self).find("#before1").show();
	   				$(self).find("#before").hide();
	   				$('#main-content').addClass('sel1');
	   				$("#banner .item .sel").removeClass("sel");
	   				/*var html='<div style="margin:40px 15px;font-size:18px;color:#2d2d2d;">'+title+'</div>';*/
	   				var html='';
	   				$.each(itemArr[id], function(i, val) {
	   					html +='<a href='+val.url+'><div class="two" style="height:30px;margin:15px 0px;text-align: center;font-size:14px;line-height:30px;">'+val.name+'</div></a>';
	   				});

	   				$(self).find('.item1').html(html);
	   				$(self).find('#childBanner').show();
	   				//$('.item1').html(html);
	   				$(self).toggleClass('sel');
	   			//	$("#childBanner").show();
	   			}
	   		};
	