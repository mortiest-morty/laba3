package lab3.weapon;

// ============== класс Оружие - задание 8.2 ==============
public abstract class Weapon8 {
    protected int ammo;

    public Weapon8(int ammo) { 
        if (ammo < 0) {
            throw new RuntimeException("Кол-во патронов не может быть отрицательным");
        }
        this.ammo = ammo;
    }

    public abstract void shoot(); 

    public int ammo() {
        return ammo;
    }

    public void discharge(){
        this.ammo = 0;
    }

    public boolean getAmmo() {
        if (ammo == 0) {
            return false;
        }
        ammo--;
        return true; 
    }

    public int load(int ammo) {
        if (ammo < 0) {
            throw new RuntimeException("Кол-во патронов не может быть отрицательным");
        }
        this.ammo = ammo;
        return ammo;
    }
}