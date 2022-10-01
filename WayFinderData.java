import java.util.ArrayList;
import java.util.List;

//--== CS400 Project Three File Header ==--
//Name: Kate Pramenko
//Email: kpramenko@wisc.edu
//Team: blue
//Group: BJ
//TA: Mu Cai
//Lecturer: Gary Dahl
//Notes to Grader: <optional extra notes>

//interface

interface WayFinderInterface {
	public String getName();

	public List<String> getEdgesLeaving();

	public List<Integer> getEdgeWeights();
}

// public class 

public class WayFinderData implements WayFinderInterface {
	private String name;
	private List<String> edgesLeaving;
	private List<Integer> edgeWeights;

	public WayFinderData(String name, List<String> edgesLeaving, List<Integer> edgeWeights) {
		this.name = name;
		this.edgesLeaving = new ArrayList<String>();
		this.edgeWeights = new ArrayList<Integer>();
		for(int i = 0; i<edgesLeaving.size();i++) {
			this.edgesLeaving.add(edgesLeaving.get(i));
		}
		//this.edgesLeaving = edgesLeaving;
		for(int i = 0; i<edgeWeights.size();i++) {
			this.edgeWeights.add(edgeWeights.get(i));
		}
		//this.edgeWeights = edgeWeights;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<String> getEdgesLeaving() {
		return this.edgesLeaving;
	}

	@Override
	public List<Integer> getEdgeWeights() {
		return this.edgeWeights;
	}

}

// Placeholder
class WayFinderPlaceholderA implements WayFinderInterface {
	public String getName() {
		return "Milwaukee";
	}

	public List<String> getEdgesLeaving() {
		List<String> list = new ArrayList<String>();
		list.add("Minneapolis");
		list.add("Chicago");
		return list;
	}

	public List<Integer> getEdgeWeights() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(300);
		list.add(150);
		return list;
	}
}

class WayFinderPlaceholderB implements WayFinderInterface {
	public String getName() {
		return "Chicago";
	}

	public List<String> getEdgesLeaving() {
		List<String> list = new ArrayList<String>();
		list.add("Milwaukee");
		list.add("Detroit");
		list.add("St. Louis");
		return list;
	}

	public List<Integer> getEdgeWeights() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(150);
		list.add(200);
		list.add(250);
		return list;
	}
}

class WayFinderPlaceholderC implements WayFinderInterface {
	public String getName() {
		return "Seattle";
	}

	public List<String> getEdgesLeaving() {
		List<String> list = new ArrayList<String>();
		list.add("Portland");
		return list;
	}

	public List<Integer> getEdgeWeights() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(150);
		return list;
	}
}
