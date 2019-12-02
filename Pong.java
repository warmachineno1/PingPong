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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A program that allows a user to edit a scene composed
 * of shapes.
 * @author Mussie Habtemichael
 * @version 6 December 2015
 */
public class Pong
{
   private static final String DIFFICULTY = "Difficulty Level: ";
   private static final String RESTART = "Restart";
   private static final String SCORE_LABEL = "Score: 0";
   private static final String LIVES_LABEL = "   Lives Left: 3";
   
   private static final int FRAME_WIDTH = 800;
   private static final int FRAME_HEIGHT = 600;
   
   public static final int DISPLAY_TOP_EDGE_CONSTANT = 40;
   public static final int DISPLAY_TOP_SIDE_CONSTANT = 325;
   public static final int DISPLAY_BOTTOM_SIDE_CONSTANT = 335;
   
   private static final int DEFAULT_GAME_LIST_INDEX_CONSTANT = 0;
   private static final int PADDLE_VERTICAL_LOW_VELOCITY_CONSTANT = 15;
   private static final int PADDLE_VERTICAL_HIGH_VELOCITY_CONSTANT = 25;
   
   private static final int TIMER_DELAY_CONSTANT = 5;
   private static final int EASY_MODE_THREAD_DELAY_CONSTANT = 3;
   private static final int MEDIUM_MODE_THREAD_DELAY_CONSTANT = 2;
   private static final int HARD_MODE_THREAD_DELAY_CONSTANT = 1;
   
   private static final int EASY_MODE_LIVES_LEFT_CONSTANT = 3;
   private static final int MEDIUM_MODE_LIVES_LEFT_CONSTANT = 5;
   private static final int HARD_MODE_LIVES_LEFT_CONSTANT = 7;
   
   private static final int PADDLE_X_POSITION_CONSTANT = 50;
   private static final int PADDLE_Y_POSITION_CONSTANT = 153;
   private static final int PADDLE_WIDTH_CONSTANT = 10;
   private static final int PADDLE_HEIGHT_CONSTANT = 100;
   private static final int PADDLE_X_COORDINATE_TRANSLATION_CONSTANT = 0;
   
   private static final int CIRCLE_X_POSITION_CONSTANT = 650;
   private static final int CIRCLE_Y_POSITION_CONSTANT = 180;
   private static final int CIRCLE_WIDTH_CONSTANT = 50;
   private static final int CIRCLE_HEIGHT_CONSTANT = 50;
   
   private static final int RIGHT_EDGE_X_POSITION_CONSTANT = 10;
   private static final int RIGHT_EDGE_Y_POSITION_CONSTANT = 10;
   private static final int RIGHT_EDGE_WIDTH_CONSTANT = 20;
   private static final int RIGHT_EDGE_HEIGHT_CONSTANT = 450;
   
   private static final int INNER_RECTANGLE_X_POSITION_CONSTANT = 30;
   private static final int INNER_RECTANGLE_Y_POSITION_CONSTANT = 30;
   private static final int INNER_RECTANGLE_WIDTH_CONSTANT = 705;
   private static final int INNER_RECTANGLE_HEIGHT_CONSTANT = 405;
   
   private static final int OUTER_RECTANGLE_X_POSITION_CONSTANT = 10;
   private static final int OUTER_RECTANGLE_Y_POSITION_CONSTANT = 10;
   private static final int OUTER_RECTANGLE_WIDTH_CONSTANT = 750;
   private static final int OUTER_RECTANGLE_HEIGHT_CONSTANT = 450;
   
   private static final String START = "Start";
   private static final String PAUSE = "Pause";
   private static final String EASY = "Easy";
   private static final String MEDIUM = "Medium";
   private static final String HARD = "Hard";
   
   private int paddleVerticalTranslationFactor;
   private Timer timer;
   private boolean threadStarted;
   
