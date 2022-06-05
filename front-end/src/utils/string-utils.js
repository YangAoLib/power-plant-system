import { NOT_EMPTY_STRING_REGEXP } from '@/utils/regexp-const'

export const SERVER_ERROR_MESSAGE = '服务器错误, 请联系管理员'

export function isBlank (str) {
  return str === undefined || str === null || str === '' || str.trim() === '' || !NOT_EMPTY_STRING_REGEXP.test(str)
}

export default {
  isBlank,
  SERVER_ERROR_MESSAGE
}
