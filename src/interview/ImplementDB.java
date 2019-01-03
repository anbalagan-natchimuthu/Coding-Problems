package interview;

/*implement database structure in Java (that would be multithreaded) 

I was at a job interview and the intreviewer gave me a task: I have an object called DBconn. that Object has 4 methods: 

� connect(Params) 

� query(String) 

� update (String) 

� disconnect() 

My mission was to implement that Database object (DBconn) in a singelton pattern - meaning the first person who create this object will get a new instance 
of it and from there one - every person will get that instance of the databse (not to create a new instance for every user - but share the same object). 

I first tried to us the standard way of singelton where I create a DBconn object in the first time that a user called it - and return the instance from 
there on. The problem was that the connect method should only run for the first time (meaning user1 connect, and then user 2 also connect - is not valid) 
and if I return the databse every user that want to harm to system can conncect or diconnect as he please. 

So i decided to return the singelton class (instead of the database object), and do the "connect" only for the first user. But then I encounter a diffrente 
problem: the query and update methods of the DBconn should work in multythread way (meaning they are synchornized - according to DBconn implemetion). 
But if I write them in a synchronized java method that will force the program to excute each of them speratly (because of the "synchronized" saved word) 
and that will ignore the beauty multythreaded way that "update" and "query" work. 

So he gave me a homework task (which ovcourse inclueded using the internet and counslting) - to try and solve this problem by writing it in java. 

this is what I did so far (I also tried to implememts a very raw database which saves persons data) but I still don't know how to make the update and query to:
 1. work in a muiltythread way. 
 2. work in a muiltythread way in the singelton class as well 

*/

import java.util.HashMap;
import java.util.Map;

class DBconnImpl {

	private Map<String, Table> tables;
	private boolean connected;

	private String username;
	private String password;

	public DBconnImpl() {
		tables = new HashMap<>();
		this.username = "MyAccount";
		this.password = "123";
		connected = false;
	}

	public boolean connect(String username, String password) throws Exception {
		if (connected) {
			throw (new Exception("Error: The database is already opened."));
		}
		if (!this.username.equals(username) || !this.password.equals(password)) {
			System.out.println("wrong username or password.");
			return false;
		}
		connected = true;
		return true;
	}

	public void create(String tableName) throws Exception {
		if (!connected) {
			throw (new Exception("Error: The database is not opened."));
		}
		tables.put(tableName, new Table(tableName));
	}

	public User query(String tableName, int rowID) throws Exception {
		if (!connected) {
			throw (new Exception("Error: The database is not opened."));
		}
		return (tables.get(tableName).query(rowID));
	}

	public void update(String tableName, User user) throws Exception {
		if (!connected) {
			throw (new Exception("Error: The database is not opened."));
		}
		tables.get(tableName).update(user);
	}

	public boolean disconnect() throws Exception {
		if (!connected) {
			throw (new Exception("Error: The database is not opened."));
		}
		connected = false;
		return true;
	}

}


class Table {

	private Map<Integer, User> table;

	public Table(String tableName) {
		table = new HashMap<>();
	}

	public User query(int id) throws Exception {
		return table.get(id);
	}

	public void update(User user) throws Exception {
		table.put(user.getId(), user);
		System.out.println("The user " + user.getName()
				+ " was successfully entered in the with ID number "
				+ user.getId() + ".");

	}

}


class User {
	private String name;
	private int id;
	private int age;

	public User(int id, String name, int age) {
		this.name = name;
		this.age = age;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return ("(" + name + " , " + age + ")");
	}
}


class UserContorller {
	SingeltonDB db;

	public UserContorller(String user, String pass) throws Exception {
		if (!SingeltonDB.isOpen()) {
			db = SingeltonDB.getInstance(user, pass);

		} else {
			System.out.println("dataBase already opened");
		}
	}

	public UserContorller() throws Exception {
		if (SingeltonDB.isOpen()) {
			db = SingeltonDB.getInstance();
		} else {
			System.out.println("dataBase not opened");
		}
	}

	public void createTable(String table) throws Exception {
		db.create(table);
	}

	public void saveUser(String table, int id, String name, int age)
			throws Exception {
		db.update(table, new User(id, name, age));
	}

	public User getUser(String table, int id) throws Exception {
		return db.query(table, id);
	}

}

class SingeltonDB {
	private static DBconnImpl db = null;
	private static SingeltonDB singalDb = null;

	private SingeltonDB(String username, String password) {
		db = new DBconnImpl();
	}

	public static boolean isOpen() {
		return (db != null);
	}

	public synchronized static SingeltonDB getInstance(String username,
			String password) throws Exception {
		if (db != null) {
		} else {
			System.out.println("The database is now open");
			singalDb = new SingeltonDB(username, password);
		}
		db.connect(username, password);
		System.out.println("The database was connected");
		return singalDb;
	}

	public synchronized static SingeltonDB getInstance() throws Exception {
		if (db == null) {
			throw (new Exception("The database is not open"));
		}

		return singalDb;
	}

	public void create(String tableName) throws Exception {
		db.create(tableName);
	}

	public User query(String tableName, int rowID) throws Exception {
		if (db == null) {
			System.out.println("Error: the database is not open");
			return null;
		}
		return (db.query(tableName, rowID));
	}

	public void update(String tableName, User user) throws Exception {
		if (db == null) {
			System.out.println("Error: the database is not open");
			return;
		}
		db.update(tableName, user);
	}

}

public class ImplementDB {
	public static void main(String[] args) throws Exception {

		UserContorller uc1 = new UserContorller("MyAccount", "123");
		UserContorller uc2 = new UserContorller();
		UserContorller uc3 = new UserContorller();

		uc1.createTable("table1");
		uc2.saveUser("table1", 1, "David", 10);
		System.out.println(uc3.getUser("table1", 1));

	}
}
