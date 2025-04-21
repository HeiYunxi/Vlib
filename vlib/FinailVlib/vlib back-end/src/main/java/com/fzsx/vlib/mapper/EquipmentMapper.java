package com.fzsx.vlib.mapper;

import com.fzsx.vlib.entity.Equipment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * vlib - SimulationEquipmentMapper
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Component
public interface EquipmentMapper {

    int saveEquipmentInfo(Equipment equipment);

    List<Equipment> queryEquipmentList(@Param("equipment") Equipment equipment, @Param("flag") int flag);

    /**
     * 在数据库中查询该角色是否已存在
     */
    Equipment getEquipmentByEquipmentName(String equipmentName);

    /**
     * 删除设备
     *
     * @param equipment
     * @return
     */
    int delete(Equipment equipment);

    /**
     * 设备明细信息
     *
     * @param equipment
     * @return
     */
    List<Map> detail(Equipment equipment);

    /**
     * 设备信息更新
     *
     * @param equipment
     * @return
     */
    int update(Equipment equipment);

    List<Equipment> queryUserEquipmentList(@Param("equipment") Equipment equipment, @Param("userId") int userId);


    int booking(Equipment equipment);

    void booking_userEquipmentInfo(@Param("userId") int userId, @Param("equipmentId") int equipmentId);

    Equipment getEquipmentStatusById(int equipmentId);

    int finishBooking(Equipment equipment);

    void finishBooking_userEquipmentInfo(@Param("userId") int userId, @Param("equipmentId") int equipmentId);

    Equipment getEquipmentFinishStatusById(int equipmentId);

    int getEquipmentId(Equipment equipment);
}
