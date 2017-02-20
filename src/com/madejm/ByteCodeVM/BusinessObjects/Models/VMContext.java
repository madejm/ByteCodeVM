package com.madejm.ByteCodeVM.BusinessObjects.Models;

import com.madejm.ByteCodeVM.BusinessLogic.Printer;

/**
 * Created by mejdej on 19/12/16.
 */
public class VMContext {

    // registers
    public int ip;              // instruction pointer register
    public int sp = -1;  		// stack pointer register

    public int startip = 0;     // where execution begins

    // memory
    public ByteCode[] code;     // word-addressable code memory but still bytecodes.
    public int[] globals;       // global variable space
    public int[] stack;		    // Operand stack, grows upwards

    public Printer printer = new Printer();
}
