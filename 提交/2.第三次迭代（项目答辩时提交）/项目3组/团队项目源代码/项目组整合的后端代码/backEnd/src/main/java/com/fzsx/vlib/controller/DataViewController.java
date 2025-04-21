package com.fzsx.vlib.controller;


import com.fzsx.vlib.service.DataViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("data")
public class DataViewController {
    @Autowired
    private DataViewService dataViewService;

    /**
     * 数据1：6组数据
     * @return
     */
    @RequestMapping("/getData1")
    public Map<String,Object> getData() {
        return dataViewService.getCountData();
    }

    /**
     * 柱状图：学生、教师、管理员分配
     * @return
     */
    @RequestMapping("/getBarData")
    public Map<String,Object> getBarData(){
        return dataViewService.getBarData();
    }

    /**
     * 饼图数据：可用设备
     * @return
     */
    @RequestMapping("/getPieData")
    public Map<String,Object> getPieData(){
        return dataViewService.getPieData();
    }

    /**
     * 柱图数据：查询实验室容纳人数
     * @return
     */
    @RequestMapping("/getColumnarData")
    public Map<String,Object> getColumnarData(){
        return dataViewService.getColumnarData();
    }

    /**
     * 线图数据：获取审批时间
     * @return
     */
    @RequestMapping("/getLineData")
    public Map<String,Object> getLineData(){
        return dataViewService.getBgetLineDataarData();
    }
}
