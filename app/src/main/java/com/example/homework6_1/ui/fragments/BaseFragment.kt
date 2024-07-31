package com.example.homework6_1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.homework6_1.utils.Resource

abstract class BaseFragment<binding : ViewBinding>(private val bindingInflater: (LayoutInflater) -> binding) : Fragment() {

    private var _binding: binding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    protected fun <T> handleResource(
        resource: Resource<T>,
        onSuccess: (data: T) -> Unit,
        onError: (message: String) -> Unit
    ){
        when(resource){
            is Resource.Loading -> {}
            is Resource.Success -> onSuccess(resource.data)
            is Resource.Error -> resource.message.let(onError)
        }
    }
}