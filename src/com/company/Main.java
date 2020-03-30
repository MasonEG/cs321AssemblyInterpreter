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
        int i;
        try {
            inputStream = Files.newInputStream(Paths.get(file), StandardOpenOption.READ);
            while ((i = inputStream.read(current, 0, 4)) != -1) {
                String byteString = "";
                for (byte b: current) {
                    byteString +=
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
	// write your code here
        print(args[0]);
        ReadFile(args[0]);
    }
}
