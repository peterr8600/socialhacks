//Import to show errors with files
import java.io.IOException;
//Import to draw Colors
import java.awt.Color;

public class SFVisualizer
{   
    public static void visualizeData(String[] r, String[] b, String[] s) 
	throws IOException
    {        
	SFDataPt[] dataset = 
	    SFFileParser.parseFile("2014_cleaned.csv");
	String[] boroughs = b;
	if(boroughs.length == 0){
	    String[] boroughs1 = {"MANHATTAN", "BROOKLYN", "BRONX", "QUEENS", 
			"STATEN ISLAND"};
	    boroughs = boroughs1;
	}

	String[] races = convertRace(r);
	if(races.length == 0){
	    String[] races1 = {"I", "A", "B", "P", "W", "Q", "X", "Z"};
	    races = races1;
	}

	String[] searchVariables = s;
       	
	SFDataPt[] filtDataset = filterByRaces(dataset, races);
	filtDataset = filterByBoroughs(filtDataset, boroughs);
	filtDataset = filterBySearchVar(filtDataset, searchVariables);
	
	//StdDraw SetScales
	int minX = SFVisualizer.getMinXCoord(dataset);
	int maxX = SFVisualizer.getMaxXCoord(dataset);
	int minY = SFVisualizer.getMinYCoord(dataset);
	int maxY = SFVisualizer.getMaxYCoord(dataset);
	StdDraw.setXscale(minX, maxX);
	StdDraw.setYscale(minY, maxY);
	
	for(int i = 0; i < races.length && i < r.length; i++){
	    System.out.println(SFStatistics.getPctPop(dataset, r[i], races[i], boroughs));
	}

	SFVisualizer.drawData(filtDataset, StdDraw.BLACK, 1000);
    }
    
    public static void drawData(SFDataPt[] dataSet, Color c, int timeMil)
    {
        StdDraw.show(0);
        StdDraw.clear();
        StdDraw.setPenColor(c);
        for(int i = 0; i < dataSet.length; i++)
	    {
		StdDraw.point(dataSet[i].getX(), dataSet[i].getY());
	    }
        StdDraw.show(timeMil);
    }
        
    public static SFDataPt[] filterByBoroughs(SFDataPt[] dataset, 
					      String[] boroughs){
	boroughs = toUpperCase(boroughs);
	
	int countBorough = 0;
	for(int i = 0; i < dataset.length; i++){
	    if(isInArr(boroughs, dataset[i].getBorough())){
		countBorough++;
	    }
	}

	SFDataPt[] filtArr = new SFDataPt[countBorough];
	for(int i = 0, x = 0; i < dataset.length; i++){
	    if(isInArr(boroughs, dataset[i].getBorough())){
		filtArr[x] = dataset[i];
		x++;
	    }
	}
	return filtArr;
    }

    public static String[] toUpperCase(String[] b){
	String[] arr = new String[b.length];
	for(int i = 0; i < arr.length; i++){
	    arr[i] = b[i].toUpperCase();
	}
	return arr;
    }

    public static SFDataPt[] filterByRaces(SFDataPt[] dataset, String[] races){
	int countRace = 0;
	for(int i = 0; i < dataset.length; i++){
	    if(isInArr(races, dataset[i].getRace())){
		countRace++;
	    }
	}

	SFDataPt[] filtArr = new SFDataPt[countRace];
	for(int i = 0, x = 0; i < dataset.length; i++){
	    if(isInArr(races, dataset[i].getRace())){
		filtArr[x] = dataset[i];
		x++;
	    }
	}
	return filtArr;
    }
	
