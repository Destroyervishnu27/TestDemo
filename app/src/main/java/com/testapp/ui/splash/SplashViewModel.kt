package com.testapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.testapp.data.repo.repo_base.Resource
import com.testapp.data.repo.repo_base.SPLASH_SCREEN_DELAY_MS
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    enum class Action { NAVIGATE_TO_HOME_SCREEN }

    private var disposable: Disposable? = null

    private val result = MutableLiveData<Resource<Any, Action>>()

    fun getResult(): LiveData<Resource<Any, Action>> {
        return result
    }

    /**
     * show home page will return false if
     * ... time==null
     * ...time==0L
     * ...time>SPLASH_SCREEN_DELAY_MS
     * ...time!=SPLASH_SCREEN_DELAY_MS
     */
    fun showHomePage(time: Long?): Boolean {
        if (time == null || time == 0L || time > SPLASH_SCREEN_DELAY_MS || time != SPLASH_SCREEN_DELAY_MS) return false
        disposable = Observable.timer(time, TimeUnit.MILLISECONDS)
            .map {
                result.postValue(Resource(null, Action.NAVIGATE_TO_HOME_SCREEN, ""))
            }.observeOn(AndroidSchedulers.mainThread()).subscribe()
        return true
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

}