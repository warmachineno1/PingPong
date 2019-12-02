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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * A component that shows a scene composed of shapes.
 * @author Mussie Habtemichael
 * @version 6 December 2015
 */
@SuppressWarnings("serial")

public class SceneComponent extends JComponent
{  
   private ArrayList<AbstractSceneShape> shapes;
   private CircleMover movingCircle;
   private JLabel scoreLabel;
   private JLabel livesLabel;
   private RectangleShape innerRectangle;
   private Timer timer;
   
   private static final String SCORE_LABEL = "Score: 0";
   private static final String LIVES_LABEL = "   Lives Left: 3";
   private static final int LEFT_OF_THE_BOUNDING_RECTANGLE = 650;
   private static final int TOP_OF_THE_BOUNDING_RECTANGLE = 180;
   private static final int WIDTH_OF_THE_BOUNDING_RECTANGLE = 20;
   private static final int HEIGHT_OF_THE_BOUNDING_RECTANGLE = 20;
   
   /**
    * Construct a SceneComponent object
    */
   public SceneComponent()
   {
      shapes = new ArrayList<AbstractSceneShape>();
      movingCircle= new CircleMover(LEFT_OF_THE_BOUNDING_RECTANGLE, 
            TOP_OF_THE_BOUNDING_RECTANGLE , WIDTH_OF_THE_BOUNDING_RECTANGLE, 
            HEIGHT_OF_THE_BOUNDING_RECTANGLE);
      
      scoreLabel = new JLabel(SCORE_LABEL);
      livesLabel = new JLabel(LIVES_LABEL);
   }
   
   /**
    * Adds a shape to the scene.
    * @param s the shape to add
    */
   public void add(AbstractSceneShape s)
   {
      shapes.add(s);
   }
   
   /**
    * Sets the moving ball of this scene
    * @param movingBall the ball to store
    */
   public void setGameBall(CircleMover movingBall)
   {
      movingCircle = movingBall;
   }
   
   /**
    * Sets the score and lives label to be used in the scene
    * @param score the game score label
    * @param lives the lives left label
    */
   public void setGameStats(JLabel score, JLabel lives)
   {
      scoreLabel = score;
      livesLabel = lives;
   }
   
   /**
    * Sets the "innerRectangle" to be used in the scene
    * @param innerRec the inner Rectangle of the scene
    */
   public void setGameDisplay(RectangleShape innerRec)
   {
      innerRectangle = innerRec;
   }
   
   /**
    * Sets the game timer of the scene
    * @param aTimer the game timer
    */
   public void setTimer(Timer aTimer)
   {
      timer = aTimer;
   }

   /**
    * Calls the UI delegate's paint method, if the UI delegate is non-null.
    * @param g the graphics context
    */
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      for (AbstractSceneShape s : shapes)
      {
         s.draw(g2);
      }
      if(movingCircle.isScoreUpdated())
      {
         int score = movingCircle.getScore();
         scoreLabel.setText("Score: " + score);
         innerRectangle.setHighScore(score);
      }
      livesLabel.setText("   Lives Left: " + movingCircle.getLivesLeft());
      
      if( movingCircle.getLivesLeft() == 0 )
      {
         innerRectangle.drawGameOver(g2);
         movingCircle.setGameStatus(false);
         timer.stop();
      }
   }

}
