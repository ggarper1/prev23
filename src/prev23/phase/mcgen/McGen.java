package prev23.phase.mcgen;

import java.io.*;
import java.util.*;

import prev23.common.report.*;

import prev23.phase.asmgen.*;
import prev23.phase.imclin.*;

import prev23.data.asm.*;
import prev23.data.lin.*;
import prev23.data.mem.*;

public class McGen {

    private final HashMap<MemTemp, Integer> regs;

    private FileOutputStream file;

    private PrintWriter print;

    public McGen(HashMap<MemTemp, Integer> map){
        this.regs = map;
        try{
            this.file = new FileOutputStream("code.mms");
            this.print = new PrintWriter(file);
        }catch(Exception e){
            throw new Report.Error("Problem generating machine code file!");
        }

        
    }

    public void printLib(){
        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tFunction _putChar");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("");

        print.println("_putChar\tLDO $1,$255,8");
        print.println("\t\t\tSET $0,$255");
        print.println("\t\t\tLDA $255,pbuff");
        print.println("\t\t\tSTO $1,$255,0");
        print.println("\t\t\tSET $1,0");
        print.println("\t\t\tSTO $1,$255,8");
        print.println("\t\t\tTRAP 0,Fputs,StdOut");
        print.println("\t\t\tSET $255,$0");
        print.println("\t\t\tPOP 0,0");

        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tEnd of Function _putChar");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("\n");

        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tFunction _getChar");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("");

        print.println("_getChar\tSET $0,$255");
        print.println("\t\t\tLDA $255,gbuff");
        print.println("\t\t\tTRAP 0,Fgets,StdIn");
        print.println("\t\t\tLDO $1,$255,0");
        print.println("\t\t\tSET $255,$0");
        print.println("\t\t\tSTO $1,$255,0");
        print.println("\t\t\tPOP 0,0");

        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tEnd of Function _getChar");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("\n");


        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tFunction _new");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("");

        print.println("_new\t\tLDO $0,$255,0");
        print.println("\t\t\tSTO $253,$255,0");
        print.println("\t\t\tADD $253,$253,0");
        print.println("\t\t\tPOP 0,0");

        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tEnd of Function _new");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("\n");


        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tFunction _del");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("");

        print.println("%Core\n");
        print.println("del_\t\tSWYM 0,0,0");
        print.println("\t\t\tPOP 0,0");

        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tEnd of Function _del");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("\n");

    }

    public void printData(){
        print.println("\t\tLOC\t\tData_Segment");
        print.println("\t\tGREG\t\t@");

        print.print("\n");

        print.println("pbuff\t\t\tOCTA\t\t0,0");
        print.println("gbuff\t\t\tOCTA\t\t0,0");

        print.println("\n\t\tGREG\t\t@");

        for(LinDataChunk d : ImcLin.dataChunks()){
            String aux = d.label.name + "\t\t\t\tOCTA\t\t0";
            long s = d.size / 8;
            while(s > 1){
                aux = aux + ", 0";
                s--;
            }
            print.println(aux);
        }

        print.print("\n\t\tLOC\t\t#100");
        print.println("\n");
        print.print("%In GETs and PUTs, 4 is the rJ register");
        print.print("\n");
    }

    public void prologue(Code c){

        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tFunction " + c.entryLabel.name);
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("");

        print.println("%\tPrologue\n");

        //Store old FP

        print.println(c.entryLabel.name+"\t\tSET $0,"+c.frame.locsSize);
        print.println("\t\t\tADD $1,$0,8");
        print.println("\t\t\tNEG $1,$1");
        print.println("\t\t\tSTO $254,$255,$1\n");
        
        //Change FP

        print.println("\t\t\tSET $254,$255\n");
        
        //Change SP

        print.println("\t\t\tSET $0,"+(c.frame.size+c.tempSize));
        print.println("\t\t\tNEG $0,$0");
        print.println("\t\t\tADD $255,$255,$0\n");

        //Store RA

        print.println("\t\t\tGET $0,4");
        print.println("\t\t\tSET $1,"+c.frame.locsSize);
        print.println("\t\t\tADD $1,$1,16");
        print.println("\t\t\tNEG $1,$1");
        print.println("\t\t\tSTO $0,$254,$1\n");

    }

