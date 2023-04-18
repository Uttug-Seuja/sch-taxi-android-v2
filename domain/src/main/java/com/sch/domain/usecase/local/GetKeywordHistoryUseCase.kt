package com.sch.domain.usecase.local

import com.sch.domain.model.ResultSearchKeyword
import com.sch.domain.repository.KakaoRepository
import retrofit2.Response
import javax.inject.Inject

class GetKeywordHistoryUseCase @Inject constructor(
    private val repository: KakaoRepository
) {
    suspend operator fun invoke(keyword: String, page: Int): Response<ResultSearchKeyword> =
        repository.getResultKeyword(keyword = keyword, page = page)
}