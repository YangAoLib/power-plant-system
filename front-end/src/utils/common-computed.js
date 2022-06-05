import { isBlank } from '@/utils/string-utils'
import { isEmpty } from '@/utils/array-utils'

/**
 * 用于处理当字符串为空时需要置为null的操作, 只能针对一层的对象进行操作
 * @param prop 对象中的属性名
 * @param obj 对象在this中的属性名
 * @returns {{set(*): void, get(): string|*}}
 */
export const commonStringNull = (prop, obj) => ({
  get () {
    // 要返回的数据
    const temp = this[obj][prop]
    // 判断是否为空, 再进行返回
    return isBlank(temp) ? '' : temp
  },
  set (value) {
    this[obj][prop] = isBlank(value) ? null : value
  }
})

/**
 * 当设置的数组为空时, 将对应的属性设置为空, 只能针对一层的对象进行操作
 * @param prop 对象中属性的名称
 * @param obj 对象在this中的名称
 * @returns {{set(*): void, get(): []|*}}
 */
export const commonArrayEmptyNull = (prop, obj) => ({
  get () {
    return this[obj][prop] === undefined && this[obj][prop] === null ? [] : this[obj][prop]
  },
  set (value) {
    // console.log(value)
    if (!(value instanceof Array)) {
      console.error('传入参数错误')
      return
    }
    this[obj][prop] = isEmpty(value) ? null : value
  }
})

export default {
  commonStringNull,
  commonArrayEmptyNull
}
