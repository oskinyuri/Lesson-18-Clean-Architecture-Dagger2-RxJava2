package com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface DayActivityScope {
}
