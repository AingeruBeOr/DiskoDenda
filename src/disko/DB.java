package disko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.GregorianCalendar;
import java.util.zip.DataFormatException;

import Salbuespenak.StringEzEgokiException;
//
public class DB {
    private BufferedReader br;
    private Connection konexioa;
    private final static int pasahitza=123;
    
    //erroreak: https://dev.mysql.com/doc/mysql-errors/8.0/en/server-error-reference.html
    
    public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
        DB db = new DB();
        db.menuPrintzipala();
    }

    private DB(){
        br = new BufferedReader(new InputStreamReader(System.in));
        konektatu();
    }
    	
    private void konektatu(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String zerbitzaria = "jdbc:mysql://localhost:3306/diskodenda";
            String erabiltzailea = "root";
            String pasahitza = "";
            konexioa = DriverManager.getConnection(zerbitzaria,erabiltzailea,pasahitza);
        }
        catch(Exception e){
        	System.out.println("Errorea egon da datu basea kargatzean. Mesedez datu-datu basera konektatu eta saiatu berriro");
        	System.exit(0);
        }
    }

    private void menuPrintzipala() {
    	int aukera=-1;
    	try {
        	while(aukera!=0){
            	System.out.println("\n\n");
                System.out.println("  __  __ ______ _   _ _    _         \r\n"
                		+ " |  \\/  |  ____| \\ | | |  | |  /\\    \r\n"
                		+ " | \\  / | |__  |  \\| | |  | | /  \\   \r\n"
                		+ " | |\\/| |  __| | . ` | |  | |/ /\\ \\  \r\n"
                		+ " | |  | | |____| |\\  | |__| / ____ \\ \r\n"
                		+ " |_|  |_|______|_| \\_|\\____/_/    \\_\\\r\n"
                		+ "                                     \r\n"
                		+ "                                    ");
                System.out.println("1.- Datuak sartu.");
                System.out.println("2.- Datuak aldatu edo ezabatu.");
                System.out.println("3.- Kontsultak egin.");
                System.out.println("0.- Irten.");
                System.out.println("\nAukera bat sartu: ");
                aukera = Integer.parseInt(br.readLine());
                if(aukera==1 || aukera==2) {
                	boolean ondo = this.pasahitzaZuzenaDa();
                	if(ondo && aukera==1) datuakSartzekoMenua();
                	else if(ondo && aukera==2) datuakAldatzekoMenua();
                }else if(aukera==3) kontsultenMenua();
            }

            konexioa.close();
        }catch(Exception e) {
        	salbuespenaTratatu(e);
        }
        menuPrintzipala();
    }
    
    private void datuakSartzekoMenua() {
    	int aukera = -1;
        try {
        	System.out.println("\n\n");
            System.out.println("1.- Produktore berria sartu.");
            System.out.println("2.- Talde berriak sartu.");
            System.out.println("3.- Disko berria sartu.");
            System.out.println("4.- Gira berriak sartu.");
            System.out.println("5.- Gira baten barruan hiri berriak sartu.");
            System.out.println("\n\nAukera bat sartu: ");
            aukera = Integer.parseInt(br.readLine());
            aukeraIrakurri(aukera, 1, true);

        }catch(Exception e) {
        	salbuespenaTratatu(e);
        }
    }
    private void datuakAldatzekoMenua() {
    	int aukera = -1;
        try {
        	System.out.println("\n\n");
            System.out.println("1.- Giraren bukera eta hasiera datak aldatu.");
            System.out.println("2.- Talde bat kaleratu.");
            System.out.println("3.- Girako hiriak kendu.");
            System.out.println("\n\nAukera bat sartu: ");
            aukera = Integer.parseInt(br.readLine());
            aukeraIrakurri(aukera,2,true);
        }catch(Exception e) {
        	salbuespenaTratatu(e);
        }
    }
    private void kontsultenMenua() {
    	int aukera = -1;
    	boolean ondo=false;
        try {
        	System.out.println("\n\n");
            System.out.println("1.- Talde baten gira guztiak lortu.");
            System.out.println("2.- Talde baten gira bateko hiri guztiak sarreraren prezioarekin.");
            System.out.println("3.- Talde baten disko baten abesti guztiak.");
            System.out.println("4.- Talde baten disko guztien prezioak.");
            System.out.println("5.- Talde bat adierazi, produktore bera erabiltzen duten taldeak lortu.");
            System.out.println("6.- Abestiak zenbakiaren arabera ordenatu.");
            System.out.println("7.- Sarrera erosi:");
            System.out.println("8.- Diskoa erosi:");
            System.out.println("\n\nAukera gehiago ikusteko pasahitza sartu nahi duzu? (B/E)");
            String erabaki=br.readLine();
            if(erabaki.equalsIgnoreCase("b")) {
            	ondo = this.pasahitzaZuzenaDa();
            	System.out.println("1.- Talde baten gira guztiak lortu.");
        		System.out.println("2.- Talde baten gira bateko hiri guztiak sarreraren prezioarekin.");
                System.out.println("3.- Talde baten disko baten abesti guztiak.");
                System.out.println("4.- Talde baten disko guztien prezioak.");
                System.out.println("5.- Talde bat adierazi, produktore bera erabiltzen duten taldeak lortu.");
                System.out.println("6.- Abestiak zenbakiaren arabera ordenatu.");
                System.out.println("7.- Sarrera erosi.");
                System.out.println("8.- Diskoa erosi.");
            	if(ondo) {
            		System.out.println("9.- Kalkulatu gira baten irabaziak.");
                    System.out.println("10.- Kalkulatu diskoaren irabaziak.");
                    System.out.println("11.- Lekuko lortutako batazbesteko irabaziak.");
                    System.out.println("12.- 1000€ baino gehiago lortu duten lekuak erakutsi.");
            	}
            }
            System.out.println("\n\nAukera bat sartu: ");
            aukera = Integer.parseInt(br.readLine());
            aukeraIrakurri(aukera,3,ondo);
   
        }catch(Exception e) {
        	salbuespenaTratatu(e);
        }
    }

    private void aukeraIrakurri(int aukera, int menu, boolean ondo){
    	if(menu==1) {
    		switch (aukera){
        	case 1:
        		produktoreBerriaSartu();
        		break;
            case 2:
                taldeakSartu();
                break;
            case 3:
                diskoBerriaSartu();
                break;
            case 4:
                giraBerriakSartu();
                break;
            case 5:
                girarenLekuakSartu(null,0);
                break;
            default:
                System.out.println("Sartu aukera egoki bat");
                break;
    		}
    	}else if(menu==2) {
    		switch (aukera){
	    	case 1:
	            girarenBukHasDatakAldatu();
	            break;
	        case 2:
	            taldeaKaleratu();
	            break;
	        case 3:
	            lekuakGiratikKendu();
	            break;
	        default:
	        	System.out.println("Sartu aukera egoki bat");
	            break;
    		}
    	}else {
    		switch(aukera) {
				case 1:
		            taldearenGirakErakutsi();
		            break;
		        case 2:
		            giraLekuezOrdenatutaErakutsi();
		            break;
		        case 3:
		            diskoarenAbestiakErakutsi();
		            break;
		        case 4:
		            diskoenPrezioakErakutsi();
		            break;
		        case 5:
		            taldeProduktoreBera();
		            break;
		        case 6:
		        	diskoaAbestiazOrdenatuaErakutsi();
		            break;
		        case 7:
		        	sarreraErosketaEgin();
		            break;
		        case 8:
		        	diskoErosketaEgin();
		            break;
		        case 9:
		        	if (ondo)giraIrabaziak();
		            break;
		        case 10:
		            if(ondo)diskoIrabaziak();
		            break;
		        case 11:
		        	if(ondo)lekuBakoitzekoBatezbestekoIrabaziak();
		        	break;
		        case 12:
		        	if(ondo)euro1000BainoGehiago2022Lekuak();
		        	break;
		        default:
		            System.out.println("Sartu aukera egoki bat");
		            break;
		    }
    	}
        
    }
    
    
    //************************************************INSERT************************************************************
    /**
     * Datu basean produktore berria sartzeko erabiliko da.
     */
    private void produktoreBerriaSartu() {
    	int saiakera=0;
    	do {
    		try {
        		System.out.println("Sartu produktorearen izena: ");
            	String izena = stringEgokiaDa(br.readLine(), 15);
            	System.out.println(izena.length());
            	System.out.println("Sartu produktorearen kodea: ");
            	int kodea = Integer.parseInt(br.readLine());
            	System.out.println("Sartu produktorearen telefono zenbakia: ");
            	int telf = Integer.parseInt(br.readLine());
            	PreparedStatement ps = konexioa.prepareStatement("INSERT INTO PRODUKTOREA VALUES(?, ?, ?)");
            	ps.setString(1,izena);
            	ps.setInt(2,kodea);
            	ps.setInt(3, telf);
            	ps.executeUpdate();
            	saiakera=3;
        	}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
    		saiakera++;
    	}while(saiakera<3);
    }

    /**
     * Talde berri bat sartuko da datu-basean. Talde hori sartzen denean, hainbat partaide sartuko ahal dira talde horren barruan.
     */
    private void taldeakSartu() {
    	int saiakera = 0,kode =0;
    	boolean ondo = false;
    	String partaideak = null;
    	String taldeIzena = null;
    	//Taldea sartzeko:
    	do{
			try {
		    	System.out.println("Sartu taldearen izena: ");
		    	taldeIzena = stringEgokiaDa(br.readLine(), 15);
				System.out.println("Sartu taldearen kodea: ");
				kode = Integer.parseInt(br.readLine());
				System.out.println("Sartu taldearen deskribapena: ");
		    	String desk = stringEgokiaDa(br.readLine(), 15);
				System.out.println("Sartu produktorearen kodea:");
				int pKode = Integer.parseInt(br.readLine());
				PreparedStatement ps = konexioa.prepareStatement("INSERT INTO TALDE VALUES(?, ?, ?, ?)");
				ps.setInt(1,kode);
				ps.setString(2,taldeIzena);
		    	ps.setString(3, desk);
		    	ps.setInt(4, pKode);
		    	ps.executeUpdate();
		    	System.out.println("'" + taldeIzena + "' taldea datu basean kargatu da.");
		    	ondo = true;
		    	System.out.println("Partaideak sartu nahi dituzu? (B/E)");
		    	partaideak = br.readLine();
			}catch(Exception e) {
				salbuespenaTratatu(e);
			}
			saiakera++;
    	}while(saiakera<3 && !ondo);
    	//Partaideak sartzeko:
    	if(ondo && partaideak.equalsIgnoreCase("B")) {
    		saiakera = 0;
    		do {
        		String hizki;
        		try {
        			do {
    					System.out.println("Sartu partaidearen izena: ");
    			    	String pIzen = stringEgokiaDa(br.readLine(),15);
    					System.out.println("Sartu partaidearen kodea: ");
    					int partKode = Integer.parseInt(br.readLine());
    					PreparedStatement ps = konexioa.prepareStatement("INSERT INTO PARTAIDEA VALUES(?, ?, ?)");
    			    	ps.setString(1,pIzen);
    			    	ps.setInt(2,partKode);
    			    	ps.setInt(3, kode);
    			    	ps.executeUpdate();
    			    	System.out.println("'"+ pIzen + "' partaidea '" + taldeIzena + "' taldearen barruan kargatu da.");
    					System.out.println("Partaide gehiagorik sartu nahi duzu? (B/E)");
    					hizki = br.readLine();
        			} while(hizki.equalsIgnoreCase("B"));
        			saiakera = 3;
        		} catch(Exception e) {
        			salbuespenaTratatu(e);
        		}
        		saiakera++;
    		}while(saiakera<3);
    	}
    	
    }

    /**
     * Datu basearen talde bati disko bat sartzeko erabliliko da. Gainera, disko horren barruan abestiak sartzeko aukera emango du.
     */
    private void diskoBerriaSartu() {
    	int saiakera=0;
    	boolean ondo = false;
    	String abestiak = null;
    	int kode = 0;
    	String diskoIzen = null;
    	//Diskoa sartzeko:
    	do {
    		try {
    			System.out.println("Sartu disko izen bat: ");
    	    	diskoIzen = stringEgokiaDa(br.readLine(),15);
    	    	System.out.println("Sartu disko kode bat: ");
    	    	kode = Integer.parseInt(br.readLine());
    	    	System.out.println("Sartu disko prezio bat: ");
    	    	float prezio = Float.parseFloat(br.readLine());
    	    	System.out.println("Sartu talde baten kodea: ");
    	    	int talKode = Integer.parseInt(br.readLine());
    	    	PreparedStatement ps = konexioa.prepareStatement("INSERT INTO DISKO VALUES(?, ?, ?, 0, ?)");
    	    	ps.setString(1, diskoIzen);
    	    	ps.setInt(2, kode);
    	    	ps.setFloat(3, prezio);
    	    	ps.setInt(4, talKode);
    	    	ps.executeUpdate();
	    		System.out.println("'" + diskoIzen + "' diskoa datu basean kargatu da.");
	    		ondo = true;
		    	System.out.println("Abestiak sartu nahi dituzu? (B/E)");
		    	abestiak = br.readLine();
    		}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}	
    		saiakera++;
    	}while(saiakera<3 && !ondo);
    	//Abestiak sartzeko:
    	if(ondo && abestiak.equalsIgnoreCase("B")) {
    		saiakera = 0;
	    	int kont = 1;
	    	do {
	    		String erantzun;
	    		try {
	    			do{
	    	    		System.out.println("Sartu abesti izen bat: ");
	    	    		String izena = stringEgokiaDa(br.readLine(),15);
	    	    		PreparedStatement psa = konexioa.prepareStatement("INSERT INTO ABESTIA VALUES(?, ?, ?)");
	    	    		psa.setString(1, izena);
	    	    		psa.setInt(2,kont);
	    	    		psa.setInt(3, kode);
	    	    		psa.executeUpdate();
	    	    		kont++; 
	    	    		System.out.println("'" + izena + "' abestia '" + diskoIzen +"' dikoaren barruan kargatu da.");
	    	    		System.out.println("Sartu berri duzun disko horretan abesti berriak sartu nahi dituzu? (B/E)");
	    	    		erantzun = br.readLine();
	    	    	} while(erantzun.equalsIgnoreCase("B"));
	    			saiakera = 3;
	    		} catch(Exception e) {
        			salbuespenaTratatu(e);
        		}
	    		saiakera++;
	    	}while(saiakera<3);
    	}	
    }	
    
    /**
     * Talde bati gira berri bat sartuko zaio. Behin gira hori sartu dela, gira horren barruan hiriak eta lekuak sartu nahi diren eskatuko da.
     * Hiri eta leku berriak sartu nahi badira, "girarenHiriakSartu" metodoari dei egingo zaio.
     */
    private void giraBerriakSartu() {
    	int saiakera=0;
    	do {
    		try {
    			System.out.println("Sartu gira berriaren hasiera data (UUUU-HH-EE formatuan): ");
    	    	String hasData = konprobatuDataFormatua(br.readLine());
    	    	System.out.println("Sartu gira berriaren bukaera data (UUUU-HH-EE formatuan): ");
    	    	String bukData = konprobatuDataFormatua(br.readLine());
    	    	System.out.println("Sartu gira horren talde kodea: ");
    	    	int taldeKode = Integer.parseInt(br.readLine());
    	    	PreparedStatement ps = konexioa.prepareStatement("INSERT INTO GIRA VALUES(?, ?, ?)");
    	    	ps.setString(1,hasData);
    	    	ps.setString(2,bukData);
    	    	ps.setInt(3,taldeKode);
    	    	ps.executeUpdate();
    	    	System.out.println("Sartu berri duzun gira horretan leku berriak sartu nahi dituzu? (B/E)");
    	    	String erantzun = br.readLine();
    	    	if(erantzun.equalsIgnoreCase("B")) girarenLekuakSartu(hasData, taldeKode);
    	    	saiakera=3;
    		}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
			saiakera++;
		}while(saiakera<3);
    }
    
    /**
     * Gira baten lekuak sartzeko erabiltzen da. Paremetroak null eta 0 badira, hurrenez hurren, giraren hasiera data eta taldearen
     * kodea sartzea eskatuko da.
     * @param hData giraren hasiera data
     * @param tKode talderaen kodea
     */
    private void girarenLekuakSartu(String hData, int tKode) {
		int saiakera=0;
		String hasData = hData;
		int taldeK = tKode;
		boolean giraDago = true;
		if(hasData == null || taldeK == 0) giraDago = false;
		while(!giraDago && saiakera < 3) {
			try {
				System.out.println("Sartu jotzen duen taldearen kodea: ");
				taldeK = Integer.parseInt(br.readLine());
				System.out.println("Sartu gira berriaren hasiera data (UUUU-HH-EE formatuan): ");
				hasData = konprobatuDataFormatua(br.readLine());
				PreparedStatement ps = konexioa.prepareStatement("SELECT * FROM GIRA WHERE taldek = ? and hasdata = ?");
				ps.setInt(1, taldeK);
				ps.setString(2, hasData);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) giraDago = true;
				else System.out.println("Gira hori ez da existitzen.");
			} catch (Exception e) {
				salbuespenaTratatu(e);
			}
			saiakera++;
		}
		
		saiakera = 0;
	    do{
	    	try{
	        	String hizki;
	        	do {
	    			System.out.println("Sartu herrialdearen izena: ");
	    		    String herrialde = stringEgokiaDa(br.readLine(),15);
	    		    System.out.println("Sartu hiriaren izena: ");
	    		    String hiriIzen = stringEgokiaDa(br.readLine(),15);
	    		    System.out.println("Sartu lekuaren izena: ");
    	        	String lekua = stringEgokiaDa(br.readLine(),15);
    	    		System.out.println("Sartu sarreren prezioa: ");
    	        	Float prezioa= Float.valueOf(br.readLine());
    	        	if(!lekuaExisititzenDa(herrialde, hiriIzen, lekua)) {
    	        		PreparedStatement ps = konexioa.prepareStatement("INSERT INTO LEKUA VALUES(?, ?, ?)");
    	        		//Hiria sortzeko:
    	    			ps.setString(1,lekua);
    	    			ps.setString(2,hiriIzen);
    	    	    	ps.setString(3,herrialde);
    	    	    	ps.executeUpdate();
    	        	}
    	        	//Hiria eta gira konektatzeko:
    	        	PreparedStatement ps2 = konexioa.prepareStatement("INSERT INTO LEKUAN_JO VALUES(?, ?, ?, ?, ?, ?, 0)");
	    	    	ps2.setString(1,lekua);
	    	    	ps2.setString(2,hiriIzen);
	    	    	ps2.setString(3,herrialde);
	    	    	ps2.setString(4, hasData);
	    	    	ps2.setInt(5, taldeK);
	    	    	ps2.setFloat(6, prezioa);
	    	    	ps2.executeUpdate();
	    	    	System.out.println("Leku gehiagorik sartu nahi duzu? (B/E)");
	    			hizki=br.readLine();
	    		}while(hizki.equalsIgnoreCase("B"));
	    		saiakera=3;
		    }catch(Exception e) {
		    	salbuespenaTratatu(e);
			}
			saiakera++;
		}while(saiakera<3);
    }
    
    
    
  //************************************************UPDATE************************************************************
    /**
     * Gira bateko datetan arazoak egonda hauek aldatuko dira:
     * <ul>
     * 		<li>Hasiera data</li>
     * 		<li>Bukaera data</li>
     * </ul>
     */
    private void girarenBukHasDatakAldatu(){
    	int saiakera=0;
    	int kodea = 0;
    	String data = null;
    	do {
    		try {
    			System.out.println("Aldatu nahi duzun girak egiten duen taldearen KODEA adierazi: ");
    			kodea = Integer.parseInt(br.readLine());
    			System.out.println("Sartu zein den aldatu nahi duzun giraren hasiera data (UUUU-HH-EE formatuan): ");
    			data = konprobatuDataFormatua(br.readLine());
    			PreparedStatement ps = konexioa.prepareStatement("SELECT * FROM GIRA WHERE hasdata = ? and taldek = ?");
    			ps.setString(1, data);
    			ps.setInt(2, kodea);
    			ResultSet rs = ps.executeQuery();
    			if(rs.next()) saiakera = 3;
    			else System.out.println("Gira hori ez da existitzen.");
			} catch (Exception e) {
				salbuespenaTratatu(e);
			}
    		saiakera++;
		} while (saiakera < 3);
    	
	    do{
	    	try{
	        	System.out.println("Hasiera data aldatu nahi duzu? Horrela bada sakatu 'B' hizkia, bestela sakatu beste bat.");
	        	if(br.readLine().equalsIgnoreCase("B")) {
	        		System.out.println("Sartu hasiera data berria (UUUU-HH-EE formatuan): ");
	        		String dataB = konprobatuDataFormatua(br.readLine());
	        		PreparedStatement ps = konexioa.prepareStatement("UPDATE GIRA SET HasData = ? WHERE TaldeK = ? AND HasData = ?");
	            	ps.setString(1,dataB);
	            	ps.setInt(2,kodea);
	            	ps.setString(3,data);
	            	ps.executeUpdate();
	        	}
	        	System.out.println("Bukaera data aldatu nahi duzu? Horrela bada sakatu 'B' hizkia, bestela sakatu beste bat.");
	        	if(br.readLine().equalsIgnoreCase("B")) {
	        		System.out.println("Sartu bukaera data berria (UUUU-HH-EE formatuan): ");
	        		String dataB = konprobatuDataFormatua(br.readLine());
	        		PreparedStatement ps = konexioa.prepareStatement("UPDATE GIRA SET BukData = ? WHERE TaldeK = ? AND HasData = ?");
	            	ps.setString(1,dataB);
	            	ps.setInt(2,kodea);
	            	ps.setString(3,data);
	            	ps.executeUpdate();
	        	}
	        	saiakera=3;
		    }
	    	catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
			saiakera++;
		}while(saiakera<3);
    }

    private void sarrerakEguneratu(int kop, String hasData, String herrialdea, String hiria, String izena) {
    	int saiakera=0;
    	do {
    		try {
    			PreparedStatement ps = konexioa.prepareStatement(
    					"UPDATE Lekuan_Jo SET SaldutakoSarrerak = (SaldutakoSarrerak + ?) "+ 
    					"WHERE Herrialdea = ? AND Hiria = ? AND izena = ? AND hasData= ?"
    			);
    	    	ps.setInt(1,kop);
    	    	ps.setString(2, herrialdea);
    	    	ps.setString(3,hiria);
    	    	ps.setString(4,izena);
    	    	ps.setString(5, hasData);
    	    	ps.executeUpdate();
    	    	System.out.println("Datu basea egoki eguneratu da");
    			saiakera=3;
        	}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
    		saiakera++;
    	}while(saiakera<3);
    }
    
    private void diskoakEguneratu(int kop, String diskoIzen, int taldek) {
    	int saiakera=0;
    	do {
    		try {
    			PreparedStatement ps = konexioa.prepareStatement(
    					"UPDATE DISKO SET SaldutakoKopiak = (SaldutakoKopiak + ?) "+ 
    					"WHERE Disko.izena=? AND DISKO.taldek=?"
    			);
    	    	ps.setInt(1,kop);
    	    	ps.setString(2,diskoIzen);
    	    	ps.setInt(3,taldek);
    	    	ps.executeUpdate();
    	    	System.out.println("Datu basea egoki eguneratu da");
    			saiakera=3;
        	}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
    		saiakera++;
    	}while(saiakera<3);
    }
    
  //************************************************DELETE************************************************************
    /**
     * Talde baten kodea sartuta, talde hori datu basetik aterako da. Dependentzia guztiak "ON DELETE CASCADE" jarrita daudenez, hauek ere ezabatuko dira.
     */
    private void taldeaKaleratu(){
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Sartu kaleratu nahi duzun taldearen kodea: ");
	        	int taldeIzen = Integer.parseInt(br.readLine());
	        	PreparedStatement ps = konexioa.prepareStatement("DELETE FROM TALDE WHERE Kodea = ?");
	        	ps.setInt(1, taldeIzen);
	        	ps.executeUpdate();
	        	saiakera=3;
		    }
	    	catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
			saiakera++;
		}while(saiakera<3);
    }

    /**
     * Erabiltzaileari datu basetik girako bateko zenbait hiri ezabatzeko aukera emango zaio.
     */
    private void lekuakGiratikKendu() {
    	int saiakera=0;
    	String erantzuna;
	    do{
	    	try{
	    		do {
	        		System.out.println("Sartu taldearen identifikazio kodea: ");
	        		int taldeKode = Integer.parseInt(br.readLine());
	        		System.out.println("Sartu " + taldeKode + " taldearen giraren hasiera-data: ");
	        		String hasData = konprobatuDataFormatua(br.readLine());
	            	System.out.println("Sartu kendu nahi duzun lekuaren hiri izena: ");
	            	String hiriIzen = konprobatuDataFormatua(br.readLine());
	            	System.out.println("Sartu kendu nahi duzun lekuaren herrialdea: ");
	            	String hiriHerrialde = br.readLine();
	            	System.out.println("Sartu kendu nahi duzun lekuaren izena: ");
	            	String lekua = br.readLine();
	            	PreparedStatement ps = konexioa.prepareStatement(
	            			"DELETE FROM Lekuan_Jo " +
	            			"WHERE Hiria = ? AND  Herrialdea = ? AND HasData = ? AND TaldeK = ? AND Izena = ?"
	            	);
	            	ps.setString(1,hiriIzen);
	            	ps.setString(2, hiriHerrialde);
	            	ps.setString(3, hasData);
	            	ps.setInt(4, taldeKode);
	            	ps.setString(5, lekua);
	            	ps.executeUpdate();
	            	System.out.println("Leku gehiago ezabatu nahi dituzu? (B/E)");
	            	erantzuna = br.readLine();
	        	}while(erantzuna.equalsIgnoreCase("B"));
	    		saiakera=3;
		    }
	    	catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
			saiakera++;
		}while(saiakera<3);
    }

  //************************************************SELECT************************************************************
    private boolean lekuaExisititzenDa(String pHerrialde, String pHiri, String pLeku) throws SQLException{
		PreparedStatement ps = konexioa.prepareStatement(
    			"SELECT Herrialdea, Hiria, Izena " + 
    			"FROM Lekua " +
    			"WHERE Herrialdea = ? AND Hiria = ? AND Izena = ?"
    	);
		ps.setString(1,pHerrialde);
		ps.setString(2,pHiri);
		ps.setString(3,pLeku);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) return true;
		else return false;
    }
    
    private void taldearenGirakErakutsi(){
    	int saiakera=0;
    	do {
    		try {
    			System.out.println("Sartu taldearen izena:");
	            String taldeIzen = br.readLine();
	            PreparedStatement ps = konexioa.prepareStatement(
	            		"SELECT GIRA.hasData, LEKUAN_JO.HERRIALDEA, LEKUAN_JO.HIRIA, LEKUAN_JO.IZENA " + 
	            		"FROM GIRA, TALDE, LEKUAN_JO " + 
	            		"WHERE TALDE.izena = ? and TALDE.kodea = GIRA.taldek and "+
	            		"Gira.hasdata=lekuan_jo.hasdata and gira.taldek=lekuan_jo.taldek");
	            ps.setString(1,taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
	            }
    			saiakera=3;
    		}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
    		saiakera++;
    	}while(saiakera<3);
    }

    /**
     * Disko baten abesti guztiak erakutsiko dira.
     * Horretatrako,
     * @throws IOException
     * @throws SQLException
     */
    private void diskoarenAbestiakErakutsi(){
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Sartu taldearen izena:");
	            String taldeIzen = br.readLine();
	            System.out.println("Sartu " + taldeIzen + " taldearen diskoaren izena:");
	            String diskoIzen = br.readLine();
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT  DISKO.Izena, ABESTIA.* " +
	                    "FROM TALDE, DISKO, ABESTIA " +
	                    "WHERE TALDE.kodea = DISKO.TaldeK AND " +
	                            "DISKO.Kodea = ABESTIA.DiskoK AND " +
	                            "DISKO.izena = ? AND " +
	                            "TALDE.Izena = ?");
	            ps.setString(1,diskoIzen);
	            ps.setString(2,taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	                System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getString(3));
	            }
	            saiakera=3;
		    }
	    	catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
			saiakera++;
		}while(saiakera<3);
    }

    private void diskoenPrezioakErakutsi() {
    	int saiakera=0;
    	do {
    		try {
    			System.out.println("Sartu talde baten izena: ");
    	        String taldeIzen = br.readLine();
    	        PreparedStatement ps = konexioa.prepareStatement(
    	        		"SELECT DISKO.izena, DISKO.prezioa " + 
    	        		"FROM TALDE, DISKO " + 
    	        		"WHERE TALDE.izena = ? and TALDE.kodea = DISKO.TaldeK ");
    	        ps.setString(1, taldeIzen);
    	        ResultSet rs = ps.executeQuery();
    	        while(rs.next()){
    	            System.out.println(rs.getString(1) + " " + rs.getFloat(2)+"€");
    	        }
    			saiakera=3;
    		}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
    		saiakera++;
    	}while(saiakera<3);
    }
    /**
     * Talde baten produktore bera erabiltzen duten taldeak.
     * Horretarako, taldearen izena adieraziko da.
     * @throws IOException
     * @throws SQLException
     */
    private void taldeProduktoreBera() {
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Sartu taldearen izena:");
	            String taldeIzen = br.readLine();
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT distinct TALDE.Izena " +
	                    "FROM TALDE, PRODUKTOREA " +
	                    "WHERE TALDE.ProdKode IN " +
	                            "(SELECT TALDE.ProdKode " +
	                            "FROM TALDE " +
	                            "WHERE TALDE.izena = ?) " +
	                    "EXCEPT "+
	                    "SELECT izena " +
	                    "FROM TALDE " +
	                    "WHERE izena = ?");
	            ps.setString(1, taldeIzen);
	            ps.setString(2, taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            if(!rs.next()) System.out.println("Ez daude talderik sartu duzun taldearen produktore berarekin");
	            else {
		            System.out.println("Hauek dira " + taldeIzen + " taldeak bere produktorea konpartitzen duen taldeak:\n");
	            	System.out.println(rs.getString(1));
		            while(rs.next()){
		                System.out.println(rs.getString(1));
		            }
	            }
	            saiakera=3;
		    }
	    	catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
			saiakera++;
		}while(saiakera<3);
    }

    /**
     * Talde batek gira batean lortuako irabaziak adieraziko dira.
     * Horretarako, taldearen izena eta gira adierziko dira.
     * @throws IOException
     * @throws SQLException
     */
    
    private void giraIrabaziak() {
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Sartu talde baten izena: ");
	            String taldeIzen = br.readLine();
	            System.out.println("Sartu girak identifikatzen duen hasiera data: ");
	            String giraData = konprobatuDataFormatua(br.readLine());
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT SUM(LEKUAN_JO.SaldutakoSarrerak * LEKUAN_JO.Prezioa) " +
	                    "FROM TALDE, GIRA, LEKUAN_JO " +
	                    "WHERE TALDE.izena = ? and " +
	                    	"TALDE.Kodea = LEKUAN_JO.TaldeK and " +
	                        "LEKUAN_JO.HasData = ? ");
	            ps.setString(1,taldeIzen);
	            ps.setString(2,giraData);
	            ResultSet rs = ps.executeQuery();
	            rs.next();
	            System.out.println(rs.getString(1)+"€");
	            saiakera=3;
		    }
	    	catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
			saiakera++;
		}while(saiakera<3);
    }

    private void diskoIrabaziak() {
    	int saiakera=0;
    	do {
    		try {
    			System.out.println("Sartu disko baten izena: ");
    	        String diskoIzen = br.readLine();
    	        PreparedStatement ps = konexioa.prepareStatement(
    	        		"SELECT SUM(prezioa * saldutakoKopiak) " + 
    	        		"FROM DISKO " + 
    	        		"WHERE DISKO.Izena = ? ");
    	        ps.setString(1,diskoIzen);
    	        ResultSet rs = ps.executeQuery();
    	        rs.next();
    	        System.out.println(rs.getFloat(1)+"€");
    			saiakera=3;
    		}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
    		saiakera++;
    	}while(saiakera<3);
    }

    private void diskoaAbestiazOrdenatuaErakutsi() {
    	int saiakera=0;
    	do {
    		try {
    			System.out.println("Sartu disko baten izena: ");
    	    	String diskoIzena = br.readLine();
    	    	System.out.println("Sartu taldearen izena ");
    	    	String taldeIzena = br.readLine();
    	    	PreparedStatement ps = konexioa.prepareStatement(
    	    			"SELECT  Abestia.Zenbakia, Abestia.Izena,  DISKO.Izena " + 
    	    			"FROM TALDE, DISKO, ABESTIA " + 
    	    			"WHERE TALDE.kodea = DISKO.TaldeK AND " + 
    	    			"DISKO.Kodea=ABESTIA.DiskoK AND DISKO.Izena=?  AND TALDE.Izena= ?  " + 
    	    			"ORDER BY ABESTIA.Zenbakia ");
    	    	ps.setString(1,diskoIzena);
    	        ps.setString(2,taldeIzena);
    	        ResultSet rs = ps.executeQuery();
    	        while(rs.next()){
    	            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
    	        }
    			saiakera=3;
    		}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
    		saiakera++;
    	}while(saiakera<3);
    	
    }

    private void giraLekuezOrdenatutaErakutsi() {	
    	int saiakera=0;
	    do{
	    	try{
	        	System.out.println("Sartu taldearen izena:");
	            String taldeIzen = br.readLine();
	            System.out.println("Sartu zein den " + taldeIzen + " taldetik ikusi nahi duzun giraren hasiera data (UUUU-HH-EE formatuan): ");
	    		String data = konprobatuDataFormatua(br.readLine());
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT  LEKUAN_JO.Izena, LEKUAN_JO.HIRIA, LEKUAN_JO.HERRIALDEA, LEKUAN_JO.Prezioa " +
	                    "FROM  TALDE,  LEKUAN_JO " +
	                    "WHERE TALDE.kodea=LEKUAN_JO.TaldeK AND "+
	                            "LEKUAN_JO.hasData = ? AND " +
	                            "TALDE.Izena = ?"+
	                    "ORDER BY LEKUAN_JO.Izena");
	            ps.setString(1,data);
	            ps.setString(2,taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4)+"€");
	            }
	            saiakera=3;
		    }
	    	catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
			saiakera++;
		}while(saiakera<3);
    }

    /**
     * Leku bakoitzeko lortzen diren batezbesteko irabaziak lortzeko erabiltzen da
     * @throws IOException
     * @throws SQLException
     */
    private void lekuBakoitzekoBatezbestekoIrabaziak() {
    	try{
    		PreparedStatement ps = konexioa.prepareStatement(
    				"SELECT Herrialdea, Hiria, Izena, AVG(Prezioa * SaldutakoSarrerak)" + 
    				"FROM LEKUAN_JO " + 
    				"GROUP BY Herrialdea, Hiria, Izena " +
    				"ORDER BY AVG(Prezioa * SaldutakoSarrerak)"
    		);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			System.out.println(rs.getString(1) + " "+ rs.getString(2) +" "+ rs.getString(3) + " "+ rs.getFloat(4)+"€"); 
    		}
	    }
    	catch(Exception e) {
			salbuespenaTratatu(e);
		}
	}

    private void euro1000BainoGehiago2022Lekuak() {
		try {
			PreparedStatement ps = konexioa.prepareStatement(
					"SELECT Herrialdea, Hiria, Izena, AVG(Prezioa * SaldutakoSarrerak)" + 
					"FROM LEKUAN_JO " +
					"WHERE YEAR(HasData)=2022 " +
					"GROUP BY Herrialdea, Hiria, Izena " +
					"HAVING AVG(Prezioa * SaldutakoSarrerak) > 1000"
			);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1) + " "+ rs.getString(2) +" "+ rs.getString(3) + " "+ rs.getFloat(4)+"€");
			}
		}
		catch(Exception e) {
			salbuespenaTratatu(e);
		}
    }
    private void diskoErosketaEgin() {
    	int saiakera=0;
    	do {
    		try {
    			System.out.println("Sartu taldearen izena:");
	            String taldeIzen = br.readLine();
	            PreparedStatement ps = konexioa.prepareStatement(
	            		"SELECT TALDE.KODEA, DISKO.IZENA, DISKO.PREZIOA " + 
	            		"FROM TALDE, DISKO " + 
	            		"WHERE TALDE.izena = ? and TALDE.kodea = DISKO.TALDEK ");
	            ps.setString(1,taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            System.out.println("Hauek dira " + taldeIzen + " taldeak dituen diskoak:");
	            int taldek=0;
	            boolean daude=false;
	            while(rs.next()){
	                System.out.println(rs.getString(3)+" "+rs.getFloat(4) + "€");
	                taldek=rs.getInt(2);
	                daude=true;
	            }
	            if(daude) {
	            	System.out.println("Aukeratu horietako bat: ");
		            System.out.println("Sartu diskoaren izena: ");
		            String diskoIzen=br.readLine();
		            System.out.println("Sartu erosi nahi dituzun unitate kopurua: ");
		            int kop= Integer.parseInt(br.readLine());
		            this.diskoakEguneratu(kop, diskoIzen, taldek);
		            System.out.println("Erosketa zuzen burtu da.");
	            }else {
	            	System.out.println("Ez daude "+taldeIzen+" taldearen diskoak eskuragarri. Beste talde bat bilatu.");
	            }
    			saiakera=3;
    		}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
    		saiakera++;
    	}while(saiakera<3);
    }
    
    private void sarreraErosketaEgin() {
    	int saiakera=0;
    	do {
    		try {
    			System.out.println("Sartu taldearen izena:");
	            String taldeIzen = br.readLine();
	            PreparedStatement ps = konexioa.prepareStatement(
	            		"SELECT LEKUAN_JO.hasData, LEKUAN_JO.herrialdea, LEKUAN_JO.Hiria, LEKUAN_JO.Izena, LEKUAN_JO.Prezioa " + 
	            		"FROM  TALDE, LEKUAN_JO" + 
	            		"WHERE TALDE.izena = ? and TALDE.kodea = LEKUAN_JO.taldek "
	            );
	            ps.setString(1,taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            System.out.println("Hauek dira " + taldeIzen + " taldeak egiten dituen girak eta lekuak:");
	            boolean daude=false;
	            while(rs.next()){
	            	daude=true;
	                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getFloat(5)+"€");
	            }
	            if(daude) {
	            	System.out.println("Aukeratu horietako bat: ");
	 	            System.out.println("Sartu hasiera data (UUUU-HH-EE formatuan): ");
	 	            String hasData = konprobatuDataFormatua(br.readLine());
	 	            System.out.println("Sartu herrialdearen izena: ");
	 	            String herrialdea = br.readLine();
	 	            System.out.println("Sartu hiriaren izena: ");
	 	            String hiria = br.readLine();
	 	            System.out.println("Sartu lekuaren izena: ");
	 	            String lekuIzen = br.readLine();
	 	            System.out.println("Sartu erosi nahi dituzun sarrera kopurua: ");
	 	            int kop = Integer.parseInt(br.readLine());
	 	            sarrerakEguneratu(kop, hasData, herrialdea,hiria, lekuIzen);
	 	            System.out.println("Erosketa zuzen burutu da.");
	            }else {System.out.println("Ez daude " + taldeIzen + " taldearen girak eskuragarri, bilatu beste talde batenak.");}
    			saiakera=3;
    		}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
    		saiakera++;
    	}while(saiakera<3);
    }
    
  //************************************************KONPROBAKETAK************************************************************
    
    private boolean pasahitzaZuzenaDa() throws IOException{
    	int saiakera=0;
    	do {
    		try {
    			System.out.println("Pasahitza sartu behar duzu datuak kudeatzeko. Pasahitza sartu: ");
            	int p= Integer.parseInt(br.readLine());
            	if (p==pasahitza) return true;
            	else System.out.println("Pasahitza okerra da.");
    		}catch(NumberFormatException e) {
    			System.out.println("Pasahitza zenbaki bat izan behar du.");
    		}
    		saiakera++;
    	}while(saiakera<3);
    	return false;
    }
    
    /**
     * String bat UUUU-HH-EE formatuan idatzita dagoen konprobatzen du. Beste hurrengo datu formatuak ere onartuko dira:
     * <ul>
     * 		<li>U-HH-EE</li>
     * 		<li>U-HH-E</li>
     * 		<li>U-H-EE</li>
     * 		<li>U-H-E</li>
     * 		<li>//TOwrite</li>
     * </ul>
     * @param pKonprobatzeko
     * @throws DataFormatException
     */
    private String konprobatuDataFormatua(String pKonprobatzeko) throws DataFormatException{
    	int i = 0;
    	int urteZifraKop = 0;
    	int hilabeteZifraKop = 0;
    	int egunZifraKop = 0;
    	int urtea = 0;
    	int hilabetea = 0;
    	int eguna = 0;
    	boolean bukatu = false;
    	//********************* Urtea konprobatu: ****************************************** 
    	if(i < pKonprobatzeko.length() && Character.isDigit(pKonprobatzeko.charAt(i))) {
    		urtea = Character.getNumericValue(pKonprobatzeko.charAt(i));
    		i++; urteZifraKop++;
    		while(!bukatu) {
    			if(i < pKonprobatzeko.length()) {
    				if(Character.isDigit(pKonprobatzeko.charAt(i)) && urteZifraKop < 4) {
    					urtea = urtea * 10;
    					urtea = urtea + Character.getNumericValue(pKonprobatzeko.charAt(i));
    					i++; urteZifraKop++;
    				}
    				else if(pKonprobatzeko.charAt(i) == '-') {
    					bukatu = true; i++;
    				}
    				else throw new DataFormatException();
    			}
    			else throw new DataFormatException();
    		}
		}
		else throw new DataFormatException();
    	
    	// ******************* Hilabetea konprobatu: *************************************
		if(i < pKonprobatzeko.length() && Character.isDigit(pKonprobatzeko.charAt(i))){
			hilabetea = Character.getNumericValue(pKonprobatzeko.charAt(i));
			i++; hilabeteZifraKop++;
			bukatu = false;
			while(!bukatu) {
				if(i < pKonprobatzeko.length()) {
					if(Character.isDigit(pKonprobatzeko.charAt(i)) && hilabeteZifraKop < 2) {
						hilabetea = hilabetea * 10;
						hilabetea = hilabetea + Character.getNumericValue(pKonprobatzeko.charAt(i));
						i++; hilabeteZifraKop++;
					}
					else if(pKonprobatzeko.charAt(i) == '-') {
						bukatu = true; 
						i++;
					}
					else throw new DataFormatException();
				}
				else throw new DataFormatException();
			}
		}
		else throw new DataFormatException();
		if(hilabetea < 1 || hilabetea > 12) throw new DataFormatException();
		
		// ********************* Eguna konprobatu: **************************************
		if(i < pKonprobatzeko.length() && Character.isDigit(pKonprobatzeko.charAt(i))){
			eguna = Character.getNumericValue(pKonprobatzeko.charAt(i));
			i++; egunZifraKop++;
			bukatu = false;
			while(!bukatu) {
				if(i < pKonprobatzeko.length()) {
					if(Character.isDigit(pKonprobatzeko.charAt(i)) && egunZifraKop < 2) {
						eguna = eguna * 10;
						eguna = eguna + Character.getNumericValue(pKonprobatzeko.charAt(i));
						i++; egunZifraKop++;
					}
					else throw new DataFormatException();
				}
				else bukatu = true;
			}
		}
		else throw new DataFormatException();
		
		if(eguna < 1) throw new DataFormatException();
		if((hilabetea == 1 || hilabetea == 3 || hilabetea == 5 || hilabetea == 7 || hilabetea == 8 || hilabetea == 10 || hilabetea == 12) && eguna > 31) throw new DataFormatException();
		if((hilabetea == 4 || hilabetea == 6 || hilabetea == 9 || hilabetea == 11) && eguna > 30) throw new DataFormatException();
		if(hilabetea == 2 && ((new GregorianCalendar().isLeapYear(urtea) && eguna > 29) || (!new GregorianCalendar().isLeapYear(urtea) && eguna > 28))) throw new DataFormatException();
    	return pKonprobatzeko;
    }
    
    /**
     * 
     * @param pKonprobatzekoString
     * @param luzera pKonprobatzekoString izan behar duen gehienezko luzeera
     * @return
     * @throws StringEzEgokiException
     */
    private String stringEgokiaDa(String pKonprobatzekoString, int luzera) throws StringEzEgokiException{
    	if(pKonprobatzekoString.length() > luzera) throw new StringEzEgokiException(true);
    	else if(pKonprobatzekoString.length() == 0) throw new StringEzEgokiException(false);
    	return pKonprobatzekoString;
    }
    
    /**
     * Salbuespen motaren arabera, era batean edo bestean tratatuko da salbuespena.
     * @param e
     */
    private void salbuespenaTratatu(Exception e) {
    	if(e instanceof StringEzEgokiException) {
    		StringEzEgokiException eaux = (StringEzEgokiException) e;
    		eaux.mezuaInprimatu();
    	}
    	else if(e instanceof NumberFormatException) {
    		System.out.println("Zenbakia ez den zeozer sartu duzu edo zenbakia handiegia da."); 
    	}
    	else if(e instanceof SQLException) {
    		SQLException eaux = (SQLException) e; 
    		System.out.print("ERRORE BAT SUERTATU DA DATU BASEAREKIN: ");
    		System.out.println(eaux.getMessage());
    		//System.out.println(eaux.getErrorCode());
    		//if (eaux.getErrorCode()==1062) System.out.println("Kode hori duen produktorea existitzen da.");
    	}
    	else if(e instanceof DataFormatException) {
    		//DataFormatException eaux = (DataFormatException) e;
    		System.out.println("Sartu duzun data ez da egokia.");
    	}
    }
}
