package com.omerakkoyun.unittestsdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.omerakkoyun.unittestsdemo.test5.User
import com.omerakkoyun.unittestsdemo.test5.UserListRepo
import com.omerakkoyun.unittestsdemo.test5.UserListRepoImpl
import com.omerakkoyun.unittestsdemo.test5.UserListViewModel
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class UserListTest {

    @get:Rule
    val rule = InstantTaskExecutorRule() // LiveData'yı test etmek için gerekli //  testImplementation 'androidx.arch.core:core-testing:2.1.0' ekledik
    //InstantTaskExecutorRule gibi kurallar ile LiveData güncellemelerinin anında tetiklenmesini sağlarız.
    //LiveData ve benzeri bileşenler, ana iş parçacığında çalışmak üzere tasarlanmıştır.
    //Ancak, birim testleri genellikle ana iş parçacığını simüle etmez, bu nedenle testleriniz beklenmedik davranışlar sergileyebilir.


    val repo = Mockito.mock(UserListRepoImpl::class.java)
    val viewModel = UserListViewModel(repo)

    @Test
    fun test_UserList_Only_1_User() {
        Mockito.`when`(repo.getUsers())
            .thenReturn(listOf(User("user1")))

        viewModel.getList()

        assert(viewModel.listLive.value?.size == 1)
    }

    @Test
    fun test_EmptyList() {
        Mockito.`when`(repo.getUsers())
            .thenReturn(listOf())

        viewModel.getList()
        assert(viewModel.listLive.value?.size == 0)
        viewModel.showEmptyLive.observeForever {} // degeri oluşsun diye observe ediyoruz.
        assert(viewModel.showEmptyLive.value == true) // true/false degerini alabilmek için önce observe etmek gerekiyor, yukarıda test için observe ettik.
    }

    @Test
    fun test_Exception() {
        Mockito.`when`(repo.getUsers())
            .then{
                throw Exception("FAIL")
            }

        viewModel.getList()

        assert(viewModel.listLive.value == null)
        assert(viewModel.errorLive.value != null)
    }

}