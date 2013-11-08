package by.zti;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * Этот класс создан в учебных целях специально для канала Zhirni Toni Inc. Здесь речь пойдет о
 * классе JButton и о менеджере раскладки, а конкретнее "Как расположить JButton в окне по абсолютным 
 * параметрам (по координатам).
 * 
 * @author cvazer
 *
 */
public class Main {
	private static JFrame frame;
	private static JScrollPane scr;
	private static JTextArea area1;
	private static JLabel lb;
	private static JButton btn;
	
	public static void main(String[] args){
		/*
		 * Прежде чем начинать, нужно знать что такое LayoutManager (Менеджер Раскладки).
		 * Менеджер раскладки - это класс обеспечиваюший расположение элементов в контейнерах JFrame.
		 * Он позволяет распологать эллементы не по абсолюьной координатной сетке, а друг относительно друга,
		 * что гарантирует пользоваттелю динамическое изменение вида интерфейса независимо от размера
		 * самого JFrame (Грубо говоря "Элементы не вылезут за обозримою облость").
		 *  
		 * Это давольно удобная функция, однако, используя раскладку сложно достичь желаемого
		 * внешнего вида программы. В некоторых случаях удобно использовать абсолютное положение компанентов (Absolut),
		 * нежели построчное заполнение (FlowLayout).
		 * 
		 * В данном примере я покажу как пользоваться абсолютной раскладкой
		 */
		
		//Для начала вызываем метод, который просто инициализирует наше окно.
		createGUI();
		//Затем устанавливаем менеджер раскладки null, чтобы получить раскладку о абсолютному значению
		frame.getContentPane().setLayout(null);
		//Теперь инициализируем компаненты
		initComponents();
		/* 
		 * Теперь вызываем метод, который укажет расположение компонентов
		 * Его использование вместе с менеджером раскладки ничего не даст,
		 * по этому мы и указали менеджер раскладки null.
		 */
		boundsSetter();
	}
	
	/**
	 * метод инициализирующий компоненты
	 */
	private static void initComponents(){
		  		//добавляем в окно текстовую облость, наклейку и кнопку
				area1 = new JTextArea();
				//Инициализируем контейнер ScrollPane (Прокручиваемая область), и добавляем в него area1
				scr = new JScrollPane(area1);
				//Добавляем scr в окно 
				frame.getContentPane().add(scr);
				//Так-же поступаем с остальными компонентами
				lb = new JLabel("Я наклейка!!!");
				frame.getContentPane().add(lb);
				btn = new JButton("Я бесполезная кнопка");
				frame.getContentPane().add(btn);
	}
	
	/**
	 * Метод указываюший расположение компонентов
	 */
	private static void boundsSetter(){
		/* Указваем пораметры для контейнера функцие setBounds(int x, int y, int width, int heigth)
		 * Где x , y - координаты на сетке, а width и heigth - шырина и высота соответственно.
		 */
		scr.setBounds(10, 10, 300, 200);
		lb.setBounds(450, 450, 200, 200);
		btn.setBounds(200, 300, 250, 25);
	}
	
	/**
	 * Класс инициализирующий окно
	 */
	private static void createGUI() {
		//Инициализируем нашу переменную-окно (JFrame)
		frame = new JFrame("Sample");
		//Задаем ему изначальный размер
		frame.setSize(600, 600);
		//Заставляем программу закрываться после нажатия крестика
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Заставляем окно появляться в центре экрана
		frame.setLocationRelativeTo(null);
		//Делаем окно видемым
		frame.setVisible(true);
	}
}
