package com.fzsx.vlib.entity;

import java.util.Date;

/**
 * vlib - SimulationEquipment
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public class Equipment {
    private int equipmentId;
    private String equipmentName;
    private String equipmentNumber;
    private int type;
    private String software_system;
    private String software_version;
    private int supplierId;
    private String supplierName;
    private int status;
    private String purpose;
    private String thumbnail;
    private Date create_time;
    private Date update_time;

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public  String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSoftware_system() {
        return software_system;
    }

    public void setSoftware_system(String software_system) {
        this.software_system = software_system;
    }

    public String getSoftware_version() {
        return software_version;
    }

    public void setSoftware_version(String software_version) {
        this.software_version = software_version;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
