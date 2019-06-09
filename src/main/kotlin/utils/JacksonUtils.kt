package utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

object JacksonUtils {

    val mapper : ObjectMapper = ObjectMapper().registerModule(JavaTimeModule())!!

}