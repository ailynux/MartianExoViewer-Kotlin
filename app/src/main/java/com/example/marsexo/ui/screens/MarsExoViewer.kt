
package com.example.marsexo.ui.screens

import android.app.Application
import com.example.marsexo.data.AppContainer
import com.example.marsexo.data.DefaultAppContainer

class MarsExoViewer : Application() { // app name will be MarsExoViewer
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
