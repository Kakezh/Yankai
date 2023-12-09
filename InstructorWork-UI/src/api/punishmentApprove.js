import request from '@/utils/request'
const punishmentApprove_api = '/counselor/punishmentApprove'

// 根据辅导员获取处罚信息
export function getPunishmentByCounselor(pageNo, pageSize, data) {
  return request({
    url: `${punishmentApprove_api}/queryPunishmentByCounselor?pageNo=${pageNo}&pageSize=${pageSize}`,
    method: 'post',
    data
  })
}

// 通过处罚
export function passPunishment(data) {
  return request({
    url: `${punishmentApprove_api}/passPunishment`,
    method: 'post',
    data
  })
}

// 拒绝处罚
export function rejectPunishment(data) {
  return request({
    url: `${punishmentApprove_api}/rejectPunishment`,
    method: 'post',
    data
  })
}