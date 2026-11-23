package lab3.weapon;

// ============== класс Пистолет - задание 4.1 ==============
public class Gun4 extends Weapon {

    public Gun4(int ammo) {
        super(ammo); 
    }

    public void shoot() {
        if (this.ammo() > 0) {
            this.getAmmo(); 
            System.out.println("\nБах!");
        } else {
            System.out.println("\nКлац!");
        }
    }

    public void show() {
        this.shoot(); 
    }
}