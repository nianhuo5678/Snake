package Snake;
import java.util.Random;
public class Food {

	private int x, y;

	public Food() {
		super();
		this.x = 200;
		this.y = 200;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int newPosition(int[] snake) {
		//新的食物的坐标，不能与蛇重合，不能与围墙重合
		//蛇的坐标由数组传入
		//XY轴除去围墙之后的坐标可以是[10,280]，在[1,28]之间的随机数
		Random r = new Random();
		int point = 0;
		boolean conflict = true;
		do {
			point = r.nextInt(28) + 1;
			for(int i : snake) {
				if (i == point) {
					//数组中任意有遇到第一个坐标与随机数坐标相同，退出for循环
					conflict = true;
					break;
				}
				else {
					conflict = false;
				}
			}
		}
		while(conflict);
		return (point * 10);
	}


}
