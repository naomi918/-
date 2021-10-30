public class Dijkstra {
	static int INF = 1000000;
	//����ġ �׷��� ���� ����
	static int[][] graph = {
			{0, 2, 5, 1, INF, INF},
			{3, 0, 3, 2, INF, INF},
			{8, 6, 0, 3, 1, 5},
			{7, 2, 3, 0, 1, INF},
			{INF, INF, 1, 1, 0, 2},
			{INF, INF, 8, INF, 4, 0}
	};
	static int[] T = {0, 0, 0, 0, 0, 0}; //T���� �迭
	static boolean [] v = new boolean[6]; //�湮�� ��带 üũ
	static int [] d = new int[6]; //����� �����ϴ� �迭
	static String [] path = new String[6]; // ��θ� �����ϴ� �迭
	static int it = 1; //Ƚ��
	static String x; //T������ �߰��� ����� ���
	static String s ="2"; //�ٿ��� ���
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
				System.out.printf("%8d", d[i]); //��� ���
			if (path[i]==null)
				System.out.printf("%12s", "-");
			else System.out.printf("%12s", path[i]); //��� ���
		}
		System.out.println();
	}
	public static int choose() { //���� ���� ��带
		int min = INF;
		int minpos = -1; //���� ���� ���
		for (int i=0; i<graph.length; i++) {
			if (d[i]<min && !v[i]) { //min���� �۰� T���տ� ���� ����� ��쿡
				min = d[i]; //�Ÿ���� ����
				minpos = i; // ��� ����
				T[it]=i+1; // T���տ� �߰�
				x=path[i]; //���� �߰��� ���
			}
		}
		it++;
		return minpos; //���� ���� ��� ��ȯ
	}

	public static void main(String[] args) {
		System.out.printf("%3s %13s %18s %10s %8s %10s %8s %10s %8s %9s %8s %10s", 
				"i" ,"T", "L(1)", "Path", "L(3)", "Path", "L(4)", "Path", "L(5)", "Path", "L(6)", "Path");
		System.out.println("");
		int l;
		
		for (int i=0; i<graph.length; i++) {
			v[i] = false; // �湮��� �迭 false�� �ʱ�ȭ
			d[i]=graph[s_int][i]; //�Ÿ����迭�� �̿� ��忡 ���� ��ũ ����� ����
			T[i]=0; //T������ 0���� �ʱ�ȭ
		}
		for(int i =0; i<graph.length; i++) {
			if(d[i] != INF && d[i] != 0) //�̿��� ����̰� �ڽ��� �ƴ� �ٸ� ��忡 ����
				path[i]="2-"+(i+1); // ��θ� �߰��Ѵ�.
		}
		path[s_int]=s; //�ٿ��� ����� ��δ� �ڱ� �ڽ��� �ְ�
		T[0]=s_int+1; //T���տ��� �ٿ��� ��带 �ְ�
		v[s_int]=true; d[s_int]=0; //�湮���� �Ÿ������ �ʱ�ȭ�Ѵ�.
		
		print_status(); //�ʱ�ȭ�� ������ ���
		for(int i=0; i<6; i++) {
			l = choose(); //T���� �� �Ÿ������ ���� ��带 ã�´�.
			if (l == -1) //��� ��尡 T���տ� ���ԵǾ����Ƿ� �˰��� ����
				break;
			v[l] = true; //T���տ� �߰��� ����� true�� ��Ʈ
			for (int n=0; n<6; n++) {
				if(!v[n])
					//���� �߰��� ��带 ���ؼ� n�������� �Ÿ������ ���
					if(d[l] + graph[l][n] < d[n]) { 
						//���� �Ÿ� ��뺸�� �߰��� ��带 ���ļ� ���� ����� �� �۴ٸ� ����
						d[n] = d[l]+graph[l][n]; 
						//���� �߰��� ����� ��ο� ������ ��� �߰�
						path[n]=x+"-"+(n+1);
					}
			}
			//���� ���� ���
			print_status();
		}
	}
}
