package com.github.fajaragungpramana.our.module

sealed class MainEvent {

    data object OnLogin : MainEvent()

}