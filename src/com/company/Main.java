package com.company;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Vector;

public class Main {

    public static void print(String s) {
       System.out.println(s);
    }

    private static Vector<Byte> program = new Vector<Byte>();

    private static void ReadFile (String file) {
        InputStream inputStream;
        byte[] current = new byte[4];
        try {
            inputStream = Files.newInputStream(Paths.get(file), StandardOpenOption.READ);
            int i;
            while ((i = inputStream.read(current, 0, 4)) != -1) {
                String byteString = "";
                for (byte b: current) {
                    program.add(b);
                    byteString += String.format("%8s", Integer.toBinaryString(b & 0xFF).replace(' ', '0'));
//                    byteString += String.format("%8s", Integer.toBinaryString(b));
                }
                print(byteString.replace(' ', '0'));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void ADD (int inst) {

    }

    public static void main(String[] args) {
	// write your code here
        ReadFile(args[0]);
        for (int i = 0; i < program.size(); i += 4) { //go through el programo
            int instruction = program.get(i + 3) |
                    program.get(i + 2) << 8 |
                    program.get(i + 1) << 16 |
                    program.get(i) << 24;
            switch (instruction) {
                case(instruction & 0b100010110000000000000000000000):
                    break;
                default:
                    print("Invalid instruction! +" + Integer.toBinaryString(instruction));
                    break;
            }
        }

    }
}
