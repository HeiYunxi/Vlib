import http from '../utils/http/http.js'

const login = (data) => {
    return http.post("/api/login", data);
};
const getUserList = (data) => {
    return http.post("/user/list", data);
};
const saveUser = (data) => {
    return http.post("/user/saveUserInfo", data);
};
const updateUser = (data) => {
    return http.post("/user/update", data);
};
const delUser = (data) => {
    return http.post("/user/delete", data);
};
const getUserDetail = (data) => {
    return http.post("/user/detail", data);
};
const register = (data) => {
    return http.post("/user/saveUserInfo", data);
};

const getRoleMap = (data) => {
    return http.post("/role/roleList", data);
};
const getUserRoleMap = (data) => {
    return http.post("/role/userRoleList", data);
};
const saveUserRole = (data) => {
    return http.post("/role/saveUserRole", data);
};

const getUserMenu = (data) => {
    return http.post("/api/getUserMenu", data);
};
export default {
    login, register, getUserList, saveUser, delUser, getUserDetail,
    updateUser, getRoleMap, getUserRoleMap, saveUserRole, getUserMenu
}