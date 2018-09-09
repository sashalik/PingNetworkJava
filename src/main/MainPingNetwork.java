package main;

import forms.FormMain;

public class MainPingNetwork {

    public static String ip = "127.0.0.1";

    public static void main(String[] args) {

        FormMain fm = new FormMain();
        fm.CreateForm();
      //  CheckConnection cC = new CheckConnection();

      //  System.out.print(cC.Check(ip));
    }
}
