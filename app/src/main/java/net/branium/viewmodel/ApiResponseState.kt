package net.branium.viewmodel

sealed class ApiResponseState() {
    object Succeeded : ApiResponseState()
    object Failed : ApiResponseState()
    object Processing : ApiResponseState()
    var message: String = ""
}