package com.example.admin.interestimage.net.function;

import com.example.admin.interestimage.net.HttpResult;

import io.reactivex.functions.Function;

public class HttpResultFunction<T> implements Function<HttpResult<T>,T> {
    @Override
    public T apply(HttpResult<T> tHttpResult) throws Exception {
        return tHttpResult.getData();
    }
}
