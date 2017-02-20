package com.madejm.ByteCodeVM;

import com.madejm.ByteCodeVM.BusinessLogic.VM;
import com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode;

import static com.madejm.ByteCodeVM.BusinessLogic.Bytecode.*;
import static com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode.*;

public class Main {

    static int[] hello = {
            ICONST, 10,
            ICONST, 22,
            IADD,
            PRINT,
            HALT
    };

    static int[] loopp = {
            // .GLOBALS 2; N, I
            // N = 10						ADDRESS
            ICONST, 10,				// 0
            GSTORE, 0,				// 2
            // I = 0
            ICONST, 0,				// 4
            GSTORE, 1,				// 6
            // WHILE I<N:
            // START (8):
            GLOAD, 1,				// 8
            GLOAD, 0,				// 10
            ILT,					// 12
            BRF, 24,				// 13
            //     I = I + 1
            GLOAD, 1,				// 15
            ICONST, 1,				// 17
            IADD,					// 19
            GSTORE, 1,				// 20
            BR, 8,					// 22
            // DONE (24):
            // PRINT "LOOPED "+N+" TIMES."
            HALT					// 24
    };

    static ByteCode[] loop = {
            new ICONST(), new VALUE(10),				// 0
            new GSTORE(), new VALUE(0),				// 2
            // I = 0
            new ICONST(), new VALUE(0),				// 4
            new GSTORE(), new VALUE(1),				// 6
            // WHILE I<N:
            // START (8):
            new GLOAD(), new VALUE(1),				// 8
            new GLOAD(), new VALUE(0),				// 10
            new ILT(),					// 12
            new BRF(), new VALUE(24),				    // 13
            //     I = I + 1
            new GLOAD(), new VALUE(1),				// 15
            new ICONST(), new VALUE(1),				// 17
            new IADD(),					// 19
            new GSTORE(), new VALUE(1),				// 20
            new BR(), new VALUE(8),					// 22
            // DONE (24):
            // PRINT "LOOPED "+N+" TIMES."
            new HALT()					// 24
    };

    static ByteCode[] multiply = {
            new ICONST(), new VALUE(10),
            new ICONST(), new VALUE(22),
            new IMUL(),
            new PRINT(),
            new HALT()
    };

    public static void main(String[] args) {
        VM vm = new VM(multiply, 0, 0);
        vm.trace = true;
        vm.exec();
        vm.dumpCodeMemory();

//        vm = new VM(loop, 0, 2);
//        vm.trace = true;
//        vm.exec();
    }
}
