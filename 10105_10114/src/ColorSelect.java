import javax.swing.*;
import java.awt.*;
public class ColorSelect extends JPanel{
	/**
	 * 개요 : 컬러 선택을 위한 JPanel / 색상 보여주기
	 * 작성자 : 문준원, 장찬희
	 * 작성일 : 2017-11-22
	 */
	private int hue; // 색조 저장
	
	// 컬러 설정
	public void setColor(int colorCode)
	{
		this.hue = colorCode;
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		// 그래픽 요소 사용을 위한 요소
		Graphics2D g2d = (Graphics2D) g;
		
		// 색상
		Color white = new Color(255, 255, 255);
        Color black = new Color(0, 0, 0);
        Color transparency = new Color(0, 0, 0, 0);
        Color color1 = Color.getHSBColor(hue / 360f, 1f, 1f);
        
        // 초기화
        g2d.setPaint(white);
        g2d.fillRect(0, 0, 300, 150);
        
        // 그라데이션 적용
	    GradientPaint back1 = new GradientPaint(0, 0, white, 300, 0, color1);
	    GradientPaint back2 = new GradientPaint(0, 0, transparency, 0, 150, black);
	    
	    // 색상 적용
	    g2d.setPaint(back1);
	    g2d.fillRect(0, 0, 300, 150);
	    g2d.setPaint(back2);
	    g2d.fillRect(0, 0, 300, 150);
	}
}
