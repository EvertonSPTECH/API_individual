package sptech.api_individual.response

data class Response <T> (
    val data: T,
    val message:String? = null
)