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
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * A circle that moves.
 * @author Mussie Habtemichael
 * @version 6 December 2015
 */
public class CircleMover extends AbstractSceneShape implements Runnable
{
   private int totalScore;
   private int livesLeft;
   private boolean gameInProgress;
   private boolean scoreUpdated;
   private RectangleShape paddle;
   private RectangleShape rightEdge;
   private int hit;
   private int paddleTopTranslationFactor;
   private int paddleBottomTranslationFactor;
   private int delay;
   private boolean movingRight;
   
   private static final int PADDLE_TOP_CONSTANT= 60;
   private static final int PADDLE_BOTTOM_CONSTANT = 90;
   private static final int LIVES_LEFT_CONSTANT = 3;
   private static final int PADDLE_HIT_CONSTANT = 0;
   private static final int PADDLE_LEFT_SIDE_HIT_CONSTANT = 1;
   private static final int TOTAL_SCORE_CONSTANT = 0;
   private static final int SCORE_CONSTANT = 10;
   public static final int CIRCLE_POSITION_TRANSLATION_CONSTANT = 5;
   public static final int CIRCLE_SIZE_DECREMENT_CONSTANT = -10;
   public static final int X_COORDINATE_TRANSLATION_CONSTANT = 1;
   public static final int Y_COORDINATE_TRANSLATION_CONSTANT = 1;
   public static final int DISPLAY_RIGHT_SIDE_CONSTANT = 735;
   public static final int DISPLAY_LEFT_SIDE_CONSTANT = 30;
   public static final int DISPLAY_TOP_SIDE_CONSTANT = 30;
   public static final int DISPLAY_BOTTOM_SIDE_CONSTANT = 385;
   private static final int THREAD_DELAY_CONSTANT = 3;
   private static final int CIRCLE_BODY_Y_COORDINATE_CHECKING_CONSTANT = 2;
   
   public CircleMover(int x, int y, int width, int height)
   {
      super(x, y, width, height);
      Random rng = new Random();
      movingRight = rng.nextInt(2) == 0 ? false : true;
      delay = THREAD_DELAY_CONSTANT;
      totalScore = TOTAL_SCORE_CONSTANT;
      hit = PADDLE_HIT_CONSTANT;
      livesLeft = LIVES_LEFT_CONSTANT;
      paddleTopTranslationFactor = PADDLE_TOP_CONSTANT;
      paddleBottomTranslationFactor = PADDLE_BOTTOM_CONSTANT;
   }
   
   public void run()
   {
       try
       {
           while(gameInProgress)
           {
               if(getXPosition() + getWidth() >= DISPLAY_RIGHT_SIDE_CONSTANT)
               {
                  setXPosition(getXPosition());
                  setYPosition(getYPosition());
                  clearMovingRight();
                  rightEdge.setBodyColor(Color.red);
                  hit = PADDLE_HIT_CONSTANT;
               }
               
               else if(getXPosition() <= DISPLAY_LEFT_SIDE_CONSTANT)
               {
                   setMovingRight();
                   rightEdge.setBodyColor(Color.orange);
                   livesLeft--;
                   hit = PADDLE_LEFT_SIDE_HIT_CONSTANT;
                   clearScoreUpdated();
               }

               if(movingRight())
               {
                   translate(X_COORDINATE_TRANSLATION_CONSTANT, 0);
               }
               else
               {
                   translate(- X_COORDINATE_TRANSLATION_CONSTANT ,0);
               }
               
               if(getYPosition() <= DISPLAY_TOP_SIDE_CONSTANT)
               {
                  setXPosition(getXPosition());
                  setYPosition(getYPosition());
                  clearMovingUp();
               }
               else if(getYPosition() >= DISPLAY_BOTTOM_SIDE_CONSTANT)
               {
                   setMovingUp();
               }

               if(movingUp())
               {
                  translate(0 , - Y_COORDINATE_TRANSLATION_CONSTANT);
               }
               else
               {
                  translate(0 , Y_COORDINATE_TRANSLATION_CONSTANT);
               }
               
               if (contains(new Point(paddle.getXPosition(), 
                     paddle.getYPosition() + paddleTopTranslationFactor) ) 
                     || contains(new Point(paddle.getXPosition(),
                           paddle.getYPosition() + 
                           paddleBottomTranslationFactor) ))
               {
                  setMovingRight();
                  setScoreUpdated();
               }
           
               Thread.sleep(delay);
           }
       }
       catch(InterruptedException exception) 
       {
           // we're done
       }
   }
   
