package derivative;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class derivative {

	//function f(x)
	public static double f(double x){
		return Math.cos(x);
	}
	
	public static void main(String[] args) {
		//scanner for user input
		Scanner keyboard = new Scanner (System.in);
		
		//arrays for from xstart and xstop and slopes between 
		//f(x) and f(x i+1)
		double[] fOx = new double[1000];
		double[] fpOx = new double[1000];
		//delta x
		double dOx;
		

		System.out.print("Enter a start value for x: ");
			double startX = keyboard.nextDouble();
		System.out.print("Enter a stop value for x: ");
			double stopX = keyboard.nextDouble();		
	
		//calculate delta x by subtracting startx from stopx and dividing by 1000
		dOx = (stopX - startX)/1000;
		
		//get name of file
		System.out.print("Enter a name for your file.");
		Path path = Paths.get(keyboard.next());
		
		//first value of f'(x) set to 0
		fpOx[0] = 0;
		//calculate the value of f(x) for each value in the array 
		for(int i = 0; i < 1000; i++){
			fOx[i] = f(startX + i * dOx);
			//calculate value of f'(x) for each item in the array
			if(i >= 1){
				fpOx[i] = (fOx[i] - fOx[i-1])/dOx;
			}
		}
		//setup for file
		ArrayList<String> file_lines = new ArrayList<String>();
		file_lines.add("x\tf(x)\tf'(x)");
		//mmaking each x and multiplying it by the incriment of deltaX
		for(int j = 0; j < fOx.length; j++){
			double x = startX + j * dOx;
			
			//prints all x values, f(x) values, and f'(x) values
			file_lines.add(String.format("%.2f\t%.2f\t%.2f", x, fOx[j], fpOx[j]));
		}
		try{
			Files.write(path, file_lines);
		}catch(IOException e){
			e.printStackTrace();
		}
		keyboard.close();
	}
}
