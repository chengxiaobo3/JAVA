package com.example.cheng.test00;

/*转义字符
public cl\u0061ss Hello  //<- 转义字符
{
	public static void main(String args[])
	{
		System.out.println("Hello\n world!!!");  //<-编译后，换行
		//System.out.println("Hello\u000a world!!!");  <-错误的，因为预处理以后就会变成 //<-编译前换行（预处理时换行）
		//System.out.println("Hello
	    //   world!!!");                          <- 这样编译器认为是错误的，所以会报错
	}
}
*/

//不定长参数与foreach循环
/*
public cl\u0061ss Hello  //<- 转义字符
{
	//不定长参数 的函数
	static void f(String ... a)
	{
		for(String i:a)
		{
			System.out.println(i);
		}
	}


	static void g(String [] a)
	{
		for(String i:a)
		{
			System.out.println(i);
		}
	}
	//------------------------------


	public static void main(String args[])
	{
		//foreach 循环开始
		int [] a=new int[]{1,2,3,4,5,6};  //<- 刚开始写成了 int [] a=new int{1,2,3,4,5,6}; 所以不对
		int []aa={1,2,3,1,3};

		for (int i:a)
		{
			System.out.println(i);
		}

		//foreach 循环结束

		//不定长函数调用
		Hello.f("Hello","world","!!!");
	   // Hello.g("Hello","world","!!!");  <- 错误的，因为它的方法的参数是 (String [] a )
	    Hello.g(new String[]{"Hello","world","!!!"});//<- 刚开始写错是因为写成了 new String("Hello","world","!!!") <- 两处错误
    }
}
*/

//java 的变量的定义  <-  需要注意的地方
/*
public class Hello
{
	public static void main(String [] args)
	{
		{
			int i;//注意这个是正确的。
		}
		int i;
		{
			//int i; //这个是错误的
		}


		{
			int k;//注意这个是正确的。
		}

		{
			int k ;
		}


		for(int j=0;j<10;j++)
		{
			System.out.println(j);
		}
		 int j=3;
		 //这个也是不对的
//		for(int j=5;j<10;j++)
//		{
//			System.out.println(j);
//		}
	}
}
*/

public class Hello
{
	public static void main(String [] args)
	{
		outer:
		//System.out.println("out next line"); //有这一行是错误的
		for (int ii = 0; ii < 10; ++ii)
		{
			inner:
			for (int jj = 0; jj < 10; ++jj)
			{
				System.out.println("" + ii + " " + jj);

				continue outer; // 直接continue到++ii而不是++jj;
			}

			System.out.println("the next of second for ");
		}


		System.out.println("----------------");

		for (int ii = 0; ii < 10; ++ii)
		{
			for (int jj = 0; jj < 10; ++jj)
			{
				System.out.println("" + ii + " " + jj);
				break;
			}

			System.out.println("the next of second for ");
		}
	}
}
	