import processing.core.PApplet;
/**
* A code that creates a game where there is snow falling and the player has to avoid hitting the snow
* @author: Ethan Rodrigues
*/
public class Sketch extends PApplet {
  // create arrays for the falling snow
  float[] circleY = new float[10];
  float[] circleX = new float[10];
  boolean[] ballHideStatus = new boolean[10];
  
  // set speed for falling snow
  float circleSpeed = 1;

  // declare variables for the lives and location of the player
  float playerCircleX = 200;
  float playerCircleY = 200;
  int intLives = 3;

  public void settings() {
    // screen size
    size(400, 400);
  }

  public void setup() {
    // background colour
    background(212, 244, 255);

    // set locations for the snow within the height and width of the screen
    for (int i = 0; i < circleX.length; i++){
      circleY[i] = random(height);
      circleX[i] = random(width);
    }
  }

  public void draw() {
    // background colour
    background(212, 244, 255);

    // controls for changing the speed of the snow
    if (keyPressed){
      if (keyCode == UP){ 
        circleSpeed = (float)(circleSpeed - 0.1);
      }
      else if (keyCode == DOWN){
        circleSpeed = (float)(circleSpeed + 0.1);
      }
    }

    // draw player circle
    fill(36, 36, 201);
    ellipse(playerCircleX, playerCircleY, 10, 10);

    // controls for player movement
    if (keyPressed) {
      if (key == 'w') {
        playerCircleY -= 1.5;
      } 
      else if (key == 'a') {
        playerCircleX -= 1.5;
      } 
      else if(key == 's'){
        playerCircleY += 1.5;
      }
      else if(key == 'd'){
        playerCircleX += 1.5;
      }
   } 
       
    // draws circle for snow if ballhidestatus is off
    for (int i = 0; i < circleY.length; i++){
      if (ballHideStatus[i] == false){
        fill(255);
        ellipse(circleX[i], circleY[i], 30, 30);
        circleY[i] = circleY[i] + circleSpeed;
      }
      
      // makes snow be redrawn at the top of the screen when it reaches the bottom
      if (circleY[i] >= height){
        circleY[i] = 0;
      }
      
      // player looses a life if their circle touches the snow
      if ((playerCircleX <= circleX[i] + 20) && (playerCircleX >= circleX[i] - 20)){
        if((playerCircleY <= circleY[i] + 20) && (playerCircleY >= circleY[i] - 20)){
          intLives = intLives - 1;
          ballHideStatus[i] = true;
        }
      }
    
      // if the player clicks on the snow, the it will be hidden
      if (mousePressed){
        if ((mouseX <= circleX[i] + 20) && (mouseX >= circleX[i] - 20)){
          if((mouseY <= circleY[i] + 20) && (mouseY >= circleY[i] - 20)){
            ballHideStatus[i] = true;
          }
        }
      }
    }
    
    // draws squares in the top right of the screen based on the number of player lives
    if(intLives == 3){
      fill(240, 24, 0);
      rect(280, 10, 30, 30);
      rect(320, 10, 30, 30);
      rect(360, 10, 30, 30);
    
    }else if(intLives == 2){
      fill(240, 24, 0);
      rect(320, 10, 30, 30);
      rect(360, 10, 30, 30);
    
    }else if(intLives == 1){
      fill(240, 24, 0);
      rect(360, 10, 30, 30);

    }else if(intLives < 1){
      background(255);  
    }
  }
  
  // if the player clicks on the snow, the it will be hidden
  public void mousePressed() {
    for (int i = 0; i < circleY.length; i++){
      if ((mouseX <= circleX[i] + 20) && (mouseX >= circleX[i] - 20)){
        if((mouseY <= circleY[i] + 20) && (mouseY >= circleY[i] - 20)){
          ballHideStatus[i] = true;
        }
      }
    }
  }
}