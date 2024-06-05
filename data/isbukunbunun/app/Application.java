package data.isbukunbunun.app;

import java.util.Scanner;

import data.isbukunbunun.parser.Parser;

public class Application 
{
    private Parser parser;
    private Scanner scanner;

    public Application()
    {
        this.parser = new Parser();
        this.scanner = new Scanner(System.in);

        System.out.println("Please input a Isbukun Bunun lexical word");
        this.parser.setup(this.scanner.nextLine());
        this.parser.parse();
        System.out.println("The result is: " + this.parser.getOutput());
    }

    public static void main(String[] args) 
    {
        new Application();
    }    
}
