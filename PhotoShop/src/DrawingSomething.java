import java.awt.*;

public class DrawingSomething extends Canvas{
	// 그림 그리기를 시작할 좌표값 저장
	public int x = -50;
	public int y = -50;
	
	// 사이즈 저장
	public int size = 30;
	
	// 색상 저장
	public Color color;
	
	// 그림 타입 저장
	public String draw_type;
	
	// 기본 생성자
	public DrawingSomething(){
		color = new Color(0, 0, 0); // 검정색
		draw_type = "pen";
	} 
	
	// 그리기 색 설정
	public void setDrawColor(int c) {
		this.color = Color.getHSBColor(c / 360f, 1f, 1f);;
	}
	// 그리는 사이즈 설정 ....(arc라고 적은건 pen이 원그리는 거라...)
	public void setSizeArc(int size) {
		this.size = size;
	}
	// 크기 증가
	public void sizeUp() {
		this.size++;
	}
	// 크기 감소
	public void sizeDown() {
		this.size--;
	}
	// 현재 사이즈 가져오기
	public int getSizeArc() {
		return this.size;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		if(draw_type.equals("pen")) {
			g.fillOval(x, y, this.size, this.size);
		} else {
			for(int i = 0; i < this.size / 2; i++)
				g.drawLine(x + i, y, x + this.size + i, y + this.size);
		}
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}
}
