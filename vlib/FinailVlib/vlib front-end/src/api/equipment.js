import http from '../utils/http/http.js'

const getSimulationEquipmentList = (data) => {
    return http.post("/equipment/list", data);
};
const updateSimulationEquipment = (data) => {
    return http.post("/equipment/update", data);
};
const delSimulationEquipment = (data) => {
    return http.post("/equipment/delete", data);
};

const getSimulationEquipmentDetail = (data) => {
    return http.post("/equipment/detail", data);
};
const saveSimulationEquipmentInfo = (data) => {
    return http.post("/equipment/save", data);
};

const getUserEquipment = (data) => {
    return http.post("/equipment/getUserEquipment", data);
};

const bookingEquipment = (data) => {
    return http.post("/equipment/booking", data);
};

const cancelBookingEquipment = (data) => {
    return http.post("/equipment/finishBooking", data);
};
const getSupplierEquipmentList = (data) => {
    return http.post("/supplier/supplierList", data);
};
export default {
    getSimulationEquipmentList,
    delSimulationEquipment,
    getSimulationEquipmentDetail,
    updateSimulationEquipment,
    saveSimulationEquipmentInfo,
    getUserEquipment,
    bookingEquipment,
    cancelBookingEquipment,
    getSupplierEquipmentList
}