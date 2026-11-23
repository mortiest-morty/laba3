package lab3.main; 

import java.util.ArrayList;
import java.util.Scanner;
import lab3.Home.Home1;
import lab3.List.IntList;
import lab3.Point.Point;
import lab3.weapon.Gun;
import lab3.weapon.Gun4;
import lab3.weapon.MachineGun;
import lab3.weapon.MachineGun4;
import lab3.weapon.Weapon;

// ============== main ==============
public class Main {
   private final Scanner scanner;

   Main() {
      this.scanner = new Scanner(System.in, "Cp866");
   }

   int readInt2() {
      while(true) {
         String input = this.scanner.nextLine().strip();
         try {
            return Integer.parseInt(input);
         } catch (NumberFormatException var3) {
            System.out.println("Ошибка! Введите целое число!");
            System.out.print("Введите цифру: ");
         }
      }
   }

   int readInt() {
      while(true) {
         System.out.print("Введите цифру: ");
         if (this.scanner.hasNextInt()) {
            int value = this.scanner.nextInt();
            this.scanner.nextLine();
            return value;
         }
         System.out.println("Ошибка! Это не число.");
         this.scanner.nextLine();
      }
   }

   int readIntInRange(int min, int max) {
      while(true) {
         int x = this.readInt();
         if (x >= min && x <= max) {
            return x;
         }
         System.out.println("Ошибка! Требуется число от " + min + " до " + max);
      }
   }

   boolean isValidName(String str) {
      if (str != null && !str.isBlank()) {
         if (!str.matches("^[\\p{L}\\s]+$")) {
            System.out.println("Ошибка! Имя или название не может содержать цифры, символы!");
            return false;
         } else {
            return true;
         }
      } else {
         System.out.println("Ошибка! Имя не может быть пустым!");
         return false;
      }
   }

