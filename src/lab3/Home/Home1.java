package lab3.Home;

// ============== класс Дом - задание 1.1 ==============
public class Home1 {
   private final int floor;

   public Home1() { 
      this.floor = 0;
   }

   public Home1(int x){
      if (x < 0 || x == 0) {
            throw new IllegalArgumentException("Кол-во этажей не может быть отрицательным или 0!");
      }
      this.floor = x;
   }

   public int getFloors() { 
      return this.floor;
   }

   public String toString() {
      String result = "Дом с " + this.floor;
      if (this.floor % 10 == 1 && this.floor % 100 != 11) {
         result = result + " этажом ";
      } else {
         result = result + " этажами ";
      }
      return result;
   }

   public void show() { 
      System.out.println(this.toString());
   }
}