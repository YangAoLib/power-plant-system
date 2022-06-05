/**
 * 判断数组是否为空
 * @param array 数组
 * @returns {boolean} 判断结果
 */
export function isEmpty (array) {
  if (array === undefined || array === null || array.length === 0) return true
  // 为空标记
  let flag = true
  for (const item of array) {
    flag = item === undefined || item === null
    if (!flag) break
  }
  return flag
}

export default {
  isEmpty
}
