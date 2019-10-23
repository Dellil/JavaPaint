import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Photo extends JFrame{
	/**
	 * 개요 : 사용자가 그림판을 사용할 수 있도록 해주는 메인 클래스
	 * 작성자 : 문준원, 장찬희
	 * 작성일 : 2017-11-20
	 */
	
	// 패널 
	JPanel leftPanel;
	JPanel rightPanel;
	JPanel centerPanel;
	
	// 캔버스
	DrawingSomething c;
	
	// 이미지 저장 <컬렉션>
	Map<String, ImageIcon> i = new HashMap<>();
	
	// 버튼 저장 <컬렉션>
	Map<String, JButton> b = new HashMap<>();
	
	// 색상 임시 저장
	int colorCode;
	
	// 기본 생성자
	public Photo() {
	}
	
	// 인자 있는 생성자
	public Photo(String title) {
		// title 설정
		super("Paint!");

		// 패널
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		centerPanel = new JPanel();
		
		// 아이콘
		i.put("pen", new ImageIcon("pen.png"));
		i.put("brush", new ImageIcon("brush.png"));
		i.put("reset", new ImageIcon("reset.png"));
		i.put("sizeUp", new ImageIcon("sizeUp.png"));
		i.put("sizeDown", new ImageIcon("sizeDown.png"));
		
		// 캔버스
		c = new DrawingSomething();
		
		// 색상 결정
		Color gray = new Color(80,80,80);
		Color dark_gray = new Color(40,40,40);
		
		// 화면 설정
		c.setSize(1050, 850);
		c.setBackground(Color.WHITE);
			
		// 버튼 세팅
		b.put("pen", new JButton(i.get("pen")));
		b.put("brush", new JButton(i.get("brush")));
		b.put("reset", new JButton(i.get("reset")));
		b.put("sizeUp", new JButton(i.get("sizeUp")));
		b.put("sizeDown", new JButton(i.get("sizeDown")));
		b.put("colorSelect", new JButton("색상 선택"));
		
		// 버튼 외곽선 제거
		b.get("pen").setBorderPainted(false); 
		b.get("brush").setBorderPainted(false);
		b.get("reset").setBorderPainted(false);
		b.get("sizeUp").setBorderPainted(false);
		b.get("sizeDown").setBorderPainted(false);
		
		// 내용 영역 채우기 안함
		b.get("pen").setContentAreaFilled(false); 
	 	b.get("brush").setContentAreaFilled(false);
		b.get("reset").setContentAreaFilled(false);
		b.get("sizeUp").setContentAreaFilled(false);
		b.get("sizeDown").setContentAreaFilled(false);
		
		// 버튼이 선택되었을때 생기는 테두리 사용안함
		b.get("pen").setFocusPainted(false); 
		b.get("brush").setFocusPainted(false);
		b.get("reset").setFocusPainted(false);
		b.get("sizeUp").setFocusPainted(false);
		b.get("sizeDown").setFocusPainted(false);
		
				
		// 좌측 패널 <요소 추가>
		leftPanel.add(b.get("pen"));
		leftPanel.add(b.get("brush"));
		leftPanel.add(b.get("reset"));
		leftPanel.add(b.get("sizeUp"));
		leftPanel.add(b.get("sizeDown"));

		
		// 드로우 컬러 선택
		ColorSelect colorSelect = new ColorSelect();
		JSlider colorSlider = new ColorSlider();
		
		// 색상 변경시
		colorSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// 변경시
				colorCode = colorSlider.getValue();
				colorSelect.setColor(colorSlider.getValue());
			}
		});
		
		// 우측 패널
		rightPanel.add(colorSelect);
		rightPanel.add(colorSlider);
		rightPanel.add(b.get("colorSelect"));
		
		// 중간 패널
		centerPanel.add(c);
		
		// 패널 크기 설정
		leftPanel.setPreferredSize(new Dimension(76, 900));
		rightPanel.setPreferredSize(new Dimension(310, 900));
		colorSelect.setPreferredSize(new Dimension(300, 150));
		colorSlider.setPreferredSize(new Dimension(300, 20));
			
		// 위치 지정
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
		add(centerPanel, BorderLayout.CENTER);
				
		// 색상 정하기
		setBackground(gray);
		leftPanel.setBackground(gray);
		rightPanel.setBackground(gray);
		centerPanel.setBackground(dark_gray);
		getContentPane().setBackground(dark_gray);

		// 이벤트처리
		c.addMouseMotionListener(new MouseClass());
		b.get("pen").addActionListener(new ActionClass());
		b.get("brush").addActionListener(new ActionClass());
		b.get("sizeDown").addActionListener(new ActionClass());
		b.get("sizeUp").addActionListener(new ActionClass());
		b.get("reset").addActionListener(new ActionClass());
		b.get("colorSelect").addActionListener(new ActionClass());
				
		// [메인] 아이콘 바꾸기
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("paint.png");
		setIconImage(img);
		
		// 프로그램 설정
		setSize(1600, 900);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// [버튼] 버튼 이벤트 <클릭>
	public class ActionClass implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// 각 버튼 클릭 이벤트
			if(e.getSource() == b.get("pen")) {
				c.draw_type = "pen";
			} else if(e.getSource() == b.get("brush")){
				c.draw_type = "brush";
			} else if(e.getSource() == b.get("sizeUp")){
				c.sizeUp();
			} else if(e.getSource() == b.get("sizeDown")){
				if(c.getSizeArc() > 8) {
					c.sizeDown();
				} else {
					JOptionPane.showMessageDialog(null, "최소 크기");
				}
					
			} else if(e.getSource() == b.get("remove")){
				c.sizeUp();
			} else if(e.getSource() == b.get("reset")) {
				c.getGraphics().clearRect(0, 0, 1200, 900);
			} else if(e.getSource() == b.get("colorSelect")) {
				c.setDrawColor(colorCode);
			}
		}
			
			
	}
	
	// [캔버스] 마우스 이벤트 <드래그>
	class MouseClass extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			c.x = e.getX();
			c.y = e.getY();
			c.repaint();
		}
	}
	
	// [메인] 메인 메소드
	public static void main(String[] args) {
		Photo program = new Photo("s");
	}
}
