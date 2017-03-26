
import java.util.*;
class Util
{
	public static String GenPattern()
	{
		return "";
	}

	
	public static String ByteToString(byte[] bt)
	{
		String ret="";
		char chr;
		for(int i=0;i<=bt.length-1;i++)
		{
			ret=ret+Character.toString((char)bt[i]);
		}
		return ret;
	}



	public static String Decypher(byte[] ClientConnect,String Username)
	{
		Users mUsers=new Users(Username);
				
		byte[] Pattern=StrToByteArr(mUsers.GetPattern());
	
				
		
		//String newPattern=new String("");
		String Decyphered=ByteToString(XorPattern(ClientConnect,Pattern));
        
		//String Decyphered=new String("");
		return Decyphered;

	}

	public static String DecypherSupporter(byte[] ClientConnect,String Username)
	{
//		Users mUsers=new Users(Username);
		Supporter mSupporter=new Supporter(Username);
		String mPattern=mSupporter.GetPattern();
				
		byte[] Pattern=StrToByteArr(mPattern);
	
				
		
		//String newPattern=new String("");
		String Decyphered=ByteToString(XorPattern(ClientConnect,Pattern));
        
		//String Decyphered=new String("");
		return Decyphered;

	}	
	
	private static int[] StringToIntArr(String Strng)
	{
		int Ret[]=new int[Strng.length()];
		for(int i=0;i<=Strng.length()-1;i++)
		{
			Ret[i]=Strng.charAt(i);
		}
		return Ret;
	}
	
	

	public static byte[] XorPattern(byte[] str1,byte[] str2)	
	{
		
		String dsf="";
		for(int i=0;i<str1.length;i++)
		{
			dsf=dsf+Character.toString((char)str1[i]);
		}
		System.out.println("A");
		System.out.println(dsf);
		
		/*
		int[] ToCypherInt=new int[str1.length()];
		ToCypherInt=StringToIntArr(str1);
		int[] PatternInt=new int[str2.length()];
		PatternInt=StringToIntArr(str2);

				
		

		int to=str1.length()<str2.length()?str1.length():str2.length();
		int DecypheredInt[]=new int[to];

		for(int i=0;i<=to-1;i++)
			DecypheredInt[i]=ToCypherInt[i]^PatternInt[i];
					

		String Decyphered=new String("");
			
		for(int i=0;i<=to-1;i++)
			Decyphered=new StringBuilder(Decyphered).append(Character.toString((char)DecypheredInt[i])).toString();

		return Decyphered;
		*/
		
		int to=str1.length<str2.length?str1.length:str2.length;
		
		byte[] ret=new byte[to];
		int erg,z1,z2;
		for(int i=0;i<to;i++)
		{
			z1=(int)str1[i];
			z2=(int)str2[i];
			erg=z1^z2;			
			ret[i]=(byte)erg;
			System.out.println(Character.toString((char)(z1)));
		}
		
		return ret;
		
	}

	public static byte[] Crypt(String UserDaten,byte[] MyPattern)
	{
		//byte[] newPattern=XorPattern(Pattern,MyPattern);	
		byte[] CryptedText=XorPattern(StrToByteArr(UserDaten),MyPattern);				
		
		return CryptedText;
	}
	
	public static byte[] StrToByteArr(String str)
	{
		byte[] ret=new byte[str.length()];
		int high=0xFF00;
		int low=0xFF;
		
		int z=0;
		for(int i=0;i<=str.length()-1;i++)
		{
			ret[i]=(byte)str.charAt(i);			
		}
		return ret;
	}
	
	public static int[] ByteToInt(byte[] Input)
	{
		int[] ret=new int[Input.length/2];
		int z=0;
		for(int i=0;i<Input.length;i+=2)
		{
			ret[z]=Input[i];
			ret[z]<<=8;
			ret[z]&=Input[i+1];
			z++;
		}
		return ret;
	}
	
	public static String IntArrToString(int[] In)
	{
		String ret="";
		for(int i=0;i<In.length;i++)
		{
			ret=ret+Character.toString((char)In[i]);
		}
		return ret;
	}

}	