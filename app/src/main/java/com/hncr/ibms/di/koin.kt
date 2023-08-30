package com.hncr.ibms.di

import com.hncr.ibms.http.httpModule
import com.hncr.ibms.ui.floornavigation.FloorNagationViewModel
import com.hncr.ibms.ui.floornavigation.FloorNavigationRepository
import com.hncr.ibms.ui.suggestion.SuggestionFragment
import com.hncr.ibms.ui.suggestion.SuggestionRepository
import com.hncr.ibms.ui.suggestion.SuggestionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by LG
 * on 2023/8/2  9:51
 * Description：
 */

val viewModelModule = module {

    viewModel {
        FloorNagationViewModel(get())
    }
    viewModel {
        SuggestionViewModel(get())
    }

}

val repositoryModule = module {
    single {
        FloorNavigationRepository(get())
    }

    single {
        SuggestionRepository(get())
    }
}

val allModule = listOf(viewModelModule,repositoryModule, httpModule)