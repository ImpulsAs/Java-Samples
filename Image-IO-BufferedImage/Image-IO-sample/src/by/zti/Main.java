package by.zti;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Main {
	private static BufferedImage img;
	private static JButton btn = new JButton("Сохранить это изображение");

	public static void main(String[] args) {
		try {
			//Загружаем изображение из директории программы
			img = ImageIO.read(new File("res/duck.bmp"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		//Вызываем метод создающий окно
		createGUI();
		//Вешаем на кнопку слушатель событий
		btn.addActionListener(new ActionListener() {
			
			/**
			 * Этот метод вызывается каждый раз, когда на кнопке происходит какое-то событие
			 * Например нажатие мышки
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				//Спрашиваем у пользователя путь, куда он хочет сохранить файл и складываем его в строку
				String path = JOptionPane.showInputDialog(null, "Введите путь по которому "
						+ "хотите сохранить файлб например C:/vasha_papka");
				//инициализируем файл по полученному пути
				File file = new File(path+"/duck.bmp");
				//Помещаем все, что выбрасывает ошибку в блок обработки ошибок
				try {
					//Создаем файл непосредственно в файловой системе
					file.createNewFile();
					//Записываем в этот файл наше изображение с указанием формата
					ImageIO.write(img, "bmp", file);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
	}
	
	/**
	 * Метод создаюший окно
	 */
	private static void createGUI(){
		//Создаем окно с именем Sample
		JFrame frame = new JFrame("Sample");
		//Заставляем программу закрываться при нажатии крестика
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Устанавливаем размер окна
		frame.setSize(500, 500);
		//Заставляем окно появляться по середине монитора
		frame.setLocationRelativeTo(null);
		//Делаем окно видемым
		frame.setVisible(true);
		//Устанавливаем на окно менеджер раскладки
		frame.getContentPane().setLayout(new FlowLayout());
		//Добавляем картинку в окно
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		//Добавляем кнопку
		frame.getContentPane().add(btn);
	}

}
