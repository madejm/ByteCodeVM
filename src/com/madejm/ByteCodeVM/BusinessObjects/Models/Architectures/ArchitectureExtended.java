package com.madejm.ByteCodeVM.BusinessObjects.Models.Architectures;

import com.madejm.ByteCodeVM.BusinessLogic.*;
import com.madejm.ByteCodeVM.BusinessObjects.Abstractions.ParseChain;
import com.madejm.ByteCodeVM.BusinessObjects.Abstractions.ParseChain.*;
import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.Architecture;

/**
 * Architektura rozszerzająca standardową, dodająca zmienne globalne, porównania, branch oraz pop
 */
public class ArchitectureExtended implements Architecture {
    public ParseChain makeChain() {
        ParseChain first = new ArchitectureStandard().makeChain(); // architecture inheritance

        first = new ParsePOP(first);
        first = new ParseGSTORE(first);
        first = new ParseGLOAD(first);
        first = new ParseBR(first);
        first = new ParseBRT(first);
        first = new ParseBRF(first);
        first = new ParseIEQ(first);
        first = new ParseILT(first);

        return first;
    }
}
