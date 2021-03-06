package com.test.karafs.di.component

import com.test.karafs.ui.relations.RelationViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }
    fun relationViewModel():RelationViewModel
}