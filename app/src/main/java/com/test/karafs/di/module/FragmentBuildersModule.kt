package com.test.karafs.di.module

import com.test.karafs.ui.relations.RelationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRelationFragment(): RelationFragment
}