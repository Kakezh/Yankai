import request from '@/utils/request'
const depart_api = '/system/depart'

// 添加部门树
export function addDepartTree(data) {
  return request({
    url: `${depart_api}/addDepartTree`,
    method: 'post',
    data
  })
}

// 编辑部门树
export function editDepartTree(data) {
  return request({
    url: `${depart_api}/editDepartTree`,
    method: 'post',
    data
  })
}

// 删除部门树
export function deleteDepartTree(departId) {
  return request({
    url: `${depart_api}/delete?id=${departId}`,
    method: 'delete'
  })
}

// 查询树列表
export function queryTreeList() {
  return request({
    url: `${depart_api}/queryTreeList`,
    method: 'get'
  })
}

// 根据部门ID查询部门
export function getDepartByDepartIds() {
  return request({
    url: `${depart_api}/getDepartByDepartIds`,
    method: 'get'
  })
}

// 查询所有部门
export function getAllDepart() {
  return request({
    url: `${depart_api}/getAllDepart`,
    method: 'get'
  })
}

// 根据用户名查询部门
export function getDepartByUsername(username) {
  return request({
    url: `${depart_api}/getDepartByUsername?username=${username}`,
    method: 'get'
  })
}