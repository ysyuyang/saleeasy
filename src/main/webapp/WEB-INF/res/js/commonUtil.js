	
	//单选/取消单选事件
	function singleSel(t){
		
		var id = $(t).attr("id");
		   var count = 0;    
		if($(t).is(":checked")){
		     arrId.push(id);
		}else{
			 $("#titlecheckbox").prop("checked",false);		 
			 $.each(arrId,function(index,item){  
	            if(item==id){
	            	arrId.splice(index,1);
	             }
	    	 });
		}
		
		checkOne();
	}

	 function checkOne(){    
	        var flag = true;    
	        $('.checkbox_class').each(function(){
	        	console.log($(this).is(':checked'));
	            if(!$(this).is(':checked')){// 判断一组复选框是否有未选中的    
	                flag = false;
	            }  
	        });
	        if(flag) { // 如果没有未选中的那么全选框被选中    
	            $('#titlecheckbox').prop("checked",true);
	        } else {    
	            $('#titlecheckbox').removeAttr('checked');    
	        }  
	     }    
	//全选事件
	function allSel(t){
		arrId = [];
		if($(t).is(":checked")){
			$('.checkbox_class').each(function(){
				$(this).prop("checked",true);
				arrId.push($(this).attr("id"));
			});
		}else{
			$('.checkbox_class').each(function(){
				$(this).prop("checked",false);
			});
			arrId = [];
		}
//		alert(arrId.join(","));
	}
	
	
/*	//弹出提示层
	function openDiv(str){
		layer.msg(str, {icon: 1,time:1000});//1是勾的图片，2是X的图标
		layer.open({
		    content: str,
		    time: 2
		});
	}*/
	
	
	
	
	function pageHtml(){
		var html = "<input type='hidden'  id='pageSize' placeholder='每页条数' value='10'/>\
				    <input type='hidden'  id='maxPage'/>\
				    <div style='font-size:14px;cursor:pointer;float:left;margin-left: 5px;'>\
						 <ul class='pagination' id='pageUl'></ul>\
				    </div>\
				     <input type='hidden'  id='pagenow'/>\
					<div style='font-size:16px;float:left;margin-left:10px;margin-top:20px;color:#337ab7;border: 1px solid #dddddd ;min-width:68px;padding:0px 5px;height:34px;border-radius:4px;text-align:center;line-height:34px;' class='totalMax'></div>";
		return html;
	}
	
	
	//分页指令
	function setPaging(totalRows,totalPages,currentPage){
		$(".pagehtml").html(pageHtml());
		var pages = totalRows;// 这里data里面有数据总量
		var totalPages = totalPages<=0?1:totalPages;//总页数
		var currentPage = currentPage;//当前页面
           var element = $('#pageUl');//对应下面ul的ID
           var numberOfPages = 5;//一页显示几个按钮（在ul里面生成5个li）
           var options = {
               bootstrapMajorVersion: 3,
               currentPage: currentPage,
               numberOfPages: numberOfPages,
               totalPages: totalPages,
               itemTexts: function (type, page, current) {
                   switch (type) {
                     case "first":
                       return "首页";
                     case "prev":
                       return "上一页";
                     case "next":
                       return "下一页";
                     case "last":
                       return "末页";
                     case "page":
                       return page;
                   }
                 },
                 onPageClicked: function (event, originalEvent, type, page) {
                	 $("#pagenow").val(page);
               	 //调查询方法
              getList();
                 }
           }
          element.bootstrapPaginator(options);
	}
	
	  function NumberCheck(t){
	        var num = t.value;
	        var re=/^\d*$/;
	        if(!re.test(num)){
	            isNaN(parseInt(num))?t.value=0:t.value=parseInt(num);
	        }
	    }
	
	