

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab1 {
	
	public String exp = new String();    //�洢����ʽ
	
	public String expression(String str) {
		
		Lab1 expression = new Lab1();
		
		String regex0 = "^\\d+\\w{1,}$";
		String regex1 = "^((\\w+\\^\\d+|\\d+\\w+|\\d+|\\w+)(-|\\+|\\*|-\\s+|\\s+-|\\s+-\\s+|\\+\\s+|\\s+\\+|\\s+\\*|\\*\\s+|\\s+\\+\\s+|\\s+\\*\\s+))+(\\d+\\w+|\\d+|\\w+|\\w+\\^\\d+)$";
		String regex2 = "^!simplify$";
		String regex3 = "^(!simplify)(\\s\\w+=\\d+)+$";
		String regex4 = "^!d/d\\s\\w+$";
	
		int i = 0, j = 0;
		
		if (str.matches(regex1) || str.matches(regex0)) {
			exp = str.replaceAll("\\s", "");
			return exp;
			
		}else if (str.matches(regex2) & !exp.equals("")) {
			
			//String st = expression.simplify(exp);
			
			return exp;
			
		}else if (str.matches(regex3) & !exp.equals("")) {
			String [] subexp1 = str.split(" ");
			
			Integer [] values = new Integer[26];
			String [] vars = new String[26];
			
			int index;
			
			for (i = 1; i < subexp1.length; i++) {
				index = subexp1[i].indexOf("=");
				vars[i-1] = subexp1[i].substring(0, index);
				values[i-1] = Integer.parseInt(subexp1[i].substring(index+1));
			}
			
			int sta, en, medium = 0;
			int count = 0;
			
			StringBuffer sb = new StringBuffer(exp);
			
			Matcher m1 = Pattern.compile("(\\d+\\w{1,})").matcher(exp);
			
			while (m1.find()) {
					
				sta = m1.start();
					
				String st = new String(m1.group());
					
				Matcher m3 = Pattern.compile("\\d+").matcher(st);
				if (m3.find()) {
					medium = m3.end();
				}
					
				sb.insert(sta+medium+count, '*');
					
				count++;
			}


			Matcher m2 = Pattern.compile("\\w{1,}").matcher(sb.toString());
			
			while (m2.find()) {
				sta = m2.start();
				en = m2.end();
					
				String st = new String(m2.group());

				//System.out.println(st + "start" + String.valueOf(sta));
				//System.out.println(st + "end" + String.valueOf(en));

				for (i = 0; i < vars.length; i++) {
					if (st.equals(vars[i])) {
						sb.replace(sta, en, String.valueOf(values[i]));
					}
				}
			}

			//System.out.println("exp2: " + sb.toString());
		
			return expression.simplify(sb.toString());
			
			
		}else if (str.matches(regex4) & !exp.equals("")) {
			String var = str.substring(5);
			String retstr2 = new String();
			
			if (exp.contains(var)) {
				StringBuffer sbuff = new StringBuffer(exp);
				
				int sta, medium = 0;
				int count = 0;
				
				Matcher m1 = Pattern.compile("(\\d+\\w{1,})").matcher(exp);
				
				while (m1.find()) {
					sta = m1.start();
					String st = new String(m1.group());
						
					Matcher m2 = Pattern.compile("\\d+").matcher(st);
					if (m2.find()) {
						medium = m2.end();
					}
					
					sbuff.insert(sta+medium+count, '*');
					count++;
				}
				
				String expr = new String(sbuff.toString());
				
				if (expr.contains("+") || expr.contains("-")) {
					String [] exps = expr.split("\\+|-");               
					
					StringBuffer sb = new StringBuffer();
					
					int length = 0;
					
					for (i = 0; i < exps.length; i++) {
						String retstr1 = new String();
						
						if (exps[i].contains(var)) {
							if (exps[i].contains("*") | exps[i].contains("^")) {
								String [] subexps = exps[i].split("\\*|\\^");
								
								boolean f = false;
								
								for (j = 0; j < subexps.length; j++) {
									if (subexps[j].equals(var)) {
										f = true;
									}
								}	
							
								if (f) {
									retstr1 = expression.derivative(exps[i], var); 
								}
									
									if (!retstr1.equals("")) {
										if (i == 0 || expr.charAt(length+i-1) == '+') {            
											sb.append("+" + retstr1);
										}else if (expr.charAt(length+i-1) == '-') {
											sb.append("-" + retstr1);
										}else {
											sb.append(retstr1);
										}
									}
								//}
							}else if (exps[i].equals(var)) {
								retstr1 = expression.derivative(exps[i], var); 
								
								if (!retstr1.equals("")) {
									if (i == 0 || expr.charAt(length+i-1) == '+') {            
										sb.append("+" + retstr1);
									}else if (expr.charAt(length+i-1) == '-') {
										sb.append("-" + retstr1);
									}
								}
							}
						}
						
						length += exps[i].length();
					}
					
					if (sb.toString().equals("")) {
						retstr2 =  String.valueOf(var) + " not found";
					}else if (sb.charAt(0) == '+') {
						sb.delete(0, 1);
						retstr2 = sb.toString();
					}else {
						retstr2 = sb.toString();
					}
					
				}else if (expr.contains("*") | expr.contains("^")) {
					String [] subexps = expr.split("\\*|\\^");
					boolean f = false;
					
					for (j = 0; j < subexps.length; j++) {
						if (subexps[j].equals(var)) {
							f = true;
						}
					}
					
					if (f) {
						retstr2 = expression.derivative(exp, var);
					}else {
						retstr2 = String.valueOf(var) + " not found";
					}
					//return expression.simplify(retstr2);
					
				}else if (expr.equals(var)) {
					retstr2 = expression.derivative(exp, var);
					//return expression.simplify(retstr2);
				}
					
				return retstr2;	
			}else {
				return String.valueOf(var) + " not found";
			}
		}else {
			return "Input out of range";
		}	
	}

	private String derivative(String expre, String var) {
		
		int i;
		int count = 0;
		int index1, index2, index3;
	
		StringBuffer sb = new StringBuffer(expre);
		
		if (!expre.contains("*") && !expre.contains(var + "^")) {
			return "1";
		}
		
		if (expre.contains("*") && !expre.contains(var + "^")) {
			String [] expr = expre.split("\\*");
			
			for (i = 0; i < expr.length; i++) {
				if (expr[i].equals(var)) {
					count++;
				}
			}
			
			int [] begin = new int[count];
			
			i = 1;
			begin[0] = expre.indexOf(var);
			
			while (i != count) {
				begin[i] = expre.indexOf(var, begin[i-1]);
				i++;
			}
			
			for (i = 0; i < begin.length; i++) {
				if (begin[i] > 0) {
					sb.delete(begin[i]-1, begin[i] + var.length());
				}else if (begin[i] == 0) {
					sb.delete(begin[i], begin[i] + var.length() + 1);
				}
			}
			
			if (count != 1) {
				sb.append("*" + var + "^" + count);
			}
			
			expre = sb.toString();       //
			//System.out.println("expre: " + expre);
		}
		
		if (expre.contains(var + "^")) {
			index1 = expre.indexOf(var);
			index2 = expre.indexOf("^", index1);
			
			if (expre.contains("*")) {
				index3 = expre.indexOf("*", index2);
				
			}else {
				index3 = expre.length();
			}
			
			if (index3 < 0) {
				index3 = expre.length();
			}
			
			String st = expre.substring(index2+1, index3);
			
			if (st.equals("2")) {
				sb.delete(index2, index3);
			}else {
				sb.replace(index2+1, index3, String.valueOf(Integer.parseInt(st)-1));
			}
			
			if (index1 > 0) {
				//sb.insert(index1-1, String.valueOf(Integer.parseInt(st)) + "*");
				sb.insert(index1, String.valueOf(Integer.parseInt(st)) + "*");
			}else {
				sb.insert(0, String.valueOf(Integer.parseInt(st)) + "*");
			}
		}
		
		//System.out.println(sb.toString());
		return sb.toString();
	}

	private String simplify(String expr) {
		int i, j;
		int result0 = 1;
		int result1 = 0;
		
		boolean flag1 = false;
		boolean flag2 = false;
		
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
		
		int length = 0;
		
		if (expr.contains("+") || expr.contains("-")) {
			String [] exps = expr.split("\\+|-");
			
			for (i = 0; i < exps.length; i++){
				if (exps[i].contains("*")){
					String [] subexprs = exps[i].split("\\*");
					result0 = 1;
					StringBuffer sb3 = new StringBuffer();
					for (j = 0; j < subexprs.length; j++) {
						if (Pattern.matches("[0-9]*", subexprs[j])){
							result0 *= 	Integer.parseInt(subexprs[j]);
							flag1 = true;
						}else {
							sb3.append(subexprs[j] + "*");
						}
					}
					if (flag1) {
						//sb1.insert(0, String.valueOf(result0) + "*");
						sb3.append(String.valueOf(result0));
					}
					
					if (i == 0 || expr.charAt(length+i-1) == '+') {
						sb3.insert(0, "+");
					}else if (i != 0 && expr.charAt(length+i-1) == '-'){
						sb3.insert(0, "-");
					}
					
					sb1.append(sb3.toString());
					
				}else {
					if (Pattern.matches("[0-9]*", exps[i])){
						if (i == 0 || expr.charAt(length+i-1) == '+') {
							result1 += Integer.parseInt(exps[i]);
						}else if (expr.charAt(length+i-1) == '-') {
							result1 -= Integer.parseInt(exps[i]);
						}
						
						flag2 = true;
					}else{
						if (i == 0 || expr.charAt(length+i-1) == '+') {
							sb2.append("+" + exps[i]);
						}else if (expr.charAt(length+i-1) == '-') {
							sb2.append("-" + exps[i]);
						}
					}
				}
				
				length += exps[i].length();	
			}
			if (flag2){
				sb2.insert(0, String.valueOf(result1));
			}
			
			if (!sb2.equals("")){
				sb1.insert(0, sb2.toString());
			}
			
			if (sb1.charAt(0) == '+') {
				sb1.delete(0, 1);
			}
			
		}else if (expr.contains("*")){
			String [] subexprs = expr.split("\\*");
			for (j = 0; j < subexprs.length; j++){
				if (Pattern.matches("[0-9]*", subexprs[j])){
					result0 *= 	Integer.parseInt(subexprs[j]);
					flag1 = true;
				}else{
					sb1.append(subexprs[j] + "*");
				}
			}
			
			if (flag1){
				sb1.insert(0, String.valueOf(result0) + "*");
			}
			
			sb1.delete(sb1.length()-1, sb1.length());
		}else{
			sb1.append(expr);
		}
		
		return sb1.toString();
	}	
		
	public static void main(String [] args) {
		
		boolean flag = true;
		Lab1 expr = new Lab1();
		
		while (flag) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			String str = scan.nextLine();
			
			if (str.equals("end")) {
				flag = false;
				System.out.println("Quit");
			}else {
				String result = expr.expression(str);
				System.out.println(result);
			}
		}	
	}
	

}

	