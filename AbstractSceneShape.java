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
import java.awt.Color;

/**
 * A shape that manages its scene state.
 * @author Mussie Habtemichael
 * @version 6 December 2015
 */
public abstract class AbstractSceneShape implements SceneShape
{
   /**
    * Fill in the state information of a constructed shape.
    * @param x the left of the bounding rectangle
    * @param y the top of the bounding rectangle
    * @param width the width of the bounding rectangle
    * @param height the height of the bounding rectangle
    */
   public AbstractSceneShape(int x, int y, int width, int height)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }
   
   /**
    * Sets the X-coordinate of the top left corner of the shape.
    * @param xCoordinate the X-coordinate of the top left corner of the shape
    */
   public void setXPosition(int xCoordinate)
   {
      x = xCoordinate;
   }
   
   /**
    * Sets the Y-coordinate of the top left corner of the shape.
    * @param yCoordinate the Y-coordinate of the top left corner of the shape
    */
   public void setYPosition(int yCoordinate)
   {
      y = yCoordinate;
   }
   
   /**
    * Gets the X-coordinate of the top left corner of the shape.
    * @return the X-coordinate of the top left corner of the shape
    */
   public int getXPosition()
   {
      return x;
   }
   
   /**
    * Gets the Y-coordinate of the top left corner of the shape.
    * @return the Y-coordinate of the top left corner of the shape
    */
   public int getYPosition()
   {
      return y;
   }
   
   /**
    * Gets the width of the shape.
    * @return the width of the shape
    */
   public int getWidth()
   {
      return width;
   }
   
   /**
    * Gets the height of the shape.
    * @return the height of the shape
    */
   public int getHeight()
   {
      return height;
   }
   
   /**
    * Gets the body color of the shape.
    * @return the body color of the shape
    */
   public Color getBodyColor()
   {
      return shapeBodyColor;
   }
   
   /**
    * Sets the body color of the shape
    * @param aColor the color to fill
    */
   public void setBodyColor(Color aColor)
   {
      shapeBodyColor = aColor;
   }
   
   /**
    * Moves the shape by a given amount.
    * @param dx the amount to translate in x-direction
    * @param dy the amount to translate in y-direction
    */
   public void translate(int dx, int dy)
   {
      x += dx;
      y += dy;
   }
   
   /**
    * Change the direction this rectangle is moving
    */
   public void reverseDirection()
   {
       movingUp = movingUp ? false : true;
   }

   /**
    * Set the direction to move down
    */
   public void clearMovingUp()
   {
       movingUp = false;
   }

   /**
    * Set the direction to move up
    */
   public void setMovingUp()
   {
       movingUp = true;
   }

   /**
    * Determine which direction the rectangle is moving
    * @return the boolean status of moving up
    */
   public boolean movingUp()
   {
       return movingUp;
   }
   
   
   // The left of the bounding rectangle
   private int x;
   
   // The top of the bounding rectangle
   private int y;
   
   private int width;
   
   private int height;
   
   private boolean movingUp;
   
   private Color shapeBodyColor;
}
