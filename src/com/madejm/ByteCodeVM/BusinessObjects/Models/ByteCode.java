package com.madejm.ByteCodeVM.BusinessObjects.Models;

import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.ByteCodeInterpreter;

/**
 * Deklaracja oraz implementacja interpretera dostępnych opercji
 */
public class ByteCode {

    public static final int FALSE = 0;
    public static final int TRUE = 1;

    public String name;
    public int numberOfArguments = 0;

    public ByteCode() {
        this.name = this.getClass().getSimpleName();
    }

    /**
     * Typ VALUE przetrzymuje parametr dla innej operacji
     */
    public static class VALUE extends ByteCode {

        public int value;

        public VALUE(int value) {
            super();

            this.value = value;
        }
    }

    // int add
    public static class IADD extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            int b = context.stack[context.sp--];   		// 2nd opnd at top of stack
            int a = context.stack[context.sp--]; 		// 1st opnd 1 below top
            context.stack[++context.sp] = a + b;      	// push result
        }
    }

    // int substract
    public static class ISUB extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            int b = context.stack[context.sp--];
            int a = context.stack[context.sp--];
            context.stack[++context.sp] = a - b;
        }
    }

    // int multiply
    public static class IMUL extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            int b = context.stack[context.sp--];
            int a = context.stack[context.sp--];
            context.stack[++context.sp] = a * b;
        }
    }

    // int less than
    public static class ILT  extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            int b = context.stack[context.sp--];
            int a = context.stack[context.sp--];
            context.stack[++context.sp] = (a < b) ? TRUE : FALSE;
        }
    }

    // int equal
    public static class IEQ  extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            int b = context.stack[context.sp--];
            int a = context.stack[context.sp--];
            context.stack[++context.sp] = (a == b) ? TRUE : FALSE;
        }
    }

    // branch
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

    // branch if true
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

    // branch if false
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

    // push constant integer
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

    // load from global memory
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

    // store in global memory
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

    // send stack top to printer
    public static class PRINT extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            String value = String.valueOf(context.stack[context.sp--]);
            context.printer.addToQueue(value);
        }
    }

    // throw away top of stack
    public static class POP extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {
            --context.sp;
        }
    }

    // finish execution
    public static class HALT extends ByteCode {

    }
}
