import processing.core.PApplet;

public class Sketch extends PApplet {
  float[] circleY = new float[10];
  float[] circleX = new float[10];
  float circleSpeed = 1;
  boolean[] ballHideStatus = new boolean[10];

  float playerCircleX = 200;
  float playerCircleY = 350;
  int intLives = 3;

  public void settings() {
    size(400, 400);
  }

  public void setup() {
    background(212, 244, 255);
    for (int i = 0; i < circleX.length; i++){
      circleX[i] = random(width);
      circleY[i] = random(height);
    }
  }

  public void draw() {
    background(212, 244, 255);

    if (keyPressed){
      if (keyCode == UP && circleSpeed > 0.1){ 
        circleSpeed = (float)(circleSpeed - 0.1);
      }
      else if (keyCode == DOWN){
        circleSpeed = (float)(circleSpeed + 0.1);
      }
    }

    fill(36, 36, 201);
    ellipse(playerCircleX, playerCircleY, 10, 10);

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
       
    for (int i = 0; i < circleY.length; i++){
      if (ballHideStatus[i] == false){
        fill(255);
        ellipse(circleX[i], circleY[i], 30, 30);
        circleY[i] = circleY[i] + circleSpeed;
      }
      
      if (circleY[i] >= height){
        circleY[i] = 0;
      }
      
      if ((playerCircleX <= circleX[i] + 20) && (playerCircleX >= circleX[i] - 20)){
        if((playerCircleY <= circleY[i] + 20) && (playerCircleY >= circleY[i] - 20)){
          intLives = intLives - 1;
          ballHideStatus[i] = true;
        }
      }
    
      if (mousePressed){
        if ((mouseX <= circleX[i] + 20) && (mouseX >= circleX[i] - 20)){
          if((mouseY <= circleY[i] + 20) && (mouseY >= circleY[i] - 20)){
            ballHideStatus[i] = true;
          }
        }
      }
    }
    
    if(intLives == 3){
      fill(240, 0, 24);
      rect(280, 10, 30, 30);
      rect(320, 10, 30, 30);
      rect(360, 10, 30, 30);
    
    }else if(intLives == 2){
      fill(240, 100, 100);
      rect(320, 10, 30, 30);
      rect(360, 10, 30, 30);
    
    }else if(intLives == 1){
      fill(240, 200, 200);
      rect(360, 10, 30, 30);

    }else if(intLives < 1){
      background(255);  
    }
  }
  
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