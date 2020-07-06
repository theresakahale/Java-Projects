package assignment5;

import java.util.Arrays;

public class Queue {
	private Object[] arr;
	private int front;   // dequeue 
	private int rear;    // enqueue

	
	public Queue() {
		arr = new Object[5];
		front = 0;
		rear = 0;
	}
	
	public void enqueue(Object obj) {
		if(rear == front && front !=0) {
			// i want to add and loop is full
			setArr(increaseArray(arr));
			arr[rear] = obj;
			rear++;
			return;
		}
		if(rear == arr.length) {
			// array at the end
			if(front>0) {
				// i have no elt at the beginning
				arr[0]= obj;
				rear = 1;
			}
			else if(front == 0) {
				setArr(copyArray(arr));
				arr[rear] = obj;
				rear++;
			}
		}
		else{
			arr[rear] = obj;
			rear++;
			
		}
	}
	
	public Object dequeue() {
		if(arr[front] == null) {
			System.out.println("Queue is empty!");
			return null;
		}
		Object obj = arr[front];
		arr[front] = null;
		front++;
		if(rear == front) {
			front = rear = 0;
		}
		if(front == arr.length) {
			if(rear>0)
				front = 0;	
		}
		return obj;
	}
	
	public boolean empty() {
		return (front==rear && arr[front]==null);
	}
	
	public Object peek() {
		if(!empty())
			return arr[front];
		else
			return null;
	}
	
	public Object[] copyArray(Object[] array) {
		// copy array as it is 
		Object[] new_array = new Object[array.length*2];
		for(int i=0; i<arr.length;i++) {
			new_array[i] = arr[i];
		}
		return new_array;
	}
	
	public Object[] increaseArray(Object[] array) {
		Object[] new_array = new Object[array.length*2];
		int pointer = 0;
		for(int i=front; i<arr.length;i++) {
			new_array[pointer]= arr[i];
			pointer++;
		}
		for(int i=0; i<rear;i++) {
			new_array[pointer]= arr[i];
			pointer++;
		}
		front = 0; 
		rear = pointer;
		return new_array;
	}
	
	public Object[] getArr() {
		return arr;
	}

	public void setArr(Object[] array) {
		arr = array;
	}

	public int getFront() {
		return front;
	}

	public void setFront(int f) {
		front = f;
	}

	public int getRear() {
		return rear;
	}

	public void setRear(int r) {
		rear = r;
	}
	
	public String display() {
		if (empty())
			return "Queue is empty!";
		String ans = "Queue [arr=";
		if(front ==0) {
			for(int i=0; i<rear;i++) {
				ans+= " " + arr[i];
			}
		}
		else if(front <rear) {
			for(int i=front; i<rear;i++) {
				if(arr[i]!= null)
					ans+= " " + arr[i];
			}
		}
		else {
			for(int i=front; i<arr.length;i++) {
				if(arr[i]!= null)
					ans+= " " + arr[i];
			}
			for(int i=0; i<rear;i++) {
				if(arr[i]!= null)
					ans+= " " + arr[i];
			}
		}
		return ans+"] front=" + front+", rear=" + rear;
	}
	
	@Override
	public String toString() {
		return "Queue [arr=" + Arrays.toString(arr) + ", front=" + front + ", rear=" + rear + "]";
	}

}
