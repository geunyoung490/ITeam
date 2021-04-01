# ITeam

- 장진이 이근영 이종웅


코드  
|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|
import java.util.Random;
import java.util.Arrays;

public class ClosestPair3 {
    public static void main(String[] arg){
        Random random = new Random();
        int N = 100;
        int points[][]= new int[N][2];
        for(int i=0;i<N;i++){
            points[i][0]=random.nextInt(25);
            points[i][1]= random.nextInt(25);
        }
        Arrays.sort(points, (p1,p2) -> {
            if(p1[0]==p2[0]) return p1[1]-p2[1];
            else return p1[0]-p2[0];
        });

        int result[][] = closestPair(points,0,N-1);
        for(int i =0;i<2;i++){System.out.println("[ "+result[i][0]+","+result[i][1]+"]"); }
    }
    public static int dist(int x1, int y1 , int x2, int y2 ) {
        return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
    }
    static int[][] bruteForce(int[][] p, int start, int end) {
        int minDist = Integer.MAX_VALUE;
        int point[][] = new int[2][2];

        for (int i = start; i < end; i++) {
            for (int j = i + 1; j <= end; j++) {
                if(p[i][0]!=p[j][0] && p[i][1]!=p[j][1]) {
                    int k = dist(p[i][0],p[i][1],p[j][0],p[j][1]);
                    if(k<minDist) {
                        point[0][0]=p[i][0];
                        point[0][1]=p[i][1];
                        point[1][0]=p[j][0];
                        point[1][1]=p[j][1];
                        minDist = k;
                    }
                }
            }
        }
        return point;
    }
    static int[][] closestPair(int[][] p, int start, int end){
        int result[][] = new int[2][2];
        int n = end - start +1;
        int mid;
        if(n==2) return p;
        if(n<=3) return bruteForce(p,start,end);
        else{
            if(n%2==0)  mid=(start+end)/2;
            else mid=(start+end)/2 +1;
        }
        int CPL[][] = closestPair(p,start,mid);
        int CPR[][] = closestPair(p,mid+1,end);
        int d = Math.min(dist(CPL[0][0],CPL[0][1],CPL[1][0],CPL[1][1]),dist(CPR[0][0],CPR[0][1],CPR[1][0],CPR[1][1]));
        int a=0;
        int [][] band = new int[mid][2];
        for(int i = start;i<=end;i++){
            int t = p[mid][0]- p[i][0];
            if(t * t < d){
                band[a][0]= p[i][0];
                band[a][1]= p[i][1];
                a++;
            }
        }
        Arrays.sort(band, (p1,p2) -> {
            if(p1[1]==p2[1]) return p1[0]-p2[0];
            else return p1[1]-p2[1];
        });
        int[][] CPC = new int[2][2];
        for(int i =0;i<a+1;i++){
            for(int j =i+1;j<=a+1;j++){
                    int t = band[j][1] - band[i][1];
                    if (t * t < d){
                        int min = dist(band[i][0],band[i][1],band[j][0],band[j][1]);
                        if(min<d) {
                            CPC[0][0]=band[i][0];
                            CPC[0][1]=band[i][1];
                            CPC[1][0]=band[j][0];
                            CPC[1][1]=band[j][1];
                            d = Math.min(d, min);
                        }
                    }

            }
        }

        int cpl = dist(CPL[0][0],CPL[0][1],CPL[1][0],CPL[1][1]);
        int cpr = dist(CPR[0][0],CPR[0][1],CPR[1][0],CPR[1][1]);
        int cpc = dist(CPC[0][0],CPC[0][1],CPC[1][0],CPC[1][1]);
        if(cpl<cpr&&cpl<cpc){
            result[0][0]=CPL[0][0];
            result[0][1]=CPL[0][1];
            result[1][0]=CPL[1][0];
            result[1][1]=CPL[1][1];
            return result;
        }
        else if(cpr<cpl&&cpr<cpc) {
            result[0][0] = CPR[0][0];
            result[0][1] = CPR[0][1];
            result[1][0] = CPR[1][0];
            result[1][1] = CPR[1][1];
            return result;
        }
        else {
            result[0][0] = CPC[0][0];
            result[0][1] = CPC[0][1];
            result[1][0] = CPC[1][0];
            result[1][1] = CPC[1][1];
            return result;
        }

    }
}
|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|---|:---:|

