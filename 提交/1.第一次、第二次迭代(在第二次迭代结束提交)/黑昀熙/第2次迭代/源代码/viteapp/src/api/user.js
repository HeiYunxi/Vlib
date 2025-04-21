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
export default {
    login,register,getUserList, saveUser, delUser, getUserDetail,updateUser
}