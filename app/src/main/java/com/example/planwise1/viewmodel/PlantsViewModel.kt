package com.example.planwise1.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planwise1.R
import com.example.planwise1.api.DataItem
import com.example.planwise1.api.RetrofitInstance
import kotlinx.coroutines.launch

class PlantsViewModel(private val context: Context) : ViewModel() {
    private val _speciesList = MutableLiveData<List<DataItem>>()
    val speciesList: LiveData<List<DataItem>> = _speciesList

    private val apiKey = context.getString(R.string.api_key)

    fun getSpeciesList() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getSpeciesList(apiKey)
                _speciesList.value = (response.data ?: emptyList<DataItem>()) as List<DataItem>?
            } catch (_: Exception) {

            }
        }
    }
}