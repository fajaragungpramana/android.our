package com.github.fajaragungpramana.our.core.app

sealed class AppResultState {

    data object InvalidName : AppResultState()
    data object InvalidEmail : AppResultState()
    data object InvalidPassword : AppResultState()

}