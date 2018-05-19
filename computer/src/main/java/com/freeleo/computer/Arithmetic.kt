package com.freeleo.computer

class Arithmetic {
    companion object Factory{

        fun subtract(a: Long , b: Long) : Long{
            return a - b
        }

        fun multiply(a: Long , b: Long) : Long{
            return a * b
        }

        fun divide(a: Long , b: Long) : Long{
            return a / b
        }
    }
}

fun Arithmetic.Factory.add(a: Long , b: Long) : Long{
    return a + b
}