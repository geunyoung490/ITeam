# ITeam

- 장진이 이근영 이종웅


코드 설명입니다.  
```javascript
import java.util.Random;

--> 입력을 랜덤으로 지정:  java.util. 패키지에 Random클래스를 불러온다.

public static void main(String[] arg){
        Random random = new Random();
        int N = 100;
        int points[][]= new int[N][2];
        for(int i=0;i<N;i++){
            points[i][0]= random.nextInt(25);
            points[i][1]= random.nextInt(25);
        }
     .....    
.}
--> 총 점들의 수를 100이라고 정해보자.
     2차원 배열에서 행은 점들의 번호, 열은 각각 x좌표 y좌표를 나타낸다.
     points라는 이차배열을 생성하여  각 점들 당 x좌표와 y좌표를 랜덤으로 지정해 준다.
     (수가 너무 크면 안될거 같아 랜덤으로 지정해 주지만 25 아래로 지정해 줍니다.)

import java.util.Arrays;
....
 Arrays.sort(points, (p1,p2) -> {
            if(p1[0]==p2[0]) return p1[1]-p2[1];
            else return p1[0]-p2[0];
        });
  .....
}
--> Array클래스를 불러와 2차원 배열 points를 x좌표의 크기를 기준으로 정렬을 하였다.

int result[][] = closestPair(points,0,N-1);
for(int i =0;i<2;i++){System.out.println("[ "+result[i][0]+","+result[i][1]+"]"); }

함수 closestPair에서 리턴 받은 2차원 배열을 reseult에 넣어 그것을 출력한다.

 public static int dist(int x1, int y1 , int x2, int y2 ) {
        return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);}
--> 일단 점과 점의 사이 거리를 구하는 함수이다. 
    식은 (x1-x2)^2+(y1-y2)^2이다.

static int[][] bruteForce(int[][] p, int start, int end) {
        int minDist = Integer.MAX_VALUE; -->정수 minDist를 Integer의 가장 큰값으로 초기화해준다.
        int point[][] = new int[2][2];          --> 두 점을 구하는 것이므로 각각 행과 열의 크기를 2로 해준다.
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j <= end; j++) { for이중으로 돌려서 가장 가까운 거리에 있는 두 점을 찾아보자.
                if(p[i][0]!=p[j][0] && p[i][1]!=p[j][1]) { ---> 점의 위치가 같지 않은 경우중에서
                    int k = dist(p[i][0],p[i][1],p[j][0],p[j][1]); --> dist 함수를 이용해 결과 값을 k로 넣는다.
                    if(k<minDist) {                         ---> 이때 minDist보다 구한 k 값이 더 작은 경우에
                        point[0][0]=p[i][0];                --> point 배열에 각 점의 위치를 대입해준다.
                        point[0][1]=p[i][1];
                        point[1][0]=p[j][0];
                        point[1][1]=p[j][1];
                        minDist = k;                      ---> 그다음 minDist에 k값을 넣는다.
                      
                    }
                }
            }
        }
        return point;  -->point배열을 리턴해주면 된다. 
    }
 
---> 완전탐색함수이다. 2차원 배열의 크기가 커질수록 시작복잡도는 O(n^2)이므로 효율적이 못하므로 크기가 클때는 분할 정복을 이용한다.
       완전탐색함수는 배열의 크기가 작을 때 사용하도록한다.


입력: x좌표의 오름차순으로 정렬된 2차배열 points에 있는 i개의 점 (point[i][0]x좌표,points[i][1]y좌표)

출력 : points배열에 있는 점들 중 최근점 점의 쌍 

int n = end - start +1;
--> n은 배열의 크기
if(n==2) return p;
if(n<=3) return bruteForce(p,start,end);
--> Line 1 if(i==2)인경우 그냥 리턴해주면 됩니다.
              if(n<=3)인 경우 배열의 크기가 작기 때문에 완전탐색 함수를 사용합니다.


else{
       if(n%2==0)  mid=(start+end)/2;
       else mid=(start+end)/2 +1;
        }
int CPL[][] = closestPair(p,start,mid); -->SL
int CPR[][] = closestPair(p,mid+1,end); -->SR
--> Line 2: n>3인경우 SL(start~mid)과SR(mid~end) 로 분할해 줄때, n이 짝수일 경우  중간값이 (start+end)/2이지만 홀수인경우 1을 더 해준다.
     Line3,Line4: 분할된 SL과 SR에 대해서 재귀적으로 최근접 점의 쌍을 찾아서 각각을 CPL과 CPR이라고 놓는다.


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

--> CPL과 CPR중 더 작은 거리를 d라고 놓자. 
for을 돌리면서  p의 중간 지점x좌표- p[i]의 x좌표를 t라고 할때 t^t이 d보다 작은 거리에 있는 점들을 band라는 배열에 넣는다.


  Arrays.sort(band, (p1,p2) -> {
            if(p1[1]==p2[1]) return p1[0]-p2[0];
            else return p1[1]-p2[1];
        });
--> Array클래스를 불러와 2차원 배열 band를 y좌표의 크기를 기준으로 정렬을 하였다.

 nt[][] CPC = new int[2][2];
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
--> Line5: d를 이용하여 중간 영역에 속하는 점들을 찾고(band), 점들 중에서 최근접의 쌍을 찾아서 이를 CPC라고 놓는다.

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
-->Line 6: line3,4에서 각각 찾은 최근접 점의 쌍 CPL과 CPR과 Line5에서 찾은 CPC중에서 가장 짧은 거리를 가진 쌍을 해로서 리턴한다.
```
