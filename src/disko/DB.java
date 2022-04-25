package disko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class DB {
    private BufferedReader br;
    private Connection konexioa;

    public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
        DB db = new DB();
        db.menuErakutsi();
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
        	System.out.println("Errorea egon da datu basea kargatzean.");
            e.printStackTrace();
        }
    }

    private void menuErakutsi() throws NumberFormatException, IOException, SQLException{
        int aukera = -1;
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
    }

    private void aukeraIrakurri(int aukera) throws IOException,SQLException{
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
                hiriakKendu();
                break;
            case 9:
                taldearenGirak();
                break;
            case 10:
                girarenHiriak();
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
                giraHiriakOrdenatuta();
                break;
            case 18:
                hiriBatekoIrabaziak();
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
    }

    /**
     * Talde berri bat sartuko da datu-basean. Talde hori sartzen denean, hainbat partaide sartuko ahal dira talde horren barruan.
     * @throws SQLException
     * @throws IOException
     * @throws NumberFormatException
     */
    private void taldeakSartu() throws SQLException, IOException, NumberFormatException{
    	//Taldea sartzeko:
    	System.out.println("Sartu taldearen izena: ");
    	String izena= br.readLine();
		System.out.println("Sartu taldearen kodea: ");
		int kode=Integer.parseInt(br.readLine());
		System.out.println("Sartu produktorearen kodea:");
		int pKode=Integer.parseInt(br.readLine());
		PreparedStatement ps = konexioa.prepareStatement("INSERT INTO TALDE VALUES(?, ?, ?)");
    	ps.setString(1,izena);
    	ps.setInt(2,kode);
    	ps.setInt(3, pKode);
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
    }

    private void diskoBerriaSartu(){

    }

    /**
     * Talde bati gira berri bat sartuko zaio. Behin gira hori sartu dela, gira horren barruan hiriak eta lekuak sartu nahi diren eskatuko da.
     * Hiri eta leku berriak sartu nahi badira, "girarenHiriakSartu" metodoari dei egingo zaio.
     * @throws SQLException 
     * @throws IOException
     */
    private void giraBerriakSartu() throws SQLException, IOException{
    	System.out.println("Sartu gira berriaren hasiera data (UUUU-HH-EE formatuan): ");
    	String hasData = br.readLine();
    	System.out.println("Sartu gira berriaren bukaera data (UUUU-HH-EE formatuan): ");
    	String bukData = br.readLine();
    	System.out.println("Sartu gira horren taldea: ");
    	String taldeKode = br.readLine();
    	PreparedStatement ps = konexioa.prepareStatement("INSERT INTO GIRA VALUES(?, ?, ?)");
    	ps.setString(1,bukData);
    	ps.setString(2,hasData);
    	ps.setString(3,taldeKode);
    	ps.executeUpdate();
    	System.out.println("Sartu berri duzun gira horretan hiri eta leku berriak sartu nahi dituzu? (B/E)");
    	String erantzun = br.readLine();
    	if(erantzun.equalsIgnoreCase("B")) girarenHiriakSartu();
    }

    /**
     * 
     * @throws SQLException
     * @throws IOException
     * @throws NumberFormatException
     */
    private void girarenHiriakSartu() throws SQLException, IOException, NumberFormatException{
    	boolean jarraitu=true;
    	boolean lekuGehiago=true;
		String herrialde, hiriIzen, hizki, lekua;
		Float prezioa;
		PreparedStatement ps, ps2;
		
		//Hiria eta gira konektatzeko:
		System.out.println("Sartu jotzen duen taldearen kodea: ");
    	int taldeK= Integer.parseInt(br.readLine());
    	System.out.println("Sartu gira berriaren hasiera data (UUUU-HH-EE formatuan): ");
    	String hasData = br.readLine();
		System.out.println("Sartu herrialdearen izena: ");
    	herrialde= br.readLine();
    	System.out.println("Sartu hiriaren izena: ");
    	hiriIzen= br.readLine();
		//AL METER LA DATA HAY Q PONER SETSTRING O SETDATE
    	
		do {
			System.out.println("Sartu herrialdearen izena: ");
		    herrialde= br.readLine();
		    System.out.println("Sartu hiriaren izena: ");
		    hiriIzen= br.readLine();
		    ps2 = konexioa.prepareStatement("INSERT INTO HIRIAN_JO VALUES(?, ?, ?, ?)");
			ps = konexioa.prepareStatement("INSERT INTO HIRIA VALUES(?, ?)");
			//Hiria sortzeko:
	    	ps.setString(1,herrialde);
	    	ps.setString(2,hiriIzen);
	    	ps.executeUpdate();
	    	//Hiria eta gira konektatzeko:
	    	ps2.setString(1,herrialde);
	    	ps2.setString(2,hiriIzen);
	    	ps2.setString(3, hasData);
	    	ps2.setInt(4, taldeK);
	    	ps2.executeUpdate();
	    	//Hirian lekuak gehitzeko:
	    	do{
	    		System.out.println("Sartu lekuaren izena: ");
	        	lekua= br.readLine();
	    		System.out.println("Sartu sarreren prezioa: ");
	        	prezioa= Float.valueOf(br.readLine());
	    		ps = konexioa.prepareStatement("INSERT INTO LEKUA VALUES(?, 0, ?, ?, ?)");
		    	ps.setString(1, lekua);
		    	ps.setFloat(2,prezioa);
		    	ps.setString(3,herrialde);
		    	ps.setString(4,hiriIzen);
		    	ps.executeUpdate();
	    		System.out.println("Leku gehiago EZ sartzeko sakatu 'G', bestela beste tekla sakatu.");
				hizki=br.readLine();
				if(hizki.equalsIgnoreCase("G")) {lekuGehiago=false;}
	    	}while(lekuGehiago);
	    	lekuGehiago=true;
			System.out.println("Gelditzeko sakatu 'G', bestela beste tekla sakatu.");
			hizki=br.readLine();
			if(hizki.equalsIgnoreCase("G")) {jarraitu=false;}
		}while(jarraitu);
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
    	
    }

    /**
     * Talde baten kodea sartuta, talde hori datu basetik aterako da. Dependentzia guztiak "ON DELETE CASCADE" jarrita daudenez, hauek ere ezabatuko dira.
     * @throws IOException
     * @throws SQLException
     */
    private void taldeKaleratu() throws IOException,SQLException{
    	System.out.println("Sartu kaleratu nahi duzun taldearen kodea: ");
    	String taldeIzen = br.readLine();
    	PreparedStatement ps = konexioa.prepareStatement("DELETE FROM TALDE WHERE Kodea = ?");
    	ps.setString(1, taldeIzen);
    	ps.executeUpdate();
    }

    /**
     * Erabiltzaileari datu basetik girako bateko zenbait hiri ezabatzeko aukera emango zaio.
     * @throws IOException
     * @throws SQLException
     */
    private void hiriakKendu() throws IOException,SQLException{
    	String erantzuna;
    	do {
    		System.out.println("Sartu taldearen identifikazio kodea: ");
    		String taldeKode = br.readLine();
    		System.out.println("Sartu " + taldeKode + " taldearen giraren hasiera-data: ");
    		String hasData = br.readLine();
        	System.out.println("Sartu kendu nahi duzun hiriaren izena: ");
        	String hiriIzen = br.readLine();
        	System.out.println("Sartu kendu nahi duzun hiriaren herrialdea: ");
        	String hiriHerrialde = br.readLine();
        	PreparedStatement ps = konexioa.prepareStatement("DELETE FROM Hirian_Jo WHERE HiriIzena = ? AND "
        																			+ "Herrialdea = ? AND "
        																			+ "HasData = ? AND "
        																			+ "TaldeK = ?");
        	ps.setString(1,hiriIzen);
        	ps.setString(2, hiriHerrialde);
        	ps.setString(3, hasData);
        	ps.setString(4, taldeKode);
        	ps.executeUpdate();
        	System.out.println("Hiri gehiago ezabatu nahi dituzu? (B/E)");
        	erantzuna = br.readLine();
    	}while(erantzuna.equalsIgnoreCase("E"));
    }

    private void taldearenGirak(){

    }

    /**
     * Gira baten hiri guztiak hauek duten sarreren prezioarekin.
     * Horretatrako,
     * @throws IOException
     * @throws SQLException
     */
    private void girarenHiriak() throws SQLException, IOException{		
    	System.out.println("Sartu taldearen izena:");
        String taldeIzen = br.readLine();
        System.out.println("Sartu zein den "+taldeIzen+" taldetik ikusi nahi duzun giraren hasiera data (UUUU-HH-EE formatuan): ");
		String data=br.readLine();
        PreparedStatement ps = konexioa.prepareStatement(
                "SELECT HIRIA.Izena, LEKUA.Izena, LEKUA.Prezioa " +
                "FROM HIRIA, LEKUA, TALDE, GIRA, HIRIAN_JO" +
                "WHERE TALDE.kodea = GIRA.TaldeK AND " +
                		"GIRA.TaldeK=HIRIAN_JO.TaldeK AND GIRA.hasData=HIRIAN_JO.hasData AND"+
                		"HIRIA.Herrialdea=HIRIAN_JO.Herrialdea AND HIRIA.Izena=HIRIAN_JO.HiriIzena AND"+
                		"HIRIA.Herrialdea=LEKUA.Herrialdea AND HIRIA.Izena=LEKUA.HiriIzena AND"+
                        "GIRA.hasData = ? AND " +
                        "TALDE.Izena = ?");
        ps.setString(1,data);
        ps.setString(2,taldeIzen);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
    }


    /**
     * Disko baten abesti guztiak erakutsiko dira.
     * Horretatrako,
     * @throws IOException
     * @throws SQLException
     */
    private void diskoarenAbestiak() throws IOException,SQLException{
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

    }

    /**
     * Talde batek gira batean lortuako irabaziak adieraziko dira.
     * Horretarako, taldearen izena eta gira adierziko dira.
     * @throws IOException
     * @throws SQLException
     */
    private void giraIrabaziak() throws IOException,SQLException{
        System.out.println("Sartu talde baten izena: ");
        String taldeIzen = br.readLine();
        System.out.println("Sartu girak identifikatzen duen hasiera data: ");
        String giraData = br.readLine();
        PreparedStatement ps = konexioa.prepareStatement(
                "SELECT SUM(LEKUA.SaldutakoSarrerak * LEKUA.Prezioa) " +
                "FROM TALDE, LEKUA, HIRIAN_JO " +
                "WHERE TALDE.izena = ? and " +
                    "TALDE.TaldeK = HIRIAN_JO.TaldeK and " +
                    "HIRIAN_JO.HasData = ? and " +
                    "LEKUA.Herrialdea = HIRIAN_JO.herrialdea and " +
                    "LEKUA.HiriIzen = HIRIAN_JO.HiriIzen");
        ps.setString(1,taldeIzen);
        ps.setString(2,giraData);
        ResultSet rs = ps.executeQuery();
        rs.next();
        System.out.println(rs.getString(1));
    }

    private void diskoIrabaziak(){

    }

    private void diskoAbestiakOrdenatu(){

    }

    private void giraHiriakOrdenatuta()throws SQLException, IOException{	
    	//TODO No seria asi el select?
    	System.out.println("Sartu taldearen izena:");
        String taldeIzen = br.readLine();
        System.out.println("Sartu zein den "+taldeIzen+" taldetik ikusi nahi duzun giraren hasiera data (UUUU-HH-EE formatuan): ");
		String data=br.readLine();
        PreparedStatement ps = konexioa.prepareStatement(
                "SELECT HIRIA.Izena, LEKUA.Izena" +
                "FROM HIRIA, LEKUA, TALDE, GIRA, HIRIAN_JO" +
                "WHERE TALDE.kodea = GIRA.TaldeK AND " +
                		"GIRA.TaldeK=HIRIAN_JO.TaldeK AND GIRA.hasData=HIRIAN_JO.hasData AND"+
                		"HIRIA.Herrialdea=HIRIAN_JO.Herrialdea AND HIRIA.Izena=HIRIAN_JO.HiriIzena AND"+
                		"HIRIA.Herrialdea=LEKUA.Herrialdea AND HIRIA.Izena=LEKUA.HiriIzena AND"+
                        "GIRA.hasData = ? AND " +
                        "TALDE.Izena = ?"+
                        "ORDER BY HIRIA.Izena");
        ps.setString(1,data);
        ps.setString(2,taldeIzen);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1) + " " + rs.getString(2));
        }

    }

    /**
     * Talde batek hiri baten iriabazitako dirua adierziko da.
     * Horretarako, taldearen izena eta hiriaren izena eskatuko dira.
     * @throws IOException
     * @throws SQLException
     */
    private void hiriBatekoIrabaziak() throws IOException,SQLException{
        System.out.println("Sartu talde baten izena: ");
        String taldeIzen = br.readLine();
        System.out.println("Sartu hiri baten izena: ");
        String hiriIzen = br.readLine();
        PreparedStatement ps = konexioa.prepareStatement(
                "SELECT HIRIA.Izena, COUNT(*), SUM(Prezioa*SaldutakoSarrerak) " +
                "FROM HIRIA, LEKUA, TALDE, GIRA " +
                "WHERE TALDE.Izena=? AND " +
                    "TALDE.Kodea=GIRA.TaldeK AND " +
                    "HIRIA.Izena=? AND " +
                    "HIRIA.Izena=LEKUA.HiriIzena AND " +
                    "HIRIA.Herrialdea=LEKUA.Herrialdea");
        ps.setString(1,taldeIzen);
        ps.setString(2,hiriIzen);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
        }
    }

    private void milaBainoGehiago(){

    }
}
