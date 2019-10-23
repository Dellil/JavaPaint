import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class ColorSlider extends JSlider{
	/**
	 * ���� : �÷� ������ ���� �����̴�(range) Ŭ����
	 * �ۼ��� : ���ؿ�, ������
	 * �ۼ��� : 2017-11-22
	 */
	private Image img = null; // �̹��� ����

	// �⺻ ������
	public ColorSlider() {
		// �θ� ������ ȣ�� - 0 ~ 359 ���� ���� - �⺻�� 0
		super(0, 359, 0);
		try {
			// �̹��� �ε�
			img = ImageIO.read(new File("background_Color.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 300, 8, null);
        g.drawImage(img, 0, 12, 300, 8, null);
    }
    
}
