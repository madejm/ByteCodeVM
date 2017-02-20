package com.madejm.ByteCodeVM;

import com.madejm.ByteCodeVM.BusinessLogic.ArchitectureSelector;
import com.madejm.ByteCodeVM.BusinessLogic.TextParser;
import com.madejm.ByteCodeVM.BusinessLogic.VM;
import com.madejm.ByteCodeVM.BusinessObjects.Interfaces.Architecture;
import com.madejm.ByteCodeVM.BusinessObjects.Models.ByteCode;
import com.madejm.ByteCodeVM.BusinessLogic.Preprocessor;

public class Main {

    // args[0] file
    // args[1] architecture

    public static void main(String[] args) {
        ByteCode[] operations;

        Architecture arch = ArchitectureSelector.get(args[1]);

        TextParser parser = new TextParser();
        parser.setStrategicArchitecture(arch);

        try {
            operations = parser.parse(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        int nglobals = Preprocessor.getGlobalsMaxIndex(operations);

        VM vm = new VM(operations, 0, nglobals);
        vm.trace = true;
        vm.exec();
        vm.dumpCodeMemory();
    }
}
