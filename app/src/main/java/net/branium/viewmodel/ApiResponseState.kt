package net.branium.viewmodel

sealed class ApiResponseState() {
    object Succeeded : ApiResponseState()
    object Failed : ApiResponseState()
    object Processing : ApiResponseState()
    var message: String = ""
}

sealed class ResponseState(val message: String) {
    class Succeeded(message: String = "Operation succeeded") : ResponseState(message)
    class Failed(message: String = "Operation failed") : ResponseState(message)
    class Processing(message: String = "Processing...") : ResponseState(message)
}
