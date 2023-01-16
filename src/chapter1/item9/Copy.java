package chapter1.item9;

import java.io.*;

public class Copy {
	private static final int BUFFER_SIZE = 8 * 1024;
	static void copy(String src, String dst) throws IOException {
		// 1. try-finally
		// 예외는 try 블록과 finally 블록 둘다 발생할 수 있는데 이경우에는 후자의 에외만 던져서 추적하기 쉽지않다. 또 지져분하다
		// 
//		InputStream in = new FileInputStream(src);
//		try {
//			OutputStream out = new FileOutputStream(dst);
//			try {
//				byte[] buf = new byte[BUFFER_SIZE];
//				int n;
//				while ((n = in.read(buf)) >= 0 ) {
//					out.write(buf,0,n);
//				} 
//			} finally {
//				out.close();
//			}
//		} finally {
//			in.close();
//		}
		
	    // 2. try-with-resources 
		// try 문을 벗어나면 try-with-resources는 try(...) 안에서 선언된 객체의 close() 메소드들을 호출합니다. 
		// 그래서 finally에서 close()를 명시적으로 호출해줄 필요가 없습니다.
       try (InputStream in   = new FileInputStream(src);
    		OutputStream out = new FileOutputStream(dst);) {
    		byte[] buf = new byte[BUFFER_SIZE];
			int n;
			while ((n = in.read(buf)) >= 0 ) {
				out.write(buf,0,n);
			}
       }

	}
public static void main(String[] args) throws IOException {
    String src = args[0];
    String dst = args[1];
    copy(src, dst);
}
}
