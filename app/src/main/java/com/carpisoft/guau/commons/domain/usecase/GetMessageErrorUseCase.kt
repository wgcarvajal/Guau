package com.carpisoft.guau.commons.domain.usecase

import android.content.Context
import androidx.compose.ui.text.toLowerCase
import com.carpisoft.guau.R
import com.carpisoft.guau.commons.ui.model.ErrorUi
import com.carpisoft.guau.commons.utils.constants.Errors
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetMessageErrorUseCase @Inject constructor(@ApplicationContext private val context: Context) {

    operator fun invoke(errorUi: ErrorUi,field:String? = null): String {
        if (errorUi.error != null) {
            return when (errorUi.error) {
                Errors.BAD_CREDENTIALS -> {
                    context.getString(R.string.bad_credentials)
                }
                Errors.DUPLICATE_FIELD -> {
                    if(field!=null) {
                        context.getString(R.string.field_duplicate_with_field, field.lowercase())
                    }
                    else{
                        context.getString(R.string.field_duplicate)
                    }
                }
                Errors.TOKEN_INVALID -> {
                    context.getString(R.string.authentication_error)
                }
                else -> {
                    context.getString(R.string.server_error)
                }
            }
        }
        return context.getString(R.string.server_error)
    }
}