    public void core(Code c){

        print.println("%\tCore");
        print.println("");

        for(int i = 1; i < c.instrs.size() - 1; i++){
            if(c.instrs.get(i) instanceof AsmLABEL){
                if(c.instrs.get(i + 1) instanceof AsmLABEL){
                    print.println(c.instrs.get(i).toString(regs)+"\t\t\tSWYM 0,0,0");
                }else{
                    print.print(c.instrs.get(i).toString(regs));
                }
            }else{
                print.println("\t\t\t"+c.instrs.get(i).toString(regs));
            }   
        }
        print.println("");

    }

    public void epilogue(Code c){

        print.println("%\tEpilogue\n");

        //Store RV
        print.println(c.exitLabel.name+"\t\t\tSTO $0,$254,0\n");

        //Change SP
        print.println("\t\t\tSET $255,$254\n");

        //Change FP
        print.println("\t\t\tSET $0," + c.frame.locsSize);
        print.println("\t\t\tADD $0,$0,8");
        print.println("\t\t\tNEG $0,$0");
        print.println("\t\t\tLDO $254,$254,$0\n");

        //Set & go to RA
        print.println("\t\t\tSET $0," + c.frame.locsSize);
        print.println("\t\t\tADD $0,$0,16");
        print.println("\t\t\tNEG $0,$0");
        print.println("\t\t\tLDO $1,$255,$0");
        print.println("\t\t\tPUT 4,$1");
        print.println("\t\t\tPOP 0,0");

        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tEnd of Function " + c.entryLabel.name);
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("\n");

    }

    public void printMain(Code c){
        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tFunction _main");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("");

        print.println("%\tPrologue");
        //set heap address 2000000000000000 + DataSegment.size()
        print.println("_main\t\tSET $253,0");
        print.println("\t\t\tSETH $253,8192");
        long sum = 0;
        for(int i = 0; i < ImcLin.dataChunks().size(); i++){
            if(sum + ImcLin.dataChunks().get(i).size > 65535){
                print.println("\t\t\tADD $253,$253,"+sum);
                sum = 0;
            }else{
                sum =+ ImcLin.dataChunks().get(i).size;
            }
        }
        print.println("\t\t\tADD $253,$253,"+sum);
        //set FP<-7FFFFFFFFFFFFFFF
        print.println("\t\t\tSETH $254,32767");
        print.println("\t\t\tSETMH $0,65535");
        print.println("\t\t\tADD $254,$254,$0");
        print.println("\t\t\tSETML $0,65535");
        print.println("\t\t\tADD $254,$254,$0");
        print.println("\t\t\tSETL $0,65535");
        print.println("\t\t\tADD $254,$254,$0");
        //set SP
        print.println("\t\t\tSET $0," + (c.frame.size+c.tempSize));
        print.println("\t\t\tNEG $0,$0");
        print.println("\t\t\tADD $255,$254,$0");

        print.println("%\tCore");
        print.println("");

        for(int i = 1; i < c.instrs.size() - 1; i++){
            if(c.instrs.get(i) instanceof AsmLABEL){
                if(c.instrs.get(i + 1) instanceof AsmLABEL){
                    print.println(c.instrs.get(i).toString(regs)+"\t\t\tSWYM 0,0,0");
                }else{
                    print.print(c.instrs.get(i).toString(regs));
                }
            }else{
                print.println("\t\t\t"+c.instrs.get(i).toString(regs));
            }
        }
        print.println(c.exitLabel.name + "\t\tTRAP 0,Halt,0");

        print.println("");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("%\tEnd of Function _main");
        print.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        print.println("\n");
    }

    public void generate(){

         try{

            printData();

            print.println("\nMain\t\t\tJMP _main\n");


            printLib();

            for(Code c : AsmGen.codes){
                if(c.entryLabel.name.equals("_main")){
                    printMain(c);
                }else if(!c.entryLabel.name.equals("_putChar") &&
                            !c.entryLabel.name.equals("_getChar")){
                    prologue(c);
                    core(c);
                    epilogue(c);
                }
            }

            print.close();
            file.close();
        
        }catch(Exception e){
            throw new Report.Error("Error when writting machine code!");
        }

    }

}