package com.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class SymmetricEncryption {

    public static int[] encryptPin(int[] pin){
        int[] _encryptedPin = new int[pin.length];
        int[] _encryptedPinCopy = new int[pin.length];

        for (int i=0; i < pin.length; i ++){
            int mod;
            mod = (pin[i] + 7 ) % 10;
            _encryptedPin[i]= mod;
        }

        _encryptedPinCopy[1]=_encryptedPin[3];
        _encryptedPinCopy[0]=_encryptedPin[2];
        _encryptedPinCopy[3]=_encryptedPin[1];
        _encryptedPinCopy[2]=_encryptedPin[0];



        return _encryptedPinCopy;


    }

    public static int[] decryptPin(int[] pin){
        int[] _decryptedPin = new int[pin.length];
        int[] _decryptedPinCopy = new int[pin.length];


        for (int i=0; i < pin.length; i ++){
            int mod;
            mod = (pin[i] + 10 ) - 7;

            _decryptedPin[i]= mod;
        }

        _decryptedPinCopy[3]=_decryptedPin[1];
        _decryptedPinCopy[2]=_decryptedPin[0];
        _decryptedPinCopy[1]=_decryptedPin[3];
        _decryptedPinCopy[0]=_decryptedPin[2];
        return _decryptedPinCopy;


    }

   
}