   /**
    * Display the scene to be edited.
    */
   public void displayScene()
   {
      JFrame frame = new JFrame();
      
      final String[] gameDifficultyStrings = {EASY, MEDIUM, HARD};
      final SceneComponent scene = new SceneComponent();
      final RectangleShape paddle =new RectangleShape(PADDLE_X_POSITION_CONSTANT
            , PADDLE_Y_POSITION_CONSTANT, PADDLE_WIDTH_CONSTANT, 
            PADDLE_HEIGHT_CONSTANT);
      final CircleMover movingCircle = new CircleMover(CIRCLE_X_POSITION_CONSTANT, 
            CIRCLE_Y_POSITION_CONSTANT , CIRCLE_WIDTH_CONSTANT, 
            CIRCLE_HEIGHT_CONSTANT);

      final JComboBox<String> gameDifficultyList = new JComboBox<String>
      (gameDifficultyStrings);
      final RectangleShape rightEdge = new RectangleShape(
            RIGHT_EDGE_X_POSITION_CONSTANT, RIGHT_EDGE_Y_POSITION_CONSTANT, 
            RIGHT_EDGE_WIDTH_CONSTANT, RIGHT_EDGE_HEIGHT_CONSTANT);
      movingCircle.setPaddle(paddle);
      
      scene.setGameBall(movingCircle);
      final JLabel scoreLabel = new JLabel(SCORE_LABEL);
      final JLabel livesLabel = new JLabel(LIVES_LABEL);
      scene.setGameStats(scoreLabel, livesLabel);
      final RectangleShape innerRectangle = new RectangleShape(
            INNER_RECTANGLE_X_POSITION_CONSTANT, INNER_RECTANGLE_Y_POSITION_CONSTANT, 
            INNER_RECTANGLE_WIDTH_CONSTANT, INNER_RECTANGLE_HEIGHT_CONSTANT);
      final RectangleShape outerRectangle = new RectangleShape(
            OUTER_RECTANGLE_X_POSITION_CONSTANT, OUTER_RECTANGLE_Y_POSITION_CONSTANT, 
            OUTER_RECTANGLE_WIDTH_CONSTANT, OUTER_RECTANGLE_HEIGHT_CONSTANT);
      
      // The timer listener can be an anonymous class
      ActionListener listener = new ActionListener() 
      {
          public void actionPerformed(ActionEvent event)
          {
             scene.repaint();
             if(movingCircle.getLivesLeft() == 0)
             {
                outerRectangle.setBodyColor(Color.RED);
             }
          }
      };

      final int DELAY = TIMER_DELAY_CONSTANT; // Milliseconds between timer ticks
      timer = new Timer(DELAY, listener);

      JButton startButton = new JButton(START);
      startButton.addActionListener(new
         ActionListener()
         {
            /**
             * Start the game.
             * @param event A semantic event which indicates that a 
             * component-defined action occurred
             */
            public void actionPerformed(ActionEvent event)
            {
               movingCircle.setGameStatus(true);
               timer.start();
               if(!threadStarted)
               {
                  new Thread(movingCircle).start();
                  threadStarted = true;
               }
               gameDifficultyList.setEnabled(false);
            }
         });
      
      paddleVerticalTranslationFactor = PADDLE_VERTICAL_LOW_VELOCITY_CONSTANT;
      startButton.addKeyListener( new KeyAdapter()
      {
         /**
          * Add a key listener to the start button that can listen 
          * to numpad arrow keys.
          * @param e A semantic event which indicates that a 
          * component-defined action occurred
          */
         public void keyPressed(KeyEvent e)
         {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_UP)
            {
               if(paddle.getYPosition() <= DISPLAY_TOP_EDGE_CONSTANT)
               {
                  paddle.setXPosition(paddle.getXPosition());
                  paddle.setYPosition(paddle.getYPosition());
                  paddle.clearMovingUp();
               }
               else if(paddle.getYPosition() <= DISPLAY_BOTTOM_SIDE_CONSTANT)
               {
                   paddle.setMovingUp();
               }

               if(paddle.movingUp())
               {
                   paddle.translate(PADDLE_X_COORDINATE_TRANSLATION_CONSTANT , 
                         -paddleVerticalTranslationFactor);
               }
               else
               {
                   paddle.translate(PADDLE_X_COORDINATE_TRANSLATION_CONSTANT , 
                         paddleVerticalTranslationFactor);
               }           

            }
            else if (keyCode == KeyEvent.VK_DOWN)
            {
               if(paddle.getYPosition() >= DISPLAY_TOP_SIDE_CONSTANT)
               {
                  paddle.setXPosition(paddle.getXPosition());
                  paddle.setYPosition(paddle.getYPosition());
                  paddle.clearMovingUp();;
               }
               else if(paddle.getYPosition() <= DISPLAY_BOTTOM_SIDE_CONSTANT)
               {
                   paddle.setMovingUp();
               }

               if(paddle.movingUp())
               {
                   paddle.translate(PADDLE_X_COORDINATE_TRANSLATION_CONSTANT , 
                         paddleVerticalTranslationFactor);
               }
               else
               {
                   paddle.translate(PADDLE_X_COORDINATE_TRANSLATION_CONSTANT ,
                         -paddleVerticalTranslationFactor);
               }
            }
         }
      });

      JButton pauseButton = new JButton(PAUSE);
      pauseButton.addActionListener(new
         ActionListener()
         {
            /**
             * Pauses the game.
             * @param event A semantic event which indicates that a 
             * component-defined action occurred
             */
            public void actionPerformed(ActionEvent event)
            {
               timer.stop();
               movingCircle.setGameStatus(false);
               threadStarted = false;
            }
         });
      
