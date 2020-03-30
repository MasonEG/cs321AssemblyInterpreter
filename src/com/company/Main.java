package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Vector;

public class Main {


    private static Vector<Byte> program = new Vector<Byte>();
    private static Byte[][] registers = new Byte[32][4];
    private static Byte[] memory;
    private static Byte[] stack;

    private static void ReadFile(String file) {
        InputStream inputStream;
        byte[] current = new byte[4];
        try {
            inputStream = Files.newInputStream(Paths.get(file), StandardOpenOption.READ);
            int i;
            while ((i = inputStream.read(current, 0, 4)) != -1) {
                String byteString = "";
                for (byte b : current) {
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

    private static void print(String s) {
        System.out.println(s);
    }

    public static void ADD (int inst) {

    }
    public static void ADDI (int inst) {

    }
    public static void AND (int inst) {

    }
    public static void ANDI (int inst) {

    }
    public static void B (int inst) {

    }
    public static void BCOND (int inst) {

    }
    public static void BL (int inst) {

    }
    public static void BR (int inst) {

    }
    public static void CBNZ (int inst) {

    }
    public static void CBZ (int inst) {

    }
    public static void DUMP () {

    }
    public static void EOR (int inst) {

    }
    public static void EORI (int inst) {

    }
    public static void HALT (int inst) {

    }
    public static void LDUR (int inst) {

    }
    public static void LDURB (int inst) {

    }
    public static void LDURH (int inst) {

    }
    public static void LDURSW (int inst) {

    }
    public static void LSL (int inst) {

    }
    public static void LSR (int inst) {

    }
    public static void MUL (int inst) {

    }
    public static void ORR (int inst) {

    }
    public static void ORRI (int inst) {

    }
    public static void PRNL (int inst) {

    }
    public static void PRNT (int inst) {

    }
    public static void SDIV (int inst) {

    }
    public static void SMULH (int inst) {

    }
    public static void STUR (int inst) {

    }
    public static void STURB (int inst) {

    }
    public static void STURH (int inst) {

    }
    public static void STURW (int inst) {

    }
    public static void SUB (int inst) {

    }
    public static void SUBI (int inst) {

    }
    public static void SUBIS (int inst) {

    }
    public static void SUBS (int inst) {

    }
    public static void UDIV (int inst) {

    }
    public static void UMULH (int inst) {

    }



    public static void main(String[] args) {
        // write your code here
        ReadFile(args[0]);
        for (int i = 0; i < program.size(); i += 4) { //go through el programo
            int instruction = program.get(i + 3) |
                    program.get(i + 2) << 8 |
                    program.get(i + 1) << 16 |
                    program.get(i) << 24;
            if ((instruction & 0b100010110000000000000000000000) == 0b100010110000000000000000000000) ADD(instruction);
            else if ((instruction & 0b100010110000000000000000000000) == 0b100010110000000000000000000000)

        }

    }
}
