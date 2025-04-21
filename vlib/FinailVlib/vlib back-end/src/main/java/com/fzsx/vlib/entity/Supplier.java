package com.fzsx.vlib.entity;

import java.util.Date;

/**
 * vlib - Supplier
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public class Supplier {
    //Id、供应商名、联系人、联系电话、邮箱、地址、创建时间、修改时间
    private int supplierId;
    private String supplierName;
    private String contact;
    private String phone;
    private String email;
    private String address;
    private Date create_time;
    private Date update_time;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
