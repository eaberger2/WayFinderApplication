import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

//--== CS400 Project Three File Header ==--
//Name: Kate Pramenko
//Email: kpramenko@wisc.edu
//Team: blue
//Group: BJ
//TA: Mu Cai
//Lecturer: Gary Dahl
//Notes to Grader: <optional extra notes>

//interface

interface WayFinderLoaderInterface {
	public List<WayFinderData> loadFile(String csvFilePath) throws FileNotFoundException;
}
// public class 

public class WayFinderLoader implements WayFinderLoaderInterface {
	
	@Override
	public List<WayFinderData> loadFile(String csvFilePath) throws FileNotFoundException{
		List<WayFinderData> list = new LinkedList<>();
		try {
			BufferedReader file = new BufferedReader(new FileReader(new File(csvFilePath)));
			//Scanner scnr = new Scanner(files[i]);
			String line = file.readLine();
			String[] data = line.split(",");
			List<String> edges = new ArrayList<String>();
			List<Integer> weights = new ArrayList<Integer>();
			int namePos = 0;
			int edgesPos = 1;
			int weightsPos = 2;
			
			namePos = 0;
			edgesPos=1;
			weightsPos=2;
			while (file.ready()) {
				int counter = 0;
				line = file.readLine();
				data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);	
				data[namePos] = data[namePos].replace("(OR)", "");
				data[namePos] = data[namePos].trim();
				String edgeStr = data[edgesPos];
				String[] edgeData = edgeStr.split(",");
				//create edges list
				for(int id=0; id<edgeData.length; id++) {
					edgeData[id] = edgeData[id].replace("[", "");
					edgeData[id] = edgeData[id].replace("]", "");
					edgeData[id] = edgeData[id].replace("(OR)", "");
					edgeData[id] = edgeData[id].replaceAll("\"", "");
					edges.add(edgeData[id].trim());
				}
				//create weights list
				String weigthsStr = data[weightsPos];
				String[] weightsData = weigthsStr.split(",");
				for(int id=0; id<weightsData.length; id++) {
					weightsData[id] = weightsData[id].replace("[", "");
					weightsData[id] = weightsData[id].replace("]", "");
					weightsData[id] = weightsData[id].replaceAll("\"", "");
					weightsData[id] = weightsData[id].trim();
					weights.add(Integer.parseInt(weightsData[id]));
				}

				list.add(new WayFinderData(data[namePos], edges , weights));
				
				edges.clear();
				weights.clear();
			}
			file.close();
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e2) {
				e2.printStackTrace();
			}
		return list;
	}

}
