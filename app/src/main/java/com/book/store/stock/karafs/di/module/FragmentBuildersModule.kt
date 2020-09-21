package com.book.store.stock.karafs.di.module

import com.book.store.stock.karafs.ui.relations.RelationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRelationFragment(): RelationFragment
}