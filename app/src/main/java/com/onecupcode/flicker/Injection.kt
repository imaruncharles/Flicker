/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onecupcode.flicker

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.onecupcode.flicker.api.FlickerServices
import com.onecupcode.flicker.data.FlickerRepository
import com.onecupcode.flicker.db.PhotoDatabase
import com.onecupcode.flicker.ui.ViewModelFactory



object Injection {


    private fun provideGithubRepository(context: Context): FlickerRepository {
        return FlickerRepository(FlickerServices.create(), PhotoDatabase.getInstance(context))
    }


    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }
}
