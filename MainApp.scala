package main

import coursedate.CourseDate
import  coursedate.CourseDateInterval
import  prima.Task
import prima.Assistant
import scala.io.StdIn.readLine

object MainApp {
  def main(args: Array[String]): Unit = {
    var exit:Boolean = false
    var assist:Assistant = new Assistant()

    var username:String = ""

    println("No user in the application, create one or import a user file")
    println("1. Create a user \n2. Import a user file\n\n")

    println("Select between 1 and 2 : " )
    var slt:String = readLine()

    if (slt == "2"){
      assist.importFromFile()
      for ((i,j) <- assist.users){
        username = i
      }
    }
    else {
      println("\n\nfill information bellow : ")
      println("\nusername : ")
      username = readLine()
      println("\nname : ")
      var new_name:String = readLine()
      println("\nsurname : ")
      var new_surname:String = readLine()
      println("\nDate of birth <YYYYMMDD> : ")
      var new_dateBirth:String = readLine()
      assist.addUser(usernames = username,new_name,new_surname,new_dateBirth)

    }

    while (!exit){
      println("\n\n1. Change user")
      println("2. Add user task")
      println("3. Remove user task")
      println("4. Print User report for a date")
      println("5. Add new user from the command-line")
      println("6. Delete user")
      println("7. Import new user")
      println("8. Export user")
      println("9. Exit")
      println("\nChose between 1 to 9 ")

      var select : String = readLine()

      if(select == "1"){
        println("\n\nEnter the username: ")
        var new_user: String = readLine()
        if(assist.userExist(new_user)){
          username = new_user
        }
        else {
          println("\n\nThis user don't exist")
        }
      }
      else if(select == "2"){
        println("\n\nTo create a task you have to fill information bellow : ")
        println("\nTask name : ")
        var taskname:String = readLine()
        println("Importance of the task (high) or (normal) : ")
        var taskimp:String = readLine()
        var imp:Boolean = false
        if(taskimp == "high"){
          imp = true
        }

        println("Date of start (YYYY-MM-DD): ")
        var taskdatestart:String = readLine()
        println("Hour of start (hh:mn-sc): ")
        var taskhourstart:String = readLine()
        println("Date of end (YYYY-MM-DD): ")
        var taskdateend:String = readLine()
        println("Hour of end (hh:mn-sc): ")
        var taskhourend:String = readLine()

        var date1:CourseDate = new CourseDate(taskdatestart.split("-")(0).toInt,
          taskdatestart.split("-")(1).toInt,taskdatestart.split("-")(2).toInt,
          taskhourstart.split(":")(0).toInt,taskhourstart.split(":")(1).toInt,
          taskhourstart.split(":")(3).toInt)
        var date2:CourseDate = new CourseDate(taskdateend.split("-")(0).toInt,
          taskdateend.split("-")(1).toInt,taskdateend.split("-")(2).toInt,
          taskhourend.split(":")(0).toInt,taskhourend.split(":")(1).toInt,
          taskhourend.split(":")(3).toInt)

        assist.AddTask(username,new Task(taskname,new CourseDateInterval(date1,date2),imp))
      }
      else if(select == "3"){
        println(s"\n\nChoose a task to remove between 0 and ${assist.userTasks(username).size-1}: ")
        var taskNbr:String = readLine()
        assist.removeTask(username,assist.userTasks(username)(taskNbr.toInt))
      }
      else if(select == "4"){
        var tasks:List[Task] = assist.userTasks(username)
        println(s"\n\n$username's tasks")
        if (tasks.size == 0){
          println("No Task")
        }
        else{
          for(i <- 0 until tasks.size){
            println(s"\nTask n°$i")
            tasks(i).generateTaskDescription()
          }
        }
      }
      else if(select == "5"){
        println("\n\nfill information bellow : ")
        println("\nusername : ")
        var new_username:String = readLine()
        println("\nname : ")
        var new_name:String = readLine()
        println("\nsurname : ")
        var new_surname:String = readLine()
        println("\nDate of birth <YYYYMMDD> : ")
        var new_dateBirth:String = readLine()
        assist.addUser(usernames = new_username,new_name,new_surname,new_dateBirth)

      }
      else if(select == "6"){
        assist.removeUser(username)
      }
      else if(select == "7"){
        assist.importFromFile()
      }
      else if(select == "8"){
        assist.exportFile(username)
      }
      else if(select == "9"){
        exit = false
      }
      else{
        println("\n\nerror with the number tou selected")
      }

    }
  }
}
