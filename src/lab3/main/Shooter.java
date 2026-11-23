package lab3.main;

import lab3.weapon.Weapon;

// ============== класс Стрелок - задание 5.8 ==============
public class Shooter {
    private String name;
    private Weapon weapon;

    public Shooter(String name) {
        this.name = name;
        this.weapon = null;
    }

    public Shooter(String name, Weapon weapon) {
        this.name = name;
        this.weapon = weapon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void shoot() {
        System.out.print(this.name + ": ");
        if (this.weapon != null) {
            this.weapon.shoot(); 
        } else {
            System.out.println("не могу участвовать в перестрелке");
        }
    }
}