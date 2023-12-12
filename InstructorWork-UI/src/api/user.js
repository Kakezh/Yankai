import request from '@/utils/request'
const login_api = '/system'
const user_api = '/system/user'
const permission_api = '/system/permission'

// 导出一个函数，用于登录
export function login(data) {
  // 发送请求，请求地址为login_api/login，请求方式为post，请求参数为data
  return request({
    url: `${login_api}/login`,
    method: 'post',
    data
  })
}

export function register(data) {
  // 发送请求，请求地址为login_api/register，请求方式为post，请求参数为data
  return request({
    url: `${login_api}/register`,
    method: 'post',
    data
  })
}

export function unfreeze(data) {
  // 发送请求，请求地址为login_api/unfreeze，请求方式为post，请求参数为data
  return request({
    url: `${login_api}/unfreeze`,
    method: 'post',
    data
  })
}

export function bind(data) {
  // 发送请求，请求地址为login_api/unfreeze，请求方式为post，请求参数为data
  return request({
    url: `${login_api}/bind`,
    method: 'post',
    data
  })
}

// 导出一个函数，用于检查用户名
export function checkusername(userId, username) {
  // 向服务器发送请求，检查用户名
  return request({
    // 请求的url
    url: `${user_api}/checkUsername?userId=${userId}&username=${username}`,
    // 请求的方法
    method: 'get'
  })
}

export function addUser(data) {
  return request({
    url: `${user_api}/addUserByRole`,
    method: 'post',
    data
  })
}

export function editUser(data) {
  return request({
    url: `${user_api}/edit`,
    method: 'post',
    data
  })
}
export function deleteUser(userId) {
  return request({
    url: `${user_api}/delete?id=${userId}`,
    method: 'delete'
  })
}
export function getAllUser(pageNo, pageSize, searchObj) {
  return request({
    url: `${user_api}/queryAll?pageNo=${pageNo}&pageSize=${pageSize}`,
    method: 'post',
    data: searchObj
  })
}

export function getPermissionList() {
  return request({
    url: `${permission_api}/list`,
    method: 'get'
  })
}

export function getUserPermissionList(username) {
  return request({
    url: `${permission_api}/queryUserRoleList?username=${username}`,
    method: 'get'
  })
}

export function getInfo(token) {
  return request({
    url: '/vue-admin-template/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout(data) {
  return request({
    url: `${login_api}/logout`,
    method: 'post',
    data
  })
}
