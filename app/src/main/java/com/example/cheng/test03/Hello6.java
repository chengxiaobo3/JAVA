package com.example.cheng.test03;
// 泛型 Generic

// 在没有泛型的年代，会这么写
// A类通过Object，接收任何类型的对象使用它们
/*
class A
{
	Object o;

	A(Object o)
	{
		this.o = o;
	}

	void foo()
	{
		System.out.println(o.toString());
	}

	Object getObject()
	{
		return o;
	}
}

public class Hello6
{
	public static void main(String[] args)
	{
		A a0 = new A("Hello World!");
		A a1 = new A(new Integer(100));

		a0.foo();
		a1.foo();

		// 但是，这样很不方便也很不安全
		// 比如这里，必须通过强制类型转换，把获得的Object对象引用
		// 转换成String对象引用。
		// 并且，这个强制转换是否正确，编译器是没办法替我们判断的
		// 程序的正确性，只能依赖开发者自己去保证。
		String s = (String)a0.getObject();
		Integer i = (Integer)a1.getObject();

		try
		{
			// 编译器会认为这句话是对的，但是运行时会发生异常
			// 对于编译器来说，getObject()返回的就是一个Object
			// 而，程序的上下文是可以判断出这个Object其实是一个
			// Integer的。在Java支持泛型之前，这种信息被编译器
			// 浪费了。
			String s1 = (String)a1.getObject();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
*/

// 使用泛型的类的定义方法
// 这里T称为类型变量（Type variable），和普通的变量不同，类型变量
// 被用来表示一个不确定的类型

// **下面这个泛型类A编译后的结果其实和上面不使用泛型的A是完全一样的
// 类型变量T在编译后的class文件中，会被Object取代。
// 这被称为类型变量的擦除（Erasure）
// <T>这个称为泛型的形参表
/*
class A <T>
{
	// 其实就是Object o
	T o;

	A(T o)
	{
		this.o = o;
	}

	void foo()
	{
		System.out.println(o.toString());
	}

	// 其实返回的还是Object类型的引用
	T getObject()
	{
		return o;
	}
}

public class Hello6
{
	public static void main(String[] args)
	{
		// 使用泛型类的方法
		// 在类后面加泛型的实参表，给类型变量提供具体的类型
		// ** 给类型变量提供的具体类型不会影响A类中方法的行为，
		// 影响的只是a0 a1被使用时的行为。
		// 比如A<String>类型的a0的构造方法需要的参数是String，
		// 但事实上，A的构造方法内部只认为得到的参数是Object。
		A<String> a0 = new A<String>("Hello World!");
		A<Integer> a1 = new A<Integer>(new Integer(100));

		a0.foo();
		a1.foo();

		// 因为编译器现在能确定getObject返回的引用类型
		// 所以不再需要强制转换
		// getObject自己只认为返回的是个Object
		// 正确与否的判断是调用方进行的
		String s = a0.getObject();
		Integer i = a1.getObject();

		// 这个是编译器明确知道getObject返回的是Integer
		// 所以编译的时候就能发现错误。
		//String s1 = (String)a1.getObject();
	}
}
*/

/*
// 类型变量的上界
class Base
{
	void foo()
	{
		System.out.println("Base.");
	}
}

// 声明类型变量时通过extends修饰规定其上界
// 上界的意思就是，给T提供的具体类型，必须是
// 规定上界的子类
// 直接写<T>相当于写<T extends Object>
class A <T extends Base>
{
	// 编译后会erasure为Base t;
	T t;

	A(T t)
	{
		this.t = t;
	}

	void foo()
	{
		t.foo();
	}
}

class B extends Base
{
	void foo()
	{
		System.out.println("B.");
	}
}

public class Hello6
{
	public static void main(String[] args)
	{
		A<B> a0 = new A<B>(new B());

		a0.foo();

		// 错误，String不是Base的子类
		// A<String> a1 = new A<String>("123");

		new A<Base>(new Base()).foo();
	}
}
*/

