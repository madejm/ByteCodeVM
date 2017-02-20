package com.madejm.ByteCodeVM.BusinessObjects.Models.Architectures;

import com.madejm.ByteCodeVM.BusinessLogic.*;
import com.madejm.ByteCodeVM.BusinessObjects.Abstractions.ParseChain;
import com.madejm.ByteCodeVM.BusinessObjects.Abstractions.ParseChain.*;
import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.Architecture;

/**
 * Created by mejdej on 20/02/17.
 */
public class ArchitectureStandard implements Architecture {
    public ParseChain makeChain() {
        ParseChain first = new ParsePARSEEND(null); // must be at the end
        first = new ParseHALT(first);
        first = new ParsePOP(first);
        first = new ParsePRINT(first);
        first = new ParseSTORE(first);
        first = new ParseGSTORE(first);
        first = new ParseLOAD(first);
        first = new ParseGLOAD(first);
        first = new ParseICONST(first);
        first = new ParseBR(first);
        first = new ParseBRT(first);
        first = new ParseBRF(first);
        first = new ParseHALT(first);
        first = new ParseIEQ(first);
        first = new ParseILT(first);
        first = new ParseIMUL(first);
        first = new ParseISUB(first);
        first = new ParseIADD(first);

        return first;
    }
}