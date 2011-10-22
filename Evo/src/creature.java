import java.util.Random;


public class creature {
	float sight = 2; //0-5
	float cooperation = 4;  //
	float food = 0; 
	float fertility = 2; // 0-5
	float movementSpeed= 4; //inverse min 1
	float gatheringSpeed = 4; // 0-8
	int x = 0;
	int y= 0;
	//path finding
	
	//DIRECTIONS
	public static final int up = 0;
	public static final int right = 1;
	public static final int down = 2;
	public static final int left = 3;
	
	creature(int xi, int yi){
		x = xi;
		y = yi;
	}
	
	creature(){		
	}

	public void move(int direction){
		main.map[this.x][this.y].removeCreature(this);
		if(direction == up && this.y>0){
			this.y--;
		}
		if(direction == down && this.y<main.size-1){
			this.y++;
		}
		if(direction == left && this.x>0){
			this.x--;
		}
		if(direction == right && this.x<main.size-1) {
			this.x++;
		}
		main.map[this.x][this.y].setCreature(this);
	}

   public void eat(){
      place loc = main.map[x][y];

      float foodRemain = loc.food;
      if(loc.food >= gatheringSpeed/4){
         this.food += gatheringSpeed/4;
         loc.food -= gatheringSpeed/4;
      }else{
         this.food = loc.food;
         loc.food = 0;
      }
      
   }
	
	public creature mateWith(creature mate){
		Random c = new Random();
		creature baby = new creature(mate.x, mate.y);
		float nf = (this.fertility + mate.fertility)/2 + c.nextFloat()*norp();
		if (nf> 5){
			baby.fertility = 5;
		}else if( nf<0){
			baby.fertility= 0;
		}else{
			baby.fertility = nf;
		}
		
		float ns = (this.sight + mate.sight)/2 + c.nextFloat()*norp();
		if (ns> 5){
			baby.sight = 5;
		}else if(ns<0){
			baby.sight = 0;
		}else
			baby.sight = ns;
		
		float nc = (this.cooperation + mate.cooperation)/2 + c.nextFloat()*norp();
		baby.cooperation = nc;
		
		float nms = (this.movementSpeed + mate.movementSpeed)/2 +c.nextFloat()*norp();
		if (nms <1){
			baby.movementSpeed = 1;
		}else{
			baby.movementSpeed = nms;
		}
		
		float ngs = (this.gatheringSpeed + mate.gatheringSpeed)/2 + c.nextFloat()*norp();
		if (ngs<0){
			baby.gatheringSpeed = 0;
		} else if(ngs>8){
			baby.gatheringSpeed = 8;
		} else{
			baby.gatheringSpeed = ngs;
		}
		
		return baby;
	}
	
	public int norp(){
		Random c = new Random();
		int n = 1;
		if (c.nextInt(1) == 1){
			n = -1;
		}
		return n;
	}
}
