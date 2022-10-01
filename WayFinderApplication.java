
public class WayFinderApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	        WayFinderBackEnd<String> engine = new WayFinderBackEnd<String>();
	        engine.insertVertex("San Jose");
	        engine.insertVertex("San Francisco");
	        engine.insertVertex("Merced");
	        engine.insertVertex("Sacramento");
	        engine.insertVertex("Fresno");
	        engine.insertVertex("Bakersfield");
	        // insert edges from Week 11. Shortest Path Activity
	        engine.insertEdge("San Jose","San Francisco",50);
	        engine.insertEdge("San Jose","Merced",120);
	        engine.insertEdge("Merced","San Jose",120);
	        engine.insertEdge("Merced","Sacramento",120);
	        engine.insertEdge("Merced","Fresno",60);
	        engine.insertEdge("Fresno","Merced",110);
	        engine.insertEdge("Fresno","Bakersfield",110);
	        engine.insertEdge("Bakersfield","Fresno",110);
	        engine.insertEdge("Sacramento","San Francisco",90);
	        engine.insertEdge("Sacramento","Merced",120);
	        WayFinderFrontEnd ui = new WayFinderFrontEnd();
	        ui.graph = engine;       
	        ui.main(args);
	}

}
