package com.cloud.list;

import java.util.Stack;

public class StackDemo {
	
	public static void main(String[] args) {
		
		Stack<String> stack = new Stack<>();
		
		stack.push("First");
		stack.push("second");
		stack.push("third");
		stack.push("fourth");
		
		System.out.println(stack);
		
		System.out.println(stack.peek());
		
		stack.pop();
		
		System.out.println(stack);
		
		System.out.println(stack.peek());
	}
	
}
