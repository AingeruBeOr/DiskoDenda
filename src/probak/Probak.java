package probak;

import java.util.zip.DataFormatException;

import disko.DB;

public class Probak {

	public static void main(String[] args) {
		//konprobatuDataFormatua metodoa konprobatzeko erabiliko da.
		//Horretarako DB eraikitzaile publiko jarri eta konprobatuDataFormatua metodoa publiko jarri
		
		
		/*DB db = new DB();
		int i = 0;
		
		try {
			db.konprobatuDataFormatua("");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("Aupa");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("-23-");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		
		try {
			db.konprobatuDataFormatua("23");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("23-");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("23384765-");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("23-z");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("23-325");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("23-");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("23-5");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("23-5-");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("23-0-");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("23-13-");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-0-23");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-15-10");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-05-");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-05-z");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-05-23");
			System.out.println("Ondo (espero dena)");
		} catch (DataFormatException e) {
			System.out.println("Txarto (espero ez dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-05-2356");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-05-2z");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-6-1");
			System.out.println("Ondo (espero dena)");
		} catch (DataFormatException e) {
			e.printStackTrace();
			System.out.println("Txarto (espero ez dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-01-00");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-01-10");
			System.out.println("Ondo (espero dena)");
		} catch (DataFormatException e) {
			System.out.println("Txarto (espero ez dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-01-40");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-02-10");
			System.out.println("Ondo (espero dena)");
		} catch (DataFormatException e) {
			System.out.println("Txarto (espero ez dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2020-02-29");
			System.out.println("Ondo (espero dena)");
		} catch (DataFormatException e) {
			System.out.println("Txarto (espero ez dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-02-29");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-03-10");
			System.out.println("Ondo (espero dena)");
		} catch (DataFormatException e) {
			System.out.println("Txarto (espero ez dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-03-45");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-04-10");
			System.out.println("Ondo (espero dena)");
		} catch (DataFormatException e) {
			System.out.println("Txarto (espero ez dena)");
		}
		
		try {
			db.konprobatuDataFormatua("2022-04-32");
			System.out.println("Txarto (espero ez dena)");
		} catch (DataFormatException e) {
			System.out.println("Ondo (espero dena)");
		}
		*/
	}

}
