package com.example.gagan.myexampleproject.daggerhelpers.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Gagan on 3/1/2018.
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface SingletonClassScope {
}
