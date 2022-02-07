package other.mvisetup.src.utils

fun String.separateCamelCase(): String {
    val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()

    return camelRegex.replace(this) {
        " ${it.value}"
    }.toLowerCase().capitalize()
}