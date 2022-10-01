import java.util.*;

public class WayFinderApp {

    public static void main(String[] args) throws Exception {
	
        List<WayFinderData> places = new WayFinderLoader().loadFile("/Users/emily/eclipse-workspace/FirstJavaFXActivity/src/WayFinderMap.csv");
        WayFinderBackEnd<String> engine = new WayFinderBackEnd<String>();
	
	for(int i = 0; i<places.size(); i++) {
		engine.insertVertex(places.get(i).getName());
	}
	
	for(int j=0; j<places.size(); j++) {
		WayFinderData curr = places.get(j);
		for(int k = 0; k<curr.getEdgesLeaving().size(); k++) {
			engine.insertEdge(curr.getName(), curr.getEdgesLeaving().get(k), curr.getEdgeWeights().get(k));
		}
	}
	WayFinderFrontEnd ui = new WayFinderFrontEnd();
	ui.graph = engine;
    ui.main(args);

    }
}