    public static SFDataPt[] filterBySearchVar(SFDataPt[] dataset, 
					      String[] SV){
	if(SV.length == 0) {
		return dataset;
	}
	
	int countSV = 0;
	for(int i = 0; i < dataset.length; i++){
	   if(isInArr(SV, "wasArrested") && dataset[i].wasArrested()){	       
		countSV++;

	   }
	   else if(isInArr(SV, "wasSummoned") && dataset[i].wasSummoned()){
		countSV++;
	   }
	   else if(isInArr(SV, "wasFrisked") && dataset[i].wasFrisked()){
		countSV++;
	   }
	   else if(isInArr(SV, "wasSearched") && dataset[i].wasSearched()){
		countSV++;
	   }
	   else if(isInArr(SV, "conFound") && dataset[i].conFound()){
		countSV++;
	   }
	   else if(isInArr(SV, "conPistol") && dataset[i].conPistol()){
		countSV++;
	   }
	   else if(isInArr(SV, "conRifle") && dataset[i].conRifle()){
		countSV++;
	   }
	   else if(isInArr(SV, "conAssaultWeap") && 
		   dataset[i].conAssaultWeap()){
		countSV++;
	   }
	   else if(isInArr(SV, "conKnife") && dataset[i].conKnife()){
		countSV++;
	   }
	   else if(isInArr(SV, "conMachineGun") && 
		   dataset[i].conMachineGun()){
		countSV++;
	   }
	   else if(isInArr(SV, "conOther") && dataset[i].conOther()){
		countSV++;
	   }
	   else if(isInArr(SV, "pfHands") && dataset[i].pfHands()){
		countSV++;
	   }
	   else if(isInArr(SV, "pfWall") && dataset[i].pfWall()){
		countSV++;
	   }
	   else if(isInArr(SV, "pfGround") && dataset[i].pfGround()){
		countSV++;
	   }
	   else if(isInArr(SV, "pfHandcuff") && dataset[i].pfHandcuff()){
		countSV++;
	   }
	   else if(isInArr(SV, "pfPepperSpray") && 
		   dataset[i].pfPepperSpray()){
		countSV++;
	   }
	   else if(isInArr(SV, "pfOther") && dataset[i].pfOther()){
		countSV++;
	   }
	   else if(isInArr(SV, "pfUsed") && dataset[i].pfUsed()){
		countSV++;
	   }
	}
	SFDataPt[] filtArr = new SFDataPt[countSV];
	for(int i = 0, x = 0; i < dataset.length; i++){
	   if(isInArr(SV, "wasArrested") && dataset[i].wasArrested()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "wasSummoned") && dataset[i].wasSummoned()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "wasFrisked") && dataset[i].wasFrisked()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "wasSearched") && dataset[i].wasSearched()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "conFound") && dataset[i].conFound()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "conPistol") && dataset[i].conPistol()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "conRifle") && dataset[i].conRifle()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "conAssaultWeap") && 
		   dataset[i].conAssaultWeap()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "conKnife") && dataset[i].conKnife()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "conMachineGun") && 
		   dataset[i].conMachineGun()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "conOther") && dataset[i].conOther()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "pfHands") && dataset[i].pfHands()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "pfWall") && dataset[i].pfWall()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "pfGround") && dataset[i].pfGround()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "pfHandcuff") && dataset[i].pfHandcuff()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "pfPepperSpray") &&
		   dataset[i].pfPepperSpray()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "pfOther") && dataset[i].pfOther()){
		filtArr[x] = dataset[i];
		x++;
	   }
	   else if(isInArr(SV, "pfUsed") && dataset[i].pfUsed()){
		filtArr[x] = dataset[i];
		x++;
	   }
	}
	return filtArr;
    }


    public static boolean isInArr(String[] arr, String s){
	for(int i = 0; i < arr.length; i++){
	    if(arr[i].equals(s)){
		return true;
	    }
	}
	return false;
    }

    public static String[] convertRace(String[] big){
	String[] small = new String[big.length];
	for(int i = 0; i < small.length; i++){
	    small[i] = convertRace(big[i]);
	}
	return small;
    }

    public static String convertRace(String big){
	big = big.toUpperCase();
	if(big.equals("AMERICAN INDIAN/ALASKAN NATIVE")){
	    return "I";
	}
	else if (big.equals("ASIAN/PACIFIC ISLANDER")){
	    return "A";
	}
	else if(big.equals("BLACK")){
	    return "B";
	}
	else if(big.equals("BLACK-HISPANIC")){
	    return "P";
	}
	else if(big.equals("WHITE")){
	    return "W";
	}
	else if(big.equals("WHITE-HISPANIC")){
	    return "Q";
	}
	else if(big.equals("UNKNOWN")){
	    return "X";
	}
	return "Z";
    }
    
    public static int getMinXCoord(SFDataPt[] dataSet)
    {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < dataSet.length; i++){
            min = Math.min(min, dataSet[i].getX());
        }
        return min;
    }
    
    public static int getMinYCoord(SFDataPt[] dataSet)
    {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < dataSet.length; i++){
            min = Math.min(min, dataSet[i].getY());
        }
        return min;
    }
    
    public static int getMaxXCoord(SFDataPt[] dataSet)
    {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < dataSet.length; i++){
            max = Math.max(max, dataSet[i].getX());
        }
        return max;
    }
    
    public static int getMaxYCoord(SFDataPt[] dataSet)
    {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < dataSet.length; i++){
            max = Math.max(max, dataSet[i].getY());
        }
        return max;
    }
}
