package application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		
		//CRIANDO UM OBJETO DO TIPO FILE
		File file = new File("c:\\temp\\in.txt");	//para indicar contra-barra é preciso usar dois contra-barras
		Scanner sc = null;
		
//		INSTANCIANDO O SCANNER A PARTIR DO ARQUIVO(file)
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		finally {
			if(sc != null) {
				sc.close();
			}
			
		}
	}

}
