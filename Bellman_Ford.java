public class Bellman_Ford {
	static int INF = 1000000;
	//����ġ �׷��� ���� �迭
	static int[][] graph = {
			{0, 2, 5, 1, INF, INF},
			{3, 0, 3, 2, INF, INF},
			{8, 6, 0, 3, 1, 5},
			{7, 2, 3, 0, 1, INF},
			{INF, INF, 1, 1, 0, 2},
			{INF, INF, 8, INF, 4, 0}
	};
	static int h = 0; //��ũ ��
	static int l [][] = new int[6][6]; //��ũ �� ���� �Ÿ������ �����ϴ� 2���� �迭
	static String path[] = new String[6]; //��θ� �����ϴ� �迭
	static int flag = 0; //���� Ȯ��
	static String s ="2"; //�ٿ��� ���
	static int s_int = Integer.parseInt(s)-1;
	//��ũ �� ���� ���� ��Ȳ�� ����ϱ� ���� ��ũ ���� �Ű������� �̿�
	public static void print_status(int h) {
		System.out.print("  "+h);
		for (int i=0; i<l.length; i++) {
			if (i==s_int) continue;
			if (l[h][i] == INF) 
				System.out.printf("%10s", "INF");
			else System.out.printf("%10d", l[h][i]); //��� ���
			if (path[i]=="2") 
				System.out.printf("%12s", "-");
			else System.out.printf("%12s", path[i]); //��� ���
		}
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.printf("%3s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s", 
				"h", "L(1)", "Path", "L(3)", "Path", "L(4)", "Path", "L(5)", "Path", "L(6)", "Path");
		System.out.println("");

		//��� ���� �迭 �ʱ�ȭ
		for(int h =0; h<l.length; h++) {
			for(int n=0; n<l.length; n++) {
				l[h][n]=INF;
			}
		}
		//�ٿ��� ��� 0���� �ʱ�ȭ
		for (int i=0; i<l.length; i++) {
			l[i][s_int]=0;
		}
		//��� ���� �迭�� �ٿ��� ��� ����
		for(int i =0; i<l.length; i++) {
			path[i]=s;
		}
		//��ũ�� 0�϶� ����Ʈ
		print_status(0);
		//�������� �˰��� ����, ��ũ �ϳ��� ������
		for(h=1; h<graph.length; h++) { //��ũ�� �߰�, ��ũ�� ����Ŭ�� ������ ��� ����� �����ٴ� �۾ƾ��Ѵ�.
			for (int n=0; n<graph.length; n++) { //n���� ���� �ּҺ�� ���
				for (int j=0; j<graph.length; j++) { //j����� ���ļ� n���� ����
					if (n==s_int) continue; //�������� �ٿ��� ����̸� �ٸ� ��带 ����Ѵ�. 
					if(graph[j][n] != INF && graph[j][n] != 0) { //�̿��� ����̰� �ڽ��� �ƴ� �ٸ� ��忡 ����
						//j��带 ���ļ� ���� ��� �� ���� ���� ����� ã�´�. 
						if (l[h-1][j] + graph[j][n] < l[h][n]) { 
							l[h][n] = l[h-1][j]+graph[j][n]; //j��带 ��ġ�鼭 ���� ���� ����� ã�Ƽ� �����ϰ�
							path[n] = path[j]+"-"+(n+1);//j��带 ���� ������ n������ ��θ� ����
						}
					}
				}
			}
			
			//h�� h-1�� ����� ���ؼ� ���̰� ���ٸ� flag+1
			for (int i=0; i<l.length; i++) {
				if(i==s_int) continue;
				if(l[h-1][i]==l[h][i])
					flag++;
			}
			//��� ����� ������ ���ٸ� �˰��� ������ �����ϰ� for���� �������´�.
			if (flag == 5) {
				print_status(h);
				break;
			}
			//�ϳ��� ����� ������ �ִٸ� flag�� �ٽ� 0���� ��Ʈ, �˰��� �ݺ�
			else {
				print_status(h);
				flag=0;
			}
		}
	}
}
