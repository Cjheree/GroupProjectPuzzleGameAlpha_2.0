package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    private boolean standing;

    public final int screenX; // X coordinate of the camera in relation to the player/world
    public final int screenY; // Y coordinate of the camera in relation to the player/world

    private int keyCount = 0; // ** This data structure may need to change depending on game loop **

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp; // Sets game panel to current game panel loaded
        this.keyH = keyH; // Sets keyHandler to follow main package keyHandler

        screenX = (gp.screenWidth/2) - (gp.tileSize/2); // Returns halfway point of screen in terms of width
        screenY = (gp.screenHeight/2) - (gp.tileSize/2); // Returns halfway point of screen in terms of height

        solidArea = new Rectangle(12, 20, 40, 40); // Creation of hit box

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues(); // Sets Default Values
        getPlayerImage();



    }

    public void setDefaultValues() { // Sets default values of entity

        worldX = gp.tileSize * 11 ; // X coordinate on world map
        worldY = gp.tileSize * 11; // Y coordinate on world map
        speed = 4; // Pixels traversed per iteration of FPS (1/FPS seconds)
        direction = "down"; // Player starts facing down
    }

    public void getPlayerImage() { // Retrieves image associated with player state (direction)

        try {
            /*
            Each variable we are assigning here is already declared as a buffered image
            We are retrieving the image through the given file path for each cycle/direction state
            ImageIO reads from the data stream and assigns the image data retrieved to our declared variable
            getClass() retrieves from resource folder that getClassLoader() loads
             */

            up = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Standing_Up.png"));
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Up_2.png"));
            down = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Standing_Down.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Down_2.png"));
            left = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Standing_Left.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Left_2.png"));
            right = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Standing_Right.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Walking_Right_2.png"));

        } catch (IOException e) { // Handles errors related to image/path not found
            e.printStackTrace();
        }
    }

    public void update() { // Player specific update() for player specific variables

         /*Location Information
         The screen's (0,0) coordinates are in the top left corner

         Y values increase as the location of the pixel moves further down the screen
         Y values decrease as the location of the pixel moves further up the screen

         X values increase as the location of the pixel moves further to the right on the screen
         X values decrease as the location of the pixel moves further to the left of the screen
        */

        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            standing = false;

            if (keyH.upPressed == true) { // Sets direction
                direction = "up";

            } else if (keyH.downPressed == true) { // Sets direction
                direction = "down";

            } else if (keyH.leftPressed == true) { // Sets direction
                direction = "left";

            } else if (keyH.rightPressed == true) { // Sets direction
                direction = "right";

            }

            collisionState = false;
            gp.cChecker.checkTile(this); // Passing this player entity to determine collision

            int objIndex = gp.cChecker.checkObject(this, true);
            objectInteraction(objIndex);

            if (collisionState == false) { // If collision is not detected, this moves player according to direction
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > (gp.FPS/8)) { // Switches walking cycle 8 times every second / every designated number of frames
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else { // Sets standing when no movement (input) is detected
            standing = true;
        }
    }

    public void objectInteraction(int i) {

        if (i != 999) {

            // Possible Future Usage Code
            // gp.obj[i] = null; // This just deletes the object!

            String objName = gp.obj[i].name;

            switch (objName) {
                case "Key_1":
                    keyCount++; // Adds keys to player's "inventory"
                    gp.obj[i] = null; // This just deletes the object!
                    break;
                case "Door_Closed_In":
                    if(keyCount > 0) {
                        gp.obj[i] = null; // This just deletes the object!
                        keyCount--; // Removes said key from player's "inventory"
                    }
                    System.out.println("keyCount: " + keyCount);
                    break;

            }
        }


    }

    public void draw(Graphics2D g2) { // Player specific draw() for player specific drawings

        BufferedImage image = null; // Declaration of BufferedImage image

        switch(direction) { // Used a switch since we are just checking to see if it changed. "If" statement too long
                            // spriteNum alternates between one and two. Changing the images to simulate walking
            case "up": // Sets image to corresponding walking positions
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                if(standing) { // Returns to standing image
                    image = up;
                }
                break;
            case "down": // Sets image to corresponding walking positions
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                if(standing) { // Returns to standing image
                    image = down;
                }
                break;
            case "left": // Sets image to corresponding walking positions
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                if(standing) { // Returns to standing image
                    image = left;
                }
                break;
            case "right": // Sets image to corresponding walking positions
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                if(standing) { // Returns to standing image
                    image = right;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }

}