package com.sch.domain.usecase.main

import com.sch.domain.NetworkResult
import com.sch.domain.repository.MainRepository
import javax.inject.Inject

class DeleteReservationUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(reservationId: Int): NetworkResult<Unit> =
        repository.deleteReservation(
            reservationId = reservationId
        )
}