package com.fzsx.vlib.mapper;

import com.fzsx.vlib.entity.Equipment;
import com.fzsx.vlib.entity.Supplier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * vlib - SupplierMapper.xml
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Component
public interface SupplierMapper {
    int saveSupplierInfo(Supplier supplier);

    int delete(Supplier supplier);

    List<Supplier> detail(Supplier supplier);

    List<Supplier> querySupplierList(Supplier supplier);

    int update(Supplier supplier);

    List<Supplier> supplierList(int equipmentId);

    List<Supplier> equipmentSupplierList(int equipmentId);

    int deleteEquipmentSupplier(int equipmentId);

    int saveEquipmentSupplierInfo(HashMap map);

    List<Equipment> getSupplierEquipmentList(int supplierId);

    int deleteSupplierEquipment(int supplierId);
}
