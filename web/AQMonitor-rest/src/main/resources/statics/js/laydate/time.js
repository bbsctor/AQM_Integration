var start = {
      elem: '#start',
      format: 'YYYY-MM-DD hh:mm:ss',
      max: laydate.now(),
      istime: false,
      istoday: false,
      choose: function (datas) {
        end.min = datas; //开始日选好后，重置结束日的最小日期
        end.start = datas //将结束日的初始值设定为开始日
      }
    };
    var end = {
      elem: '#end',
      format: 'YYYY-MM-DD hh:mm:ss',
      max: laydate.now(),
      istime: false, //是否开启时间选择
      istoday: false,
      isclear: true, //是否显示清空
      issure: true, //是否显示确认
      choose: function (datas) {
        start.max = datas; //结束日选好后，重置开始日的最大日期
      }
    };
    laydate(start);
    laydate(end);
    //给input赋值
    $('#start').val(laydate.now(-100, 'YYYY-MM-DD hh:mm:ss'));
     $('#end').val(laydate.now(0, 'YYYY-MM-DD hh:mm:ss'));




