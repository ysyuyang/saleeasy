	function getParameterByName(name) {  
	    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);  
	    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));  
	}
	var userId = getParameterByName('userId');
	var token = getParameterByName('token');
function menuhtml(){
		var html = "<div id='banner' class='banner'>\
						<div class='item'>\
							<div onselectstart='return false;' onclick='selItem(this,0)' style='padding-top:15px;'><img id='before1' src='./image/productchecked.png' style='width:20px;height:20px;margin:10px 24px;display:none;'/><img id='before' src='./image/productunchecked.png' style='width:20px;height:20px;margin:10px 24px'/><span>商品管理</span><img id='after' src='./image/menudown.png' style='width:12px;height:6px;margin-right:10px;float:right;margin-top:17px;'/>\
								<div class='item1' id='childBanner'>\
								</div>\
							</div>\
							<div onselectstart='return false;' onclick='selItem(this,1)'><img id='before1' src='./image/snodechecked.png' style='width:20px;height:20px;margin:10px 24px;display:none;'/><img id='before' src='./image/snodeunchecked.png' style='width:20px;height:20px;margin:10px 24px'/><span>网点管理</span><img id='after' src='./image/menudown.png' style='width:12px;height:6px;margin-right:10px;float:right;margin-top:17px;'/>\
								<div class='item1' id='childBanner'>\
								</div>\
							</div>\
							<div onselectstart='return false;' onclick='selItem(this,2)'><img id='before1' src='./image/saleschecked.png' style='width:20px;height:20px;margin:10px 24px;display:none;'/><img id='before' src='./image/salesunchecked.png' style='width:20px;height:20px;margin:10px 24px'/><span>人员管理</span><img id='after' src='./image/menudown.png' style='width:12px;height:6px;margin-right:10px;float:right;margin-top:17px;'/>\
								<div class='item1' id='childBanner'>\
								</div>\
							</div>\
							<div onselectstart='return false;' onclick='selItem(this,3)'><img id='before1' src='./image/targetchecked.png' style='width:20px;height:20px;margin:10px 24px;display:none;'/><img id='before' src='./image/targetunchecked.png' style='width:20px;height:20px;margin:10px 24px'/><span>业绩管理</span><img id='after' src='./image/menudown.png' style='width:12px;height:6px;margin-right:10px;float:right;margin-top:17px;'/>\
								<div class='item1' id='childBanner'>\
								</div>\
							</div>\
							<div onselectstart='return false;' onclick='selItem(this,4)'><img id='before1' src='./image/reportchecked.png' style='width:20px;height:20px;margin:10px 24px;display:none;'/><img id='before' src='./image/reportunchecked.png' style='width:20px;height:20px;margin:10px 24px'/><span>报表中心</span><img id='after' src='./image/menudown.png' style='width:12px;height:6px;margin-right:10px;float:right;margin-top:17px;'/>\
								<div class='item1' id='childBanner'>\
								</div>\
							</div>\ <div onselectstart='return false;' onclick='selItem(this,5)'><img id='before1' src='./image/salarychecked.png' style='width:20px;height:20px;margin:10px 24px;display:none;'/><img id='before' src='./image/salaryunchecked.png' style='width:20px;height:20px;margin:10px 24px'/><span>工资管理</span><img id='after' src='./image/menudown.png' style='width:12px;height:6px;margin-right:10px;float:right;margin-top:17px;'/>\
								<div class='item1' id='childBanner'>\
								</div>\
							</div>\
						</div>";
					return html;
	}

	
	var itemArr = [ 
	   			[{name:'品牌管理', url:'brand.html?userId='+userId+'&token='+token+'&a=a&b=a1'}, {name:'产品管理', url:'product.html?userId='+userId+'&token='+token+'&a=a&b=a2'}],
	   			[{name:'网点管理', url:'index.html?userId='+userId+'&token='+token+'&a=b&b=a1'}, {name:'网点设置', url:'snode_set.html?userId='+userId+'&token='+token+'&a=b&b=a2'}/*, {name:'网点派单', url:'snode_work.html'}*/],
	   			[{name:'人员信息', url:'sales_info.html?userId='+userId+'&token='+token+'&a=c&b=a1'},{name:'未分配人员', url:'unwork_info.html?userId='+userId+'&token='+token+'&a=c&b=a2'}/*, {name:'人员调出', url:'move_out.html'}, {name:'人员调入', url:'move_in.html'}*/],
	   			[{name:'区域管理', url:'area.html?userId='+userId+'&token='+token+'&a=d&b=a1'}, {name:'上报管理', url:'report.html?userId='+userId+'&token='+token+'&a=d&b=a2'}/*, {name:'产品分值', url:'product_score.html'}, {name:'任务发放', url:'target.html'}*/],
	  		    [{name:'人员信息', url:'sales_info_report.html?userId='+userId+'&token='+token+'&a=e&b=a1'},{name:'休假情况', url:'offwork_info_report.html?userId='+userId+'&token='+token+'&a=e&b=a2'},{name:'签到信息', url:'clock_info_report.html?userId='+userId+'&token='+token+'&a=e&b=a3'},
	  		     {name:'店铺签到状态', url:'shop_sign_report.html?userId='+userId+'&token='+token+'&a=e&b=a4'}],
	    			[{name:'工资发放', url:'salary.html?userId='+userId+'&token='+token+'&a=f&b=a1'}],
	   	/*		[{name:'周期设置', url:'cycle_set.html'}]*/
	   		];
	   		function selItem(self, id) {
	   		/*var title =	$(self).find('span').html();*/
	   			var _class = $(self).attr("class");
	   			if(_class != null && _class.split(' ').indexOf('sel')>-1) {
	   				$(self).find("#after").attr("src", "./image/menudown.png");
	   				$(self).find("#before").show();
	   				$(self).find("#before1").hide();
	   				$(self).removeClass("sel");
	   				$(self).find('#childBanner').hide();
	   				$('#main-content').removeClass('sel1');
	   			} else {
	   				$(self).find("#after").attr("src", "./image/menuup.png");
	   				$(self).find("#before1").show();
	   				$(self).find("#before").hide();
	   				$('#main-content').addClass('sel1');
	   				$("#banner .item .sel").removeClass("sel");
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
	