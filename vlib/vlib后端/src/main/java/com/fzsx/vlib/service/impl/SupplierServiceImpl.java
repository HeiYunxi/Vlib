package com.fzsx.vlib.service.impl;

import com.fzsx.vlib.entity.Equipment;
import com.fzsx.vlib.entity.Role;
import com.fzsx.vlib.entity.Supplier;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.mapper.RoleMapper;
import com.fzsx.vlib.mapper.SupplierMapper;
import com.fzsx.vlib.service.ISupplierService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * vlib - SupplierServiceImpl
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Service
@Transactional
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    SupplierMapper supplierMapper;

    /**
     * 保存供应商信息
     * @param supplier
     * @return
     */
    public int saveSupplierInfo(Supplier supplier) {
        supplier.setCreate_time(new Date());
        supplier.setUpdate_time(new Date());
        return supplierMapper.saveSupplierInfo(supplier);
    }

    /**
     * 分页查询供应商信息
     *
     * @param currentPage
     * @param pageSize
     * @param role
     * @return
     */
    public PageInfo<Supplier> getSupplierByHelprList(int currentPage, int pageSize, Supplier supplier) {

        PageHelper.startPage(currentPage, pageSize);
        //分页查询
        List<Supplier> SupplierList = supplierMapper.querySupplierList(supplier);
        //得到分页的结果对象
        PageInfo<Supplier> SupplierPageInfo = new PageInfo<>(SupplierList);
        return SupplierPageInfo;
    }


    /**
     * 删除供应商
     *
     * @param supplier
     * @return
     */
    public int delete(Supplier supplier) {
        return supplierMapper.delete(supplier);
    }

    /**
     * 供应商明细信息
     *
     * @param supplier
     * @return
     */
    public List<Supplier> detail(Supplier supplier) {
        return supplierMapper.detail(supplier);
    }

    /**
     * 修改供应商信息
     *
     * @param supplier
     * @return
     */
    public int update(Supplier supplier) {
        supplier.setUpdate_time(new Date());
        return supplierMapper.update(supplier);
    }


    /**
     * 查询设备的供应商
     *
     * @param equipmentId
     * @return
     */
    public List<Supplier> supplierList(int equipmentId) {

        return supplierMapper.supplierList(equipmentId);
    }

    /**
     * 查询指定设备供应商信息
     *
     * @param userId
     * @return
     */
    public List<Supplier> equipmentSupplierList(int equipmentId) {

        return supplierMapper.equipmentSupplierList(equipmentId);
    }

    /**
     * 删除指定设备的所属供应商信息
     *
     * @param userId
     * @return
     */
    public int deleteEquipmentSupplier(int equipmentId) {

        return supplierMapper.deleteEquipmentSupplier(equipmentId);
    }

    /**
     * 插入设备供应商信息
     *
     * @param userId
     * @param list
     * @return
     */
    public int saveEquipmentSupplierInfo(int equipmentId, List list) {
        if (list.size() > 0) {
            int flag = 0;
            for (int i = 0; i < list.size(); i++) {
                String supplierId = list.get(i).toString();
                HashMap map = new HashMap();
                map.put("equipmentId", equipmentId);
                map.put("supplierId", supplierId);
                flag = flag + supplierMapper.saveEquipmentSupplierInfo(map);
            }
            return flag;
        } else {
            return 0;
        }
    }

    @Override
    public int saveEquipmentSupplierId(int equipmentId, int supplierId) {
        HashMap map = new HashMap();
        map.put("equipmentId", equipmentId);
        map.put("supplierId", supplierId);
        int flag =  supplierMapper.saveEquipmentSupplierInfo(map);
        return flag;
    }

    /**
     * 查询供应商对应所有设备ID
     *
     * @param roleId
     * @return
     */
    public List<Equipment> getSupplierEquipmentList(int supplierId) {
        return supplierMapper.getSupplierEquipmentList(supplierId);
    }

    /**
     * 保存指定供应商下的设备列表信息
     *
     * @param roleId
     * @param list
     * @return
     */
    public int saveSupplierEquipmentList(int supplierId, List list) {
        //删除原来角色对应用户信息
        int flag = supplierMapper.deleteSupplierEquipment(supplierId);
        //新增新的角色菜单信息
        if (flag >= 0) {
            HashMap map = new HashMap<>();
            flag = 0;
            if (list.size() == 0) {
                //清除所有用户
                flag = 0;
            }
            //插入新的角色对应用户信息
            for (int i = 0; i < list.size(); i++) {
                LinkedHashMap equipmentmap = (LinkedHashMap) list.get(i);
                map.put("supplierId", supplierId);
                map.put("equipmentId", equipmentmap.get("equipmentId"));
                flag = flag + supplierMapper.saveEquipmentSupplierInfo(map);
            }
            return flag;
        }
        return 0;
    }
}
