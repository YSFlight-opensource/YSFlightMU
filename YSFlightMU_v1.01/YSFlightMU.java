import java.awt.*;
import java.io.*;
import java.util.Random;

public class YSFlightMU extends Frame
{
	//Variable Creation
	//Global Varialbes
	private int i_xScreen = 500;
	private int i_yScreen = 500;

	private String version = "ysflight";

	//Buttons
	private Button b_IFF1, b_IFF2, b_Player, b_Quit;
	private Choice c_VersionSelect;
	private Choice c_MapSelect;
	private Choice c_DayNightSelect;

	private Checkbox cb_AllowGUN, cb_AllowAAM, cb_AllowAGM, cb_AllowRKT, cb_AllowBOM;
	private Button b_Generate;
	

	//IFF1GUIElements
	private Choice c_IFF1Aircraft, c_IFF1StartPos, c_IFF1Formation;
	private List li_IFF1Category;
	private Checkbox cb_IFF1Random, cb_IFF1IncludePlayer;
	private Button b_IFF1Restrict;

	private TextField tf_IFF1PosE, tf_IFF1PosH, tf_IFF1PosN;
	private TextField tf_IFF1OrZ, tf_IFF1OrX, tf_IFF1OrY;
	private TextField tf_IFF1InitSped;
	private TextField tf_IFF1ControlThrottle;
	private Checkbox cb_IFF1ControlGear;

	private Label l_IFF1Pos, l_IFF1Or, l_IFF1Sped, l_IFF1Throt;
	private Button b_IFF1ReloadStartPos;

	private Choice c_IFF1ComputerLevel;

	//IFF1GuiArray
	private TextField tf_IFF1ArrayEastNum, tf_IFF1ArrayHeightNum, tf_IFF1ArrayNorthNum;
	private TextField tf_IFF1ArrayEastSpace, tf_IFF1ArrayHeightSpace, tf_IFF1ArrayNorthSpace;
	private Label l_IFF1ArrayNum, l_IFF1ArraySpace;

	//IFF1GuiRandom
	private TextField tf_IFF1RandomEastSize, tf_IFF1RandomHeightSize, tf_IFF1RandomNorthSize;
	private TextField tf_IFF1RandomAmmountAircraft;
	private Label l_IFF1RandomSize, l_IFF1RandomAmount;

	//IFF2GUIElements
	private Choice c_IFF2Aircraft, c_IFF2StartPos, c_IFF2Formation;
	private List li_IFF2Category;
	private Checkbox cb_IFF2Random, cb_IFF2IncludePlayer;
	private Button b_IFF2Restrict;

	private TextField tf_IFF2PosE, tf_IFF2PosH, tf_IFF2PosN;
	private TextField tf_IFF2OrZ, tf_IFF2OrX, tf_IFF2OrY;
	private TextField tf_IFF2InitSped;
	private TextField tf_IFF2ControlThrottle;
	private Checkbox cb_IFF2ControlGear;

	private Label l_IFF2Pos, l_IFF2Or, l_IFF2Sped, l_IFF2Throt;
	private Button b_IFF2ReloadStartPos;

	private Choice c_IFF2ComputerLevel;

	//IFF2GuiArray
	private TextField tf_IFF2ArrayEastNum, tf_IFF2ArrayHeightNum, tf_IFF2ArrayNorthNum;
	private TextField tf_IFF2ArrayEastSpace, tf_IFF2ArrayHeightSpace, tf_IFF2ArrayNorthSpace;
	private Label l_IFF2ArrayNum, l_IFF2ArraySpace;

	//IFF2GuiRandom
	private TextField tf_IFF2RandomEastSize, tf_IFF2RandomHeightSize, tf_IFF2RandomNorthSize;
	private TextField tf_IFF2RandomAmmountAircraft;
	private Label l_IFF2RandomSize, l_IFF2RandomAmount;

	//PlayerGUIElements
	private Choice c_PlayerAircraft, c_PlayerStartPos;
	private List li_PlayerCategory;
	private Checkbox cb_PlayerRandom, cb_PlayerIncludePlayer;
	private Button b_PlayerRestrict;

	private TextField tf_PlayerPosE, tf_PlayerPosH, tf_PlayerPosN;
	private TextField tf_PlayerOrZ, tf_PlayerOrX, tf_PlayerOrY;
	private TextField tf_PlayerInitSped;
	private TextField tf_PlayerControlThrottle;
	private Checkbox cb_PlayerControlGear;

	private Label l_PlayerPos, l_PlayerOr, l_PlayerSped, l_PlayerThrot;
	private Button b_PlayerReloadStartPos;
	private Choice c_PlayerIFF;
	private Checkbox cb_PlayerEscort;

	//PlayerGuiArray
	private TextField tf_PlayerArrayEastNum, tf_PlayerArrayHeightNum, tf_PlayerArrayNorthNum;
	private TextField tf_PlayerArrayEastSpace, tf_PlayerArrayHeightSpace, tf_PlayerArrayNorthSpace;
	private Label l_PlayerArrayNum, l_PlayerArraySpace;

	//PlayerGuiRandom
	private TextField tf_PlayerRandomEastSize, tf_PlayerRandomHeightSize, tf_PlayerRandomNorthSize;
	private TextField tf_PlayerRandomAmmountAircraft;
	private Label l_PlayerRandomSize, l_PlayerRandomAmount;

	//Global Var

	private String aircraftList[] = new String[10000];
	private String aircraftTypeList[] = new String[10000];

	private StartMap mapList[] = new StartMap[100];
	private StartPos stpList[] = new StartPos[1000];

	private String restrictIFF1List[] = new String[20];
	private String restrictIFF2List[] = new String[20];
	private String restrictPlayerList[] = new String[20];

	private int maxNumAir = 0;
	private int i_iffSelect = 1;

	PrintWriter runTimeData;
	PrintWriter outputFile;

