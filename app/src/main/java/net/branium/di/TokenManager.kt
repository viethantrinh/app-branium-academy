package net.branium.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import net.branium.util.Constants
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    // Method to store the token
    fun storeToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(Constants.TOKEN, token)
        editor.apply()  // Saves the token asynchronously
    }

    // Method to retrieve the token
    fun getToken(): String? {
        return sharedPreferences.getString(Constants.TOKEN, null)
    }

    // Method to clear the token (e.g., on logout)
    fun clearToken() {
        val editor = sharedPreferences.edit()
        editor.remove(Constants.TOKEN)
        editor.apply()  // Removes the token asynchronously
    }

    // Method to check if a token is present
    fun hasToken(): Boolean {
        return sharedPreferences.contains(Constants.TOKEN)
    }
}
