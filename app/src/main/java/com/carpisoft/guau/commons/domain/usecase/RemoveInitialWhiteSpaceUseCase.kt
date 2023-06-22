package com.carpisoft.guau.commons.domain.usecase

import javax.inject.Inject

class RemoveInitialWhiteSpaceUseCase @Inject constructor() {
    operator fun invoke(value: String): String {
        return value.replaceFirstChar {
            if(it.equals(" "))
            {
                ""
            }
            else{
                it.toString()
            }
        }
    }
}