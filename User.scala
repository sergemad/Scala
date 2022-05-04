package prima


class User(user_name:String,user_surname:String,user_Date_of_birth:String){
  var name: String = user_name
  var surname: String = user_surname
  var Date_birth: String = user_Date_of_birth
}

object User{
  def apply(user_name:String,user_surname:String,user_Date_of_birth:String): User ={
    var user:User = new User(user_name,user_surname, user_Date_of_birth)
    return user
  }
}