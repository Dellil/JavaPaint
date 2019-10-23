import javax.swing.*;
import java.awt.*;
public class ColorSelect extends JPanel{
	/**
	 * ���� : �÷� ������ ���� JPanel / ���� �����ֱ�
	 * �ۼ��� : ���ؿ�, ������
	 * �ۼ��� : 2017-11-22
	 */
	private int hue; // ���� ����
	
	// �÷� ����
	public void setColor(int colorCode)
	{
		this.hue = colorCode;
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		// �׷��� ��� ����� ���� ���
		Graphics2D g2d = (Graphics2D) g;
		
		// ����
		Color white = new Color(255, 255, 255);
        Color black = new Color(0, 0, 0);
        Color transparency = new Color(0, 0, 0, 0);
        Color color1 = Color.getHSBColor(hue / 360f, 1f, 1f);
        
        // �ʱ�ȭ
        g2d.setPaint(white);
        g2d.fillRect(0, 0, 300, 150);
        
        // �׶��̼� ����
	    GradientPaint back1 = new GradientPaint(0, 0, white, 300, 0, color1);
	    GradientPaint back2 = new GradientPaint(0, 0, transparency, 0, 150, black);
	    
	    // ���� ����
	    g2d.setPaint(back1);
	    g2d.fillRect(0, 0, 300, 150);
	    g2d.setPaint(back2);
	    g2d.fillRect(0, 0, 300, 150);
	}
}
