import http from '../utils/http/http.js'

const getSupplierList = (data) => {
    return http.post("/supplier/list", data);
};
const updateSupplier = (data) => {
    return http.post("/supplier/update", data);
};
const delSupplier = (data) => {
    return http.post("/supplier/delete", data);
};

const getSupplierDetail = (data) => {
    return http.post("/supplier/detail", data);
};
const saveSupplierInfo = (data) => {
    return http.post("/supplier/saveSupplierInfo", data);
};
const getSupplierSimulationEquipmentList= (data) => {
    return http.post("/supplier/getSupplierEquipmentList", data);
};
const saveSupplierSimulationEquipmentList= (data) => {
    return http.post("/supplier/saveSupplierEquipmentList", data);
};
const getEquipmentList= (data) => {
    return http.post("/equipment/list", data);
};
export default {
    getSupplierList,
    delSupplier,
    getSupplierDetail,
    updateSupplier,
    saveSupplierInfo,
    getSupplierSimulationEquipmentList,
    saveSupplierSimulationEquipmentList,
    getEquipmentList
}