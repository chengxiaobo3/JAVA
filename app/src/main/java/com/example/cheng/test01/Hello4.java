package com.example.cheng.test01;
// 错误/异常

/*
// Throwable接口，所有错误和异常都是实现Throwable接口
// 错误的父类是Error，异常的父类是Exception
// 异常又分为RuntimeException(unchecked exception?)和其他的异常（checked exception）

// Error和RuntimeException不需要人为捕获，checked exception必须捕获

class MyException extends Exception
{
}

public class Hello4
{
	static void f0() throws MyException
	{
		throw new MyException();
	}

	static void f1()
	{
		// f0(); // 不允许的，checked exception必须处理

		try
		{
			f0();
		}
		catch (MyException e)
		{
			// 处理MyException异常
		}
	}

	static void f2() throws MyException
	{
		f0(); // 可以的，因为对MyExcetpion的处理方法是抛给调用函数
	}

	static void f3()
	{
		// 对于unchecked异常，不需要处理。但是不处理的话，一样会导致线程崩溃。
		throw new ArithmeticException(); // ArithmeticException是RuntimeException的子类
	}

	public static void main(String[] args) throws Exception
	{
		try
		{
			f2(); // f2会抛出MyExcetpion，所以必须在try里调用
		}
		// catch中可以用父类捕获所有子类的异常对象
		catch (Exception e)
		{
			// throw e; // 不可以直接抛出catch的参数（7.0以后就可以直接写了）
			//throw new Exception(e); // 需要创建新的异常对象向下抛出（Java 7.0以后就不需要这样了，但是现在用的是6.0）
		}

		f3();
	}
}
*/

public class Hello4
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println("Hello try.");

			// Java不允许有不可能执行到的语句
			// 比如
			// return;
			// x = 0; <- 无法执行到的语句。
			// 但是
			// if (1 == 1) return;
			// x = 0; <- 不会被认为无法执行到，虽然其实还是无法执行到
			if (1 == 1) throw new Exception();

			System.out.println("Hello throw.");
		}
		catch (Exception e)
		{
			System.out.println("Hello catch.");
		}
		finally
		{
			System.out.println("Hello finally.");
		}

		try
		{
			System.out.println("Hello try.");

			// try和catch中，任何位置发生会离开当前try/catch/finally语句块的行为，都会执行finally的内容
			// finally内的语句无论如何都会被执行。
			return;
		}
		catch (Exception e)
		{
			System.out.println("Hello catch.");
		}
		finally
		{
			System.out.println("Hello finally.");
		}

		// 一个try可以带好几个catch，发生异常的时候会逐个判断是否是对应的处理语句
		try
		{
		}
		// 如果发生的异常是ArithmeticException，就会在这里被处理，否则判断下面的catch是否符合条件
		catch (ArithmeticException e)
		{
		}
		// 如果异常没有在上面被捕获，就会到这里来判断。当然所有漏掉的异常都回在这里被处理，因为捕获用的类型是Exception
		catch (Exception e)
		{
		}
		finally
		{
		}
	}
}