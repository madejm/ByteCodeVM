package com.madejm.ByteCodeVM;

import com.madejm.ByteCodeVM.BusinessLogic.TextParser;
import com.madejm.ByteCodeVM.BusinessLogic.VM;
import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.Architecture;
import com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode;
import com.madejm.ByteCodeVM.BusinessLogic.UtilityProcessor;

public class Main {

    /**
     * @param args
     * args[0] ścieżka do pliku z kodem
     * args[1] flaga wykorzystanej architektury
     */

    public static void main(String[] args) {
        ByteCode[] operations;

        Architecture arch = UtilityProcessor.getInstance().selectArchitecture(args[1]);

        TextParser parser = new TextParser();
        parser.setStrategicArchitecture(arch);

        try {
            operations = parser.parse(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        VM vm = new VM(operations, 0);
        vm.trace = true;
        vm.exec();
        vm.dumpCodeMemory();
    }
}
