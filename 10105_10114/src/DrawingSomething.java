import java.awt.*;

public class DrawingSomething extends Canvas{
	// �׸� �׸��⸦ ������ ��ǥ�� ����
	public int x = -50;
	public int y = -50;
	
	// ������ ����
	public int size = 30;
	
	// ���� ����
	public Color color;
	
	// �׸� Ÿ�� ����
	public String draw_type;
	
	// �⺻ ������
	public DrawingSomething(){
		color = new Color(0, 0, 0); // ������
		draw_type = "pen";
	} 
	
	// �׸��� �� ����
	public void setDrawColor(int c) {
		this.color = Color.getHSBColor(c / 360f, 1f, 1f);;
	}
	// �׸��� ������ ���� ....(arc��� ������ pen�� ���׸��� �Ŷ�...)
	public void setSizeArc(int size) {
		this.size = size;
	}
	// ũ�� ����
	public void sizeUp() {
		this.size++;
	}
	// ũ�� ����
	public void sizeDown() {
		this.size--;
	}
	// ���� ������ ��������
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
