package other.mvisetup.src.utils

import org.jetbrains.kotlin.lombok.utils.capitalize

fun String.separateCamelCase(): String {
    val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()

    return camelRegex.replace(this) {
        " ${it.value}"
    }.lowercase().capitalize()
}
