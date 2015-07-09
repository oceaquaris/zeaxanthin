package org.zeatrace;

import java.util.ArrayList;
import java.awt.Color;
import java.util.regex.*;

public class Seed
{
    public static final Color DARK_RED = new Color(102, 0, 0);
    public static final Color COLORLESS = null;
    
    /**
     * Instance data
     */
    private Color pericarp, aleurone, endosperm;
    private ArrayList<Transposon> transposons;

    
    
    public Seed(Color pericarp, Color aleurone, Color endosperm)
    {
        this.pericarp = pericarp;
        this.aleurone = aleurone;
        this.endosperm = endosperm;
        this.transposons = null;
    }
    public Seed(Color pericarp, Color aleurone, Color endosperm, Transposon transposon)
    {
        this.pericarp = pericarp;
        this.aleurone = aleurone;
        this.endosperm = endosperm;
        this.transposons = new ArrayList<Transposon>();
        this.transposons.add(transposon);
    }
    public Seed(Color pericarp, Color aleurone, Color endosperm, Transposon[] transposons)
    {
        this.pericarp = pericarp;
        this.aleurone = aleurone;
        this.endosperm = endosperm;
        this.transposons = new ArrayList<Transposon>();
        for(Transposon tmp : transposons) {
            this.transposons.add(tmp);
        }
    }
    public Seed(Color pericarp, Color aleurone, Color endosperm, ArrayList<Transposon> transposons)
    {
        this.pericarp = pericarp;
        this.aleurone = aleurone;
        this.endosperm = endosperm;
        this.transposons = transposons;
    }
    
    /**
     * Get methods
     */
    public Color getColor_pericarp()
    {
        return this.pericarp;
    }
    public Color getColor_aleurone()
    {
        return this.aleurone;
    }
    public Color getColor_endosperm()
    {
        return this.endosperm;
    }
    public ArrayList<Transposon> getTransposons()
    {
        return this.transposons;
    }
    
    public String getColorString_pericarp()
    {
        return Seed.colorToString(this.pericarp);
    }
    public String getColorString_aleurone()
    {
        return Seed.colorToString(this.aleurone);
    }
    public String getColorString_endosperm()
    {
        return Seed.colorToString(this.endosperm);
    }
    public String getTransposons_String()
    {
        String output = new String("");
        for(int i = 0; i < this.transposons.size()-1; i++) {
            output += this.transposons.get(i).toString();
            output += ", ";
        }
        output += this.transposons.get(this.transposons.size()-1).toString();
        
        return output;
    }
    
    
    
    
    public static String colorToString(Color color)
    {
        if(color == Seed.COLORLESS) {
            return "Colorless";
        }
        else if(color.equals(Color.BLACK)) {
            return "Black";
        }
        else if(color.equals(Color.BLUE)) {
            return "Blue";
        }
        //else if(color.equals(Color.CYAN)) {
        //    return "Cyan";
        //}
        else if(color.equals(Color.DARK_GRAY)) {
            return "Dark Gray";
        }
        else if(color.equals(Color.GRAY)) {
            return "Gray";
        }
        //else if(color.equals(Color.GREEN)) {
        //    return "Green";
        //}
        else if(color.equals(Color.LIGHT_GRAY)) {
            return "Light Gray";
        }
        //else if(color.equals(Color.MAGENTA)) {
        //    return "Magenta";
        //}
        else if(color.equals(Color.ORANGE)) {
            return "Orange";
        }
        else if(color.equals(Color.PINK)) {
            return "Pink";
        }
        else if(color.equals(Color.WHITE)) {
            return "White";
        }
        else if(color.equals(Color.YELLOW)) {
            return "Yellow";
        }
        else if(color.equals(Seed.DARK_RED)) {
            return "Dark Red";
        }
        
        else {
            return "R:" + color.getRed() +
                   "G:" + color.getGreen() +
                   "B:" + color.getBlue();
        }
    }
    
    public static Color StringToColor(String s)
    {
        if(s.equalsIgnoreCase("Colorless")) {
            return Seed.COLORLESS;
        }
        else if(s.equalsIgnoreCase("Black")) {
            return Color.BLACK;
        }
        else if(s.equalsIgnoreCase("Blue")) {
            return Color.BLUE;
        }
        else if(s.equalsIgnoreCase("Dark Gray")) {
            return Color.DARK_GRAY;
        }
        else if(s.equalsIgnoreCase("Gray")) {
            return Color.GRAY;
        }
        else if(s.equalsIgnoreCase("Light Gray")) {
            return Color.LIGHT_GRAY;
        }
        else if(s.equalsIgnoreCase("Orange")) {
            return Color.ORANGE;
        }
        else if(s.equalsIgnoreCase("Pink")) {
            return Color.PINK;
        }
        else if(s.equalsIgnoreCase("White")) {
            return Color.WHITE;
        }
        else if(s.equalsIgnoreCase("Yellow")) {
            return Color.YELLOW;
        }
        else if(s.equalsIgnoreCase("Dark Red")) {
            return Seed.DARK_RED;
        }
        
        else {
            Pattern pattern = Pattern.compile("R:(\\d+)G:(\\d+)B:(\\d+)");
            Matcher matcher = pattern.matcher(s);
            return new Color(Integer.parseInt(matcher.group(1)),
                             Integer.parseInt(matcher.group(2)),
                             Integer.parseInt(matcher.group(3))  );
        }
    }
    
    private static String ArrayList_Transposon_ToString(ArrayList<Transposon> list)
    {
        if(list.size() == 0)
            return "\"\"";
        
        String output = new String("\"");
        
        for(int i = 0; i < list.size()-1; i++) {
            output += list.get(i).toString() + ",";
        }
        output += list.get(list.size()-1).toString() + "\"";
        
        return output;
    }
    
    public String toString()
    {
        String output = new String("\"");
        
        output += Seed.colorToString(this.pericarp) + ",";
        output += Seed.colorToString(this.aleurone) + ",";
        output += Seed.colorToString(this.endosperm) + ",";
        output += Seed.ArrayList_Transposon_ToString(this.transposons);
        output += "\"";
        
        return output;
    }
}