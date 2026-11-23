package lab3.weapon;

// ============== класс Пистолет - задание 1.5 ==============
public class Gun {
   protected int maxCartridges;
   protected int cartridges;

   public Gun() { 
      this.maxCartridges = 0;
      this.cartridges = 0;
   }

   public Gun(int x, int y) { 
      this.maxCartridges = x;
      this.cartridges = y;
   }

   public void setMaxCartridges(int x){ 
      this.maxCartridges = x;
   }

   public void setCartridges(int x) { 
      if (x < 0){
         throw new IllegalArgumentException("Кол-во патронов не может быть отрицательным!");
      }
      this.cartridges = x;
   }

   public int getCartridges() { 
      return this.cartridges;
   }

   public int getMaxCartridges() { 
      return this.maxCartridges;
   }

   public String toString() {
      if (this.cartridges == 0) {
         return "\nКлац!";
      } 
      else {
         --this.cartridges;
         return "\nБах!";
      }
   }

   public void show() { 
      System.out.println(this.toString());
   }
}