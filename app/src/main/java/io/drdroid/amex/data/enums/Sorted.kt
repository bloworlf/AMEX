package io.drdroid.amex.data.enums

enum class Sorted(val sort: Int) {
    AZ(0),
    ZA(1);

    fun value(): Int {
        return this.sort
    }

    companion object{
//        fun fromValue(sort:Int){
//            return Sorted.values().
//        }
    }
}