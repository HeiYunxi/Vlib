import http from '../utils/http/http.js'

const getMenuList = (data) => {
    return http.post("/menu/list", data);
};
const updateMenu = (data) => {
    return http.post("/menu/update", data);
};
const delMenu = (data) => {
    return http.post("/menu/delete", data);
};
const getMenuAll = (data) => {
    return http.post("/menu/getMenuAll", data);
};
const saveMenu = (data) => {
    return http.post("/menu/saveMenuInfo", data);
};
const getMenuDetail = (data) => {
    return http.post("/menu/detail", data);
};
export default {
    getMenuList,
    delMenu,
    getMenuDetail,
    updateMenu,
    getMenuAll,
    saveMenu,
}