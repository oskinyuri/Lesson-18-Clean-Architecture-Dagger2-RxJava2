package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface DayActivityScope {
}
