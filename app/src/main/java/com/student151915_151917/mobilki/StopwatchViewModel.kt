import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StopwatchViewModel : ViewModel() {

    private val _elapsedTime = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long> = _elapsedTime

    private var startTime = 0L
    private var isRunning = false
    private var job: Job? = null

    init {
        _elapsedTime.value = 0L
    }

    fun start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - (_elapsedTime.value ?: 0L)
            isRunning = true
            job = viewModelScope.launch {
                while (isRunning) {
                    _elapsedTime.value = System.currentTimeMillis() - startTime
                    delay(1000)
                }
            }
        }
    }

    fun stop() {
        isRunning = false
        job?.cancel()
    }

    fun reset() {
        stop()
        _elapsedTime.value = 0L
    }

    override fun onCleared() {
        super.onCleared()
        stop()
    }
}