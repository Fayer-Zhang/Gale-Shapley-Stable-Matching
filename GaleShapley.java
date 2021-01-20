/*
 * Student Name: Feier Zhang
 * Student Number: 8589976
 * Description: Gale-Shapley algorithm
 */

import java.util.*;
import java.io.*;

public class GaleShapley{

	static int n;
	static Stack<Integer> Sue = new Stack<Integer>(); //unmatched employers
	static int[] students;
	static String[] studentName;
	static int[] employers;
	static String[] employerName;
	
	static ArrayList<HeapPriorityQueue<Integer, Integer>> PQ = new ArrayList<HeapPriorityQueue<Integer, Integer>>();
	static int[][] A;


	/* Read the input file and perform all initialization steps */
	public static void initialize(String filename) throws Exception
	{
		File directory = new File("");
		filename = directory.getCanonicalPath()+"/"+filename;


		FileReader fr1 = new FileReader(filename);
		BufferedReader reader1 = new BufferedReader(fr1);

		String num = readLine(1, reader1);
		n = Integer.parseInt(num);
	
		students  = new int[n];
		employers = new int[n];
		studentName  = new String[n];
		employerName = new String[n];
		A = new int[n][n];


		// read and initialize employers' and students' information
		for(int i=0; i<n; i++)
		{
			int line_employer = i+2;
			int line_student  = i+2+n;

			FileReader fr2 = new FileReader(filename);
			BufferedReader reader2 = new BufferedReader(fr2);

			FileReader fr3 = new FileReader(filename);
			BufferedReader reader3 = new BufferedReader(fr3);

			String eachEmployerName = readLine(line_employer, reader2);
			String eachStudentName = readLine(line_student, reader3);
			
			employerName[i] = eachEmployerName;
			employers[i] = -1;
			Sue.push(i);

			studentName[i] = eachStudentName;
			students[i] = -1;
		}

		// read and initialize ranking information in PQ and A
		for(int i=0; i<n; i++)
		{
			int line = i+2*n+2;

			FileReader fr4 = new FileReader(filename);
			BufferedReader reader4 = new BufferedReader(fr4);
			
			String rankPairs = readLine(line, reader4);

			String[] splitedRankPair = rankPairs.split("\\s+");
			
			HeapPriorityQueue<Integer,Integer> pq = new HeapPriorityQueue<>(n);
			
			for(int j=0; j<n; j++)
			{
				String[] splitedRankNum = splitedRankPair[j].split(",");

				int num1 = Integer.parseInt(splitedRankNum[0]);
				pq.insert(num1, j);
						
				int num2 = Integer.parseInt(splitedRankNum[1]);
				A[j][i] = num2;
			}
			
			PQ.add(pq);	

		}
	}

	/* Read specific lines in the *.txt file */
	private static String readLine(int lineNumber, BufferedReader reader) throws Exception{
		String line = "";
		String text = "";
		int tmpCount=0;

		while((line = reader.readLine()) != null)
		{
			tmpCount++;
			if(tmpCount == lineNumber)
			{
				text = line;
			}
		}

		return text;
	}


	/* Perform the Gale-Shapley algorithm */
	public static void execute(){
		while(!Sue.empty())
		{
			int e = Sue.pop(); // e is looking for a student
			
			Entry<Integer,Integer> pairMin = (PQ.get(e)).removeMin();
			int s = pairMin.getValue(); // most preferred student of e
			
			int eStudent = students[s];
			
			// student is unmatched
			if(eStudent == -1) 
			{
				//match (e,s)
				students[s] = e;
				employers[e] = s; 
			}
			
			// s prefers e to employer e’
			else if(A[s][e] < A[s][eStudent])
			{
				students[s] = e;
				employers[e] = s;  // Replace the match
				employers[eStudent] = -1; // now unmatched
				Sue.push(eStudent);
			}

			else
			{
				Sue.push(e);
			}	
		}
	}

	/* Save the results in text file */
	public static void save(String filename) throws Exception{
		String content = "";

		String txtName = filename;
		File directory = new File("");
		filename = directory.getCanonicalPath()+"/"+filename;
		
		File file = new File(filename);
		File newfile = new File(file.getParent() + File.separator + "matches_" + txtName);
		
		// Create the output file
		if(!newfile.exists())
		{
			newfile.createNewFile();
		}
		
		FileWriter fw = new FileWriter(newfile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		// Write results into the output file
		for(int i=0; i<n; i++)
		{
			String str = String.valueOf(i);
			int index = employers[i];
			content = "Match " + str + ": " + employerName[i] + " - " + studentName[index] + "\n"; 
			bw.write(content);
		}

   		bw.close();
	}

	/* Ask for a filename, calls the method initialize, execute and save */
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter a file name: ");
		String filename = br.readLine();
		
		initialize(filename);
		execute();
		save(filename);
	}
}