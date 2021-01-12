package com.example.shoestoreinventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestoreinventory.data.Shoe
import com.example.shoestoreinventory.data.User

/**
 * SharedViewModel is at the Activity level and is shared between the Fragments.
 */
class SharedViewModel : ViewModel() {

    // List of Shoes
    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    // Shoe entered by the user
    private var _anotherShoe: MutableLiveData<Shoe>
    val anotherShoe: LiveData<Shoe>
        get() = _anotherShoe

    // List of Users
    private val _users = MutableLiveData<MutableList<User>>()
    val users: LiveData<MutableList<User>>
        get() = _users

    // Login data entered by the user
    private var _anotherUser: MutableLiveData<User>
    val anotherUser: LiveData<User>
        get() = _anotherUser

    // Boolean events
    private val _eventLogin = MutableLiveData<Boolean>()
    val eventLogin: LiveData<Boolean>
        get() = _eventLogin

    private val _eventSignup = MutableLiveData<Boolean>()
    val eventSignup: LiveData<Boolean>
        get() = _eventSignup

    private val _eventWelcomeNext = MutableLiveData<Boolean>()
    val eventWelcomeNext: LiveData<Boolean>
        get() = _eventWelcomeNext

    private val _eventInstructionNext = MutableLiveData<Boolean>()
    val eventInstructionNext: LiveData<Boolean>
        get() = _eventInstructionNext

    private val _eventListPlus = MutableLiveData<Boolean>()
    val eventListPlus: LiveData<Boolean>
        get() = _eventListPlus

    private val _eventDetailSave = MutableLiveData<Boolean>(false)
    val eventDetailSave: LiveData<Boolean>
        get() = _eventDetailSave

    private val _eventDetailCancel = MutableLiveData<Boolean>(false)
    val eventDetailCancel: LiveData<Boolean>
        get() = _eventDetailCancel


    init {
        // Pre-populate _shoes with some dummy Shoes
        _shoes.value = mutableListOf<Shoe>(
                Shoe("Air zoom pegasus 37", "Nike", 8.5F, "Running shoes"),
                Shoe("Fresh Foam 1080v10", "New Balance", 9.0F, "Running shoes"),
                Shoe("Evo Mafate", "Hoka One One", 10.0F, "Trail Running shoes")
        )
        _anotherShoe = MutableLiveData(Shoe())

        // Pre-populate _users with some dummy Users
        _users.value = mutableListOf<User>(
                User("john.smith@gmail.com", "2021"),
                User("mary.lee@yahoo.com", "2022")
        )
        _anotherUser = MutableLiveData(User())

        _eventLogin.value = false
        _eventSignup.value = false
        _eventWelcomeNext.value = false
        _eventInstructionNext.value = false
        _eventListPlus.value = false
        _eventDetailSave.value = false
        _eventDetailCancel.value = false
    }

    /*
     * Methods for Login
     */
    fun onLogin() {
        _eventLogin.value = true
    }

    fun onLoginComplete() {
        _eventLogin.value = false
    }

    // Checks if the user with the given email and password is in _users list.
    fun isAKnownUser(email: String, password: String): Boolean {
        _anotherUser.value?.email = email
        _anotherUser.value?.password = password
        return _users.value?.contains(User(email, password)) == true
    }

    fun addUser(email: String, password: String) {
        _anotherUser.value?.email = email
        _anotherUser.value?.password = password
        _users.value?.add(User(email, password))
    }

    fun onSignup() {
        _eventSignup.value = true
    }

    fun onSignupComplete() {
        _eventSignup.value = false
    }

    /*
     *   Methods for Welcome
     */
    fun onWelcomeNext() {
        _eventWelcomeNext.value = true
    }

    fun onWelcomeNextComplete() {
        _eventWelcomeNext.value = false
    }

    /*
     * Methods for Instruction
     */
    fun onInstructionNext() {
        _eventInstructionNext.value = true
    }

    fun onInstructionNextComplete() {
        _eventInstructionNext.value = false
    }

    /*
     * Methods for List
     */
    fun onListPLus() {
        _eventListPlus.value = true
    }

    fun onListPLusComplete() {
        _eventListPlus.value = false
    }

    /*
     * Methods for Detail
     */
    fun onDetailSave() {
        _eventDetailSave.value = true
    }

    fun addShoe(name: String, company: String, size: Float, description: String) {
        _anotherShoe.value?.name = name
        _anotherShoe.value?.company = company
        _anotherShoe.value?.size = size
        _anotherShoe.value?.description = description
        _shoes.value?.add(Shoe(name, company, size, description))
    }

    fun onDetailSaveComplete() {
        _eventDetailSave.value = false
    }

    fun onDetailCancel() {
        _eventDetailCancel.value = true
    }

    fun onDetailCancelComplete() {
        _eventDetailCancel.value = false
    }
}