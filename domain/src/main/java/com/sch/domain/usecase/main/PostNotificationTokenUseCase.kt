package com.sch.domain.usecase.main

import com.sch.domain.NetworkResult
import com.sch.domain.model.Participation
import com.sch.domain.model.Reservation
import com.sch.domain.model.ResultSearchKeyword
import com.sch.domain.model.Token
import com.sch.domain.repository.KakaoRepository
import com.sch.domain.repository.MainRepository
import retrofit2.Response
import javax.inject.Inject

class PostNotificationTokenUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(deviceId: String, token: String): NetworkResult<Unit> =
        repository.postNotificationToken(deviceId = deviceId, token = token)
}