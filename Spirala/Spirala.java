public class Spirala {
    private static int x;
    private static int y;
    private int dir_x = 1;
    private int dir_y = 0;

    public void choose_start_point(int size) {

        if (size % 2 == 0) {
            this.y = size / 2 - 1;
            this.x = size / 2;
        } else {
            this.y = (size / 2);
            this.x = size / 2;

        }
    }

    public void choose_direction() {
        if (this.dir_x == 0 & this.dir_y == -1) { //jesli idziemy w lewo to potem idziemy w dol
            this.dir_x = 1;
            this.dir_y = 0;
        } else if (this.dir_x == 1 & this.dir_y == 0) { //jesli idziemy w dol to potem idziemy w prawo
            this.dir_x = 0;
            this.dir_y = 1;
        } else if (this.dir_x == 0 & this.dir_y == 1) { //jesli idziemy w prawo to potem idziemy w gore
            this.dir_x = -1;
            this.dir_y = 0;
        } else if (this.dir_x == -1 & this.dir_y == 0) { //jesli idziemy w gore to potem idziemy w lewo
            this.dir_x = 0;
            this.dir_y = -1;
        }

    }

    static boolean isPrime(int x) {
        if (x <= 1)
            return false;
        for (int i = 2; i < x; i++)
            if (x % i == 0)
                return false;

        return true;
    }


    public int[][] spiral(int size) throws  IllegalArgumentException {
        if (size < 2 || size > 200){
            throw new IllegalArgumentException("parametr spoza zakresu 2...200");
        }
        int spiral[][] = new int[size][size];
        int n = 1;
        int change_direction = 1;
        int step_size = 0;
        choose_start_point(size);

        for (int i = 0; i < size * size; i++) {
            spiral[this.x][this.y] = i + 1;
            if (i + 1 == change_direction) {
                choose_direction(); // wybieramy change_direction w ktorym zmienimy direction
                if (n % 2 == 1) {
                    step_size = step_size + 1; // co 2 zmiany kierunku zmieniamy krok
                }
                n = n + 1;

                change_direction = change_direction + step_size;
            }
            this.x = this.x + this.dir_x;
            this.y = this.y + this.dir_y;

        }
       /* for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(spiral[i][j] + "\t");    //TEST DLA SPRAWDZENIA POPRAWNOSCI SPIRALI
            }
            System.out.println();
        }*/
        return spiral;

    }

    public int[][] prime_spiral(int arr[][]) {
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!isPrime(arr[i][j])) {
                    arr[i][j] = 0;
                }
            }
        }
        return arr;
    }

    public void draw(int arr[][]) {
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (arr[i][j] != 0) {
                    System.out.print("*" + "\t");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Spirala nowa_spirala = new Spirala();
        String napis = args[0];
        int n = Integer.valueOf(napis);
        int tablica[][] = nowa_spirala.spiral(n);
        int tablica2[][] = nowa_spirala.prime_spiral(tablica);
        nowa_spirala.draw(tablica2);

    }
}






