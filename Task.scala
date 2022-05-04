package prima

import coursedate.CourseDateInterval

class Task(task_name:String, task_interval:CourseDateInterval, importance:Boolean ){
  var name: String = task_name
  var interval:CourseDateInterval = task_interval
  var important:Boolean = importance

  def generateTaskDescription(): Unit = {
    println(s"$name \n" +
      s"Scheduled between : ${interval.generateDateIntervalString()} \n" +
      s"Importance : ${if(important) "high" else "normal"}\n" )
  }

  def descriptionTask(): String = {
    return s"$name \n" +
      s"Scheduled between : ${interval.generateDateIntervalString()} \n" +
      s"Importance : ${if(important) "high" else "normal"}\n"
  }

}