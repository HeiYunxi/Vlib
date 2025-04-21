package com.fzsx.vlib.service.impl;

import com.fzsx.vlib.mapper.DataViewMapper;
import com.fzsx.vlib.service.DataViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataViewServiceImpl implements DataViewService {
    @Autowired
    private DataViewMapper dataViewMapper;

    @Override
    public Map<String, Object> getCountData() {
        //学生、教师、管理员
        List<Map<String, Object>> countData = dataViewMapper.getCountData();
        HashMap<String, Object> res = new HashMap<>();
        for (Map<String, Object> countDatum : countData) {
            Object o = countDatum.get("roleName");
            if ("管理员".equals(o)) {
                res.put("admin", countDatum.get("countNum"));
            } else if ("教师".equals(o)) {
                res.put("teacher", countDatum.get("countNum"));
            } else if ("学生".equals(o)) {
                res.put("student", countDatum.get("countNum"));
            }
        }
        //设备数
        res.put("equipmentCount", dataViewMapper.equipmentCount());
        //实验室数
        res.put("equipmentLabCount", dataViewMapper.equipmentLabCount());
        //供应商数
        res.put("equipmentSupplier", dataViewMapper.equipmentSupplierCount());
        return res;
    }

    @Override
    public Map<String, Object> getBarData() {
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", dataViewMapper.getBarData());
        return res;
    }

    @Override
    public Map<String, Object> getPieData() {
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", dataViewMapper.getPieData());
        return res;
    }

    @Override
    public Map<String, Object> getColumnarData() {
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", dataViewMapper.getColumnarData());
        return res;
    }

    @Override
    public Map<String, Object> getBgetLineDataarData() {
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", dataViewMapper.getLineData());
        return res;
    }
}
