package com.example.cheng.test02;
/*
// 线程 I 最基本的线程
// 使用线程的一种方式是，继承Thread类
class A extends Thread
{
	int x = 0;
	int id;

	A(int id)
	{
		this.id = id;
	}

	// 实现run方法作为线程的主体
	public void run()
	{
		for (; x < 500; ++x)
		{
			System.out.println("" + id + " " + x);
		}
	}
}

public class Hello5
{
	public static void main(String[] args)
	{
		// 也可以写A a = new A(1);
		Thread a = new A(1);
		Thread b = new A(2);

		// 调用线程对象的start方法，会建立一个线程
		// 并在那个线程中执行线程对象的run方法
		// start方法会立即返回，不会等run方法返回
		a.start();
		b.start();

		// 这个时候程序一共有三个线程，a和b的run运行的线程，
		// 和main运行的线程。一般把main运行的线程，叫做主线程
		for (int x = 0; x < 500; ++x)
		{
			System.out.println("main " + x);
		}
	}
}
*/

/*
// 线程 II 另一种方法
class A implements Runnable
{
	public void run()
	{
		for (int x = 0; x < 100; ++x)
		{
			System.out.println("Thread: " + x);
		}
	}
}

// Thread类的一部分
//class Thread implements Runnable
//{
//	Thread(Runnable runnable)
//	{
//		this.runnable = runnable;
//	}
//
//	Runnable runnable;
//
//	public void run()
//	{
//		runnable.run();
//	}
//}


public class Hello5
{
	public static void main(String[] args)
	{
		// 这里必须是Thread a，因为new的是Thread
		// 其实就是说，Thread类默认的run方法就是执行
		// Thread对象的一个Runnable对象的run方法
		Runnable r = new A();
		Thread a = new Thread(r);

		a.start();

		for (int x = 0; x < 500; ++x)
		{
			System.out.println("main " + x);
		}
	}
}
*/

/*
// 线程 III 基本同步
class A extends Thread
{
	// volatile的意义在于，
	// 可以这么理解，如果不加volatile，修改变量的时候改的是寄存器
	// 不一定会修改内存内容，读取变量时，如果变量值在寄存器里，就会
	// 直接读取寄存器的内容。如果加了volatile，读写都会直接在内存
	// 中进行。
	// 平时加volatile会降低效率，但是对于多个线程共同使用的变量，
	// 如果不加volatile，相当于每个线程有都有自己的一个变量，
	// 就会发生错误。
	// 好比有个数据库，如果两台电脑都把数据读到自己的内存中操作，
	// 分别都给同一个数据加了50次1，结果是两台电脑分别由一个加了50
	// 的这个数据。这是没有volatile的情形。
	// 如果有volatile，那么就是这两台电脑都是直接把数据库里的数据加1。
	// 这样的结果，就是数据库里的那个数据最后被加了100。
	static volatile int x = 0;

	int id;
	Object lock;

	A(int id, Object lock)
	{
		this.id = id;
		this.lock = lock;
	}

	public void run()
	{
		for (;;)
		{
			// 锁
			// Java里可以简单地通过synchronized关键字使用锁
			// 当然Java SE中也有独立的锁对象，这里暂时不介绍。
			// Java中任何对象都可以通过synchronized关键字作为锁使用。
			// 下面就是把lock引用的对象作为锁使用。
			// 进入synchronized语句块时会尝试获得lock的锁，如果其他
			// 线程正在占用，就会等待它释放lock的锁。
			// 离开synchronized语句块时（无论是正常离开，还是通过return
			// 或者break等跳转离开）就会释放lock的锁，其他线程就有机会获得。
			// 下面这个处理实际效果是两个线程同一时间只能有一个执行，
			// 某种意义上说，又变回了单线程。如果synchronized语句块外
			// 还进行其他操作就不是单线程了。

			// 注意，synchronized语句块中的代码不会和其他获取相同对象
			// 的锁的synchronized语句块同时执行（都是synchronized(lock)的语句块
			// 不会同时执行）。但是synchronized的语句块可以和非synchronized
			// 语句块同时执行。获取不同对象的锁的synchronized语句块也可以同时执行
			// （如果lock1和lock2引用的不是同一个对象，synchronized(lock1)得语句块
			// 和synchronized(lock2)的语句块是可以同时执行的。
			synchronized (lock)
			{
				if (x < 500)
				{
					System.out.println("" + id + " " + x++);
				}
				else
				{
					break;
				}
			}

//			if (x < 500)
//			{
//				System.out.println("" + id + " " + x++);
//			}
//			else
//			{
//				break;
//			}
		}
	}
}

public class Hello5
{
	public static void main(String[] args)
	{
		Object l = new Hello5();

		Thread a = new A(1, l);
		Thread b = new A(2, l);

		a.start();
		b.start();
	}
}
*/

