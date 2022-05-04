package coursedate

class CourseDateInterval(date1: CourseDate, date2: CourseDate){
  var startDate:CourseDate = date1
  var endDate:CourseDate =date2

  def generateDateIntervalString(): String ={
    return startDate.generateDateString() + " - " + endDate.generateDateString()
  }

  def overlapsWith(interval:CourseDateInterval): Boolean ={
    return startDate.between(interval.startDate,interval.endDate) || endDate.between(interval.startDate,interval.endDate)
  }
}