// 通配类型（Wildcard type）
/*
class A <T>
{
	T t;

	A(T t)
	{
		this.t = t;
	}

	void foo()
	{
		System.out.println(t.toString());
	}
}

public class Hello6
{
	public static void main(String[] args)
	{
		// 定义一个泛型类的变量时，可以用?表示一个类型变量
		// 可以是任意类型。称作通配类型。
		A<?> a0 = new A<String>("Hello Wildcard.");
		A<?> a1 = new A<Integer>(100);
		A<String> a2 = new A<String>("Hello again.");

		a0.foo();
		a1.foo();

		// 正确，因为可以确定a2.t的类型是String
		String s = a2.t;

		// 不正确，因为a0.t的类型是?，不能保证是String
		// String s1 = a0.t;
	}
}
*/

// 通配类型的上下界
/*
class A <T>
{
	T t;

	A()
	{
	}

	A(T t)
	{
		this.t = t;
	}

	void setT(T t)
	{
		this.t = t;
	}

	T getT()
	{
		return t;
	}
}

public class Hello6
{
	public static void main(String[] args)
	{
		// 通过? extends ...的写法规定通配类型的上界
		// 下面的A<? extends Number>的意义是，赋值给这个类型变量
		// 的引用的第一个类型实参必须是Number的子类
		A<? extends Number> a0 = new A<Integer>(100);

		// String不是Number的子类，所以是错误的
		//A<? extends Number> a1 = new A<String>("Wrong.");

		// 因为限制A的T必须是Number的子类，所以可以保证
		// getT()返回的肯定是个Number的对象
		Number n = a0.getT();

		// 通过? super ...的写法规定通配类型的下界
		// 提供给类型变量的具体类型必须是下界的父类
		A<? super Integer> a1 = new A<Number>();

		// 因为a1的T是Integer的父类，所以可以保证Integer能
		// 安全的作为setT的参数。而阻止String作为其参数。
		a1.setT(100);
		//a1.setT("Wrong.");
	}
}
*/

// 泛型方法
/*
public class Hello6
{
	// 方法可以通过在返回类型前加类型形参表变为泛型方法
	// 和泛型类一样，泛型方法也有类型变量，同样类型变量
	// 也会在编译时被擦除。类型也有上界，默认是Object。
	// 总之全都一样。
	static <T> void foo(T t)
	{
		System.out.println(t.toString());
	}

	public static void main(String[] args)
	{
		// 调用的时候类型变量的实参会自动匹配。
		// 比如这里自动匹配为String
		foo("Hello World.");

		// 这里自动匹配为Integer
		foo(100);
	}
}
*/


// 通配类型的捕获
// 通配类型的捕获目的在于把多个相同的不确定类型，能视为同一个类型。
// 比如下面，如果T被提供了通配类型，上下文中可以看出t1和t2的类型是相同的
// 捕获的目的就是让编译器能意识到这种相同。
class A<T>
{
	T t1;
	T t2;

	A(T t)
	{
		t1 = t;
	}
}

public class Hello6
{
	// 这里即便T获得的是capture，也只能是一个capture
	// 于是a.t2和a.t1的类型都是那个唯一的captuer，
	// 所以是可以互相赋值的
	static <T> A<T> foo(A<T> a)
	{
		a.t2 = a.t1;

		return a;
	}

	public static void main(String[] args)
	{
		// 实际上对于通配类型Java不是完全视其为不确定类型
		// 这里就目前Java的行为
		// 对于a1的t1和t2的类型，会被具体规定为
		// "capture#??? of ?"
		// 假设一个变量的类型是"capture#123 of ?"
		// 而一个值的类型也是"capture#123 of ?"
		// 那么这个值就可以赋值给这个变量
		A<?> a1 = new A<String>("Hello.");

		// 目前Java的行为
		// 通配类型的捕获发生在表达式求值的时候
		// 下面两个t1的因为是分别求值的，所以会得到连个不同的
		// 捕获，导致两个t1的类型不同。
		// 这并不是一个合理的行为。
		//a1.t1 = a1.t1;

		// 对于foo的T，获得的具体类型是一个capture，而不是真正的具体类型
		foo(a1);

		// 这里T的capture会是"capture#??? of ? extends Number super Integer>
		// capture也会有上下界
		A<? extends Number> a2 = new A<Integer>(100);

		// 虽然是不确定类型，但是"capture of ? extends Number"是Number的子类
		Number n = a2.t1;
	}
}
