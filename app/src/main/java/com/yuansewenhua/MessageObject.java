package com.yuansewenhua;

import java.io.Serializable;

/**
 * Created by fangshuai on 2015-01-24-0024.
 */
public class MessageObject implements Serializable {

    private CommandEnum what;    // 编码
    private Object object; // 对象内容
    private String certificate; // 认证信息，是否允许连接服务器


    public CommandEnum getWhat() {
        return what;
    }

    public void setWhat(CommandEnum what) {
        this.what = what;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
