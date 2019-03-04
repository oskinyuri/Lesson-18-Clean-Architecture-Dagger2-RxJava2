package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto;

import androidx.annotation.NonNull;

public class ResponseBundle<T> {

    private boolean mHasValue;
    private T mValue;

    private Exception mExceptions;

    public ResponseBundle(@NonNull T value) {
        mHasValue = true;
        mValue = value;
    }

    public ResponseBundle(Exception exceptions) {
        mHasValue = false;
        mExceptions = exceptions;
    }

    public T getResponse() {
        return mValue;
    }

    public Exception getExceptions() {
        return mExceptions;
    }



    public boolean isHasValue() {
        return mHasValue;
    }
}
