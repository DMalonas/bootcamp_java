package org.practice.model

import com.google.gson.annotations.SerializedName

data class Sentence(
    @SerializedName("value") var value: String,
    @SerializedName("original_text") var originalText: String?
)