// 线程 IV 同步的另一种写法

/*
class A
{
	synchronized void foo()
	{
		// ...
	}

	// 上面的foo等效于这么写
//	void foo()
//	{
//		synchronized(this)
//		{
//			// ...
//		}
//	}

	static synchronized void goo()
	{
	}

	// 上面的goo大体相当于这么写
	// 就是获取A类对象的锁（一个Class对象）
//	static void goo()
//	{
//		synchronized(Class.forName("A"));
//	}
}
*/

// 事件（条件）
/*
class A extends Thread
{
	static int x = 0;

	Object signal;

	A(Object s)
	{
		signal = s;
	}

	public void run()
	{
		// 要等待一个对象的事件，首先要获得这个对象的锁
		synchronized(signal)
		{
			System.out.println("A before waiting... " + x);

			try
			{
				// 通过调用这个对象的wait方法开始等待这个对象的事件
				// 进入等待状态时会释放这个对象的锁
				// 当这个对象的事件被别的线程通过调用这个对象的
				// notify或者notifyAll触发后，这个线程就会再次
				// 试图获得这个对象的锁，获得后继续向下执行
				signal.wait();
			}
			catch (Exception e)
			{
			}

			System.out.println("A after waiting..." + x);
		}
	}
}

public class Hello5
{
	public static void main(String[] args)
	{
		Object s = new Hello5();

		Thread a = new A(s);

		System.out.println("Main before starting A...");

		a.start();

		A.x = 100;

		try
		{
			Thread.sleep(1000);
		}
		catch (Exception e)
		{
		}

		// 触发一个对象的事件同样需要获得它的锁
		synchronized(s)
		{
			System.out.println("Main before waking A...");

			// 可以通过调用这个对象的notify或者notifyAll触发它的事件
			// notify会随机选择一个等待事件的线程唤醒
			// notifyAll会唤醒所有等待这个对象事件的线程
			// 但是被唤醒的线程不会立即执行，必须等待调用notify的线程
			// 释放锁以后才能执行。
			s.notify();

			System.out.println("Main after waking A...");
		}
	}

	// 这个是对的
//	synchronized void foo()
//	{
//		notify();
//	}

	// 相当于
//	void foo()
//	{
//		synchronized(this)
//		{
//			this.notify();
//		}
//	}
}
*/

// 停止线程
/*
class A extends Thread
{
	public void run()
	{
		for (;;)
		{
			System.out.println("!");
		}
	}
}

public class Hello5
{
	public static void main(String[] args)
	{
		Thread a = new A();

		a.start();

		try
		{
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
		}

		// 强制停止线程，不建议用的方法
		a.stop();
	}
}
*/

/*
class A extends Thread
{
	public void run()
	{
		// 如果线程被标记需要停止，循环结束
		// 当然被标记需要停止的线程，也可以无视标记，继续执行
		for (; !isInterrupted();)
		{
			System.out.println("!");
		}
	}
}

public class Hello5
{
	public static void main(String[] args)
	{
		Thread a = new A();

		a.start();

		try
		{
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
		}

		// 标记线程需要停止，但是不会停止线程
		a.interrupt();
	}
}
*/

class A extends Thread
{
	public void run()
	{
		try
		{
			Thread.sleep(10000000);
		}
		// 如果线程在等待状态（wait方法，或者sleep方法等）被中断，就会产生
		// 一个InterruptedException异常，同时结束等待状态。
		// 如果不这样设定，等待中的线程将没有机会判断是否被标记需要停止。
		catch (InterruptedException e)
		{
			System.out.println("Interrupted!");
		}
	}
}

public class Hello5
{
	public static void main(String[] args)
	{
		Thread a = new A();

		a.start();

		try
		{
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
		}

		// 标记线程需要停止，但是不会停止线程
		a.interrupt();
	}
}
