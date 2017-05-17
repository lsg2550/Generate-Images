/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.parse;

import java.io.File;
import java.util.List;

/**
 *
 * @author Luis
 */
public class TryParse {

    public static boolean TryListOfFileArray(Object testObject) {
        try {
            List<File[]> name = (List<File[]>) testObject;
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public static boolean TryFileArray(Object testObject) {
        try {
            File[] name = (File[]) testObject;
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }
}
