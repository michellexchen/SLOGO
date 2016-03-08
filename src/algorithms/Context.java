package algorithms;

public class Context {

	private Strategy algorithmicStrategy;
	
	public Context(Strategy algorithm) {
		this.algorithmicStrategy = algorithm;
	}
	
	public double performOperation(String name){
		return algorithmicStrategy.encodeDecodeAlgorithm(name);
	}

}
