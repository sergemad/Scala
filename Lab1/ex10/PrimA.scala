import scala.collection.mutable.Map
import scala.io.Source.fromFile
import scala.io.StdIn.readLine

def Exercice11(map:Map[String,String], name:String,
surname:String, date_of_birth:String): Unit ={
    map("name") = name
    map("surname") = surname
    map("date of birth") = date_of_birth
    println(map)
}

def Exercice12(filename:String): Unit ={
    var result_line:Array[String] = Array()
    var res:List[String] = List()
    for (line <- fromFile(filename).getLines()){
        result_line = line.split(':')
        res = result_line(1) :: res
    }
    var map : Map[String, String] = Map("name" -> "", "surname" -> "", "date of birth" -> "")
    Exercice11(map, res(2),res(1),res(0))
}


def Exercice14(map:Map[String,String]): Unit ={
    println("Enter the time activite as <yyyymmdd hhmmss> : ")
    var time_ac = readLine()
    println("Enter the name  of your activite : ")
    var name = readLine()

    map += (time_ac -> name)
    println(map)
}

def Exercice15(filename:String): Map[String,String] ={
    var result_line:Array[String] = Array()
    var map : Map[String, String] = Map()
    for (line <- fromFile(filename).getLines()){
        result_line = line.split(':')
        map += (result_line(0) -> result_line(1))
    }
    
    return map
}


def Exercice16(map:Map[String,String], date:String): Unit ={
    var temp:Map[String,String] = Map()

    map.foreach((e: (String, String)) => 
    println(String.toString(e._1.split(" ")))
    )
    //if (date == e._1.split(" ")[0]){
    //    temp = (e._1 -> e._2)
    //})
    println(temp)
}

var map : Map[String, String] = Exercice15("/Users/sergekeita/Desktop/Learning/M1_Efrei/Scala/Lab1/ex10/userActivities.txt")
Exercice16(map,"20220404")
//Exercice15("/Users/sergekeita/Desktop/Learning/M1_Efrei/Scala/Lab1/ex10/userActivities.txt")
//Exercice14(map)
//Exercice12("/Users/sergekeita/Desktop/Learning/M1_Efrei/Scala/Lab1/ex10/userInfo.txt")
//Exercice11(map,"Keita","Serge","07/01/2000")
