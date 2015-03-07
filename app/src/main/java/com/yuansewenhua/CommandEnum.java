package com.yuansewenhua;

/**
 * Created by fangshuai on 2015-01-24-0024.
 */
public enum CommandEnum {

    FILE,   // 传文件
    FILE_OK,
    FILE_FAIL,

    DB,     // 传数据库json形式
    DB_OK,
    DB_FAIL,

    ORDER_CREATE,    // 创建订单
    ORDER_CREATE_OK,
    ORDER_CREATE_FAIL,

    ORDER_APPEND,    // 追加订单
    ORDER_APPEND_OK,
    ORDER_APPEND_FAIL,

    ORDER_REDUCE,    // 削减订单
    ORDER_REDUCE_OK,
    ORDER_REDUCE_FAIL,

    BILL,           // 结账
    BILL_OK,
    BILL_FAIL;

}
