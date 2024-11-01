package com.example.listview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private var _userList = MutableLiveData<MutableList<User>>()
    val userList: MutableLiveData<MutableList<User>> = _userList


    fun addList(list: MutableList<User>) {
        _userList.value = list
    }

    fun getUser(position: Int): User? {
        return _userList.value?.get(position)
    }

    fun getList(): MutableList<User>?{
        return _userList.value
    }

    fun addUser(user: User) {
        val listOfUsers = _userList.value ?: mutableListOf<User>()
        listOfUsers.add(user)
        _userList.value = listOfUsers
    }

    fun deleteUser(user: User) {
        val listOfUsers = _userList.value?: mutableListOf<User>()
        listOfUsers.remove(user)
        _userList.value = listOfUsers
    }
}