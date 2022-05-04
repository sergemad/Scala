package coursedate

class CourseDate(y:Int,m:Int,d:Int,h:Int,min:Int,s:Int){
  var year:Int = y
  var month:Int = m
  var day:Int = d
  var hour:Int = h
  var minute:Int = min
  var second:Int = s

  def generateDateString(): String ={
    var Smonth:String =""
    var Sday:String =""
    var Shour:String =""
    var Sminute:String =""
    var Ssecond:String =""
    if(month>10){
     Smonth= s"$month"
    }
    else {
      Smonth = s"0$month"
    }
    if(day>10){
      Sday = s"$day"
    }
    else {
      Sday = s"0$day"
    }
    if(hour>10){
      Shour = s"$hour"
    }
    else {
      Shour = s"0$hour"
    }
    if(minute>10){
      Sminute = s"$minute"
    }
    else {
      Sminute = s"0$minute"
    }
    if(second>10){
      Ssecond = s"$second"
    }
    else {
      Ssecond = s"0$second"
    }
    return s"$year-$Smonth-$Sday $Shour:$Sminute:$Ssecond"
  }
  def sameDateAs(date:CourseDate): Boolean ={
    return date.generateDateString().split(" ")(0) == this.generateDateString().split(" ")(0)
  }

  def between(date1:CourseDate, date2:CourseDate): Boolean ={
    if (this.sameDateAs(date1) || this.sameDateAs(date2)){
      if ((hour > date1.hour) && (hour < date2.hour)){
        return true
      }
      else if ((hour == date1.hour) || (hour == date2.hour)){
        if((minute >= date1.minute) || (minute <= date2.minute)) {
          return true
        }
      }
    }
    return false
  }
}