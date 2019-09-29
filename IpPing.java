

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class IpPing {
	public static double pingIp(String host) {
		try {
			String command="ping -c5 "+host;
			//|awk '{print substr($8, 6, length($8))}'
			Process p =Runtime.getRuntime().exec(command);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			input.readLine();
			String str;
			int count=0;
			double pingTime[]= new double[5];
			while(count<5) {  
				str=input.readLine();
				pingTime[count]=Double.parseDouble( str.substring(str.indexOf("time=")+5, str.indexOf("ms")-1));
//            	System.out.println(pingTime[count]);
            	count++;
			}
			Arrays.sort(pingTime);
			return pingTime[2];
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return 0.0;
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String host = sc.nextLine();
		System.out.println("Median of the time taken to ping "+host+" is "+pingIp(host)+" ms");
	}

}
