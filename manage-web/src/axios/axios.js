//进行远程调用
import axios from 'axios'
//包装param参数
import qs from 'qs'

// 环境的切换
// if (process.env.NODE_ENV == 'development') {
    axios.defaults.baseURL = 'http://127.0.0.1:8080';
// } else if (process.env.NODE_ENV == 'debug') {
//     axios.defaults.baseURL = 'http://127.0.0.1:8090/debug';
// } else if (process.env.NODE_ENV == 'production') {
//     axios.defaults.baseURL = 'http://127.0.0.1:8090/production';
// }

let token = ''

//创建http请求，包括url，消息头，参数
let http = axios.create({
    baseURL: axios.defaults.baseURL,
    withCredentials: false,
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
        'Authorization': token
    },
    transformRequest: [function (data) {
        let newData = '';
        for (let k in data) {
            if (data.hasOwnProperty(k) === true) {
                newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&';
            }
        }
        return newData;
    }]
});

//http response 拦截器，根据响应内容作出拦截，例如错误返回登录，token过期等等
http.interceptors.response.use(
    res => {
        console.log(res)
        if (res.data.code == 2 || res.data.code == 3) {

            Message({
                showClose: true,
                message: '登录信息失效，请重新登录!',
                type: 'error',
                duration: 2000
            })

            //携带当前页面路由，以在登录页面完成登录后返回当前页面
            router.replace({
                path: '/login',
                query: {
                    redirect: router.currentRoute.fullPath
                }
            });
        }

        if (res.status === 200) {
            return Promise.resolve(res);
        } else {
            return Promise.reject(res);
        }
    },
    err => {
        return Promise.reject(err);
    });

// 请求拦截器
// http.interceptors.request.use(
//     config => {
//         token = window.sessionStorage.getItem('access-token');
//         token = "11111111111111111"
//         // alert(token)
//         // config.headers.common['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8'
//         // config.headers.common['Authorization'] = token
//         config.headers = {
//             'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
//             'Authorization': token
//         }
//         return res
//     },error => {
//         console.log("error:",error)
//         return Promise.reject(error)
//     }
// )

//封装http请求方法，包括get/post，url，参数，响应
function apiAxios(method, url, params, response) {
    http({
        method: method,
        url: url,
        data: method === 'POST' || method === 'PUT' ? params : null,
        params: method === 'GET' || method === 'DELETE' ? params : null,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
            'Authorization': window.sessionStorage.getItem('access-token')
        }
    }).then(function (res) {
        response(res);
    }).catch(function (err) {
        response(err);
    })
}

//调用方法
export default {
    get: function (url, params, response) {
        return apiAxios('GET', url, params, response)
    },
    post: function (url, params, response) {
        return apiAxios('POST', url, params, response)
    },
    put: function (url, params, response) {
        return apiAxios('PUT', url, params, response)
    },
    delete: function (url, params, response) {
        return apiAxios('DELETE', url, params, response)
    }
}

//声明一个调用方法
export const requestLogin = params => {
  return axios.post('/login',qs.stringify(params)).then(res => res.data)
}
