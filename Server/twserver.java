import java.net.*;import java.io.*;import java.sql.*;class twserver extends java.lang.Thread{	public void run()	{		do		{			try			{				disconnectInactive();				disconnectLogout();				connectNewSession();			}catch(Exception e){}			try{twserver.sleep(1000L);	}catch(Exception e){}					}while(true);	}		public void disconnectInactive()	{		Socket echoSocket = null;		PrintWriter out = null;		String[][] Blubb=null;		Sessions mSession=new Sessions();				Blubb=mSession.CheckInactive();				for(int i=0;i<Blubb.length-1;i++)			{			try			{				if((Blubb[i][0].compareTo("")!=0) && (Blubb[i][1].compareTo("")!=0))				{						String provider=Blubb[i][2];						Betreiber b=new Betreiber(provider);						String bPass=b.getPassword();								ClientConThread cct=new ClientConThread(Blubb[i][0],Blubb[i][1],"D",bPass);								cct.start();															}			}catch(Exception e){e.printStackTrace();}			}	}		public void disconnectLogout()	{		ResultSet rs=null;		ResultSet rs1=null;		String Username=null;		String IP=null;		String IPRouter=null;		Socket echoSocket = null;		String ID=null;		PrintWriter out = null;		DBQuery query=new DBQuery();				boolean weiter;				try{			rs=query.DoQuery("SELECT * FROM Logout");					if(rs.first())			{				do				{					try					{								Username=rs.getString("Username");						rs1=query.DoQuery("SELECT * FROM Sessions WHERE Username='"+Username+"'");						if(rs1.first())						{							IP=rs1.getString("IP");							IPRouter=rs1.getString("RouterIP");							String Provider=rs1.getString("Provider");														try{								query.DoQuery("DELETE FROM Sessions WHERE Username='"+rs.getString("Username")+"'");													}catch(Exception e){}													Betreiber b=new Betreiber(Provider);							String bPass=b.getPassword();								System.out.println("X");						System.out.println("X");						System.out.println("X");						System.out.println("X");						System.out.println("X");							ClientConThread cct=new ClientConThread(IP,IPRouter,"D",bPass);							cct.start();							System.out.println("Y");							System.out.println("Y");							System.out.println("Y");							System.out.println("Y");													}						query.DoQuery("DELETE FROM Logout WHERE Username='"+rs.getString("Username")+"'");					}catch(Exception e){System.out.println("Exception DisconnectLogout:");e.printStackTrace();}											}while(rs.next());			}		}catch(Exception e){System.out.println("SQL Exception DisconnectLogout:");}	}			public void connectNewSession()	{		ResultSet rs=null;		ResultSet rs1=null;		String Username=null;		String IP=null;		String IPRouter=null;		Socket echoSocket = null;		String ID=null;		PrintWriter out = null;		DBQuery query=new DBQuery();				boolean weiter=true;				rs=query.DoQuery("SELECT * FROM NewSession");		if(rs!=null)		{			try{				rs.first();								do				{									    try{							IPRouter=rs.getString("RouterIP");							IP=rs.getString("IP");														String provider=rs.getString("Provider");							query.DoQuery("DELETE FROM NewSession WHERE ID='"+rs.getString("ID")+"'");														Betreiber b=new Betreiber(provider);							String bPass=b.getPassword();									System.out.println("Pass:"+bPass);							System.out.println("PRov:"+provider);							ClientConThread cct=new ClientConThread(IP,IPRouter,"A",bPass);							cct.start();							}catch(Exception e){}				}while(rs.next());					}catch(Exception e){}																		weiter=false;			//try{weiter=rs.next();}catch(Exception e){}		}	}}