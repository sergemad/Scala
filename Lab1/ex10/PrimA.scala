import scala.collection.mutable.Map
import scala.io.Source.fromFile
import scala.io.StdIn.readLine
import java.io._

def Exercice11(map:Map[String,String], name:String,
surname:String, date_of_birth:String): Map[String, String] ={
    map("name") = name
    map("surname") = surname
    map("date of birth") = date_of_birth
    return map
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


def Exercice14(map:Map[String,String]): Map[String, String] ={
    println("Enter the time activite as <yyyymmdd hhmmss> : ")
    var time_ac = readLine()
    println("Enter the name  of your activite : ")
    var name = readLine()

    map += (time_ac -> name)
    return map
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
    for (i <- l.reverse){
        str = str + i + "\n" 
    }
    println("Enter the file name tou want (exemple: Prima.txt):")
    var filename : String = readLine()
    var file = new File(s"$filename")
    var bw = new BufferedWriter(new FileWriter(file))
    bw.write(str)
    bw.close()
} 

def Exercice19(): Unit ={
    var exit:Boolean = false
    var userI: Map[String, String] = Map()
    var userA: Map[String, String] = Map()

    while (exit == false){
        println("1. Create a user from a given file")
        println("2. update user from the command line")
        println("3. Add user activities from a given file")
        println("4. Add a user activity from the command line")
        println("5. Greet and show activies for a given day")
        println("6. write a greeting and the activities for a given day in a file")
        println("7. Exit")
        println("\nChose between 1 to 7 ")

        var select : String = readLine()
        if (select == "7"){
            exit = true
        }
        else if (select == "1"){
            println("Enter the name of the file (userInfo.txt):")
            var userInfo : String = readLine() 
            userI = Exercice12(s"/Users/sergekeita/Desktop/Learning/M1_Efrei/Scala/Lab1/ex10/$userInfo")
            println(userI)
        }
        else if (select == "2"){
            println("Enter the name:")
            var name:String = readLine()
            println("Enter the surname:")
            var surname:String = readLine()
            println("Enter the date of birth (exemple: 20220404):")
            var date_birth:String = readLine()
            userI = Exercice11(userI,name,surname,date_birth)
            println(userI)
        }
        else if (select == "3"){
            println("Enter the name of the file (userActivities.txt):")
            var userActivities : String = readLine() 
            userA = Exercice15(s"/Users/sergekeita/Desktop/Learning/M1_Efrei/Scala/Lab1/ex10/$userActivities")
            println(userA)
        }
        else if (select == "4"){
            userA = Exercice14(userA)
            println(userA)
        }
        else if (select == "5"){
            println("Enter the corresponding day (exemple: 20220404):")
            var day : String = readLine() 
            var greet : List[String] = Exercice17(userI,userA,day)
            println(greet)
        }
        else if (select == "6"){
            println("Enter the corresponding day (exemple: 20220404):")
            var day : String = readLine() 
            var greet : List[String] = Exercice17(userI,userA,day)
            Exercice18(greet)
        }
        else {
            println("ERROR: bad entry")
        }
    }
}


Exercice19()

