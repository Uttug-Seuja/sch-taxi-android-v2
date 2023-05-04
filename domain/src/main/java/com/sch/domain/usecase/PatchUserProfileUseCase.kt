package com.sch.domain.usecase

import com.sch.domain.NetworkResult
import com.sch.domain.model.UserProfile
import com.sch.domain.repository.MainRepository
import javax.inject.Inject

class PatchUserProfileUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(profilePath: String): NetworkResult<UserProfile> =
        repository.patchUserProfile(
            profilePath = profilePath
        )
}