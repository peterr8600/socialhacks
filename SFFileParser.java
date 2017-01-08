//Import list create and manipulate lists
import java.util.List;
import java.util.ArrayList;
//Import to read files
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//Import to read files
import com.opencsv.CSVReader;
import com.opencsv.CSVParser;

public class SFFileParser
{
    /**
     * Given a file name for a CSV file, returns an array of SFDataPts that
     * represents the data set in the file.
     */
    public static SFDataPt[] parseFile(String fileName) throws IOException
    {
        ArrayList<SFDataPt> sfDataList = new ArrayList<SFDataPt>();
        CSVReader reader = new CSVReader(new FileReader(fileName), 1, new CSVParser());
        String[] row;
        while ((row = reader.readNext()) != null)
	    {
		//SFDataPoint constructor. (type) (name) = (new) (constructor(args));
		SFDataPt sfdp = new SFDataPt(row);
		sfDataList.add(sfdp);
	    }
        
        SFDataPt[] sfData = new SFDataPt[sfDataList.size()];
        sfData = sfDataList.toArray(sfData);
        return sfData;
    }
    
    
}