package com.example.architecturalsample.questions

import com.example.architecturalsample.Constants
import com.example.architecturalsample.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchQuestionDetailsUseCase {

    sealed class Result {
        class Success(val question: String) : Result()
        object Failure : Result()
    }

    // init retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
   private val stackoverflowApi = retrofit.create(StackoverflowApi::class.java)

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