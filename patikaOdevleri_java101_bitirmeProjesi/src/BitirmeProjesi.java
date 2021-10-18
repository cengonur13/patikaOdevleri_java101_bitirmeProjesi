import java.util.Random;
import java.util.Scanner;

public class BitirmeProjesi {
    /**
     * @author Onur TAŞ, 2021...
     */
    public static void main(String[] args) {
        int row,col;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz!\n");
        System.out.println("Oyununuz Hazırlanıyor...");

        System.out.print("Satır sayısını giriniz   : ");
        row = scanner.nextInt();
        System.out.print("Sütun sayısını giriniz   : ");
        col = scanner.nextInt();

        MineSweeper mineSweeper = new MineSweeper(row,col);
        mineSweeper.run();


    }
}
