package com.madejm.ByteCodeVM.BusinessLogic;

import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.Architecture;
import com.madejm.ByteCodeVM.BusinessObjects.Models.Architectures.*;

import java.util.HashMap;

/**
 * Created by mejdej on 20/02/17.
 */
public class ArchitectureSelector {

    private static HashMap<String, Class> architectures = new HashMap<String, Class>() {{
        put("-std", ArchitectureStandard.class);
        put("-ext", ArchitectureExtended.class);
    }};

    public static Architecture get(String flag) {
        Class archClass = architectures.get(flag);
        Architecture arch = null;

        try {
            arch = (Architecture)archClass.getConstructors()[0].newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arch;
    }

}
