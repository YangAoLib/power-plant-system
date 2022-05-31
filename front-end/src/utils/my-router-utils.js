import clone from 'clone'
import Path from 'path'
import router from '@/router'

// 将组件解析出来
export function componentGet (item) {
  const path = item.component
  if (item.component) {
    // 路由组件
    // item.component = resolve => require([`@/views${path}`], resolve)
    item.component = () => import(`@/views${path}`)
  }
  if (item.children) {
    item.children.forEach(item => componentGet(item))
  }
}

// 动态添加路由
export function addRoute (parentName, item) {
  item = clone(item)
  if (item.component) {
    // 当前路由存在组件, 则直接添加到路由
    const { children, ...singleItem } = item
    router.addRoute(parentName, singleItem)
    parentName = item.name
    if (item.children) {
      // 有子路由则进行添加到父
      item.children.forEach(value => {
        addRoute(parentName, value)
      })
    }
  } else if (item.children) { // 如果当前父路由没有组件, 则将子路由直接添加上层路由中
    // 对每一个children执行修改路径并添加到路由的操作
    item.children.forEach(value => {
      // 修改子路由的路径, 使其匹配
      value.path = Path.join(item.path, value.path)
      addRoute(parentName, value)
    })
  }
}

export function addDynamicRoute (parentName, menuList) {
  menuList = clone(menuList)
  menuList.forEach(item => componentGet(item))
  menuList.forEach(item => addRoute(parentName, item))
}

export default {
  componentGet,
  addRoute,
  addDynamicRoute
}
