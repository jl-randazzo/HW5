import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;


/*
 * I set this up so that we could still print all the data in the hash table. I thought it would be nice to be able
 * to visualize where all of the info was ending up.
* */

public class LukeHW5Part3 {

    public static void main(String[] args) throws IOException {
        String fileName = "dma.txt";
        String outputFile = "output.txt";
        FileWriter fw = new FileWriter(outputFile);
        boolean selector;
        Scanner kb = new Scanner(System.in);
        String inp;
        LukeHash Part3 = new LukeHash (213);

        while(true) {
            System.out.println("Please select the data you'd like to use as a key\n1)dma code\n2)region");
            inp = kb.nextLine();
            if(inp.equals("1")){selector = true; break;}
            else if(inp.equals("2")){selector = false; break;}
            else System.out.println("-----Invalid entry-----\n\n");
        }
        long startTime = System.currentTimeMillis();
        readFile(fileName, Part3, selector);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("\n\n--------------------------\n"+
                            " TOTAL CPU TIME: " + totalTime +
                            "\n TOTAL COLLISIONS: " + Part3.collisions +
                            "\n--------------------------");



        while(true){
            System.out.println("Please select the output algorithm you'd like to use for each slot:\n1)Preorder\n2)Inorder");
            inp = kb.nextLine();
            if(inp.equals("1")){selector = true; break;}
            else if(inp.equals("2")){selector = false; break;}
            else System.out.println("-----Invalid entry-----\n\n");
        }

        fw.write("\n\n--------------------------\n"+
                " TOTAL CPU TIME: " + totalTime +
                "\n TOTAL COLLISIONS: " + Part3.collisions +
                "\n--------------------------");
        Part3.output(fw, selector);
        fw.close();
        System.out.println("Your output has been saved in the root folder as 'output.txt'");

    }

    public static void readFile(String fileName, LukeHash hashMap, boolean selector) throws IOException{
        Scanner fileRead = new Scanner(new File(fileName));
        fileRead.nextLine();
        CityOb temp;

        while(fileRead.hasNextLine()){
            temp = new CityOb(fileRead.nextLine(), selector);
            hashMap.add(temp);
        }

        fileRead.close();
    }
}
