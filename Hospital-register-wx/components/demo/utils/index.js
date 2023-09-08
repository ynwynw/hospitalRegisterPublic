/**
 * 为样式动态赋值
 * @param {*} style
 */
export function obj2style(style) {
  let str = [];
  Object.keys(style).forEach(key => {
    str.push(`${key}:${style[key]};`);
  });
  return str.join(';');
}
