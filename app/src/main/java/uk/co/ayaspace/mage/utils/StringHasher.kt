package uk.co.ayaspace.mage.utils

import uk.co.ayaspace.mage.BuildConfig
import java.security.spec.KeySpec
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class StringHasher() {

    private val ALGORITHM = "PBKDF2WithHmacSHA512"
    private val ITERATIONS = 120000
    private val KEY_LENGTH = 256
    private val HASH_SALT = BuildConfig.HASH_SALT
    fun generateHash(password: String): String {
        val combinedSalt = HASH_SALT.toByteArray()
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), combinedSalt, ITERATIONS, KEY_LENGTH)
        val key: SecretKey = factory.generateSecret(spec)
        val hash: ByteArray = key.encoded
        return toHex(hash)
    }

    fun toHex(bytes: ByteArray): String {
        val hexArray = "0123456789ABCDEF".toCharArray()

        val hexChars = CharArray(bytes.size * 2)
        for (j in bytes.indices) {
            val v = bytes[j].toInt() and 0xFF

            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }
}