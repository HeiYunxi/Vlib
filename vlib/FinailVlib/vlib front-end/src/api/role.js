import http from '../utils/http/http.js'

const getRoleList = (data) => {
    return http.post("/role/list", data);
};
const saveRole = (data) => {
    return http.post("/role/saveRoleInfo", data);
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
const getMenuAll = (data) => {
    return http.post("/menu/getMenuAll", data);
};
const getRoleMenu = (data) => {
    return http.post("/menu/getRoleMenu", data);
};
const saveRoleMenu = (data) => {
    return http.post("/menu/saveRoleMenu", data);
};
//查询某个角色下的用户ID
const getRoleUserList = (data) => {
    return http.post("/role/getRoleUserList", data);
};
//保存某个角色下的用户ID
const saveRoleUserList = (data) => {
    return http.post("/role/saveRoleUserList", data);
};
const getUserList = (data) => {
    return http.post("/user/list", data);
};
export default {
    saveRole,
    getRoleDetail,
    getRoleList,
    delRole,
    updateRole,
    saveRoleMenu,
    getMenuAll,
    getRoleMenu,
    getRoleUserList,
    saveRoleUserList,
    getUserList
}