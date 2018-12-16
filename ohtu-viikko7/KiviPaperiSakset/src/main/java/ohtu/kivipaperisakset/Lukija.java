package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Lukija implements IO {
    
    private Scanner scanner;

    public Lukija() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String nextLine() {
        return this.scanner.nextLine();
    }    
}