   int[] readIntArrayFromLine() {
    System.out.print("Введите числа через запятую (например: 5, -2, 7): ");
    String line = this.scanner.nextLine().strip();

    if (line.isEmpty()) {
        return new int[0];
    }

    String[] parts = line.split("\\s*,\\s*");
    int[] result = new int[parts.length];

    for (int i = 0; i < parts.length; i++) {
        try {
            result[i] = Integer.parseInt(parts[i]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка! '" + parts[i] + "' — не целое число. Попробуйте снова.");
            return readIntArrayFromLine();
        }
    }
    return result;
}

   void menu1() {
      System.out.print("Введите количество этажей: ");
      int floor = readInt2();

      try {
         Home1 home = new Home1(floor);
      } 
      catch (IllegalArgumentException e) {
         while (floor < 0 || floor == 0){
            System.out.println(e.getMessage());
            System.out.print("Введите количество этажей: ");
            floor = readInt2();
         }
      }

      Home1 home = new Home1(floor);
      home.show();
   }

   void menu2(){
      System.out.print("Введите максимальное кол-во патронов для вашего пистолета: ");
      int MaxCartridges = readInt2(); 

      while (MaxCartridges < 0 || MaxCartridges == 0){
         System.out.print("Ошибка! Максимально кол-во патронов не может быть 0!\n Введите еще раз: ");
         MaxCartridges = readInt2();
      }

      Gun gun = new Gun(MaxCartridges, 0);
      int choice = 0;

      while(choice < 7){
         System.out.println("\n^-^ ==== Меню ==== ^-^:\n 1 - зарядить пистолет\n 2 - выстрелить из пистолета \n 3 - узнать кол-во имеющихся патронов\n 4 - узнать разряжен/разряжен пистолет\n 5 - узнать макс. кол-во патронов пистолета\n 6 - разрядить пистолет\n 7 - вернуться в менюшку");
         choice = readIntInRange(1, 7);

         if (choice == 1){
            System.out.print("Введите кол-во патронов, чтобы зарядить пистолет: ");
            int catridges = readInt2();
            try {
               gun.setCartridges(catridges);
            } 
            catch (IllegalArgumentException e) {
               while (catridges < 0 || catridges == 0){
                  System.out.println(e.getMessage());
                  System.out.print("Введите кол-во патронов: ");
                  catridges = readInt2();
               }
            }
            if(catridges > gun.getMaxCartridges()){
               gun.setCartridges(gun.getMaxCartridges());
               System.out.println("Возвращено лишних патронов: " + (catridges - gun.getMaxCartridges()));
            }
            else gun.setCartridges(catridges);
         }

         if (choice == 2){
            System.out.print("Введите кол-во выстрелов: ");
            int catridges = readInt2();
            while (catridges > 0){
               gun.show();
               catridges--;
            }
         }
         if (choice == 3){
            System.out.println(gun.getCartridges() + " - патронов в пистолете");
         }
         if (choice == 4){
            if (gun.getCartridges() > 0){
               System.out.println( "Пистолет заряжен");
            }
            else System.out.println("Пистолет разряжен");
         }
         if (choice == 5){
            System.out.println( gun.getMaxCartridges() + " макс. кол-во патронов в пистолете");
         }
         if (choice == 6){
            System.out.println( "Пистолет разряжен");
            gun.setCartridges(0);
         }
      }
   }

   void menu3() {
    System.out.println("=== Создание списка ===");
    IntList list;
    System.out.println("1 - ввести числа по одному");
    System.out.println("2 - ввести все числа одной строкой через запятую");
    System.out.println("3 - скопировать уже существующий список");
    int mode = readIntInRange(1, 3);

    if (mode == 1) {
        System.out.print("Введите кол-во чисел в списке: ");
        int n = readInt2();
        while (n < 0) {
            System.out.print("Ошибка! Число не может быть отрицательным. Введите кол-во чисел в списке: ");
            n = readInt2();
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Элемент [" + i + "]: ");
            arr[i] = readInt2();
        }
        list = new IntList(arr);
    } else if (mode == 2) {
        list = new IntList(readIntArrayFromLine());
    } else {
        System.out.println("Сначала создадим исходный список, который будем копировать.");
        System.out.print("Сколько чисел в исходном списке? ");
        int n = readInt2();
        while (n < 0) {
            System.out.print("Ошибка! Число не может быть отрицательным. Ещё раз: ");
            n = readInt2();
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Элемент [" + i + "]: ");
            arr[i] = readInt2();
        }
        IntList source = new IntList(arr);
        list = new IntList(source);
        System.out.println("Скопирован список: " + source);
    }

    int choice = 0;
    while (choice != 6) {
        System.out.println("\n^-^ ==== Меню ==== ^-^:");
        System.out.println("1 - получить значение по позиции");
        System.out.println("2 - заменить значение по позиции");
        System.out.println("3 - показать список");
        System.out.println("4 - узнать размер и пустоту");
        System.out.println("5 - получить стандартный массив");
        System.out.println("6 - вернуться");
        choice = readIntInRange(1, 6);

        if (choice == 1) {
            System.out.print("Позиция: ");
            int pos = readInt2();
            try {
                System.out.println("Значение: " + list.get(pos));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        if (choice == 2) {
            System.out.print("Позиция: ");
            int pos = readInt2();
            System.out.print("Новое значение: ");
            int val = readInt2();
            try {
                list.set(pos, val);
                System.out.println("Готово.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        if (choice == 3) System.out.println("Список: " + list);
        if (choice == 4) System.out.println("Размер: " + list.size() + ", пуст: " + list.isEmpty());
        if (choice == 5) {
            int[] arr = list.toArray();
            System.out.print("Массив: [");
            for (int i = 0; i < arr.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(arr[i]);
            }
            System.out.println("]");
        }
      }
   }

   void menu4(){
      System.out.println("^-^ === Меню создания автомата === ^-^:\n 1 - по умолчанию\n 2 - задать макс. числом патронов\n 3 - задать макс. числом патронов и скорострельностью ");
      int choice1 =readIntInRange(1, 3);
      MachineGun gun = new MachineGun();

      if (choice1 == 1){
         MachineGun automatGun = new MachineGun();
         gun = automatGun;
      }
      if (choice1 == 2){
         System.out.print("Введите макс. число патронов: ");
         int x = readInt2();
         while (x < 0 || x == 0){
            System.out.print("Ошибка! Введите макс. число патронов: ");
            x = readInt2();
         }
         MachineGun automatGun = new MachineGun(x);
         gun = automatGun;
      }
      if (choice1 == 3){
         System.out.print("Введите макс. число патронов: ");
         int x = readInt2();
         while (x < 0 || x == 0){
            System.out.print("Ошибка! Введите макс. число патронов: ");
            x = readInt2();
         }
         System.out.print("Введите скорострельность автомата: ");
         int y = readInt2();
         while (y < 0 || y == 0){
            System.out.print("Ошибка! Введите скорострельность автомата: ");
            y = readInt2();
         }
         MachineGun automatGun = new MachineGun(x, y);
         gun = automatGun;
      }

      int choice = 0;
      while(choice < 8){
         System.out.println("\n^-^ ==== Меню ==== ^-^:\n 1 - зарядить автомат\n 2 - стрелять\n 3 - стрелять N секунд \n 4 - узнать кол-во имеющихся патронов\n 5 - узнать разряжен/разряжен автомат\n 6 - узнать макс. кол-во патронов\n 7 - разрядить автомат\n 8 - вернуться в менюшку");
         choice = readIntInRange(1, 8);

         if (choice == 1){
            System.out.print("Введите кол-во патронов, чтобы зарядить пистолет: ");
            int catridges = readInt2();
            try {
               gun.setCartridges(catridges);
            }
            catch (IllegalArgumentException e) {
               while (catridges < 0 || catridges == 0){
                  System.out.println(e.getMessage());
                  System.out.print("Введите кол-во патронов: ");
                  catridges = readInt2();
               }
            }
            if(catridges > gun.getMaxCartridges()){
               gun.setCartridges(gun.getMaxCartridges());
               System.out.println("Возвращено лишних патронов: " + (catridges - gun.getMaxCartridges()));
            }
            else gun.setCartridges(catridges);
         }
         if (choice == 2){
            int rateOfFire = gun.getRateOfFire();
            while (rateOfFire > 0){
               gun.show();
               --rateOfFire;
            }
         }
         if (choice == 3){
            System.out.print("Введите N секунд: ");
            int N = readInt2();
            while (N < 0 || N == 0){
               System.out.print("Ошибка! Введите N секунд: ");
               N = readInt2();
            }
            gun.shotPerNSecond(N);
         }
         if (choice == 4) System.out.println(gun.getCartridges() + " - патронов в пистолете");
         if (choice == 5){
            if (gun.getCartridges() > 0) System.out.println( "Пистолет заряжен");
            else System.out.println("Пистолет разряжен");
         }
         if (choice == 6) System.out.println( gun.getMaxCartridges() + " макс. кол-во патронов в пистолете");
         if (choice == 7){
            System.out.println( "Пистолет разряжен");
            gun.setCartridges(0);
         }
      }
   }

   void menu5() {
      System.out.print("Введите начальное кол-во патронов для вашего пистолета: ");
      int ammo = readInt2(); 

      while (ammo < 0) {
         System.out.print("Ошибка! Патронов не может быть отрицательное кол-во!\nВведите еще раз: ");
         ammo = readInt2();
      }

      Gun4 gun = new Gun4(ammo);
      int choice = 0;

      while(choice != 6) { 
         System.out.println("\n^-^ ==== Меню ==== ^-^:");
         System.out.println("1 - зарядить пистолет");
         System.out.println("2 - выстрелить из пистолета");
         System.out.println("3 - узнать кол-во имеющихся патронов");
         System.out.println("4 - узнать разряжен/заряжен пистолет");
         System.out.println("5 - разрядить пистолет");
         System.out.println("6 - вернуться в менюшку");
         
         choice = readIntInRange(1, 6);

         if (choice == 1){
            System.out.print("Введите кол-во патронов, чтобы зарядить пистолет: ");
            int addAmmo = readInt2();
            gun.load(addAmmo); 
         }
         if (choice == 2){
            System.out.print("Введите кол-во выстрелов: ");
            int shots = readInt2();
            while (shots > 0){
               gun.shoot(); 
               shots--;
            }
         }
         if (choice == 3) System.out.println(gun.ammo() + " - патронов в пистолете");
         if (choice == 4){
            if (gun.ammo() > 0) System.out.println("Пистолет заряжен");
            else System.out.println("Пистолет разряжен");
         }
         if (choice == 5){
            System.out.println("Пистолет разряжен");
            gun.load(0); 
         }
      }
   }

      void menu6() {
      // Используем ArrayList, чтобы можно было добавлять стрелков в любой момент
      ArrayList<Shooter> shooters = new ArrayList<>();
      
      int choice = 0;
      while (choice != 6) { // Изменили на 6, так как добавился пункт "Создать"
         System.out.println("\n^-^ ==== Меню Стрелка ==== ^-^:");
         System.out.println("1 - Создать нового стрелка");
         System.out.println("2 - Выстрелить из всех по очереди");
         System.out.println("3 - Сменить имя стрелку");
         System.out.println("4 - Выдать оружие стрелку");
         System.out.println("5 - Забрать оружие у стрелка");
         System.out.println("6 - Вернуться в главное меню");
         
         choice = readIntInRange(1, 6);

         if (choice == 1) {
            System.out.println("\n=== Создание стрелка ===");
            System.out.print("Введите имя стрелка: ");
            String name = this.scanner.nextLine().strip();
            while (!isValidName(name)) { 
                  System.out.print("Введите имя еще раз: ");
                  name = this.scanner.nextLine().strip();
            }

            System.out.println("Выдать оружие сразу?\n 1 - Да\n 2 - Нет");
            int hasWeapon = readIntInRange(1, 2);

            Weapon weapon = null;
            if (hasWeapon == 1) {
                  System.out.println("Какое оружие?\n 1 - Пистолет\n 2 - Автомат");
                  int weaponType = readIntInRange(1, 2);
                  if (weaponType == 1) {
                     System.out.print("Введите кол-во патронов для пистолета: ");
                     int ammo = readInt2();
                     weapon = new Gun4(ammo);
                  } else {
                     System.out.print("Введите кол-во патронов для автомата: ");
                     int ammo = readInt2();
                     System.out.print("Введите скорострельность: ");
                     int rate = readInt2();
                     weapon = new MachineGun4(ammo, rate);
                  }
            }
            // Добавляем стрелка в список
            shooters.add(new Shooter(name, weapon));
            System.out.println("Стрелок успешно создан!");
         }

         // Проверка, что стрелки вообще есть, чтобы не было ошибок
         if (choice > 1 && choice < 6 && shooters.isEmpty()) {
            System.out.println("Пока что ни одного стрелка нет :<");
            continue; // Пропускаем остальной код и возвращаемся в меню
         }

         if (choice == 2) {
            System.out.println("=== Перестрелка ===");
            for (int i = 0; i < shooters.size(); i++) {
               shooters.get(i).shoot();
            }
         }

         if (choice == 3) {
            System.out.println("Список стрелков:");
            for (int i = 0; i < shooters.size(); i++) {
               System.out.println((i + 1) + " - " + shooters.get(i).getName());
            }
            
            System.out.print("Кому меняем имя? (Введите номер от 1 до " + shooters.size() + "): ");
            int target = readIntInRange(1, shooters.size()) - 1;
            
            System.out.print("Введите новое имя: ");
            String newName = this.scanner.nextLine().strip();
            while (!isValidName(newName)) {
               System.out.print("Введите имя еще раз: ");
               newName = this.scanner.nextLine().strip();
            }
            
            shooters.get(target).setName(newName);
            System.out.println("Имя изменено!");
         }

         if (choice == 4) {
            System.out.println("Список стрелков:");
            for (int i = 0; i < shooters.size(); i++) {
               System.out.println((i + 1) + " - " + shooters.get(i).getName());
            }

            System.out.print("Кому выдаем оружие? (Введите номер от 1 до " + shooters.size() + "): ");
            int target = readIntInRange(1, shooters.size()) - 1;
            
            System.out.print("Какое оружие выдать? (1 - Пистолет, 2 - Автомат): ");
            int weaponType = readIntInRange(1, 2);
            Weapon newWeapon;

            if (weaponType == 1) {
               System.out.print("Введите кол-во патронов для пистолета: ");
               int ammo = readInt2();
               newWeapon = new Gun4(ammo);
            } else {
               System.out.print("Введите кол-во патронов для автомата: ");
               int ammo = readInt2();
               System.out.print("Введите скорострельность: ");
               int rate = readInt2();
               newWeapon = new MachineGun4(ammo, rate);
            }

            shooters.get(target).setWeapon(newWeapon);
            System.out.println("Оружие выдано!");
         }

         if (choice == 5) {
            System.out.println("Список стрелков:");
            for (int i = 0; i < shooters.size(); i++) {
               System.out.println((i + 1) + " - " + shooters.get(i).getName());
            }

            System.out.print("У кого забираем оружие? (Введите номер от 1 до " + shooters.size() + "): ");
            int target = readIntInRange(1, shooters.size()) - 1;
            
            shooters.get(target).setWeapon(null);
            System.out.println("Оружие забрано!");
         }
      }
   }

   void menu7(){
      ArrayList<Point> pointsArr = new ArrayList<>();
      int choice = 0;
      while (choice != 3) {
         System.out.println("\n^-^ ==== Меню ==== ^-^:");
         System.out.println("1 - Сравнить точки");
         System.out.println("2 - Создать точку");
         System.out.println("3 - Вернуться в менюшку");
         choice = readIntInRange(1, 3);

         if(choice == 1){
            int n = 1;

            if(pointsArr.isEmpty()) {
               System.err.println("Пока что нет ни одной точки :<");
            }

            else{
               System.out.println("==== Все точки ====");

               for(Point i : pointsArr){
                  System.out.println(n + " - " + i.toString());
                  n++;
               }

               System.out.print("Введите № первой точки: ");
               int point1 = readInt2();
               while(point1 < 1 || point1 > n-1){
                  System.out.println("Ошибка! Попробуйте снова");
                  System.out.print("Введите № первой точки: ");
                  point1 = readInt2();
               }

               System.out.print("Введите № второй точки: ");
               int point2 = readInt2();
               while(point2 < 1 || point2 > n-1){
                  System.out.println("Ошибка! Попробуйте снова");
                  System.out.print("Введите № второй точки: ");
                  point2 = readInt2();
               }

               Point point11 = pointsArr.get(--point1);
               Point point22 = pointsArr.get(--point2);

               if(point11.equals(point22)) System.out.println("Данные точки одинаковы");
               else System.out.println("Данные точки не одинаковы");
            }
         }

         if (choice == 2) {
            System.out.print("Введите координату x: ");
            int x = readInt2();

            System.out.print("Введите координату y: ");
            int y = readInt2();

            Point point = new Point(x,y);
            pointsArr.add(point);
         }  
      }
   }

   // ============== Задание 7.3 ==============
   void menu8() {
      System.out.println("\n=== Возведение в степень ===");
      System.out.print("Введите число x : ");
      String xStr = this.scanner.nextLine().strip();
      
      System.out.print("Введите степень y : ");
      String yStr = this.scanner.nextLine().strip();

      try {
         int x = Integer.parseInt(xStr);
         int y = Integer.parseInt(yStr);
         double result = Math.pow(x, y);
         System.out.println("Результат: " + x + " ^ " + y + " = " + result);
      } catch (NumberFormatException e) {
         System.out.println("Ошибка! Введены не числа.");
      }
   }

   void menuMain() {
      int choice = 0;
      while (choice != 9) {
         System.out.println("\n================ Главная менюшка ================");
         System.out.println("1 - Дом - задание 1.1");
         System.out.println("2 - Пистолет - задание 1.5");
         System.out.println("3 - Неизменяемый список - задание 2.1");
         System.out.println("4 - Автомат - задание 3.4");
         System.out.println("5 - Оружие - задание 4.1");
         System.out.println("6 - Лучший стрелок - задание 5.8");
         System.out.println("7 - Точки - задание 6.2");
         System.out.println("8 - Возведение в степень - задание 7.3"); 
         System.out.println("9 - Выйти из программы");
         
         choice = readIntInRange(1, 9); 

         if (choice == 1) menu1();
         if (choice == 2) menu2();
         if (choice == 3) menu3();
         if (choice == 4) menu4();
         if (choice == 5) menu5();
         if (choice == 6) menu6();
         if (choice == 7) menu7();
         if (choice == 8) menu8(); 
         if (choice == 9) {
            System.out.println("Прога завершена *-*");
            System.exit(0); 
         }
      }
   }

   public static void main(String args[]){
      new Main().menuMain(); 
   }
}