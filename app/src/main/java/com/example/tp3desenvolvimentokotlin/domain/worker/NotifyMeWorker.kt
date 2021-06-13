package com.example.tp3desenvolvimentokotlin.domain.worker

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.tp3desenvolvimentokotlin.domain.entity.Event

class NotifyMeWorker(appContext: Context, workerParams: WorkerParameters, events: LiveData<List<Event>>): Worker(appContext, workerParams) {

    override fun doWork(): Result {
        println("BAZINGA")
        return Result.success();
    }
}