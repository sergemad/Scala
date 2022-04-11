import scala.io.StdIn.readLine //Input command
import scala.io.Source.fromFile
import java.io._

object HelloWorl {

    /* Difference between var and val : 
     val makes a variable immutable — like final in Java —
     and var makes a variable mutable.*/

    def greetUser(name : String): Unit = {
        println(s"Hello, $name")
    }

    def greetUserFun(name : String) : String = {
        var greet:String = s"Hello, $name !" 
        return greet
    }

    def greetUserFun2(name : String) : String = {
        var greet:String = s"Welcome, $name !" 
        return greet
    }

    def greetWithCustomGreeting(name : String, 
    greetGenerator : (String) => String ) : Unit = {
        println(greetGenerator(name))
    }

    def Exercice2(): String = {
        println("Enter your name")
        var inputName = readLine()
        return inputName
    }

    def Exercice6(): Unit = {
        var guests:List[String] = List("Alice","Bob","Sam")
        for ( i <- 0 until guests.size){
            greetWithCustomGreeting(guests(i),greetUserFun)
        }
        for (greet <- guests){
            greetWithCustomGreeting(greet,greetUserFun2)
        }
    }

    def Exercice7(): Unit = {
        var guestsFromFile:List[String] = List()
        val filename = "/Users/sergekeita/Desktop/Learning/M1_Efrei/Scala/Lab1/ex00/guests.txt"

        for (line <- fromFile(filename).getLines()){
            println(line)
            guestsFromFile = line :: guestsFromFile
        }

        var indexOfGuestToGreet:Int = 0

        while( indexOfGuestToGreet < guestsFromFile.size){
            if (indexOfGuestToGreet % 2 == 0){
                greetWithCustomGreeting(guestsFromFile(indexOfGuestToGreet),greetUserFun)
            }else {
                greetWithCustomGreeting(guestsFromFile(indexOfGuestToGreet),greetUserFun2)
            }
            indexOfGuestToGreet = indexOfGuestToGreet + 1
        }

    }

    def generateGreetingList(l:List[String],
    greet1: (String) => String, 
    greet2: (String) => String): Unit = {

        //var listname:List[String] = List()
        var listname = l.reverse
        var generatedGreetings:String = ""
        for (i <- 0 until listname.size){
            if(i % 2 == 0){
                generatedGreetings = generatedGreetings + greet1(listname(i)) + "\n" 
            }else{
                generatedGreetings = generatedGreetings + greet2(listname(i)) + "\n" 
            }
        }
        
        var file = new File("greetedGuests.txt")
        var bw = new BufferedWriter(new FileWriter(file))
        bw.write(generatedGreetings)
        bw.close()
    }

    def Exercice8(): Unit = {
        var guestsFromFile:List[String] = List()
        val filename = "/Users/sergekeita/Desktop/Learning/M1_Efrei/Scala/Lab1/ex00/guests.txt"

        for (line <- fromFile(filename).getLines()){
            guestsFromFile = line :: guestsFromFile
        }
        
        generateGreetingList(guestsFromFile,greetUserFun,greetUserFun2)
    }

    def Exercice9(name:String): Unit = {
        println(s"Hello, $name !")
    }

    def main(args: Array[String]): Unit = {

        Exercice9(args(0))
    }
}
