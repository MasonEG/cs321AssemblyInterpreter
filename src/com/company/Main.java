package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Vector;

public class Main {


    private static Vector<Byte> program = new Vector<Byte>();
    private static int[] registers = new int[32];
    private static Byte[] memory = new Byte[4096];
    private static Byte[] stack;
    private static int pC = 0;

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
                    byteString = byteString.concat(String.format("%8s", Integer.toBinaryString(b & 0xFF).replace(' ', '0')));
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

    // 0b000000000000000000000000000000 (template)
    public static void ADD (int inst) { // R
        int Rm = (inst & 0b000000000001111100000000000000) >> 14;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] + registers[Rm];
    }
    public static void ADDI (int inst) { // I
        int imm = (inst & 0b000000000111111111110000000000) >> 10;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] + imm;
    }
    public static void AND (int inst) {
        int Rm = (inst & 0b000000000001111100000000000000) >> 14;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] & registers[Rm];
    }
    public static void ANDI (int inst) {
        int imm = (inst & 0b000000000111111111110000000000) >> 10;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] & imm;
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
        int Rm = (inst & 0b000000000001111100000000000000) >> 14;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] ^ registers[Rm];
    }
    public static void EORI (int inst) {
        int imm = (inst & 0b000000000111111111110000000000) >> 10;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] ^ imm;
    }
    public static void HALT (int inst) {

    }
    public static void LDUR (int inst) {

    }
    public static void LSL (int inst) {
        int Rm = (inst & 0b000000000001111100000000000000) >> 14;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] << registers[Rm];
    }
    public static void LSR (int inst) {
        int Rm = (inst & 0b000000000001111100000000000000) >> 14;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] >> registers[Rm];
    }
    public static void MUL (int inst) {
        int Rm = (inst & 0b000000000001111100000000000000) >> 14;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] * registers[Rm];
    }
    public static void ORR (int inst) {
        int Rm = (inst & 0b000000000001111100000000000000) >> 14;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] | registers[Rm];
    }
    public static void ORRI (int inst) {
        int imm = (inst & 0b000000000111111111110000000000) >> 10;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] | imm;
    }
    public static void PRNL (int inst) {

    }
    public static void PRNT (int inst) {

    }
    public static void STUR (int inst) {

    }
    public static void SUB (int inst) {
        int Rm = (inst & 0b000000000001111100000000000000) >> 14;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] - registers[Rm];
    }
    public static void SUBI (int inst) {
        int imm = (inst & 0b000000000111111111110000000000) >> 10;
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;
        int Rd = (inst & 0b000000000000000000000000011111);

        registers[Rd] = registers[Rn] | imm;
    }
    public static void SUBIS (int inst) {

    }
    public static void SUBS (int inst) {

    }



    public static void main(String[] args) {
        // write your code here
        ReadFile(args[0]);
        while (pC < program.size()) { //go through el programo
            int i = pC * 4;
            int instruction = program.get(i + 3) |
                    program.get(i + 2) << 8 |
                    program.get(i + 1) << 16 |
                    program.get(i) << 24;
            if ((instruction & 0b100010110000000000000000000000) == 0b100010110000000000000000000000) ADD(instruction);
            else if ((instruction & 0b100100010000000000000000000000) == 0b100100010000000000000000000000) ADDI(instruction);
            else if ((instruction & 0b100010100000000000000000000000) == 0b100010100000000000000000000000) AND(instruction);
            else if ((instruction & 0b100100100000000000000000000000) == 0b100100100000000000000000000000) ANDI(instruction);
            else if ((instruction & 0b000101000000000000000000000000) == 0b000101000000000000000000000000) B(instruction);
            else if ((instruction & 0b010101000000000000000000000000) == 0b010101000000000000000000000000) BCOND(instruction);

            pC++;
        }

    }
}
