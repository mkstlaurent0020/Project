public class Player
{
   //Instantiations.
   int posY = 0;
   int posX = 0;

   //Constructor.
   public Player()
   {
      posY = 0;
      posX = 0; 
   }
   
   //Set Method.
   public void setPlayerPos(int startY, int startX)
   {
      this.posY = startY;
      this.posX = startX;
   }
}