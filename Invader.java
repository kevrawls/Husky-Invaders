
public class Invader {
	public int x, y, width, height, origX, origY, length, counter;
	public String type;
	public boolean isOnBottom;
	
	public Invader(int x,int y,int width,int height, int length, String type, boolean isOnBottom){
		this.length = length;
		this.x = x;
		this.y = y;
		this.origX = x;
		this.origY = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.isOnBottom = isOnBottom;
	}
}
