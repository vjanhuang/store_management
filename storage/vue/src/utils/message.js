/**
 * @Description: 重写message挂载，实现 Class 的私有属性
 * @param { String } options => 消息内容
 * @param { Boolean } single => 是否只显示一个
 */
import { Message } from 'element-ui';

const showMessage = Symbol('showMessage');

class MyMessage {
    success (options, single = false) {
        this[showMessage]('success', options, single);
    }
    warning (options, single = false) {
        this[showMessage]('warning', options, single);
    }
    info (options, single = false) {
        this[showMessage]('info', options, single);
    }
    error (options, single = true) {
        this[showMessage]('error', options, single);
    }

    [showMessage] (type, options, single) {
        if (single) {
            // 判断是否已存在Message
            if (document.getElementsByClassName('el-message--error').length === 0) {
                Message[type](options);
            }
        } else {
            Message[type](options);
        }
    }
}

// 默认导出 私有 Message 组件
export default new MyMessage()
