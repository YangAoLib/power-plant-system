// 深拷贝
const clone = (obj) => {
  let o
  // 如果  他是对象object的话  , 因为null,object,array  也是 'object';
  if (typeof obj === 'object') {
    // 如果  他是空的话
    if (obj === null) {
      o = null
    } else {
      if (obj instanceof Array) { // 如果  他是数组arr的话
        o = []
        for (let i = 0, len = obj.length; i < len; i++) {
          o.push(clone(obj[i]))
        }
      } else { // 如果  他是对象object的话
        o = {}
        for (const j in obj) {
          o[j] = clone(obj[j])
        }
      }
    }
  } else {
    o = obj
  }
  return o
}

export default clone
