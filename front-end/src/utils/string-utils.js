import { NOT_EMPTY_STRING_REGEXP } from '@/utils/regexp-const'

export function isBlank (str) {
  return str === undefined || str === null || str === '' || str.trim() === '' || !NOT_EMPTY_STRING_REGEXP.test(str)
}

export default {
  isBlank
}
