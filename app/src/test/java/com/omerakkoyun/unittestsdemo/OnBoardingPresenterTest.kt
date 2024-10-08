package com.omerakkoyun.unittestsdemo

import com.omerakkoyun.unittestsdemo.test3.IOnBoardingView
import com.omerakkoyun.unittestsdemo.test3.IPreferenceRepository
import com.omerakkoyun.unittestsdemo.test3.OnBoardingPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class OnBoardingPresenterTest {

    private lateinit var presenter: OnBoardingPresenter
    private lateinit var view: IOnBoardingView
    private lateinit var repo: IPreferenceRepository

    @Before // testlerden önce ayağa kalkacak nesneler
    fun setup(){
        view = Mockito.mock(IOnBoardingView::class.java)
        repo = Mockito.mock(IPreferenceRepository::class.java)
        presenter = OnBoardingPresenter(repo,view)
    }


    @Test
    fun test_Show_OnBoarding_If_Is_BoardingSeen_False(){
        // onBoarding görüntülenmediyse, gösterilmesi ve ardından kaydedilmesi gerekir. 2 case
        Mockito.`when`(repo.isBoardingSeen())
            .thenReturn(false) // test için false dön

        presenter.check() // kontrolü başlat

        verify(view).showOnBoarding() // gösterildi mi?
        verify(repo).setOnBoardingSeen() // kaydedildi mi?

    }

    @Test
    fun test_Show_Login_If_Is_BoardingSeen_True(){
        Mockito.`when`(repo.isBoardingSeen())
            .thenReturn(true) // test için true dön

        presenter.check()

        verify(view).showLogin() // login'e gitti mi?
        verify(view, times(1)).showLogin() // login'e gitti mi sadece 1 kere?
        verify(view, never()).showOnBoarding() // onBoarding hiç çalışmamalı, çalışmadı değil mi?
        verify(repo).setToken("123") // setToken "123" çalıştı mı?

        argumentCaptor<String>{
            verify(repo).setToken(capture()) // hem settoken çağırıldı mı? hemde gönderilen tip doğru mu?
            println("value = $firstValue")
            assert(firstValue == "123") // gönderilen değer kontrolü?
        }
    }

}