import axios from "axios";

const http = axios.create({
    //在這裏改變後端的請求地址
    // baseURL: '/api',
    baseURL: '/api',
    timeout: 10000,
})

axios.interceptors.request.use(function (config){
    return config;
},function (error){
    return Promise.reject(error)
    }
);

http.interceptors.response.use(function (response){
    return response;
}, function (error){
    return Promise.reject(error)
})
export default http