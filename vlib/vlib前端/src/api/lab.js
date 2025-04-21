import http from '../utils/http/http.js'

const getLabList = (data) => {
    return http.post("/lab/list", data);
};
const updateLab = (data) => {
    return http.post("/lab/update", data);
};
const delLab = (data) => {
    return http.post("/lab/delete", data);
};

const getLabDetail = (data) => {
    return http.post("/lab/detail", data);
};
const saveLabInfo = (data) => {
    return http.post("/lab/save", data);
};
const getlabUserList = (data) => {
    return http.post("/lab/getManagerList", data);
};

export default {
    getLabList,
    delLab,
    getLabDetail,
    updateLab,
    saveLabInfo,
    getlabUserList
}