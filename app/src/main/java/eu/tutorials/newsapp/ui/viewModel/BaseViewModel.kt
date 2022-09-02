package eu.tutorials.newsapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorials.newsapp.MainApp
import eu.tutorials.newsapp.data.ArticleCategory
import eu.tutorials.newsapp.data.getArticleCategory
import eu.tutorials.newsapp.model.NewsResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.Exception


class BaseViewModel(application: Application) : AndroidViewModel(application)  {

    private val newsRepository = getApplication<MainApp>().newsRepository

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean>
        get() = _isError


    private val errorHandler = CoroutineExceptionHandler { _, error ->
        if(error is Exception) {
            _isError.value = true
        }
    }

    private val _newsResponse = MutableStateFlow(NewsResponse())
    val newsResponse: StateFlow<NewsResponse>
        get() = _newsResponse


    private val _getArticleByCategory = MutableStateFlow(NewsResponse())
    val getArticleByCategory:StateFlow<NewsResponse>
         get() = _getArticleByCategory

    private val _selectedCategory : MutableStateFlow<ArticleCategory?> = MutableStateFlow(null)
    val selectedCategory: StateFlow<ArticleCategory?>
        get() = _selectedCategory

    val sourceName = MutableStateFlow("engadget")
    private val _getArticleBySource = MutableStateFlow(NewsResponse())
    val getArticleBySource: StateFlow<NewsResponse>
        get() = _getArticleBySource

    val query = MutableStateFlow("")
    private val _searchedNewsResponse = MutableStateFlow(NewsResponse())
    val searchedNewsResponse: StateFlow<NewsResponse>
        get() = _searchedNewsResponse


    fun getTopArticles() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            _newsResponse.value = newsRepository.getArticles()
            _isLoading.value = false
        }
    }

    fun getArticlesByCategory(category: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            _getArticleByCategory.value = newsRepository.getArticlesByCategory(category)
            _isLoading.value = false
        }
    }

    fun getArticlesBySource() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            _getArticleBySource.value = newsRepository.getArticlesBySource(sourceName.value)
            _isLoading.value = false
        }
    }

    fun getSearchedArticles(query: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            _searchedNewsResponse.value = newsRepository.getSearchedArticles(query)
            _isLoading.value = false
        }
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getArticleCategory(category)
        _selectedCategory.value = newCategory
    }



}