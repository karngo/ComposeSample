package com.example.composesample.analytics

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class LogWorker(
    private val appContext: Context, workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    private val FILE_NAME = "insure_me_logs.txt"

    override suspend fun doWork(): Result {
        writeToFile()
        return Result.success()
    }

    private suspend fun writeToFile() {
        val logs = inputData.getStringArray(LOG_KEY) ?: return

        val file = File(appContext.filesDir, FILE_NAME)

        withContext(Dispatchers.IO) {
            try {
                if (!file.exists()) {
                    file.createNewFile()
                }

                val fileWriter = FileWriter(file, true)
                val bufferedWriter = BufferedWriter(fileWriter)
                logs.forEach { bufferedWriter.append(it).append("\n") }
                bufferedWriter.close()
            } catch (e: Exception) {
                Log.d("worker_log", "writeToFile: $e")
            }
        }
    }

    companion object {
        const val LOG_KEY = "LOG_KEY"
    }
}