    //Import to show errors with files
import java.io.IOException;
//Import to draw Colors
import java.awt.Color;

public class SFVisualizerTwo
{
    SFDataPt[] dSet;
    String origDSetName;
    
    public SFVisualizer()throws IOException{
	dSet = SFFileParser.parseFile("2013_cleaned.csv");
	origDSetName = "2013_cleaned.csv";
	int minX = this.getMinXCoord();
        int maxX = this.getMaxXCoord();
        int minY = this.getMinYCoord();
        int maxY = this.getMaxYCoord();
        StdDraw.setXscale(minX, maxX);
        StdDraw.setYscale(minY, maxY);
    }

    public SFVisualizer(String fileName)throws IOException{
	dSet = SFFileParser.parseFile(fileName);
	origDSetName = fileName;
	int minX = this.getMinXCoord();
        int maxX = this.getMaxXCoord();
        int minY = this.getMinYCoord();
        int maxY = this.getMaxYCoord();

        StdDraw.setXscale(minX, maxX);
        StdDraw.setYscale(minY, maxY);
    }
    
    /**
     * Reads in a Stop and Frisk data set from a file and visualizes
     * the data set.
     */
    public static void visualizeData() throws IOException
    {
        // Get all the data from the file
        SFDataPt[] dataSet = SFFileParser.parseFile("2013_cleaned.csv");
        
        // Get ONLY the data for the appropriate borough
        SFDataPt[] boroughDataSet = 
            SFVisualizer.filterByBorough(dataSet, "MANHATTAN");

        int minX = SFVisualizer.getMinXCoord(boroughDataSet);
        int maxX = SFVisualizer.getMaxXCoord(boroughDataSet);
        int minY = SFVisualizer.getMinYCoord(boroughDataSet);
        int maxY = SFVisualizer.getMaxYCoord(boroughDataSet);
        StdDraw.setXscale(minX, maxX);
        StdDraw.setYscale(minY, maxY);
        
        SFDataPt[] asianPacIslData = SFFactFinder.filterByRaceEnc(boroughDataSet, "A");
        SFDataPt[] hispData = SFFactFinder.filterByRaceEnc(boroughDataSet, "PQ");
        SFDataPt[] blackData = SFFactFinder.filterByRaceEnc(boroughDataSet, "B");
        SFDataPt[] amIndAkNatData = SFFactFinder.filterByRaceEnc(boroughDataSet, "I");
        SFDataPt[] whiteData = SFFactFinder.filterByRaceEnc(boroughDataSet, "W");
        SFDataPt[] otherData = SFFactFinder.filterByRaceEnc(boroughDataSet, "UXZ");
        
        SFDataPt[] pct14 = SFFactFinder.filterByPct(boroughDataSet, 14);
        while(true)
        {
           
            //SFVisualizer.drawData(pct14, StdDraw.BLACK, 1000);
            SFVisualizer.drawData(boroughDataSet, StdDraw.BLACK, 2000);
            //SFVisualizer.drawData(asianPacIslData, StdDraw.PINK, 1000);
            //SFVisualizer.drawData(blackData, StdDraw.BLUE, 1000);
            //SFVisualizer.drawData(hispData, StdDraw.RED, 1000);
            //SFVisualizer.drawData(whiteData, StdDraw.GREEN, 1000);
            //SFVisualizer.drawData(otherData, StdDraw.MAGENTA, 1000);
        }
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

    public void drawData(Color c, int timeMil)
    {
        StdDraw.show(0);
        StdDraw.clear();
        StdDraw.setPenColor(c);
        for(int i = 0; i < this.dSet.length; i++)
        {
            StdDraw.point(this.dSet[i].getX(), this.dSet[i].getY());
        }
        StdDraw.show(timeMil);
    }

    public void rollBack()throws IOException{
	this.dSet = SFFileParser.parseFile(this.origDSetName);
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
    public static SFDataPt[] filterByBorough(SFDataPt[] dataSet, String borough)
    {
        return SFFactFinder.filterByBorough(dataSet, borough);
    }

    public void filterByBorough(String borough)
    {
        this.dSet = SFFactFinder.filterByBorough(this.dSet, borough);
    }
    
    public static SFDataPt[] filterByRace(SFDataPt[] dataSet, String race){
        return SFFactFinder.filterByRaceEnc(dataSet, race);
    }

    public void filterByRace(String race){
        this.dSet = SFFactFinder.filterByRaceEnc(this.dSet, race);
    }
    
    /**
     * Returns the minimum x-coordinate contained in the dataSet.
     * 
     * @param dataSet An array of SFDataPts
     */
    public static int getMinXCoord(SFDataPt[] dataSet)
    {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dataSet.length; i++){
            if (dataSet[i].getX() < min){
                min = dataSet[i].getX();
            }
        }
        return min;
    }

    public int getMinXCoord()
    {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < this.dSet.length; i++){
            if (this.dSet[i].getX() < min){
                min = this.dSet[i].getX();
            }
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
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < dataSet.length; i++){
            if (dataSet[i].getY() < max){
                max = dataSet[i].getY();
            }
        }
        return max;
    }

    public int getMinYCoord()
    {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < this.dSet.length; i++){
            if (this.dSet[i].getY() < min){
                min = this.dSet[i].getY();
            }
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
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dataSet.length; i++){
            if (dataSet[i].getX() < min){
                min = dataSet[i].getX();
            }
        }
        return min;
    }

    public int getMaxXCoord()
    {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < this.dSet.length; i++){
            if (this.dSet[i].getX() > max){
                max = this.dSet[i].getX();
            }
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
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < dataSet.length; i++){
            if (dataSet[i].getY() < min){
                min = dataSet[i].getY();
            }
        }
        return min;
    }

    public int getMaxYCoord()
    {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < this.dSet.length; i++){
            if (this.dSet[i].getY() > max){
                max = this.dSet[i].getY();
            }
	}
        return max;
    }

    public static void main(String[]args) throws IOException{
	SFVisualizer SFV = new SFVisualizer();
	SFV.filterByRace("A");
	SFV.drawData(StdDraw.BLACK, 2000);
    }
}
