package chucknorris;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = sc.nextLine();
            switch (operation) {
                case "encode":
                    encode();
                    break;
                case "decode":
                   decode();
                    break;
                case "exit":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("There is no '" + operation + "' operation");
                    System.out.println(); 
                    break;
            }
        }
    }
  
    public static void encode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input string:");
        String s = sc.nextLine();
        System.out.println("Encoded string:");
       
        String binary = "";       
        for (int ch : s.toCharArray()) {
            binary += String.format("%7s", Integer.toBinaryString(ch)).replace(" ", "0");
        }

        int[] array = new int[binary.length()];
        for (int i = 0; i < binary.length(); i++) {
            array[i] = Character.getNumericValue(binary.charAt(i));
		}
        
        int count7Bit = 0;    
        if (array[0] == 0) {
            System.out.print(String.format("%s00 0", count7Bit % 7 == 0 ? "" : " "));
        } else if (array[0] == 1) {
            System.out.print(String.format("%s0 0", count7Bit % 7 == 0 ? "" : " "));
        }
        count7Bit++;

        int previous = array[0];               
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 0 && previous == 1) {
                System.out.print(" 00 0");
            } else if (array[i] == 0 && previous == 0) {
                System.out.print("0");
            } else if (array[i] == 1 && previous == 0) {
                System.out.print(" 0 0");
            } else if (array[i] == 1 && previous == 1) {
                System.out.print("0");
            }
            previous = array[i];  
        }
        System.out.println();
    }

    public static void decode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input encoded string:");
        String s = sc.nextLine();
        
        for (int i = 0; i < s.length(); i++) {
            if ('0' != s.charAt(i) && ' ' != s.charAt(i)) {
                System.out.println("Encoded string is not valid.");
                System.out.println();
                return;
            }
        } 

        String[] sArr = s.split(" ");
        String binary = "";
        for (int i = 0; i < sArr.length - 1; i++) {         
                if (i % 2 == 0 && !"0".equals(sArr[i]) && i % 2 == 0 && !"00".equals(sArr[i])) {
                System.out.println("Encoded string is not valid.");
                System.out.println();
                return;
            } else if (sArr.length % 2 != 0) {
                System.out.println("Encoded string is not valid.");
                System.out.println();
                return;
            } else if (i % 2 == 0 && "0".equals(sArr[i])) {
                binary += sArr[i + 1].replace("0", "1");
            } else if (i % 2 == 0 && "00".equals(sArr[i])) {
                binary += sArr[i + 1];
            }
        }
        if (binary.length() % 7 != 0) {
            System.out.println("Encoded string is not valid.");
            System.out.println();
            return;
        }
        System.out.println("Decoded string:");
        while (binary.length() >= 7) {
            String sub = binary.substring(0, 7);
            System.out.print((char)Integer.parseInt(sub, 2)); 
            binary = binary.substring(7);          
        } 
        System.out.println(); 
    }
}