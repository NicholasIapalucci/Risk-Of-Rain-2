package znick_.riskofrain2.api.mc.enums;

/**
 * Represents a Cardinal direction in 3D space, such as {@link #NORTH} or {@link #SOUTH}.
 * 
 * @author zNick_
 */
public enum Cardinal {
	NORTH,
	SOUTH,
	EAST,
	WEST,
	/**The zenith direction, equivalent to positive Y.*/
	ZENITH,
	/**The nadir direction, equivalent to negative Y.*/
	NADIR;
	
	public Face toFace() {
		switch(this) {
		case ZENITH: return Face.POSITIVE_Y;
		case NADIR: return Face.NEGATIVE_Y;
		default: throw new UnsupportedOperationException("Cannot convert " + this.toString() + " to a face");
		}
	}
}
