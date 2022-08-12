package co.micol.web;
//junit 단위테스트 @Test가 붙은 메서드를 자동으로 실행시킴(멀티스레드로 동시에 다 실행)
//나중에 배포할때 빠짐
import org.junit.Test;


public class DaoTest {

		@Test
		public void test1() {
			System.out.println("aa");
		}
		
		@Test
		public void test2() {
			//int a=5/0;
			System.out.println("bb");
		}
}
