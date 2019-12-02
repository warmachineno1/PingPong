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
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * A shape that is a part of a scene.
 * @author Mussie Habtemichael
 * @version 6 December 2015
 */
public interface SceneShape
{  
   /**
    * Sets the X-coordinate of the top left corner of the shape
    * @param xCoordinate the X-coordinate of the top left corner of the shape
    */
   void setXPosition(int xCoordinate);
   
   /**
    * Sets the Y-coordinate of the top left corner of the shape
    * @param yCoordinate the Y-coordinate of the top left corner of the shape
    */
   void setYPosition(int yCoordinate);
   
   /**
    * Gets the X-coordinate of the top left corner of the shape
    * @return the X-coordinate of the top left corner of the shape
    */
   int getXPosition();
   
   /**
    * Gets the Y-coordinate of the top left corner of the shape
    * @return the Y-coordinate of the top left corner of the shape
    */
   int getYPosition();
   
   /**
    * Gets the width of the shape.
    * @return the width of the shape
    */
   int getWidth();
   
   /**
    * Gets the height of the shape.
    * @return the height of the shape
    */
   int getHeight();
   
   /**
    * Gets the body color of the shape.
    * @return the body color of the shape
    */
   Color getBodyColor();
   
   /**
    * Sets the body color of the shape
    * @param aColor the color to fill
    */
   void setBodyColor(Color aColor);
   
   /**
    * Translates this item by a given amount.
    * @param dx the amount to translate in x-direction
    * @param dy the amount to translate in y-direction
    */
   void translate(int dx, int dy);
   
   /**
    * Draws this item.
    * @param g2 the graphics context
    */
   void draw(Graphics2D g2);
   
   /**
    * Draws the "Game over" and "Game Score" texts
    * @param g2 the graphics context
    */
   void drawGameOver(Graphics2D g2);
   
   /**
    * Sets the game score
    * @param the score to set
    */
   void setHighScore(int aScore);
   
   /**
    * Tests whether this item contains a given point.
    * @param p a point
    * @return true if this item contains p
    */
   boolean contains(Point2D p);
}

