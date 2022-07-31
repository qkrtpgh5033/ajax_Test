package com.ll.exam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResultData<T>{

    private String resultCode;
    private String msg;
    private T data;

    public ResultData(String resultCode, String msg, T data) {
        this.resultCode = resultCode;
        this.msg = msg;
        this.data = data;
    }
}
