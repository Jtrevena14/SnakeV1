import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;
public class Snakev2{
	public static void main(String [] args){
		Random rand = new Random();
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		HashMap<Integer,String> map2 = new HashMap<Integer,String>();
		Scanner keyboard = new Scanner(System.in);
		int board[][] = new int[5][5];
		String[] k = new String[2];
		int x=board.length/2,y=board.length/2,counter=1,counter2=counter,length=0,score=0;
		int xdot=rand.nextInt(0,board.length),ydot=rand.nextInt(0,board.length);
		board[y][x]=1;
		board[ydot][xdot]=5;
		map.put(0,y+","+x);
		while(true){
			String a = keyboard.nextLine();
			switch(a){
				case "w":
					y--;
					break;
				case "s":
					y++;
					break;
				case "a":
					x--;
					break;
				case "d":
					x++;
					break;
				case "":
					if(map2.get(counter2-1).equals("w")){
						y--;
					}
					else if(map2.get(counter2-1).equals("s")){
						y++;
					}
					else if(map2.get(counter2-1).equals("a")){
						x--;
					}
					else if(map2.get(counter2-1).equals("d")){
						x++;
					}
					break;
			}

			if(board[y][x]==1){
				System.out.println("Game Over!");
				break;
			}

			if(board[y][x]==5){
				board[ydot][xdot]=0;
				int h = 0;
				while(board[ydot][xdot]!=0 || (ydot == y && xdot == x) || (ydot == Integer.parseInt(k[0]) && xdot == Integer.parseInt(k[1]))){
					ydot = rand.nextInt(0,board.length);
					xdot = rand.nextInt(0,board.length);
					h++;
					if(h>10){
						System.out.println("You WIN!");
						break;
					}
				}
				if(h>10){
					break;
				}
				board[ydot][xdot]=5;
				length--;
				score++;
			}

			board[y][x]=1;
			map.put(counter,y+","+x);
			counter++;
			map2.put(counter2,a);

			if(!a.equals("")){
				counter2++;
			}

			for(int i = 0; i<board.length;i++){
				for(int j = 0; j<board[0].length;j++){
					System.out.print(board[i][j]+ " " );
				}
				System.out.println();
			}

			System.out.println(score);

			if(counter>6 && (a.equals("") || a.equals("w") || a.equals("s") || a.equals("a") || a.equals("d"))){
				k = map.get(length).split(",");
				board[Integer.parseInt(k[0])][Integer.parseInt(k[1])]=0;
				length++;
			}
		}
	}
}