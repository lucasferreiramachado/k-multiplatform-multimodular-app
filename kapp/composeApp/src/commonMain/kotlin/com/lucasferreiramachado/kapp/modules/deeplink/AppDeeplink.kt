package com.lucasferreiramachado.kapp.deeplink
public object AppDeeplink {

    var schemes: List<String> = listOf(
         "myapp:/", "myapp://",
    )

    fun defaultScheme(): String = schemes.first()
}