package com.alphawallet.app.util.boc;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

class GsonResponseBodyConverterToString<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverterToString(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    public T convert(ResponseBody value) throws IOException {
        String returned = value.string();

        try {
            return this.gson.fromJson(returned, this.type);
        } catch (JsonParseException var4) {
            return (T) returned;
        }
    }
}

