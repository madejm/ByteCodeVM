package com.madejm.ByteCodeVM.BusinessLogic;

/**
 * Created by mejdej on 17/12/16.
 */
import com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode;
import com.madejm.ByteCodeVM.BusinessObjects.Models.VMContext;
import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.ByteCodeInterpreter;

import java.util.ArrayList;
import java.util.List;

import static com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode.*;

/** A simple stack-based interpreter */

public class VM {
    public static final int DEFAULT_STACK_SIZE = 1000;
    public static final int FALSE = 0;
    public static final int TRUE = 1;

    public VMContext context = new VMContext();

    public boolean trace = false;

    public VM(ByteCode[] code, int startip, int nglobals) {
        this.context.code = code;
        this.context.startip = startip;
        this.context.globals = new int[nglobals];
        this.context.stack = new int[DEFAULT_STACK_SIZE];
    }

    public void exec() {
        this.context.ip = this.context.startip;
        cpu();
    }

    /** Simulate the fetch-decode execute cycle */
    protected void cpu() {
        ByteCode opcode = this.context.code[this.context.ip];
        int a,b,addr,offset;
        while (opcode instanceof HALT && this.context.ip < this.context.code.length) {
            if ( trace ) System.err.printf("%-35s", disInstr());
            this.context.ip++; //jump to next instruction or to operand

            if (opcode instanceof ByteCodeInterpreter) {
                ((ByteCodeInterpreter)opcode).interpret(this.context);
            } else {
                throw new Error("invalid opcode: "+opcode+" at ip="+(this.context.ip-1));
            }

            if ( trace ) System.err.println(stackString());
            opcode = this.context.code[this.context.ip];
        }
        if ( trace ) System.err.printf("%-35s", disInstr());
        if ( trace ) System.err.println(stackString());
        if ( trace ) dumpDataMemory();
    }

    protected String stackString() {
        StringBuilder buf = new StringBuilder();
        buf.append("stack=[");
        for (int i = 0; i <= this.context.sp; i++) {
            int o = this.context.stack[i];
            buf.append(" ");
            buf.append(o);
        }
        buf.append(" ]");
        return buf.toString();
    }

    protected String disInstr() {
        ByteCode opcode = this.context.code[this.context.ip];
        String opName = opcode.name;
        StringBuilder buf = new StringBuilder();
        buf.append(String.format("%04d:\t%-11s", this.context.ip, opName));
        int nargs = opcode.numberOfArguments;
        if ( nargs>0 ) {
            List<String> operands = new ArrayList<String>();
            for (int i=this.context.ip+1; i<=this.context.ip+nargs; i++) {
                operands.add(String.valueOf(this.context.code[i]));
            }
            for (int i = 0; i<operands.size(); i++) {
                String s = operands.get(i);
                if ( i>0 ) buf.append(", ");
                buf.append(s);
            }
        }
        return buf.toString();
    }

    public void dumpDataMemory() {
        System.err.println("Data memory:");
        int addr = 0;
        for (int o : this.context.globals) {
            System.err.printf("%04d: %s\n", addr++, o);
        }
        System.err.println();
    }

    public void dumpCodeMemory() {
        System.err.println("Code memory:");
        int addr = 0;
        for (ByteCode o : this.context.code) {
            String value = o instanceof VALUE ? String.valueOf(((VALUE)o).value) : o.name;

            System.err.printf("%04d: " + value + "\n", addr++);
        }
        System.err.println();
    }
}
