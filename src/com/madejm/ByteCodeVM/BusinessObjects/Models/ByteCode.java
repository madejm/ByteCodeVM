package com.madejm.ByteCodeVM.BusinessObjects.Models;

import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.ByteCodeInterpreter;

/**
 * Created by mejdej on 19/12/16.
 */
public class ByteCode {

    public static final int FALSE = 0;
    public static final int TRUE = 1;

    public String name;
    public int numberOfArguments = 0;

    public ByteCode() {
        this.name = this.getClass().getSimpleName();
    }

    public static class VALUE extends ByteCode {

        public int value;

        public VALUE(int value) {
            super();

            this.value = value;
        }
    }

    public static class IADD extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            int b = context.stack[context.sp--];   		// 2nd opnd at top of stack
            int a = context.stack[context.sp--]; 		// 1st opnd 1 below top
            context.stack[++context.sp] = a + b;      	// push result
        }
    }

    public static class ISUB extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            int b = context.stack[context.sp--];
            int a = context.stack[context.sp--];
            context.stack[++context.sp] = a - b;
        }
    }

    public static class IMUL extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            int b = context.stack[context.sp--];
            int a = context.stack[context.sp--];
            context.stack[++context.sp] = a * b;
        }
    }

    public static class ILT  extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            int b = context.stack[context.sp--];
            int a = context.stack[context.sp--];
            context.stack[++context.sp] = (a < b) ? TRUE : FALSE;
        }
    }

    public static class IEQ  extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            int b = context.stack[context.sp--];
            int a = context.stack[context.sp--];
            context.stack[++context.sp] = (a == b) ? TRUE : FALSE;
        }
    }

    public static class BR extends ByteCode implements ByteCodeInterpreter {

        public BR() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {
            VALUE addr = (VALUE)context.code[context.ip++];
            context.ip = addr.value;
        }
    }

    public static class BRT extends ByteCode implements ByteCodeInterpreter {

        public BRT() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {
            VALUE addr = (VALUE)context.code[context.ip++];
            if ( context.stack[context.sp--]==TRUE ) context.ip = addr.value;
        }
    }

    public static class BRF extends ByteCode implements ByteCodeInterpreter {

        public BRF() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {
            VALUE addr = (VALUE)context.code[context.ip++];
            if ( context.stack[context.sp--]==FALSE ) context.ip = addr.value;
        }
    }

    public static class ICONST extends ByteCode implements ByteCodeInterpreter {

        public ICONST() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {
            // push operand
            VALUE addr = (VALUE)context.code[context.ip++];
            context.stack[++context.sp] = addr.value;
        }
    }

//    public static class LOAD extends ByteCode implements ByteCodeInterpreter {
//
//        public LOAD() {
//            super();
//
//            this.numberOfArguments = 1;
//        }
//
//        public void interpret(VMContext context) {
//
//        }
//    }

    public static class GLOAD extends ByteCode implements ByteCodeInterpreter {

        public GLOAD() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {
            // load from global memory
            VALUE addr = (VALUE)context.code[context.ip++];
            context.stack[++context.sp] = context.globals[addr.value];
        }
    }

//    public static class STORE extends ByteCode implements ByteCodeInterpreter {
//
//        public STORE() {
//            super();
//
//            this.numberOfArguments = 1;
//        }
//
//        public void interpret(VMContext context) {
//
//        }
//    }

    public static class GSTORE extends ByteCode implements ByteCodeInterpreter {

        public GSTORE() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {
            VALUE addr = (VALUE)context.code[context.ip++];
            context.globals[addr.value] = context.stack[context.sp--];
        }
    }

    public static class PRINT extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            String value = String.valueOf(context.stack[context.sp--]);
            context.printer.addToQueue(value);
        }
    }

    public static class POP extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            --context.sp;
        }
    }

    public static class HALT extends ByteCode {

    }
}
