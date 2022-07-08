package uk.co.ayaspace.mage.utils

interface CustomPreferenceManager {
    fun setUserName(name: String)
    fun getUserName(): String
    fun setBirthday(birthday: String)
    fun getBirthday(): String
    fun setPronouns1(pronouns1: String)
    fun getPronouns1(): String
    fun setPronouns2(pronouns2: String)
    fun getPronouns2(): String
    fun setUserExists(bool: Boolean)
    fun getUserExists(): Boolean
    fun clearPrefs()
}