package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		//INFORMANDO O ARQUIVO QUE DESEJA TRABALHAR ATRAVÉS DO PATH
		System.out.println("Enter file path: ");
		String sourceFileStr = sc.nextLine();
		
		File sourceFile = new File(sourceFileStr);
		//getParent VAI OBTER O CAMINHO DESPREZANDO O ARQUIVO EX: C:\temp
		String sourceFolderStr = sourceFile.getParent();	
		
		//CRIANDO UMA PASTA CHAMADA out
		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		//CASO A OPERAÇÃO ACIMA DER CERTO O RETORNO SERÁ TRUE
		System.out.println("Folder created: " + success);
		//CRIANDO UM CAMINHO CHAMADO C:\\TEMP\\OUT\\SUMARY.CSV
		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";
		
		//RECURSO PARA LER O CONTEÚDO DO ARQUIVO ARMAZENADA NO PATH sourceFileStr
		try(BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){
			//br.readLine() ler uma linha do texto
			String itemCsv = br.readLine();
			//VAI REPETIR ATÉ QUANDO NÃO HAVER MAIS LINHAS PARA LER
			while(itemCsv != null) {
				//Quebrando a linha em tres valores separados por vírgula
				String[] fields = itemCsv.split(",");
				String name = fields[0];
				Double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);
				//DEPOIS DE TER QUEBRADO A STRING EM TRES PARTES. INSTANCIA-SE O Product
				list.add(new Product(name, price, quantity));
				
				itemCsv = br.readLine();
			}
			//RECURSO PARA ESCREVER UM NOVO ARQUIVO
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
				
				for(Product item : list) {
					//VAI ESCREVER O (NOME, TOTAL)
					bw.write(item.getName() + "," + String.format("%.2f", item.total()));
					bw.newLine(); 		//Escreve uma nova linha
				}
				
				System.out.println(targetFileStr + " Created");
				
			} catch(IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		
		sc.close();
	}

}
