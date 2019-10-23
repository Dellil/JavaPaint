import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class ColorSlider extends JSlider{
	/**
	 * 개요 : 컬러 선택을 위한 슬라이더(range) 클래스
	 * 작성자 : 문준원, 장찬희
	 * 작성일 : 2017-11-22
	 */
	private Image img = null; // 이미지 저장

	// 기본 생성자
	public ColorSlider() {
		// 부모 생성자 호출 - 0 ~ 359 까지 범위 - 기본값 0
		super(0, 359, 0);
		try {
			// 이미지 로드
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
