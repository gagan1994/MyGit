package com.example.gagan.myexampleproject.daggerhelpers.modules;

import android.content.Context;

import com.example.gagan.myexampleproject.daggerhelpers.scope.SingletonClassScope;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Gagan on 3/1/2018.
 */
@Module(includes = {ContextModule.class, NetworkModule.class})
public class PicassoModule {
    @Provides
    @SingletonClassScope
    public Picasso picasso(Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    @SingletonClassScope
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }
}
