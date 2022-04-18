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

def Exercice12(filename:String): Map[String,String] ={
    var result_line:Array[String] = Array()
    var res:List[String] = List()
    for (line <- fromFile(filename).getLines()){
        result_line = line.split(':')
        res = result_line(1) :: res
    }
    var map : Map[String, String] = Map("name" -> res(2), "surname" -> res(1), "date of birth" -> res(0))
    //Exercice11(map, res(2),res(1),res(0))
    return map
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


def Exercice16(map:Map[String,String], date:String): Map[String,String] ={
    var temp:Map[String,String] = Map()

    for ((e: (String, String)) <- map){
        var date_to_compare:Array[String] = e._1.split(" ")
        if (date == date_to_compare(0)){
            temp += (e._1 -> e._2)
        }
    }
    return temp
}

def Exercice17(userinfo: Map[String,String],useract: Map[String,String],date: String): List[String] = {
    var res:List[String] = List()
    var username:String = ""
    var date_of_birth:String = ""
    for ((e: (String, String)) <- userinfo){
        if (e._1 == "name"){
            username += e._2
        }
        else if (e._1 == "surname"){
            username += " " + e._2
        }
        else {
            date_of_birth = e._2
        }
    }
    if (date_of_birth == date){
        res = s"Happy birthday $username !" :: res
    }
    else{
        res = s"Welcome $username !" :: res
    }
    
    var act_scheduled:Map[String,String] = Exercice16(useract,date)
    for ((e: (String, String)) <- act_scheduled){
        var v1:String = e._1
        var v2:String = e._2
        res = s"You have $v2 at $v1" :: res
    }

    return res
}

def Exercice18(l:List[String]): Unit = {
    var str:String = ""
    for (i <- until l.size){
        str = str + i + "\n" 
    }
    var file = new File("PrimA.txt")
    var bw = new BufferedWriter(new FileWriter(file))
    bw.write(str)
    bw.close()
} 


var map : Map[String, String] = Exercice15("/Users/sergekeita/Desktop/Learning/M1_Efrei/Scala/Lab1/ex10/userActivities.txt")
var map1 : Map[String,String] = Exercice12("/Users/sergekeita/Desktop/Learning/M1_Efrei/Scala/Lab1/ex10/userInfo.txt")
var res : List[String] = Exercice17(map1,map,"20220404")
println(res)
//Exercice16(map,"20220404")
//Exercice15("/Users/sergekeita/Desktop/Learning/M1_Efrei/Scala/Lab1/ex10/userActivities.txt")
//Exercice14(map)
//Exercice12("/Users/sergekeita/Desktop/Learning/M1_Efrei/Scala/Lab1/ex10/userInfo.txt")
//Exercice11(map,"Keita","Serge","07/01/2000")
