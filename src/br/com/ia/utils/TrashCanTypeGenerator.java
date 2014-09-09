package br.com.ia.utils;

public class TrashCanTypeGenerator {
	
	private static Integer index = 0;
	private static TrashCanType[] trashCanTypes = TrashCanType.values();

		
	public static TrashCanType next(){
		TrashCanType trashCanType;
		if(index < trashCanTypes.length){
			trashCanType = trashCanTypes[index];
			index++;
		}else{
			index = 0;
			trashCanType = trashCanTypes[index];
		}
		
		return trashCanType;
		
	}


}
