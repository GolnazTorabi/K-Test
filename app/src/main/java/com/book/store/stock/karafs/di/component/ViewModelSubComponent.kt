package com.book.store.stock.karafs.di.component

import com.book.store.stock.karafs.ui.relations.RelationViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }
    fun relationViewModel():RelationViewModel
}