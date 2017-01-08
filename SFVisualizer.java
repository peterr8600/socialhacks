//Import to show errors with files
import java.io.IOException;
//Import to draw Colors
import java.awt.Color;

public class SFVisualizer
{   
    public static void main(String[] arg) throws IOException{
		String[] races = {"Black"};
		for(String hi : races){
			System.out.println(hi);
		}
			
		visualizeData(new String[0], new String[0], new String[0]);
    }

    /**
     * Reads in a Stop and Frisk data set from a file and visualizes
     * the data set.
     */
    public static void visualizeData(String[] r, String[] b, String[] s) throws IOException
    {        
        // Get all the data from the file
        //SFDataPt[] dataSet2013 = SFFileParser.parseFile("2013_cleaned.csv");
        //SFDataPt[] dataSet2014 = SFFileParser.parseFile("2014_cleaned.csv");
	SFDataPt[] dataset = 
	    SFFileParser.parseFile("2014_cleaned.csv");
	String[] boroughs = b;
	String[] races = r;
	String[] searchVariables = s;
        //SFDataPt[] dataSet = appendArr(dataSet2013, dataSet2014);
        
        // New dataSet with only blacks and hispanics
        //SFDataPt[] filteredDataset = filterByRaces(dataset, races);
		//System.out.println(filteredDataset.length);
		//filteredDataset = filterByBoroughs(filteredDataset, boroughs);
		//filteredDataset = filterBySearchVar(filteredDataset, searchVariables);
		SFDataPt[] datasetFiltByRace = filterByRaces(dataset, races);
	
	//StdDraw SetScales
		int minX = SFVisualizer.getMinXCoord(dataset);
        int maxX = SFVisualizer.getMaxXCoord(dataset);
        int minY = SFVisualizer.getMinYCoord(dataset);
        int maxY = SFVisualizer.getMaxYCoord(dataset);
        StdDraw.setXscale(minX, maxX);
        StdDraw.setYscale(minY, maxY);


	    //SFVisualizer.drawData(datasetFiltByRace, StdDraw.BLACK, 1000);
		//StdDraw.save("image/image0.png");
        SFVisualizer.drawData(datasetFiltByRace, StdDraw.BLACK, 1000);
		//SFVisualizer.drawData(datasetFiltByRace, StdDraw.GREEN, 1000);
		//StdDraw.save("image/image1.png");

    }
    
    /**public static SFDataPt[] filterByMY(SFDataPt[] dataSet, int month, 
					int year){
        int count = 0;
        for(int i = 0; i < dataSet.length; i++){
            if(dataSet[i].getMonth() == month && dataSet[i].getYear() == year){
                count++;
            }
        }
        
        SFDataPt[] retArr = new SFDataPt[count];
        for(int i = 0, x = 0; i < dataSet.length; i++){
            if(dataSet[i].getMonth() == month && dataSet[i].getYear() == year){
                retArr[x] = dataSet[i];
                x++;
            }
        }
        return retArr;
	}**/
    
    public static SFDataPt[] appendArr(SFDataPt[] arr1, SFDataPt[] arr2){
        SFDataPt[] retArr = new SFDataPt[arr1.length + arr2.length];
        for(int i = 0; i < arr1.length; i++){
            retArr[i] = arr1[i];
        }
        for(int i = 0; i < arr2.length; i++){
            retArr[arr1.length + i] = arr2[i];
        }
        
        return retArr;
    }
    
    /**
     * Given an array of SFDataPts, a color, and a time (measured in
     * milliseconds), draw the points on the cavnas.
     */
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
    
    /**
     * Given an array of SFDataPts and a borough, returns an array of only the
     * SFDataPts that occurred in that boroguh.
     * 
     * @param dataSet an array of SFDataPts
     * @param borough the filtering borough
     *   Precondition: borough should be "BRONX", "BROOKLYN", "MANHATTAN",
     *   "QUEENS", or "STATEN ISLAND"
     */
    /**public static SFDataPt[] filterByBorough(SFDataPt[] dataSet, String borough)
    {
        if(borough.isEmpty()){
            return dataSet;
        }
        
        int countBorough = 0;
        for(int i = 0; i < dataSet.length; i++){
            if(dataSet[i].inBorough(borough)){
                countBorough++;
            }
        }
        
        SFDataPt[] filtArr = new SFDataPt[countBorough];
        for(int i = 0, x = 0; i < dataSet.length; i++){
            if(dataSet[i].inBorough(borough)){
                filtArr[x] = dataSet[i];
                x++;
            }
        }
        return filtArr;
	}**/

    public static SFDataPt[] filterByBoroughs(SFDataPt[] dataset, 
					      String[] boroughs){
	if(boroughs.length == 0){
	    return dataset;
	}
	
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

    public static SFDataPt[] filterByRaces(SFDataPt[] dataset, String[] races){
	String[] shortRaces = convertRace(races);

	if(races.length == 0){
	    return dataset;
	}

	int countRace = 0;
	for(int i = 0; i < dataset.length; i++){
	    if(isInArr(shortRaces, dataset[i].getRace())){
		countRace++;
	    }
	}

	SFDataPt[] filtArr = new SFDataPt[countRace];
	for(int i = 0, x = 0; i < dataset.length; i++){
	    if(isInArr(shortRaces, dataset[i].getRace())){
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
	//Amer Indian/Alaskan Nat, Asian/Pac Islander, Black, Black-Hisp, 
	//White, White-Hisp, Unknown, Other
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
    
    /**
     * Returns the minimum x-coordinate contained in the dataSet.
     * 
     * @param dataSet An array of SFDataPts
     */
    public static int getMinXCoord(SFDataPt[] dataSet)
    {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < dataSet.length; i++){
            min = Math.min(min, dataSet[i].getX());
        }
        return min;
    }
    
    /**
     * Returns the minimum y-coordinate contained in the dataSet.
     * 
     * @param dataSet An array of SFDataPts
     */
    public static int getMinYCoord(SFDataPt[] dataSet)
    {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < dataSet.length; i++){
            min = Math.min(min, dataSet[i].getY());
        }
        return min;
    }
    
    /**
     * Returns the maximum x-coordinate contained in the dataSet.
     * 
     * @param dataSet An array of SFDataPts
     */
    public static int getMaxXCoord(SFDataPt[] dataSet)
    {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < dataSet.length; i++){
            max = Math.max(max, dataSet[i].getX());
        }
        return max;
    }
    
    /**
     * Returns the maximum y-coordinate contained in the dataSet.
     * 
     * @param dataSet An array of SFDataPts
     */
    public static int getMaxYCoord(SFDataPt[] dataSet)
    {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < dataSet.length; i++){
            max = Math.max(max, dataSet[i].getY());
        }
        return max;
    }
}