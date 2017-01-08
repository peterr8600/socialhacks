public class SFDataPt
{
    //Part 0
    //Location
    private int x;
    private int y;

    //private int perStop;
    //Stop/Search/Frisk
    private boolean wasArrested;
    private boolean wasSummoned;
    private boolean wasFrisked;
    private boolean wasSearched;
    
    //Contraband (abbv. "con")
    private boolean conFound;
    private boolean conPistol;
    private boolean conRifle;
    private boolean conAssaultWeap;
    private boolean conKnife;
    private boolean conMachineGun;
    private boolean conOther;

    //Physical Force (abbv. "pf")
    private boolean pfUsed;
    private boolean pfHands;
    private boolean pfWall;
    private boolean pfGround;
    private boolean pfWeapDrawn;
    private boolean pfWeapPoint;
    private boolean pfBaton;
    private boolean pfHandcuff;
    private boolean pfPepperSpray;
    private boolean pfOther;
    
    //Suspect Specific Characteristics
    private String race;
    private String borough;
        
    /**private int perObs;
    //Reasons for stops
    private boolean suspObj;
    private boolean fitsDesc;
    private boolean casing;
    private boolean lookout;
    private boolean crimeClothes;
    private boolean drugTransSusp;
    private boolean other;
    //Reasons for frisks
    private boolean suspViolCrime;
    private boolean otherSuspWeap;
    private boolean inappAttire;
    private boolean incompliance;
    private boolean verbThreats;
    private boolean priorCrimeBehav;
    //Reasons for stops AND frisks
    private boolean engViolCrime;
    private boolean furtMove;
    private boolean suspBulge;**/
    
    /**
     * Constructor for a SFDataPt.
     * 
     * @param row An array of Strings that represents a SINGLE row of the 
     * Stop and Frisk Data Set. Each String in the array is the value of a
     * single column. See the Stop and Frisk Data Specification to
     * determine which index in the String corresponds to which column in
     * the data.
     */
    public SFDataPt(String[] row)
    {
        //Part 0
	//Location
	x = Integer.parseInt(row[107]);
	y = Integer.parseInt(row[108]);
	//Stop/Search/Frisk
        wasArrested = row[13].equals("Y");
        wasSummoned = row[15].equals("Y");
	wasFrisked = row[22].equals("Y");
	wasSearched = row[23].equals("Y");
	//Contraband
	conFound = row[24].equals("Y");
	conPistol = row[26].equals("Y");
	conRifle = row[27].equals("Y");
	conAssaultWeap = row[28].equals("Y");
	conKnife = row[29].equals("Y");
	conMachineGun = row[30].equals("Y");
	conOther = row[31].equals("Y");
	//Physical Force
	pfHands = row[32].equals("Y");
	pfWall = row[33].equals("Y");
	pfGround = row[34].equals("Y");
	pfWeapDrawn = row[35].equals("Y");
	pfWeapPoint = row[36].equals("Y");
	pfHandcuff = row[37].equals("Y");
	pfPepperSpray = row[38].equals("Y");
	pfOther = row[39].equals("Y");
	pfUsed = pfHands || pfWall || pfGround || pfWeapDrawn || pfWeapPoint ||
	    pfHandcuff || pfPepperSpray || pfOther;
	//Suspect Specific Characteristics
	race = row[81];
	borough = row[100];
    }
    
    /**
     * Returns a String representation of Stop and Frisk data point. 
     */
    public String toString()
    {
        //return 
	//"period of stop: " + perStop + "\n" +
        return "arrest made?: " + wasArrested + "\n" +
        "summoned?: " + wasSummoned + "\n" +
        "physical force used?: " + pfUsed + "\n" +
        "race of suspect: " + race + "\n" +
        "borough: " + borough + "\n" +
        "x-coordiante: " + x + "\n" +
	    "y-coordinate: " + y + "\n";
    }
    
    //Part 0
    /**public int getPerStop(){
        return perStop;
	}**/
    
    public boolean wasArrested(){
        return wasArrested;
    }
    
    public boolean wasSummoned(){
        return wasSummoned;
    }
    
    public boolean wasFrisked(){
	return wasFrisked;
    }

    public boolean wasSearched(){
	return wasSearched;
    }

    public boolean wasCharged(){
        return wasSummoned || wasArrested;
    }

    public boolean conFound(){
	return conFound;
    }

    public boolean conPistol(){
	return conPistol;
    }

    public boolean conRifle(){
	return conRifle;
    }

    public boolean conAssaultWeap(){
	return conAssaultWeap;
    }

    public boolean conKnife(){
	return conKnife;
    }

    public boolean conMachineGun(){
	return conMachineGun;
    }

    public boolean conOther(){
	return conOther;
    }
    
    public boolean pfUsed(){
        return pfUsed;
    }

    public boolean pfHands(){
	return pfHands;
    }

    public boolean pfWall(){
	return pfWall;
    }

    public boolean pfGround(){
	return pfGround;
    }

    public boolean pfWeapDrawn(){
	return pfWeapDrawn;
    }

    public boolean pfWeapPoint(){
	return pfWeapPoint;
    }

    public boolean pfBaton(){
	return pfBaton;
    }

    public boolean pfHandcuff(){
	return pfHandcuff;
    }

    public boolean pfPepperSpray(){
	return pfPepperSpray;
    }

    public boolean pfOther(){
	return pfOther;
    }
    
    public String getRace(){
        return race;
    }
    
    public String getBorough(){
        return borough;
    }
    
    public boolean inBorough(String b){
        return b.indexOf(borough) != -1;
    }
    
    /**
     * Accessor for xcoord.
     * 
     * Returns the x-coordinate of the stop and Frisk data point.
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Accessor for ycoord.
     * 
     * Returns the y-coordinate of the Stop and Frisk data point.
     */
    public int getY()
    {
        return y;
    }
    
    //Part 1
    /**public int getPerObs(){
        return perObs;
    }
    
    public boolean getSuspObj(){
        return suspObj;
    }
    
    public boolean getFitsDesc(){
        return fitsDesc;
    }
    
    public boolean getCasing(){
        return casing;
    }
    
    public boolean getLookout(){
        return lookout;
    }
    
    public boolean getCrimeClothes(){
        return crimeClothes;
    }
    
    public boolean getDrugTransSusp(){
        return drugTransSusp;
    }
    
    public boolean getOther(){
        return other;
    }
    
    public boolean getSuspViolCrime(){
        return suspViolCrime;
    }
    
    public boolean getOtherSuspWeap(){
        return otherSuspWeap;
    }
    
    public boolean getInappAttire(){
        return inappAttire;
    }
    
    public boolean getIncompliance(){
        return incompliance;
    }
    
    public boolean getVerbThreats(){
        return verbThreats;
    }
    
    public boolean getPriorCrimeBehav(){
        return priorCrimeBehav;
    }
    
    public boolean getEngViolCrime(){
        return engViolCrime;
    }
    
    public boolean getFurtMove(){
        return furtMove;
    }
    
    public boolean getSuspBulge(){
        return suspBulge;
	}**/
    
    //Part 2
    /**public int getDate(){
        return dateStop;
    }
    
    public int getMonth(){
        return dateStop / 1000000;
    }
    
    public int getYear(){
        return dateStop % 10000;
	}**/
    
    /**
     * Quick test of SFDataPt
     */
    public static void main(String[] args)
    {
        String[] exInputData = new String[109];
        exInputData[10] = "5";
        exInputData[14] = "N";
        exInputData[16] = "Y";
        exInputData[81] = "B";
        exInputData[100] = "BROOKLYN";
        exInputData[107] = "35";
        exInputData[108] = "57";
        
        SFDataPt testPt = new SFDataPt(exInputData);
        /*
        System.out.println("perStop = " + testPt.getPerStop());
        System.out.println("Was arrested = " + testPt.wasArrested());
        System.out.println("Was summoned = " + testPt.wasSummoned());
        System.out.println("Race = " + testPt.getRace());
        System.out.println("Borough = " + testPt.getBorough());
        System.out.println("y = " + testPt.getY());
        System.out.println("x = " + testPt.getX());
        System.out.println(testPt);
        */
    }
}