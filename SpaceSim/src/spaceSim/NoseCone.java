package spaceSim;

public class NoseCone {

	/*
	 * Class Instance Variables
	 * 
	 */
	
	private String 	modelNumber;
	private String	modelName;
	private String	manufacturer;
	private double	innerDiameter;
	private double	outerDiameter;
	private double	noseLength;
	private	double	shoulderLength;
	private double	weight;
	
	/*
	 * Class Constants
	 * 
	 */
	
	
	/*
	 * Getters and Setters
	 * 
	 */
	
	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public double getInnerDiameter() {
		return innerDiameter;
	}

	public void setInnerDiameter(double innerDiameter) {
		this.innerDiameter = innerDiameter;
	}

	public double getOuterDiameter() {
		return outerDiameter;
	}

	public void setOuterDiameter(double outerDiameter) {
		this.outerDiameter = outerDiameter;
	}

	public double getNoseLength() {
		return noseLength;
	}

	public void setNoseLength(double noseLength) {
		this.noseLength = noseLength;
	}

	public double getShoulderLength() {
		return shoulderLength;
	}

	public void setShoulderLength(double shoulderLength) {
		this.shoulderLength = shoulderLength;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/*
	 * Constructor Methods
	 * 
	 */
	
	public NoseCone() {
		modelNumber		= "";
		modelName		= "";
		manufacturer	= "";
		innerDiameter	= 0.0;
		outerDiameter	= 0.0;
		noseLength		= 0.0;
		shoulderLength	= 0.0;
		weight			= 0.0;
	}

	public NoseCone(String model, String name, String manufacturer, double inner, double outer, double nose, double shoulder, double weight) {
		modelNumber			= model;
		modelName			= name;
		this.manufacturer	= manufacturer;
		innerDiameter		= inner;
		outerDiameter		= outer;
		noseLength			= nose;
		shoulderLength		= shoulder;
		this.weight			= weight;
	}
}
