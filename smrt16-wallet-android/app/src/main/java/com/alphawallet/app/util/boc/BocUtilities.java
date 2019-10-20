package com.alphawallet.app.util.boc;

import java.util.ArrayList;

public class BocUtilities {
    public ArrayList<Account> accountList = new ArrayList<>();
    public ArrayList<String> accountListNames = new ArrayList<>();
    public ArrayList<String> accountListID = new ArrayList<>();

    private static BocUtilities instsance = null;


    public static BocUtilities getInstance(){
        if(instsance == null) {
            instsance = new BocUtilities();
        }
        return instsance;
    }

}
