package com.mobilne.foto_zabawa.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class MainViewModel(): ViewModel() {
    var currentView by mutableStateOf("Settings")

    var currentCardId by mutableStateOf(0)
    var currentCardText by mutableStateOf("Test")



}