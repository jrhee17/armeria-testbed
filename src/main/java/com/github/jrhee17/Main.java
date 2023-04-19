package com.github.jrhee17;

import com.linecorp.armeria.common.Flags;

public class Main {
    public static void main(String[] args) {
        System.out.println(Flags.requestContextStorageProvider());
    }
}
