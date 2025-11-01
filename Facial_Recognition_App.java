package Week_9;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
public class Facial_Recognition_App {
	static Scanner userinput = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "C:\\Users\\Boyum\\Desktop\\Text_File_for_Facial_Recognition_App.txt";
		File textFile = new File(fileName);
		Scanner in = new Scanner(textFile);
		// the six measurements for the 6 people (5 should eventually be read from a file)
		double [][] measure;
		measure = new double[6][6];
		String [] names;
		names = new String[6];
		String line;
		String number;
		float x,y;
		int i,j,k,l;
		
		for (i=0; i<5; i++) {
			line = in.nextLine();
			for (j=0; j<line.length()/10; j++) {
				//record the names from the text file
				if (j==0) {
					number = line.substring(j*10, j*10+9);
					names[i] = number.trim();
				}
				//record the measurements from the text file
				else {
					number = line.substring(j*10, j*10+9);
					x = Float.parseFloat(number.trim());
					measure[i][j-1] = x;
				}
			}
		}
		//Get data from the user for the unknown picture
		for (k=0; k<6; k++) {
			System.out.format("Enter a data value for measurement %c: \n", (char)(65 + k));
			y = Float.parseFloat(userinput.nextLine());
			measure[5][k] = y;
		}
		
		// the fifteen ratios for the 6 people
		double [][] r;
		r = new double [6][15];
		int person;
		int size;
		
		//calculate the ratios for each picture including the unknown picture
		for (person = 0; person < 6; person++) {
			for (size = 0; size < 5; size++) {		
				if (size == 0) {
					r[person][0] = measure[person][size] / measure [person][size+1];
					r[person][1] = measure[person][size] / measure [person][size+2];
					r[person][2] = measure[person][size] / measure [person][size+3];
					r[person][3] = measure[person][size] / measure [person][size+4];
					r[person][4] = measure[person][size] / measure [person][size+5];
				}
				if (size == 1) {
					r[person][5] = measure[person][size] / measure [person][size+1];
					r[person][6] = measure[person][size] / measure [person][size+2];
					r[person][7] = measure[person][size] / measure [person][size+3];
					r[person][8] = measure[person][size] / measure [person][size+4];
				}
				if (size == 2) {
					r[person][9] = measure[person][size] / measure [person][size+1];
					r[person][10] = measure[person][size] / measure [person][size+2];
					r[person][11] = measure[person][size] / measure [person][size+3];
				}
				if (size == 3) {
					r[person][12] = measure[person][size] / measure [person][size+1];
					r[person][13] = measure[person][size] / measure [person][size+2];
				}
				if (size == 4) {
					r[person][14] = measure[person][size] / measure [person][size+1];
				}
			}
		}
		
		//sum of squares % difference
		double z;
		int closest = 10;
		double comparison = 0;
		//determine which picture is the closest to the unknown picture
		for (l=0; l<5; l++) {
			z = ((r[5][0]-r[l][0])*(r[5][0]-r[l][0]) + (r[5][1]-r[l][1])*(r[5][1]-r[l][1]) + 
				(r[5][2]-r[l][2])*(r[5][2]-r[l][2]) + (r[5][3]-r[l][3])*(r[5][3]-r[l][3]) + 
				(r[5][4]-r[l][4])*(r[5][4]-r[l][4]) + (r[5][5]-r[l][5])*(r[5][5]-r[l][5]) + 
				(r[5][6]-r[l][6])*(r[5][6]-r[l][6]) + (r[5][7]-r[l][7])*(r[5][7]-r[l][7]) + 
				(r[5][8]-r[l][8])*(r[5][8]-r[l][8]) + (r[5][9]-r[l][9])*(r[5][9]-r[l][9]) + 
				(r[5][10]-r[l][10])*(r[5][10]-r[l][10]) + (r[5][11]-r[l][11])*(r[5][11]-r[l][11]) + 
				(r[5][12]-r[l][12])*(r[5][12]-r[l][12]) + (r[5][13]-r[l][13])*(r[5][13]-r[l][13]) + 
				(r[5][14]-r[l][14])*(r[5][14]-r[l][14]));
			if (l==0) {
				comparison = z;
				closest = l;
			}
			else if (z<comparison) {
				comparison = z;
				closest = l;
			}
		}
		if ((closest == 0) || (closest == 1) || (closest == 2) || (closest == 3) || (closest == 4)) {
			System.out.println("This is a picture of " + names[closest] + ".");
		}
		else {System.out.println("Picture not identified");}
		
	}
}
