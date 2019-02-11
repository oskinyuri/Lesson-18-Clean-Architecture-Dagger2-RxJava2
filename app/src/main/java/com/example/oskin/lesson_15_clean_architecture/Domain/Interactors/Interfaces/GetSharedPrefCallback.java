package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;

public interface GetSharedPrefCallback {
    void onResponse(SharedPrefDTO sharedPrefDTO);
}
