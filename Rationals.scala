package rationals

import scala.language.implicitConversions

class Rationals(n_number:Int, d_number:Int) {
  private val n:Int = n_number / gcd(n_number, d_number)
  private val d:Int = d_number / gcd(n_number, d_number)
  val num:Int = n
  val denum:Int = d
  if (d == 0) throw new ArithmeticException("Cannot divide a number by zero")

  def this(value:Int) = {
    this(1,value)
  }

  def gcd(num:Int,denum:Int): Int ={
    if (denum == 0){
      return  num
    }
    else {
      gcd(denum, num % denum)
    }
  }

  def +(value : Rationals)= new Rationals(
    (n*value.d)+(d*value.n),
    d*value.d)

  def -(value : Rationals)= new Rationals(
    (n*value.d)-(d*value.n),
    d*value.d)

  def *(value : Rationals)= new Rationals(
    n*value.n,
    d*value.d)

  def /(value : Rationals)= new Rationals(
    n*value.d,
    d*value.n)

  def ~() = new Rationals(d,n)

  def -() = new Rationals(-1*n,d)

  def +(value: Int) = new Rationals(n+(value*d),d)

  def -(value: Int) = new Rationals(n-(value*d),d)

  def *(value: Int) = new Rationals(n*value,d)

  def /(value: Int) = new Rationals(n,d*value)

}

object Rationals{
  def apply(n_number:Int, d_number:Int): Rationals ={
    return new Rationals(n_number,d_number)
  }
  def apply(d_number:Int): Rationals ={
    return new Rationals(d_number)
  }
  implicit def int2Rationals(value:Int) :Rationals = Rationals(value,1)

  implicit var int2Rat:Int => Rationals = value => Rationals(value,1)

}
