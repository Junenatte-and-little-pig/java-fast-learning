import axios from 'axios'
import {Message} from 'element-ui'
import Qs from 'qs'
import store from '@/store'


//防止sessionid 不一致
axios.defaults.withCredentials=true;

// 记录和显示错误
function errorLog (msg) {
    // 显示提示
    Message({
      message: msg,
      type: 'error',
      duration: 5 * 1000
    })
  }

const service = axios.create({
    //设置默认请求前缀
    baseURL: '/api',
    timeout: 50000,
    headers: {
    'content-type': 'application/x-www-form-urlencoded;charset=UTF-8',
    },
    responseType:'json',
  })

  // 请求拦截器
service.interceptors.request.use(
    config => {
        // store.state.user
    //   // 在请求发送之前做一些处理
    //   const token = util.cookies.get('token')
    //   // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
    //   config.headers['token'] = token
      return config
    },
    error => {
      // 发送失败
      console.log(error)
      return Promise.reject(error)
    }
  )
  
  // 响应拦截器
  service.interceptors.response.use(
    response => {
  
      return response;
    },
    error => {
      if (error && error.response) {
        switch (error.response.status) {
          case 400: error.message = '请求错误'; break
          case 401: error.message = '未授权，请登录'; break
          case 403: error.message = '拒绝访问'; break
          case 404: error.message = `请求地址出错: ${error.response.config.url}`; break
          case 408: error.message = '请求超时'; break
          case 500: error.message = '服务器内部错误'; break
          case 501: error.message = '服务未实现'; break
          case 502: error.message = '网关错误'; break
          case 503: error.message = '服务不可用'; break
          case 504: error.message = '网关超时'; break
          case 505: error.message = 'HTTP版本不受支持'; break
          default: break
        }
      }
      errorLog(error)
      return Promise.reject(error)
    }
  )
  
  export default {
    //get请求，其他类型请求复制粘贴，修改method
    get(url, param) {
        return new Promise((cback, reject) => {
            service({
                method: 'get',
                url,
                params: param,
            }).then(res => {
                var res_code = res.status.toString();
                if (res_code.charAt(0) == 2) {
                    cback(res);
                } else {
                    console.log(res, '异常1')
                }
            }).catch(err => {
                if (!err.response) {
                    console.log(err,'请求错误')
                } else {
                    reject(err.response);
                    console.log(err.response, '异常2')
                }
            })
        })
    },
    post(url, param) {
        return new Promise((cback, reject) => {
            service({
                method: 'post',
                url,
                data: Qs.stringify(param),
            }).then(res => {
                var res_code = res.status.toString();
                if (res_code.charAt(0) == 2) {
                    cback(res);
                } else {
                    console.log(res, '异常1')
                }
            })
            .catch(err => {
                if (!err.response) {
                    console.log(err,'请求错误')
                } else {
                    reject(err.response);
                    console.log(err.response, '异常2')
                }
            })
        })
    },
    postmedia(url, param) {
        return new Promise((cback, reject) => {
            service({
                method: 'post',
                headers: {
                    'content-type': 'multipart/form-data',
                },
                url,
                data: param,
            }).then(res => {
              
                var res_code = res.status.toString();
        
                if (res_code.charAt(0) == 2) {
                    cback(res);   //cback在promise执行器内部
                } else {
                    console.log(res, '异常1')
                }
            })
            .catch(err => {
                if (!err.response) {
                    console.log(err,'请求错误')
                } else {
                    reject(err.response); 
                    console.log(err.response, '异常2')
                }
            })
        })
    }
  } 