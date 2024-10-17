package com.example.lyfecycle_homework

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver

import androidx.lifecycle.LifecycleOwner


class MyObserver : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        Log.d("TAG", "onCreate")
    }

}