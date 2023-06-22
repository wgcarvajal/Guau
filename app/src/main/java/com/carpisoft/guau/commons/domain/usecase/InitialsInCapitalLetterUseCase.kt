package com.carpisoft.guau.commons.domain.usecase

import javax.inject.Inject

class InitialsInCapitalLetterUseCase @Inject constructor(){
    operator fun invoke(value:String):String
    {
        var result = ""
        if(value.isNotEmpty())
        {
            val split = value.split(" ")
            for(word in split.withIndex()) {
                if (word.index != 0) {
                    result += " "
                }
                result += "${word.value.replaceFirstChar { it.uppercase() }}"
            }
        }
        return result
    }
}