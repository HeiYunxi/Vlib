package com.fzsx.vlib.service.impl;

import com.fzsx.vlib.entity.Equipment;
import com.fzsx.vlib.mapper.EquipmentMapper;
import com.fzsx.vlib.service.IEquipmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * vlib - EquipmentServiceImpl
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Service
@Transactional
public class EquipmentServiceImpl implements IEquipmentService {

    @Autowired
    EquipmentMapper equipmentMapper;

    /**
     * @param equipment 实体类：虚拟设备
     * @return int - 数据操作数
     */
    public int saveSimulationEquipmentInfo(Equipment equipment) {
        String equipmentName = equipment.getEquipmentName();
        System.out.println("test:" + equipmentName);
        Equipment flag = equipmentMapper.getEquipmentByEquipmentName(equipmentName);
        if (flag != null) {
            return 0;
        } else {
            equipment.setCreate_time(new Date());
            equipment.setUpdate_time(new Date());
            return equipmentMapper.saveEquipmentInfo(equipment);
        }
    }

    @Override
    public PageInfo<Equipment> getEquipmentByHelperList(int currentPage, int pageSize, Equipment equipment, int flag) {
        PageHelper.startPage(currentPage, pageSize);
        //分页查询
        List<Equipment> equipmentList = equipmentMapper.queryEquipmentList(equipment, flag);
        //得到分页的结果对象
        PageInfo<Equipment> equipmentListPageInfo = new PageInfo<>(equipmentList);
        return equipmentListPageInfo;
    }

    /**
     * 删除设备
     *
     * @param equipment
     * @return
     */
    @Override
    public int delete(Equipment equipment) {
        return equipmentMapper.delete(equipment);
    }

    @Override
    public List<Map> detail(Equipment equipment) {
        return equipmentMapper.detail(equipment);
    }

    @Override
    public int update(Equipment equipment) {
        equipment.setUpdate_time(new Date());
        return equipmentMapper.update(equipment);
    }

    @Override
    public int booking(Equipment equipment, int userId) {
        int equipmentId = equipment.getEquipmentId();
        System.out.println("test:" + equipmentId);
        Equipment flag = equipmentMapper.getEquipmentStatusById(equipmentId);
        if (flag == null) {
            return 0;
        } else {
            equipment.setUpdate_time(new Date());
            equipmentMapper.booking_userEquipmentInfo(userId, equipment.getEquipmentId());
            return equipmentMapper.booking(equipment);
        }
    }

    @Override
    public int finishBooking(Equipment equipment, int userId) {
        int equipmentId = equipment.getEquipmentId();
        System.out.println("test:" + equipmentId);
        Equipment flag = equipmentMapper.getEquipmentFinishStatusById(equipmentId);
        if (flag == null) {
            return 0;
        } else {
            equipment.setUpdate_time(new Date());
            equipmentMapper.finishBooking_userEquipmentInfo(userId, equipment.getEquipmentId());
            return equipmentMapper.finishBooking(equipment);
        }
    }

    @Override
    public PageInfo<Equipment> getUserEquipment(int pageNum, int pageSize, Equipment equipment, int userId) {
        PageHelper.startPage(pageNum, pageSize);
        //分页查询
        List<Equipment> equipmentList = equipmentMapper.queryUserEquipmentList(equipment, userId);
        //得到分页的结果对象
        PageInfo<Equipment> simulationEquipmentListPageInfo = new PageInfo<>(equipmentList);
        return simulationEquipmentListPageInfo;
    }

    public int getEquipmentId(Equipment equipment){
        return equipmentMapper.getEquipmentId(equipment);
    }
}
