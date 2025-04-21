package com.fzsx.vlib.controller;

import com.fzsx.vlib.entity.Equipment;
import com.fzsx.vlib.service.IEquipmentService;
import com.fzsx.vlib.service.ISupplierService;
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
 * SimulationEquipmentController - 设备控制类 - 后端
 *
 * @author Heiyunxi
 * @version 0.3.0
 * 2024.6.29
 */
@Controller
@RequestMapping("equipment")
public class EquipmentController {

    @Autowired
    IEquipmentService iEquipmentService;
    @Autowired
    ISupplierService iSupplierService;

    /**
     * 添加设备
     *
     * @param equipment 设备实体类
     * @return Map {"data", flag-操作条目}
     * @path equipment/save
     */
    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> saveSimulationEquipmentInfo(@RequestBody Equipment equipment) {

        if (null != equipment) {
            if (null != equipment.getEquipmentName() && null != equipment.getEquipmentName() &&
                    !"".equals(equipment.getEquipmentNumber()) && !"".equals(equipment.getEquipmentNumber())) {

                int flag = iEquipmentService.saveSimulationEquipmentInfo(equipment);

                int equipmentId = iEquipmentService.getEquipmentId(equipment);
                int supplierId = equipment.getSupplierId();
                iSupplierService.saveEquipmentSupplierId(equipmentId, supplierId);

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
     * 设备列表分页
     *
     * @param map {“pageNum”,“pageSize”,“equipmentName”}
     * @return res {"data", "total"}
     * @path equipment/list
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> selectSimulationEquipmentList(@RequestBody Map<String, Object> map) {

        int pageNum = map.get("pageNum").toString() == null ? 0 : Integer.parseInt(map.get("pageNum").toString());
        int pageSize = map.get("pageSize").toString() == null ? 0 : Integer.parseInt(map.get("pageSize").toString());
        String equipmentName = map.get("equipmentName").toString() == null ? "" : map.get("equipmentName").toString();
        //int flag = map.get("flag").toString() == null ? 0 : Integer.parseInt(map.get("flag").toString());
        int flag = map.get("flag").toString()=="true" ? 1 : 0;

        Equipment equipment = new Equipment();
        equipment.setEquipmentName(equipmentName);
        //0：分页查询 1：可用查询
        PageInfo<Equipment> simulationEquipmentPageInfo = iEquipmentService.getEquipmentByHelperList(pageNum, pageSize, equipment,flag);
        //得到分页中的user条目对象
        List<Equipment> pageList = simulationEquipmentPageInfo.getList();
        Map<String, Object> res = new HashMap<>();
        res.put("data", pageList);
        res.put("total", simulationEquipmentPageInfo.getTotal());
        return res;
    }

    /**
     * 删除设备信息
     *
     * @param equipment 设备实体类
     * @return Map {"data", flag-操作条目}
     * @path equipment/delete
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody Equipment equipment) {
        if (null != equipment) {
            if (null != String.valueOf(equipment.getEquipmentId()) && !"".equals(String.valueOf(equipment.getEquipmentId()))) {
                int flag = iEquipmentService.delete(equipment);
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
     * 设备详情信息
     *
     * @param equipment 设备实体类
     * @return Map {"data", equipmentList}
     * @path equipment/detail
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Map<String, Object> detail(@RequestBody Equipment equipment) {

        if (null != equipment) {
            if (null != String.valueOf(equipment.getEquipmentId()) && !"".equals(String.valueOf(equipment.getEquipmentId()))) {
                List<Map> equipmentList = iEquipmentService.detail(equipment);
                Map<String, Object> map = new HashMap<>();
                map.put("data", equipmentList);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 更新设备信息
     *
     * @param equipment 设备实体类
     * @return Map {"data", flag-操作条目}
     * @path equipment/update
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Equipment equipment) {
        if (null != equipment) {
            if (null != String.valueOf(equipment.getEquipmentId()) && !"".equals(String.valueOf(equipment.getEquipmentId()))) {

                int equipmentId = equipment.getEquipmentId();
                int supplierId = equipment.getSupplierId();

                iSupplierService.deleteEquipmentSupplier(equipmentId);
                iSupplierService.saveEquipmentSupplierId(equipmentId, supplierId);

                int flag = iEquipmentService.update(equipment);
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
     * 预约设备（修改设备状态为已预约）
     *
     * @param map 设备实体类
     * @return Map {"data", flag-操作条目}
     * @path equipment/booking
     */
    @RequestMapping(value = "/booking")
    @ResponseBody
    public Map<String, Object> booking(@RequestBody Map<String, Object> map) {
        int equipmentId = map.get("equipmentId").toString() == null ? 0 : Integer.parseInt(map.get("equipmentId").toString());
        int userId = map.get("userId").toString() == null ? 0 : Integer.parseInt(map.get("userId").toString());

//        if (null != equipment) {
            if (!"".equals(equipmentId)) {
                Equipment equipment = new Equipment();
                equipment.setEquipmentId(equipmentId);
                int flag = iEquipmentService.booking(equipment,userId);
                Map<String, Object> maps = new HashMap<>();
                maps.put("data", flag);
                return maps;
            } else {
                return null;
            }
    }

    /**
     * 归还设备（修改设备状态为可预约）
     *
     * @param simulationEquipment 设备实体类
     * @return Map {"data", flag-操作条目}
     * @path equipment/booking
     */
    @RequestMapping(value = "/finishBooking")
    @ResponseBody
    public Map<String, Object> finishBooking(@RequestBody Map<String, Object> map) {
        int equipmentId = map.get("equipmentId").toString() == null ? 0 : Integer.parseInt(map.get("equipmentId").toString());
        int userId = map.get("userId").toString() == null ? 0 : Integer.parseInt(map.get("userId").toString());
//        if (null != simulationEquipment) {
            if (!"".equals(equipmentId)) {
                Equipment equipment = new Equipment();
                equipment.setEquipmentId(equipmentId);
                int flag = iEquipmentService.finishBooking(equipment,userId);
                Map<String, Object> maps = new HashMap<>();
                maps.put("data", flag);
                return maps;
            } else {
                return null;
            }
//        }
//        return null;
    }

    /**
     * 获取用户预约设备列表
     *
     * @param map map {“pageNum”，“pageSize”，“simulationEquipmentName”，“userId”}
     * @return res {data，total}
     * @path equipment/getUserEquipment
     */
    @RequestMapping(value = "/getUserEquipment")
    @ResponseBody
    public Map<String, Object> getUserEquipment(@RequestBody Map<String, Object> map) {
        int pageNum = map.get("pageNum").toString() == null ? 0 : Integer.parseInt(map.get("pageNum").toString());
        int pageSize = map.get("pageSize").toString() == null ? 0 : Integer.parseInt(map.get("pageSize").toString());
        String equipmentName = map.get("equipmentName").toString() == null ? "" : map.get("equipmentName").toString();
        int userId = map.get("userId").toString() == null ? 0 : Integer.parseInt(map.get("userId").toString());
        Equipment equipment = new Equipment();
        equipment.setEquipmentName(equipmentName);

        PageInfo<Equipment> simulationEquipmentPageInfo = iEquipmentService.getUserEquipment(pageNum, pageSize, equipment,userId);

        List<Equipment> pageList = simulationEquipmentPageInfo.getList();
        Map<String, Object> res = new HashMap<>();
        res.put("data", pageList);
        res.put("total", simulationEquipmentPageInfo.getTotal());
        return res;
    }

}
