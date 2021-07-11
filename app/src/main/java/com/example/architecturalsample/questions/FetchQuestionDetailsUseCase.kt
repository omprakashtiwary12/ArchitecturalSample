package com.example.architecturalsample.questions

import com.example.architecturalsample.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchQuestionDetailsUseCase(private val stackoverflowApi: StackoverflowApi) {

    sealed class Result {
        class Success(val question: String) : Result()
        object Failure : Result()
    }

    // init retrofit



   suspend fun fetchLatestQuestionDetail(questionId: String): Result {
       return withContext(Dispatchers.IO){
           try {
               val response = stackoverflowApi.questionDetails(questionId)
               if (response.isSuccessful && response.body() != null){
                   return@withContext Result.Success(response.body()!!.question.body)
               }else {
                   return@withContext  Result.Failure
               }
           }catch (t: Throwable) {
               if (t !is CancellationException){
                   return@withContext Result.Failure
               }else{
                   throw t
               }
           }
       }
   }

}