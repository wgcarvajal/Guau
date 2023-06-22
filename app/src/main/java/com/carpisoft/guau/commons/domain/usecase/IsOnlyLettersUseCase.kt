package com.carpisoft.guau.commons.domain.usecase

import java.util.regex.Pattern
import javax.inject.Inject

class IsOnlyLettersUseCase @Inject constructor() {
    operator fun invoke(value:String) = value.matches(Regex("[a-zA-z\\s]*"))
}