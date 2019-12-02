/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PingPong;

/**
 *
 * @author Win 8.1 Version 2
 */
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * A rectangle that can be moved around.
 * @author Mussie Habtemichael
 * @version 6 December 2015
 */
public class RectangleShape extends AbstractSceneShape
{
   /**
    * Construct a rectangle item.
    * @param x the left of the bounding rectangle
    * @param y the top of the bounding rectangle
    * @param width the width of the bounding rectangle
    * @param height the height of the bounding rectangle
    */
   public RectangleShape(int x, int y, int width, int height)
   {
      super(x, y, width, height);
   }

   /**
    * Draws the shape
    * @param g2 the graphics context
    */
   public void draw(Graphics2D g2)
   {
      Rectangle2D.Double aRectangle 
         = new Rectangle2D.Double(super.getXPosition(), super.getYPosition(),
               super.getWidth(), super.getHeight());
      
      g2.setColor(super.getBodyColor());
      g2.fill(aRectangle);
      g2.draw(aRectangle);
   }
   
   /**
    * Draws the "Game over" and "Game Score" texts
    * @param g2 the graphics context
    */
   public void drawGameOver(Graphics2D g2)
   {
      g2.setFont(new Font("default", Font.BOLD, 40));
      g2.drawString("Game Over", super.getXPosition() + 250, 
            super.getYPosition() + 202);
      g2.setFont(new Font("default", Font.BOLD, 20));
      g2.drawString("Game Score: " + score, super.getXPosition() + 500, 
            super.getYPosition() + 40);
   }
   
   /**
    * Sets the game score
    * @param the score to set
    */
   public void setHighScore(int aScore)
   {
      score = aScore;
   }
   
   /**
    * Tests whether this item contains a given point.
    * @param p a point
    * @return true if this item contains p
    */
   public boolean contains(Point2D p)
   {
      return super.getXPosition() <= p.getX() && p.getX() <= 
            super.getXPosition() + super.getWidth() 
         && super.getYPosition() <= p.getY() && p.getY() <= 
         super.getYPosition() + 
         SQUARE_BODY_Y_COORDINATE_CHECKING_CONSTANT * super.getWidth();
   }
   
   private static final int SQUARE_BODY_Y_COORDINATE_CHECKING_CONSTANT = 2;
   private int score;
}