	public YSFlightMU()
	{
		super("YSFlight Mission Utility");

		setLayout(null);

		try
		{
			runTimeData = new PrintWriter("out.txt");
			runTimeData.write("START\n");
		}
		catch (Exception eff)
		{

		}

		//Button Creation
		b_IFF1 = new Button("IFF1");
		b_IFF2 = new Button("IFF2");
		b_Player = new Button("Player");
		b_Quit = new Button("Quit");
		c_VersionSelect = new Choice();
		c_MapSelect = new Choice();
		c_DayNightSelect = new Choice();
		c_DayNightSelect.addItem("DAY");
		c_DayNightSelect.addItem("NIGHT");

		cb_AllowGUN = new Checkbox("Allow GUN");
		cb_AllowAAM = new Checkbox("Allow AAM");
		cb_AllowAGM = new Checkbox("Allow AGM");
		cb_AllowRKT = new Checkbox("Allow RKT");
		cb_AllowBOM = new Checkbox("Allow BOM");

		cb_AllowGUN.setState(true);
		cb_AllowAAM.setState(true);
		cb_AllowAGM.setState(true);
		cb_AllowRKT.setState(true);
		cb_AllowBOM.setState(true);

		b_Generate = new Button("Generate");

		//Locate Versions
		File locater = new File("ysflight/");
		String dir[] = locater.list();


		for (int i = 0; i < dir.length; i++)
		{
			String temp = dir[i];
			File temp_loc = new File("ysflight/" + temp + "/aircraft");
			if (temp_loc.exists())
			{
				c_VersionSelect.addItem(temp);
			}
		}


		//IFF1 Creation
		c_IFF1Aircraft =  new Choice();
		c_IFF1StartPos = new Choice();

		c_IFF1Formation = new Choice();
		c_IFF1Formation.addItem("Array");
		c_IFF1Formation.addItem("Random");
		
		li_IFF1Category = new List(5, true);
		li_IFF1Category.addItem("NONE");
		li_IFF1Category.addItem("NORMAL");
		li_IFF1Category.addItem("UTILITY");
		li_IFF1Category.addItem("CATEGORY");
		li_IFF1Category.addItem("FIGHTER");
		li_IFF1Category.addItem("ATTACKER");
		li_IFF1Category.addItem("TRAINER");
		li_IFF1Category.addItem("BOMBER");
		li_IFF1Category.addItem("HEAVYBOMBER");
		li_IFF1Category.addItem("WW2FIGHTER");
		li_IFF1Category.addItem("WW2ATTACKER");
		li_IFF1Category.addItem("WW2BOMBER");
		li_IFF1Category.addItem("WW2DIVEBOMBER");

		cb_IFF1Random = new Checkbox("Random");
		cb_IFF1IncludePlayer = new Checkbox("Include Player");

		b_IFF1Restrict = new Button("Restrict");

		tf_IFF1PosE = new TextField("0");
		tf_IFF1PosH = new TextField("0");
		tf_IFF1PosN = new TextField("0");

		tf_IFF1OrZ = new TextField("0");
		tf_IFF1OrX = new TextField("0");
		tf_IFF1OrY = new TextField("0");

		tf_IFF1InitSped = new TextField("0");
		tf_IFF1ControlThrottle = new TextField("0");

		cb_IFF1ControlGear = new Checkbox("Gear Engaged");

		l_IFF1Pos = new Label("Pos (E, H, N)");
		l_IFF1Or = new Label("Or (Z, X, Y)");
		l_IFF1Sped = new Label("Initial Speed");
		l_IFF1Throt = new Label("Initial Throttle");

		b_IFF1ReloadStartPos = new Button("Reload");

		c_IFF1ComputerLevel = new Choice();
		c_IFF1ComputerLevel.addItem("NO DOGFIGHT");
		c_IFF1ComputerLevel.addItem("NOVICE (G3)");
		c_IFF1ComputerLevel.addItem("VETERAN (G5)");
		c_IFF1ComputerLevel.addItem("ACE (G7)");
		c_IFF1ComputerLevel.addItem("DEADLY (G9)");
		c_IFF1ComputerLevel.addItem("LETHAL (G11)");

		tf_IFF1ArrayEastNum = new TextField("0");
		tf_IFF1ArrayHeightNum = new TextField("0");
		tf_IFF1ArrayNorthNum = new TextField("0");
		tf_IFF1ArrayEastSpace = new TextField("100");
		tf_IFF1ArrayHeightSpace = new TextField("100");
		tf_IFF1ArrayNorthSpace = new TextField("100");

		tf_IFF1RandomEastSize = new TextField("0");
		tf_IFF1RandomHeightSize = new TextField("0");
		tf_IFF1RandomNorthSize = new TextField("0");
		tf_IFF1RandomAmmountAircraft = new TextField("0");

		l_IFF1ArrayNum = new Label("No. Aircraft (E, H, N)");
		l_IFF1ArraySpace = new Label("Spacing (E, H, N)");

		l_IFF1RandomSize = new Label("Box Size (E, H, N)");
		l_IFF1RandomAmount = new Label("No. Aircraft");

		//IFF2 Creation
		c_IFF2Aircraft =  new Choice();
		c_IFF2StartPos = new Choice();

		c_IFF2Formation = new Choice();
		c_IFF2Formation.addItem("Array");
		c_IFF2Formation.addItem("Random");
		
		li_IFF2Category = new List(5, true);
		li_IFF2Category.addItem("NONE");
		li_IFF2Category.addItem("NORMAL");
		li_IFF2Category.addItem("UTILITY");
		li_IFF2Category.addItem("CATEGORY");
		li_IFF2Category.addItem("FIGHTER");
		li_IFF2Category.addItem("ATTACKER");
		li_IFF2Category.addItem("TRAINER");
		li_IFF2Category.addItem("BOMBER");
		li_IFF2Category.addItem("HEAVYBOMBER");
		li_IFF2Category.addItem("WW2FIGHTER");
		li_IFF2Category.addItem("WW2ATTACKER");
		li_IFF2Category.addItem("WW2BOMBER");
		li_IFF2Category.addItem("WW2DIVEBOMBER");

		cb_IFF2Random = new Checkbox("Random");
		cb_IFF2IncludePlayer = new Checkbox("Include Player");

		b_IFF2Restrict = new Button("Restrict");

		tf_IFF2PosE = new TextField("0");
		tf_IFF2PosH = new TextField("0");
		tf_IFF2PosN = new TextField("0");

		tf_IFF2OrZ = new TextField("0");
		tf_IFF2OrX = new TextField("0");
		tf_IFF2OrY = new TextField("0");

		tf_IFF2InitSped = new TextField("0");
		tf_IFF2ControlThrottle = new TextField("0");

		cb_IFF2ControlGear = new Checkbox("Gear Engaged");

		l_IFF2Pos = new Label("Pos (E, H, N)");
		l_IFF2Or = new Label("Or (Z, X, Y)");
		l_IFF2Sped = new Label("Initial Speed");
		l_IFF2Throt = new Label("Initial Throttle");

		b_IFF2ReloadStartPos = new Button("Reload");

		c_IFF2ComputerLevel = new Choice();
		c_IFF2ComputerLevel.addItem("NO DOGFIGHT");
		c_IFF2ComputerLevel.addItem("NOVICE (G3)");
		c_IFF2ComputerLevel.addItem("VETERAN (G5)");
		c_IFF2ComputerLevel.addItem("ACE (G7)");
		c_IFF2ComputerLevel.addItem("DEADLY (G9)");
		c_IFF2ComputerLevel.addItem("LETHAL (G11)");

		tf_IFF2ArrayEastNum = new TextField("0");
		tf_IFF2ArrayHeightNum = new TextField("0");
		tf_IFF2ArrayNorthNum = new TextField("0");
		tf_IFF2ArrayEastSpace = new TextField("100");
		tf_IFF2ArrayHeightSpace = new TextField("100");
		tf_IFF2ArrayNorthSpace = new TextField("100");

		tf_IFF2RandomEastSize = new TextField("0");
		tf_IFF2RandomHeightSize = new TextField("0");
		tf_IFF2RandomNorthSize = new TextField("0");
		tf_IFF2RandomAmmountAircraft = new TextField("0");

		l_IFF2ArrayNum = new Label("No. Aircraft (E, H, N)");
		l_IFF2ArraySpace = new Label("Spacing (E, H, N)");

		l_IFF2RandomSize = new Label("Box Size (E, H, N)");
		l_IFF2RandomAmount = new Label("No. Aircraft");

		//Player Creation
		c_PlayerAircraft = new Choice();
		c_PlayerStartPos = new Choice();

		li_PlayerCategory = new List(5, true);
		li_PlayerCategory.addItem("NONE");
		li_PlayerCategory.addItem("NORMAL");
		li_PlayerCategory.addItem("UTILITY");
		li_PlayerCategory.addItem("CATEGORY");
		li_PlayerCategory.addItem("FIGHTER");
		li_PlayerCategory.addItem("ATTACKER");
		li_PlayerCategory.addItem("TRAINER");
		li_PlayerCategory.addItem("BOMBER");
		li_PlayerCategory.addItem("HEAVYBOMBER");
		li_PlayerCategory.addItem("WW2FIGHTER");
		li_PlayerCategory.addItem("WW2ATTACKER");
		li_PlayerCategory.addItem("WW2BOMBER");
		li_PlayerCategory.addItem("WW2DIVEBOMBER");

		cb_PlayerRandom = new Checkbox("Random");
		cb_PlayerIncludePlayer = new Checkbox("Include Player");
		cb_PlayerIncludePlayer.setState(true);

		b_PlayerRestrict = new Button("Restrict");

		tf_PlayerPosE = new TextField("0");
		tf_PlayerPosH = new TextField("0");
		tf_PlayerPosN = new TextField("0");

		tf_PlayerOrZ = new TextField("0");
		tf_PlayerOrX = new TextField("0");
		tf_PlayerOrY = new TextField("0");

		tf_PlayerInitSped = new TextField("0");
		tf_PlayerControlThrottle = new TextField("0");

		cb_PlayerControlGear = new Checkbox("Gear Engaged");

		l_PlayerPos = new Label("Pos (E, H, N)");
		l_PlayerOr = new Label("Or (Z, X, Y)");
		l_PlayerSped = new Label("Initial Speed");
		l_PlayerThrot = new Label("Initial Throttle");

		b_PlayerReloadStartPos = new Button("Reload");
		c_PlayerIFF = new Choice();
		c_PlayerIFF.addItem("1");
		c_PlayerIFF.addItem("2");
		c_PlayerIFF.addItem("3");
		c_PlayerIFF.addItem("4");

		cb_PlayerEscort = new Checkbox("Escort");

		//Button Adding
		add(b_IFF1);
		add(b_IFF2);
		add(b_Player);
		add(b_Quit);
		add(c_VersionSelect);
		add(c_MapSelect);
		add(c_DayNightSelect);
		add(cb_AllowGUN);
		add(cb_AllowAAM);
		add(cb_AllowAGM);
		add(cb_AllowRKT);
		add(cb_AllowBOM);
		add(b_Generate);

		//IFF1 Adding
		add(c_IFF1Aircraft);
		add(c_IFF1StartPos);
		add(c_IFF1Formation);
		add(li_IFF1Category);
		add(cb_IFF1Random);
		add(cb_IFF1IncludePlayer);
		add(b_IFF1Restrict);
		add(tf_IFF1PosE);
		add(tf_IFF1PosH);
		add(tf_IFF1PosN);
		add(tf_IFF1OrZ);
		add(tf_IFF1OrX);
		add(tf_IFF1OrY);
		add(tf_IFF1InitSped);
		add(tf_IFF1ControlThrottle);
		add(cb_IFF1ControlGear);
		add(l_IFF1Pos);
		add(l_IFF1Or);
		add(l_IFF1Sped);
		add(l_IFF1Throt);
		add(b_IFF1ReloadStartPos);
		add(c_IFF1ComputerLevel);

		add(tf_IFF1ArrayEastNum);
		add(tf_IFF1ArrayHeightNum);
		add(tf_IFF1ArrayNorthNum);
		add(tf_IFF1ArrayEastSpace);
		add(tf_IFF1ArrayHeightSpace);
		add(tf_IFF1ArrayNorthSpace);

		add(tf_IFF1RandomEastSize);
		add(tf_IFF1RandomHeightSize);
		add(tf_IFF1RandomNorthSize);
		add(tf_IFF1RandomAmmountAircraft);

		add(l_IFF1ArrayNum);
		add(l_IFF1ArraySpace);

		add(l_IFF1RandomSize);
		add(l_IFF1RandomAmount);

		//IFF2 Adding
		add(c_IFF2Aircraft);
		add(c_IFF2StartPos);
		add(c_IFF2Formation);
		add(li_IFF2Category);
		add(cb_IFF2Random);
		add(cb_IFF2IncludePlayer);
		add(b_IFF2Restrict);
		add(tf_IFF2PosE);
		add(tf_IFF2PosH);
		add(tf_IFF2PosN);
		add(tf_IFF2OrZ);
		add(tf_IFF2OrX);
		add(tf_IFF2OrY);
		add(tf_IFF2InitSped);
		add(tf_IFF2ControlThrottle);
		add(cb_IFF2ControlGear);
		add(l_IFF2Pos);
		add(l_IFF2Or);
		add(l_IFF2Sped);
		add(l_IFF2Throt);
		add(b_IFF2ReloadStartPos);
		add(c_IFF2ComputerLevel);

		add(tf_IFF2ArrayEastNum);
		add(tf_IFF2ArrayHeightNum);
		add(tf_IFF2ArrayNorthNum);
		add(tf_IFF2ArrayEastSpace);
		add(tf_IFF2ArrayHeightSpace);
		add(tf_IFF2ArrayNorthSpace);

		add(tf_IFF2RandomEastSize);
		add(tf_IFF2RandomHeightSize);
		add(tf_IFF2RandomNorthSize);
		add(tf_IFF2RandomAmmountAircraft);

		add(l_IFF2ArrayNum);
		add(l_IFF2ArraySpace);

		add(l_IFF2RandomSize);
		add(l_IFF2RandomAmount);

		//Player Adding
		add(c_PlayerAircraft);
		add(c_PlayerStartPos);
		add(li_PlayerCategory);
		add(cb_PlayerRandom);
		add(cb_PlayerIncludePlayer);
		add(b_PlayerRestrict);
		add(tf_PlayerPosE);
		add(tf_PlayerPosH);
		add(tf_PlayerPosN);
		add(tf_PlayerOrZ);
		add(tf_PlayerOrX);
		add(tf_PlayerOrY);
		add(tf_PlayerInitSped);
		add(tf_PlayerControlThrottle);
		add(cb_PlayerControlGear);
		add(l_PlayerPos);
		add(l_PlayerOr);
		add(l_PlayerSped);
		add(l_PlayerThrot);
		add(b_PlayerReloadStartPos);
		add(c_PlayerIFF);
		add(cb_PlayerEscort);

		resize(500, 500);
		show();

		//Button Resizing
		b_IFF1.reshape(0, 22, 100, 20);
		b_IFF2.reshape(100, 22, 100, 20);
		b_Player.reshape(200, 22, 100, 20);
		b_Quit.reshape(i_xScreen - 100, 22, 100, 20);
		c_VersionSelect.reshape(300, 22, 100, 20);
		c_MapSelect.reshape(20, i_yScreen - 80, 150, 20);
		c_DayNightSelect.reshape(20, i_yScreen - 50, 150, 20);

		cb_AllowGUN.reshape(200, i_yScreen - 50, 100, 20);
		cb_AllowAAM.reshape(300, i_yScreen - 80, 100, 20);
		cb_AllowAGM.reshape(300, i_yScreen - 50, 100, 20);
		cb_AllowRKT.reshape(400, i_yScreen - 80, 100, 20);
		cb_AllowBOM.reshape(400, i_yScreen - 50, 100, 20);

		b_Generate.reshape(200, i_yScreen - 80, 100, 20);


		showIFF1();
		hideIFF2();
		hidePlayer();

		loadDetails();
		placeDetails(-1);

		showIFF1Array();

	}

	public void paint(Graphics g)
	{
		g.drawLine(0, 42, i_xScreen, 42);
		g.drawLine(0, i_yScreen - 100, i_xScreen, i_yScreen - 100);
	}

	public int linearSearch(String key, int type)
	{
		if (type == 0 && restrictPlayerList.length > 0)
		{
			for (int n = 0; n < restrictPlayerList.length; n++)
			{
				if (restrictPlayerList[n] != null)
				{
					if (restrictPlayerList[n].equals(key))
					{
						return n;
					}
				}
				
			}

			return -1;
		}

		if (type == 1 && restrictIFF1List.length > 0)
		{
			for (int n = 0; n < restrictIFF1List.length; n++)
			{
				if (restrictIFF1List[n] != null)
				{
					if (restrictIFF1List[n].equals(key))
					{
						return n;
					}
				}
			}

			return -1;
		}

		if (type == 2 && restrictIFF2List.length > 0)
		{
			for (int n = 0; n < restrictIFF2List.length; n++)
			{
				if (restrictIFF2List[n] != null)
				{
					if (restrictIFF2List[n].equals(key))
					{
						return n;
					}
				}
				
			}

			return -1;
		}

		return -1;
	}

	public void placeDetails(int type)
	{
		if (type == 0 || type == -1)
		{
			c_PlayerAircraft.removeAll();
			int temp_index = 0;
			try
			{
				while (aircraftList[temp_index].length() > 0)
				{
					if (linearSearch(aircraftTypeList[temp_index], 0) == -1)
					{
						c_PlayerAircraft.addItem(aircraftList[temp_index]);
					}
					//c_PlayerAircraft.addItem(aircraftList[temp_index] + " " + aircraftTypeList[temp_index]);
					temp_index += 1;
				}
			}
			catch (Exception e)
			{
				//YSFlightMU.classc_PlayerAircraft.addItem(e.toString() + " " + String.valueOf(temp_index) + " " + String.valueOf(maxNumAir) + " " + String.valueOf(aircraftList.length));
				//runTimeData.print(e.toString() + "\n");
			}
			
		}

		if (type == 1 || type == -1)
		{
			c_IFF1Aircraft.removeAll();
			int temp_index = 0;
			try
			{
				while (aircraftList[temp_index].length() > 0)
				{
					if (linearSearch(aircraftTypeList[temp_index], 1) == -1)
					{
						c_IFF1Aircraft.addItem(aircraftList[temp_index]);
					}
					temp_index += 1;
				}
			}
			catch (Exception e)
			{
				//runTimeData.print(e.toString() + "\n");
			}
			
		}

		if (type == 2 || type == -1)
		{
			c_IFF2Aircraft.removeAll();
			int temp_index = 0;
			try
			{
				while (aircraftList[temp_index].length() > 0)
				{
					if (linearSearch(aircraftTypeList[temp_index], 2) == -1)
					{
						c_IFF2Aircraft.addItem(aircraftList[temp_index]);
					}
					temp_index += 1;
				}
			}
			catch (Exception e)
			{
				//runTimeData.print(e.toString() + "\n");
			}
			
		}
	}

