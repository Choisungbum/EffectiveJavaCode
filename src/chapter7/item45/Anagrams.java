package chapter7.item45;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Stream;



/*
 * item 45 스트림은 주의해서 사용하라 
 * 
 * 스트림의 구성
 * 스트림 -> 데이터 원소의 유한 혹은 무한 시퀀스를 뜻한다
 * 스트림파이프라인 -> 원소들로 수행하는 연산단계를 표현하는 개념 
 * 데이터 원소 -> 객체참조나 기본타임(int,long,double)
 * 
 * 스트림 파이프라인 진행과정
 * 소스 스트림
 * 중간연산 - map, filter, sorted, flapMap
 * 종단연산 - Collect, forEach
 * 
 * Lazy Evaluation(지연평가) 
 * -> 불필요한 연산을 피하기 위해 연산을 지연시키는것 
 * 
 * 스트림을 과용하면 프로그램이 읽거나 유지보수하기 어려워진다.
 * 
 * 코드블럭에서 할 수 있는 일을 스트림에서 할 수 없는경우가 있다. (ex) return문, break, continue 사용 등)
 * 
 * 다음 일들에 스트림을 사용하기 좋다
 * 1. 원소들의 시퀀스를 일관되게 변환한다.
 * 2. 원소들의 시퀀스를 필터링한다.
 * 3. 원소들의 시퀀스를 하나의 연산을 사용해 결합한다
 * 4. 원소들의 시퀀스를 컬렉션에 모은다.(공통된 속성을 기준으로 묶어가며)
 * 5. 원소들의 시퀀스에서 특정 조건을 만족하는 원소를 찾는다.
 * 
 * !! 스트림과 반복 중 어느 쪽이 나은지 확신하기 어렵다면 둘 다 해보고 더 나은 쪽을 택하라!!
 * 
 * 
 * */

public class Anagrams {

	public static void main(String[] args) {
		//File dictionary = new File(args[0]);
		Path dictionary = Paths.get(args[0]);
		int minGroupSize = Integer.parseInt(args[1]);
		
		// 1 아나그램 출력 코드 
// 		Map<String, Set<String>> groups = new HashMap<>();
//		try(Scanner s = new Scanner(dictionary)){
//			while(s.hasNext()) {
//				String word = s.next(); 
//				groups.computeIfAbsent(alphabetize(word), 
//						(unused)-> new TreeSet<>()).add(word);
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		for(Set<String> group : groups.values()) {
//			if(group.size() >= minGroupSize) {
//				System.out.println(group.size() + ": " + group);
//			}
//		}
//			
//	}
		// 2. 스트림을 과하게 사용한 예 
//		try(Stream<String> words = Files.lines(dictionary)){
//			words.collect(
//					groupingBy(word -> word.chars().sorted().collect(StringBuilder::new
//							,(sb, c) -> sb.append((char) c), StringBuilder::append).toString()))
//				 .values().stream()
//				 .filter(group -> group.size() >= minGroupSize)
//				 .map(group -> group.size() + ": " + group)
//				 .forEach(System.out::println);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		try (Stream<String> words = Files.lines(dictionary)) {
			words.collect(groupingBy(word -> alphabetize(word))).values().stream()
					.filter(group -> group.size >= minGroupSize)
					.forEach(g -> System.out.println(g.size() + ": " + g));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static String alphabetize(String s) {
		char[] a = s.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}

}
