package com.fzsx.vlib.service;

import com.fzsx.vlib.entity.Equipment;
import com.fzsx.vlib.entity.Supplier;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * vlib - ISupplierService
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public interface ISupplierService {
    int saveSupplierInfo(Supplier supplier);

    /**
     * 分页查询供应商信息
     *
     * @param currentPage
     * @param pageSize
     * @param role
     * @return
     */
    PageInfo<Supplier> getSupplierByHelprList(int currentPage, int pageSize, Supplier supplier);


    /**
     * 删除供应商
     *
     * @param supplier
     * @return
     */
    int delete(Supplier supplier) ;

    /**
     * 供应商明细信息
     *
     * @param supplier
     * @return
     */
    List<Supplier> detail(Supplier supplier);
    /**
     * 修改供应商信息
     *
     * @param supplier
     * @return
     */
    int update(Supplier supplier);


    /**
     * 查询所有设备的供应商
     *
     * @param equipmentId 可不传参，未修改
     * @return
     */
    List<Supplier> supplierList(int equipmentId);

    /**
     * 查询指定设备供应商信息
     *
     * @param userId
     * @return
     */
    List<Supplier> equipmentSupplierList(int equipmentId);

    /**
     * 删除指定设备的所属供应商信息
     *
     * @param userId
     * @return
     */
    int deleteEquipmentSupplier(int equipmentId);
    /**
     * 插入设备供应商信息
     *
     * @param userId
     * @param list
     * @return
     */
    int saveEquipmentSupplierInfo(int equipmentId, List list);
    int saveEquipmentSupplierId(int equipmentId, int supplierId);
    /**
     * 查询供应商对应所有设备ID
     *
     * @param roleId
     * @return
     */
    List<Equipment> getSupplierEquipmentList(int supplierId);

    /**
     * 保存指定供应商下的设备列表信息
     *
     * @param roleId
     * @param list
     * @return
     */
    int saveSupplierEquipmentList(int supplierId, List list);
}
