public class Dijkstra {
	static int INF = 1000000;
	//가중치 그래프 저장 베열
	static int[][] graph = {
			{0, 2, 5, 1, INF, INF},
			{3, 0, 3, 2, INF, INF},
			{8, 6, 0, 3, 1, 5},
			{7, 2, 3, 0, 1, INF},
			{INF, INF, 1, 1, 0, 2},
			{INF, INF, 8, INF, 4, 0}
	};
	static int[] T = {0, 0, 0, 0, 0, 0}; //T집합 배열
	static boolean [] v = new boolean[6]; //방문한 노드를 체크
	static int [] d = new int[6]; //비용을 저장하는 배열
	static String [] path = new String[6]; // 경로를 저장하는 배열
	static int it = 1; //횟수
	static String x; //T집합의 추가된 노드의 경로
	static String s ="2"; //근원지 노드
	static int s_int = Integer.parseInt(s)-1;
	public static void print_status() {
		System.out.print("  "+it +"\t");
		System.out.print("{");
		for(int j = 0; j<T.length; j++) {
			System.out.print(T[j]);
			if (j!=T.length-1)
				System.out.print(", ");
		}
		System.out.print("}"); 
		for (int i=0; i<6; i++) {
			if (i==1)
				continue;
			if (d[i] == INF) 
				System.out.printf("%8s", "INF");
			else
				System.out.printf("%8d", d[i]); //비용 출력
			if (path[i]==null)
				System.out.printf("%12s", "-");
			else System.out.printf("%12s", path[i]); //경로 출력
		}
		System.out.println();
	}
	public static int choose() { //가장 작은 노드를
		int min = INF;
		int minpos = -1; //가장 작은 노드
		for (int i=0; i<graph.length; i++) {
			if (d[i]<min && !v[i]) { //min보다 작고 T집합에 없는 노드인 경우에
				min = d[i]; //거리비용 저장
				minpos = i; // 노드 저장
				T[it]=i+1; // T집합에 추가
				x=path[i]; //새로 추가한 노드
			}
		}
		it++;
		return minpos; //가장 작은 노드 반환
	}

	public static void main(String[] args) {
		System.out.printf("%3s %13s %18s %10s %8s %10s %8s %10s %8s %9s %8s %10s", 
				"i" ,"T", "L(1)", "Path", "L(3)", "Path", "L(4)", "Path", "L(5)", "Path", "L(6)", "Path");
		System.out.println("");
		int l;
		
		for (int i=0; i<graph.length; i++) {
			v[i] = false; // 방문노드 배열 false로 초기화
			d[i]=graph[s_int][i]; //거리비용배열에 이웃 노드에 대한 링크 비용을 저장
			T[i]=0; //T집합은 0으로 초기화
		}
		for(int i =0; i<graph.length; i++) {
			if(d[i] != INF && d[i] != 0) //이웃한 노드이고 자신이 아닌 다른 노드에 대해
				path[i]="2-"+(i+1); // 경로를 추가한다.
		}
		path[s_int]=s; //근원지 노드의 경로는 자기 자신을 넣고
		T[0]=s_int+1; //T집합에는 근원지 노드를 넣고
		v[s_int]=true; d[s_int]=0; //방문노드와 거리비용을 초기화한다.
		
		print_status(); //초기화한 내용을 출력
		for(int i=0; i<6; i++) {
			l = choose(); //T집합 중 거리비용이 가장 노드를 찾는다.
			if (l == -1) //모든 노드가 T집합에 포함되었으므로 알고리즘 종료
				break;
			v[l] = true; //T집합에 추가한 노드을 true로 세트
			for (int n=0; n<6; n++) {
				if(!v[n])
					//새로 추가한 노드를 통해서 n노드까지의 거리비용을 계산
					if(d[l] + graph[l][n] < d[n]) { 
						//현재 거리 비용보다 추가한 노드를 거쳐서 가는 비용이 더 작다면 갱신
						d[n] = d[l]+graph[l][n]; 
						//새로 추가한 노드의 경로에 목적지 노드 추가
						path[n]=x+"-"+(n+1);
					}
			}
			//현재 상태 출력
			print_status();
		}
	}
}
