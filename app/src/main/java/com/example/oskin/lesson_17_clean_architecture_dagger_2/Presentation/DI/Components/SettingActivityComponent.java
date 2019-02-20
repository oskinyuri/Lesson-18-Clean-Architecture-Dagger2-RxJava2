package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Components;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.activity.SettingActivityModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.scopes.SettingActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.UI.SettingsActivity;

import dagger.Component;

@Component (dependencies = AppComponent.class, modules = SettingActivityModule.class)
@SettingActivityScope
public interface SettingActivityComponent {

    void injectActivity(SettingsActivity settingsActivity);

}
