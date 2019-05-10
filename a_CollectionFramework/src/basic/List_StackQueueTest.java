package basic;

import java.util.LinkedList;

public class List_StackQueueTest {

	public static void main(String[] args) {
		/*
		 * Stack > 후입선출(LIFO)의 자료구조
		 * Queue > 선입선출(FIFO)의 자료구조 
		 * Stack과 Queue는 LinkedList를 이용하여 사용할 수 있다 
		 */
		
		/*
		 * Stack의 명령 
		 * 1) 자료입력 : push(저장할 값)
		 * 2) 자료출력 : pop() > 자료를 꺼내온 후 꺼내온 자료를 stack에서 삭제한다 
		 */
		LinkedList<String> stack = new LinkedList<>();
		
		System.out.println("stack");
		System.out.println();
		stack.push("red");
		stack.push("blue");
		stack.push("yellow");
		System.out.println("stack > " + stack);
		
		String data = stack.pop();
		System.out.println("꺼낸 데이터 > " + data);
		System.out.println("stack > " + stack);
		
		stack.push("dark");
		System.out.println("push > dark" );
		System.out.println("stack > " + stack);
		System.out.println("꺼낸 데이터 > " + stack.pop());
		System.out.println("stack > " + stack);
		System.out.println("-------------------------------------");
		
		
		/*
		 * Queue의 명령 
		 * 1)자료입력 : offer(저장할 값)
		 * 2)자료출력 : poll > 자료를 Queue에서 꺼내온 후 꺼내온 자료는 Queue에서 삭제된다  > 큐안에 자료가 없을때는 null값을 return 한다 
		 */
		LinkedList<String> queue = new LinkedList<>();
		
		System.out.println("queue");
		System.out.println();
		
		queue.offer("red");
		queue.offer("green");
		queue.offer("yellow");
		queue.offer("pink");
		
		System.out.println("queue > " + queue);
		data = queue.poll();
		System.out.println("꺼낸 데이터 > " + data);
		System.out.println("queue > " + queue);
		
		queue.offer("dark");
		System.out.println("offer > dark");
		System.out.println("queue > " + queue);
		System.out.println("꺼낸 데이터 > " + queue.poll());
		System.out.println("queue > " + queue);
	}

}
