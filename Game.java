import java.io.*;
import java.util.*;
class TrackSizeLimitException extends Exception {           // To handle size of the track
	public TrackSizeLimitException(String message) {
		super(message);
	}
}
class UnlimitedRollsException extends Exception {           // To handle the limit of total rolls
	public UnlimitedRollsException(String message) {
		super(message);
	}
}
class SnakeBiteException extends Exception {               
	public SnakeBiteException(String message) {
		super(message);
	}
}
class CricketBiteException extends Exception {
	public CricketBiteException(String message) {
		super(message);
	}
}
class VultureBiteException extends Exception {
	public VultureBiteException(String message) {
		super(message);
	}
}
class TrampolineException extends Exception {
	public TrampolineException(String message) {
		super(message);
	}
}
class GameWinnerException extends Exception {
	public GameWinnerException(String message) {
		super(message);
	}
}
abstract class Tile implements Serializable           // Blueprint for tile
{
	private static final long serialVersionUID = 1L;
	protected int number;
	protected int attackNumber;
	Tile() {
		number=0;
		attackNumber=0;
	}
	abstract public void affect(Player p) throws SnakeBiteException, CricketBiteException, VultureBiteException, TrampolineException;
}
class Snake extends Tile         // Tile's Subclass
{
	private static final long serialVersionUID = 2L;
	Snake(int y,int x) {
		number=y;
		attackNumber=x;
	}
	@Override
	public void affect(Player p) throws SnakeBiteException {
		if(attackNumber<p.getCurrentNumber())
			p.setCurrentNumber(p.getCurrentNumber()-attackNumber);
		else
			p.setCurrentNumber(1);
		p.setSnakeBites(p.getSnakeBites()+1);
		throw new SnakeBiteException("Hiss...! I am a Snake, you go back "+attackNumber+" tiles!");
	}
}
class Cricket extends Tile         // Tile's Subclass
{
	private static final long serialVersionUID = 3L;
	Cricket(int y,int x) {
		number=y;
		attackNumber=x;
	}
	@Override
	public void affect(Player p) throws CricketBiteException {
		if(attackNumber<p.getCurrentNumber())
			p.setCurrentNumber(p.getCurrentNumber()-attackNumber);
		else
			p.setCurrentNumber(1);
		p.setCricketBites(p.getCricketBites()+1);
		throw new CricketBiteException("Chirp...! I am a Cricket, you go back "+attackNumber+" tiles!");
	}
}
class Vulture extends Tile          // Tile's Subclass
{
	private static final long serialVersionUID = 4L;
	Vulture(int y,int x) {
		number=y;
		attackNumber=x;
	}
	@Override
	public void affect(Player p) throws VultureBiteException {
		if(attackNumber<p.getCurrentNumber())
			p.setCurrentNumber(p.getCurrentNumber()-attackNumber);
		else
			p.setCurrentNumber(1);
		p.setVultureBites(p.getVultureBites()+1);
		throw new VultureBiteException("Yapping...! I am a Vulture, you go back "+attackNumber+" tiles!");
	}
}
class Trampoline extends Tile       // Tile's Subclass
{ 
	private static final long serialVersionUID = 5L;
	Trampoline(int y,int x) {
		number=y;
		attackNumber=x;
	}
	@Override
	public void affect(Player p) throws TrampolineException {
		if(attackNumber<p.getTotalTiles()-p.getCurrentNumber())
			p.setCurrentNumber(p.getCurrentNumber()+attackNumber);
		else
			p.setCurrentNumber(p.getTotalTiles());
		p.setTrampolines(p.getTrampolines()+1);
		throw new TrampolineException("PingPong! I am a Trampoline, you advance "+attackNumber+" tiles!");
	}
}
class White extends Tile        // Tile's Subclass
{ 
	private static final long serialVersionUID = 6L;
	White(int y) {
		super();
		number=y;
	}
	@Override
	public void affect(Player p) {
		System.out.println("I am a White Tile!");
	}
}
class Player implements Serializable         // Blueprint for player
{
	private static final long serialVersionUID=42L;
	private final String name;
	private int currentNumber;
	private int totalTiles;
	private int snakeBites;
	private int cricketBites;
	private int vultureBites;
	private int trampolines;
	private int save1;
	private int save2;
	private int save3;
	private Tile[] track;
	private int rollsLimit;
	private int rolls;
	Player(String name, int total,Tile[] track,int rollsLimit,int rolls) {
		this.name=name;
		currentNumber=1;
		totalTiles=total;
		snakeBites=0;
		cricketBites=0;
		vultureBites=0;
		trampolines=0;
		save1=0;
		save2=0;
		save3=0;
		this.track=track.clone();
		this.rollsLimit=rollsLimit;
		this.rolls=rolls;
	}
	public void move(int steps) {           // method to move on the track
		currentNumber+=steps;
	}
	@Override
	public boolean equals(Object o) {           //Overriden equals method
		if(o!=null&&this.getClass()==o.getClass()) {
			Player p=(Player)o;
			return((name.compareTo(p.name)==0)&&(currentNumber==p.currentNumber)&&(totalTiles==p.totalTiles)&&(snakeBites==p.snakeBites)&&(vultureBites==p.vultureBites)&&(cricketBites==p.cricketBites)&&(trampolines==p.trampolines));
		}
		return false;
	}
	@Override
	public String toString() {                  //Overriden toString method
		return "("+name+","+currentNumber+","+totalTiles+","+snakeBites+","+cricketBites+","+vultureBites+","+trampolines+")";
	}
	public int getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}
	public int getTotalTiles() {
		return totalTiles;
	}
	public void setTotalTiles(int totalTiles) {
		this.totalTiles = totalTiles;
	}
	public int getSnakeBites() {
		return snakeBites;
	}
	public void setSnakeBites(int snakeBites) {
		this.snakeBites = snakeBites;
	}
	public int getCricketBites() {
		return cricketBites;
	}
	public void setCricketBites(int cricketBites) {
		this.cricketBites = cricketBites;
	}
	public int getVultureBites() {
		return vultureBites;
	}
	public void setVultureBites(int vultureBites) {
		this.vultureBites = vultureBites;
	}
	public int getTrampolines() {
		return trampolines;
	}
	public void setTrampolines(int trampolines) {
		this.trampolines = trampolines;
	}
	public String getName() {
		return name;
	}
	public int getSave1() {
		return save1;
	}
	public void setSave1(int save1) {
		this.save1 = save1;
	}
	public int getSave2() {
		return save2;
	}
	public void setSave2(int save2) {
		this.save2 = save2;
	}
	public int getSave3() {
		return save3;
	}
	public void setSave3(int save3) {
		this.save3 = save3;
	}
	public Tile[] getTrack() {
		return track;
	}
	public int getRollsLimit() {
		return rollsLimit;
	}
	public void setRollsLimit(int rollsLimit) {
		this.rollsLimit = rollsLimit;
	}
	public int getRolls() {
		return rolls;
	}
	public void setRolls(int rolls) {
		this.rolls = rolls;
	}
}
public class Game            // main class
{
	public static int rollsLimit;
	public static int rolls=0;
	public static ArrayList<String> players=new ArrayList<String>();
	public static Tile[] track; 
	public static Scanner s=new Scanner(System.in);
	public static Random r=new Random();
	public static void setTrack() throws TrackSizeLimitException {     // function to make the track
		System.out.println("Enter total number of tiles on the race track (length)");
		boolean temp=true;
		while(temp) {
			try {
				int total=Integer.parseInt(s.nextLine());
				if((total>0&&total<10)||total>10000)
					throw new TrackSizeLimitException("Track size should be >= 10 and practically finite!");
				rollsLimit=total*100;
				System.out.println("Setting up the race track...");
				track=new Tile[total+1];
				track[1]=new White(1);
				track[total]=new White(total);
				ArrayList<Integer> indices=new ArrayList<Integer>();
				for(int i=2;i<total;i++)
					indices.add(i);
				int snakePower=0;
				int cricketPower=0;
				int vulturePower=0;
				int trampolinePower=0;
				if(total<=100) {
					snakePower=r.nextInt(total/10)+1;
					cricketPower=r.nextInt(total/10)+1;
					vulturePower=r.nextInt(total/10)+1;
					trampolinePower=r.nextInt(total/10)+1;
				}
				else {
					snakePower=r.nextInt(10)+1;
					cricketPower=r.nextInt(10)+1;
					vulturePower=r.nextInt(10)+1;
					trampolinePower=r.nextInt(10)+1;
				}
				int snakeNo=r.nextInt((total/5)-(total/20))+(total/20)+1;
				for(int j=0;j<snakeNo;j++) {
					int index=r.nextInt(indices.size());
					Tile x=new Snake(indices.get(index), snakePower);
					track[indices.get(index)]=x;
					indices.remove(indices.get(index));
				}
				int cricketNo=r.nextInt((total/5)-(total/20))+(total/20)+1;
				for(int j=0;j<cricketNo;j++) {
					int index=r.nextInt(indices.size());
					Tile x=new Cricket(indices.get(index), cricketPower);
					track[indices.get(index)]=x;
					indices.remove(indices.get(index));
				}
				int vultureNo=r.nextInt((total/5)-(total/20))+(total/20)+1;
				for(int j=0;j<vultureNo;j++) {
					int index=r.nextInt(indices.size());
					Tile x=new Vulture(indices.get(index), vulturePower);
					track[indices.get(index)]=x;
					indices.remove(indices.get(index));
				}
				int trampolineNo=r.nextInt((total/5)-(total/20))+(total/20)+1;
				for(int j=0;j<trampolineNo;j++) {
					int index=r.nextInt(indices.size());
					Tile x=new Trampoline(indices.get(index), trampolinePower);
					track[indices.get(index)]=x;
					indices.remove(indices.get(index));
				}
				for(int i=0;i<indices.size();i++) {
					track[indices.get(i)]=new White(indices.get(i));
				}
				System.out.println("<Danger>: There are "+snakeNo+", "+cricketNo+", "+vultureNo+" numbers of Snakes, Cricket, and Vultures respectively on your track!\n<Danger>: Each Snake, Cricket, and Vultures can throw you back by "+snakePower+", "+cricketPower+", "+vulturePower+" number of Tiles respectively!\n<Good News>: There are "+trampolineNo+" number of Trampolines on your track!\n<Good News>: Each Trampoline can help you advance by "+trampolinePower+" number of Tiles!");
				temp=false;
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Please enter a non zero and non-negative nummber!");
			}
			catch(NumberFormatException e) {
				System.out.println("Please enter a valid number!");
			}
		}	
	}
	public static void tileAction(Tile t, Player x) {     // function for tile action
		try {
			t.affect(x);
		}
		catch(SnakeBiteException e) {
			System.out.println(e.getMessage());
		}
		catch(CricketBiteException e) {
			System.out.println(e.getMessage());
		}
		catch(VultureBiteException e) {
			System.out.println(e.getMessage());
		}
		catch(TrampolineException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void saveGame(Player x) throws IOException {
		
		if(x.getCurrentNumber()>=0.25*x.getTotalTiles()&&x.getCurrentNumber()<0.5*x.getTotalTiles())
			x.setSave1(x.getSave1()+1);
		else if(x.getCurrentNumber()>=0.5*x.getTotalTiles()&&x.getCurrentNumber()<0.75*x.getTotalTiles())
			x.setSave2(x.getSave2()+1);
		else if(x.getCurrentNumber()>=0.75*x.getTotalTiles()&&x.getCurrentNumber()<x.getTotalTiles())
			x.setSave3(x.getSave3()+1);
		if((x.getCurrentNumber()>=0.25*x.getTotalTiles()&&x.getCurrentNumber()<0.5*x.getTotalTiles()&&x.getSave1()==1)||(x.getCurrentNumber()>=0.5*x.getTotalTiles()&&x.getCurrentNumber()<0.75*x.getTotalTiles()&&x.getSave2()==1)||(x.getCurrentNumber()>=0.75*x.getTotalTiles()&&x.getCurrentNumber()<x.getTotalTiles()&&x.getSave3()==1)) {
			System.out.println("Do you want to save your progress and exit?(yes/no)");
			String option;
			do {
				option=s.nextLine();
				if(option.compareTo("yes")==0) {
					x.setRolls(rolls);
					serialize(x);
					System.exit(0);
				}
				else if(option.compareTo("no")==0)
					break;
				else 
					System.out.println("Wrong Input, Enter Again!");
			}
			while(option.compareTo("yes")!=0||option.compareTo("no")!=0);
		}
	}
	public static void deleteProfile(Player x) throws IOException {
		File f1=new File(x.getName()+".txt");
		if(f1.exists()) {
			f1.delete();
			players.remove(x.getName());
			File f2=new File("savedProfiles.txt");
			f2.delete();
			FileWriter f=new FileWriter("savedProfiles.txt",true);
			for(int i=0;i<players.size();i++)
				f.write(players.get(i)+"\n");
			f.close();
		}	
	}
	public static int rollDice() {         // function to roll dice
		rolls++;
		//s.nextLine();                 // For making the game user interactive
		return r.nextInt(6)+1;
	}
	public static void runGame(Player x) throws UnlimitedRollsException,IOException,GameWinnerException {    // function to run the game
		System.out.println("Game Started ========================>");
		while(x.getCurrentNumber()!=track.length-1) {
			int temp=0;
			if(x.getCurrentNumber()==1) {
				while(temp!=6) {
					temp=rollDice();
					if(rolls>rollsLimit) 
						throw new UnlimitedRollsException("This race can not be won in finite number of rolls!");
					if(temp==6)
						System.out.println("[Roll-"+rolls+"]: "+x.getName()+" rolled "+temp+" at Tile-"+x.getCurrentNumber()+". You are out of the cage! You get a free roll");
					else 
						System.out.println("[Roll-"+rolls+"]: "+x.getName()+" rolled "+temp+" at Tile-"+x.getCurrentNumber()+", Oops, you need 6 to start");
				}
				temp=rollDice();
				if(rolls>rollsLimit) 
					throw new UnlimitedRollsException("This race can not be won in finite number of rolls!");
				System.out.print("[Roll-"+rolls+"]: "+x.getName()+" rolled "+temp+" at Tile-"+x.getCurrentNumber()+",");
				x.move(temp);
				System.out.println(" landed on Tile "+x.getCurrentNumber());
			}
			else if(x.getCurrentNumber()==track.length-1) 
				break;
			else {
				System.out.println("Trying to shake the Tile-"+x.getCurrentNumber());
				tileAction(track[x.getCurrentNumber()],x);
				if(x.getCurrentNumber()==1) 
					System.out.println(x.getName()+" moved to Tile 1 as it can’t go back further");
				else {
					System.out.println(x.getName()+" moved to Tile "+x.getCurrentNumber());
					saveGame(x);
					if(x.getCurrentNumber()==track.length-1)
						break;
					else {
						temp=rollDice();
						if(rolls>rollsLimit) 
							throw new UnlimitedRollsException("This race can not be won in finite number of rolls!");
						if(x.getCurrentNumber()+temp>track.length-1) {
							while(x.getCurrentNumber()+temp>track.length-1) {
								System.out.println("[Roll-"+rolls+"]: "+x.getName()+" rolled "+temp+" at Tile-"+x.getCurrentNumber()+", landed on Tile "+x.getCurrentNumber());
								temp=rollDice();
								if(rolls>rollsLimit) 
									throw new UnlimitedRollsException("This race can not be won in finite number of rolls!");
							}	
						}
						System.out.print("[Roll-"+rolls+"]: "+x.getName()+" rolled "+temp+" at Tile-"+x.getCurrentNumber()+",");
						x.move(temp);
						System.out.println(" landed on Tile "+x.getCurrentNumber());
					}
				}	
			}
		}
		winner(x);
	}
	public static void startGame() throws IOException {       // function to start the game
		gameMenu();
		boolean temp1=true;
		while(temp1) {
			try {
				int ch;
				do {
					ch=Integer.parseInt(s.nextLine());
					if(ch==1) {                          //case for new game
						try {
							setTrack();
							System.out.println("Enter the Player Name");
							Player x=new Player(s.nextLine(),track.length-1,track,rollsLimit,rolls);
							System.out.println("Starting the game with "+x.getName()+" at Tile-1\nControl transferred to Computer for rolling the Dice for "+x.getName()+"\nHit enter to start the game");
							boolean tr=true;
							while(tr) {
								String temp=s.nextLine();
								if(temp.compareTo("")==0) {
									runGame(x);
									tr=false;
								}	
								else 
									System.out.println("Hit enter only!");
							}
						}
						catch(TrackSizeLimitException e) {
							System.out.println(e.getMessage());
							startGame();
						}
						catch(UnlimitedRollsException e) {
							System.out.println(e.getMessage());
							gameMenu();
						}
						catch(GameWinnerException e) {
							System.exit(0);
						}
					}
					else if(ch==2) {                   //case for loading saved game
						try {
							System.out.println("Enter the name of the Player");
							String name=s.nextLine();
							FileReader f3=new FileReader("savedProfiles.txt");
							BufferedReader br=new BufferedReader(f3);
							String x1;
							while((x1=br.readLine())!=null) {
								if(!players.contains(x1)) 
									players.add(x1);
							}	
							f3.close();
							if(players.contains(name)) {
								Player x=deserialize(name);
								track=x.getTrack().clone();
								rollsLimit=x.getRollsLimit();
								rolls=x.getRolls();
								System.out.println("Continuing the game with "+x.getName()+" at Tile-"+x.getCurrentNumber()+"\nControl transferred to Computer for rolling the Dice for "+x.getName()+"\nHit enter to continue the game");
								boolean tr=true;
								while(tr) {
									String temp=s.nextLine();
									if(temp.compareTo("")==0) {
										runGame(x);
										tr=false;
									}	
									else 
										System.out.println("Hit enter only!");
								}	
							}
							else
								System.out.println("No such player profile exists!");
						}
						catch(ClassNotFoundException e) {
							System.out.println("ClassNotFoundException is caught");
						}
						catch(UnlimitedRollsException e) {
							System.out.println(e.getMessage());
							gameMenu();
						}
						catch(GameWinnerException e) {
							System.exit(0);
						}
					}
					else if(ch==3)                  //case to exit
						break;
					else {
						System.out.println("Wrong Input, Enter Again!");
					}
				}
				while(ch!=3);
				temp1=false;
			}
			catch(NumberFormatException e) {
				System.out.println("Please enter a valid number!");
			}
		}	
	}
	public static void winner(Player x) throws GameWinnerException,IOException {
		System.out.println(x.getName()+" wins the race in "+rolls+" rolls!\nTotal Snake Bites = "+x.getSnakeBites()+"\nTotal Cricket Bites = "+x.getCricketBites()+"\nTotal Vulture Bites = "+x.getVultureBites()+"\nTotal Trampolines = "+x.getTrampolines());
		deleteProfile(x);
		throw new GameWinnerException("");
	}
	public static void gameMenu() {             //function to display game menu
		System.out.println("Welcome to the game!\n1) New Game\n2) Load Saved Game\n3) Exit");
	}
	public static void serialize(Player p) throws IOException {       //serialization
		ObjectOutputStream out=null;
		try {
			if(!players.contains(p.getName())) {
				players.add(p.getName());
				FileWriter f=new FileWriter("savedProfiles.txt",true);
				f.write(p.getName()+"\n");
				f.close();
			}	
			out=new ObjectOutputStream(new FileOutputStream(p.getName()+".txt"));
			out.writeObject(p);
		}
		finally {
			out.close();
		}
	}
	public static Player deserialize(String name) throws IOException,ClassNotFoundException {
		ObjectInputStream in=null;                            //deserialization
		try {
			in=new ObjectInputStream(new FileInputStream(name+".txt"));
			Player p=(Player)in.readObject();
			return p;
		}
		finally {
			in.close();
		}
	}
	public static void main(String[] args) throws IOException {    // main function
		File f1=new File("savedProfiles.txt");
		if(!f1.exists())
			f1.createNewFile();
		startGame();
	}
}