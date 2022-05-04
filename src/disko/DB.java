package disko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.zip.DataFormatException;

import Salbuespenak.StringLuzeegiaException;
//
public class DB {
    private BufferedReader br;
    private Connection konexioa;
    private final static int pasahitza=123;
    
    //erroreak: https://dev.mysql.com/doc/mysql-errors/8.0/en/server-error-reference.html
    
    public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
        DB db = new DB();
        //db.menuErakutsi();
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
            //e.printStackTrace();
        }
    }

    private void menuPrintzipala() {
    	int aukera=-1;
    	try {
        	while(aukera!=0){
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
                System.out.println("\n\nAukera bat sartu: ");
                aukera = Integer.parseInt(br.readLine());
                if(aukera==1 || aukera==2) {
                	System.out.println("Pasahitza sartu behar duzu datuak kudeatzeko: ");
                	boolean ondo=this.pasahitzaZuzenaDa();
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
           
            System.out.println("1.- Produktore berria sartu.");
            System.out.println("2.- Talde berriak sartu.");
            System.out.println("3.- Disko berria sartu.");
            System.out.println("4.- Gira berriak sartu.");
            System.out.println("5.- Gira baten barruan hiri berriak sartu.");
            System.out.println("\n\nAukera bat sartu: ");
            aukera = Integer.parseInt(br.readLine());
            aukeraIrakurri2(aukera, 1, true);

        }catch(Exception e) {
        	salbuespenaTratatu(e);
        }
    }
    private void datuakAldatzekoMenua() {
    	int aukera = -1;
        try {
            
            System.out.println("1.- Giraren bukera eta hasiera datak aldatu.");
            System.out.println("2.- Talde bat kaleratu.");
            System.out.println("3.- Girako hiriak kendu.");
            System.out.println("\n\nAukera bat sartu: ");
            aukera = Integer.parseInt(br.readLine());
            aukeraIrakurri2(aukera,2,true);
        }catch(Exception e) {
        	salbuespenaTratatu(e);
        }
    }
    private void kontsultenMenua() {
    	int aukera = -1;
    	boolean ondo=false;
        try {
            System.out.println("1.- Talde baten gira guztiak lortu.");
            System.out.println("2.- Talde baten gira bateko hiri guztiak sarreraren prezioarekin.");
            System.out.println("3.- Talde baten disko baten abesti guztiak.");
            System.out.println("4.- Talde baten disko guztien prezioak.");
            System.out.println("5.- Talde bat adierazi, produktore bera erabiltzen duten taldeak lortu.");
            System.out.println("6.- Abestiak zenbakiaren arabera ordenatu.");
            System.out.println("7.- Hiriak izenaren arabera ordenatu.");
            System.out.println("8.- Sarrera erosi:");
            System.out.println("9.- Diskoa erosi:");
            System.out.println("\n\nAukera gehiago ikusteko pasahitza sartu nahi duzu? (B/E)");
            String erabaki=br.readLine();
            if(erabaki.equalsIgnoreCase("b")) {
            	ondo=this.pasahitzaZuzenaDa();
            	System.out.println("1.- Talde baten gira guztiak lortu.");
        		System.out.println("2.- Talde baten gira bateko hiri guztiak sarreraren prezioarekin.");
                System.out.println("3.- Talde baten disko baten abesti guztiak.");
                System.out.println("4.- Talde baten disko guztien prezioak.");
                System.out.println("5.- Talde bat adierazi, produktore bera erabiltzen duten taldeak lortu.");
                System.out.println("6.- Abestiak zenbakiaren arabera ordenatu.");
                System.out.println("7.- Hiriak izenaren arabera ordenatu.");
                System.out.println("8.- Sarrera erosi:");
                System.out.println("9.- Diskoa erosi:");
            	if(ondo) {
            		System.out.println("10.- Kalkulatu gira baten irabaziak.");
                    System.out.println("11.- Kalkulatu diskoaren irabaziak.");
                    System.out.println("12.- Talde batek hiri batean lortutako irabaziak.");
                    System.out.println("13.- 1000€ baino gehiago irabazi duten taldeak erakutsi.");
            	}
            }
            System.out.println("\n\nAukera bat sartu: ");
            aukera = Integer.parseInt(br.readLine());
            aukeraIrakurri2(aukera,3,ondo);
   
        }catch(Exception e) {
        	salbuespenaTratatu(e);
        }
    }

    private void menuErakutsi(){
    	int aukera = -1;
        try {
        	while(aukera!=21){
                System.out.println("  __  __ ______ _   _ _    _         \r\n"
                		+ " |  \\/  |  ____| \\ | | |  | |  /\\    \r\n"
                		+ " | \\  / | |__  |  \\| | |  | | /  \\   \r\n"
                		+ " | |\\/| |  __| | . ` | |  | |/ /\\ \\  \r\n"
                		+ " | |  | | |____| |\\  | |__| / ____ \\ \r\n"
                		+ " |_|  |_|______|_| \\_|\\____/_/    \\_\\\r\n"
                		+ "                                     \r\n"
                		+ "                                    ");
                System.out.println("0.- Produktore berria sartu.");
                System.out.println("1.- Talde berriak sartu.");
                System.out.println("2.- Disko berria sartu.");
                System.out.println("3.- Gira berriak sartu.");
                System.out.println("4.- Gira baten barruan hiri berriak sartu.");
                System.out.println("5.- Giraren bukera eta hasiera datak aldatu.");
                System.out.println("6.- Talde bat kaleratu.");
                System.out.println("7.- Girako hiriak kendu.");
                System.out.println("8.- Talde baten gira guztiak lortu.");
                System.out.println("9.- Talde baten gira bateko hiri guztiak sarreraren prezioarekin.");
                System.out.println("10.- Talde baten disko baten abesti guztiak.");
                System.out.println("11.- Talde baten disko guztien prezioak.");
                System.out.println("12.- Talde bat adierazi, produktore bera erabiltzen duten taldek lortu.");
                System.out.println("13.- Kalkulatu gira baten irabaziak.");
                System.out.println("14.- Kalkulatu diskoaren irabaziak.");
                System.out.println("15.- Abestiak zenbakiaren arabera ordenatu.");
                System.out.println("16.- Hiriak izenaren arabera ordenatu.");
                System.out.println("17.- Talde batek hiri batean lortutako irabaziak.");
                System.out.println("18.- 1000€ baino gehiago irabazi duten taldeak erakutsi.");
                System.out.println("19.- Sarrera erosi:");
                System.out.println("20.- Diskoa erosi:");
                System.out.println("21.- Irten");
                System.out.println("\n\nAukera bat sartu: ");
                aukera = Integer.parseInt(br.readLine());
                aukeraIrakurri(aukera);
            }

            konexioa.close();
        }catch(Exception e) {
        	salbuespenaTratatu(e);
        }
        menuErakutsi();
    }

    private void aukeraIrakurri(int aukera){
        switch (aukera){
        	case 0:
        		produktoreBerriaSartu();
        		break;
            case 1:
                taldeakSartu();
                break;
            case 2:
                diskoBerriaSartu();
                break;
            case 3:
                giraBerriakSartu();
                break;
            case 4:
                girarenLekuakSartu(null,0);
                break;
            case 5:
                girarenBukHasDatakAldatu();
                break;
            case 6:
                taldeaKaleratu();
                break;
            case 7:
                lekuakGiratikKendu();
                break;
            case 8:
                taldearenGirakErakutsi();
                break;
            case 9:
                girarenLekuakErakutsi();
                break;
            case 10:
                diskoarenAbestiakErakutsi();
                break;
            case 11:
                diskoenPrezioakErakutsi();
                break;
            case 12:
                taldeProduktoreBera();
                break;
            case 13:
                giraIrabaziak();
                break;
            case 14:
                diskoIrabaziak();
                break;
            case 15:
                diskoaAbestiazOrdenatuaErakutsi();
                break;
            case 16:
                giraLekuezOrdenatutaErakutsi();
                break;
            case 17:
            	lekuBakoitzekoBatezbestekoIrabaziak();
                break;
            case 18:
                euro1000BainoGehiago2022Lekuak();
                break;
            case 19:
            	sarreraErosketaEgin();
            	break;
            case 20:
            	diskoErosketaEgin();
            	break;
            case 21:
                System.exit(0);
                break;
            default:
                System.out.println("Sartu aukera egoki bat");
                break;
        }
    }
    private void aukeraIrakurri2(int aukera, int menu, boolean ondo){
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
                girarenLekuakErakutsi();
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
            	giraLekuezOrdenatutaErakutsi();
                break;
            case 8:
            	sarreraErosketaEgin();
                break;
            case 9:
            	diskoErosketaEgin();
                break;
            case 10:
            	if (ondo)giraIrabaziak();
                break;
            case 11:
                if(ondo)diskoIrabaziak();
                break;
            case 12:
            	if(ondo)lekuBakoitzekoBatezbestekoIrabaziak();
            	break;
            case 13:
            	if(ondo)euro1000BainoGehiago2022Lekuak();
            	break;
            default:
                System.out.println("Sartu aukera egoki bat");
                break;
        }
    	}
        
    }
    
    
    //************************************************INSERT************************************************************
    private void produktoreBerriaSartu() {
    	int saiakera=0;
    	do {
    		try {
        		System.out.println("Sartu produktorearen izena: ");
            	String izena = stringEgokiaDa(br.readLine(), 15);
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
     * @throws SQLException
     * @throws IOException
     * @throws NumberFormatException
     */
    private void taldeakSartu() {
    	int saiakera=0;
    	do{
			try {
				//Taldea sartzeko:
		    	System.out.println("Sartu taldearen izena: ");
		    	String izena = stringEgokiaDa(br.readLine(), 15);
				System.out.println("Sartu taldearen kodea: ");
				int kode = Integer.parseInt(br.readLine());
				System.out.println("Sartu taldearen deskribapena: ");
		    	String desk = stringEgokiaDa(br.readLine(), 15);
				System.out.println("Sartu produktorearen kodea:");
				int pKode = Integer.parseInt(br.readLine());
				PreparedStatement ps = konexioa.prepareStatement("INSERT INTO TALDE VALUES(?, ?, ?, ?)");
		    	ps.setString(2,izena);
		    	ps.setInt(1,kode);
		    	ps.setString(3, desk);
		    	ps.setInt(4, pKode);
		    	ps.executeUpdate();
				//Partaideak sartzeko:
				String pIzen, hizki;
				int partKode;
				do {
					System.out.println("Sartu partaidearen izena: ");
			    	pIzen= br.readLine();
					System.out.println("Sartu partaidearen kodea: ");
					partKode=Integer.parseInt(br.readLine());
					ps = konexioa.prepareStatement("INSERT INTO PARTAIDEA VALUES(?, ?, ?)");
			    	ps.setString(1,pIzen);
			    	ps.setInt(2,partKode);
			    	ps.setInt(3, kode);
			    	ps.executeUpdate();
					System.out.println("Partaide gehiagorik sartu nahi duzu? (B/E)");
					hizki=br.readLine();
				}while(hizki.equalsIgnoreCase("B"));
				saiakera=3;
			}
			catch(Exception e) {
				salbuespenaTratatu(e);
			}
			saiakera++;
		}while(saiakera<3);
    }

    private void diskoBerriaSartu() {
    	int saiakera=0;
    	do {
    		try {
    			System.out.println("Sartu disko izen bat: ");
    	    	String izen = br.readLine();
    	    	System.out.println("Sartu disko kode bat: ");
    	    	int kode = Integer.parseInt(br.readLine());
    	    	System.out.println("Sartu disko prezio bat: ");
    	    	float prezio = Float.parseFloat(br.readLine());
    	    	System.out.println("Sartu talde baten kodea: ");
    	    	int talKode = Integer.parseInt(br.readLine());
    	    	PreparedStatement ps = konexioa.prepareStatement("INSERT INTO DISKO VALUES(?, ?, ?, 0, ?)");
    	    	ps.setString(1, izen);
    	    	ps.setInt(2, kode);
    	    	ps.setFloat(3, prezio);
    	    	ps.setInt(4, talKode);
    	    	ps.executeUpdate();
    	    	String erantzun;
    	    	int kont = 0;
    	    	do{
    	    		kont++; 
    	    		System.out.println("Sartu abesti izen bat: ");
    	    		String izena = br.readLine();
    	    		PreparedStatement psa = konexioa.prepareStatement("INSERT INTO ABESTIA VALUES(?, ?, ?)");
    	    		psa.setString(1, izena);
    	    		psa.setInt(2,kont);
    	    		psa.setInt(3, kode);
    	    		psa.executeUpdate();
    	    		System.out.println("Sartu berri duzun disko horretan abesti berriak sartu nahi dituzu? (B/E)");
    	    		erantzun = br.readLine();
    	    	} while(erantzun.equalsIgnoreCase("B"));
    			saiakera=3;
    		}
    		catch(Exception e) {
    			salbuespenaTratatu(e);
    		}
    		saiakera++;
    	}while(saiakera<3);
    	
    }

    /**
     * Talde bati gira berri bat sartuko zaio. Behin gira hori sartu dela, gira horren barruan hiriak eta lekuak sartu nahi diren eskatuko da.
     * Hiri eta leku berriak sartu nahi badira, "girarenHiriakSartu" metodoari dei egingo zaio.
     * @throws SQLException 
     * @throws IOException
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
     * 
     * @throws SQLException
     * @throws IOException
     * @throws NumberFormatException
     */
    private void girarenLekuakSartu(String hData, int tKode) {
		int saiakera=0;
		String hasData = hData;
		int taldeK = tKode;
	    do{
	    	try{
	    		if(hData == null && tKode == 0) {
	    			System.out.println("Sartu jotzen duen taldearen kodea: ");
		        	taldeK = Integer.parseInt(br.readLine());
		        	System.out.println("Sartu gira berriaren hasiera data (UUUU-HH-EE formatuan): ");
		        	hasData = konprobatuDataFormatua(br.readLine());
	    		}
	        	String hizki;
	        	do {
	    			System.out.println("Sartu herrialdearen izena: ");
	    		    String herrialde= br.readLine();
	    		    System.out.println("Sartu hiriaren izena: ");
	    		    String hiriIzen= br.readLine();
	    		    System.out.println("Sartu lekuaren izena: ");
    	        	String lekua= br.readLine();
    	    		System.out.println("Sartu sarreren prezioa: ");
    	        	Float prezioa= Float.valueOf(br.readLine());
    	        	if(!lekuaExisititzenDa(herrialde, hiriIzen, lekua)) {
    	        		PreparedStatement ps = konexioa.prepareStatement("INSERT INTO LEKUA VALUES(?, ?, ?)");
    	        		//Hiria sortzeko:
    	    			ps.setString(1,lekua);
    	    			ps.setString(2,hiriIzen);
    	    	    	ps.setString(3,herrialde);
    	    	    	//ps.setFloat(4, prezioa);
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
     * @throws IOException
     * @throws SQLException
     * @throws NumberFormatException
     */
    private void girarenBukHasDatakAldatu(){
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Aldatu nahi duzun girak egiten duen taldearen KODEA adierazi: ");
	        	int kodea= Integer.parseInt(br.readLine());
	        	System.out.println("Sartu zein den aldatu nahi duzun giraren hasiera data (UUUU-HH-EE formatuan): ");
	    		String data = konprobatuDataFormatua(br.readLine());
	        	String dataB;
	        	System.out.println("Hasiera data aldatu nahi duzu? Horrela bada sakatu 'B' hizkia, bestela sakatu beste bat.");
	        	if(br.readLine().equalsIgnoreCase("B")) {
	        		System.out.println("Sartu hasiera data berria (UUUU-HH-EE formatuan): ");
	        		dataB = konprobatuDataFormatua(br.readLine());
	        		PreparedStatement ps = konexioa.prepareStatement("UPDATE GIRA SET HasData = ? WHERE TaldeK = ? AND HasData = ?");
	            	ps.setString(1,dataB);
	            	ps.setInt(2,kodea);
	            	ps.setString(3,data);
	            	ps.executeUpdate();
	        	}
	        	System.out.println("Bukaera data aldatu nahi duzu? Horrela bada sakatu 'B' hizkia, bestela sakatu beste bat.");
	        	if(br.readLine().equalsIgnoreCase("B")) {
	        		System.out.println("Sartu bukaera data berria (UUUU-HH-EE formatuan): ");
	        		dataB = konprobatuDataFormatua(br.readLine());
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

    private void sarrerakEguneratu(int kop, String hasData, String hiria, String izena) {
    	int saiakera=0;
    	do {
    		try {
    			PreparedStatement ps = konexioa.prepareStatement(
    					"UPDATE Lekuan_Jo SET SaldutakoSarrerak = (SaldutakoSarrerak + ?) "+ 
    					"WHERE Hiria = ? AND izena = ? AND hasData= ?"
    			);
    	    	ps.setInt(1,kop);
    	    	ps.setString(2,hiria);
    	    	ps.setString(3,izena);
    	    	ps.setString(4, hasData);
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
     * @throws IOException
     * @throws SQLException
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
     * @throws IOException
     * @throws SQLException
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
    			"FROM Lekua" +
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
     * Gira baten hiri guztiak hauek duten sarreren prezioarekin.
     * Horretatrako,
     * @throws IOException
     * @throws SQLException
     */
    private void girarenLekuakErakutsi(){		
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Sartu taldearen izena:");
	            String taldeIzen = br.readLine();
	            System.out.println("Sartu zein den " + taldeIzen + " taldetik ikusi nahi duzun giraren hasiera data (UUUU-HH-EE formatuan): ");
	    		String data = konprobatuDataFormatua(br.readLine());
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT LEKUAN_JO.Hiria, LEKUAN_JO.Herrialdea, LEKUAN_JO.Izena, LEKUAN_JO.Prezioa " +
	                    "FROM TALDE, LEKUAN_JO " +
	                    "WHERE TALDE.kodea =LEKUAN_JO.TaldeK AND "+
	                            "LEKUAN_JO.hasData = ? AND " +
	                            "TALDE.Izena = ?");
	            ps.setString(1,data);
	            ps.setString(2,taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)+ " " + rs.getFloat(4)+"€");
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
    	            System.out.println(rs.getString(1) + " " + rs.getFloat(2));
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
	                            "WHERE TALDE.izena= ?)");
	            ps.setString(1, taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	                System.out.println(rs.getString(1));
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
	            System.out.println(rs.getString(1));
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
    	        System.out.println(rs.getFloat(1));
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
    	    			"SELECT  DISKO.Kodea, DISKO.Izena, ABESTIA.* " + 
    	    			"FROM TALDE, DISKO, ABESTIA " + 
    	    			"WHERE TALDE.kodea = DISKO.TaldeK AND " + 
    	    			"DISKO.Kodea=ABESTIA.DiskoK AND DISKO.Izena=?  AND TALDE.Izena= ?  " + 
    	    			"ORDER BY ABESTIA.Zenbakia ");
    	    	ps.setString(1,diskoIzena);
    	        ps.setString(2,taldeIzena);
    	        ResultSet rs = ps.executeQuery();
    	        while(rs.next()){
    	            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getInt(5));
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
	            System.out.println("Sartu zein den "+taldeIzen+" taldetik ikusi nahi duzun giraren hasiera data (UUUU-HH-EE formatuan): ");
	    		String data = konprobatuDataFormatua(br.readLine());
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT  LEKUAN_JO.Izena, LEKUAN_JO.HIRIA, LEKUAN_JO.HERRIALDEA " +
	                    "FROM  TALDE,  LEKUAN_JO " +
	                    "WHERE TALDE.kodea=LEKUAN_JO.TaldeK AND "+
	                            "LEKUAN_JO.hasData = ? AND " +
	                            "TALDE.Izena = ?"+
	                    "ORDER BY LEKUAN_JO.Izena");
	            ps.setString(1,data);
	            ps.setString(2,taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
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
    			System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getFloat(4)); 
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
				System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getFloat(4));
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
	            		"SELECT TALDE.IZENA, TALDE.KODEA, DISKO.IZENA, DISKO.PREZIOA " + 
	            		"FROM TALDE, DISKO " + 
	            		"WHERE TALDE.izena = ? and TALDE.kodea = DISKO.TALDEK ");
	            ps.setString(1,taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            System.out.println("Hauek dira " + taldeIzen + " taldeak dituen diskoak:");
	            int taldek=0;
	            boolean daude=false;
	            while(rs.next()){
	                System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
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
	                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getFloat(5));
	            }
	            if(daude) {
	            	System.out.println("Aukeratu horietako bat: ");
	 	            System.out.println("Sartu hasiera data (UUUU-HH-EE formatuan): ");
	 	            String hasData = konprobatuDataFormatua(br.readLine());
	 	            System.out.println("Sartu hiriaren izena: ");
	 	            String hiria = br.readLine();
	 	            System.out.println("Sartu lekuaren izena: ");
	 	            String lekuIzen = br.readLine();
	 	            System.out.println("Sartu erosi nahi dituzun sarrera kopurua: ");
	 	            int kop = Integer.parseInt(br.readLine());
	 	            sarrerakEguneratu(kop, hasData, hiria, lekuIzen);
	 	            System.out.println("Erosketa zuzen burtu da.");
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
    			System.out.println("Pasahitza sartu: ");
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
     * String bat UUUU-HH-EE formatuan idatzita dagoen konprobatzen du.
     * @param pKonprobatzeko
     * @throws DataFormatException
     */
    private String konprobatuDataFormatua(String pKonprobatzeko) throws DataFormatException{
    	char karakterea;
    	boolean bukatu = false;
    	int i = 0;
    	while(i < 9 && !bukatu) {
        	karakterea = pKonprobatzeko.charAt(i);
        	if(i == 0 || i == 1 || i == 2 || i ==3 || i == 5 || i == 6 || i == 8 || i == 9) {
        		if(!Character.isDigit(karakterea)) bukatu = true;
        	}
        	else if(karakterea != '-') bukatu = true;
    	}
    	if(bukatu) throw new DataFormatException();
    	else return pKonprobatzeko;
    }
    
    /**
     * 
     * @param pKonprobatzekoString
     * @param luzera pKonprobatzekoString izan behar duen gehienezko luzeera
     * @return
     * @throws StringLuzeegiaException
     */
    private String stringEgokiaDa(String pKonprobatzekoString, int luzera) throws StringLuzeegiaException{
    	if(pKonprobatzekoString.length() > luzera) throw new StringLuzeegiaException();
    	return pKonprobatzekoString;
    }
    
    /**
     * Salbuespen motaren arabera, era batean edo bestean tratatuko da salbuespena.
     * @param e
     */
    private void salbuespenaTratatu(Exception e) {
    	if(e instanceof StringLuzeegiaException) {
    		StringLuzeegiaException eaux = (StringLuzeegiaException) e;
    		eaux.mezuaInprimatu();
    	}
    	else if(e instanceof NumberFormatException) {
    		NumberFormatException eaux = (NumberFormatException) e;
    		System.out.println(eaux.getMessage());
    		System.out.println(eaux.getLocalizedMessage());
    		System.out.println("Zenbakia ez den zeozer sartu duzu edo zenbakia handiegia da."); 
    	}
    	else if(e instanceof SQLException) {
    		SQLException eaux = (SQLException) e; 
    		System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
    		System.out.println(eaux.getMessage());
    		System.out.println(eaux.getErrorCode());
    		if (eaux.getErrorCode()==1062) System.out.println("Kode hori duen produktorea existitzen da.");
    	}
    	else if(e instanceof DataFormatException) {
    		DataFormatException eaux = (DataFormatException) e;
    		System.out.println("Sartu duzun data formatua ez da egokia.");
    	}
    }
}