	public void loadDetails()
	{

		hideIFF1();
		hideIFF2();
		hidePlayer();

		c_MapSelect.removeAll();

		aircraftList = new String[10000];
		aircraftTypeList = new String[10000];
		mapList = new StartMap[100];
		stpList = new StartPos[1000];

		int temp_index = 0;

		String gameVersion = c_VersionSelect.getSelectedItem();

		String path = "ysflight/" + gameVersion;
		//LoadingAircraft

		File aircraftLoad = new File(path+ "/aircraft");
		String dir[] = aircraftLoad.list();
		maxNumAir = 0;
		for (int i = 0; i < dir.length; i++)
		{
			if (dir[i].startsWith("air") && dir[i].endsWith(".lst"))
			{
				try
				{
					RandomAccessFile airList = new RandomAccessFile(path + "/aircraft/" + dir[i], "r");
					do
					{
						String t = airList.readLine();
						if (t.indexOf(".dat") != -1)
						{
							// char tc[] = new char[t.indexOf(".dat") + 4];
							// t.getChars(0, t.indexOf(".dat"), tc, 0);
							// String t2 = new String(tc);
							String t2 = t.substring(0, t.indexOf(".dat") + 4);
							try
							{
								RandomAccessFile aircraftDat = new RandomAccessFile(path + "/" + t2, "r");
								do
								{
									String t3 = aircraftDat.readLine();

									if (t3.startsWith("IDENTIFY"))
									{
										String t4 = t3.substring(10, t3.length() - 1);
										aircraftList[temp_index] = t4;
										//c_IFF1Aircraft.addItem(t4);
										//c_IFF2Aircraft.addItem(t4);
										//c_PlayerAircraft.addItem(t4);
									}
									else if (t3.startsWith("CATEGORY"))
									{
										String t5 = t3.substring(9, t3.length());
										aircraftTypeList[temp_index] = t5;
										//c_PlayerAircraft.addItem(t5);
									}
								} while (aircraftDat.getFilePointer() < aircraftDat.length());
							}
							catch (IOException e)
							{
								//c_IFF1Aircraft.addItem("Pants");
								//runTimeData.print(e.toString() + "\n");
							}
							temp_index += 1;

						}

					} while (airList.getFilePointer() < airList.length());

				}
				catch (IOException e)
				{
					//c_IFF1Aircraft.addItem("Pants2");
					//runTimeData.print(e.toString() + "\n");
				}

				maxNumAir += temp_index;
				
			}
		}

		//Loading Maps
		File mapLoad = new File(path + "/scenery");
		String dir2[] = mapLoad.list();
		temp_index = 0;
		for (int i = 0; i < dir2.length; i++)
		{
			if (dir2[i].startsWith("sce") && dir2[i].endsWith(".lst"))
			{
				try
				{
					RandomAccessFile mapLoadList = new RandomAccessFile(path + "/scenery/" + dir2[i], "r");
					do
					{
						String t6 = mapLoadList.readLine();
						String t7 = t6.substring(0, t6.indexOf(" "));
						String t8 = t6.substring(t6.indexOf(".fld") + 5, t6.indexOf(".stp") + 4);
						String t9 = t6.substring(t6.indexOf(" ") + 1, t6.indexOf(".fld") + 4);
						c_MapSelect.addItem(t7);
						mapList[temp_index] = new StartMap(t7, t8, t9);
						temp_index += 1;

					} while (mapLoadList.getFilePointer() < mapLoadList.length());
				}
				catch (Exception e)
				{
					//runTimeData.print(e.toString() + "\n");	
				}
			}
		}

		if (i_iffSelect == 0)
		{
			showPlayer();
		}
		else if (i_iffSelect == 1)
		{
			showIFF1();
		}
		else if (i_iffSelect == 2)
		{
			showIFF2();
		}

		loadStartPos();
	}

