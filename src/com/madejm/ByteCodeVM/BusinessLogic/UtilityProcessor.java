package com.madejm.ByteCodeVM.BusinessLogic;

import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.Architecture;
import com.madejm.ByteCodeVM.BusinessObjects.Models.Architectures.ArchitectureExtended;
import com.madejm.ByteCodeVM.BusinessObjects.Models.Architectures.ArchitectureStandard;
import com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode;
import com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode.*;

import java.util.HashMap;

/**
 * Singleton udostępniający dodatkowe metody.
 */
public class UtilityProcessor {

    private static UtilityProcessor instance;

    private static HashMap<String, Class> architectures = new HashMap<String, Class>() {{
        put("-std", ArchitectureStandard.class);
        put("-ext", ArchitectureExtended.class);
    }};

    public static UtilityProcessor getInstance() {
        if (instance == null) {
            instance = new UtilityProcessor();
        }

        return instance;
    }

    /**
     *
     * Metoda zwraca konkretną architekturę na podstawie przekazanego parametru
     *
     * @param flag
     * Flaga przekazana na wywołaniu programu
     * @return
     */
    public Architecture selectArchitecture(String flag) {
        Class archClass = architectures.get(flag);
        Architecture arch = null;

        try {
            arch = (Architecture)archClass.getConstructors()[0].newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arch;
    }

    /**
     * Metoda zwracająca ilość potrzebych miejsc w rejestrze zmiennych globalnych
     *
     * @param operations
     * Lista operacji do wykonania
     * @return
     */

    public int getGlobalsMaxIndex(ByteCode[] operations) {
        int maxIndex = 0;

        for (int i=0; i<operations.length; i++) {
            ByteCode addr = operations[i];

            if (addr instanceof GSTORE && i+1<operations.length) {
                ByteCode value = operations[i+1];

                if (value instanceof VALUE) {
                    int globalIndex = ((VALUE) value).value + 1;

                    if (globalIndex > maxIndex) {
                        maxIndex = globalIndex;
                    }
                }
            }
        }

        return maxIndex;
    }
}
