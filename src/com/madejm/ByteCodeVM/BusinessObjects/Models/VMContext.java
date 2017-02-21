package com.madejm.ByteCodeVM.BusinessObjects.Models;

import com.madejm.ByteCodeVM.BusinessLogic.Printer;

/**
 *
 * Kontekst maszyny wirtualnej.
 * Przetrzymuje listę operacji, stos zmiennych, zmienne globalne oraz wskaźniki stosów.
 *
 */

public class VMContext {

    /**
     * Wskaźnik rejestru instrukcji
     */
    public int ip;
    /**
     * Wskaźnik rejestru stosu zmiennych
     */
    public int sp = -1;

    /**
     * Wskaźnik początkowej operacji
     */
    public int startip = 0;

    /**
     * Rejestr operacji
     */
    public ByteCode[] code;

    /**
     * Rejestr zmiennych globalnych
     */
    public int[] globals;

    /**
     * Stos zmiennych
     */
    public int[] stack;

    /**
     * Printer do wypisywania wyników operacji
     */
    public Printer printer = new Printer();
}
