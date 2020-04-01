package com.company;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Vector;

public class Main {


    private static Vector<Byte> program = new Vector<Byte>();
    private static long[] registers = new long[32];
    private static Byte[] memory = new Byte[4096];
    private static Byte[] stack = new Byte[512];
    private static long pC = 0;
    private static short flags = 0;

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
        int addr = (inst & 0b000000111111111111111111111111);
        pC += addr;
    }
    public static void BCOND (int inst) {
        int addr = (inst & 0b000000011111111111111111100000) >> 5;
        int Rt = (inst & 0b000000000000000000000000011111);

        if (flags == Rt) pC += addr;
    }
    public static void BL (int inst) {
        int addr = (inst & 0b000000111111111111111111111111);

        registers[30] = pC;
        pC += addr;
    }
    public static void BR (int inst) {
        int Rn = (inst & 0b000000000000000000001111100000) >> 5;

        pC = registers[Rn];
    }
    public static void CBNZ (int inst) {
        int addr = (inst & 0b000000011111111111111111100000) >> 5;
        int Rt = (inst & 0b000000000000000000000000011111);

        if (Rt != 0) pC += addr;
    }
    public static void CBZ (int inst) {
        int addr = (inst & 0b000000011111111111111111100000) >> 5;
        int Rt = (inst & 0b000000000000000000000000011111);

        if (Rt == 0) pC += addr;
    }
    //This is an added instruction that will display the contents of all
    //        registers and memory, as well as the disassembled program (branch
    //        targets are given as the PC-relative offset from the branch
    //        instruction).
    public static void DUMP () {
        System.out.println(registers + "/n" + memory + "/n" + stack);
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

    //This is an added instruction that will trigger a DUMP and terminate the emulator.
    public static void HALT () {
        DUMP();
        System.exit(0);
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
        ReadFile(args[0]);
        while ((pC * 4) < program.size()) { //go through el programo
            int i = (int) pC * 4;
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
            else if ((instruction & 0b100101000000000000000000000000) == 0b100101000000000000000000000000) BL(instruction); //b instruction format
            else if ((instruction & 0b110101100000000000000000000000) == 0b110101100000000000000000000000) BR(instruction);
            else if ((instruction & 0b101101010000000000000000000000) == 0b101101010000000000000000000000) CBNZ(instruction); //cb instruction format
            else if ((instruction & 0b101101000000000000000000000000) == 0b101101000000000000000000000000) CBZ(instruction); //cb instruction format
            else if ((instruction & 0b111111111100000000000000000000) == 0b111111111100000000000000000000) DUMP();
            else if ((instruction & 0b110010100000000000000000000000) == 0b110010100000000000000000000000) EOR(instruction);
            else if ((instruction & 0b110100100000000000000000000000) == 0b110100100000000000000000000000) EORI(instruction);
            else if ((instruction & 0b111111111110000000000000000000) == 0b111111111110000000000000000000) HALT();
            else if ((instruction & 0b111110000100000000000000000000) == 0b111110000100000000000000000000) LDUR(instruction);
            else if ((instruction & 0b110100110110000000000000000000) == 0b110100110110000000000000000000) LSL(instruction);
            else if ((instruction & 0b110100110100000000000000000000) == 0b110100110100000000000000000000) LSR(instruction);
            else if ((instruction & 0b100110110000000000000000000000) == 0b100110110000000000000000000000) MUL(instruction); //does not include shamt binary
            else if ((instruction & 0b101010100000000000000000000000) == 0b101010100000000000000000000000) ORR(instruction);
            else if ((instruction & 0b101100100000000000000000000000) == 0b101100100000000000000000000000) ORRI(instruction);
            else if ((instruction & 0b111111111000000000000000000000) == 0b111111111000000000000000000000) ORRI(instruction);
            else if ((instruction & 0b111111111000000000000000000000) == 0b111111111000000000000000000000) PRNL(instruction);
            else if ((instruction & 0b111111111010000000000000000000) == 0b111111111010000000000000000000) PRNT(instruction);
            else if ((instruction & 0b111110000000000000000000000000) == 0b111110000000000000000000000000) STUR(instruction);
            else if ((instruction & 0b110010110000000000000000000000) == 0b110010110000000000000000000000) SUB(instruction);
            else if ((instruction & 0b110100010000000000000000000000) == 0b110100010000000000000000000000) SUBI(instruction);
            else if ((instruction & 0b111100010000000000000000000000) == 0b111100010000000000000000000000) SUBIS(instruction);
            else if ((instruction & 0b111010110000000000000000000000) == 0b111010110000000000000000000000) SUBS(instruction);

            pC++;
        }

    }
}
