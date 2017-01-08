public class SFStatistics{
    //Order of races
    //Amer Ind/Ala Nat
    //Asian/Pac Isl
    //Black
    //Black-Hisp
    //White
    //White-Hisp
    //Unknown
    //Other
    
    private static int[] manhattan = new int[8];
    private static int[] brooklyn = new int[8];
    private static int[] bronx = new int[8];
    private static int[] queens = new int[8];
    private static int[] staten_island = new int[8];

    public static void populate(){
	double[] manhattanPer = {0.012, 0.13, 0.178, 0.004, 0.468, 0.176, 0.002, 0.030};
	for(int i = 0; i < manhattan.length; i++){
	    //System.out.println("i == " + i + manhattan[i]);
	    manhattan[i] = (int)(manhattanPer[i] * 1644518);
	}

	double[] brooklynPer = {0.01, 0.125, 0.333, 0.015, 0.359, 0.134, 0.004, 0.02};
	for(int i = 0; i < brooklyn.length; i++){
	    brooklyn[i] = (int)(brooklynPer[i] * 2636735);
	}

	double[] bronxPer = {0.029, 0.049, 0.414, 0.02, 0.101, 0.353, 0.004, 0.03};
	for(int i = 0; i < bronx.length; i++){
	    bronx[i] = (int)(bronxPer[i] * 1455444);
	}
	double[] queensPer = {0.013, 0.265, 0.191, 0.015, 0.258, 0.229, 0.003, 0.025};
	for(int i = 0; i < queens.length; i++){
	    queens[i] = (int)(queensPer[i] * 2339150);
	}
	double[] staten_islandPer = {0.006, 0.087, 0.006, 0.113, 0.62, 0.147, 0.002, 
			 0.018};
	for(int i = 0; i < staten_island.length; i++){
	    staten_island[i] = (int)(staten_islandPer[i] * 474558);
	}
    }

    public static void main(String[] args){
	
    }

    //Returns a string that describes percent of people of one race stopped vs.
    //that race's percentage representation in that city.
    public static String getPctPop(SFDataPt[] dataset, String raceLong,  String r, 
				   String[] boroughs){
	populate();
	boroughs = SFVisualizer.toUpperCase(boroughs);
	
	//Dataset that is filtered to include only people of a race that were 
	//stopped and frisked.
	SFDataPt[] filtByBorough =
	    SFVisualizer.filterByBoroughs(dataset, boroughs);

	String[] race = new String[1];
	race[0] = r;
	SFDataPt[] filtByRace = SFVisualizer.filterByRaces(filtByBorough, race);

	String[] SV = {"wasFrisked"};
	SFDataPt[] filtByRaceSF =
	    SFVisualizer.filterBySearchVar(filtByRace, SV);

	SFDataPt[] filtBySF = SFVisualizer.filterBySearchVar(filtByBorough, SV);
		
	double pctSF = (double)filtByRaceSF.length / filtBySF.length * 100;

	String[] WA = {"wasArrested"};
	SFDataPt[] filtByRaceWA = SFVisualizer.filterBySearchVar(filtByRace, WA);
        
	double pctWA = (double)filtByRaceWA.length / filtByRace.length * 100;

	if(filtByRace.length == 0){
	    pctWA = 0;
	}

	int rPop = calculatePop(boroughs, getIndexOfRace(r));
	
	int totPop = getTotalPop(boroughs);
	double pctPop = (double)rPop / totPop * 100;

	return "Race: " + raceLong + "\n" +
	    "Borough(s): " + toString(boroughs) + "\n" +
	    "Population of race in that borough: " + pctPop + "%" + "\n" +
	    "Stop and Frisk by race: " + pctSF + "%" +  "\n" +
	    "Chances of arrest: " + pctWA + "%" + "\n";
    }

    public static int arrAdd(int[] arr){
	int n = 0;
	for(int x: arr){
	    n += x;
	}
	return n;
    }

    public static int getTotalPop(String[] boroughs){
	int n = 0;
	if(SFVisualizer.isInArr(boroughs, "MANHATTAN")){
	    n += arrAdd(manhattan);
	    //System.out.println(arrAdd(manhattan));
	}
	else if(SFVisualizer.isInArr(boroughs, "BROOKLYN")){
	    n += arrAdd(brooklyn);
	}
	else if(SFVisualizer.isInArr(boroughs, "BRONX")){
	    n += arrAdd(bronx);
	}
	else if(SFVisualizer.isInArr(boroughs, "QUEENS")){
	    n += arrAdd(queens);
	}
	else if(SFVisualizer.isInArr(boroughs, "STATEN ISLAND")){
	    n += arrAdd(staten_island);
	}
	return n;
    }

    public static int calculatePop(String[] boroughs, int idx){
	int n = 0;
	if(SFVisualizer.isInArr(boroughs, "MANHATTAN")){
	    n += manhattan[idx];
	}
	else if(SFVisualizer.isInArr(boroughs, "BROOKLYN")){
	    n += brooklyn[idx];
	}
	else if(SFVisualizer.isInArr(boroughs, "BRONX")){
	    n += bronx[idx];
	}
	else if(SFVisualizer.isInArr(boroughs, "QUEENS")){
	    n += queens[idx];
	}
	else if(SFVisualizer.isInArr(boroughs, "STATEN ISLAND")){
	    n += staten_island[idx];
	}
	return n;
    }
    
    public static int getIndexOfRace(String race){
	if(race.equals("I")){
	    return 0;
	}
	else if(race.equals("A")){
	    return 1;
	}
	else if(race.equals("B")){
	    return 2;
	}
	else if(race.equals("P")){
	    return 3;
	}
	else if(race.equals("W")){
	    return 4;
	}
	else if(race.equals("Q")){
	    return 5;
	}
	else if(race.equals("X")){
	    return 6;
	}
	else return 7;
    }

    public static String getSFToArrest(SFDataPt[] dataset, String r){
	String[] race = new String[1];
	race[0] = r;
	SFDataPt[] filtByRace = SFVisualizer.filterByRaces(dataset, race);
	
	String[] SV0 = {"wasArrested", "wasFrisked"};
	SFDataPt[] filtByRaceArrestedSF = 
	    SFVisualizer.filterBySearchVar(filtByRace, SV0);

	String[] SV1 = {"wasFrisked"};
	SFDataPt[] filtBySF = SFVisualizer.filterBySearchVar(filtByRace, SV1);
	
	double pctLedToArrest = filtByRaceArrestedSF.length/filtBySF.length;
	return "Race: " + r +
	    "% of stop and frisks that led to arrests: " + pctLedToArrest;
    }
    
    public static String toString(String[] arr){
	String s = "";
	for(int i = 0; i < arr.length; i++){
	    s = s + arr[i] + (i == arr.length - 1? "": ", ");
	}
	return s;
    }
}