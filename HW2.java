

import static java.lang.System.out;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HW2 {

	public static void main(String[] args) throws Exception {		
		
		testRational() ;
		
		out.println("==============================================================") ;
		
		testRationalUsingReflection() ;
		
		
	}


	static void testRational() throws Exception {
		Class aClass = Rational.class;
		Class bClass = Rational.class;
		Method[] meth = aClass.getDeclaredMethods();
		out.println(meth[0].getName());
		Rational r1 = new Rational(3,7);
		Rational r2 = new Rational(6,15);
		Field numerator1 = aClass.getDeclaredField("numerator"); 
		Field denominator1 = aClass.getDeclaredField("denominator"); 
		numerator1.setAccessible(true);
		denominator1.setAccessible(true);
		Field numerator2 = bClass.getDeclaredField("numerator"); 
		Field denominator2 = bClass.getDeclaredField("denominator"); 
		numerator2.setAccessible(true);
		denominator2.setAccessible(true);
		Constructor constructor = aClass.getConstructor(long.class, long.class);
		Constructor constructor2 = bClass.getConstructor(long.class, long.class);
		Object obj = constructor.newInstance(3,7);
		Object obj2 = constructor2.newInstance(6,15);

		

		
		Method getNumerator1 = aClass.getMethod("getNumerator");
		Method getdenominator1 = aClass.getMethod("getDenominator");
		Method add1 = aClass.getMethod("add",Rational.class);
		Method sub1 = aClass.getMethod("subtract",Rational.class);
		Method mul1 = aClass.getMethod("multiply",Rational.class);
		Method div1 = aClass.getMethod("divide",Rational.class);
		Method doublvalue1 = aClass.getMethod("doubleValue");
		Method compare1 = aClass.getMethod("compareTo",Rational.class);
		//Object c = add1.invoke(obj2,obj);
		
		out.println(obj+" has numerator : " + getNumerator1.invoke(obj) );
		out.println(r1 + " has numerator : "   + r1.getNumerator() +"   ----") ;
		out.println(obj+" has numerator : "+(long)numerator1.get(obj) +"  FIELD");
		//-------------------------------------------------------------
		out.println(r2 + " has denominator : " + r2.getDenominator() ) ;
		out.println(obj2 + " has denominator : " + getdenominator1.invoke(obj2) +"   ----") ;
		out.println(obj2 + " has denominator : " +(long)denominator1.get(obj2)+"  FIELD");
		
		
		
		out.println(r1 + " + " + r2 + " = " + r1.add(r2) ) ;
		out.println(obj + " + " + obj2 + " = "+add1.invoke(obj,obj2)+ "   ----" ) ;
		//------------------------------------------------------------------------------------------
		out.println(r1 + " - " + r2 + " = " + r1.subtract(r2) ) ;
		out.println(obj + " + " + obj2 + " = "+sub1.invoke(obj,obj2)+ "   ----" ) ;
		//------------------------------------------------------------------------------------------
		out.println(r1 + " x " + r2 + " = " + r1.multiply(r2) ) ;
		out.println(obj + " + " + obj2 + " = "+mul1.invoke(obj,obj2)+ "   ----" ) ;
		//------------------------------------------------------------------------------------------
		out.println(r1 + " / " + r2 + " = " + r1.divide(r2) ) ;
		out.println(obj + " + " + obj2 + " = "+div1.invoke(obj,obj2)+ "   ----" ) ;
		//------------------------------------------------------------------------------------------
		out.println(r2 + "has double value :" + r2.doubleValue()) ;
		out.println(obj + "has double value :"+ doublvalue1.invoke(obj2)+ "   ----" ) ;
		
		//int rlt = r1.compareTo(r2);
		int rlt = (int)compare1.invoke(obj,obj2);
		if(rlt >0) {
			out.println(r1 + " is greatre than  " + r2 ) ;
		} else if(rlt < 0) {
			out.println(r1 + " is greatre than  " + r2) ;
			
		} else {
			out.println(r1 + " is equals to  " + r2) ;
		}
		
		Rational[] rs = new Rational[5] ;
		Object obj4 = Array.newInstance(aClass,5);
		Array.set(obj4,0,obj);
		rs[0] = r1;
		for(int k = 1; k< 5; k++){
			rs[k] = rs[k-1].multiply(r2);
			Array.set(obj4,k,mul1.invoke( Array.get(obj4,k-1),obj2));
		}
		
		for(int k = 0; k < 5; k++ ){
		 Rational r = rs[k] ;
		// Rational f = Array.get(obj4,k);
		 out.printf( "----"+"(%s)x(%s)^%d has value %s!\n", r1, r2, k, r) ;	
		 out.printf( "----"+"(%s)x(%s)^%d has value %s!\n", obj, obj2, k, Array.get(obj4,k)+"----------");
		}
		
		
	}
	
	
	/**
	 * Rewrite the previous method testRational using reflection.<br>
	 * The requirements are given as follows:
	 * <ol>
	 * <li> The output of this method should look the same as that of testRational().
	 * <li> No use of 'new'. 
	 *      Hence, you cannot use <code> new Rational(3,7)</code> to construct <code>r1</code>, and cannot use
	 *      <code>new Rational[5]</code> to construct <code>rs</code>. 
	 *      Instead, you should use the Constructor and/or Array class provided in reflection to construct rational objects r1,r2 and array object rs.
	 * <li> No direct invocation of all methods defined in Rational. Hence, you cannot call <code>r1.add(r2)</code>;
	 *   instead, you should first get the Method instance m for 'add' and then call <code>m.invoke()</code> by passing suitable arguments to get a result same as <code>r1.add(r2)</code>.
	 * <li> The numerator and denominator value of r1 (and r2) must be got by reflectional field access, instead of via direct or reflectional method invocation.
	 * <li> The content of an array arr cannot be got/set directly by <code>arr[i]</code> or <code> arr[k] = ...</code>. INstead , you must use methods from the class Array in the reflection package.
	 * <li> Note: To enable a private Field f to be accessible, you must call <code>f.setAccessible(true)</code> before you can get/set value for the field.    
	 *	</ol>    
	 */
	public static void testRationalUsingReflection() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
