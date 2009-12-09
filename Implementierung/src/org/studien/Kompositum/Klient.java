package org.studien.kompositum;

public class Klient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Picture myfirstPicture = new Picture();
		Picture mysecondPicture = new Picture();
		
		Directory dir1 = new Directory();
		
		PictureSet nummer1 = new PictureSet();
		PictureSet nummer2 = new PictureSet();
		
		
		nummer1.add(myfirstPicture);
		nummer1.add(mysecondPicture);
		nummer1.add(dir1);
		nummer1.add(nummer2);
		nummer2.add(myfirstPicture);
		nummer2.add(dir1);

		
		System.out.println(nummer1.getItems().toString());
		
		

	}

}
