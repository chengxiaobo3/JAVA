package com.example.cheng.test01;
/*
// 接口
// 接口某种意义上相当于抽象类，但是接口完全不能有实体——只有有抽象方法，不能有对象成员域。
// 另外也不能有类方法和非final的类成员域
interface I
{
	int x = 10; // 默认有public static final修饰

	void foo(); // 默认有public abstract修饰
}

// 接口可以继承其他接口
interface J extends I
{
	void goo();
	void foo(); // 如果在接口继承结构中有相同的方法，视为同一个
}

// 类通过加implements实现（也就是继承）接口
class A implements I
{
	// 覆盖接口的方法时，必须是public访问权限，因为接口中的抽象方法都是public的
	public void foo()
	{

	}
}

// 类可以实现多个接口（因为接口没有实体，所以不会造成真正意义上的多继承，只会造成虚方法表的多继承，而虚方法表的多继承要比真正的多继承简单）
// 类如果没有实现接口的所有抽象方法，那么这个类必须是抽象类
abstract class B implements I, J
{
	public void goo()
	{
	}
}

class C implements I, J
{
	// 虽然I和J分别有一个foo，J继承I有多了一个foo，但是其实这三个foo是同一个
	public void foo()
	{
	}

	public void goo()
	{
	}
}

public class Hello3
{
	public static void main(String[] args)
	{
		I i = new C();

		// 接口默认含有Object的所有方法的抽象方法
		i.toString();
	}
}
*/

// 内嵌类和内部类

/*
class A
{
	int x = 10;

	// 内嵌(embeded)，只是写在A中的另一个类而已
	// 可以有private访问权限导致只能在A内使用
	public static class B
	{
	}

	// 内部类(inner class)
	// 相当于普通的类，但是有一个隐藏的A类的对象引用。
	// 和内嵌类一样可以通过private设为私有
	public class C
	{
		// 有一个类似这样的隐藏成员
		// 内部类的秘密仅此而已
		// private A outer;

		// 其实有两个参数，而不是只有一个this，还有一个传进来的A的对象引用，用于初始化outer
		C()
		{
		}

		void foo()
		{
			// A.this 其实就是隐含的那个 outer
			System.out.println(A.this.x);

			// A.super就是外套类的super
			// A.super.equals(this);
		}
	}

	// 遍以后这两个C在代码逻辑上是相同的
//	public static class C
//	{
//		private A outer;
//
//		C(A outer)
//		{
//			this.outer = outer;
//		}
//
//		void foo()
//		{
//			System.out.println(outer.x);
//		}
//	}

	C foo()
	{
		// 内部类必须在外套类对象的上下文中实例化
		C c = new C();

		// 在A的对象成员方法中，直接new C()相当于写this.new C()，把this.省略了，因为this.总是可以省略的就像this.x可以写成x一样
		// C c = this.new C();

		// 对于那个public static class C
		// C c = new C(this);

		return c;
	}


	static C goo()
	{
		// 没有外套类对象的上下文，因为是静态方法
		//C c = new C();

		// 5.0以后可以通过“对象.new”来提供外套类对象。
		A a = new A();
		C c = a.new C();

		return c;
	}

	// 接口和枚举默认带有static，不可能是内部的，只能是内嵌的
	interface I
	{
	}

	enum E
	{
	}
}

public class Hello3
{
	public static void main(String[] args)
	{
		A.C c = A.goo();

		c.foo();

		A a = new A();

		a.x = 100;

		A.C c0 = a.foo();

		//直接写C而不是A.C
		A.C c1 = a.new C();

		c0.foo();

		// 内嵌类
		A.B b = new A.B();

	}
}
*/

/* local class
abstract class Base
{
	abstract void foo();
}

class A
{
	int x;

	Base zoo(final int n)
	{
		// 局部类 (local class)
		class D extends Base
		{
			void foo()
			{
				// 局部类直接使用局部变量，要求是变量必须有final修饰。
				System.out.println(n + " " + A.this.x);
			}
		}

		// 相当于
//		class D extends Base
//		{
//			private A outer;
//			final int local_n;
//
//			D(A outer, int local_n)
//			{
//				this.outer = outer;
//				this.local_n = local_n;
//			}
//
//			void foo()
//			{
//				System.out.println(local_n + " " + outer.x);
//			}
//		}

		Base b = new D();

		// 相当于
//		Base b = new D(this, n);

		return b;
	}
}

public class Hello3
{
	public static void main(String[] args)
	{
		A a = new A();
		Base base = a.zoo(10);

		base.foo();
	}
}
*/

// anonymous inner class 匿名内部类

abstract class AA {
    abstract void foo();
}

class BB {
    AA a;

    BB(AA a) {
        this.a = a;
    }

    void goo() {
        a.foo();
    }
}

public class Hello3 {
    public static void main(String[] args) {
        // 匿名内部类（严格说这个是匿名局部类）
        AA a = new AA() {
            void foo() {
                System.out.println("I'm anonymous.");
            }
        };

        // 上面的匿名类相当于这么一个类，只是上面这种写法，可以不给这个类提供类名，因为只需要实例化一次，没必要再次使用。
//		class C extends A
//		{
//			void foo()
//			{
//				System.out.println("I'm anonymous.");
//			}
//		}

        BB b = new BB(a);

        b.goo();
    }
}