	public void loadStartPos()
	{
		c_IFF1StartPos.removeAll();
		c_IFF2StartPos.removeAll();
		c_PlayerStartPos.removeAll();
		try
		{
			
			String gameVersion = c_VersionSelect.getSelectedItem();
			String path = "ysflight/" + gameVersion;
			String map = c_MapSelect.getSelectedItem();

			for (int i = 0; i < mapList.length; i++)
			{
				//c_IFF1StartPos.addItem("helloX" + String.valueOf(i));
				if (mapList[i] != null)
				{
					//c_IFF1StartPos.addItem(mapList[i].mapName);
				}
				else
				{
					//c_IFF1StartPos.addItem("null");
				}
				
				try
				{
					if (mapList[i].mapName.equals(map))
					{
						//c_IFF1StartPos.addItem("helloA" + String.valueOf(i));
						try
						{
							RandomAccessFile stpListFile = new RandomAccessFile(path + "/" + mapList[i].mapPath, "r");
							int stpListIndex = 0;
							String t_name = "ORIGIN";
							double t_pos_east = 0;
							double t_pos_height = 0;
							double t_pos_north = 0;
							double t_or_z = 0;
							double t_or_x = 0;
							double t_or_y = 0;
							double t_initSpeed = 0;
							double t_control_throttle = 0;
							boolean  t_control_gear = true;

							stpList[stpListIndex] = new StartPos(t_name, t_pos_east, t_pos_height, t_pos_north, t_or_z, t_or_x, t_or_y, t_initSpeed, t_control_throttle, t_control_gear);
							c_IFF1StartPos.addItem("ORIGIN");
							c_IFF2StartPos.addItem("ORIGIN");
							c_PlayerStartPos.addItem("ORIGIN");
							stpListIndex = 1;
							do
							{
								String t = stpListFile.readLine();

								if (t.startsWith("N "))
								{
									t_name = t.substring(t.indexOf("N ") + 2, t.length());
									c_IFF1StartPos.addItem(t_name);
									c_IFF2StartPos.addItem(t_name);
									c_PlayerStartPos.addItem(t_name);
								}
								else if (t.startsWith("C POSITION "))
								{
									t_pos_east = new Double(t.substring(11, t.indexOf("m", 12)));
									//c_IFF2StartPos.addItem("{" + t.substring(11, t.indexOf("m", 12)) + "}");
									String t_restPos = t.substring(t.indexOf("m") + 1, t.length());
									//c_IFF2StartPos.addItem(t_restPos);
									t_pos_height = new Double(t_restPos.substring(0, t_restPos.indexOf("m")));
									t_restPos = t_restPos.substring(t_restPos.indexOf("m") + 1, t_restPos.length());
									//c_IFF2StartPos.addItem(t_restPos);
									t_pos_north = new Double(t_restPos.substring(0, t_restPos.indexOf("m")));
									//c_IFF2StartPos.addItem("helloA" + String.valueOf(i));
								}
								else if (t.startsWith("C ATTITUDE "))
								{
									t_or_z = new Double(t.substring(11, t.indexOf("deg", 12)));
									////runTimeData.print(t.substring(11, t.indexOf("deg", 12)) + "\n");
									String t_restPos = t.substring(t.indexOf("deg") + 3, t.length());
									t_or_x = new Double(t_restPos.substring(0, t_restPos.indexOf("deg")));
									t_restPos = t_restPos.substring(t_restPos.indexOf("deg") + 3, t_restPos.length());
									t_or_y = new Double(t_restPos.substring(0, t_restPos.indexOf("deg")));
									//c_IFF2StartPos.addItem("helloB" + String.valueOf(i));
								}
								else if (t.startsWith("C INITSPED "))
								{
									t_initSpeed = new Double(t.substring(11, t.indexOf("m/s")));
									//c_IFF2StartPos.addItem("helloC" + String.valueOf(i));
								}
								else if (t.startsWith("C CTLTHROT "))
								{
									t_control_throttle = new Double(t.substring(11, t.length()));
									//c_IFF2StartPos.addItem("helloD" + String.valueOf(i));
								}
								else if (t.startsWith("C CTLLDGEA "))
								{
									String t_restPos = t.substring(11, t.length());
									if (t_restPos.equals("TRUE"))
									{
										t_control_gear = true;
									}
									else
									{
										t_control_gear = false;
									}
									//c_IFF2StartPos.addItem("helloE" + String.valueOf(i));
									stpList[stpListIndex] = new StartPos(t_name, t_pos_east, t_pos_height, t_pos_north, t_or_z, t_or_x, t_or_y, t_initSpeed, t_control_throttle, t_control_gear);
									stpListIndex += 1;
									//c_IFF2StartPos.addItem("helloF" + String.valueOf(i));
								}
							} while (stpListFile.getFilePointer() < stpListFile.length());
						}
						catch (Exception e)
						{
							//c_IFF2StartPos.addItem(effff.toString());
							//runTimeData.print(e.toString() + "\n");
						}
					}
				}
				catch (Exception e)
				{
					//c_IFF1StartPos.addItem("helloY" + String.valueOf(i));
					//runTimeData.print(e.toString() + "\n");
				}
				
			}
		}
		catch (Exception e)
		{
			//c_IFF1StartPos.addItem("hello2");
			//runTimeData.print(e.toString() + "\n");
		}

		placeStartDetails(-1);
	}
	public void placeStartDetails(int type)
	{
		try
		{
			if (type == 1 || type == -1)
			{
				String startPosSelect = c_IFF1StartPos.getSelectedItem();

				for (int i = 0; i < stpList.length; i++)
				{
					if (stpList[i] != null)
					{
						//tf_IFF1ControlThrottle.setText(String.valueOf(i));
						if (stpList[i].name.equals(startPosSelect))
						{
							tf_IFF1PosE.setText(String.valueOf(stpList[i].pos_east));
							tf_IFF1PosH.setText(String.valueOf(stpList[i].pos_height));
							tf_IFF1PosN.setText(String.valueOf(stpList[i].pos_north));

							tf_IFF1OrZ.setText(String.valueOf(stpList[i].or_z));
							tf_IFF1OrX.setText(String.valueOf(stpList[i].or_x));
							tf_IFF1OrY.setText(String.valueOf(stpList[i].or_y));

							tf_IFF1InitSped.setText(String.valueOf(stpList[i].initSpeed));
							tf_IFF1ControlThrottle.setText(String.valueOf(stpList[i].control_throttle));
							cb_IFF1ControlGear.setState(stpList[i].control_gear);
							break;
						}
					}
				}
			}

			if (type == 2 || type == -1)
			{
				String startPosSelect = c_IFF2StartPos.getSelectedItem();

				for (int i = 0; i < stpList.length; i++)
				{
					if (stpList[i] != null)
					{
						if (stpList[i].name.equals(startPosSelect))
						{
							tf_IFF2PosE.setText(String.valueOf(stpList[i].pos_east));
							tf_IFF2PosH.setText(String.valueOf(stpList[i].pos_height));
							tf_IFF2PosN.setText(String.valueOf(stpList[i].pos_north));

							tf_IFF2OrZ.setText(String.valueOf(stpList[i].or_z));
							tf_IFF2OrX.setText(String.valueOf(stpList[i].or_x));
							tf_IFF2OrY.setText(String.valueOf(stpList[i].or_y));

							tf_IFF2InitSped.setText(String.valueOf(stpList[i].initSpeed));
							tf_IFF2ControlThrottle.setText(String.valueOf(stpList[i].control_throttle));
							cb_IFF2ControlGear.setState(stpList[i].control_gear);
							break;
						}
					}
				}
			}

			if (type == 0 || type == -1) 
			{
				String startPosSelect = c_PlayerStartPos.getSelectedItem();

				for (int i = 0; i < stpList.length; i++)
				{
					if (stpList[i] != null)
					{
						////runTimeData.print(startPosSelect + "[]" + stpList[i].name + "[]" + String.valueOf(stpList[i].name.equals(startPosSelect)) + "[]" + String.valueOf(stpList[i].name == startPosSelect) + "[]" + String.valueOf(i) +  "\n");
						if (stpList[i].name.equals(startPosSelect))
						{
							////runTimeData.print(startPosSelect + "[]" + stpList[i].name + "[]" + String.valueOf(stpList[i].name.equals(startPosSelect)) + "\n");
							//tf_PlayerInitSped.setText("[" + startPosSelect + "[]" + stpList[i].name + "[]" + String.valueOf(stpList[i].pos_east) + "[]" + String.valueOf(i));
							tf_PlayerPosE.setText(String.valueOf(stpList[i].pos_east));
							tf_PlayerPosH.setText(String.valueOf(stpList[i].pos_height));
							tf_PlayerPosN.setText(String.valueOf(stpList[i].pos_north));

							tf_PlayerOrZ.setText(String.valueOf(stpList[i].or_z));
							tf_PlayerOrX.setText(String.valueOf(stpList[i].or_x));
							tf_PlayerOrY.setText(String.valueOf(stpList[i].or_y));

							tf_PlayerInitSped.setText(String.valueOf(stpList[i].initSpeed));
							tf_PlayerControlThrottle.setText(String.valueOf(stpList[i].control_throttle));
							cb_PlayerControlGear.setState(stpList[i].control_gear);
							break;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			//tf_IFF1InitSped.setText(e.toString());
			//runTimeData.print("placeStartDetails " + String.valueOf(type) + " " + e.toString() + "\n");
		}
	}

	public void showIFF1()
	{
		c_IFF1Aircraft.reshape(20, 60, 150, 20);
		c_IFF1StartPos.reshape(20, 100, 150, 20);
		c_IFF1Formation.reshape(20, 300, 150, 20);
		li_IFF1Category.reshape(330, 60, 150, 150);
		cb_IFF1Random.reshape(200, 60, 150, 20);
		cb_IFF1IncludePlayer.reshape(200, 90, 150, 20);
		b_IFF1Restrict.reshape(330, 220, 100, 20);

		tf_IFF1PosE.reshape(100, 150, 70, 20);
		tf_IFF1PosH.reshape(170, 150, 70, 20);
		tf_IFF1PosN.reshape(240, 150, 70, 20);

		tf_IFF1OrZ.reshape(100, 180, 70, 20);
		tf_IFF1OrX.reshape(170, 180, 70, 20);
		tf_IFF1OrY.reshape(240, 180, 70, 20);

		tf_IFF1InitSped.reshape(110, 210, 100, 20);
		tf_IFF1ControlThrottle.reshape(110, 240, 100, 20);

		cb_IFF1ControlGear.reshape(10, 270, 150, 20);

		l_IFF1Pos.reshape(10, 150, 100, 20);
		l_IFF1Or.reshape(10, 180, 100, 20);
		l_IFF1Sped.reshape(10, 210, 100, 20);
		l_IFF1Throt.reshape(10, 240, 100, 20);

		b_IFF1ReloadStartPos.reshape(210, 210, 100, 20);

		c_IFF1ComputerLevel.reshape(170, 120, 150, 20);
	}

	public void hideIFF1()
	{
		c_IFF1Aircraft.reshape(-1000, -1000, 150, 20);
		c_IFF1StartPos.reshape(-1000, -1000, 150, 20);
		c_IFF1Formation.reshape(-1000, -1000, 100, 20);
		li_IFF1Category.reshape(-1000, -1000, 150, 150);
		cb_IFF1Random.reshape(-1000, -1000, 150, 20);
		cb_IFF1IncludePlayer.reshape(-1000, -1000, 150, 20);
		b_IFF1Restrict.reshape(-1000, -1000, 100, 20);

		tf_IFF1PosE.reshape(-1000, -1000, 150, 20);
		tf_IFF1PosH.reshape(-1000, -1000, 150, 20);
		tf_IFF1PosN.reshape(-1000, -1000, 150, 20);

		tf_IFF1OrZ.reshape(-1000, -1000, 150, 20);
		tf_IFF1OrX.reshape(-1000, -1000, 150, 20);
		tf_IFF1OrY.reshape(-1000, -1000, 150, 20);

		tf_IFF1InitSped.reshape(-1000, -1000, 150, 20);
		tf_IFF1ControlThrottle.reshape(-1000, -1000, 150, 20);

		cb_IFF1ControlGear.reshape(-1000, -1000, 150, 20);

		l_IFF1Pos.reshape(-1000, -1000, 150, 20);
		l_IFF1Or.reshape(-1000, -1000, 150, 20);
		l_IFF1Sped.reshape(-1000, -1000, 150, 20);
		l_IFF1Throt.reshape(-1000, -1000, 150, 20);

		b_IFF1ReloadStartPos.reshape(-1000, -1000, 150, 20);

		c_IFF1ComputerLevel.reshape(-1000, -1000, 150, 20);
	}

	public void showIFF2()
	{
		c_IFF2Aircraft.reshape(20, 60, 150, 20);
		c_IFF2StartPos.reshape(20, 100, 150, 20);
		c_IFF2Formation.reshape(20, 300, 150, 20);
		li_IFF2Category.reshape(330, 60, 150, 150);
		cb_IFF2Random.reshape(200, 60, 150, 20);
		cb_IFF2IncludePlayer.reshape(200, 90, 150, 20);
		b_IFF2Restrict.reshape(330, 220, 100, 20);

		tf_IFF2PosE.reshape(100, 150, 70, 20);
		tf_IFF2PosH.reshape(170, 150, 70, 20);
		tf_IFF2PosN.reshape(240, 150, 70, 20);

		tf_IFF2OrZ.reshape(100, 180, 70, 20);
		tf_IFF2OrX.reshape(170, 180, 70, 20);
		tf_IFF2OrY.reshape(240, 180, 70, 20);

		tf_IFF2InitSped.reshape(110, 210, 100, 20);
		tf_IFF2ControlThrottle.reshape(110, 240, 100, 20);

		cb_IFF2ControlGear.reshape(10, 270, 150, 20);

		l_IFF2Pos.reshape(10, 150, 100, 20);
		l_IFF2Or.reshape(10, 180, 100, 20);
		l_IFF2Sped.reshape(10, 210, 100, 20);
		l_IFF2Throt.reshape(10, 240, 100, 20);

		b_IFF2ReloadStartPos.reshape(210, 210, 100, 20);

		c_IFF2ComputerLevel.reshape(170, 120, 150, 20);
	}

	public void hideIFF2()
	{
		c_IFF2Aircraft.reshape(-1000, -1000, 150, 20);
		c_IFF2StartPos.reshape(-1000, -1000, 150, 20);
		c_IFF2Formation.reshape(-1000, -1000, 100, 20);
		li_IFF2Category.reshape(-1000, -1000, 150, 150);
		cb_IFF2Random.reshape(-1000, -1000, 150, 20);
		cb_IFF2IncludePlayer.reshape(-1000, -1000, 150, 20);
		b_IFF2Restrict.reshape(-1000, -1000, 100, 20);

		tf_IFF2PosE.reshape(-1000, -1000, 150, 20);
		tf_IFF2PosH.reshape(-1000, -1000, 150, 20);
		tf_IFF2PosN.reshape(-1000, -1000, 150, 20);

		tf_IFF2OrZ.reshape(-1000, -1000, 150, 20);
		tf_IFF2OrX.reshape(-1000, -1000, 150, 20);
		tf_IFF2OrY.reshape(-1000, -1000, 150, 20);

		tf_IFF2InitSped.reshape(-1000, -1000, 150, 20);
		tf_IFF2ControlThrottle.reshape(-1000, -1000, 150, 20);

		cb_IFF2ControlGear.reshape(-1000, -1000, 150, 20);

		l_IFF2Pos.reshape(-1000, -1000, 150, 20);
		l_IFF2Or.reshape(-1000, -1000, 150, 20);
		l_IFF2Sped.reshape(-1000, -1000, 150, 20);
		l_IFF2Throt.reshape(-1000, -1000, 150, 20);

		b_IFF2ReloadStartPos.reshape(-1000, -1000, 150, 20);

		c_IFF2ComputerLevel.reshape(-1000, -1000, 150, 20);
	}

	public void showPlayer()
	{
		c_PlayerAircraft.reshape(20, 60, 150, 20);
		c_PlayerStartPos.reshape(20, 100, 150, 20);
		li_PlayerCategory.reshape(330, 60, 150, 150);
		cb_PlayerRandom.reshape(200, 60, 150, 20);
		cb_PlayerIncludePlayer.reshape(200, 90, 150, 20);
		b_PlayerRestrict.reshape(330, 220, 100, 20);

		tf_PlayerPosE.reshape(100, 150, 70, 20);
		tf_PlayerPosH.reshape(170, 150, 70, 20);
		tf_PlayerPosN.reshape(240, 150, 70, 20);

		tf_PlayerOrZ.reshape(100, 180, 70, 20);
		tf_PlayerOrX.reshape(170, 180, 70, 20);
		tf_PlayerOrY.reshape(240, 180, 70, 20);

		tf_PlayerInitSped.reshape(110, 210, 100, 20);
		tf_PlayerControlThrottle.reshape(110, 240, 100, 20);

		cb_PlayerControlGear.reshape(10, 270, 150, 20);
		cb_PlayerEscort.reshape(10, 300, 150, 20);

		l_PlayerPos.reshape(10, 150, 100, 20);
		l_PlayerOr.reshape(10, 180, 100, 20);
		l_PlayerSped.reshape(10, 210, 100, 20);
		l_PlayerThrot.reshape(10, 240, 100, 20);

		b_PlayerReloadStartPos.reshape(210, 210, 100, 20);

		c_PlayerIFF.reshape(170, 120, 150, 20);
	}

	public void hidePlayer()
	{
		c_PlayerAircraft.reshape(-1000, -1000, 150, 20);
		c_PlayerStartPos.reshape(-1000, -1000, 150, 20);
		li_PlayerCategory.reshape(-1000, -1000, 150, 150);
		cb_PlayerRandom.reshape(-1000, -1000, 150, 20);
		cb_PlayerIncludePlayer.reshape(-1000, -1000, 150, 20);
		b_PlayerRestrict.reshape(-1000, -1000, 100, 20);

		tf_PlayerPosE.reshape(-1000, -1000, 150, 20);
		tf_PlayerPosH.reshape(-1000, -1000, 150, 20);
		tf_PlayerPosN.reshape(-1000, -1000, 150, 20);

		tf_PlayerOrZ.reshape(-1000, -1000, 150, 20);
		tf_PlayerOrX.reshape(-1000, -1000, 150, 20);
		tf_PlayerOrY.reshape(-1000, -1000, 150, 20);

		tf_PlayerInitSped.reshape(-1000, -1000, 150, 20);
		tf_PlayerControlThrottle.reshape(-1000, -1000, 150, 20);

		cb_PlayerControlGear.reshape(-1000, -1000, 150, 20);
		cb_PlayerEscort.reshape(-1000, -1000, 150, 20);

		l_PlayerPos.reshape(-1000, -1000, 150, 20);
		l_PlayerOr.reshape(-1000, -1000, 150, 20);
		l_PlayerSped.reshape(-1000, -1000, 150, 20);
		l_PlayerThrot.reshape(-1000, -1000, 150, 20);

		b_PlayerReloadStartPos.reshape(-1000, -1000, 150, 20);

		c_PlayerIFF.reshape(-1000, -1000, 150, 20);
	}

	public void showIFF1Array()
	{
		l_IFF1ArrayNum.reshape(10, 330, 150, 20);
		tf_IFF1ArrayEastNum.reshape(160, 330, 100, 20);
		tf_IFF1ArrayHeightNum.reshape(260, 330, 100, 20);
		tf_IFF1ArrayNorthNum.reshape(360, 330, 100, 20);

		l_IFF1ArraySpace.reshape(10, 360, 150, 20);
		tf_IFF1ArrayEastSpace.reshape(160, 360, 100, 20);
		tf_IFF1ArrayHeightSpace.reshape(260, 360, 100, 20);
		tf_IFF1ArrayNorthSpace.reshape(360, 360, 100, 20);
	}

	public void hideIFF1Array()
	{
		l_IFF1ArrayNum.reshape(-1000, -1000, 150, 20);
		tf_IFF1ArrayEastNum.reshape(-1000, -1000, 150, 20);
		tf_IFF1ArrayHeightNum.reshape(-1000, -1000, 150, 20);
		tf_IFF1ArrayNorthNum.reshape(-1000, -1000, 150, 20);

		l_IFF1ArraySpace.reshape(-1000, -1000, 150, 20);
		tf_IFF1ArrayEastSpace.reshape(-1000, -1000, 150, 20);
		tf_IFF1ArrayHeightSpace.reshape(-1000, -1000, 150, 20);
		tf_IFF1ArrayNorthSpace.reshape(-1000, -1000, 150, 20);
	}

	public void showIFF1Random()
	{
		l_IFF1RandomSize.reshape(10, 330, 150, 20);
		tf_IFF1RandomEastSize.reshape(160, 330, 100, 20);
		tf_IFF1RandomHeightSize.reshape(260, 330, 100, 20);
		tf_IFF1RandomNorthSize.reshape(360, 330, 100, 20);

		l_IFF1RandomAmount.reshape(10, 360, 150, 20);
		tf_IFF1RandomAmmountAircraft.reshape(160, 360, 100, 20);
	}

	public void hideIFF1Random()
	{
		l_IFF1RandomSize.reshape(-1000, -1000, 150, 20);
		tf_IFF1RandomEastSize.reshape(-1000, -1000, 150, 20);
		tf_IFF1RandomHeightSize.reshape(-1000, -1000, 150, 20);
		tf_IFF1RandomNorthSize.reshape(-1000, -1000, 150, 20);

		l_IFF1RandomAmount.reshape(-1000, -1000, 150, 200);
		tf_IFF1RandomAmmountAircraft.reshape(-1000, -1000, 150, 20);
	}

	public void showIFF2Array()
	{
		l_IFF2ArrayNum.reshape(10, 330, 150, 20);
		tf_IFF2ArrayEastNum.reshape(160, 330, 100, 20);
		tf_IFF2ArrayHeightNum.reshape(260, 330, 100, 20);
		tf_IFF2ArrayNorthNum.reshape(360, 330, 100, 20);

		l_IFF2ArraySpace.reshape(10, 360, 150, 20);
		tf_IFF2ArrayEastSpace.reshape(160, 360, 100, 20);
		tf_IFF2ArrayHeightSpace.reshape(260, 360, 100, 20);
		tf_IFF2ArrayNorthSpace.reshape(360, 360, 100, 20);
	}

	public void hideIFF2Array()
	{
		l_IFF2ArrayNum.reshape(-1000, -1000, 150, 20);
		tf_IFF2ArrayEastNum.reshape(-1000, -1000, 150, 20);
		tf_IFF2ArrayHeightNum.reshape(-1000, -1000, 150, 20);
		tf_IFF2ArrayNorthNum.reshape(-1000, -1000, 150, 20);

		l_IFF2ArraySpace.reshape(-1000, -1000, 150, 20);
		tf_IFF2ArrayEastSpace.reshape(-1000, -1000, 150, 20);
		tf_IFF2ArrayHeightSpace.reshape(-1000, -1000, 150, 20);
		tf_IFF2ArrayNorthSpace.reshape(-1000, -1000, 150, 20);
	}

	public void showIFF2Random()
	{
		l_IFF2RandomSize.reshape(10, 330, 150, 20);
		tf_IFF2RandomEastSize.reshape(160, 330, 100, 20);
		tf_IFF2RandomHeightSize.reshape(260, 330, 100, 20);
		tf_IFF2RandomNorthSize.reshape(360, 330, 100, 20);

		l_IFF2RandomAmount.reshape(10, 360, 150, 20);
		tf_IFF2RandomAmmountAircraft.reshape(160, 360, 100, 20);
	}

	public void hideIFF2Random()
	{
		l_IFF2RandomSize.reshape(-1000, -1000, 150, 20);
		tf_IFF2RandomEastSize.reshape(-1000, -1000, 150, 20);
		tf_IFF2RandomHeightSize.reshape(-1000, -1000, 150, 20);
		tf_IFF2RandomNorthSize.reshape(-1000, -1000, 150, 20);

		l_IFF2RandomAmount.reshape(-1000, -1000, 150, 200);
		tf_IFF2RandomAmmountAircraft.reshape(-1000, -1000, 150, 20);
	}

	public void exitProgram()
	{
		hide();
		dispose();
		runTimeData.flush();
		runTimeData.close();
		outputFile.flush();
		outputFile.close();
	}

	public boolean handleEvent(Event e)
	{
		if (e.id == Event.WINDOW_DESTROY)
		{
			exitProgram();
			return true;
		}

		return super.handleEvent(e);
	}

	public boolean action(Event e, Object o)
	{
		if (e.target == b_Quit)
		{
			exitProgram();
			return true;
		}
		else if (e.target == b_IFF1)
		{
			showIFF1();
			hideIFF2();
			hidePlayer();

			hideIFF2Array();
			hideIFF2Random();

			if (c_IFF1Formation.getSelectedItem().equals("Array"))
			{
				showIFF1Array();
			}
			else
			{
				showIFF1Random();
			}

			i_iffSelect = 1;
		}
		else if (e.target == b_IFF2)
		{
			showIFF2();
			hideIFF1();
			hidePlayer();

			hideIFF1Array();
			hideIFF1Random();

			if (c_IFF2Formation.getSelectedItem().equals("Array"))
			{
				showIFF2Array();
			}
			else
			{
				showIFF2Random();
			}

			i_iffSelect = 2;
		}
		else if (e.target == b_Player)
		{
			showPlayer();
			hideIFF1();
			hideIFF2();

			hideIFF1Array();
			hideIFF1Random();
			hideIFF2Array();
			hideIFF2Random();
		}
		else if (e.target == b_IFF1Restrict)
		{
			restrictIFF1List = li_IFF1Category.getSelectedItems();
			placeDetails(1);
		}
		else if (e.target == b_IFF2Restrict)
		{
			restrictIFF2List = li_IFF2Category.getSelectedItems();
			placeDetails(2);
		}
		else if (e.target == b_PlayerRestrict)
		{
			restrictPlayerList = li_PlayerCategory.getSelectedItems();
			placeDetails(0);
		}
		else if (e.target == c_VersionSelect)
		{
			loadDetails();
			placeDetails(-1);
		}
		else if (e.target == c_MapSelect)
		{
			loadStartPos();
		}
		else if (e.target == c_IFF1StartPos)
		{
			placeStartDetails(1);
		}
		else if (e.target == c_IFF2StartPos)
		{
			placeStartDetails(2);
		}
		else if (e.target == c_PlayerStartPos)
		{
			placeStartDetails(0);
		}
		else if (e.target == b_IFF1ReloadStartPos)
		{
			placeStartDetails(1);
		}
		else if (e.target == b_IFF2ReloadStartPos)
		{
			placeStartDetails(2);
		}
		else if (e.target == b_PlayerReloadStartPos)
		{
			placeStartDetails(0);
		}
		else if (e.target == c_IFF1Formation)
		{
			if (c_IFF1Formation.getSelectedItem().equals("Array"))
			{
				showIFF1Array();
				hideIFF1Random();
			}
			else
			{
				showIFF1Random();
				hideIFF1Array();
			}
		}
		else if (e.target == c_IFF2Formation)
		{
			if (c_IFF2Formation.getSelectedItem().equals("Array"))
			{
				showIFF2Array();
				hideIFF2Random();
			}
			else
			{
				showIFF2Random();
				hideIFF2Array();
			}
		}
		else if (e.target == cb_IFF1IncludePlayer)
		{
			if (cb_IFF1IncludePlayer.getState() == true)
			{
				if (b_Player.isEnabled() == true)
				{
					b_Player.setEnabled(false);
				}
				cb_IFF2IncludePlayer.setState(false);
			}
			else if(cb_IFF1IncludePlayer.getState() == false && cb_IFF2IncludePlayer.getState() == false)
			{
				b_Player.setEnabled(true);
			}
		}
		else if (e.target == cb_IFF2IncludePlayer)
		{
			if (cb_IFF2IncludePlayer.getState() == true)
			{
				if (b_Player.isEnabled() == true)
				{
					b_Player.setEnabled(false);
				}
				cb_IFF1IncludePlayer.setState(false);
			}
			else if(cb_IFF1IncludePlayer.getState() == false && cb_IFF2IncludePlayer.getState() == false)
			{
				b_Player.setEnabled(true);
			}
		}
		else if (e.target == b_Generate)
		{
			//b_Generate.setEnabled(false);
			//runTimeData.print("A1\n");
			if (tf_IFF1ArrayEastNum.getText().indexOf(".") != -1)
			{
				tf_IFF1ArrayEastNum.setText(tf_IFF1ArrayEastNum.getText().substring(0, tf_IFF1ArrayEastNum.getText().indexOf(".")));
			}
		
			if (tf_IFF1ArrayHeightNum.getText().indexOf(".") != -1)
			{
				tf_IFF1ArrayHeightNum.setText(tf_IFF1ArrayHeightNum.getText().substring(0, tf_IFF1ArrayHeightNum.getText().indexOf(".")));
			}
		
			if (tf_IFF1ArrayNorthNum.getText().indexOf(".") != -1)
			{
				tf_IFF1ArrayNorthNum.setText(tf_IFF1ArrayNorthNum.getText().substring(0, tf_IFF1ArrayNorthNum.getText().indexOf(".")));
			}

			if (tf_IFF1RandomAmmountAircraft.getText().indexOf(".") != -1)
			{
				tf_IFF1RandomAmmountAircraft.setText(tf_IFF1RandomAmmountAircraft.getText().substring(0, tf_IFF1RandomAmmountAircraft.getText().indexOf(".")));
			}


			if (tf_IFF2ArrayEastNum.getText().indexOf(".") != -1)
			{
				tf_IFF2ArrayEastNum.setText(tf_IFF2ArrayEastNum.getText().substring(0, tf_IFF2ArrayEastNum.getText().indexOf(".")));
			}
		
			if (tf_IFF2ArrayHeightNum.getText().indexOf(".") != -1)
			{
				tf_IFF2ArrayHeightNum.setText(tf_IFF2ArrayHeightNum.getText().substring(0, tf_IFF2ArrayHeightNum.getText().indexOf(".")));
			}
		
			if (tf_IFF2ArrayNorthNum.getText().indexOf(".") != -1)
			{
				tf_IFF2ArrayNorthNum.setText(tf_IFF2ArrayNorthNum.getText().substring(0, tf_IFF2ArrayNorthNum.getText().indexOf(".")));
			}

			if (tf_IFF2RandomAmmountAircraft.getText().indexOf(".") != -1)
			{
				tf_IFF2RandomAmmountAircraft.setText(tf_IFF2RandomAmmountAircraft.getText().substring(0, tf_IFF2RandomAmmountAircraft.getText().indexOf(".")));
			}


			if (Double.valueOf(tf_IFF1ControlThrottle.getText()) > 1)
			{
				tf_IFF1ControlThrottle.setText("1");
			}
			else if (Double.valueOf(tf_IFF1ControlThrottle.getText()) < 0)
			{
				tf_IFF1ControlThrottle.setText("0");
			}

			if (Double.valueOf(tf_IFF2ControlThrottle.getText()) > 1)
			{
				tf_IFF2ControlThrottle.setText("1");
			}
			else if (Double.valueOf(tf_IFF2ControlThrottle.getText()) < 0)
			{
				tf_IFF2ControlThrottle.setText("0");
			}

			if (Double.valueOf(tf_PlayerControlThrottle.getText()) > 1)
			{
				tf_PlayerControlThrottle.setText("1");
			}
			else if (Double.valueOf(tf_PlayerControlThrottle.getText()) < 0)
			{
				tf_PlayerControlThrottle.setText("0");
			}

			//runTimeData.print("A2\n");
			int planeIndex = 0;
			int numIFF1Aircraft = 0;
			int numIFF2Aircraft = 0;

			if (c_IFF1Formation.getSelectedItem() == "Array")
			{
				numIFF1Aircraft = (int)(Double.valueOf(tf_IFF1ArrayEastNum.getText()) * Double.valueOf(tf_IFF1ArrayHeightNum.getText()) * Double.valueOf(tf_IFF1ArrayNorthNum.getText()));
				////runTimeData.print(String.valueOf(numIFF1Aircraft));
			}
			else
			{
				numIFF1Aircraft = new Integer(tf_IFF1RandomAmmountAircraft.getText());
			}

			if (c_IFF2Formation.getSelectedItem() == "Array")
			{
				numIFF2Aircraft = (int)(Double.valueOf(tf_IFF2ArrayEastNum.getText()) * Double.valueOf(tf_IFF2ArrayHeightNum.getText()) * Double.valueOf(tf_IFF2ArrayNorthNum.getText()));
				////runTimeData.print(String.valueOf(numIFF2Aircraft));
			}
			else
			{
				numIFF2Aircraft = new Integer(tf_IFF2RandomAmmountAircraft.getText());
			}

			String outFileName = "PLACEHOLDER.yfs";
			runTimeData.print("A1X\n");

			if (c_IFF1Aircraft.getSelectedItem().indexOf("/") == -1)
			{
				if (c_IFF1Aircraft.getSelectedItem().indexOf("_", 4) != -1)
				{

					outFileName = String.valueOf(numIFF1Aircraft) + "x" + c_IFF1Aircraft.getSelectedItem().substring(0, c_IFF1Aircraft.getSelectedItem().indexOf("_", 4));
				}
				else
				{
					outFileName = String.valueOf(numIFF1Aircraft) + "x" + c_IFF1Aircraft.getSelectedItem();
				}
			}
			else
			{
				outFileName = String.valueOf(numIFF1Aircraft) + "x" + c_IFF1Aircraft.getSelectedItem().substring(0, c_IFF1Aircraft.getSelectedItem().indexOf("/"));
			}
			
			if (c_IFF2Aircraft.getSelectedItem().indexOf("/") == -1)
			{
				{
					if (c_IFF2Aircraft.getSelectedItem().indexOf("_", 4) != -1)
					{
						outFileName += "_V_" + String.valueOf(numIFF2Aircraft) + "x" + c_IFF2Aircraft.getSelectedItem().substring(0, c_IFF2Aircraft.getSelectedItem().indexOf("_", 4));
					}
					else
					{
						outFileName += "_V_" + String.valueOf(numIFF2Aircraft) + "x" + c_IFF2Aircraft.getSelectedItem();
					}
				}
			}
			else
			{
				outFileName += "_V_" + String.valueOf(numIFF2Aircraft) + "x" + c_IFF2Aircraft.getSelectedItem().substring(0, c_IFF2Aircraft.getSelectedItem().indexOf("/"));
			}
			
			runTimeData.print("A2X\n");

			//runTimeData.print("A3\n");
			try
			{
				////runTimeData.print("A1\n");
				outputFile = new PrintWriter(outFileName + ".yfs");
				////runTimeData.print("A2\n");
				outputFile.print("FIELDNAM ");
				outputFile.print(c_MapSelect.getSelectedItem());
				outputFile.print(" 0 0 0 0 0 0 FALSE LOADAIR:FALSE\n");
				////runTimeData.print("A3\n");
				outputFile.print("ENVIRONM ");
				outputFile.print(c_DayNightSelect.getSelectedItem());
				outputFile.print("\n");
				//runTimeData.print("A4\n");

				outputFile.print("ALLOWGUN ");
				if (cb_AllowGUN.getState() == true)
				{
					outputFile.print("TRUE\n");
				}
				else
				{
					outputFile.print("FALSE\n");
				}

				outputFile.print("ALLOWAAM ");
				if (cb_AllowAAM.getState() == true)
				{
					outputFile.print("TRUE\n");
				}
				else
				{
					outputFile.print("FALSE\n");
				}

				outputFile.print("ALLOWAGM ");
				if (cb_AllowAGM.getState() == true)
				{
					outputFile.print("TRUE\n");
				}
				else
				{
					outputFile.print("FALSE\n");
				}

				outputFile.print("ALLOWBOM ");
				if (cb_AllowBOM.getState() == true)
				{
					outputFile.print("TRUE\n");
				}
				else
				{
					outputFile.print("FALSE\n");
				}

				outputFile.print("ALLOWRKT ");
				if (cb_AllowRKT.getState() == true)
				{
					outputFile.print("TRUE\n");
				}
				else
				{
					outputFile.print("FALSE\n");
				}

				//runTimeData.print("A5\n");
				if (cb_IFF1IncludePlayer.getState() == false && cb_IFF2IncludePlayer.getState() == false && cb_PlayerIncludePlayer.getState() == true)
				{
					outputFile.print("AIRPLANE ");
					outputFile.print(c_PlayerAircraft.getSelectedItem());
					outputFile.print(" TRUE\n");

					outputFile.print("AIRPCMND POSITION ");
					outputFile.print(String.valueOf(tf_PlayerPosE.getText()) + "m " + String.valueOf(tf_PlayerPosH.getText()) + "m " + String.valueOf(tf_PlayerPosN.getText()) + "m");
					outputFile.print("\n");

					outputFile.print("AIRPCMND ATTITUDE ");
					outputFile.print(String.valueOf(tf_PlayerOrZ.getText()) + "deg " + String.valueOf(tf_PlayerOrX.getText()) + "deg " + String.valueOf(tf_PlayerOrY.getText()) + "deg");
					outputFile.print("\n");

					outputFile.print("AIRPCMND INITSPED ");
					if (tf_PlayerInitSped.getText().indexOf("kt") != -1 || tf_PlayerInitSped.getText().indexOf("m/s") != -1)
					{
						outputFile.print(tf_PlayerInitSped.getText());
					}
					else
					{
						outputFile.print(tf_PlayerInitSped.getText() + "m/s");
					}
					outputFile.print("\n");

					outputFile.print("AIRPCMND CTLTHROT ");
					outputFile.print(tf_PlayerControlThrottle.getText());
					outputFile.print("\n");

					outputFile.print("AIRPCMND CTLLDGEA ");
					if (cb_PlayerControlGear.getState() == true)
					{
						outputFile.print("TRUE\n");
					}
					else
					{
						outputFile.print("FALSE\n");
					}

					outputFile.print("IDENTIFY ");
					outputFile.print(String.valueOf(Integer.valueOf(c_PlayerIFF.getSelectedItem()) - 1));
					outputFile.print("\n");

					outputFile.print("IDANDTAG ");
					outputFile.print(String.valueOf(planeIndex) + " " + "\"GOLD_LEADER\"");
					planeIndex += 1;
					outputFile.print("\n");

					//runTimeData.print("A4\n");
					if (cb_PlayerEscort.getState() == true)
					{
						
						outputFile.print("AIRPLANE ");
						outputFile.print(c_PlayerAircraft.getSelectedItem());
						outputFile.print(" FALSE\n");

						outputFile.print("AIRPCMND POSITION ");
						outputFile.print(String.valueOf(Double.valueOf(tf_PlayerPosE.getText()) + Math.sin(Math.toRadians(Double.valueOf(tf_PlayerOrZ.getText()) - 135)) * 50) + "m " + String.valueOf(tf_PlayerPosH.getText()) + "m " + String.valueOf(Double.valueOf(tf_PlayerPosN.getText()) + Math.cos(Math.toRadians(Double.valueOf(tf_PlayerOrZ.getText()) - 135)) * 50) + "m");
						outputFile.print("\n");

						outputFile.print("AIRPCMND ATTITUDE ");
						outputFile.print(String.valueOf(tf_PlayerOrZ.getText()) + "deg " + String.valueOf(tf_PlayerOrX.getText()) + "deg " + String.valueOf(tf_PlayerOrY.getText()) + "deg");
						outputFile.print("\n");

						outputFile.print("AIRPCMND INITSPED ");
						if (tf_PlayerInitSped.getText().indexOf("kt") != -1 || tf_PlayerInitSped.getText().indexOf("m/s") != -1)
						{
							outputFile.print(tf_PlayerInitSped.getText());
						}
						else
						{
							outputFile.print(tf_PlayerInitSped.getText() + "m/s");
						}
						outputFile.print("\n");

						outputFile.print("AIRPCMND CTLTHROT ");
						outputFile.print(tf_PlayerControlThrottle.getText());
						outputFile.print("\n");

						outputFile.print("AIRPCMND CTLLDGEA ");
						if (cb_PlayerControlGear.getState() == true)
						{
							outputFile.print("TRUE\n");
						}
						else
						{
							outputFile.print("FALSE\n");
						}

						outputFile.print("IDENTIFY ");
						outputFile.print(String.valueOf(Integer.valueOf(c_PlayerIFF.getSelectedItem()) - 1));
						outputFile.print("\n");

						outputFile.print("IDANDTAG ");
						outputFile.print(String.valueOf(planeIndex) + "4444 " + "\"GOLD_1\"");
						planeIndex += 1;
						outputFile.print("\n");

						outputFile.print("INTENTIO\n");
						outputFile.print("MINIALTI 0\n");
						outputFile.print("DOGFIGHT G11.0 B20.0 + F");
						outputFile.print(String.valueOf(planeIndex));
						outputFile.print("\n");

						outputFile.print("GIVEUPDS 200000.0m\n");
						outputFile.print("ENDINTEN\n");

						outputFile.print("AIRPLANE ");
						outputFile.print(c_PlayerAircraft.getSelectedItem());
						outputFile.print(" FALSE\n");

						outputFile.print("AIRPCMND POSITION ");
						outputFile.print(String.valueOf(Double.valueOf(tf_PlayerPosE.getText()) + Math.sin(Math.toRadians(Double.valueOf(tf_PlayerOrZ.getText()) + 135)) * 50) + "m " + String.valueOf(tf_PlayerPosH.getText()) + "m " + String.valueOf(Double.valueOf(tf_PlayerPosN.getText()) + Math.cos(Math.toRadians(Double.valueOf(tf_PlayerOrZ.getText()) + 135)) * 50) + "m");
						outputFile.print("\n");

						outputFile.print("AIRPCMND ATTITUDE ");
						outputFile.print(String.valueOf(tf_PlayerOrZ.getText()) + "deg " + String.valueOf(tf_PlayerOrX.getText()) + "deg " + String.valueOf(tf_PlayerOrY.getText()) + "deg");
						outputFile.print("\n");

						outputFile.print("AIRPCMND INITSPED ");
						if (tf_PlayerInitSped.getText().indexOf("kt") != -1 || tf_PlayerInitSped.getText().indexOf("m/s") != -1)
						{
							outputFile.print(tf_PlayerInitSped.getText());
						}
						else
						{
							outputFile.print(tf_PlayerInitSped.getText() + "m/s");
						}
						outputFile.print("\n");

						outputFile.print("AIRPCMND CTLTHROT ");
						outputFile.print(tf_PlayerControlThrottle.getText());
						outputFile.print("\n");

						outputFile.print("AIRPCMND CTLLDGEA ");
						if (cb_PlayerControlGear.getState() == true)
						{
							outputFile.print("TRUE\n");
						}
						else
						{
							outputFile.print("FALSE\n");
						}

						outputFile.print("IDENTIFY ");
						outputFile.print(String.valueOf(Integer.valueOf(c_PlayerIFF.getSelectedItem()) - 1));
						outputFile.print("\n");

						outputFile.print("IDANDTAG ");
						outputFile.print(String.valueOf(planeIndex) + "4444 " + "\"GOLD_2\"");
						planeIndex += 1;
						outputFile.print("\n");

						outputFile.print("INTENTIO\n");
						outputFile.print("MINIALTI 0\n");
						outputFile.print("DOGFIGHT G11.0 B20.0 + F");
						outputFile.print(String.valueOf(planeIndex));
						outputFile.print("\n");

						outputFile.print("GIVEUPDS 200000.0m\n");
						outputFile.print("ENDINTEN\n");
						
					}
				}

				runTimeData.print("A3X\n");
				//runTimeData.print("A6\n");
				if (numIFF1Aircraft != 0)
				{
					String aircraftIFF1List[] = new String[c_IFF1Aircraft.getItemCount()];
					//runTimeData.print(String.valueOf(c_IFF1Aircraft.getItemCount()) + "\n");

					//runTimeData.print(aircraftIFF1List[0] + "|" + String.valueOf(0) + "\n");
					Random aircraftChoose = new Random();
					if (cb_IFF1Random.getState() == true)
					{
						for (int i = 0; i < c_IFF1Aircraft.getItemCount(); i++)
						{
							aircraftIFF1List[i] = c_IFF1Aircraft.getItem(i);
							//runTimeData.print(aircraftIFF1List[i] + "|" + String.valueOf(i) + "\n");
						}
					}
					
					if (c_IFF1Formation.getSelectedItem() == "Array")
					{
						Double initPosE = Double.valueOf(tf_IFF1PosE.getText());
						Double initPosH = Double.valueOf(tf_IFF1PosH.getText());
						Double initPosN = Double.valueOf(tf_IFF1PosN.getText());

						Double spacePosE = Double.valueOf(tf_IFF1ArrayEastSpace.getText());
						Double spacePosH = Double.valueOf(tf_IFF1ArrayHeightSpace.getText());
						Double spacePosN = Double.valueOf(tf_IFF1ArrayNorthSpace.getText());

						for (int i = 0; i < Integer.valueOf(tf_IFF1ArrayEastNum.getText()); i++)
						{
							for (int j = 0; j < Integer.valueOf(tf_IFF1ArrayNorthNum.getText()); j++)
							{
								for (int k = 0; k < Integer.valueOf(tf_IFF1ArrayHeightNum.getText()); k++)
								{
									////runTimeData.print(String.valueOf(i) + " " + String.valueOf(k) + " " + String.valueOf(j) + "\n");
									outputFile.print("AIRPLANE ");
									if (cb_IFF1Random.getState() == true)
									{
										outputFile.print(aircraftIFF1List[aircraftChoose.nextInt(aircraftIFF1List.length)]);
									}
									else
									{
										outputFile.print(c_IFF1Aircraft.getSelectedItem());
									}
									
									if (cb_IFF1IncludePlayer.getState() == true && planeIndex == 0)
									{
										outputFile.print(" TRUE\n");
									}
									else
									{
										outputFile.print(" FALSE\n");
									}

									outputFile.print("AIRPCMND POSITION ");
									outputFile.print(String.valueOf(initPosE + spacePosE * i) + "m " + String.valueOf(initPosH + spacePosH * k) + "m " + String.valueOf(initPosN + spacePosN * j) + "m");
									outputFile.print("\n");

									outputFile.print("AIRPCMND ATTITUDE ");
									outputFile.print(String.valueOf(tf_IFF1OrZ.getText()) + "deg " + String.valueOf(tf_IFF1OrX.getText()) + "deg " + String.valueOf(tf_IFF1OrY.getText()) + "deg");
									outputFile.print("\n");

									outputFile.print("AIRPCMND INITSPED ");
									if (tf_IFF1InitSped.getText().indexOf("kt") != -1 || tf_IFF1InitSped.getText().indexOf("m/s") != -1)
									{
										outputFile.print(tf_IFF1InitSped.getText());
									}
									else
									{
										outputFile.print(tf_IFF1InitSped.getText() + "m/s");
									}
									outputFile.print("\n");

									outputFile.print("AIRPCMND CTLTHROT ");
									outputFile.print(tf_IFF1ControlThrottle.getText());
									outputFile.print("\n");

									outputFile.print("AIRPCMND CTLLDGEA ");
									if (cb_IFF1ControlGear.getState() == true)
									{
										outputFile.print("TRUE\n");
									}
									else
									{
										outputFile.print("FALSE\n");
									}

									outputFile.print("IDENTIFY ");
									outputFile.print("0");
									outputFile.print("\n");

									outputFile.print("IDANDTAG ");
									outputFile.print(String.valueOf(planeIndex) + " " + "\"" + String.valueOf(planeIndex) + "\"");
									outputFile.print("\n");

									if (planeIndex != 0 || cb_IFF1IncludePlayer.getState() == false)
									{
										////runTimeData.print(String.valueOf(planeIndex) + "\n");
										outputFile.print("INTENTIO\n");
										outputFile.print("MINIALTI 500\n");
										if (c_IFF1ComputerLevel.getSelectedItem() != "NO DOGFIGHT")
										{
											outputFile.print("DOGFIGHT G");
											if (c_IFF1ComputerLevel.getSelectedItem().startsWith("NOVICE"))
											{
												outputFile.print("3.00 B20.00 F");
											}
											else if (c_IFF1ComputerLevel.getSelectedItem().startsWith("VETERAN"))
											{
												outputFile.print("5.00 B20.00 F");
											}
											else if (c_IFF1ComputerLevel.getSelectedItem().startsWith("ACE"))
											{
												outputFile.print("7.00 B20.00 F");
											}
											else if (c_IFF1ComputerLevel.getSelectedItem().startsWith("DEADLY"))
											{
												outputFile.print("9.00 B20.00 F");
											}
											else if (c_IFF1ComputerLevel.getSelectedItem().startsWith("LETHAL"))
											{
												outputFile.print("11.00 B20.00 F");
											}
											outputFile.print(String.valueOf(0));
											outputFile.print("\n");
										}

										outputFile.print("GIVEUPDS 200000.0m\n");
										outputFile.print("ENDINTEN\n");
									}
									planeIndex += 1;
								}
							}
						}
					}
					else if (c_IFF1Formation.getSelectedItem() == "Random")
					{
						Double initPosE = Double.valueOf(tf_IFF1PosE.getText());
						Double initPosH = Double.valueOf(tf_IFF1PosH.getText());
						Double initPosN = Double.valueOf(tf_IFF1PosN.getText());

						Integer randSizeE = Integer.valueOf(tf_IFF1RandomEastSize.getText()) + 1;
						Integer randSizeH = Integer.valueOf(tf_IFF1RandomHeightSize.getText()) + 1;
						Integer randSizeN = Integer.valueOf(tf_IFF1RandomNorthSize.getText()) + 1;

						for (int i = 0; i < numIFF1Aircraft; i++)
						{
							outputFile.print("AIRPLANE ");
							if (cb_IFF1Random.getState() == true)
							{
								outputFile.print(aircraftIFF1List[aircraftChoose.nextInt(aircraftIFF1List.length)]);
							}
							else
							{
								outputFile.print(c_IFF1Aircraft.getSelectedItem());
							}
							
							if (cb_IFF1IncludePlayer.getState() == true && planeIndex == 0)
							{
								outputFile.print(" TRUE\n");
							}
							else
							{
								outputFile.print(" FALSE\n");
							}

							outputFile.print("AIRPCMND POSITION ");
							outputFile.print(String.valueOf(initPosE + aircraftChoose.nextInt(randSizeE) - (randSizeE / 2)) + "m " + String.valueOf(initPosH + aircraftChoose.nextInt(randSizeH) - (randSizeH / 2)) + "m " + String.valueOf(initPosN + aircraftChoose.nextInt(randSizeN) - (randSizeN / 2)) + "m");
							outputFile.print("\n");

							outputFile.print("AIRPCMND ATTITUDE ");
							outputFile.print(String.valueOf(tf_IFF1OrZ.getText()) + "deg " + String.valueOf(tf_IFF1OrX.getText()) + "deg " + String.valueOf(tf_IFF1OrY.getText()) + "deg");
							outputFile.print("\n");

							outputFile.print("AIRPCMND INITSPED ");
							if (tf_IFF1InitSped.getText().indexOf("kt") != -1 || tf_IFF1InitSped.getText().indexOf("m/s") != -1)
							{
								outputFile.print(tf_IFF1InitSped.getText());
							}
							else
							{
								outputFile.print(tf_IFF1InitSped.getText() + "m/s");
							}
							outputFile.print("\n");

							outputFile.print("AIRPCMND CTLTHROT ");
							outputFile.print(tf_IFF1ControlThrottle.getText());
							outputFile.print("\n");

							outputFile.print("AIRPCMND CTLLDGEA ");
							if (cb_IFF1ControlGear.getState() == true)
							{
								outputFile.print("TRUE\n");
							}
							else
							{
								outputFile.print("FALSE\n");
							}

							outputFile.print("IDENTIFY ");
							outputFile.print("0");
							outputFile.print("\n");

							outputFile.print("IDANDTAG ");
							outputFile.print(String.valueOf(planeIndex) + " " + "\"" + String.valueOf(planeIndex) + "\"");
							
							outputFile.print("\n");

							if (planeIndex != 0 || cb_IFF1IncludePlayer.getState() == false)
							{
								outputFile.print("INTENTIO\n");
								outputFile.print("MINIALTI 500\n");
								if (c_IFF1ComputerLevel.getSelectedItem() != "NO DOGFIGHT")
								{
									outputFile.print("DOGFIGHT G");
									if (c_IFF1ComputerLevel.getSelectedItem().startsWith("NOVICE"))
									{
										outputFile.print("3.00 B20.00 F");
									}
									else if (c_IFF1ComputerLevel.getSelectedItem().startsWith("VETERAN"))
									{
										outputFile.print("5.00 B20.00 F");
									}
									else if (c_IFF1ComputerLevel.getSelectedItem().startsWith("ACE"))
									{
										outputFile.print("7.00 B20.00 F");
									}
									else if (c_IFF1ComputerLevel.getSelectedItem().startsWith("DEADLY"))
									{
										outputFile.print("9.00 B20.00 F");
									}
									else if (c_IFF1ComputerLevel.getSelectedItem().startsWith("LETHAL"))
									{
										outputFile.print("11.00 B20.00 F");
									}
									outputFile.print(String.valueOf(0));
									outputFile.print("\n");
								}

								outputFile.print("GIVEUPDS 200000.0m\n");
								outputFile.print("ENDINTEN\n");
							}
							planeIndex += 1;
						}
					}
				}
				runTimeData.print("A4X\n");
				if (numIFF2Aircraft != 0)
				{
					String aircraftIFF2List[] = new String[c_IFF2Aircraft.getItemCount()];
					Random aircraftChoose = new Random();
					if (cb_IFF2Random.getState() == true)
					{
						for (int i = 0; i < c_IFF2Aircraft.getItemCount(); i++)
						{
							aircraftIFF2List[i] = c_IFF2Aircraft.getItem(i);
						}
					}
					if (c_IFF2Formation.getSelectedItem() == "Array")
					{
						Double initPosE = Double.valueOf(tf_IFF2PosE.getText());
						Double initPosH = Double.valueOf(tf_IFF2PosH.getText());
						Double initPosN = Double.valueOf(tf_IFF2PosN.getText());

						Double spacePosE = Double.valueOf(tf_IFF2ArrayEastSpace.getText());
						Double spacePosH = Double.valueOf(tf_IFF2ArrayHeightSpace.getText());
						Double spacePosN = Double.valueOf(tf_IFF2ArrayNorthSpace.getText());

						for (int i = 0; i < Integer.valueOf(tf_IFF2ArrayEastNum.getText()); i++)
						{
							for (int j = 0; j < Integer.valueOf(tf_IFF2ArrayNorthNum.getText()); j++)
							{
								for (int k = 0; k < Integer.valueOf(tf_IFF2ArrayHeightNum.getText()); k++)
								{
									outputFile.print("AIRPLANE ");
									if (cb_IFF2Random.getState() == true)
									{
										outputFile.print(aircraftIFF2List[aircraftChoose.nextInt(aircraftIFF2List.length)]);
									}
									else
									{
										outputFile.print(c_IFF2Aircraft.getSelectedItem());
									}

									if (cb_IFF2IncludePlayer.getState() == true && planeIndex == numIFF1Aircraft)
									{
										outputFile.print(" TRUE\n");
									}
									else
									{
										outputFile.print(" FALSE\n");
									}

									outputFile.print("AIRPCMND POSITION ");
									outputFile.print(String.valueOf(initPosE + spacePosE * i) + "m " + String.valueOf(initPosH + spacePosH * k) + "m " + String.valueOf(initPosN + spacePosN * j) + "m");
									outputFile.print("\n");

									outputFile.print("AIRPCMND ATTITUDE ");
									outputFile.print(String.valueOf(tf_IFF2OrZ.getText()) + "deg " + String.valueOf(tf_IFF2OrX.getText()) + "deg " + String.valueOf(tf_IFF2OrY.getText()) + "deg");
									outputFile.print("\n");

									outputFile.print("AIRPCMND INITSPED ");
									if (tf_IFF2InitSped.getText().indexOf("kt") != -1 || tf_IFF2InitSped.getText().indexOf("m/s") != -1)
									{
										outputFile.print(tf_IFF2InitSped.getText());
									}
									else
									{
										outputFile.print(tf_IFF2InitSped.getText() + "m/s");
									}
									outputFile.print("\n");

									outputFile.print("AIRPCMND CTLTHROT ");
									outputFile.print(tf_IFF2ControlThrottle.getText());
									outputFile.print("\n");

									outputFile.print("AIRPCMND CTLLDGEA ");
									if (cb_IFF2ControlGear.getState() == true)
									{
										outputFile.print("TRUE\n");
									}
									else
									{
										outputFile.print("FALSE\n");
									}

									outputFile.print("IDENTIFY ");
									outputFile.print("1");
									outputFile.print("\n");

									outputFile.print("IDANDTAG ");
									outputFile.print(String.valueOf(planeIndex) + " " + "\"" + String.valueOf(planeIndex) + "\"");
									
									outputFile.print("\n");

									if (planeIndex != numIFF1Aircraft || cb_IFF2IncludePlayer.getState() == false)
									{
										outputFile.print("INTENTIO\n");
										outputFile.print("MINIALTI 500\n");
										if (c_IFF2ComputerLevel.getSelectedItem() != "NO DOGFIGHT")
										{
											outputFile.print("DOGFIGHT G");
											if (c_IFF2ComputerLevel.getSelectedItem().startsWith("NOVICE"))
											{
												outputFile.print("3.00 B20.00 F");
											}
											else if (c_IFF2ComputerLevel.getSelectedItem().startsWith("VETERAN"))
											{
												outputFile.print("5.00 B20.00 F");
											}
											else if (c_IFF2ComputerLevel.getSelectedItem().startsWith("ACE"))
											{
												outputFile.print("7.00 B20.00 F");
											}
											else if (c_IFF2ComputerLevel.getSelectedItem().startsWith("DEADLY"))
											{
												outputFile.print("9.00 B20.00 F");
											}
											else if (c_IFF2ComputerLevel.getSelectedItem().startsWith("LETHAL"))
											{
												outputFile.print("11.00 B20.00 F");
											}
											outputFile.print(String.valueOf(1));
											outputFile.print("\n");
										}

										outputFile.print("GIVEUPDS 200000.0m\n");
										outputFile.print("ENDINTEN\n");
									}
									planeIndex += 1;

								}
							}
						}
					}
					else if (c_IFF2Formation.getSelectedItem() == "Random")
					{
						Double initPosE = Double.valueOf(tf_IFF2PosE.getText());
						Double initPosH = Double.valueOf(tf_IFF2PosH.getText());
						Double initPosN = Double.valueOf(tf_IFF2PosN.getText());

						Integer randSizeE = Integer.valueOf(tf_IFF2RandomEastSize.getText()) + 1;
						Integer randSizeH = Integer.valueOf(tf_IFF2RandomHeightSize.getText()) + 1;
						Integer randSizeN = Integer.valueOf(tf_IFF2RandomNorthSize.getText()) + 1;

						for (int i = 0; i < numIFF2Aircraft; i++)
						{
							outputFile.print("AIRPLANE ");
							if (cb_IFF2Random.getState() == true)
							{
								outputFile.print(aircraftIFF2List[aircraftChoose.nextInt(aircraftIFF2List.length)]);
							}
							else
							{
								outputFile.print(c_IFF2Aircraft.getSelectedItem());
							}
							
							if (cb_IFF2IncludePlayer.getState() == true && planeIndex == numIFF1Aircraft)
							{
								outputFile.print(" TRUE\n");
							}
							else
							{
								outputFile.print(" FALSE\n");
							}

							outputFile.print("AIRPCMND POSITION ");
							//runTimeData.print(String.valueOf(aircraftChoose.nextInt(randSizeE)) + "\n");
							//runTimeData.print(String.valueOf(aircraftChoose.nextInt(randSizeH)) + "\n");
							//runTimeData.print(String.valueOf(aircraftChoose.nextInt(randSizeN)) + "\n");
							//runTimeData.print(String.valueOf(initPosE + aircraftChoose.nextInt(randSizeE) - (randSizeE / 2)) + "m " + String.valueOf(initPosH + aircraftChoose.nextInt(randSizeH) - (randSizeH / 2)) + "m " + String.valueOf(initPosN + aircraftChoose.nextInt(randSizeN) - (randSizeN / 2)) + "m" + "\n");
							outputFile.print(String.valueOf(initPosE + aircraftChoose.nextInt(randSizeE) - (randSizeE / 2)) + "m " + String.valueOf(initPosH + aircraftChoose.nextInt(randSizeH) - (randSizeH / 2)) + "m " + String.valueOf(initPosN + aircraftChoose.nextInt(randSizeN) - (randSizeN / 2)) + "m");
							outputFile.print("\n");

							outputFile.print("AIRPCMND ATTITUDE ");
							outputFile.print(String.valueOf(tf_IFF2OrZ.getText()) + "deg " + String.valueOf(tf_IFF2OrX.getText()) + "deg " + String.valueOf(tf_IFF2OrY.getText()) + "deg");
							outputFile.print("\n");

							outputFile.print("AIRPCMND INITSPED ");
							if (tf_IFF2InitSped.getText().indexOf("kt") != -1 || tf_IFF2InitSped.getText().indexOf("m/s") != -1)
							{
								outputFile.print(tf_IFF2InitSped.getText());
							}
							else
							{
								outputFile.print(tf_IFF2InitSped.getText() + "m/s");
							}
							outputFile.print("\n");

							outputFile.print("AIRPCMND CTLTHROT ");
							outputFile.print(tf_IFF2ControlThrottle.getText());
							outputFile.print("\n");

							outputFile.print("AIRPCMND CTLLDGEA ");
							if (cb_IFF2ControlGear.getState() == true)
							{
								outputFile.print("TRUE\n");
							}
							else
							{
								outputFile.print("FALSE\n");
							}

							outputFile.print("IDENTIFY ");
							outputFile.print("1");
							outputFile.print("\n");

							outputFile.print("IDANDTAG ");
							outputFile.print(String.valueOf(planeIndex) + " " + "\"" + String.valueOf(planeIndex) + "\"");
							
							outputFile.print("\n");

							if (planeIndex != numIFF1Aircraft || cb_IFF2IncludePlayer.getState() == false)
							{
								outputFile.print("INTENTIO\n");
								outputFile.print("MINIALTI 500\n");
								if (c_IFF2ComputerLevel.getSelectedItem() != "NO DOGFIGHT")
								{
									outputFile.print("DOGFIGHT G");
									if (c_IFF2ComputerLevel.getSelectedItem().startsWith("NOVICE"))
									{
										outputFile.print("3.00 B20.00 F");
									}
									else if (c_IFF2ComputerLevel.getSelectedItem().startsWith("VETERAN"))
									{
										outputFile.print("5.00 B20.00 F");
									}
									else if (c_IFF2ComputerLevel.getSelectedItem().startsWith("ACE"))
									{
										outputFile.print("7.00 B20.00 F");
									}
									else if (c_IFF2ComputerLevel.getSelectedItem().startsWith("DEADLY"))
									{
										outputFile.print("9.00 B20.00 F");
									}
									else if (c_IFF2ComputerLevel.getSelectedItem().startsWith("LETHAL"))
									{
										outputFile.print("11.00 B20.00 F");
									}
									outputFile.print(String.valueOf(1));
									outputFile.print("\n");
								}
								
								outputFile.print("GIVEUPDS 200000.0m\n");
								outputFile.print("ENDINTEN\n");
							}
							planeIndex += 1;
						}
					}
				}


			}
			catch (IOException er)
			{
				runTimeData.print("H" + er.toString() + "\n");
			}
			catch (Exception err)
			{
				runTimeData.print("H1" + err.toString() + "\n");
			}

			runTimeData.print("A5X\n");
			String map = c_MapSelect.getSelectedItem();
			String gameVersion = c_VersionSelect.getSelectedItem();
			String path = "ysflight/" + gameVersion;

			for (int i = 0; i < mapList.length; i++)
			{
				try
				{
					if (mapList[i].mapName.equals(map))
					{
						//c_IFF1StartPos.addItem("helloA" + String.valueOf(i));
						try
						{
							boolean gobReach = false;
							String name = "";
							String pos = "";
							String flg = "";
							String iff = "";
							Boolean pmt = false;
							RandomAccessFile fldListFile = new RandomAccessFile(path + "/" + mapList[i].fldPath, "r");
							do
							{
								String t = fldListFile.readLine();
								if (t.startsWith("GOB"))
								{
									gobReach = true;
									name = "";
									pos = "";
									flg = "";
									iff = "";
									pmt = false;
								}

								if (gobReach)
								{
									if (t.startsWith("POS "))
									{
										pos = t;
									}
									else if (t.startsWith("NAM "))
									{
										name = t;
									}
									else if (t.startsWith("FLG "))
									{
										flg = t;
									}
									else if (t.startsWith("IFF "))
									{
										iff = t;
									}
									else if (t.startsWith("PMT"))
									{
										pmt = true;
									}
									else if (t.equals("END"))
									{
										outputFile.print("GROUNDOB ");
										outputFile.print(name.substring(name.indexOf(" ") + 1, name.length()));
										outputFile.print(" FALSE\n");

										outputFile.print("IDENTIFY ");
										outputFile.print(iff.substring(iff.indexOf(" ") + 1, iff.length()));
										outputFile.print("\n");

										outputFile.print("IDANDTAG ");
										outputFile.print(String.valueOf(planeIndex) + " \"\"");
										outputFile.print("\n");

										outputFile.print("GNDPOSIT ");
										String pos1 = pos.substring(pos.indexOf(" ") + 1, pos.length());
										String pos2 = pos1.substring(pos1.indexOf(" ") + 1, pos1.length());
										String pos3 = pos2.substring(pos2.indexOf(" ") + 1, pos2.length());
										String pos4 = pos3.substring(pos3.indexOf(" ") + 1, pos3.length());
										String pos5 = pos4.substring(pos4.indexOf(" ") + 1, pos4.length());
										String pos6 = pos5.substring(pos5.indexOf(" ") + 1, pos5.length());
										runTimeData.print(pos6);
										runTimeData.print("\n");
										outputFile.print(pos1.substring(0, pos1.indexOf(" ")) + "m ");
										outputFile.print(pos2.substring(0, pos2.indexOf(" ")) + "m ");
										outputFile.print(pos3.substring(0, pos3.indexOf(" ")) + "m ");
										outputFile.print("\n");
										outputFile.print("GNDATTIT ");
										outputFile.print(String.valueOf(Double.valueOf(pos4.substring(0, pos4.indexOf(" "))) * Math.pow(10, -5) * 9.587366298) + "rad ");
										outputFile.print(String.valueOf(Double.valueOf(pos5.substring(0, pos5.indexOf(" "))) * Math.pow(10, -5) * 9.587366298) + "rad ");
										outputFile.print(String.valueOf(Double.valueOf(pos6) * Math.pow(10, -5) * 9.587366298) + "rad");
										outputFile.print("\n");

										if (pmt)
										{
											outputFile.print("PRTARGET\n");
										}

										outputFile.print("GNDFLAGS ");
										outputFile.print(flg.substring(flg.indexOf(" "), flg.length()));
										outputFile.print("\n");
										planeIndex += 1;
										pmt = false;


									}
								}

							} while (fldListFile.getFilePointer() < fldListFile.length());
						}
						catch (Exception er)
						{
							//runTimeData.print("H" + er.toString() + "\n");
						}
					}
				}
				catch (Exception er)
				{
					//runTimeData.print("H" + er.toString() + "\n");
				}
			}

			runTimeData.print("A6X\n");
			outputFile.flush();
			outputFile.close();
			//b_Generate.setEnabled(true);

		}


		return true;
	}

	public static void main(String args[])
	{
		YSFlightMU ysfmu = new YSFlightMU();
	}
}