   /**
    * Sets the time delay of the thread
    * @param threadDelay the delay of the thread
    */
   public void setThreadDelay(int threadDelay)
   {
      delay = threadDelay;
   }
   
   /**
    * Set the paddle used in the game
    * @param pad the paddle used in the game
    */
   public void setPaddle(RectangleShape pad)
   {
       paddle = pad;
   }
   
   /**
    * Sets the right edge displayed in the scene
    * @param right the right edge of the inner Rectangle
    */
   public void setRightEdge(RectangleShape right)
   {
       rightEdge = right;
   }
   
   /**
    * Gets the current score of the game
    * @return the total score of the game
    */
   public int getScore()
   {
       return totalScore;
   }
   

   /**
    * Change the direction this rectangle is moving
    */
   public void reverseDirection()
   {
       movingRight = movingRight ? false : true;
   }

   /**
    * Set the direction to move left
    */
   public void clearMovingRight()
   {
       movingRight = false;
   }
   
   /**
    * Determines if the score is updated
    * @return the boolean status of an updated score
    */
   public boolean isScoreUpdated()
   {
       return scoreUpdated;
   }
   
   /**
    * Clears the score updated boolean status of the game
    */
   public void clearScoreUpdated()
   {
       scoreUpdated = false;
   }
   
   /**
    * Updates the score status of the game
    */
   public void setScoreUpdated()
   {
      scoreUpdated  = true;
      if (hit == PADDLE_HIT_CONSTANT)
      {
         totalScore = totalScore + SCORE_CONSTANT;
         hit++;
      }
   }
   
   /**
    * Clears the player's game stats
    */
   public void clearStats()
   {
       totalScore = TOTAL_SCORE_CONSTANT;
       livesLeft = LIVES_LEFT_CONSTANT;
   }

   /**
    * Set the direction to move right
    */
   public void setMovingRight()
   {
       movingRight = true;
   }
   
   /**
    * Determine which direction the rectangle is moving
    * @return the boolean status of moving to the right
    */
   public boolean movingRight()
   {
       return movingRight;
   }
   
   /**
    * Sets the "game started" boolean status of the game
    */
   public void setGameStatus(boolean gameStarted)
   {
       gameInProgress = gameStarted;
   }
   
   /**
    * Sets the lives left in the game
    */
   public void setLivesLeft(int lives)
   {
       livesLeft = lives;
   }
   
   /**
    * Gets the lives left in the game
    * @return the lives left
    */
   public int getLivesLeft()
   {
       return livesLeft;
   }
   
   /**
    * Draws the shape.
    * @param g2 the graphics context
    */
   public void draw(Graphics2D g2)
   {
      
      Ellipse2D.Double movingBall = new Ellipse2D.Double(super.getXPosition() + 
            CIRCLE_POSITION_TRANSLATION_CONSTANT, 
            super.getYPosition() + CIRCLE_POSITION_TRANSLATION_CONSTANT, 
            super.getWidth() + 
            CIRCLE_SIZE_DECREMENT_CONSTANT, super.getHeight() 
            + CIRCLE_SIZE_DECREMENT_CONSTANT);
      g2.setColor(Color.green);
      g2.fill(movingBall);
      g2.draw(movingBall);
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
      g2.drawString("Game Score: " , super.getXPosition() + 500, 
            super.getYPosition() + 40);
   }
   
   /**
    * Sets the game score
    * @param the score to set
    */
   public void setHighScore(int aScore)
   {
      totalScore = aScore;
   }
   
   /**
    * Tests whether this item contains a given point.
    * @param p a point
    * @return true if this item contains p
    */
   public boolean contains(Point2D p)
   {
      return super.getXPosition() <= p.getX() && p.getX() <= 
            super.getXPosition() + super.getWidth() && super.getYPosition() 
            <= p.getY() && p.getY() <= super.getYPosition()
         + CIRCLE_BODY_Y_COORDINATE_CHECKING_CONSTANT * super.getWidth();
   }
}