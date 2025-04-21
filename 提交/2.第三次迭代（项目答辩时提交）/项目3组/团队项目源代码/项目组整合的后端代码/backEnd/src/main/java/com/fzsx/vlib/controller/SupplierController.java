package com.fzsx.vlib.controller;

import com.fzsx.vlib.entity.Equipment;
import com.fzsx.vlib.entity.Role;
import com.fzsx.vlib.entity.Supplier;
import com.fzsx.vlib.entity.User;
import com.fzsx.vlib.service.IRoleService;
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
 * vlib - SupplierController
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Controller
@RequestMapping(value = "supplier")
public class SupplierController {
    @Autowired
    IRoleService iRoleService;
    @Autowired
    ISupplierService iSupplierService;

    /**
     * 保存供应商信息（添加供应商）
     *
     * @param supplier 供应商实体类
     * @return Map {"data", flag-操作条目}
     * @path supplier/saveSupplierInfo
     */
    @RequestMapping(value = "/saveSupplierInfo")
    @ResponseBody
    public Map<String, Object> saveSupplierInfo(@RequestBody Supplier supplier) {
        if (null != supplier) {
            if (null != supplier.getSupplierName() && !"".equals(supplier.getSupplierName())) {

                int flag = iSupplierService.saveSupplierInfo(supplier);
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
     * 获取供应商列表
     *
     * @param map {“pageNum”,“pageSize”,“supplierName”}
     * @return Map {"data", “total”}
     * @path supplier/list
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> getSupplierByHelprList(@RequestBody Map<String, Object> map) {
        int pageNum=map.get("pageNum").toString()==null?0:Integer.parseInt(map.get("pageNum").toString());
        int pageSize=map.get("pageSize").toString()==null?0:Integer.parseInt(map.get("pageSize").toString());
        String supplierName=map.get("supplierName").toString()==null?"":map.get("supplierName").toString();
        Supplier supplier=new Supplier();
        supplier.setSupplierName(supplierName);
        //分页查询
        PageInfo<Supplier> supplierPageInfo = iSupplierService.getSupplierByHelprList(pageNum,pageSize,supplier);

        List<Supplier> pageList = supplierPageInfo.getList();
        Map<String,Object> res = new HashMap<>();
        res.put("data",pageList);
        res.put("total",supplierPageInfo.getTotal());
        return res;
    }

    /**
     * 删除供应商
     *
     * @param supplier 供应商实体类
     * @return Map {"data", flag-操作条目}
     * @path supplier/delete
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody Supplier supplier) {
        if (null != supplier) {
            if (null != String.valueOf(supplier.getSupplierId()) &&!"".equals(String.valueOf(supplier.getSupplierId()))) {
                int flag = iSupplierService.delete(supplier);
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
     * 供应商详情信息
     *
     * @param supplier 供应商实体类
     * @return Map {"data", supplierList}
     * @path supplier/detail
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Map<String, Object> detail(@RequestBody Supplier supplier) {
        if (null != supplier) {
            if (null != String.valueOf(supplier.getSupplierId()) &&!"".equals(String.valueOf(supplier.getSupplierId()))) {
                List<Supplier> supplierList= iSupplierService.detail(supplier);
                Map<String, Object> map = new HashMap<>();
                map.put("data", supplierList);
                return map;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 更新供应商信息
     *
     * @param supplier 供应商实体类
     * @return Map {"data", flag-操作条目}
     * @path supplier/update
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Supplier supplier) {
        if (null != supplier) {
            if (null != String.valueOf(supplier.getSupplierId()) &&!"".equals(String.valueOf(supplier.getSupplierId()))) {

                int flag = iSupplierService.update(supplier);
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
     * 获取供应商列表
     *
     * @param map {equipmentId}
     * @return maps {"data", supplierList}
     * @path supplier/supplierList
     */
    @RequestMapping(value = "/supplierList")
    @ResponseBody
    public Map<String, Object> supplierList(@RequestBody Map<String, Object> map) {
        int equipmentId=map.get("equipmentId").toString()==null?0:Integer.parseInt(map.get("equipmentId").toString());
        List<Supplier> supplierLists= iSupplierService.supplierList(equipmentId);
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", supplierLists);
        return maps;

    }

    /**
     * 根据设备ID获取供应商列表
     *
     * @param map {equipmentId}
     * @return maps {"data", rolelist}
     * @path role/equipmentRoleList
     */
    @RequestMapping(value = "/equipmentSupplierList")
    @ResponseBody
    public Map<String, Object> equipmentSupplierList(@RequestBody Map<String, Object> map) {
        int equipmentId=map.get("equipmentId").toString()==null?0:Integer.parseInt(map.get("equipmentId").toString());
        List<Supplier> roleLists= iSupplierService.equipmentSupplierList(equipmentId);
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", roleLists);
        return maps;
    }

    /**
     * 保存设备供应商信息
     *
     * @param map
     * @path role/saveEquipmentSupplier
     */
    @RequestMapping(value = "/saveEquipmentSupplier")
    @ResponseBody
    public Map<String, Object> saveEquipmentSupplier(@RequestBody Map<String, Object> map) {
        int equipmentId=map.get("equipmentId").toString()==null?0:Integer.parseInt(map.get("equipmentId").toString());
        List list=(List)map.get("supplierList");
        Map<String, Object> maps = new HashMap<>();
        if(null!=list&&list.size()>0) {
            //删除原有的设备供应商信息
            int flag = iSupplierService.deleteEquipmentSupplier(equipmentId);
            //新增新的设备供应商信息
            if(flag>=0) {
                int status = iSupplierService.saveEquipmentSupplierInfo(equipmentId, list);
                maps.put("data", status);
            }
            return maps;
        }
        return null;
    }

    /**
     * 查询供应商对应所有设备ID
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/getSupplierEquipmentList")
    @ResponseBody
    public Map<String, Object> getSupplierEquipmentList(@RequestBody Map<String, Object> map) {


        int supplierId = map.get("supplierId").toString() == null ? 0 : Integer.parseInt(map.get("supplierId").toString());
        List<Equipment> roleLists = iSupplierService.getSupplierEquipmentList(supplierId);
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", roleLists);
        return maps;

    }

    /**
     * 保存指定供应商下的设备列表信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/saveSupplierEquipmentList")
    @ResponseBody
    public Map<String, Object> saveSupplierEquipmentList(@RequestBody Map<String, Object> map) {
        int supplierId = map.get("supplierId").toString() == null ? 0 : Integer.parseInt(map.get("supplierId").toString());
        List list = (List) map.get("equipmentList");
        Map<String, Object> maps = new HashMap<>();

        //删除原有的供应商菜单信息
        int flag = iSupplierService.saveSupplierEquipmentList(supplierId, list);
        maps.put("data", flag);
        return maps;

    }
}
