package com.madejm.ByteCodeVM.BusinessObjects.Abstractions;

import com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode;

import java.util.regex.Pattern;

/**
 * Created by mejdej on 20/02/17.
 */
public abstract class ParseChain {
    protected ParseChain next;

    public ParseChain(ParseChain ch) {
        next = ch;
    }

    public abstract ByteCode makeObject(String ln) throws Exception;

    // CLASSES

    public static class ParseException extends Exception {
        private static final long serialVersionUID = 9158070077781811054L;

        public ParseException() {
            System.out.println("Error while parsing.");
        }
    }

    public static class ParseIADD extends ParseChain {
        public ParseIADD(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("IADD", ln)) {
                return new ByteCode.IADD();
            } else
                return next.makeObject(ln);
        }

    }

    public static class ParseISUB extends ParseChain {
        public ParseISUB(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("ISUB", ln)) {
                return new ByteCode.ISUB();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseIMUL extends ParseChain {
        public ParseIMUL(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("IMUL", ln)) {
                return new ByteCode.IMUL();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseILT extends ParseChain {
        public ParseILT(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("ILT", ln)) {
                return new ByteCode.ILT();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseIEQ extends ParseChain {
        public ParseIEQ(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("IEQ", ln)) {
                return new ByteCode.IEQ();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseBR extends ParseChain {
        public ParseBR(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("BR", ln)) {
                return new ByteCode.BR();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseBRT extends ParseChain {
        public ParseBRT(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("BRT", ln)) {
                return new ByteCode.BRT();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseBRF extends ParseChain {
        public ParseBRF(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("BRF", ln)) {
                return new ByteCode.BRF();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseICONST extends ParseChain {
        public ParseICONST(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("ICONST", ln)) {
                return new ByteCode.ICONST();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseLOAD extends ParseChain {
        public ParseLOAD(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("LOAD", ln)) {
                return new ByteCode.LOAD();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseGLOAD extends ParseChain {
        public ParseGLOAD(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("GLOAD", ln)) {
                return new ByteCode.GLOAD();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseSTORE extends ParseChain {
        public ParseSTORE(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("STORE", ln)) {
                return new ByteCode.STORE();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseGSTORE extends ParseChain {
        public ParseGSTORE(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("GSTORE", ln)) {
                return new ByteCode.GSTORE();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParsePRINT extends ParseChain {
        public ParsePRINT(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("PRINT", ln)) {
                return new ByteCode.PRINT();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParsePOP extends ParseChain {
        public ParsePOP(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("POP", ln)) {
                return new ByteCode.POP();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParseHALT extends ParseChain {
        public ParseHALT(ParseChain ch) {
            super(ch);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            if (Pattern.matches("HALT", ln)) {
                return new ByteCode.HALT();
            } else
                return next.makeObject(ln);
        }
    }

    public static class ParsePARSEEND extends ParseChain {

        public ParsePARSEEND(ParseChain ch) {
            super(null);
        }

        @Override
        public ByteCode makeObject(String ln) throws Exception {
            System.out.println("ERROR, operator not found for current architecture: " + ln);
            throw new ParseException();
        }
    }
}