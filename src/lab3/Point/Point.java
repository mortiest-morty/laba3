package lab3.Point;

// ============== класс Точка - задание 6.2 ==============
public class Point {
    private int x;
    private int y;

   public Point() {
      this.x = 0;
      this.y = 0;
   }

   public Point(int x, int y){
      this.x = x;
      this.y = y;
   }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

   int getX() {
      return this.x;
   }

   int getY() {
      return this.y;
   }

   public boolean equals(Point point){
        if(point.x == this.x || point.y == this.y) return true;
        else return false;
   }

   public String toString() {
      String result = "{" + this.x + ";" + this.y + "}";
      return result;
   }

   void show() {
      System.out.println(this.toString());
   }
}

