package disko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
//
public class DB {
    private BufferedReader br;
    private Connection konexioa;
    private static int pasahitza=123;
    
    public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
        DB db = new DB();
        db.menuErakutsi();
    }

    private DB(){
        br = new BufferedReader(new InputStreamReader(System.in));
        konektatu();
    }
    
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

    private void konektatu(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String zerbitzaria = "jdbc:mysql://localhost:3306/diskodenda";
            String erabiltzailea = "root";
            String pasahitza = "";
            konexioa = DriverManager.getConnection(zerbitzaria,erabiltzailea,pasahitza);
        }
        catch(Exception e){
        	System.out.println("Errorea egon da datu basea kargatzean.");
            e.printStackTrace();
        }
    }

    private void menuErakutsi() throws NumberFormatException, IOException, SQLException{
    	int aukera = -1;
        try {
        	while(aukera!=20){
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
                System.out.println("5.- Saldutako sarreraren zenbakia eguneratu.");
                System.out.println("6.- Giraren bukera eta hasiera datak aldatu.");
                System.out.println("7.- Talde bat kaleratu.");
                System.out.println("8.- Girako hiriak kendu.");
                System.out.println("9.- Talde baten gira guztiak lortu.");
                System.out.println("10.- Talde baten gira bateko hiri guztiak sarreraren prezioarekin.");
                System.out.println("11.- Talde baten disko baten abesti guztiak.");
                System.out.println("12.- Talde baten disko guztien prezioak.");
                System.out.println("13.- Talde bat adierazi, produktore bera erabiltzen duten taldek lortu.");
                System.out.println("14.- Kalkulatu gira baten irabaziak.");
                System.out.println("15.- Kalkulatu diskoaren irabaziak.");
                System.out.println("16.- Abestiak zenbakiaren arabera ordenatu.");
                System.out.println("17.- Hiriak izenaren arabera ordenatu.");
                System.out.println("18.- Talde batek hiri batean lortutako irabaziak.");
                System.out.println("19.- 1000€ baino gehiago irabazi duten taldeak erakutsi.");
                System.out.println("20.- Irten");
                System.out.println("\n\nAukera bat sartu: ");
                aukera = Integer.parseInt(br.readLine());
                aukeraIrakurri(aukera);
            }
            konexioa.close();
        }catch(NumberFormatException e) {
        	System.out.println("Zenbakia ez den zeozer sartu duzu.");
        }
        menuErakutsi();
    }

    private void aukeraIrakurri(int aukera) throws IOException,SQLException{
    	if((aukera==5 && aukera==9 && aukera==10 && aukera==11 && aukera==12 && aukera==13 && aukera==16 && aukera==17) || !pasahitzaZuzenaDa()) aukera=100;
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
                girarenHiriakSartu();
                break;
            case 5:
                sarrerakEguneratu();
                break;
            case 6:
                girarenBukHasDatakAldatu();
                break;
            case 7:
                taldeKaleratu();
                break;
            case 8:
                lekuakKenduGiratik();
                break;
            case 9:
                taldearenGirak();
                break;
            case 10:
                girarenLekuak();
                break;
            case 11:
                diskoarenAbestiak();
                break;
            case 12:
                diskoenPrezioa();
                break;
            case 13:
                taldeProduktoreBera();
                break;
            case 14:
                giraIrabaziak();
                break;
            case 15:
                diskoIrabaziak();
                break;
            case 16:
                diskoAbestiakOrdenatu();
                break;
            case 17:
                giraLekuakOrdenatuta();
                break;
            case 18:
            	lekuBatekoIrabaziak();
                break;
            case 19:
                milaBainoGehiago();
                break;
            case 20:
                System.exit(0);
                break;
            default:
                System.out.println("Sartu aukera egoki bat");
                break;
        }
    }
    
    private void produktoreBerriaSartu() throws SQLException, IOException, NumberFormatException{
    	int saiakera=0;
    	do {
    		try {
        		System.out.println("Sartu produktorearen izena: ");
            	String izena = br.readLine();
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
        	}catch(NumberFormatException e) {
        		System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");   		
        	}catch(SQLException e) {
        		System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
        		System.out.println(e.getMessage());
        		System.out.println(e.getErrorCode());
        		if (e.getErrorCode()==1062) System.out.println("Kode hori duen produktorea existitzen da.");
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
    private void taldeakSartu() throws SQLException, IOException, NumberFormatException{
    	int saiakera=0;
    	do{
			try {
				//Taldea sartzeko:
		    	System.out.println("Sartu taldearen izena: ");
		    	String izena= br.readLine();
				System.out.println("Sartu taldearen kodea: ");
				int kode=Integer.parseInt(br.readLine());
				System.out.println("Sartu taldearen deskribapena: ");
		    	String desk= br.readLine();
				System.out.println("Sartu produktorearen kodea:");
				int pKode=Integer.parseInt(br.readLine());
				PreparedStatement ps = konexioa.prepareStatement("INSERT INTO TALDE VALUES(?, ?, ?, ?)");
		    	ps.setString(2,izena);
		    	ps.setInt(1,kode);
		    	ps.setString(3, desk);
		    	ps.setInt(4, pKode);
		    	ps.executeUpdate();
				//Partaideak sartzeko:
				boolean jarraitu=true;
				String pIzen;
				int partKode;
				String hizki;
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
					System.out.println("Gelditzeko sakatu 'G', bestela beste tekla sakatu.");
					hizki=br.readLine();
					if(hizki.equalsIgnoreCase("G")) {jarraitu=false;}
				}while(jarraitu);
				saiakera=3;
			}catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
        		System.out.println(e.getErrorCode());
        		if (e.getErrorCode()==1062) System.out.println("Kode edo izen hori duen taldea existitzen da.");
			}
			saiakera++;
		}while(saiakera<3);
    }

    private void diskoBerriaSartu() throws IOException, SQLException{
    	System.out.println("Sartu disko izen bat: ");
    	String izen = br.readLine();
    	System.out.println("Sartu disko kode bat: ");
    	int kode = Integer.parseInt(br.readLine());
    	System.out.println("Sartu disko prezio bat: ");
    	float prezio = Float.parseFloat(br.readLine());
    	System.out.println("Sartu talde baten kode bat: ");
    	int talKode = Integer.parseInt(br.readLine());
    	PreparedStatement ps = konexioa.prepareStatement("INSERT INTO DISKO VALUES(?, ?, 0, ?)");
    	ps.setString(1, izen);
    	ps.setInt(2, kode);
    	ps.setFloat(3, prezio);
    	ps.setInt(4, talKode);
    	ps.executeUpdate();
    	String erantzun;
    	int kont = 0;
    	do{
    		kont = kont + 1; 
    		System.out.println("Sartu abesti izen bat: ");
    		String izena = br.readLine();
    		PreparedStatement psa = konexioa.prepareStatement("INSERT INTO DISKO VALUES(?, " + kont + ", " + kode + ")");
    		psa.setString(1, izena);
    		psa.executeUpdate();
    		System.out.println("Sartu berri duzun disko horretan abesti berriak sartu nahi dituzu? (B/E)");
    		erantzun = br.readLine();
    	} while(erantzun.equalsIgnoreCase("B"));
    }

    /**
     * Talde bati gira berri bat sartuko zaio. Behin gira hori sartu dela, gira horren barruan hiriak eta lekuak sartu nahi diren eskatuko da.
     * Hiri eta leku berriak sartu nahi badira, "girarenHiriakSartu" metodoari dei egingo zaio.
     * @throws SQLException 
     * @throws IOException
     */
    private void giraBerriakSartu() throws SQLException, IOException{
    	int saiakera=0;
    	do {
    		try {
    			System.out.println("Sartu gira berriaren hasiera data (UUUU-HH-EE formatuan): ");
    	    	String hasData = br.readLine();
    	    	System.out.println("Sartu gira berriaren bukaera data (UUUU-HH-EE formatuan): ");
    	    	String bukData = br.readLine();
    	    	System.out.println("Sartu gira horren talde kodea: ");
    	    	int taldeKode = Integer.parseInt(br.readLine());
    	    	PreparedStatement ps = konexioa.prepareStatement("INSERT INTO GIRA VALUES(?, ?, ?)");
    	    	ps.setString(1,hasData);
    	    	ps.setString(2,bukData);
    	    	ps.setInt(3,taldeKode);
    	    	ps.executeUpdate();
    	    	System.out.println("Sartu berri duzun gira horretan hiri eta leku berriak sartu nahi dituzu? (B/E)");
    	    	String erantzun = br.readLine();
    	    	if(erantzun.equalsIgnoreCase("B")) sortuBerriDenGirarenLekuakSartu(hasData, taldeKode);
    	    	saiakera=3;
    		}catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
        		System.out.println(e.getErrorCode());
        		if (e.getErrorCode()==1062) System.out.println("Hasiera data hori duen gira badu taldea.");
			}
			saiakera++;
		}while(saiakera<3);
    }
    
    private void sortuBerriDenGirarenLekuakSartu(String hData, int tKode) throws SQLException, IOException, NumberFormatException{
		String herrialde="";
		String hiriIzen="";
		String hizki, lekua="";
		Float prezioa;
		PreparedStatement ps, ps2=konexioa.prepareStatement("");
		int errore=0;
		int saiakera=0;
	    do{
	    	try{
	    		do {
	    			System.out.println("Sartu herrialdearen izena: ");
	    		    herrialde= br.readLine();
	    		    System.out.println("Sartu hiriaren izena: ");
	    		    hiriIzen= br.readLine();
	    		    System.out.println("Sartu lekuaren izena: ");
    	        	lekua= br.readLine();
    	    		System.out.println("Sartu sarreren prezioa: ");
    	        	prezioa= Float.valueOf(br.readLine());
	    		    ps2 = konexioa.prepareStatement("INSERT INTO LEKUAN_JO VALUES(?, ?, ?, ?, ?)");
	    			ps = konexioa.prepareStatement("INSERT INTO LEKUA VALUES(?, ?, ?, 0, ?)");
	    			//Hiria sortzeko:
	    			ps.setString(1,lekua);
	    			ps.setString(2,hiriIzen);
	    	    	ps.setString(3,herrialde);
	    	    	ps.setFloat(4, prezioa);
	    	    	ps.executeUpdate();
	    	    	errore++;
	    	    	
	    	    	//Hiria eta gira konektatzeko:
	    	    	ps2.setString(1,herrialde);
	    	    	ps2.setString(2,hiriIzen);
	    	    	ps2.setString(3,lekua);
	    	    	ps2.setString(4, hData);
	    	    	ps2.setInt(5, tKode);
	    	    	ps2.executeUpdate();
	    	    	//Hirian lekuak gehitzeko:
	    	    	System.out.println("Leku gehiago EZ sartzeko sakatu 'G', bestela beste tekla sakatu.");
	    			hizki=br.readLine();
	    		}while(!hizki.equalsIgnoreCase("G"));
	    		saiakera=3;
	    		
		    }catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
				if (e.getErrorCode()==1062 && errore==0) {
					System.out.println("Lekua jada existitzen da datu basean, baina girari gehituko zaio.");
					ps2.setString(1,herrialde);
	    	    	ps2.setString(2,hiriIzen);
	    	    	ps2.setString(3,lekua);
	    	    	ps2.setString(4, hData);
	    	    	ps2.setInt(5, tKode);
	    	    	ps2.executeUpdate();
	    	    	System.out.println("Leku gehiago EZ sartzeko sakatu 'G', bestela beste tekla sakatu.");
	    			hizki=br.readLine();
	    			if(!hizki.equalsIgnoreCase("G")) sortuBerriDenGirarenLekuakSartu(hData, tKode);
	    			else saiakera=3;
				}
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
    private void girarenHiriakSartu() throws SQLException, IOException, NumberFormatException{
		String herrialde="";
		String hiriIzen="";
		String lekua="";
		String hizki, hasData="";
		Float prezioa;
		int taldeK=0;
		int saiakera=0;
		PreparedStatement ps, ps2=konexioa.prepareStatement("");
		
	    do{
	    	try{
	    		System.out.println("Sartu jotzen duen taldearen kodea: ");
	        	taldeK= Integer.parseInt(br.readLine());
	        	System.out.println("Sartu gira berriaren hasiera data (UUUU-HH-EE formatuan): ");
	        	hasData = br.readLine();
	        	do {
	    			System.out.println("Sartu herrialdearen izena: ");
	    		    herrialde= br.readLine();
	    		    System.out.println("Sartu hiriaren izena: ");
	    		    hiriIzen= br.readLine();
	    		    System.out.println("Sartu lekuaren izena: ");
    	        	lekua= br.readLine();
    	    		System.out.println("Sartu sarreren prezioa: ");
    	        	prezioa= Float.valueOf(br.readLine());
	    		    ps2 = konexioa.prepareStatement("INSERT INTO LEKUAN_JO VALUES(?, ?, ?, ?, ?)");
	    			ps = konexioa.prepareStatement("INSERT INTO LEKUA VALUES(?, ?, ?, 0, ?)");
	    			//Hiria sortzeko:
	    			ps.setString(1,lekua);
	    			ps.setString(2,hiriIzen);
	    	    	ps.setString(3,herrialde);
	    	    	ps.setFloat(4, prezioa);
	    	    	ps.executeUpdate();
	    	    	
	    	    	//Hiria eta gira konektatzeko:
	    	    	ps2.setString(1,herrialde);
	    	    	ps2.setString(2,hiriIzen);
	    	    	ps2.setString(3,lekua);
	    	    	ps2.setString(4, hasData);
	    	    	ps2.setInt(5, taldeK);
	    	    	ps2.executeUpdate();
	    	    	//Hirian lekuak gehitzeko:
	    	    	System.out.println("Leku gehiago EZ sartzeko sakatu 'G', bestela beste tekla sakatu.");
	    			hizki=br.readLine();
	    		}while(!hizki.equalsIgnoreCase("G"));
	    		saiakera=3;
	    		
		    }catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("Lekua jada existitzen da datu basean, baina girari gehituko zaio.");
				ps2.setString(1,herrialde);
    	    	ps2.setString(2,hiriIzen);
    	    	ps2.setString(3,lekua);
    	    	ps2.setString(4, hasData);
    	    	ps2.setInt(5, taldeK);
    	    	ps2.executeUpdate();
    	    	System.out.println("Leku gehiago EZ sartzeko sakatu 'G', bestela beste tekla sakatu.");
    			hizki=br.readLine();
    			if(!hizki.equalsIgnoreCase("G")) sortuBerriDenGirarenLekuakSartu(hasData, taldeK);
    			else saiakera=3;
			}
			saiakera++;
		}while(saiakera<3);
    }

    private void sarrerakEguneratu(){

    }
    
    /**
     * Gira bateko datetan arazoak egonda hauek aldatuko dira:
     * @throws IOException
     * @throws SQLException
     * @throws NumberFormatException
     */
    private void girarenBukHasDatakAldatu()throws SQLException, NumberFormatException, IOException{
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Aldatu nahi duzun girak egiten duen taldearen KODEA adierazi: ");
	        	int kodea= Integer.parseInt(br.readLine());
	        	System.out.println("Sartu zein den aldatu nahi duzun giraren hasiera data (UUUU-HH-EE formatuan): ");
	    		String data=br.readLine();
	        	String dataB;
	        	System.out.println("Hasiera data aldatu nahi duzu? Horrela bada sakatu 'B' hizkia, bestela sakatu beste bat.");
	        	if(br.readLine().equalsIgnoreCase("B")) {
	        		System.out.println("Sartu hasiera data berria (UUUU-HH-EE formatuan): ");
	        		dataB=br.readLine();
	        		PreparedStatement ps = konexioa.prepareStatement("UPDATE GIRA SET HasData = ? WHERE TaldeK = ? AND HasData = ?");
	            	ps.setString(1,dataB);
	            	ps.setInt(2,kodea);
	            	ps.setString(3,data);
	            	ps.executeUpdate();
	        	}
	        	System.out.println("Bukaera data aldatu nahi duzu? Horrela bada sakatu 'B' hizkia, bestela sakatu beste bat.");
	        	if(br.readLine().equalsIgnoreCase("B")) {
	        		System.out.println("Sartu bukaera data berria (UUUU-HH-EE formatuan): ");
	        		dataB=br.readLine();
	        		PreparedStatement ps = konexioa.prepareStatement("UPDATE GIRA SET BukData = ? WHERE TaldeK = ? AND HasData = ?");
	            	ps.setString(1,dataB);
	            	ps.setInt(2,kodea);
	            	ps.setString(3,data);
	            	ps.executeUpdate();
	        	}
	        	saiakera=3;
		    }catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			}
			saiakera++;
		}while(saiakera<3);
    }

    /**
     * Talde baten kodea sartuta, talde hori datu basetik aterako da. Dependentzia guztiak "ON DELETE CASCADE" jarrita daudenez, hauek ere ezabatuko dira.
     * @throws IOException
     * @throws SQLException
     */
    private void taldeKaleratu() throws IOException,SQLException{
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Sartu kaleratu nahi duzun taldearen kodea: ");
	        	String taldeIzen = br.readLine();
	        	PreparedStatement ps = konexioa.prepareStatement("DELETE FROM TALDE WHERE Kodea = ?");
	        	ps.setString(1, taldeIzen);
	        	ps.executeUpdate();
	        	saiakera=3;
		    }catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			}
			saiakera++;
		}while(saiakera<3);
    }

    /**
     * Erabiltzaileari datu basetik girako bateko zenbait hiri ezabatzeko aukera emango zaio.
     * @throws IOException
     * @throws SQLException
     */
    private void lekuakKenduGiratik() throws IOException,SQLException{
    	int saiakera=0;
    	String erantzuna;
	    do{
	    	try{
	    		do {
	        		System.out.println("Sartu taldearen identifikazio kodea: ");
	        		int taldeKode = Integer.parseInt(br.readLine());
	        		System.out.println("Sartu " + taldeKode + " taldearen giraren hasiera-data: ");
	        		String hasData = br.readLine();
	            	System.out.println("Sartu kendu nahi duzun lekuaren hiri izena: ");
	            	String hiriIzen = br.readLine();
	            	System.out.println("Sartu kendu nahi duzun lekuaren herrialdea: ");
	            	String hiriHerrialde = br.readLine();
	            	System.out.println("Sartu kendu nahi duzun lekuaren izena: ");
	            	String lekua=br.readLine();
	            	PreparedStatement ps = konexioa.prepareStatement("DELETE FROM Lekuan_Jo WHERE Hiria = ? AND "
	            																			+ "Herrialdea = ? AND "
	            																			+ "HasData = ? AND "
	            																			+ "TaldeK = ? AND"
	            																			+ "Izena = ?");
	            	ps.setString(1,hiriIzen);
	            	ps.setString(2, hiriHerrialde);
	            	ps.setString(3, hasData);
	            	ps.setInt(4, taldeKode);
	            	ps.setString(5, lekua);
	            	ps.executeUpdate();
	            	System.out.println("Hiri gehiago ezabatu nahi dituzu? (B/E)");
	            	erantzuna = br.readLine();
	        	}while(erantzuna.equalsIgnoreCase("E"));
	    		saiakera=3;
		    }catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			}
			saiakera++;
		}while(saiakera<3);
    }

    private void taldearenGirak(){
    	
    }

    /**
     * Gira baten hiri guztiak hauek duten sarreren prezioarekin.
     * Horretatrako,
     * @throws IOException
     * @throws SQLException
     */
    private void girarenLekuak() throws SQLException, IOException{		
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Sartu taldearen izena:");
	            String taldeIzen = br.readLine();
	            System.out.println("Sartu zein den "+taldeIzen+" taldetik ikusi nahi duzun giraren hasiera data (UUUU-HH-EE formatuan): ");
	    		String data=br.readLine();
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT LEKUA.Hiria, LEKUA.Herrialdea, LEKUA.Izena, LEKUA.Prezioa " +
	                    "FROM LEKUA, TALDE, GIRA, LEKUAN_JO" +
	                    "WHERE TALDE.kodea = GIRA.TaldeK AND " +
	                    		"GIRA.TaldeK=LEKUAN_JO.TaldeK AND GIRA.hasData=LEKUAN_JO.hasData AND"+
	                    		"LEKUA.Herrialdea=LEKUAN_JO.Herrialdea AND LEKUA.Izena=LEKUAN_JO.Izena AND"+
	                    		"LEKUAN_JO.Hiria=LEKUA.Hiria AND"+
	                            "GIRA.hasData = ? AND " +
	                            "TALDE.Izena = ?");
	            ps.setString(1,data);
	            ps.setString(2,taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
	            }
	            saiakera=3;
		    }catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
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
    private void diskoarenAbestiak() throws IOException,SQLException{
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Sartu taldearen izena:");
	            String taldeIzen = br.readLine();
	            System.out.println("Sartu " + taldeIzen + " taldearen diskoaren izena:");
	            String diskoIzen = br.readLine();
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT DISKO.Kodea, DISKO.Izena, ABESTIA.* " +
	                    "FROM TALDE, DISKO, ABESTIA " +
	                    "WHERE TALDE.kodea = DISKO.TaldeK AND " +
	                            "DISKO.Kodea = ABESTIA.DiskoK AND " +
	                            "DISKO.izena = ? AND " +
	                            "TALDE.Izena = ?");
	            ps.setString(1,diskoIzen);
	            ps.setString(2,taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
	            }
	            saiakera=3;
		    }catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			}
			saiakera++;
		}while(saiakera<3);
    }

    private void diskoenPrezioa(){

    }
    /**
     * Talde baten produktore bera erabiltzen duten taldeak.
     * Horretarako, taldearen izena adieraziko da.
     * @throws IOException
     * @throws SQLException
     */
    private void taldeProduktoreBera()throws IOException,SQLException{
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Sartu taldearen izena:");
	            String taldeIzen = br.readLine();
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT TALDE.Izena " +
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
		    }catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
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
    
    private void giraIrabaziak() throws IOException,SQLException{
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Sartu talde baten izena: ");
	            String taldeIzen = br.readLine();
	            System.out.println("Sartu girak identifikatzen duen hasiera data: ");
	            String giraData = br.readLine();
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT SUM(LEKUA.SaldutakoSarrerak * LEKUA.Prezioa) " +
	                    "FROM TALDE, LEKUA, GIRA, LEKUAN_JO " +
	                    "WHERE TALDE.izena = ? and " +
	                    	"GIRA.TaldeK = TALDE.Kodea and " +
	                        "GIRA.TaldeK = LEKUAN_JO.TaldeK and " +
	                        "GIRA.HasData = LEKUAN_JO.HasData and " +
	                        "LEKUAN_JO.HasData = ? and " +
	                        "LEKUA.Herrialdea = LEKUAN_JO.herrialdea and " +
	                        "LEKUA.Hiria = LEKUAN_JO.Hiria and"+
	                        "LEKUA.Izena = LEKUAN_JO.Izena");
	            ps.setString(1,taldeIzen);
	            ps.setString(2,giraData);
	            ResultSet rs = ps.executeQuery();
	            rs.next();
	            System.out.println(rs.getString(1));
	            saiakera=3;
		    }catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			}
			saiakera++;
		}while(saiakera<3);
    }

    private void diskoIrabaziak(){
    	
    }

    private void diskoAbestiakOrdenatu(){
    	
    }

    private void giraLekuakOrdenatuta()throws SQLException, IOException{	
    	int saiakera=0;
	    do{
	    	try{
	        	System.out.println("Sartu taldearen izena:");
	            String taldeIzen = br.readLine();
	            System.out.println("Sartu zein den "+taldeIzen+" taldetik ikusi nahi duzun giraren hasiera data (UUUU-HH-EE formatuan): ");
	    		String data=br.readLine();
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT HIRIA.Izena, LEKUA.Izena" +
	                    "FROM LEKUA, TALDE, GIRA, LEKUAN_JO" +
	                    "WHERE TALDE.kodea = GIRA.TaldeK AND " +
	                    		"GIRA.TaldeK=LEKUAN_JO.TaldeK AND GIRA.hasData=LEKUAN_JO.hasData AND"+
	                    		"LEKUA.Herrialdea=LEKUAN_JO.Herrialdea AND LEKUA.Izena=LEKUAN_JO.Izena AND"+
	                    		"LEKUA.Hiria=LEKUAN_JO.Hiria AND"+
	                            "GIRA.hasData = ? AND " +
	                            "TALDE.Izena = ?"+
	                    "ORDER BY HIRIA.Izena");
	            ps.setString(1,data);
	            ps.setString(2,taldeIzen);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	                System.out.println(rs.getString(1) + " " + rs.getString(2));
	            }
	            saiakera=3;
		    }catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			}
			saiakera++;
		}while(saiakera<3);
    }

    /**
     * Talde batek hiri baten iriabazitako dirua adierziko da.
     * Horretarako, taldearen izena eta hiriaren izena eskatuko dira.
     * @throws IOException
     * @throws SQLException
     */
    private void lekuBatekoIrabaziak() throws IOException,SQLException{
    	int saiakera=0;
	    do{
	    	try{
	    		System.out.println("Sartu talde baten izena: ");
	            String taldeIzen = br.readLine();
	            System.out.println("Sartu leku baten izena: ");
	            String leku = br.readLine();
	            PreparedStatement ps = konexioa.prepareStatement(
	                    "SELECT LEKUA.Izena, COUNT(*), SUM(Prezioa*SaldutakoSarrerak) " +
	                    "FROM LEKUA, TALDE, GIRA, LEKUAN_JO " +
	                    "WHERE TALDE.Izena=? AND " +
	                        "TALDE.Kodea=GIRA.TaldeK AND " +
	                        "GIRA.TaldeK=LEKUAN_JO.TaldeK AND GIRA.hasData=LEKUAN_JO.hasData AND"+
                    		"LEKUA.Herrialdea=LEKUAN_JO.Herrialdea AND LEKUA.Izena=LEKUAN_JO.Izena AND"+
                    		"LEKUA.Hiria=LEKUAN_JO.Hiria AND"+
	                        "LEKUA.Izena=?");
	            ps.setString(1,taldeIzen);
	            ps.setString(2, leku);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	                System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
	            }
	            saiakera=3;
		    }catch(NumberFormatException e) {
				System.out.println("Zenbakia ez den zeozer sartu duzu, saiatu berriro: ");  		
			}catch(SQLException e) {
				System.out.println("ERRORE BAT SUERTATU DA DATU BASEAREKIN");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			}
			saiakera++;
		}while(saiakera<3);
	}

    private void milaBainoGehiago(){
    	
    }
}

