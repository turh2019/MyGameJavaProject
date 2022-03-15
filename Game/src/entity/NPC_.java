package entity;

import Main.GamePanel;

public class NPC_ extends Entity{
    
    public NPC_(GamePanel gp){
        super(gp);
    }

    public int MaxDialog = 0;
    public String Dialogues[] = new String[20];
    public int CurrentDialogues = 0; 

    public void SetDialogues() { }

    public void Speak()
    {
        if(Dialogues[CurrentDialogues] == null)
        {
            CurrentDialogues = 0;
        }
        gp.ui.CurrentDialogue = Dialogues[CurrentDialogues];
        CurrentDialogues++;


        switch(gp.player.direction){
            case "up": direction = "down"; break;
            case "down": direction = "up";break;
            case "left":direction = "right";break;
            case "right":direction = "left";break;   
        }
    }
}
