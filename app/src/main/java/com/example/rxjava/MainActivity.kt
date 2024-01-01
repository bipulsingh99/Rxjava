package com.example.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TimeFormatException
import android.widget.TextView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.create
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.TimeoutCancellationException

class MainActivity : AppCompatActivity() {
    private lateinit var textView:TextView
    val TAG = "mainactivity"
    lateinit var disposable : Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.text1)
        val observable = create( object: ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(10)
                emitter.onNext(20)
                emitter.onNext(30)
                emitter.onNext(40)
//                emitter.onError(Throwable("sdf"))
                emitter.onComplete()
            }
        }).map { it -> it*5
        }.filter{
            it > 100
        }.subscribe({
            Log.d(TAG,"$it")
        },{
            Log.d(TAG,"$it")
        },{
            Log.d(TAG,"oncomplete")
        })
        }

    }
