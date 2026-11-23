package lab3.List;

// ============== класс Неизменяемый Список Значений - задание 2.1 ==============
public class IntList {
    private final int[] values;

    public IntList(int... nums) { 
        this.values = new int[nums.length];
        System.arraycopy(nums, 0, this.values, 0, nums.length);
    }

    public IntList(IntList other) { 
        if (other == null) {
            throw new IllegalArgumentException("Список не может быть пустым");
        }
        this.values = new int[other.values.length];
        System.arraycopy(other.values, 0, this.values, 0, other.values.length);
    }

    public int size() { 
        return this.values.length;
    }

    public boolean isEmpty() { 
        return this.values.length == 0;
    }

    public int get(int pos) { 
        checkIndex(pos);
        return this.values[pos];
    }

    public void set(int pos, int value) { 
        checkIndex(pos);
        this.values[pos] = value;
    }

    private void checkIndex(int pos) { 
        if (pos < 0 || pos >= this.values.length) {
            throw new IllegalArgumentException(
                "Позиция " + pos + " вне диапазона 0 " + (this.values.length - 1));
        }
    }

    public int[] toArray() { 
        int[] copy = new int[this.values.length];
        System.arraycopy(this.values, 0, copy, 0, this.values.length);
        return copy;
    }

    public String toString() { 
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.values.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(this.values[i]);
        }
        return sb.append("]").toString();
    }
}