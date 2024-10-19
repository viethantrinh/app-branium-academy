package net.branium.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import net.branium.util.Constants
import javax.inject.Inject

class CartQuantityManager @Inject constructor(val context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("cart_quantities", Context.MODE_PRIVATE)

    // Method to store the token
    fun storeCartQuantity(quantity: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(Constants.CART_QUANTITY, quantity)
        editor.apply()  // Saves the token asynchronously
    }

    // Method to retrieve the token
    fun getCartQuantity(): Int {
        return sharedPreferences.getInt(Constants.CART_QUANTITY, 0)
    }

    // Method to clear the token (e.g., on logout)
    fun resetCartQuantity() {
        val editor = sharedPreferences.edit()
        editor.remove(Constants.CART_QUANTITY)
        editor.apply()  // Removes the token asynchronously
    }

    // Method to check if a token is present
    fun hasCartQuantity(): Boolean {
        return sharedPreferences.contains(Constants.CART_QUANTITY)
    }
}