      JButton restartButton = new JButton(RESTART);
      
      restartButton.addActionListener(new
            ActionListener()
            {
               /**
                * Restarts the game.
                * @param event A semantic event which indicates that a 
                * component-defined action occurred
                */
               public void actionPerformed(ActionEvent event)
               {
                  timer.stop();
                  movingCircle.clearStats();
                  scoreLabel.setText("Score: " + movingCircle.getScore());
                  livesLabel.setText("   Lives Left: " + 
                        movingCircle.getLivesLeft());
                  movingCircle.setGameStatus(false);
                  rightEdge.setBodyColor(Color.red);
                  outerRectangle.setBodyColor(Color.blue);
                  threadStarted = false;
                  paddle.setXPosition(PADDLE_X_POSITION_CONSTANT);
                  paddle.setYPosition(PADDLE_Y_POSITION_CONSTANT);
                  movingCircle.setXPosition(CIRCLE_X_POSITION_CONSTANT);
                  movingCircle.setYPosition(CIRCLE_Y_POSITION_CONSTANT);
                  gameDifficultyList.setEnabled(true);
                  gameDifficultyList.setSelectedIndex(0);
                  scene.repaint();
               }
            });
      
      JLabel difficultyLabel = new JLabel(DIFFICULTY);
      
      gameDifficultyList.setSelectedIndex(DEFAULT_GAME_LIST_INDEX_CONSTANT);
      gameDifficultyList.addActionListener( new ActionListener()
      {
         /**
          * Displays a game difficulty combobox.
          * @param event A semantic event which indicates that a 
          * component-defined action occurred
          */
         public void actionPerformed(ActionEvent event)
         {
            @SuppressWarnings("unchecked")
            JComboBox<String> cb = (JComboBox<String>) event.getSource();
            String difficulty = (String)cb.getSelectedItem();
            if(difficulty.equals(EASY))
            {
               movingCircle.setLivesLeft(EASY_MODE_LIVES_LEFT_CONSTANT );
               livesLabel.setText("   Lives Left: " + 
                     movingCircle.getLivesLeft());
               movingCircle.setThreadDelay(EASY_MODE_THREAD_DELAY_CONSTANT);
               paddleVerticalTranslationFactor = 
                     PADDLE_VERTICAL_LOW_VELOCITY_CONSTANT;
            }
            
            else if (difficulty.equals(MEDIUM))
            {
               movingCircle.setLivesLeft(MEDIUM_MODE_LIVES_LEFT_CONSTANT );
               livesLabel.setText("   Lives Left: " + 
                     movingCircle.getLivesLeft());
               movingCircle.setThreadDelay(MEDIUM_MODE_THREAD_DELAY_CONSTANT);
               paddleVerticalTranslationFactor = 
                     PADDLE_VERTICAL_LOW_VELOCITY_CONSTANT;
            }
            else
            {
               movingCircle.setLivesLeft(HARD_MODE_LIVES_LEFT_CONSTANT );
               livesLabel.setText("   Lives Left: " + 
                     movingCircle.getLivesLeft());
               movingCircle.setThreadDelay(HARD_MODE_THREAD_DELAY_CONSTANT);
               paddleVerticalTranslationFactor = 
                     PADDLE_VERTICAL_HIGH_VELOCITY_CONSTANT;
            }
            scene.repaint();
         }
      });
      
      

      JPanel topPanel = new JPanel();
      topPanel.add(scoreLabel);
      topPanel.add(livesLabel);
      topPanel.add(startButton);
      topPanel.add(pauseButton);
      topPanel.add(restartButton);
      
      JPanel difficultyPanel = new JPanel();
      difficultyPanel.add(difficultyLabel);
      difficultyPanel.add(gameDifficultyList);
      
      outerRectangle.setBodyColor(Color.blue);
      innerRectangle.setBodyColor(Color.black);
      scene.setGameDisplay(innerRectangle);
      rightEdge.setBodyColor(Color.red);
      movingCircle.setRightEdge(rightEdge);
      paddle.setBodyColor(Color.blue);
      
      scene.add(outerRectangle);
      scene.add(innerRectangle);
      scene.add(rightEdge);
      scene.add(paddle);
      scene.add(movingCircle);
      scene.setTimer(timer);
      
      frame.setResizable(false);
      frame.add(topPanel, BorderLayout.NORTH);
      frame.add(scene, BorderLayout.CENTER);
      frame.add(difficultyPanel, BorderLayout.SOUTH);
      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      
      JOptionPane.showMessageDialog(frame,
            "Use the numeric keys for moving the paddle. Key Up for going up"
            + " while Key Down for going down.");

      
   }
   
   public static void main(String[] args)
   {
      Pong editableScene = new Pong();
      editableScene.displayScene();
   }
}
