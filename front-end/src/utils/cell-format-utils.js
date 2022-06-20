import moment from 'moment'

/**
 * 格式化表格内的日期
 * @param row 行
 * @param column 列
 * @param cellValue 单元格的值
 * @param index 索引
 * @returns {string} 格式化后的字符串
 */
export const cellDateFormate = (row, column, cellValue, index) => {
  if (cellValue) {
    return moment(cellValue).format('yyyy-MM-DD')
  } else {
    return '时间不详'
  }
}

/**
 * 格式化表格内的日期时间
 * @param row 行
 * @param column 列
 * @param cellValue 单元格的值
 * @param index 索引
 * @returns {string} 格式化后的字符串
 */
export const cellDateTimeFormate = (row, column, cellValue, index) => {
  if (cellValue) {
    return moment(cellValue).format('yyyy-MM-DD HH:mm:ss')
  } else {
    return '时间不详'
  }
}

export default {
  cellDateTimeFormate,
  cellDateFormate
}
