package ShawHomework4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Program that solves the classic stable marriage or partner problem.
 *
 * @author Clint Shaw
 * @version 6/11/2017
 */
public class StableMarriage {

    private Map<String, ArrayList<String>> womanPref; // holds name and list of preferences
    private Map<String, ArrayList<String>> manPref;
    private List<String> women; // women and men in a list
    private List<String> men;
    private Map<String, String> marriageMap; // holds man and woman pairs

    public StableMarriage(File file) throws FileNotFoundException, IOException {
        womanPref = new TreeMap<>();
        manPref = new TreeMap<>();
        men = new ArrayList<>();
        women = new ArrayList<>();
        boolean switchGender = false;

        BufferedReader mbr = new BufferedReader(new FileReader(file));

        String line = null;
        while ((line = mbr.readLine()) != null) {
            if (line.trim().equals("")) {
                switchGender = true;
                line = mbr.readLine();
            }
            if (!switchGender) {
                String split[];// list for holding     
                split = line.split(":");
                String manName = split[0];
                this.men.add(manName);
                String tempPref = split[1];
                String[] temp = tempPref.split(" ");    //Split by space
                this.manPref.put(manName, new ArrayList<>());
                this.manPref.get(manName).addAll(Arrays.asList(temp)); // add each preference to array list in order
            } else {
                String split[];// list for holding     
                split = line.split(":");
                String womanName = split[0];
                this.women.add(womanName);
                String tempPref = split[1];
                String[] temp = tempPref.split(" ");    //Split by space
                this.womanPref.put(womanName, new ArrayList<>());
                this.womanPref.get(womanName).addAll(Arrays.asList(temp)); // add each preference to array list in order
            }
        }
    }

    public Map<String, String> doCouples() {
        this.marriageMap = new TreeMap<>(); // maps women to men.
        List<String> singleMen = new LinkedList<>();
        singleMen.addAll(men); // makes all men free

        //Loops while there are men still available
        while (!singleMen.isEmpty()) {
            String currMan = singleMen.get(0);
            singleMen.remove(0);
            List<String> currManPref = manPref.get(currMan);

            for (String currWoman : currManPref) {
                currWoman = "Woman " + currWoman;
                if (this.marriageMap.get(currWoman) == null) {
                    this.marriageMap.put(currWoman, currMan);
                    break;
                } else {
                    String diffMan = this.marriageMap.get(currWoman);
                    String[] split = diffMan.split(" ");
                    diffMan = split[1];
                    String[] split2 = currMan.split(" ");
                    currMan = split2[1];
                    List<String> currWomanPrefs = womanPref.get(currWoman);

                    if (currWomanPrefs.indexOf(currMan) < currWomanPrefs.indexOf(diffMan)) {
                        currMan = "Man " + currMan;
                        this.marriageMap.put(currWoman, currMan);
                        diffMan = "Man " + diffMan;
                        singleMen.add(diffMan);
                        break;
                    } else {
                        currMan = "Man " + currMan;
                        diffMan = "Man " + diffMan;
                    }
                }
            }
        }
        return this.marriageMap;
    }

    @Override
    public String toString() {
        return "StableMarriage{" + "womanPref=" + womanPref
                + ", \nmanPref=" + manPref + ", \nwomen=" + women
                + ", \nmen=" + men + ", \nmarriageMap=" + marriageMap + '}';
    }

}
