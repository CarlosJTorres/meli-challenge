package ar.com.pensa.meli.technicalchallenge.utils

import java.text.NumberFormat
import java.util.*

class AppUtils {

    companion object {
        fun formatNumber(number: Float): String {
            val localeNumberFormat: NumberFormat = NumberFormat.getInstance(Locale.getDefault())

            return localeNumberFormat.format(number)
        }
    }

}