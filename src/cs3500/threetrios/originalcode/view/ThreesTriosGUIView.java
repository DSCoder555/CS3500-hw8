package cs3500.threetrios.originalcode.view;

import java.io.IOException;

import javax.swing.*;

import cs3500.threetrios.originalcode.model.ReadOnlyThreeTriosModel;

/**
 * Defines the behaviors for a GUI view of ThreeTrios such as adding a feature listener, displaying
 * the GUI, and outputting certain messages to indicate whether a valid or invalid action was made.
 */
public class ThreesTriosGUIView extends JFrame implements ThreeTriosGUI {

  private final GameFrame frame;
  private JOptionPane messageFrame;

  /**
   * Sets up the model.
   *
   * @param model The model to set up.
   * @throws IllegalArgumentException if model is null.
   * @throws IOException              if the setting up of the GUI did not work.
   */
  public ThreesTriosGUIView(ReadOnlyThreeTriosModel model)
          throws IOException {
    if (model == null) {
      throw new IllegalArgumentException("model or model listener cannot be null");
    }
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame = new GameFrame(model);
  }

  @Override
  public void addFeatureListener(ViewFeatures features) {
    this.frame.addFeaturesListener(features);
  }


  /**
   * Delegates to the game frame class to begin rendering.
   */
  public void startRendering() {
    this.frame.startRendering();
  }


  @Override
  public void display(boolean show) {
    this.frame.setVisible(show);
  }

  @Override
  public void advance() {
    this.frame.update();
  }

  @Override
  public void error() {
    this.frame.error();
  }

  @Override
  public void displayMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException("Message cannot be null");
    }
    JOptionPane.showMessageDialog(messageFrame, message);
  }

  @Override
  public void errorMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException("Message cannot be null");
    }
    JOptionPane.showMessageDialog(messageFrame, message);
  }

}
