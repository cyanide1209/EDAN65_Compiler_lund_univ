package p;
import p.A;

public class B{
	public static void main(String[] args){
		System.out.println("Hello "+args[0]+"!");
		A a = new A();
		System.out.println(a.toString());
		/*
		while(true){
			System.out.println("Foo");
		}
		*/
	}
}
