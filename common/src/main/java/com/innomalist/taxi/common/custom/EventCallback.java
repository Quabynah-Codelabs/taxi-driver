package com.innomalist.taxi.common.custom;

import androidx.annotation.Nullable;

public interface EventCallback<T> {

    void onError(@Nullable String message);

    void onSuccess(@Nullable T result);

    void onStart();
}
