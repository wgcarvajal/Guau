package com.carpisoft.guau.commons.domain.usecase

import javax.inject.Inject

class IsMaxStringSizeUseCase @Inject constructor() {
    operator fun invoke(value:String,maxSize:Int)= value.length<=maxSize
}