import http from "../utils/http/http.js";

const getData1 = () => {
    const path = '/data/getData1'
    return http.post(path,null );
}
const getBarData = () => {
    const path = '/data/getBarData'
    return http.post(path,null);
}

const getPieData = () => {
    const path = '/data/getPieData'
    return http.post(path );
}

const getColumnarData = () => {
    const path = '/data/getColumnarData'
    return http.post(path );
}

const getLineData = () => {
    const path = '/data/getLineData'
    return http.post(path );
}
export default {
    getData1,
    getBarData,
    getPieData,
    getColumnarData,
    getLineData
}

