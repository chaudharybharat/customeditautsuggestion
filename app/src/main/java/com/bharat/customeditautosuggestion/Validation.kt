package com.bharat.customeditautosuggestion

class Validation {

    companion object{
        fun isRequiredField(strText: String?): Boolean {
            return strText != null && !strText.trim { it <= ' ' }.isEmpty()
        }

        fun isEmailValid(email: String?): Boolean {
            return if (!isRequiredField(
                    email.toString()
                )
            ) {
                false
            } else android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }




}