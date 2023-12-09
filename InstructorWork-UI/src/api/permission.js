import request from '@/utils/request'
const permission_api = '/system/permission'

// 添加权限
export function addPermission(data) {
  return request({
    url: `${permission_api}/add`,
    method: 'post',
    data
  })
}
// 编辑权限
export function editPermission(data) {
  return request({
    url: `${permission_api}/edit`,
    method: 'post',
    data
  })
}

// 删除权限
export function deletePermission(permissionId) {
  return request({
    url: `${permission_api}/delete?id=${permissionId}`,
    method: 'delete'
  })
}