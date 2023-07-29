package io.drdroid.amex.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.drdroid.amex.data.impl.RepositoryImpl
import io.drdroid.amex.data.repo.Repository

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesRepository(): Repository {
        return RepositoryImpl()
    }
}