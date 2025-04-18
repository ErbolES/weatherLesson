package com.example.lessons11.day_four

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Person{
    var name:String? = null
    var age:Int? = null
}

class Example {

    val DATE_TIME_FORMAT  = "dd.MMM.yy HH.mm"

    fun main(args:Array<String>){
        print(greetingFun)
        print(4,5) {
           " greetingFun"
        }

        val date = Date(123)

        date.format()

        val person:Person = Person().also {
            println(it.age)
            println(it.name)
        }

        val personTwo = person.apply {
            name ="sfasdasd"
            age = 2312
        }

        person?.let { person ->
            println(person)
        }

        println() {  person.age}
        println() {  person.name}

        with(person) {
            println(age)
            println(name)
        }


    }

  /*  val greetingFun = fun():String{
        return "Hello"
    }*/

    val greetingFun = {"Hell0"}

    fun print(a:Int , b :Int ,block: ()-> String){
        println(block)
    }

    fun print(block: (txt:String) -> String){
        println(block)
    }

    val sum = {a:Int, b:Int -> a+b}

    fun sumFunc(a:Int, b:Int):Int{
        return a + b
    }

    fun bind(note:String){
        val title = note
        print(title)
    }

   fun Date.format():String {
     return  SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(this)
   }

    fun printAge(person: Person) = run {
        val person :Person = Person()

    }

    val txt = StringBuilder().apply {
        append("sad")
        append("asdasd")
    }.also {
        print { it }
    }

}