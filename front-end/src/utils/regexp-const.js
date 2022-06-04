// 身份证号 正则表达式
export const CARD_ID_REGEXP = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([012]\d)|3[0-1])\d{3}(\d|[Xx])$/
// 手机号 正则表达式
export const PHONE_REGEXP = /^1(3\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\d|9[0-35-9])\d{8}$/
// 非空字符串 正则表达式
export const NOT_EMPTY_STRING_REGEXP = /^[^\s\n\f\r\t\v]+$/

export default {
  CARD_ID_REGEXP,
  PHONE_REGEXP,
  NOT_EMPTY_STRING_REGEXP
}
