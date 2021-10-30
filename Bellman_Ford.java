public class Bellman_Ford {
	static int INF = 1000000;
	//가중치 그래프 저장 배열
	static int[][] graph = {
			{0, 2, 5, 1, INF, INF},
			{3, 0, 3, 2, INF, INF},
			{8, 6, 0, 3, 1, 5},
			{7, 2, 3, 0, 1, INF},
			{INF, INF, 1, 1, 0, 2},
			{INF, INF, 8, INF, 4, 0}
	};
	static int h = 0; //링크 수
	static int l [][] = new int[6][6]; //링크 수 마다 거리비용을 저장하는 2차원 배열
	static String path[] = new String[6]; //경로를 저장하는 배열
	static int flag = 0; //갱신 확인
	static String s ="2"; //근원지 노드
	static int s_int = Integer.parseInt(s)-1;
	//링크 수 마다 현재 상황을 출력하기 위해 링크 수를 매개변수로 이용
	public static void print_status(int h) {
		System.out.print("  "+h);
		for (int i=0; i<l.length; i++) {
			if (i==s_int) continue;
			if (l[h][i] == INF) 
				System.out.printf("%10s", "INF");
			else System.out.printf("%10d", l[h][i]); //비용 출력
			if (path[i]=="2") 
				System.out.printf("%12s", "-");
			else System.out.printf("%12s", path[i]); //경로 출력
		}
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.printf("%3s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s", 
				"h", "L(1)", "Path", "L(3)", "Path", "L(4)", "Path", "L(5)", "Path", "L(6)", "Path");
		System.out.println("");

		//비용 저장 배열 초기화
		for(int h =0; h<l.length; h++) {
			for(int n=0; n<l.length; n++) {
				l[h][n]=INF;
			}
		}
		//근원지 비용 0으로 초기화
		for (int i=0; i<l.length; i++) {
			l[i][s_int]=0;
		}
		//경로 저장 배열에 근원지 노드 저장
		for(int i =0; i<l.length; i++) {
			path[i]=s;
		}
		//링크가 0일때 프린트
		print_status(0);
		//벨만포드 알고리즘 시작, 링크 하나일 때부터
		for(h=1; h<graph.length; h++) { //링크를 추가, 링크는 사이클이 없도록 적어도 노드의 수보다는 작아야한다.
			for (int n=0; n<graph.length; n++) { //n으로 가는 최소비용 계산
				for (int j=0; j<graph.length; j++) { //j노드을 거쳐서 n노드로 간다
					if (n==s_int) continue; //목적지가 근원지 노드이면 다른 노드를 계산한다. 
					if(graph[j][n] != INF && graph[j][n] != 0) { //이웃한 노드이고 자신이 아닌 다른 노드에 대해
						//j노드를 거쳐서 가는 비용 중 가장 작은 비용을 찾는다. 
						if (l[h-1][j] + graph[j][n] < l[h][n]) { 
							l[h][n] = l[h-1][j]+graph[j][n]; //j노드를 거치면서 가장 작은 비용을 찾아서 갱신하고
							path[n] = path[j]+"-"+(n+1);//j노드를 거쳐 목적지 n노드로의 경로를 저장
						}
					}
				}
			}
			
			//h와 h-1의 비용을 비교해서 차이가 없다면 flag+1
			for (int i=0; i<l.length; i++) {
				if(i==s_int) continue;
				if(l[h-1][i]==l[h][i])
					flag++;
			}
			//모든 노드의 갱신이 없다면 알고리즘 실행을 중지하고 for문을 빠져나온다.
			if (flag == 5) {
				print_status(h);
				break;
			}
			//하나라도 노드의 갱신이 있다면 flag을 다시 0으로 세트, 알고리즘 반복
			else {
				print_status(h);
				flag=0;
			}
		}
	}
}
