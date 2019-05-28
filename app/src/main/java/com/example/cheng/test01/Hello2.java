package  com.example.cheng.test01;
// 继承
/*
class A
{
	int x = 1;

	// Java中对象成员方法都是虚方法
	// Java里所有对象成员方法默认都是通过虚方法表调用的，而不是直接调用的
	// 除了三个特例：构造方法，私有方法，加final修饰的方法。因为这三种方法不可能被覆盖，所以没必要动态调用。
	// 或者说在编译的时候就能确定被调用的方法是那个。
	void foo()
	{
		System.out.println("I'm an A." + x);
	}

	A goo()
	{
		return this;
	}
}

class B extends A
{
	int x = 2;

	B()
	{
		//x = super.x;
	}

	void foo()
	{
		// B的x隐藏了A的x的声明，所以这里的x只能是B的x。
		System.out.println("I'm a B." + x);
	}

	// Java 5.0以后允许覆盖方法修改返回类型。但是返回类型必须是父类被覆盖方法的返回类型的子类。因为这样不会发生类型冲突。
	B goo()
	{
		return this;
	}
}

class C extends A
{
	void foo()
	{
		// 因为在A的x的作用域内，并且A的x没有被隐藏，所以x是A的x
		System.out.println("I'm a C." + x);
	}
}

public class Hello2
{
	public static void main(String args[])
	{
		// 多态
//		A[] a = {new A(), new B(), new C()};
//
//		for (A p : a)
//		{
//			// 因为是通过A类访问的x，所以是A的x。成员域不存在子类覆盖父类的情况。子类域只会隐藏父类域。（变量不存在“虚变量”）
//			System.out.println(p.x);
//
//			p.foo();
//		}

		A a = new A();
		B b = new B();

		A a0 = a.goo();
		A a1 = b.goo(); //b.goo()虽然该变了返回类型，但是其返回类型是在a.goo()的类型之内的，因为B本来就是A的子类。 不会改变A类中给goo的行为规定的限制。
	}
}
*/

// final
/*
final class A
{
}

class B extends A // 不可以因为A不允许继承
{
}
*/

/*
class A
{
	final void foo()
	{
	}
}

class B extends A
{
	void foo() // 不可以，因为foo不允许覆盖
	{
	}
}
*/

// 静态成员
/*
class A
{
	static int x; // 其实就是全局变量，因为Java里不能在类外面写东西，所以全局变量都写在类里
	static int foo() { return 0; } // 全局方法

	// 虚方法调用用的是invokevirtual <- 需要虚方法表
	// 其他情况是invokdespecial <- 直接调用 // 稍微快一点

	int goo()
	{
		A.x = 0; // 用“类名.成员名”访问静态成员

		x = 1;
		this.x = 2; // 用“对象.成员名”也可以访问静态成员
	}
}
*/

// instanceof运算 判断一个对象的类是否是给定类或者其子类

// 抽象方法 抽象类

// 有抽象方法的类必须是抽象类，类也需要用abstract修饰
// Object就是一个抽象类
// ** Java中抽象类不一定要有抽象方法，但是依然不能实例化
/*
abstract class A
//class A
{
	// 没有方法体的方法，相当于只限定了方法的参数和返回值，没限定具体实现（具体实现由子类提供）
	// 这种方法叫抽象方法，需要用abstract修饰。
	abstract void foo();

	void goo()
	{
		//A a = new A(); //抽象类不能用于实例化，只能作为父类存在。
		// 实例化抽象类，相当于造成对应的函数指针是空指针。
	}
}

class B extends A
{
	void foo()
	{
		System.out.println("Hello!");
	}
}

public class Hello2
{
	public static void main(String[] args)
	{
		A a = new B();

		// 抽象类只能作为父类使用，不能去实例化。（抽象类相当于一个“协议”，限制子类的行为）
		a.foo();
	}
}
*/

/*
// Object是所有类的终极父类
class A
// class A extends Object // 两种写法是一码事，没有继承就是继承Object
{
	void foo()
	{
		A a = null; // 空指针literal。类型就是隐藏的null type，null type是所有类的子类。所以null可以给所有类的引用赋值
	}
}
*/

/*
// Wrapper和Boxing

class A
{
	void foo()
	{
		// Object a = 1; // 非引用类型不能直接赋值给Object，Object只是所有引用类型的父类。

		Integer i = 1; // Integer类就是int类到引用类型的一个wrapper（把int裹起来变成一个对象）
		Object a = i; // 然后就可以赋值给Object引用变量了。

		Integer j = new Integer(1); // Java 5.0之前都需要这么写，称为Boxing
		Integer k = 1; // 5.0之后这么写就相当于上面的写法，称为Autoboxing

		int ii = i; // 5.0之后，Auto-unboxing，相当于5.0之前
		int jj = j.intValue();
	}
}
*/

/*
// 枚举

enum A
{
	GOOD(0), BAD(1), GREAT(2);

	A(int x)
	{
		this.x = x;
	}

	int x;

	void foo()
	{
		System.out.println(x);
	}
};

// 相当于
//final class A extends Enum
//{
//	static A GOOD = new A(0);
//	static A BAD = new A(1);
//	static A GREAT = new A(2);
//
//	private A(int x)
//	{
//		this.x = x;
//	}
//
//	int x;
//
//	void foo()
//	{
//		System.out.println(x);
//	}
//}

public class Hello2
{
	public static void main(String ... args)
	{
		A.GOOD.foo();
		A.BAD.foo();
		A.GREAT.foo();
	}
}
*/

class A
{
	public void foo()
	{
	}
}

class B extends A
{
	// 覆盖方法不能缩小访问权限
	// 这里必须是public void foo()
//	private void foo()
//	{
//	}
}