package lab3.weapon;

// ============== класс Автомат - для задания 5.8 ==============
public class MachineGun4 extends Gun4 {
   private int rateOfFire;

   public MachineGun4() {
      super(30); 
      this.rateOfFire = 30;
   }
   
   public MachineGun4(int x) {
      super(x); 
      this.rateOfFire = x / 2;
   }

   public MachineGun4(int x, int z) {
      super(x);  
      this.rateOfFire = z;
   }

   public void setRateOfFire(int x){
      this.rateOfFire = x;
   }

   public int getRateOfFire(){
      return this.rateOfFire;
   }

   public void shotPerNSecond(int N){
      int ratesOfFires = N * this.getRateOfFire();
      while(ratesOfFires > 0){
         this.show();
         --ratesOfFires;
      }
   }
}