package com.gigasloth.standup

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object GsonModule {

    @Provides
    fun providesJsonResourceReader(
        @ApplicationContext context: Context,
        @PlainGson gson: Gson
    ) : JsonResourceReader {
        return JsonResourceReader(gson, context.resources)
    }

    @Provides
    @PrettyGson
    fun providesPrettyGson(gsonBuilder: GsonBuilder) : Gson {
        return gsonBuilder.setPrettyPrinting().create()
    }

    @Provides
    @PlainGson
    fun providesGson(gsonBuilder: GsonBuilder) : Gson {
        return gsonBuilder.create()
    }

    @Provides
    fun providesGsonBuilder() : GsonBuilder {
        return GsonBuilder()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrettyGson

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PlainGson