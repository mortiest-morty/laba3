package lab3.weapon;

// ============== класс Оружие - задание 4.1 ==============
public abstract class Weapon {
    private int ammo;

    Weapon(int ammo) {
        if (ammo < 0) {
            throw new RuntimeException("Кол-во патронов не может быть отрицательным");
        }
        this.ammo = ammo;
    }

    public abstract void shoot();

    public int ammo() {
        return ammo;
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
        int tmp = ammo;
        this.ammo = ammo;
        return tmp;
    }
}
