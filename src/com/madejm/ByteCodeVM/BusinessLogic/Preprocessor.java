package com.madejm.ByteCodeVM.BusinessLogic;

import com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode;
import com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode.*;

/**
 * Created by mejdej on 20/02/17.
 */
public class Preprocessor {

    public static int getGlobalsMaxIndex(ByteCode[] operations) {
        int maxIndex = 0;

        for (int i=0; i<operations.length; i++) {
            ByteCode addr = operations[i];

            if (addr instanceof GSTORE && i+1<operations.length) {
                ByteCode value = operations[i+1];

                if (value instanceof VALUE) {
                    int globalIndex = ((VALUE) value).value;

                    if (globalIndex > maxIndex) {
                        maxIndex = globalIndex;
                    }
                }
            }
        }

        return maxIndex;
    }
}
