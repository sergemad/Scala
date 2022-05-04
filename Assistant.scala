package prima

import scala.collection.mutable.Map
import prima.Task
import prima.User
import coursedate.CourseDate
import coursedate.CourseDateInterval
import scala.io.Source.fromFile
import scala.io.StdIn.readLine
import java.io._

class Assistant(){
  var users:Map[String, User] = Map()
  var userTasks:Map[String, List[Task]] = Map()

  def userExist(usernames:String): Boolean= {
    for ((names,user) <- users){
      if (names == usernames){
        return true
      }
    }
    return false
  }

  def addUser(usernames:String = "",name:String,surname:String,date_birth:String): Unit ={
    var user:User = User.apply(name, surname, date_birth)
    var task: List[Task] = List()
    if(usernames == ""){
      users += (s"$name $surname" -> user)
      userTasks += (s"$name $surname" -> task)
    }
    else {
      users += (usernames -> user)
      userTasks += (usernames -> task)
    }
  }

  def removeUser(usernames:String): Unit ={
    users.-(usernames)
    userTasks.-(usernames)
  }

  def AddTask(username:String,task:Task): Unit ={
    if (userExist(username)){
      var listTasks:List[Task] = userTasks(username)
      var importantOverlaps: Boolean = false
      for (oldTask <- listTasks){
        if (task.interval.overlapsWith(oldTask.interval)){
          if (task.important || oldTask.important){
            importantOverlaps = true
          }
        }
      }
      if (importantOverlaps){
        println("Important task overlaps. Can not add new task")
      }
      else {
        listTasks = task :: listTasks
        userTasks += (username -> listTasks)
      }
    }
    else {
      println("username don't exist")
    }
  }

  def removeTask(username:String,task: Task): Unit ={
    if(userExist(username)){
      var tasks:List[Task] = List()
      for (i <- 0 until userTasks(username).size) {
        if(task.descriptionTask() != userTasks(username)(i).descriptionTask()){
          tasks = userTasks(username)(i) :: tasks
        }
      }
      userTasks += (username -> tasks)
    }
    else{
      println("username don't exist")
    }
  }

  def exportFile(user:String): Unit ={
    var str:String = ""
    for ((username, map) <- users){
      if(username==user){
        str = str + s"username: $username / name: ${map.name} / surname: ${map.surname} / Date of birth: ${map.Date_birth} \n"
      }
    }
    var file = new File("userinfo.txt")
    var bw = new BufferedWriter(new FileWriter(file))
    bw.write(str)
    bw.close()

    str = ""
    for ((username,map) <- userTasks){
      if(username==user){
        for (task <- map){
          str = str + s"username: $username / name: ${task.name} / Importance : ${if(task.important) "high" else "normal"} / Scheduled between : ${task.interval.generateDateIntervalString()} \n"
        }
      }
    }

    var file2 = new File("userTaskInfo.txt")
    var bw2 = new BufferedWriter(new FileWriter(file2))
    bw2.write(str)
    bw2.close()
  }

  def importFromFile(): Unit ={
    var result_line:Array[String] = Array()
    var res:List[String] = List()
    println("Enter the name of the user file: ")
    var filename:String = readLine()

    for (line <- fromFile(filename).getLines()){
      result_line = line.split('/')
      var usernames: String = result_line(0).split(":")(1)
      var name: String = result_line(1).split(":")(1)
      var surname: String = result_line(2).split(":")(1)
      var date_birth: String = result_line(3).split(":")(1)
      this.addUser(usernames,name,surname,date_birth)
    }

    println("Enter the name of the user tasks file: ")
    filename = readLine()

    for (line <- fromFile(filename).getLines()){
      result_line = line.split('/')
      var usernames: String = result_line(0).split(":")(1)
      var name: String = result_line(1).split(":")(1)
      var important: String = result_line(2).split(":")(1)
      var date = result_line(3).split(": ")(1)
      var date1: String = date.split(" - ")(1)
      var date2: String = date.split(" - ")(2)
      var min1:String = date1.split(":")(1)
      var sec1:String = date1.split(":")(2)
      var year1:String = date1.split(":")(0).split("-")(0)
      var month1:String = date1.split(":")(0).split("-")(1)
      var day1:String = date1.split(":")(0).split("-")(2).split(" ")(0)
      var hour1:String = date1.split(":")(0).split("-")(2).split(" ")(1)

      var min2:String = date2.split(":")(1)
      var sec2:String = date2.split(":")(2)
      var year2:String = date2.split(":")(0).split("-")(0)
      var month2:String = date2.split(":")(0).split("-")(1)
      var day2:String = date2.split(":")(0).split("-")(2).split(" ")(0)
      var hour2:String = date2.split(":")(0).split("-")(2).split(" ")(1)

      var date_course_start:CourseDate = new CourseDate(y = year1.toInt, m = month1.toInt, d = day1.toInt, h = hour1.toInt, min = min1.toInt, s = sec1.toInt)
      var date_course_end:CourseDate = new CourseDate(y = year2.toInt, m = month2.toInt, d = day2.toInt, h = hour2.toInt, min = min2.toInt, s = sec2.toInt)
      var Interval:CourseDateInterval = new CourseDateInterval(date_course_start,date_course_end)

      var imp:Boolean = false
      if (important == " high "){
         imp = true
      }
      var task:Task = new Task(name,Interval,imp)

      this.AddTask(usernames,task=task)
    }
  }
}