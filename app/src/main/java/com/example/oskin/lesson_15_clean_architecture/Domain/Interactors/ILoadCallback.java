package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

public interface ILoadCallback {

    void onResponse();
    void onFailure();
}
