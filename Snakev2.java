import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;
public class Snakev2{
	public static void main(String [] args){
		Random rand = new Random();
		HashMap<Integer,String> map = new HashMap<Integer,String>();  //This Hashmap keeps track of the coordinates that the snake has already gone over.
		HashMap<Integer,String> map2 = new HashMap<Integer,String>(); //This hashmap keeps track of the keys that are pressed, IE, (1,"w"), (2,"w"), (3,"s"), (4,"d"), (5,"d")
		Scanner keyboard = new Scanner(System.in);
		int board[][] = new int[8][8];
		String[] k = new String[2];  //This will be used later to break appart the coordiates when erasing the back of the snake.
		int x=board.length/2,y=board.length/2,counter=1,counter2=counter,length=0,score=0; //These varibales keep track of the x and y coordinates of the snake
		int xdot=rand.nextInt(0,board.length),ydot=rand.nextInt(0,board.length);
		board[y][x]=1;   //First position will put a one in that spot.
		board[ydot][xdot]=5;
		map.put(0,y+","+x);  //As the starting position put a 0, and the starting x and y coordinates, this is why counter starts at 1.
		while(true){
			String a = keyboard.nextLine();  //Enter w a s d or "" (nothing) in a switch statement everytime the while loop repeats
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
				case "":                          //if the user enters nothing ie "", find the last key that was pressed, using one of the hashmaps, and repeat that key until the user enters a different key.
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

			if(board[y][x]==1){                  //If the user goes over a 1, essentially when the snake goes over itself the game ends.
				System.out.println("Game Over!");
				break;
			}

			if(board[y][x]==5){               //If the user collects a 5, set the 5 to a 0, use random integers to make another 5 elsewhere 
				board[ydot][xdot]=0;	  //and while loop to check if the 5 is able to go anywhere that does not contain a one cause that is where the snake is. 
				int h = 0;
				while(board[ydot][xdot]!=0 || (ydot == y && xdot == x) || (ydot == Integer.parseInt(k[0]) && xdot == Integer.parseInt(k[1]))){  //While the random number gives coords to a spot that is not where the snake is or one a square that the snake is going to.
					ydot = rand.nextInt(0,board.length);
					xdot = rand.nextInt(0,board.length);
					h++;            //Attempt to put a 5 somewhere on the board checking random numbers.
					if(h>10){      //if there are no spots that satisfy the conditions after 10 attemps, display the win as there are no other places a 5 could be placed.
						System.out.println("You WIN!");
						break;
					}
				}
				if(h>10){
					break;
				}
				board[ydot][xdot]=5; //If there is an avaliable spot for a 5, put it there.
				length--; //After 5 is collected the length is delayed by one, giving the snake an extra tile length.
				score++; //Increase score.
			}

			board[y][x]=1; //If the snake goes onto a tile with a 0, turn it into a one.
			map.put(counter,y+","+x); //put those coordinates the snake just went over into a hashmap along with an iterator number (IE counter variable).
			counter++;
			map2.put(counter2,a); //Put the key the user pressed into a hashmap with its own number unique to the key.

			if(!a.equals("")){ //If the user enters nothing stop increasing the counter2, meaning it will delay hashmap to always have a key to enter when user spams the ""
				counter2++;  //for example, if the user enters a "w" and then 5 "" nulls, the program will only use the w that was pressed because it was the last real key that was pressed.
			}

			for(int i = 0; i<board.length;i++){
				for(int j = 0; j<board[0].length;j++){
					System.out.print(board[i][j]+ " " ); //This is the main display of the board, just print every line of the board in a nice fashion with spaces in between.
				}
				System.out.println();
			}

			System.out.println(score); //The score at the bottom.
															//THIS IS THE HARD PART!!!! \/\/
			if(counter>2 && (a.equals("") || a.equals("w") || a.equals("s") || a.equals("a") || a.equals("d"))){ //After 2 moves, if the user enters any of the 5 options 
				k = map.get(length).split(",");                  //k has only 2 slots to put values in, split appart the coordinates by the comma, "," that originally is a String stored like such, (1,"6,5"), (2,"3,6")...
				board[Integer.parseInt(k[0])][Integer.parseInt(k[1])]=0; //These values will be delayed by about 2 spots, so when we start taking these values and converting it to a zero it will be about 2 spots behind the true position of the front of the snake, giving the erasing illusion.
				length++;  //when counter>2 length will start counting up, counter variable and length variable will always be more than 2 away from each other.
			}
		}
	}
}
