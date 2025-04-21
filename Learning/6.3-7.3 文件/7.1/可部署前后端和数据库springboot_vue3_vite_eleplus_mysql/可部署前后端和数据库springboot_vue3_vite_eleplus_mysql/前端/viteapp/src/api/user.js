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

const saveMenu = (data) => {
    return http.post("/menu/saveMenuInfo", data);
};
const getMenuDetail = (data) => {
    return http.post("/menu/detail", data);
};
const getMenuList = (data) => {
    return http.post("/menu/list", data);
};
const updateMenu = (data) => {
    return http.post("/menu/update", data);
};
const delMenu = (data) => {
    return http.post("/menu/delete", data);
};
//获取所有菜单列表
const getMenuAll = (data) => {
    return http.post("/menu/getMenuAll", data);
};
//获取加色选中的菜单列表
const getRoleMenu = (data) => {
    return http.post("/menu/getRoleMenu", data);
};
//设置角色菜单
const saveRoleMenu = (data) => {
    return http.post("/menu/saveRoleMenu", data);
};
//查询登录后角色菜单
const getUserMenu = (data) => {
    return http.post("/api/getUserMenu", data);
};

//角色列表
const getRoleMap = (data) => {
    return http.post("/role/roleList", data);
};
const getUserRoleMap = (data) => {
    return http.post("/role/userRoleList", data);
};
const saveUserRole = (data) => {
    return http.post("/role/saveUserRole", data);
};
//查询某个角色下的用户ID
const getRoleUserList = (data) => {
    return http.post("/role/getRoleUserList", data);
};
//保存某个角色下的用户ID
const saveRoleUserList = (data) => {
    return http.post("/role/saveRoleUserList", data);
};

export default {
    login, getUserList, saveUser, delUser, getUserDetail,updateUser,register,getRoleList,saveRole,updateRole,delRole,getRoleDetail,saveMenu,getMenuDetail,getMenuList,delMenu,updateMenu,getRoleMenu,saveRoleMenu,getRoleMap,getUserRoleMap,saveUserRole,getMenuAll,getUserMenu,getRoleUserList,saveRoleUserList
}