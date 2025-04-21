package com.fzsx.vlib.controller;

import com.fzsx.vlib.entity.Lab;
import com.fzsx.vlib.entity.Supplier;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.service.ILabService;
import com.fzsx.vlib.service.IRoleService;
import com.fzsx.vlib.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * vlib - LibController
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Controller
@RequestMapping("lab")
public class LabController {

    /* 自动注解-Service层 LabService */
    @Autowired
    ILabService labService;
    @Autowired
    IRoleService roleService;

    //新增、删除、查询、详情、分配设备
    /**
     * 添加实验室
     *
     * @return Map {"data", flag-操作条目}
     * @path lab/save
     */
    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> saveLabInfo(@RequestBody Lab lab) {

        if (null != lab) {
            if (null != lab.getLabName() && null != lab.getLabName() &&
                    !"".equals(lab.getLabNumber()) && !"".equals(lab.getLabNumber())) {

                int flag = labService.saveLabInfo(lab);
                Map<String, Object> map = new HashMap<>();
                map.put("data", flag);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 实验室列表分页
     *
     * @param map {“pageNum”,“pageSize”,“labName”}
     * @return res {"data", "total"}
     * @path lab/list
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> selectLabList(@RequestBody Map<String, Object> map) {

        int pageNum = map.get("pageNum").toString() == null ? 0 : Integer.parseInt(map.get("pageNum").toString());
        int pageSize = map.get("pageSize").toString() == null ? 0 : Integer.parseInt(map.get("pageSize").toString());
        String labName = map.get("labName").toString() == null ? "" : map.get("labName").toString();
        //int flag = map.get("flag").toString() == null ? 0 : Integer.parseInt(map.get("flag").toString());

        Lab lab = new Lab();
        lab.setLabName(labName);
        PageInfo labPageInfo = labService.getLabByHelperList(pageNum, pageSize, labName);
        //得到分页中的user条目对象
        List pageList = labPageInfo.getList();
        Map<String, Object> res = new HashMap<>();
        res.put("data", pageList);
        res.put("total", labPageInfo.getTotal());
        return res;
    }

    /**
     * 删除实验室信息
     *
     * @param lab 实验室实体类
     * @return Map {"data", flag-操作条目}
     * @path lab/delete
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody Lab lab) {
        if (null != lab) {
            if (null != String.valueOf(lab.getLabId()) && !"".equals(String.valueOf(lab.getLabId()))) {
                int flag = labService.delete(lab);
                Map<String, Object> map = new HashMap<>();
                map.put("data", flag);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 实验室详情信息
     *
     * @param lab 设备实体类
     * @return Map {"data", labList}
     * @path lab/detail
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Map<String, Object> detail(@RequestBody  Lab lab) {

        if (null != lab) {
            if (null != String.valueOf(lab.getLabId()) && !"".equals(String.valueOf(lab.getLabId()))) {
                List<Map> labList = labService.detail(lab);
                Map<String, Object> map = new HashMap<>();
                map.put("data", labList);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 更新实验室信息
     *
     * @param lab 设备实体类
     * @return Map {"data", flag-操作条目}
     * @path lab/update
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Lab lab) {
        if (null != lab) {
            if (null != String.valueOf(lab.getLabId()) && !"".equals(String.valueOf(lab.getLabId()))) {
                int flag = labService.update(lab);
                Map<String, Object> map = new HashMap<>();
                map.put("data", flag);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 获取教师列表
     *
     * @return maps {"data", managerList}
     * @path lab/getManagerList
     */
    @RequestMapping(value = "/getManagerList")
    @ResponseBody
    public Map<String, Object> getManagerList() {
        List<User> teacherList= roleService.getTeacherList();
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", teacherList);
        return maps;

    }
}
