package com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Components;

import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.activity.SettingActivityModule;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.scopes.SettingActivityScope;
import com.example.oskin.lesson_15_clean_architecture.Presentation.UI.SettingsActivity;

import dagger.Component;

@Component (dependencies = AppComponent.class, modules = SettingActivityModule.class)
@SettingActivityScope
public interface SettingActivityComponent {

    void injectActivity(SettingsActivity settingsActivity);

}
