package com.madejm.ByteCodeVM.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mejdej on 20/02/17.
 */
public class Printer {

    private List<String> printerQueue = new ArrayList<>();

    public void addToQueue(String content) {
        printerQueue.add(content);
    }

    public void print() {
        for (String content : this.printerQueue) {
            System.out.println("\u001B[35m\u001B[1m"+content+"\u001B[0m");
        }

        this.printerQueue.clear();
    }

}
