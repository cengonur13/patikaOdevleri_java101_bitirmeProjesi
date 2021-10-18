import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row;
    int col;
    int mineNumber;
    int trueSelect;
    int needWin;
    boolean isGameOver = false;
    char[][] screenMap;
    int[][] mineMap;
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    MineSweeper(int row, int col){
        if(row > 0){
            this.row = row;
        }else {
            System.out.println("Hatalı Satır Sayısı... Oyun Kapatıldı!");
            this.row = 0;
        }
        if(col > 0){
            this.col = col;
        }else {
            System.out.println("Hatalı Sütun Sayısı... Oyun Kapatıldı!");
            this.col = 0;
        }
        this.mineMap = new int[row][col];
        this.screenMap = new char[row][col];
        this.mineNumber = (row * col) / 4;
        this.trueSelect = 0;
        this.needWin = (this.row * this.col) - this.mineNumber;
        /*
        System.out.println(needWin);
        System.out.println(trueSelect);
         */
    }

    public void fillScreenMap(){
        for (int i=0; i<screenMap.length; i++){
            for (int j=0; j<screenMap[i].length; j++){
                screenMap[i][j] = '-';
            }
        }
    }
    public void fillMineMap(){
        int number=0;

        while (number != this.mineNumber){
            for(int i=0; i<mineMap.length; i++){
                for (int j=0; j<mineMap[i].length; j++){
                    if(number < this.mineNumber){
                        mineMap[i][j] = (random.nextInt(100) <= 25) ? 1:0;
                        if (mineMap[i][j] == 1){
                            number++;
                        }
                    }else
                        mineMap[i][j] = 0;
                }
            }
            if(number < this.mineNumber){
                number = 0;
            }
        }
    }
    private void printMineMap(){
        for (int i=0; i<mineMap.length; i++){
            for (int j=0; j<mineMap[i].length; j++){
                System.out.print(mineMap[i][j]+" ");
            }
            System.out.println();
        }
    }
    public void printScreenMap(){
        for (int i=0; i<screenMap.length; i++){
            for (int j=0; j<screenMap[i].length; j++){
                System.out.print(screenMap[i][j]+" ");
            }
            System.out.println();
        }
    }
    public boolean isMine(int row, int col){                // seçilen yerde mayın var mı
        return (mineMap[row][col] == 1) ? true:false;
    }
    public int countMine(int row, int col) throws ArrayIndexOutOfBoundsException{                 // komşu mayın sayısı
        int countMineNumber = 0;

        for (int i=row-1; i<= row+1; i++){
            for (int k=col-1; k<= col+1; k++){
                if(i == row && k == col){
                    continue;
                }else if (i < 0 || k < 0){
                    continue;
                }else if (i >= mineMap.length || k >= mineMap[i].length){
                    continue;
                }
                else {
                    if (mineMap[i][k] == 1){
                        countMineNumber++;
                    }
                }
            }
        }
        return countMineNumber;
    }

    public void run(){
        int row,col;
        fillScreenMap();
        fillMineMap();
        /*
        System.out.println("*********");
        printMineMap();
        System.out.println("**********");
        */

        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz!");
        printScreenMap();

        while (!isGameOver){
            System.out.print("Satır Giriniz : ");
            row = scanner.nextInt();
            if(!(row>=0 && row<mineMap.length)){
                System.out.println("Yanlış Satır Bilgisi...");
                continue;
            }
            System.out.print("Sütun Giriniz : ");
            col = scanner.nextInt();
            if(!(col>=0 && col<mineMap[0].length)){
                System.out.println("Yanlış Sütun Bilgisi...");
                continue;
            }

            if(!isMine(row,col)){
                trueSelect++;
                /*
                System.out.println("Doğru secim ->"+trueSelect);
                System.out.println("Kazanmak için ->"+needWin);
                 */

                if(trueSelect == this.needWin){
                    System.out.println("Oyunu Kazandınız...");
                    printScreenMap();
                    isGameOver = true;
                }else {
                    screenMap[row][col] = Character.forDigit(countMine(row,col),10);
                    printScreenMap();
                }
            }else{
                System.out.println("Game Over!!");
                isGameOver = true;
            }
        }
    }
}
