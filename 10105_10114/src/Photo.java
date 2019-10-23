import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Photo extends JFrame{
	/**
	 * ���� : ����ڰ� �׸����� ����� �� �ֵ��� ���ִ� ���� Ŭ����
	 * �ۼ��� : ���ؿ�, ������
	 * �ۼ��� : 2017-11-20
	 */
	
	// �г� 
	JPanel leftPanel;
	JPanel rightPanel;
	JPanel centerPanel;
	
	// ĵ����
	DrawingSomething c;
	
	// �̹��� ���� <�÷���>
	Map<String, ImageIcon> i = new HashMap<>();
	
	// ��ư ���� <�÷���>
	Map<String, JButton> b = new HashMap<>();
	
	// ���� �ӽ� ����
	int colorCode;
	
	// �⺻ ������
	public Photo() {
	}
	
	// ���� �ִ� ������
	public Photo(String title) {
		// title ����
		super("Paint!");

		// �г�
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		centerPanel = new JPanel();
		
		// ������
		i.put("pen", new ImageIcon("pen.png"));
		i.put("brush", new ImageIcon("brush.png"));
		i.put("reset", new ImageIcon("reset.png"));
		i.put("sizeUp", new ImageIcon("sizeUp.png"));
		i.put("sizeDown", new ImageIcon("sizeDown.png"));
		
		// ĵ����
		c = new DrawingSomething();
		
		// ���� ����
		Color gray = new Color(80,80,80);
		Color dark_gray = new Color(40,40,40);
		
		// ȭ�� ����
		c.setSize(1050, 850);
		c.setBackground(Color.WHITE);
			
		// ��ư ����
		b.put("pen", new JButton(i.get("pen")));
		b.put("brush", new JButton(i.get("brush")));
		b.put("reset", new JButton(i.get("reset")));
		b.put("sizeUp", new JButton(i.get("sizeUp")));
		b.put("sizeDown", new JButton(i.get("sizeDown")));
		b.put("colorSelect", new JButton("���� ����"));
		
		// ��ư �ܰ��� ����
		b.get("pen").setBorderPainted(false); 
		b.get("brush").setBorderPainted(false);
		b.get("reset").setBorderPainted(false);
		b.get("sizeUp").setBorderPainted(false);
		b.get("sizeDown").setBorderPainted(false);
		
		// ���� ���� ä��� ����
		b.get("pen").setContentAreaFilled(false); 
	 	b.get("brush").setContentAreaFilled(false);
		b.get("reset").setContentAreaFilled(false);
		b.get("sizeUp").setContentAreaFilled(false);
		b.get("sizeDown").setContentAreaFilled(false);
		
		// ��ư�� ���õǾ����� ����� �׵θ� ������
		b.get("pen").setFocusPainted(false); 
		b.get("brush").setFocusPainted(false);
		b.get("reset").setFocusPainted(false);
		b.get("sizeUp").setFocusPainted(false);
		b.get("sizeDown").setFocusPainted(false);
		
				
		// ���� �г� <��� �߰�>
		leftPanel.add(b.get("pen"));
		leftPanel.add(b.get("brush"));
		leftPanel.add(b.get("reset"));
		leftPanel.add(b.get("sizeUp"));
		leftPanel.add(b.get("sizeDown"));

		
		// ��ο� �÷� ����
		ColorSelect colorSelect = new ColorSelect();
		JSlider colorSlider = new ColorSlider();
		
		// ���� �����
		colorSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// �����
				colorCode = colorSlider.getValue();
				colorSelect.setColor(colorSlider.getValue());
			}
		});
		
		// ���� �г�
		rightPanel.add(colorSelect);
		rightPanel.add(colorSlider);
		rightPanel.add(b.get("colorSelect"));
		
		// �߰� �г�
		centerPanel.add(c);
		
		// �г� ũ�� ����
		leftPanel.setPreferredSize(new Dimension(76, 900));
		rightPanel.setPreferredSize(new Dimension(310, 900));
		colorSelect.setPreferredSize(new Dimension(300, 150));
		colorSlider.setPreferredSize(new Dimension(300, 20));
			
		// ��ġ ����
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
		add(centerPanel, BorderLayout.CENTER);
				
		// ���� ���ϱ�
		setBackground(gray);
		leftPanel.setBackground(gray);
		rightPanel.setBackground(gray);
		centerPanel.setBackground(dark_gray);
		getContentPane().setBackground(dark_gray);

		// �̺�Ʈó��
		c.addMouseMotionListener(new MouseClass());
		b.get("pen").addActionListener(new ActionClass());
		b.get("brush").addActionListener(new ActionClass());
		b.get("sizeDown").addActionListener(new ActionClass());
		b.get("sizeUp").addActionListener(new ActionClass());
		b.get("reset").addActionListener(new ActionClass());
		b.get("colorSelect").addActionListener(new ActionClass());
				
		// [����] ������ �ٲٱ�
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("paint.png");
		setIconImage(img);
		
		// ���α׷� ����
		setSize(1600, 900);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// [��ư] ��ư �̺�Ʈ <Ŭ��>
	public class ActionClass implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// �� ��ư Ŭ�� �̺�Ʈ
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
					JOptionPane.showMessageDialog(null, "�ּ� ũ��");
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
	
	// [ĵ����] ���콺 �̺�Ʈ <�巡��>
	class MouseClass extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			c.x = e.getX();
			c.y = e.getY();
			c.repaint();
		}
	}
	
	// [����] ���� �޼ҵ�
	public static void main(String[] args) {
		Photo program = new Photo("s");
	}
}
