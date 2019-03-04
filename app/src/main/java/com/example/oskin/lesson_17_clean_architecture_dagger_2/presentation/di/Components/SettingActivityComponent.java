package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Components;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.activity.SettingActivityModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.scopes.SettingActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.ui.SettingsActivity;

import dagger.Component;

@Component (dependencies = AppComponent.class, modules = SettingActivityModule.class)
@SettingActivityScope
public interface SettingActivityComponent {

    void injectActivity(SettingsActivity settingsActivity);

}
