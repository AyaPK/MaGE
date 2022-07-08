package uk.co.ayaspace.mage.utils

import android.content.Context
import android.content.SharedPreferences

open class PreferenceHelper constructor(context: Context) : CustomPreferenceManager {
    private val PREFS_NAME = "SharedPreferenceDemo"
    private var preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun setUserName(name: String) {
        preferences[NAME] = name
    }

    override fun getUserName(): String {
        return preferences[NAME] ?: ""
    }

    override fun setBirthday(birthday: String) {
        preferences[BIRTHDAY] = birthday
    }

    override fun getBirthday(): String {
        return preferences[BIRTHDAY] ?: ""
    }

    override fun setPronouns1(pronouns1: String) {
        preferences[PRONOUNS1] = pronouns1
    }

    override fun getPronouns1(): String {
        return preferences[PRONOUNS1] ?: ""
    }

    override fun setPronouns2(pronouns2: String) {
        preferences[PRONOUNS2] = pronouns2
    }

    override fun getPronouns2(): String {
        return preferences[PRONOUNS2] ?: ""
    }

    override fun setUserExists(bool: Boolean) {
        preferences[USER_EXISTS] = bool
    }

    override fun getUserExists(): Boolean {
        return preferences[USER_EXISTS] ?: false
    }

    override fun clearPrefs() {
        preferences.edit().clear().apply()
    }

    companion object {
        const val NAME = "name"
        const val BIRTHDAY = "birthday"
        const val PRONOUNS1 = "pronouns1"
        const val PRONOUNS2 = "pronouns2"
        const val USER_EXISTS = "userExists"
    }
}
private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = this.edit()
    operation(editor)
    editor.apply()
}
private operator fun SharedPreferences.set(key: String, value: Any?) {
    when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

private inline operator fun <reified T : Any> SharedPreferences.get(
    key: String,
    defaultValue: T? = null
): T? {
    return when (T::class) {
        String::class -> getString(key, defaultValue as? String) as T?
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}