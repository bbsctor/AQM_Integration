window.onload=function(){
	 $('#start').val(laydate.now(-1, 'YYYY-MM-DD 00:00:00'));
	 $('#end').val(laydate.now(0, 'YYYY-MM-DD hh:mm:ss'));
	    
	 var initmn=88888880000001;
	 var initbegin=document.getElementById("start").value;
	 var initend=document.getElementById("end").value;
		
		$("#jqGrid").jqGrid({
	        url: baseURL + 'realtime/rtdata/list',
	        datatype: "json",
	        colModel: [			
				{ label: '时间', name: 'time', width: 100, key: true},
				{ label: '站号', name: 'mn', width: 75 },
	            { label: 'SO2', name: 'so2', width: 75 },
				{ label: 'NO2', name: 'no2', width: 75 },
				{ label: 'O3', name: 'o3', width: 75 },
				{ label: 'CO', name: 'co', width: 75 },
				{ label: 'PM10', name: 'pm10',  width: 75},
				{ label: 'PM2.5', name: 'pm25', width: 75},
				{ label: 'TVOC', name: 'tvoc', width: 75}
	        ],
			viewrecords: true,
	        height: 385,
	        rowNum: 10,
			rowList : [10,30,50],
	        rownumbers: true, 
	        rownumWidth: 50, 
	        autowidth:true,
	        multiselect: true,
	        pager: "#jqGridPager",
	        jsonReader : {
	            root: "page.list",
	            page: "page.currPage",
	            total: "page.totalPage",
	            records: "page.totalCount"
	        },
	        prmNames : {
	            page:"page", 
	            rows:"limit", 
	            order: "order"
	        },
	        gridComplete:function(){
	        	//隐藏grid底部滚动条
	        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
	        }
	    });
		
		 $.ajax({	//使用JQuery内置的Ajax方法         
		        type : "post",		//post请求方式
		      //  contentType:"application/json;charset=utf-8",
		        async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		        url : baseURL + '/realtime/rtdata/data',	//请求发送到ShowInfoIndexServlet处
		        data: {'mn': initmn,'begindatetime':initbegin, 'enddatetime':initend},		//提交数据到后台
		        dataType : "json",		//返回数据形式为json
		 })
	
}




var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
        	stationInfos:[],
            mn: null,
            begin:null,
            end:null
        },
        showList: true,
    },
//    created: function(){
//    	this.getStationInfo();
//    },
    methods: {
    	
    	getStationInfo:function()
    	{
    		$.ajax({ //使用JQuery内置的Ajax方法         
    			type : "post", //post请求方式
    			contentType : "application/json;charset=utf-8",
    			async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    			url : baseURL + '/station/stationinfo/data', //请求发送到ShowInfoIndexServlet处
    			dataType : "json", //返回数据形式为json

    			success : function(result) {

    				//请求成功时执行该函数内容，result即为服务器返回的json对象
    				if (result != null && result.length > 0) {
    					vm.q.stationInfos = result;
    				}
    			},
    			error : function(errorMsg) {
    				//请求失败时执行该函数
    				alert(errorMsg);
    			}
    		});
    	},
    	
    	getBegin: function () {

    	　　　　laydate({
    	　　　　　 elem: '#start',
    	        format: 'YYYY-MM-DD hh:mm:ss', // 分隔符可以任意定义
    	        istime: true,
    	　　　　　　choose: function (begin) {
    				vm.q.begin = begin;
    			    alert(vm.q.begin);
    	　　　　　　}
    	　　　　});
    	},
    	getEnd: function () {

    	　　　 laydate({
    	　　　　　 elem: '#end',
                format: 'YYYY-MM-DD hh:mm:ss', // 分隔符可以任意定义
                istime: true,
    	　　　　　 choose: function (end) {
	                  vm.q.end = end;
	    			  alert(vm.q.end);
    	　　　　　　}
    	　　　　});
    	},
        query: function () {
            vm.reload();
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'mn': vm.q.mn,'begindatetime': vm.q.begin, 'enddatetime':vm.q.end},
                page:page
            }).trigger("reloadGrid");
        }
    }
});