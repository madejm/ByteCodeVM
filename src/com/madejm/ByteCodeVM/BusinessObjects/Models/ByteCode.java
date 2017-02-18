package com.madejm.ByteCodeVM.BusinessObjects.Models;

import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.ByteCodeInterpreter;

/**
 * Created by mejdej on 19/12/16.
 */
public class ByteCode {

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

//                case IADD:
//                    b = this.context.stack[this.context.sp--];   			// 2nd opnd at top of stack
//                    a = this.context.stack[this.context.sp--]; 			// 1st opnd 1 below top
//                    this.context.stack[++this.context.sp] = a + b;      	// push result
//                    break;
//                case ISUB:
//                    b = this.context.stack[this.context.sp--];
//                    a = this.context.stack[this.context.sp--];
//                    this.context.stack[++this.context.sp] = a - b;
//                    break;
//                case IMUL:
//                    b = this.context.stack[this.context.sp--];
//                    a = this.context.stack[this.context.sp--];
//                    this.context.stack[++this.context.sp] = a * b;
//                    break;
//                case ILT :
//                    b = this.context.stack[this.context.sp--];
//                    a = this.context.stack[this.context.sp--];
//                    this.context.stack[++this.context.sp] = (a < b) ? TRUE : FALSE;
//                    break;
//                case IEQ :
//                    b = this.context.stack[this.context.sp--];
//                    a = this.context.stack[this.context.sp--];
//                    this.context.stack[++this.context.sp] = (a == b) ? TRUE : FALSE;
//                    break;
//                case BR :
//                    this.context.ip = this.context.code[this.context.ip++];
//                    break;
//                case BRT :
//                    addr = this.context.code[this.context.ip++];
//                    if ( this.context.stack[this.context.sp--]==TRUE ) this.context.ip = addr;
//                    break;
//                case BRF :
//                    addr = this.context.code[this.context.ip++];
//                    if ( this.context.stack[this.context.sp--]==FALSE ) this.context.ip = addr;
//                    break;
//                case ICONST:
//                    this.context.stack[++this.context.sp] = this.context.code[this.context.ip++]; // push operand
//                    break;
//                case GLOAD :// load from global memory
//                    addr = this.context.code[this.context.ip++];
//                    this.context.stack[++this.context.sp] = this.context.globals[addr];
//                    break;
//                case GSTORE :
//                    addr = this.context.code[this.context.ip++];
//                    this.context.globals[addr] = this.context.stack[this.context.sp--];
//                    break;
//                case PRINT :
//                    System.out.println(this.context.stack[this.context.sp--]);
//                    break;
//                case POP:
//                    --this.context.sp;
//                    break;

    public static class IADD extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {

        }
    }

    public static class ISUB extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {

        }
    }

    public static class IMUL extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {

        }
    }

    public static class ILT  extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {

        }
    }

    public static class IEQ  extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {

        }
    }

    public static class BR extends ByteCode implements ByteCodeInterpreter {

        public BR() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {

        }
    }

    public static class BRT extends ByteCode implements ByteCodeInterpreter {

        public BRT() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {

        }
    }

    public static class BRF extends ByteCode implements ByteCodeInterpreter {

        public BRF() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {

        }
    }

    public static class ICONST extends ByteCode implements ByteCodeInterpreter {

        public ICONST() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {

        }
    }

    public static class LOAD extends ByteCode implements ByteCodeInterpreter {

        public LOAD() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {

        }
    }

    public static class GLOAD extends ByteCode implements ByteCodeInterpreter {

        public GLOAD() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {

        }
    }

    public static class STORE extends ByteCode implements ByteCodeInterpreter {

        public STORE() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {

        }
    }

    public static class GSTORE extends ByteCode implements ByteCodeInterpreter {

        public GSTORE() {
            super();

            this.numberOfArguments = 1;
        }

        public void interpret(VMContext context) {

        }
    }

    public static class PRINT extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {

        }
    }

    public static class POP extends ByteCode implements ByteCodeInterpreter {

        public void interpret(VMContext context) {

        }
    }

    public static class HALT extends ByteCode {

    }
}
