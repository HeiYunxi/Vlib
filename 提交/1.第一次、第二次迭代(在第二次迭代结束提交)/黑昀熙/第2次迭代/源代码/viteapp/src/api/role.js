import http from '../utils/http/http.js'

const getRoleList = (data) => {
    return http.post("/role/list", data);
};
const updateRole = (data) => {
    return http.post("/role/update", data);
};
const delRole = (data) => {
    return http.post("/role/delete", data);
};
const getRoleDetail = (data) => {
    return http.post("/role/detail", data);
};
const saveRoleInfo = (data) => {
    return http.post("/role/saveRoleInfo", data);
};
export default {
    saveRoleInfo,getRoleList, delRole, getRoleDetail,updateRole
}