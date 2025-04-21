import http from '../utils/http/http.js'
import {ElMessage} from "element-plus";

const getApplyList = (data) => {
    return http.post("/apply/listApplysByUserId", data);
};
const getReviewList= (data) => {
    return http.post("/apply/listApplysTeacherByInfor", data);
};
const updateApply = (data) => {
    return http.post("/apply/updateApply", data);
};
const delLab = (data) => {
    return http.post("/lab/delete", data);
};
const saveApplyInfo = (data) => {
    return http.post("/apply/submitApply", data);
};
const cancelApply = (data) => {
    return http.post("/apply/withDrawApply", data);
};
const getApplyById = (data) => {
    //const path = '/apply/getApplyById'
    return http.post("/apply/getApplyById", data);
}
const getApplyDetail= (data) => {
    //const path = '/apply/getApplyById'
    return http.post("/apply/detail", data);
}
const getReviewDetail= (data) => {
    //const path = '/apply/getApplyById'
    return http.post("/apply/reviewDetail", data);
}

const reviewApply= (data) => {
    //const path = '/apply/getApplyById'
    return http.post("/apply/reviewApply", data);
}

export default {
    getReviewDetail,
    getApplyList,
    delLab,
    saveApplyInfo,
    cancelApply,
    getApplyById,
    updateApply,
    getApplyDetail,
    getReviewList,
    reviewApply
}