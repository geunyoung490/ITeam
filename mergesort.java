import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class mergesort {
public static void mergeSort(int[][] arr) {
	int[][] temp = new int[arr.length][];
	mergeSort(arr, temp, 0, arr[0].length -1);
}
public static void mergeSort(int[][] arr, int[][] temp, int left, int right) {
if(arr[0][left] < arr[0][right]) {
	int mid = (arr[0][left] + arr[0][right])/2;
	mergeSort(arr,temp,left,mid);
	mergeSort(arr,temp,mid+1,right);
	merge(arr,temp,left,mid,right);
}
}
private static void merge(int[][] arr, int[][] temp, int start, int mid, int end) {
	for(int i = start; i<=end; i++)  { 
		temp[0][i]=arr[0][i]; 
	}
	int parta = start;
	int partb = mid+1;
	int index = start;
	while (parta <= mid && partb <= end) {
		if(temp[parta] <= temp[partb]) {
			arr[index] = temp[parta];
			parta++;
		}else {
			arr[index]=temp[partb];
			partb++;
		}
		index++;
	}
	for(int i = 0; i <= mid-parta; i++) {
		arr[index+1]=temp[parta+1];
	}
}
public static void printArray(int[][] arr) {
	for(int i=0; i<arr.length; i++) {
		for(int j=0; j<arr[i].length; j++) {
			System.out.print(arr[i][j] + ", ");}
		System.out.println();}
	}

static int[][] makeArray() {
	Random rand = new Random();
	Scanner scanner = new Scanner(System.in);
	System.out.print("난수의 개수를 입력하세요: ");
	int a = scanner.nextInt(); //생성할 점의 개수
	
	int temp[][]=new int[2][a]; //0행 0~a열에는 x좌표, 1행 0~a열에는 y좌표 입력
	
	for(int i=0; i<temp.length; i++) {
		for(int j=0; j<temp[i].length; j++) {
			temp[i][j]=rand.nextInt(99)+1;}}
	
	scanner.close();
	return temp;
	}

public static void dist_D() {
	
	int x= [0][b]-[0][a];
	int xx= Math.pow(x, 2);
	int y= [1][b]-[1][a];
	int yy= Math.pow(y, 2);
	xx+yy
	
	
}

public static void main(String[] args) {
		int matching[][]=makeArray();
		printArray(matching);
		
		
		
		
		
	
	}

}