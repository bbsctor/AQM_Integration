package com.stkj.modules.realtime.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.stkj.common.annotation.SysLog;
import com.stkj.common.utils.PageUtils;
import com.stkj.common.utils.R;
import com.stkj.common.validator.ValidatorUtils;
import com.stkj.common.validator.group.AddGroup;
import com.stkj.modules.endpoint.entity.StationInfo;
import com.stkj.modules.realtime.entity.RealData;
import com.stkj.modules.realtime.service.RTDataService;
import com.stkj.modules.sys.controller.AbstractController;

@RestController
@RequestMapping("/realtime/rtdata")
public class RTDataController extends AbstractController {
	@Autowired
	private RTDataService rtDataService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("realtime:rtdata:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = rtDataService.queryPage(params);

		return R.ok().put("page", page);
	}
	
	@RequestMapping("/data")
	@RequiresPermissions("realtime:rtdata:data")
	public List<RealData> TimeAndMN(@RequestParam Map<String, Object> params) {
		List<RealData> realData =rtDataService.queryData(params);
		logger.info(JSON.toJSONString(realData));
	
		return realData;
	}
	
	 /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public R info(@PathVariable("id") Long id){
        RealData rtdata = rtDataService.selectById(id);

        return R.ok().put("rtdata", rtdata);
    }
	
	
    
    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("realtime:rtdata:delete")
    public R delete(@RequestBody Long[] ids){
    	rtDataService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
