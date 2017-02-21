package com.madejm.ByteCodeVM.BusinessObjects.Interfaces;

import com.madejm.ByteCodeVM.BusinessObjects.Abstractions.ParseChain;

/**
 * Interfejs architektur wstrzykiwanych jako strategia do parsera.
 */
public interface Architecture {
    ParseChain makeChain();
}
