package com.fzsx.vlib.service;

import com.fzsx.vlib.entity.Equipment;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * vlib - IEquipmentService
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public interface IEquipmentService {
    int saveSimulationEquipmentInfo(Equipment equipment);

    PageInfo<Equipment> getEquipmentByHelperList(int pageNum, int pageSize, Equipment equipment, int flag);

    int delete(Equipment equipment);

    /**
     * 设备明细信息
     * @param equipment
     * @return
     */
    List<Map> detail(Equipment equipment);

    int update(Equipment equipment);

    int booking(Equipment equipment, int userId);

    int finishBooking(Equipment equipment, int userId);

    PageInfo<Equipment> getUserEquipment(int pageNum, int pageSize, Equipment equipment, int userId);

    int getEquipmentId(Equipment equipment);
}
