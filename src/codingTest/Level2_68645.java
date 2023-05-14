package codingTest;

// 삼각다랭인
public class Level2_68645 {
	 static int answerArr[][];
	 static int value = 1;
	 static int x = 0;
	 static int y = 0;
	 static int n = 0;
	public Level2_68645(int n) {
		 answerArr = new int[n][n];
		 this.n = n;
	}
	
	public static int[] result() {
		 while(true) {
	            // 1. 위에서 아래로
	            while (true) {
	                answerArr[y][x] = value++;
	                if(y + 1 == n || answerArr[y+1][x] != 0) break;
	                System.out.println("answerArr[" + y + "][" + x + "]  : " + answerArr[y][x] );
	                y += 1;
	                
	            }
	            if(x+1 == n || answerArr[y][x+1] != 0)  break;
	            System.out.println("answerArr[" + y + "][" + x + "]  : " + answerArr[y][x] );
	            x += 1;
	            
	            // 2. 오른쪽으로 
	            while (true) {
	                answerArr[y][x] = value++;
	                if(x + 1 == n || answerArr[y][x+1] != 0) break;
	                System.out.println("answerArr[" + y + "][" + x + "]  : " + answerArr[y][x] );
	                x += 1;
	                
	            }
	            if(answerArr[y-1][x-1] != 0)  break;
	            System.out.println("answerArr[" + y + "][" + x + "]  : " + answerArr[y][x] );
	            x -= 1;
	            y -= 1;
	            
	            // 3. 왼쪽위로 이동 
	            while (true) {
	                answerArr[y][x] = value++;
	                if(answerArr[y-1][x-1] != 0) break;
	                System.out.println("answerArr[" + y + "][" + x + "]  : " + answerArr[y][x] );
	                 x -= 1;
	                 y -= 1;
	                 
	            }
	            if(y+1 == n ||  answerArr[y+1][x] != 0)  break;
	            System.out.println("answerArr[" + y + "]["+x+"]  : " + answerArr[y][x] );
	            y += 1;
	        }
	        
	        int[] answer = new int[value-1];
	        int index = 0;
	        for(int i = 0; i < n; i++) {
	            for (int j = 0; j <= i; j++) {
	                answer[index++] = answerArr[i][j];
	            }
	        }
	        return answer;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Level2_68645 cls = new Level2_68645(5);
		int[] ans = cls.result();
		
		for(int i : ans) {
			System.out.println("value : " + i);
		}
       
	}

}
