import { isBlank } from '@/utils/string-utils'

export const commonStringNull = (prop, obj) => ({
  get () {
    // 要返回的数据
    const temp = obj[prop]
    // 判断是否为空, 再进行返回
    return isBlank(temp) ? '' : temp
  },
  set (value) {
    obj[prop] = isBlank(value) ? null : value
  }
})

export default {
  commonStringNull
}
