package com.example.gagan.myexampleproject.daggerhelpers.modules;

import android.content.Context;

import com.example.gagan.myexampleproject.daggerhelpers.scope.SingletonClassScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Gagan on 3/1/2018.
 */
@Module
public class ContextModule {
    public final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @SingletonClassScope
    public Context context() {
        return context;
    }
}
