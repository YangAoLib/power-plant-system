import axios from 'axios'
import router from '@/router'

// axios.defaults.baseURL = '/api'
axios.defaults.baseURL = 'http://localhost:9090'

const request = axios.create({
  timeout: 6000
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
  config.headers['Content-Type'] = 'application/json;charset=utf-8'
  config.headers.token = localStorage.getItem('token') // 设置请求头

  // 如果配置不添加token, 则将token清除
  if (config.headers.noToken === true) {
    config.headers.token = ''
  }
  return config
}, error => {
  return Promise.reject(error)
})

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
  response => {
    let res = response.data
    // 如果是返回的文件
    if (response.config.responseType === 'blob') {
      return res
    }
    // 兼容服务端返回的字符串数据
    if (typeof res === 'string') {
      res = res ? JSON.parse(res) : res
    }
    // 未登录 或 权限不足, 跳转到登录页面
    if (res.status === 401 || res.status === 403) {
      // Message.error('您尚未登录, 请登录后再进行操作')
      router.replace('/login')
      // 返回异常信息
      return Promise.reject(res)
    }
    return res
  },
  error => {
    // 未登录 或 权限不足, 跳转到登录页面
    if (error.status === 401 || error.status === 403) {
      // Message.error('您尚未登录, 请登录后再进行操作')
      router.push('/login')
    }
    console.log('interceptor, seed error: ' + error) // for debug
    return Promise.reject(error)
  }
)

export default request
