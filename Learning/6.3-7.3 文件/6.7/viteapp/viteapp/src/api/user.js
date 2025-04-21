import http from '../utils/http/http.js'

const login = (data) => {
    return http.post("/user/login", data);
};
const getUserList = (data) => {
    return http.post("/user/list", data);
};
const saveUser = (data) => {
    return http.post("/user/save", data);
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
export default {
    login, getUserList, saveUser, delUser, getUserDetail,updateUser
}