package com.book.store.stock.karafs.di.component

import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }
}