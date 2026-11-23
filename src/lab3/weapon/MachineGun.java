package lab3.weapon;

// ============== класс Автомат - задание 3.4 ==============
public class MachineGun extends Gun{
   private int RateOfFire;

   public MachineGun() {
      super(30, 0); 
      this.RateOfFire = 30;
   }
   
   public MachineGun(int x) {
      super(x, 0); 
      this.RateOfFire = x / 2;
   }

   public MachineGun(int x, int z) {
      super(x, 0);  
      this.RateOfFire = z;
   }

   public void setRateOfFire(int x){
      this.RateOfFire = x;
   }

   public int getRateOfFire(){
      return this.RateOfFire;
   }

   public void shotPerNSecond(int N){
      int ratesOfFires = N *this.getRateOfFire();
      while(ratesOfFires > 0){
         this.show();
         --ratesOfFires;
      }
   }

}