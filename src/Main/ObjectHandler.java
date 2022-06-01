package Main;

import objects.*;

public class ObjectHandler {

    GamePanel gp;

    public ObjectHandler(GamePanel gp) { // Constructor

        this.gp = gp;

    }

    public void setObject() { // Sets objects and their parameters to indexes in the obj array for recall

        gp.obj[0] = new object_Door_Closed_In();
        gp.obj[0].worldX = 11 * gp.tileSize;
        gp.obj[0].worldY = 19 * gp.tileSize;

        gp.obj[1] = new object_Chest_Closed();
        gp.obj[1].worldX = 2 * gp.tileSize;
        gp.obj[1].worldY = 2 * gp.tileSize;

        gp.obj[2] = new object_Chest_Open();
        gp.obj[2].worldX = 3 * gp.tileSize;
        gp.obj[2].worldY = 2 * gp.tileSize;

        gp.obj[3] = new object_Key_1();
        gp.obj[3].worldX = 4 * gp.tileSize;
        gp.obj[3].worldY = 2 * gp.tileSize;

        gp.obj[4] = new object_Ladder_Up();
        gp.obj[4].worldX = 5 * gp.tileSize;
        gp.obj[4].worldY = 2 * gp.tileSize;

        gp.obj[5] = new object_Ladder_Down();
        gp.obj[5].worldX = 6 * gp.tileSize;
        gp.obj[5].worldY = 2 * gp.tileSize;

        gp.obj[6] = new object_Lock_Locked();
        gp.obj[6].worldX = 7 * gp.tileSize;
        gp.obj[6].worldY = 2 * gp.tileSize;

    }
}