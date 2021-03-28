import axios from '../axios.min.js'

const message = window.ElementPlus.ElMessage

// create an axios instance
const service = axios.create({
  baseURL: "http://sacp.com", // url = base url + request url
  timeout: 50000,
  withCredentials: true
})

// 请求前拦截
service.interceptors.request.use(
  config => {

    return config
  },
  error => {
    console.log(error)
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    const res = response.data
      if (res.code !==200){
          console.log(ElementPlus)
          message.error('请求失败！')
      }else {
          return res
      }
  },
  error => {
    console.log('err' + error) // for debug
    message.error('系统异常！')
    return Promise.reject(error)
  }
)

export default service
