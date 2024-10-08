package com.omerakkoyun.unittestsdemo.test5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map


/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class UserListViewModel(private val repo: UserListRepo) : ViewModel() {

    val listLive = MutableLiveData<List<User>>()
    val errorLive = MutableLiveData<Throwable>()
    val showEmptyLive: LiveData<Boolean> = listLive.map {
        it.isEmpty()
    }

    fun getList() {
        try {
            val users = repo.getUsers()
            listLive.postValue(users)
        } catch (e: Exception) {
            errorLive.postValue(e)
        }

    }
}