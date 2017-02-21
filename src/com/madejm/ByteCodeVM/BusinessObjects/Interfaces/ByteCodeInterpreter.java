package com.madejm.ByteCodeVM.BusinessObjects.Interfaces;

import com.madejm.ByteCodeVM.BusinessObjects.Models.VMContext;

import java.util.Map;

/**
 * Główny interfejs interpretera operacji.
 */
public interface ByteCodeInterpreter {
    public void interpret(VMContext context);
}