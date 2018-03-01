package com.example.gagan.myexampleproject.daggerhelpers.modules;

import android.content.Context;

import com.example.gagan.myexampleproject.daggerhelpers.scope.SingletonClassScope;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by Gagan on 3/1/2018.
 */
@Module(includes = ContextModule.class)
public class NetworkModule {
    @Provides
    @SingletonClassScope

    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @SingletonClassScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
    }

    @Provides
    @SingletonClassScope
    public File file(Context context) {
        File cache = new File(context.getCacheDir(), "okhttp_cache");
        cache.mkdir();
        return cache;
    }

    @Provides
    @SingletonClassScope
    public Cache cache(File cache) {

        return new Cache(cache, 10 * 1000 * 1000);

    }
}
