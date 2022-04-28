package Main;



import Main.GamePanel.Gamestate;

public class EventHandler {
    
    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX,previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp)
    {
        this.gp =gp;
        eventRect = new  EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventReactDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventReactDefaultY = eventRect[col][row].y;
            col++;
            if(col == gp.maxWorldCol)
            {
                col =0;
                row++;
            }
        }

    }

    public void ChackEvent()
    {
        // Check if player is more then one time
        int XDistance = Math.abs(gp.player.worldX - previousEventX); 
        int YDistance = Math.abs(gp.player.worldY - previousEventY); 
        int Distance = Math.max(XDistance, YDistance);
        if(Distance > gp.tileSize)
        {
            canTouchEvent = true;
        }

        if(!canTouchEvent) return;

        if(hit(20, 30, "any")) damgePit(20,30,Gamestate.dialogue);
       // if(hit(20, 30, "any")) teleport(Gamestate.dialogue);
        if(hit(24, 30, "any")) HealingPool(24,30,Gamestate.dialogue);
    }

    public boolean hit(int col , int row ,String reqDirection)
    {
        boolean  hit = false;
        
        gp.player.soidArea.x = gp.player.worldX + gp.player.soidArea.x;
        gp.player.soidArea.y = gp.player.worldY + gp.player.soidArea.y;
        
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x; 
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if(gp.player.soidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone)
        {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))
            {
                hit = true;
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }
        gp.player.soidArea.x = gp.player.SolidAreaDefaultX;
        gp.player.soidArea.y = gp.player.SolidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventReactDefaultX; 
        eventRect[col][row].y = eventRect[col][row].eventReactDefaultY;

        return hit;
    }


    public void damgePit(int col , int row,Gamestate gamestate)
    {
        gp.gamestate = gamestate;
        gp.ui.CurrentDialogue = "you fall into a pit";
        gp.player.life -=1;
        canTouchEvent = false;
       // teleport(col,row,gamestate);
       // eventRect[col][row].eventDone = true;
    }

    public void teleport(int col , int row,Gamestate gamestate){
        gp.gamestate = gamestate;
        gp.ui.CurrentDialogue =" you teleport to the start";
        gp.player.worldX = gp.tileSize * 24;
        gp.player.worldY = gp.tileSize * 10;
    }

    public void  HealingPool(int col , int row,Gamestate gamestate)
    {
            if(gp.keyH.spacePressed){
                gp.player.attackCancel = true;
                gp.gamestate = gamestate;
                gp.ui.CurrentDialogue = "you drink the water \nyour life is been recover";
                gp.player.life =  gp.player.MaxLife;

            }
            
    }

}
