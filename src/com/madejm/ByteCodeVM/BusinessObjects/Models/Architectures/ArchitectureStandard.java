package com.madejm.ByteCodeVM.BusinessObjects.Models.Architectures;

import com.madejm.ByteCodeVM.BusinessLogic.*;
import com.madejm.ByteCodeVM.BusinessObjects.Abstractions.ParseChain;
import com.madejm.ByteCodeVM.BusinessObjects.Abstractions.ParseChain.*;
import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.Architecture;

/**
 * Standardowa architektura obsługująca operacje dodawania, odejmowania oraz mnożenia, przetrzymywanie wartości oraz wypisywanie.
 */
public class ArchitectureStandard implements Architecture {
    public ParseChain makeChain() {
        ParseChain first = new ParsePARSEEND(null); // must be at the end

        first = new ParseHALT(first);
        first = new ParsePRINT(first);
        first = new ParseICONST(first);
        first = new ParseIMUL(first);
        first = new ParseISUB(first);
        first = new ParseIADD(first);

        return first;
    }
}