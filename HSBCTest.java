package com.interview.HSBC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author pogy
 *支持输入任意长度的数据
 *输出该数组对应的字母组合
 */
public class HSBCTest {
	private static Map<Integer, Character[]> digitsToLetter = null;
	public static Stack<Character> stack = null;
	public static Set<String> possibleletters = null;
	private static StringBuilder tempLetter = null;
	static {
		digitsToLetter = new HashMap<>();
		digitsToLetter.put(0, new Character[] {});
		digitsToLetter.put(1, new Character[] {});
		digitsToLetter.put(2, new Character[] {'A','B','C'});
		digitsToLetter.put(3, new Character[] {'D','E','F'});
		digitsToLetter.put(4, new Character[] {'G','H','I'});
		digitsToLetter.put(5, new Character[] {'J','K','L'});
		digitsToLetter.put(6, new Character[] {'M','N','O'});
		digitsToLetter.put(7, new Character[] {'P','Q','R','S'});
		digitsToLetter.put(8, new Character[] {'T','U','V'});
		digitsToLetter.put(9, new Character[] {'W','X','Y','Z'});
	}
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        combination(sc);
	}
	
	private static void combination(Scanner sc) {
		stack = new Stack<>();
		possibleletters = new HashSet<>();
		tempLetter = new StringBuilder();
		
		List<Character[]> charMapList = getCharacterList(sc);
        getCombination(charMapList, charMapList.size(), 0);
        
        System.out.print("Output:");
        for(String item:possibleletters) {
        	System.out.printf(" %s", item.toLowerCase());
        }
        
        System.out.println("");
        System.out.println("------组合匹配完成------");
        combination(sc);
	}
	
	private static  List<Character[]> getCharacterList(Scanner sc) {
		System.out.println("------开始组合匹配------");
		System.out.println("请输入数组长度");
        int num = getEffectNum(sc, true);
        List<Integer> numList = new ArrayList<>(num);
        for(int i=0; i < num; i++){
        	System.out.printf("请输入数组第%4d个元素", i+1);
        	numList.add(getEffectNum(sc, false));
        }
        
        List<Character[]> charMapList = new ArrayList<>();
        for(Integer item:numList) {
        	Character chararray[] = null;
        	if(item > 9) {
        		List<Character> charlist = new ArrayList<>();
        		charlist.addAll(Arrays.asList(digitsToLetter.get(item%10)));
        		charlist.addAll(Arrays.asList(digitsToLetter.get(item/10)));
        		chararray = charlist.toArray(new Character[charlist.size()]);
        	}else {
        	    chararray = digitsToLetter.get(item);
        	}
        	charMapList.add(chararray);
        }
        return charMapList;
	}
	
	private static Integer getEffectNum(Scanner sc, boolean isArrLength) {
		String numStr = sc.next();
        if(!isNumeric(numStr)) {
        	System.out.println("请输入整数");
        	return getEffectNum(sc, isArrLength);
        }
        Integer num = Integer.parseInt(numStr);
        if(!isArrLength
        		&&(num < 0
        		|| num >99)) {
        	System.out.println("请输入0~99的整数");
        }
        return num;
	}
	
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	private static void getCombination(List<Character[]> charMapList, int targetCount, int currentCount) {
        if(targetCount == currentCount) {
        	possibleletters.add(String.valueOf(tempLetter));
            return;
        }
        
        Character[] nextCharList =  charMapList.get(currentCount);
        for(int i=0; i<nextCharList.length; i++) {
            stack.add(nextCharList[i]);
            tempLetter.append(nextCharList[i]);
            getCombination(charMapList, targetCount, currentCount+1);
            stack.pop();
            tempLetter = new StringBuilder(tempLetter.substring(0, tempLetter.length()-1));
        }
    }
	
}
