package apjp2014.hw1;

import static java.lang.System.out;


import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * This class contains a simple testRational() method for testing a companion <a href="Rational">Rational</a>
 * class. Your task in this homework is to add a new method into this class called testRationalUsingReflection()
 * which is a rewrite of the testRational() method using reflectional methods instead of direct new, method calls or field access.
 * Details requirements are listed in the head of testRationalUsingReflection.   
 * 
 * @author chencc
 *
 */
public class HW1 {

	public static void main(String[] args) throws Exception {		
		
		testRational() ;
		
		out.println("==============================================================") ;
		
		testRationalUsingReflection() ;
		
		
	}


	static void testRational() throws Exception {
		
		Rational r1 = new Rational(3,7);
		Rational r2 = new Rational(6,15);
		
		
		out.println(r1 + " has numerator : "   + r1.getNumerator() ) ;
		out.println(r2 + " has denominator : " + r2.getDenominator() ) ;
				
		out.println(r1 + " + " + r2 + " = " + r1.add(r2) ) ;
		out.println(r1 + " - " + r2 + " = " + r1.subtract(r2) ) ;
		out.println(r1 + " x " + r2 + " = " + r1.multiply(r2) ) ;
		out.println(r1 + " / " + r2 + " = " + r1.divide(r2) ) ;
		out.println(r2 + "has double value :" + r2.doubleValue()) ;
		
		int rlt = r1.compareTo(r2);
		
		if(rlt >0) {
			out.println(r1 + " is greatre than  " + r2 ) ;
		} else if(rlt < 0) {
			out.println(r1 + " is greatre than  " + r2) ;
			
		} else {
			out.println(r1 + " is equals to  " + r2) ;
		}
		
		Rational[] rs = new Rational[5] ;
		rs[0] = r1;

		for(int k = 1; k< 5; k++){
			rs[k] = rs[k-1].multiply(r2);
		}
		
		for(int k = 0; k < 5; k++ ){
		 Rational r = rs[k] ;
		 out.printf( "(%s)x(%s)^%d has value %s!\n", r1, r2, k, r) ;		 
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
		// TODO Complete your code here
		Class aClass = Rational.class;
		
		//Because of Private Access Modifier , we have to use getDeclaredField() instead of getField()
		Field numerator1 = aClass.getDeclaredField("numerator"); 
		Field denominator1 = aClass.getDeclaredField("denominator"); 
		//we must to setAcessible() to make them acesssible
		numerator1.setAccessible(true);
		denominator1.setAccessible(true);
		
		//Use of Constructor
		Constructor constructor = aClass.getConstructor(long.class, long.class);
		Object obj = constructor.newInstance(3,7);
		Object obj2 = constructor.newInstance(6,15);
		
	
		//The Methods will be used with reflection 
		Method add1 = aClass.getMethod("add",Rational.class);
		//Method sub1 = aClass.getMethod("subtract",Rational.class);
		Method sub1 = aClass.getDeclaredMethod("subtract",Rational.class);
		Method mul1 = aClass.getMethod("multiply",Rational.class);
		Method div1 = aClass.getMethod("divide",Rational.class);
		Method doublvalue1 = aClass.getMethod("doubleValue");
		Method compare1 = aClass.getMethod("compareTo",Rational.class);
		
		//------------------------------------------------------------------------------------
		
		out.println(obj+" has numerator : "+(long)numerator1.get(obj));//Use of Field
		out.println(obj2 + " has denominator : " +(long)denominator1.get(obj2));//Use of Field
		//Use of Method reflection
		out.println(obj + " + " + obj2 + " = "+add1.invoke(obj,obj2)) ;
		out.println(obj + " + " + obj2 + " = "+sub1.invoke(obj,obj2)) ;
		out.println(obj + " + " + obj2 + " = "+mul1.invoke(obj,obj2)) ;
		out.println(obj + " + " + obj2 + " = "+div1.invoke(obj,obj2)) ;
		out.println(obj + "has double value :"+ doublvalue1.invoke(obj2)) ;
		//
		int rlt = (int)compare1.invoke(obj,obj2);
		if(rlt >0) {
			out.println(obj + " is greatre than  " + obj2 ) ;
		} else if(rlt < 0) {
			out.println(obj + " is greatre than  " + obj2) ;
			
		} else {
			out.println(obj + " is equals to  " + obj2) ;
		}
		
		//Use of Array reflection
		Object obj4 = Array.newInstance(Rational.class,5);
		Array.set(obj4,0,obj);
	
		for(int k = 1; k< 5; k++){

			Array.set(obj4,k,mul1.invoke( Array.get(obj4,k-1),obj2));

		}
		
		for(int k = 0; k < 5; k++ ){
		 Object r = Array.get(obj4,k) ;
		
		 out.printf( "(%s)x(%s)^%d has value %s!\n", obj, obj2, k, r);
		}
		
		
		
	}
	
	
	
}