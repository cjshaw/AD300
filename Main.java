/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShawHomework4;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Clint Shaw
 */
public class Main {

    public static void main(String args[]) throws IOException {
        File testFile = new File("testFile.txt");
        StableMarriage sm = new StableMarriage(testFile);
        sm.doCouples();
        System.out.println(sm);
    }

}
